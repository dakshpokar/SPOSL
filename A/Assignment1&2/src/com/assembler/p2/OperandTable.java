package com.assembler.p2;

import java.util.HashMap;
import java.util.Map;

public class OperandTable {
	private Map<String, String> map = new HashMap<String, String>();
	
	OperandTable(){
		map.put("AREG", "1");
		map.put("BREG", "2");
		map.put("CREG", "3");
		map.put("DREG", "4");
		map.put("ANY", "6");
	}
	public String getOperand(String operand){
		return map.get(operand);
	}
}
