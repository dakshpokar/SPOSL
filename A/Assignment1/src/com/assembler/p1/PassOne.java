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
	private ArrayList<IOLine> io = new ArrayList<IOLine>();
	private ArrayList<ForIC> iCode = new ArrayList<ForIC>();
	private ArrayList<ForIC> iSLC = new ArrayList<ForIC>();
	private ArrayList<String> littab = new ArrayList<String>();
	private ArrayList<String> pooltab = new ArrayList<String>();

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
		MnemonicTable m = new MnemonicTable();
		BufferedReader br = null;
		String tempS = "";
		Integer pool_cntr = 1;
		Integer lit_cntr = 1;
		try {
		    String s;
		    br = new BufferedReader(new FileReader(this.fileName));
		    while ((s = br.readLine()) != null) {
        		if(!s.isEmpty()){
	        		String[] str = s.split("\t", -1);
        			if(s.contains("START")){
		        		this.startAddress = Integer.valueOf(str[2]);
		        		LC = this.startAddress;
		        		iCode.add(m.getMnemonic("START"));
		        		iSLC.add(new ForIC("C", String.valueOf(this.startAddress)));
		        		continue;
		        	}
        			if(s.contains("LTORG")){
        				
        			}
        			if(s.contains("ORIGIN")){
        				String label = null;
        				Integer number = 0;
        				if(str[2].contains("+") || str[2].contains("-"))
        				{
        					String[] x = null;
        					x = str[2].split("\\+");
        					label = x[0];
        					number = Integer.parseInt(x[1]);
        					
        				}
        				System.out.println();
        				for(SymbolLine x: symbols){
        					System.out.println(x.getSymbol() + "\n");
        				}
		        		LC = symbols.get(symbols.indexOf(label)).getAddress() + number;
		        		System.out.println(LC);
		        		iCode.add(m.getMnemonic(str[1]));
		        		iSLC.add(new ForIC("C", String.valueOf(this.startAddress)));
		        		continue;
        			}
        			IOLine io_line = new IOLine();
		          	SymbolLine sl = new SymbolLine();

        			if(!str[0].equals("")){
		        		System.out.print(str[0] + " - ");
		        		sl.setSymbol(str[0]);
		        		sl.setAddress(LC);
		        		symbols.add(sl);
			        }
		        	if(!str[1].equals("")){
		        		System.out.print(str[1] + " - ");
		        		iCode.add(m.getMnemonic(str[1]));
		        		io_line.setInstruction(str[1]);		        		
		        	}
		        	if(str.length>2){
		        		System.out.print(str[2] + "\n");
			        	if(!str[2].equals("")){
			        		io_line.setOperand(str[2]);
			        	}
		        	}
		        	
		        	io.add(io_line);
        			
        			
        		}
		    }    
		
		} catch (IOException e) {
		    e.printStackTrace();
		}
		System.out.println("##########INC############");	
		for(int i = 0;i<iCode.size();i++){
			ForIC x = iCode.get(i);
			System.out.print("(" + x.getStatementType() + ", " + x.getCode() + ")  (");
			x = iSLC.get(i);
			System.out.print(x.getCode() + ", " + x.getStatementType() + ")\n");
		}
	}	
	
}

/*
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
	private ArrayList<IOLine> io = new ArrayList<IOLine>();
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
		Integer LC = 0;
		Integer lineCount = 0;
		try {
		    String s;
		    br = new BufferedReader(new FileReader(this.fileName));
		    while ((s = br.readLine()) != null) {
        		lineCount++;
        		System.out.print(LC + " - ");
		    	if(!s.isEmpty()){
		    		IOLine io_line = new IOLine();
		          	SymbolLine sl = new SymbolLine();

		        	if(s.contains("START")){
		        		String[] str = s.split("\t", -1);
		        		this.startAddress = Integer.valueOf(str[2]);
		        		LC = this.startAddress;
		        		continue;
		        	}
		        	
		        	String[] str = s.split("\t", -1);
		        	if(!str[0].equals("")){
		        		System.out.print(str[0] + " - ");
		        		sl.setSymbol(str[0]);
		        		sl.setLineNumber(lineCount);
		        		sl.setAddress(LC);
		        		symbols.add(sl);
			        }
		        	if(!str[1].equals("")){
		        		System.out.print(str[1] + " - ");
		        		io_line.setInstruction(str[1]);		        		
		        	}
		        	if(str.length>2){
		        		System.out.print(str[2] + "\n");
			        	if(!str[2].equals("")){
			        		io_line.setOperand(str[2]);
			        	}
		        	}
		        	
		        	io_line.setLineNumber(lineCount);
		        	io.add(io_line);		        	
		    	}
		    }    
		
		} catch (IOException e) {
		    e.printStackTrace();
		}
		System.out.println("\n########Instructions########");
		for(SymbolLine x : symbols){
			System.out.print(x.getLineNumber() + " - ");
			System.out.print(x.getSymbol() + " - ");
			System.out.println("****************************");
		}	
	}	
	
}
*/