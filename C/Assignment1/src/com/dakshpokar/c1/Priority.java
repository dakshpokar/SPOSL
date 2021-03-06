package com.dakshpokar.c1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class Priority {
	public Scanner scan = new Scanner(System.in);
	public ArrayList<Process> p_list = new ArrayList<Process>();
	public static void main(String[] args) {
		
		Priority priority = new Priority();
		Scanner scan = new Scanner(System.in);
		String cho = "";
		while(!cho.equals("n")){
			priority.menu();
			System.out.println("Do you want to continue[y/n]: ");
			cho = scan.next();
		}
	}
	public void menu(){
		System.out.println("\nWelcome to Priority Program");
		System.out.println("1. Add a new process");
		System.out.println("2. Delete a process");
		System.out.println("3. Show Priority output");
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
		System.out.println("Enter the priority: ");
		Integer priority = scan.nextInt();
		System.out.println("Enter the execution time of Process: ");
		Double execTime = scan.nextDouble();
		
		p.setName(name);
		p.setPriority(priority);
		p.setExecTime(execTime);
	
		p_list.add(p);

	}
	@SuppressWarnings("unchecked")
	private void showAllProcesses(){
		GanttChart gantt = new GanttChart();
		Iterator iterator = p_list.iterator();
		System.out.println("\nBefore sorting");
		System.out.println("\nP.N." + "\t" + "P." +"\t"+"E.T.");
		while(iterator.hasNext()){
			Process p = (Process) iterator.next();
			System.out.println(p.getName() + "\t" + p.getPriority() + "\t" + p.getExecTime());
		}
		System.out.println("\nAfter sorting using FCFS");
		System.out.println("\nSorting according to Execution Time");
		Collections.sort(p_list, new PriorityComparator());
		iterator = p_list.iterator();
		System.out.println("\nP.N." + "\t" + "P" +"\t"+"E.T."+"\t"+"W.T.");
		Double waitTime = 0.0;
		ArrayList<Double> wait_time = new ArrayList<>();
		while(iterator.hasNext()){
			Process p = (Process) iterator.next();
			System.out.println(p.getName() + "\t" + p.getPriority() + "\t" + p.getExecTime() + "\t" + waitTime + "\t");
			wait_time.add(waitTime);
			waitTime = waitTime + p.getExecTime();
		}
		gantt.getFrame(new ProcessList(p_list));
		iterator = wait_time.iterator();
		Double avg_wait_time = 0.0;	
		while(iterator.hasNext()){
			avg_wait_time += (Double)iterator.next();
		}
		avg_wait_time = avg_wait_time / wait_time.size();
		System.out.println("Average Wait Time" + avg_wait_time);
	}
}
