package com.spring.reference.exception.business;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ApiException extends ApiRuntimeException {

	/**
	 * Orders Command domain custom exception for business validations
	 */
	private static final long serialVersionUID = 2652858543957870604L;

	public ApiException(String statusMessage) {
		super(statusMessage);
	}
}
