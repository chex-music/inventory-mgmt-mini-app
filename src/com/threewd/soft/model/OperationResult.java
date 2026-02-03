package com.threewd.soft.model;

public class OperationResult {

	private boolean success;
	private String message;
	private int affectedRows;

	public OperationResult() {
		super();
	}

	public OperationResult(boolean success, String message, int affectedRows) {
		super();
		this.success = success;
		this.message = message;
		this.affectedRows = affectedRows;
	}

	public OperationResult(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public int getAffectedRows() {
		return affectedRows;
	}

	public void setAffectedRows(int affectedRows) {
		this.affectedRows = affectedRows;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
