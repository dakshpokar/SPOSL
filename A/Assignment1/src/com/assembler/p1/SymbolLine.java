package com.assembler.p1;

public class SymbolLine{
	private Integer lineNumber;
	private String Symbol;
	private Integer Address;
	private Integer Length;
	public Integer getAddress() {
		return Address;
	}
	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}
	public Integer getLineNumber() {
		return lineNumber;
	}
	public Integer getLength() {
		return Length;
	}
	public String getSymbol() {
		return Symbol;
	}
	public void setAddress(Integer address) {
		Address = address;
	}
	
	public void setLength(Integer length) {
		Length = length;
	}
	public void setSymbol(String symbol) {
		Symbol = symbol;
	}
}