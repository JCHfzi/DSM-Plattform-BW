package de.fraunhofer.iao.domain;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * * Simulates different Companys from Stage One A/B or Stage Two The Different
 * * Values are crucial for the choice of the Constructor. * * Created by
 * Lausterer on 03.04.2017.
 */
public class Company {
	private static Logger log = Logger.getLogger(Company.class);
	@JsonIgnore
	/** * eindeutige UnternehmensId */
	private String uId;
	private IAOGeneratorConfiguration iaoGeneratorConfig = new IAOGeneratorConfiguration();
	public IAOGeneratorConfiguration getIaoGeneratorConfig() {
		return iaoGeneratorConfig;
	}
	private List<IAOFlexAnlage> flexAnlagen = new ArrayList<IAOFlexAnlage>();
	/**
	 * * Constructor for Stage One A and B In Stage One A are only the six
	 * Values * excluding the uid and fid available. In Stage One B are more
	 * Values * available for example the "verfügbarkeit" but these are optional
	 * Values.
	 */
	public Company(String uId, String fid, String pPlus1_4, String pMinus1_4,
			String pPlus1, String pMinus1, String pPlus4, String pMinus4,
			String tShiftMax, String eShift, String dS, String dV,
			String deltaRampe, String dA, String pTeilbar, String verfügbarkeit,
			String maxAktivierungen) {
		this.uId = uId;
		IAOFlexAnlage iaoFlexAnlage = new IAOFlexAnlage();
		iaoFlexAnlage.setfId(fid);
		iaoFlexAnlage.settShiftMax(tShiftMax);
		iaoFlexAnlage.seteShift(eShift);
		iaoFlexAnlage.setdS(dS);
		iaoFlexAnlage.setdV(dV);
		iaoFlexAnlage.setDeltaRampe(deltaRampe);
		iaoFlexAnlage.setdA(dA);
		iaoFlexAnlage.setpTeilbar(pTeilbar);
		iaoFlexAnlage.setVerfügbarkeit(verfügbarkeit);
		iaoFlexAnlage.setMaxAktivierungen(maxAktivierungen);
		this.flexAnlagen.add(iaoFlexAnlage);
		IAOFlexibility iaoFlexibility = new IAOFlexibility();
		iaoFlexibility.setpPlus1(pPlus1);
		iaoFlexibility.setpPlus1_4(pPlus1_4);
		iaoFlexibility.setpPlus4(pPlus4);
		iaoFlexibility.setpMinus1(pMinus1);
		iaoFlexibility.setpMinus1_4(pMinus1_4);
		iaoFlexibility.setpMinus4(pMinus4);
		iaoFlexAnlage.getFlexibilities().add(iaoFlexibility);
		log.info("Company of Stage One A/B was generated.");
	}
	/**
	 * * Constructor for Stage Two with the Values min and max inbetween the new
	 * * Values are generated. *
	 */
	public Company(String uId, String maxpPlus1, String minpPlus1,
			String maxpMinus1, String minpMinus1, String maxpPlus1_4,
			String minpPlus1_4, String maxpMinus1_4, String minpMinus1_4,
			String maxpPlus4, String minpPlus4, String maxpMinus4,
			String minpMinus4, String timeInPast, String ifFileExists,
			List<IAOFlexAnlage> anlagen) {
		this.uId = uId;
		iaoGeneratorConfig.setMaxpPlus1(maxpPlus1);
		iaoGeneratorConfig.setMinpPlus1(minpPlus1);
		iaoGeneratorConfig.setMaxpMinus1(maxpMinus1);
		iaoGeneratorConfig.setMinpMinus1(minpMinus1);
		iaoGeneratorConfig.setMaxpPlus1_4(maxpPlus1_4);
		iaoGeneratorConfig.setMinpPlus1_4(minpPlus1_4);
		iaoGeneratorConfig.setMaxpMinus1_4(maxpMinus1_4);
		iaoGeneratorConfig.setMinpMinus1_4(minpMinus1_4);
		iaoGeneratorConfig.setMaxpPlus4(maxpPlus4);
		iaoGeneratorConfig.setMinpPlus4(minpPlus4);
		iaoGeneratorConfig.setMaxpMinus4(maxpMinus4);
		iaoGeneratorConfig.setMinpMinus4(minpMinus4);
		iaoGeneratorConfig.setTimeInPast(timeInPast);
		iaoGeneratorConfig.setIfFileExists(ifFileExists);
		this.flexAnlagen = anlagen;
		log.info("Company of Stage Two is generated.");
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public List<IAOFlexAnlage> getAnlagen() {
		return flexAnlagen;
	}
	public void setAnlagen(List<IAOFlexAnlage> anlagen) {
		this.flexAnlagen = anlagen;
	}
	public void setIaoGeneratorConfig(
			IAOGeneratorConfiguration iaoGeneratorConfig) {
		this.iaoGeneratorConfig = iaoGeneratorConfig;
	}
	public List<IAOFlexAnlage> getFlexAnlagen() {
		return flexAnlagen;
	}
	public void setFlexAnlagen(List<IAOFlexAnlage> flexAnlagen) {
		this.flexAnlagen = flexAnlagen;
	}
}