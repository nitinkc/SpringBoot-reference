package com.spring.reference.exception;

public enum ErrorCodes {
	ERR_122("122", "ERROR: Entity is not present in the DB"),
	ERR_123("123", "ERROR: DB is down"),
	ERR_124("124", "Error: :: Some Business Exception"),
	ERR_140("140", "Error: :: Validation Error"),
	;

	//TODO: Error codes which are dependant on other micro services like Orders service, etc,.
	private final String errorCode;
	private final String errorMessage;

	public String getCode() {
		return this.getErrorCode() + " :: " + this.getErrorMessage();
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	ErrorCodes(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}