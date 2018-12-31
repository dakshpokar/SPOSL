package com.assembler.p1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class PassOne {
	public static Scanner scan;
	private Integer LC;
	public String fileName;
	private Integer startAddress;
	private ArrayList<SymbolLine> symbols = new ArrayList<SymbolLine>();
	private ArrayList<String> instructions = new ArrayList<String>();
	private ArrayList<String> operand = new ArrayList<String>();
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		PassOne p1 = new PassOne();
		System.out.println("Hello!");
		//String fileName = scan.next();
		String fileName = "program.asm";
		p1.getFileName(fileName);
		p1.readFile();
	}
	public void getFileName(String fileName){
		this.fileName = fileName;
	}
	public void readFile(){
		BufferedReader br = null;
		String tempS = "";
		try {
		    String s;
		    br = new BufferedReader(new FileReader(this.fileName));
		    while ((s = br.readLine()) != null) {
		    	if(!s.isEmpty()){
		        	if(s.contains("START")){
		        		String[] str = s.split("\t", -1);
		        		this.startAddress = Integer.valueOf(str[2]);
		        		continue;
		        	}
		        	System.out.println(s);
		        	String[] str = s.split("\t", -1);
		        	
		        	System.out.println(str[0]);
		        	if(!str[0].equals("\t")){
			        	SymbolLine sl = new SymbolLine();
		        		sl.setSymbol(str[0]);
		        		symbols.add(sl);
		        	}
		        	if(str[1] != null && str[2] !=null){
		        		instructions.add(str[1]);
		        		if(str[2] != null && !str[2].equals("\n")){
		        			operand.add(str[2]);
		        		}
		        	}
			      
		    	}
		    }    
		
		} catch (IOException e) {
		    e.printStackTrace();
		}
		for(SymbolLine x:symbols){
			System.out.println(x.getSymbol());
		}
		System.out.println(instructions.size());
		System.out.println(operand.size());
		for(int i = 0; i<instructions.size(); i++){
			System.out.println(instructions.get(i));
			System.out.println("*************************************");
		}
		for(int i = 0; i<operand.size();i++){
			System.out.println(operand.get(i));
			System.out.println("*************************************");
		}
	}	
	
}
