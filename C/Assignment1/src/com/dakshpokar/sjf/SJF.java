package com.dakshpokar.sjf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class SJF {
	public Scanner scan = new Scanner(System.in);
	public ArrayList<Process> p_list = new ArrayList<Process>();
	public static void main(String[] args) {
		
		SJF sjf = new SJF();
		Scanner scan = new Scanner(System.in);
		String cho = "";
		while(!cho.equals("n")){
			sjf.menu();
			System.out.println("Do you want to continue[y/n]: ");
			cho = scan.next();
		}
	}
	public void menu(){
		System.out.println("\nWelcome to SJF Program");
		System.out.println("1. Add a new process");
		System.out.println("2. Delete a process");
		System.out.println("3. Show SJF output");
		System.out.println("Enter your choice: ");
		int choice = scan.nextInt();
		switch(choice){
		case 1:
			newProcess();
			break;
		case 2:
			deleteProcess();
			break;
		case 3:
			showAllProcesses();
			break;
		}
	}
	private void deleteProcess() {
		
	}
	private void newProcess() {
		Process p = new Process();
		System.out.println("Enter the name of Process: ");
		String name = scan.next();
		System.out.println("Enter the arrival time of Process: ");
		Double arrTime = scan.nextDouble();
		System.out.println("Enter the execution time of Process: ");
		Double execTime = scan.nextDouble();
		
		p.setName(name);
		p.setArrTime(arrTime);
		p.setExecTime(execTime);
		
		p_list.add(p);
	}
	private void showAllProcesses(){
		Iterator iterator = p_list.iterator();
		System.out.println("\nBefore sorting");
		System.out.println("\nP.N." + "\t" + "A.T." +"\t"+"E.T.");
		while(iterator.hasNext()){
			Process p = (Process) iterator.next();
			System.out.println(p.getName() + "\t" + p.getArrTime() + "\t" + p.getExecTime());
		}
		System.out.println("\nAfter sorting using FCFS");
		System.out.println("\nSorting according to Execution Time");
		Collections.sort(p_list);
		iterator = p_list.iterator();
		System.out.println("\nP.N." + "\t" + "A.T." +"\t"+"E.T."+"\t"+"S.T.");
		Double servTime = 0.0;
		while(iterator.hasNext()){
			Process p = (Process) iterator.next();
			System.out.println(p.getName() + "\t" + p.getArrTime() + "\t" + p.getExecTime() + "\t" + servTime);
			servTime = servTime + p.getExecTime();
		}
		System.exit(0);
	}
}
