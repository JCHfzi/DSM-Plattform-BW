package de.fraunhofer.iao.GenerateValues;
import java.io.IOException;
import java.time.Instant;

import org.apache.log4j.Logger;

import com.mashape.unirest.http.exceptions.UnirestException;

import de.fraunhofer.iao.ProxyServer.ProxyServer;
import de.fraunhofer.iao.domain.Company;
import de.fraunhofer.iao.domain.IAOFlexAnlage;
import de.fraunhofer.iao.domain.IAOGeneratorConfiguration;
import de.fraunhofer.iao.service.RestStageTwoTimerTask;
import de.fzi.domain.FlexibilityImportObject;
import de.fzi.domain.FlexibilityPowerValues;

/**
 * * Generate the Timestamps and Values of the year ago. This i realized with an
 * TreeMap because this Map is already sorted.
 */

public class FlexibilityGeneratorInPast {
	private static Logger log = Logger
			.getLogger(FlexibilityGeneratorInPast.class);
	/**
	 * * Generate the Values and the Timestamps depend of the actual Time one
	 * Year * ago.
	 */
	public void generateValues(Instant inst, IAOGeneratorConfiguration config,
			Company comp, IAOFlexAnlage iaoFlexAnlage)	throws IOException, UnirestException {
		log.info("Generate the File. The File contains a set of Timestamps and Values.");
		int generatePastTimeInDays = 0;
		int count;
		int yearInMinutes = 35040; // Ein jahr hat 35040 viertel Stunden.
		int i = 0;
		int viertelStundeInMillis = 900000;
		long actualTimeInMillies = inst.toEpochMilli();
		Instant newTime = inst;

		FlexibilityImportObject flexibilityImportObject = new FlexibilityImportObject();

		if (config.getTimeInPast() != null) {
			generatePastTimeInDays = Integer.parseInt(config.getTimeInPast());
		}
		log.info("Wie viele Tage der Vergangenheit generiert werden sollen. resolutionInDays: "	+ generatePastTimeInDays);
		if (config.getTimeInPast() != null) {
			count = (generatePastTimeInDays * 24 * 60 / 15);
		} else {
			count = yearInMinutes;
		}
		
		while (i <= count) {
			actualTimeInMillies -= viertelStundeInMillis;
			RealisticValueGenerator realisticValues = new RealisticValueGenerator();
			FlexibilityPowerValues flexibilityPowerValues = new FlexibilityPowerValues();
			flexibilityPowerValues.setpPlus_1(realisticValues.generateValue(
					Double.parseDouble(config.getMinpPlus1()),
					Double.parseDouble(config.getMaxpPlus1()), newTime));
			flexibilityPowerValues.setpPlus_4(realisticValues.generateValue(
					Double.parseDouble(config.getMinpPlus4()),
					Double.parseDouble(config.getMaxpPlus4()), newTime));
			flexibilityPowerValues.setpPlus_1_4(realisticValues.generateValue(
					Double.parseDouble(config.getMinpPlus1_4()),
					Double.parseDouble(config.getMaxpPlus1_4()), newTime));
			flexibilityPowerValues.setpMinus_1(realisticValues.generateValue(
					Double.parseDouble(config.getMinpMinus1()),
					Double.parseDouble(config.getMaxpMinus1()), newTime));
			flexibilityPowerValues.setpMinus_4(realisticValues.generateValue(
					Double.parseDouble(config.getMinpMinus4()),
					Double.parseDouble(config.getMaxpMinus4()), newTime));
			flexibilityPowerValues.setpMinus_1_4(realisticValues.generateValue(
					Double.parseDouble(config.getMinpMinus1_4()),
					Double.parseDouble(config.getMaxpMinus1_4()), newTime));
			flexibilityPowerValues.setTS1(newTime.toString());
			flexibilityPowerValues.setTS2(newTime.plusSeconds(1800).toString());
			newTime = Instant.ofEpochMilli(actualTimeInMillies);
			flexibilityImportObject.setUid(comp.getuId());
			flexibilityImportObject.setFid(iaoFlexAnlage.getfId());
			flexibilityImportObject.addFlexibilityPowerValues(flexibilityPowerValues);
			i++;
		}

		ProxyServer proxy = new ProxyServer();
		proxy.pushData(flexibilityImportObject);
		config.setIfText("Not null anymore");
		RestStageTwoTimerTask stageTwo = new RestStageTwoTimerTask(config, comp,
				iaoFlexAnlage);
		stageTwo.run();
	}
}
