package de.fraunhofer.iao.service;
import java.time.Instant;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import de.fraunhofer.iao.GenerateValues.FlexibilityGeneratorInPast;
import de.fraunhofer.iao.GenerateValues.SetflexibilityPowerValues;
import de.fraunhofer.iao.domain.Company;
import de.fraunhofer.iao.domain.IAOFlexAnlage;
import de.fraunhofer.iao.domain.IAOGeneratorConfiguration;
import de.fzi.domain.FlexibilityImportObject;
/**
 * * Handles the Stage Two, write in a File if there is not existing one, *
 * otherwise it´s updating the values in quarter Hours intervals.
 */
public class RestStageTwoTimerTask extends TimerTask {
	private static Logger log = Logger.getLogger(RestStageTwoTimerTask.class);
	private IAOGeneratorConfiguration config;
	private Company comp;
	private IAOFlexAnlage iaoFlexAnlage;
	public RestStageTwoTimerTask(IAOGeneratorConfiguration config, Company comp,
			IAOFlexAnlage iaoFlexAnlage) {
		this.config = config;
		this.comp = comp;
		this.iaoFlexAnlage = iaoFlexAnlage;
	}
	/**
	 * * Write in a File if it´s the first Time and there is no existing file.
	 */
	public void generateFile(IAOGeneratorConfiguration config, Company comp, IAOFlexAnlage iaoFlexAnlagen) throws Throwable {
		log.info("Write Timestamps and Values in a File.");
		FlexibilityGeneratorInPast flexibility = new FlexibilityGeneratorInPast();
		flexibility.generateValues(Instant.now(), config, comp, iaoFlexAnlage);
	}
	/**
	 * * Runs in quarter hours intervals and is organized with an Timer. Its *
	 * checking the actual Company if therefore is existing a file. If there is
	 * * a file they just generate new Values. If there is no file existing it
	 * has * to generate a new one. * * @see java.util.TimerTask#run()
	 */
	@Override
	public void run() {
		log.info("Run Job Stage Two with Timer.");
		if (config.getIfFileExists() == null) {
			try {
				generateFile(config, comp, iaoFlexAnlage);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		} else {
			SetflexibilityPowerValues gener = new SetflexibilityPowerValues();
			log.info("Push Stage Two to Rest Service.");
			comp.getAnlagen().forEach(anlage -> {
				FlexibilityImportObject updateDTO = new FlexibilityImportObject();
				updateDTO.setUid(comp.getuId());
				updateDTO.setFid(anlage.getfId());
				gener.generateValues(config, updateDTO);
				HttpResponse<String> response = null;
				try {
					response = Unirest
							.post("http://dsm.acosnms.de/DSM/rest/receiveDataFromClient")
							.body(updateDTO).asString();
				} catch (UnirestException e) {
					e.printStackTrace();
				}
				log.info("Status code:" + response.getStatus());
				log.info("Response text:" + response.getBody());
			});
		}
	}
}