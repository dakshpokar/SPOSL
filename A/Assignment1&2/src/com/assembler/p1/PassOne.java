package com.assembler.p1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


public class PassOne {
	public static Scanner scan;
	private Integer LC;
	public String fileName;
	private Integer startAddress;
	private Map<String, SymbolLine> symbols = new HashMap<String, SymbolLine>();
	private ArrayList<IOLine> io = new ArrayList<IOLine>();
	private ArrayList<LitLine> littab = new ArrayList<LitLine>();
	private ArrayList<String> pooltab = new ArrayList<String>();

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		PassOne p1 = new PassOne();
		/*
		 * Taking File Name
		 */
		System.out.println("Enter the name of file with extension: ");
		String fileName = scan.next();
		p1.getFileName(fileName);		//Setting class object filename with user inputted filename
		try {
			p1.readFile();	//Scanning the file input.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void getFileName(String fileName){
		this.fileName = fileName;
	}
	public void readFile() throws IOException{
		MnemonicTable m = new MnemonicTable();	//MT object
		BufferedReader br = null;	//Reading file
		String tempS = "";
		Integer lit_count = 1;
		Boolean ltorg = false;
	    BufferedWriter inter = new BufferedWriter(new FileWriter("intercode"));
	    ForIC ic = null;
		try {
		    String s;
		    br = new BufferedReader(new FileReader(this.fileName));
		    
		    while ((s = br.readLine()) != null) {
		    	if(!s.isEmpty()){
			    	if(!s.contains("EQU") && !s.contains("ORIGIN") && !s.contains("START") && !s.contains("LTORG") && !s.contains("END")){
			    		inter.write("\n" + String.valueOf(LC));
			    	}
        			String[] str = s.split("\t", -1);
        			if(s.contains("START")){
		        		this.startAddress = Integer.valueOf(str[2]);
		        		LC = this.startAddress;
		        		ic = m.getMnemonic("START");
		        		inter.write("\t(" + ic.getStatementType() + ", " + ic.getCode() + ")\t");
		        		inter.write("(C, " + String.valueOf(this.startAddress) + ")");
		        		continue;
		        	}
        			if(s.contains("LTORG") || s.contains("END")){
    					pooltab.add("#" + lit_count.toString() + "\n");
        				ltorg = true;	//If END of LTORG 
        				continue;
        			}
        			
        			if(s.contains("ORIGIN")){
        				String label = null;
        				Integer number = 0;
        				if(str[2].contains("+"))
        				{
        					String[] x = null;
        					x = str[2].split("\\+");
        					label = x[0];
        					number = Integer.parseInt(x[1]);
        				}
	        			else if(str[2].contains("-"))
        				{
        					String[] x = null;
        					x = str[2].split("\\-");
        					label = x[0];
        					number = -Integer.parseInt(x[1]);
        				}
	        			else {
	        				label = str[2];
	        			}
        				SymbolLine x = null;
        				x = symbols.get(label);
		        		LC = x.getAddress() + number;
		        		ic = m.getMnemonic("ORIGIN");
		        		inter.write("\n\t(" + ic.getStatementType() + ", " + ic.getCode() + ")\t");
		        		inter.write(str[2]);	
		        		continue;
        			}
        			
        			IOLine io_line = new IOLine();
		          	SymbolLine sl = new SymbolLine();

        			if(!str[0].equals("")){
        				if(ltorg == true){
        					ltorg = false;
        				}
	        			Integer oldLC = null;
		        		if(s.contains("EQU") || s.contains("PRINT")){
		        			String label = null;
		        			Integer number = null;
		        			if(str[2].contains("+"))
	        				{
	        					String[] x = null;
	        					x = str[2].split("\\+");
	        					label = x[0];
	        					number = Integer.parseInt(x[1]);
	        				}
		        			else if(str[2].contains("-"))
	        				{
	        					String[] x = null;
	        					x = str[2].split("\\-");
	        					label = x[0];
	        					number = -Integer.parseInt(x[1]);
	        				}
		        			else {
		        				label = str[2];
		        			}
	        				oldLC = LC;
	        				SymbolLine x = null;	        				
	        				x = symbols.get(label);
	        				oldLC = LC ;
	        				if(number == null){
	        					LC = x.getAddress();
	        				}
	        				else{
	        					LC = x.getAddress() + number;
	        				}
	        			}
		        		sl.setAddress(LC);
		        		sl.setLength(1);
		        		symbols.put(str[0], sl);
		        		if(s.contains("EQU") || s.contains("PRINT")){
		        			LC = oldLC;
		        		}

        			}
        			if(ltorg == true){
        				littab.add(new LitLine(str[2], LC));
        				lit_count += 1;
        			}
    				if(!s.contains("LTORG") && !s.contains("EQU") && !s.contains("END")){
    					LC = LC + 1;
    				}

		        	if(!str[1].equals("")){
		        		io_line.setInstruction(str[1]);
			        	ic = m.getMnemonic(str[1]);
			        	if(s.contains("EQU")){
			        		inter.write("\n");
			        	}
			        	inter.write("\t(" + ic.getStatementType() + ", " + ic.getCode() + ")\t");
		        	}
		        	if(str.length>2){
			        	if(!str[2].equals("")){
			        		io_line.setOperand(str[2]);
			        		if(ltorg==true){
			        			inter.write("\t(DL, 05)\t");
		        				inter.write("(L, "+str[2].substring(1, str[2].length()) + ")");
			        		}else{
			        			if(s.contains("DS") || s.contains("DC"))
			        			{
			        				inter.write("\t(C, " + str[2] + ")");
			        			}
			        			else{
			        				inter.write(str[2]);
			        			}
			        		}
			        	}
			        	
		        	}
		        	io.add(io_line);

        		}
		    	else{
		    		LC = LC + 1;
			    	inter.write("\n");
		    	}
		    	
		    }    
		
		} catch (IOException e) {
		    e.printStackTrace();
		}
		System.out.println("##########INC############");	
		inter.close();
        Iterator hmIterator = symbols.entrySet().iterator();
	    BufferedWriter writer = new BufferedWriter(new FileWriter("symtab"));

		while (hmIterator.hasNext()) { 
            Map.Entry mapElement = (Map.Entry)hmIterator.next(); 
            SymbolLine sl = ((SymbolLine)mapElement.getValue()); 
            System.out.println(mapElement.getKey() + " : " + sl.getAddress()); 
            writer.write(mapElement.getKey() + "\t" + sl.getAddress().toString() + "\t" + sl.getLength().toString() + "\n");
        } 
		writer.close();
		
        hmIterator = littab.iterator();
	    writer = new BufferedWriter(new FileWriter("littab"));
	    Integer k = 1;
		while (hmIterator.hasNext()) { 
			LitLine l = (LitLine)hmIterator.next();
            writer.write(k.toString() + "\t" + l.getLiteral() + "\t" + l.getAddress().toString() + "\n");
            k++;
        } 
		writer.close();
	    
	    writer = new BufferedWriter(new FileWriter("pooltab"));
	    hmIterator = pooltab.iterator();
	    while(hmIterator.hasNext()){
	    	writer.write((String)hmIterator.next());
	    }
		writer.close();
	}	
	
}