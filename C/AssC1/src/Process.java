import java.util.Collections;
import java.util.Scanner;

public class Process {
	private Integer pid;
	private double arrivalTime;
	private double burstTime;
	private double remainingTime, completionTime, tatTime, waitingTime;
	private Scanner scan;
	public Process() {
		scan = new Scanner(System.in);
	}

	
	public double getCompletionTime() {
		return completionTime;
	}


	public void setCompletionTime(double completionTime) {
		this.completionTime = completionTime;
	}


	public double getTatTime() {
		return tatTime;
	}


	public void setTatTime(double tatTime) {
		this.tatTime = tatTime;
	}


	public double getWaitingTime() {
		return waitingTime;
	}


	public void setWaitingTime(double waitingTime) {
		this.waitingTime = waitingTime;
	}


	public Process(double arrivalTime, double burstTime) {
		super();
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
	}
	
	
	public double getRemainingTime() {
		return remainingTime;
	}
	public void setRemainingTime(double remainingTime) {
		this.remainingTime = remainingTime;
	}
	public void get(int i) {
		System.out.println("P("+i+"): Enter the Arrival Time and Burst Time: ");
		pid = i;
		arrivalTime = scan.nextDouble();
		burstTime = scan.nextDouble();
		remainingTime = burstTime;
		completionTime = -1;
	}
	public Integer getPid() {
		return pid;
	}


	public void setPid(Integer pid) {
		this.pid = pid;
	}


	public double getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(double arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public double getBurstTime() {
		return burstTime;
	}
	public void setBurstTime(double burstTime) {
		this.burstTime = burstTime;
	}
	
}
