package de.fraunhofer.iao.service;
import org.apache.log4j.Logger;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import de.fzi.domain.FlexibilityImportObject;
/**
 * * Should be executed once to pass stage 1 data to server. After it has pushed
 * * the Data from Stage One A or B to the Rest Interface the Program becomes *
 * terminated. * * @author Lausterer *
 */
public class RestStageOneTask {
	private static Logger log = Logger.getLogger(RestStageOneTask.class);
	private FlexibilityImportObject flexValues;
	public RestStageOneTask(FlexibilityImportObject flexValues) {
		this.flexValues = flexValues;
	}
	public void run() {
		log.info("Stage One pushe to REST.");
		try {
			//Unirest.post("http://localhost:8280/DSM/rest/receiveDataFromClient")
			Unirest.post("http://dsm.acosnms.de/DSM/rest/receiveDataFromClient")
					.body(flexValues).asJson();
			System.exit(0);
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}
}