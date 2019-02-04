package com.macro.p1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


public class PassOne {
	private Map<String, String> fvp;
	private Map<String, Map<String, String>> fvp_table = new HashMap<String, Map<String, String>>();

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		PassOne p1 = new PassOne();
		System.out.println("Enter the name of file with extension: ");
		//String fileName = scan.next();
		p1.getFileName("program.asm");
		p1.readFile();
	}
	private void readFile() throws IOException {
	    BufferedWriter mdt = new BufferedWriter(new FileWriter("mdt"));
	    BufferedWriter mnt = new BufferedWriter(new FileWriter("mnt"));
	    BufferedWriter inter = new BufferedWriter(new FileWriter("intercode"));
	    String s;
		BufferedReader br = new BufferedReader(new FileReader(this.fileName));
		boolean macro_line = false; 
		boolean macro_started = false;
		int pointer = 0;
		int macro_num = 0;
		int line = 1;
	    while ((s = br.readLine()) != null) {
	    	System.out.println(s);
	    	if(s.contains("MACRO") || macro_line == true){
    			macro_started = true;
	    		String[] sp = s.split("\t");
	    		if(sp.length > 1){
	    			if(macro_line == true){
	    				pointer = 0;
	    				macro_line = false;
	    			}
	    			else{
	    				pointer = 1;
	    			}
	    			mnt.write(sp[pointer] + "\t");
	    			String[] param = null;
	    			if(sp.length <= 2){
	    				mnt.write("0\t" + String.valueOf(line) + "\n");
	    				continue;
	    			}else{
		    			if(sp[pointer+1].contains(", ")){
		    				param = sp[pointer+1].split(", ");
		    			}
		    			else{
		    				param = sp[pointer+1].split(",");
		    			}
	    			}
	    			macro_num += 1;
	    			int pos = 1;
	    			fvp = new HashMap<String, String>();
	    			for(String x : param){
	    				fvp.put(x, "#" + String.valueOf(pos));
	    				pos++;
	    			}
	    			for(String x : param){
	    				System.out.println(x + fvp.get(x));
	    			}
	    			mnt.write(pos-1+"\t"+String.valueOf(line)+"\n");
	    			fvp_table.put(String.valueOf(macro_num), fvp);
	    		}
	    		else{
	    			macro_line = true;
	    		}
	    		continue;
	    	}
	    	if(s.contains("MEND")){
	    		mdt.write(String.valueOf(line) + ":" + "MEND" + "\n");
	    		line++;
	    		macro_started = false;
	    		continue;
	    	}
	    	if(macro_started == true){
	    		String[] sp = null;
	    		boolean tabs = true;
	    		if(s.contains("\t")){
	    			 sp = s.split("\t", -2);
	    		}
	    		else if(s.contains(" ")){
	    			sp = s.split(" ", -2);
	    			tabs = false;
	    		}
	    		if(sp[1].contains("&")){
		    		if(tabs == false){
			    		mdt.write(String.valueOf(line) + ":" + sp[0] + " " + fvp.get(sp[1]) + "\n");
			    		line++;
		    		}else{
		    			mdt.write(String.valueOf(line) + ":" + sp[0] + "\t" + fvp.get(sp[1]) + "\n");
			    		line++;
		    		}
	    		}
	    		else{
	    			mdt.write(String.valueOf(line) + ":" + s + "\n");
	    			line++;
	    		}
	    	}
	    	if(macro_started == false){
	    		inter.write(s + "\n");
	    	}
	    }
	    mdt.close();
	    mnt.close();
	    inter.close();
	}
	private String fileName;
	public void getFileName(String fileName) throws IOException{
		this.fileName = fileName;
				
	}
}
