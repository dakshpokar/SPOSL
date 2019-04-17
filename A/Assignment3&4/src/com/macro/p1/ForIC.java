package com.macro.p1;

public class ForIC {
	private String code;
	private String statementType;
	public String getCode() {
		return code;
	}
	public String getStatementType() {
		return statementType;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setStatementType(String statementType) {
		this.statementType = statementType;
	}
	public ForIC(String code, String statementType) {
		this.code = code;
		this.statementType = statementType;
	}
}
