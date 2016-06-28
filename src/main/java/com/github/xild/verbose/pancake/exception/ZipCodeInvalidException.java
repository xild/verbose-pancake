/**
 * @Description:
 * @Author: lvieir9
 * @Since: Jun 27, 2016
 * @Version: 
 */
package com.github.xild.verbose.pancake.exception;

import feign.Response;

/**
 * @author Luis Vieira
 *
 */
public class ZipCodeInvalidException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7918027114419061179L;
	private Response response;

	public ZipCodeInvalidException() {
		super();
	}

	public ZipCodeInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public ZipCodeInvalidException(String message) {
		super(message);
	}

	public ZipCodeInvalidException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param methodKey
	 * @param response
	 */
	public ZipCodeInvalidException(String methodKey, Response response) {
		super(methodKey);
		this.setResponse(response);
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

}
