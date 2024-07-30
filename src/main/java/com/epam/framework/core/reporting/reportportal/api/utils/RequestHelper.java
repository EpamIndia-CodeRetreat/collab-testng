package com.epam.framework.core.reporting.reportportal.api.utils;


import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class RequestHelper {
	
	private RequestHelper(){}

	@SuppressWarnings("deprecation")
	public static String getJsonString(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.getSerializationConfig().without(SerializationFeature.WRITE_NULL_MAP_VALUES);

        try {
            return objectMapper.writeValueAsString(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
