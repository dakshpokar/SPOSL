package com.assembler.p1;

public class SymbolLine{
	private String Symbol;
	private Integer Address;
	private Integer Length;
	public Integer getAddress() {
		return Address;
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