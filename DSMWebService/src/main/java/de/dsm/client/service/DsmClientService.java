//REST Endpoint muss nur Werte wegspeichern
package de.dsm.client.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.dsm.data.dto.FlexibilityImportObject;
import de.dsm.data.entity.Company;
import de.dsm.data.entity.Flexibility;
import de.dsm.data.entity.FlexiblePower;
import de.dsm.data.service.FlexibilityService;
import de.dsm.data.service.FlexiblePowerService;
import de.dsm.services.ProcessorResultCodes;
import de.dsm.services.flex.FlexibilePowerProcessorService;

/**
 * Class contains REST endpoints for receiving push messages from DSM client.
 * 
 * @author dsm
 *
 */
@Controller
@Transactional
public class DsmClientService {

	private static Logger log = Logger.getLogger(DsmClientService.class);
	
	@Autowired
	private FlexiblePowerService flexPowerService;
	@Autowired
	private FlexibilityService flexibilityService;
	@Autowired
	private FlexibilePowerProcessorService flexPowerProcessor;
	int i = 0;

	/**
	 * Test method to check if endpoint is published. Should be available under
	 * http://localhost:8280/DSM/rest/test
	 * 
	 * @return test string
	 * @throws IOException
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public String test() throws IOException {
		
		FlexiblePower flexPwr = flexPowerService.getFlexiblePower(1003);
		if(flexPwr != null) {
			log.info("Flexible Power: " +flexPwr.getSid() + " - " + flexPwr.getCategory());
			Flexibility flex = flexPwr.getFlexibility();
			log.info("Flexibility: " + flex.getId() + " - " 
								+ flex.getP015n() + " - "
								+ flex.getP015p() + " - "
								+ flex.getP060n() + " - "
								+ flex.getP060p() + " - "
								+ flex.getP240n() + " - "
								+ flex.getP240p() + " - "
								+ flex.getActivationDuration() + " - "
								+ flex.getNegLeistung() + " - "
								+ flex.getPosLeistung() + " - "
								+ flex.getTeilbarkeit()
					);
			Company comp = flex.getCompany();
			log.info("Company: " + comp.getCompanyLabel() + " - " 
								+ comp.getCompId());
		}
		
		return "test succesful";
	}

	/**
	 * REST endpoint to receive data, which is pushed by client. Prints message to console currently.
	 * 
	 * @param http
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/receiveDataFromClient", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
	@ResponseBody
	public String receiveDataFromClient(@RequestBody String body,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
		//print first 500 chars of http body to console to check if data was received
		long stringLength = body.length();
		log.info(body.substring(0, (int) Math.min(500, stringLength)));
		
		//Deserialisierung
		String json = body;
		FlexibilityImportObject freshlyImported = new ObjectMapper().readValue(json, FlexibilityImportObject.class);
		
		// fetch the corresponding flexibility entity
		Flexibility flex = flexibilityService.getById(freshlyImported.getFid());

		if(flex != null) {
			ProcessorResultCodes result = flexPowerProcessor.processNewValues(flex, freshlyImported.getZeitreihen());
			log.error(result.getDescription());
		
			switch(result.getHttpResponse()) {
				case("200"):
					response.setStatus(HttpServletResponse.SC_OK);
					return "{\"state\":\"ok\",\"description\":\""+result.getDescription()+"\" }";
				case("400"):
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					return "{\"state\":\"error\",\"description\":\""+result.getDescription()+"\" }";
				case("404"):
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
					return "{\"state\":\"error\",\"description\":\""+result.getDescription()+"\" }";
				default:
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					return "{\"state\":\"error\",\"description\":\""+result.getDescription()+"\" }";
			}
		} else {
			log.error("Flexibility for id=" + freshlyImported.getFid() + " could not be found");
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "{\"state\":\"error\",\"description\":\"unknown flexibility\" }";
		}
	}
	
	public FlexibilityService getFlexibilityService() {
		return flexibilityService;
	}
	
	public void setFlexibilityService(FlexibilityService flexibilityService) {
		this.flexibilityService = flexibilityService;
	}

	public FlexiblePowerService getFlexPowerService() {
		return flexPowerService;
	}

	public void setFlexPowerService(FlexiblePowerService flexPowerService) {
		this.flexPowerService = flexPowerService;
	}
}
