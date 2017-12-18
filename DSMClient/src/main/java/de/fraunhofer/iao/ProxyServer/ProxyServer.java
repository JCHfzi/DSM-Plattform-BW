package de.fraunhofer.iao.ProxyServer;

import org.apache.log4j.Logger;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import de.fzi.domain.FlexibilityImportObject;

/**
 * Is for pushing the data to the Server.
 * @author Nico Lausterer
 *
 */
public class ProxyServer {
	private static Logger log = Logger.getLogger(ProxyServer.class);

	public ProxyServer() {

	}

	public void pushData(FlexibilityImportObject updateDTO) {
		
			try {
				Unirest.post("http://dsm.acosnms.de/DSM/rest/receiveDataFromClient")
						.body(updateDTO).asString();
			} catch (UnirestException e) {
				e.printStackTrace();
			}
		
		log.info("The \"File\" is pushed to the Rest Service.");
	}

}
