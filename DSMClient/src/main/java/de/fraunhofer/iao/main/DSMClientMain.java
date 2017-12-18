package de.fraunhofer.iao.main;

import java.io.FileInputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Timer;

import org.apache.log4j.Logger;

import de.fraunhofer.iao.domain.Company;
import de.fraunhofer.iao.domain.IAOFlexAnlage;
import de.fraunhofer.iao.domain.IAOGeneratorConfiguration;
import de.fraunhofer.iao.service.JsonConverter;
import de.fraunhofer.iao.service.RestStageOneTask;
import de.fraunhofer.iao.service.RestStageTwoTimerTask;
import de.fzi.domain.FlexibilityImportObject;
import de.fzi.domain.FlexibilityPowerValues;

public class DSMClientMain {

	private static Logger log = Logger.getLogger(DSMClientMain.class);
	/**
	 * * Starts the external Jar and coordinates the Procedur. This is the *
	 * starting class/Methode for the whole Programm. *
	 */

	public static void main(String[] args) throws Exception {
		log.info("main is running...");
		new JsonConverter().setMapper();
		// 2,3 for testing Stage One a
		// 5, for testing Stage One b
		// 4, for testing Stage 2
		// 1,6 for testing of the System exit

		String link;
		try {
			link = args[0];
		} catch (IndexOutOfBoundsException e) {
			log.info("No path found. Using default path.");
			link = "/home/dsm/Development/workspace/DSMClient/src/test/resources/LOCAL_CONFIG_DSM4.properties";
		}
		getCompaniesProperties(link);
	}
	/**
	 * * Get the properties from the properties file for one Flexibilityplant. *
	 * Every new Flexibilityplant needs an new PropertiesFile.
	 */
	public static void getCompaniesProperties(String args) throws Exception {
		log.info(
				"Get Properties of the Company and the Flexibilitdata from the Config File.");
		FileInputStream input = new FileInputStream(args);
		Properties prop = new Properties();
		prop.load(input);
		/**
		 * * Values for Stage One A at least this Values have to be transfered
		 * by * the Company pro unternhemn und flex anlage eine config ansonsten
		 * * format ändern
		 */
		String uid = prop.getProperty("UnternehmensId");
		String fid = prop.getProperty("flexibilitätenId");
		String pMinus1 = prop.getProperty("pMinus1");
		String pMinus1_4 = prop.getProperty("pMinus1_4");
		String pPlus1 = prop.getProperty("pPlus1");
		String pPlus4 = prop.getProperty("pPlus4");
		String pPlus1_4 = prop.getProperty("pPlus1_4");
		String pMinus4 = prop.getProperty("pMinus4");
		/**
		 * * Values for Stage One B They are optional they could be send with
		 * but * don't have to.
		 */
		String tShiftMax = prop.getProperty("stage1b_tShiftMax");
		String eShift = prop.getProperty("stage1b_eShift");
		String dS = prop.getProperty("stage1b_dS");
		String dV = prop.getProperty("stage1b_dV");
		String deltaRampe = prop.getProperty("stage1b_deltaRampe");
		String dA = prop.getProperty("stage1b_dA");
		String pTeilbar = prop.getProperty("stage1b_pTeilbar");
		String verfügbarkeit = prop.getProperty("stage1b_verfügbarkeit");
		String maxAktivierungen = prop.getProperty("stage1b_maxAktivierungen");
		/** * Stage 2 */
		String maxpMinus1 = prop.getProperty("stage2_max_pMinus1");
		String minpMinus1 = prop.getProperty("stage2_min_pMinus1");
		String maxpPlus1 = prop.getProperty("stage2_max_pPlus1");
		String minpPlus1 = prop.getProperty("stage2_min_pPlus1");
		String maxpMinus1_4 = prop.getProperty("stage2_max_pMinus1_4");
		String minpMinus1_4 = prop.getProperty("stage2_min_pMinus1_4");
		String maxpPlus1_4 = prop.getProperty("stage2_max_pPlus1_4");
		String minpPlus1_4 = prop.getProperty("stage2_min_pPlus1_4");
		String maxpPlus4 = prop.getProperty("stage2_max_pPlus4");
		String minpPlus4 = prop.getProperty("stage2_min_pPlus4");
		String maxpMinus4 = prop.getProperty("stage2_max_pMinus4");
		String minpMinus4 = prop.getProperty("stage2_min_pMinus4");
		String generateTimeInPast = prop.getProperty("resolutionInDays");
		String ifFileexists = prop.getProperty("text");
		String stageTwo = prop.getProperty("stage");
		int isStageTwo = 0;
		String generateFutureValues = prop.getProperty("generateFutureValues");
		String generateFutureValuesLength = prop
				.getProperty("generateFutureValuesLength");
		if (stageTwo != null) {
			isStageTwo = Integer.parseInt(stageTwo);
		}
		/**
		 * * The Values who are given decide in which Constructor we have to go
		 * * and which type of Company we have to create.
		 */
		if (uid == null || fid == null || pMinus1 == null || pMinus1_4 == null
				|| pPlus1 == null || pPlus4 == null || pPlus1_4 == null
				|| pMinus4 == null) {
			System.out.println("Zu wenige Informationen wurden eingegeben."
					+ " Geben sie mindestens die 8 Pflichtwerte ein!");
		} else if (maxpMinus1 == null || maxpMinus1_4 == null
				|| maxpPlus1 == null || maxpPlus4 == null || maxpPlus1_4 == null
				|| minpMinus1_4 == null || minpMinus1 == null
				|| maxpMinus4 == null || minpPlus4 == null || minpPlus1 == null
				|| minpPlus1_4 == null || minpMinus4 == null) {
			log.info("Stufe 1 a / b wird erstellt und gepusht.");
			createCompanyStageAOrBAndPushOnceToServer(uid, fid, pMinus1,
					pMinus1_4, pPlus1, pPlus4, pPlus1_4, pMinus4, tShiftMax,
					eShift, dS, dV, deltaRampe, dA, pTeilbar, verfügbarkeit,
					maxAktivierungen);
			/**
			 * * Verfügbarkeit entfällt bei Stufe 2 deshalb ist die Abfrage nach
			 * * verfügbarkeit == null mit innbegriffen. Und alle Grenzen die
			 * zum * Generieren neuer Werte benötigt werden müssen un gleich
			 * null * sein. Da ansonsten keine Werte erzeugt werden können.
			 */
		} else if (isStageTwo == 2 && verfügbarkeit == null
				&& maxpMinus1 != null && maxpMinus1_4 != null
				&& maxpPlus1 != null && maxpPlus4 != null && maxpPlus1_4 != null
				&& minpMinus1_4 != null && minpMinus1 != null
				&& maxpMinus4 != null && minpPlus4 != null && minpPlus1 != null
				&& minpPlus1_4 != null && minpMinus4 != null) {
			log.info(
					"Stufe 2 wird erstellt und mit Timerereignis an die Rest-Schnittstelle gepusht.");
			createCompanyStageTwoAndPushWithTimerToServer(uid, fid, maxpMinus1,
					minpMinus1, maxpPlus1, minpPlus1, maxpMinus1_4,
					minpMinus1_4, maxpPlus1_4, minpPlus1_4, maxpPlus4,
					minpPlus4, maxpMinus4, minpMinus4, generateTimeInPast,
					ifFileexists, generateFutureValues,
					generateFutureValuesLength);
		} else {
			System.out.println("No suitable Variables.");
		}
	}
	/**
	 * * Creates an Object for Stage Two and also pushed this Object to the
	 * Server. * @param uid * @param fid * @param maxpMinus1 * @param minpMinus1
	 * * @param maxpPlus1 * @param minpPlus1 * @param maxpMinus1_4 * @param
	 * minpMinus1_4 * @param maxpPlus1_4 * @param minpPlus1_4 * @param maxpPlus4
	 * * @param minpPlus4 * @param maxpMinus4 * @param minpMinus4 * @param
	 * generateTimeInPast * @param ifFileexists * @param generateFutureValues
	 * * @param generateFutureValuesLength
	 */
	private static void createCompanyStageTwoAndPushWithTimerToServer(
			String uid, String fid, String maxpMinus1, String minpMinus1,
			String maxpPlus1, String minpPlus1, String maxpMinus1_4,
			String minpMinus1_4, String maxpPlus1_4, String minpPlus1_4,
			String maxpPlus4, String minpPlus4, String maxpMinus4,
			String minpMinus4, String generateTimeInPast, String ifFileexists,
			String generateFutureValues, String generateFutureValuesLength) {
		List<IAOFlexAnlage> anlagen = new ArrayList<>();
		IAOFlexAnlage anlage = new IAOFlexAnlage();
		anlage.setfId(fid);
		anlagen.add(anlage);
		Company comp = new Company(uid, maxpPlus1, minpPlus1, maxpMinus1,
				minpMinus1, maxpPlus1_4, minpPlus1_4, maxpMinus1_4,
				minpMinus1_4, maxpPlus4, minpPlus4, maxpMinus4, minpMinus4,
				generateTimeInPast, ifFileexists, anlagen);
		IAOGeneratorConfiguration generateConfig = comp.getIaoGeneratorConfig();
		IAOFlexAnlage iaoFlexAnlagen = new IAOFlexAnlage();
		iaoFlexAnlagen.setfId(fid);
		new Timer().schedule(
				new RestStageTwoTimerTask(generateConfig, comp, iaoFlexAnlagen),
				0, 9000);
	}
	/**
	 * * Creates an Object for Stage One A and B and also pushed this Object to
	 * the Server. * @param uid * @param fid * @param pMinus1 * @param pMinus1_4
	 * * @param pPlus1 * @param pPlus4 * @param pPlus1_4 * @param pMinus4
	 * * @param tShiftMax * @param eShift * @param dS * @param dV * @param
	 * deltaRampe * @param dA * @param pTeilbar * @param verfügbarkeit * @param
	 * maxAktivierungen
	 */
	private static void createCompanyStageAOrBAndPushOnceToServer(String uid,
			String fid, String pMinus1, String pMinus1_4, String pPlus1,
			String pPlus4, String pPlus1_4, String pMinus4, String tShiftMax,
			String eShift, String dS, String dV, String deltaRampe, String dA,
			String pTeilbar, String verfügbarkeit, String maxAktivierungen) {
		String ts1 = Instant.now().toString();
		String ts2 = ts1;
		IAOFlexAnlage iaoFlexAnlage = new IAOFlexAnlage();
		iaoFlexAnlage.setfId(fid);
		FlexibilityImportObject updateDTO = new FlexibilityImportObject();
		FlexibilityPowerValues powerValues = ConvertValues(pPlus1_4, pMinus1_4,
				pPlus1, pMinus1, pPlus4, pMinus4, ts1, ts2);
		updateDTO.setUid(uid);
		updateDTO.setFid(fid);
		updateDTO.addFlexibilityPowerValues(powerValues);
		new RestStageOneTask(updateDTO).run();
	}
	/**
	 * * Is jused used for Converting the Values from String to Double. * @param
	 * pPlus1_4 * @param pMinus1_4 * @param pPlus1 * @param pMinus1 * @param
	 * pPlus4 * @param pMinus4 * @param ts1 * @param ts2 * @return
	 */
	private static FlexibilityPowerValues ConvertValues(String pPlus1_4,
			String pMinus1_4, String pPlus1, String pMinus1, String pPlus4,
			String pMinus4, String ts1, String ts2) {
		double pPlus_1_4 = Double.parseDouble(pPlus1_4);
		double pMinus_1_4 = Double.parseDouble(pMinus1_4);
		double pPlus_1 = Double.parseDouble(pPlus1);
		double pMinus_1 = Double.parseDouble(pMinus1);
		double pPlus_4 = Double.parseDouble(pPlus4);
		double pMinus_4 = Double.parseDouble(pMinus4);
		String tS1 = ts1;
		String tS2 = ts2;
		return new FlexibilityPowerValues(pMinus_4, pMinus_1, pMinus_1_4,
				pPlus_4, pPlus_1, pPlus_1_4, tS1, tS2);
	}
}