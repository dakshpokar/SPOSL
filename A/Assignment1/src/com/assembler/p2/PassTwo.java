package com.assembler.p2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.assembler.p1.MnemonicTable;
import com.assembler.p1.SymbolLine;
import com.assembler.p1.LitLine;

public class PassTwo {
	private static Scanner scan;
	private Map<String, SymbolLine> symtab = new HashMap<String, SymbolLine>();
	private Map<Integer, LitLine> littab = new HashMap<Integer, LitLine>();

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		System.out.print("Enter the name of intermediate code file: ");
		String icode_file = "intercode";
		PassTwo pass2 = new PassTwo();
		try {
			pass2.readFile(icode_file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void readFile(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		BufferedWriter wr = new BufferedWriter(new FileWriter("target"));
		readSymbolTable();
		readLiteralTable();
		MnemonicTable mt = new MnemonicTable();
		OperandTable ot = new OperandTable();
		String line;
		line = br.readLine();
		Integer DEBUG = 0;
		Integer lit_ptr = 1;
		while((line=br.readLine()) != null){
			if(DEBUG !=null)
			{
				System.out.println(line);
				String[] x = line.split("\t", -2);
				if(x.length > 2 && !line.contains("AD, 03") && !line.contains("AD, 04")){
					String wc = x[1].substring(1, x[1].length() - 1);
					wr.write(x[0] + ")\t");
					String[] comma_split1 = wc.split(", ");
					System.out.println(x.length);
					String[] wk = null; 
					if(!x[2].isEmpty()){
						wk = x[2].substring(1, x[2].length() - 1).split(", ");
						
						if(!wk[0].equals("L")){
							wr.write("+" + comma_split1[1] + "\t");
						}
						else{
							wr.write("+00\t");
							wr.write(String.valueOf(0) + "\t");
							wr.write("00" + wk[1].substring(1, wk[1].length() - 1));
							wr.write("\n");
							continue;
						}
						if(x[2].contains(", ")){
							String[] comma_split = x[2].split(", ");
							String v = ot.getOperand(comma_split[0]);
							if(v != null){
								wr.write(v + "\t");
							}
							else{
								wr.write("1" + "\t");
							}
							if(comma_split[1].contains("=")){
								wr.write(String.valueOf(littab.get(lit_ptr).getAddress()));
								lit_ptr++;
							}
							else{
								wr.write(String.valueOf(symtab.get(comma_split[1]).getAddress()));
							}
						}
					}
					
					wr.write("\n");
				}
				else{
					System.out.println("LESS");
				}
			}
			DEBUG++;
		}
		wr.close();
	}
	public void readSymbolTable() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("symtab"));
		String line;
		while((line = br.readLine())!=null){
			String[] x = line.split("\t");
			if(x.length > 2){
				SymbolLine s = new SymbolLine();
				s.setAddress(Integer.parseInt(x[1]));
				s.setLength(Integer.parseInt(x[2]));
				symtab.put(x[0], s);
			}
		}
	}
	public void readLiteralTable() throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new FileReader("littab"));
		String line;
		while((line = br.readLine())!=null){
			String[] x = line.split("\t");
			if(x.length > 2){
				LitLine e = new LitLine();
				e.setLiteral(x[1]);
				e.setAddress(Integer.parseInt(x[2]));
				littab.put(Integer.parseInt(x[0]), e);
			}
		}
	}
}
