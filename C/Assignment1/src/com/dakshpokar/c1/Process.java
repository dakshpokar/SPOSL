package com.dakshpokar.c1;

import java.util.Comparator;

public class Process{
	String name;
	Double arrTime;
	Double execTime;
	Integer priority;
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
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}
