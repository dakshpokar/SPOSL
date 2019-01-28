package com.assembler.p1;

public class LitLine {
	private String literal;
	private Integer address;
	public Integer getAddress() {
		return address;
	}
	public String getLiteral() {
		return literal;
	}
	public void setAddress(Integer address) {
		this.address = address;
	}
	public void setLiteral(String literal) {
		this.literal = literal;
	}
	public LitLine() {
		// TODO Auto-generated constructor stub
	}
	public LitLine(String literal, Integer address){
		this.literal = literal;
		this.address = address;
		
	}
}
