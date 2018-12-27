package com.dakshpokar.c1;

import java.util.ArrayList;

public class ProcessList {
	ArrayList<Process> processes = new ArrayList<>();
	public ProcessList(ArrayList<Process> processes) {
		this.processes = processes;
	}
	public ProcessList() {
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Process> getProcesses() {
		return processes;
	}
}
