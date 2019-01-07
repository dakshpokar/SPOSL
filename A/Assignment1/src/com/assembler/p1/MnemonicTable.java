package com.assembler.p1;

import java.util.HashMap;
import java.util.Map;


public class MnemonicTable {
	private Map<String, ForIC> map = new HashMap<String, ForIC>();
	
	MnemonicTable(){
		map.put("STOP", new ForIC("00", "IS"));
		map.put("ADD", new ForIC("01", "IS"));
		map.put("SUB", new ForIC("02", "IS"));
		map.put("MULT", new ForIC("03", "IS"));
		map.put("MOVE R", new ForIC("04", "IS"));
		map.put("MOVE M", new ForIC("05", "IS"));
		map.put("COMP", new ForIC("06", "IS"));
		map.put("BC", new ForIC("07", "IS"));
		map.put("DIV", new ForIC("08", "IS"));
		map.put("READ", new ForIC("09", "IS"));
		map.put("PRINT", new ForIC("10", "IS"));
		
		/*
		map.put("00", "STOP");
		map.put("01", "ADD");
		map.put("02", "SUB");
		map.put("03", "MULT");
		map.put("04", "MOVE R");
		map.put("05", "MOVE M");
		map.put("06", "COMP");
		map.put("07", "BC");
		map.put("08", "DIV");
		map.put("09", "READ");
		map.put("10", "PRINT");
		*/
		
		map.put("START", new ForIC("01", "AD"));
		map.put("END", new ForIC("02", "AD"));
		map.put("ORIGIN", new ForIC("03", "AD"));
		map.put("EQU", new ForIC("04", "AD"));
		map.put("LTORG", new ForIC("05", "AD"));

		map.put("DS", new ForIC("01", "AD"));
		map.put("DC", new ForIC("02", "AD"));
		
	}
	public ForIC getMnemonic(String Instruction){
		return map.get(Instruction);
	}
	
}
