/**
 * @Description:
 * @Author: lvieir9
 * @Since: Jun 23, 2016
 * @Version: 
 */
package com.github.xild.verbose.pancake.model.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Luis Vieira
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorOutput implements TransferObject {

	private static final long serialVersionUID = 8870027544757295266L;

	@JsonProperty
	private String error;

	@JsonProperty
	private String message;

	@JsonProperty
	private int httpStatus;

	public String getErrors() {
		return error;
	}

	public String getMessage() {
		return message;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public static class Builder {
		private String errors;
		private String message;
		private int httpStatus;

		public Builder errors(String errors) {
			this.errors = errors;
			return this;
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public Builder httpStatus(int httpStatus) {
			this.httpStatus = httpStatus;
			return this;
		}

		public ErrorOutput build() {
			return new ErrorOutput(this);
		}
	}

	private ErrorOutput(Builder builder) {
		this.error = builder.errors;
		this.message = builder.message;
		this.httpStatus = builder.httpStatus;
	}
}
