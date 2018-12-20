package com.dakshpokar.sjf;

import java.util.Comparator;

public class Process implements Comparable<Process>{
	String name;
	Double arrTime;
	Double execTime;
	public Process() {
	}
	public Double getArrTime() {
		return arrTime;
	}
	public Double getExecTime() {
		return execTime;
	}
	public String getName() {
		return name;
	}
	public void setArrTime(Double arrTime) {
		this.arrTime = arrTime;
	}
	public void setExecTime(Double execTime) {
		this.execTime = execTime;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int compareTo(Process p) {
		if(this.getExecTime() < p.getExecTime()){
			return -1;
		}
		else if(this.getExecTime() > p.getExecTime()){
			return 1;
		}
		else	{
			return 0;
		}
	}
}
