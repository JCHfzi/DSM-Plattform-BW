package de.dsm.services;

import java.util.HashMap;
import java.util.Map;

public enum ProcessorResultCodes {
	OK("0001", "success", "200"),
	ERRORS_OCCURRED("0002", "errors occurred while processing. not all values processed.", "200"),
	
	UNKNOWN_FLEX_PWR("9996", "flexible power not found", "404"),
	UNKNOWN_FLEXIBILITY("9997", "unknown flexibility", "404"),
	INVALID_DATE_FORMAT("9998", "invalid date format", "400"),
	UNKNOWN_ERROR("9999", "unknown_error", "500");
	
	String code;
	String description;
	String httpResponse;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private ProcessorResultCodes(String code, String description, String httpResponse) {
		this.code = code;
		this.description = description;
		this.httpResponse = httpResponse;
	}
	
	public String getCode() {
		return code;
	}

	
	public String getHttpResponse() {
		return httpResponse;
	}

	public void setHttpResponse(String httpResponse) {
		this.httpResponse = httpResponse;
	}


	private static final Map<String, ProcessorResultCodes> map = new HashMap<String, ProcessorResultCodes>();
	static{
	  for(ProcessorResultCodes rCode: values())  map.put(rCode.getCode(), rCode);
	}

	public static ProcessorResultCodes getByCode(String code){
	  return map.get(code);
	}
}
