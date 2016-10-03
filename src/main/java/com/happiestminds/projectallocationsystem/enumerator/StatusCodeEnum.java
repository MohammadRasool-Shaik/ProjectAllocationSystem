package com.happiestminds.projectallocationsystem.enumerator;

public enum StatusCodeEnum {
	FAILURE("Failure"), SUCCESS("Success");

	private String msg;

	StatusCodeEnum(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
