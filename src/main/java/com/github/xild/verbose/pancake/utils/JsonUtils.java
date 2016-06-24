/**
 * @Description:
 * @Author: lvieir9
 * @Since: Jun 7, 2016
 * @Version: 
 */
package com.github.xild.verbose.pancake.utils;


import org.apache.commons.lang3.SerializationException;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Luis Vieira 
 *
 */ 
public class JsonUtils {
	
	private static ObjectMapper mapper = new ObjectMapper();

	public static <T> T toObject(String json, Class<T> classType) {
		if (json == null) {
			return null;
		}
		try {
			return mapper.readValue(json, classType);
		} catch (Exception e) {
			throw new SerializationException(e);
		}
	}

	public static String toJson(Object object) {
		if (object == null) {
			return null;
		}
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new SerializationException(e);
		}
	}
}
