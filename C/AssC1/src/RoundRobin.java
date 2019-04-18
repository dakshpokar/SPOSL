import java.util.ArrayList;
import java.util.Scanner;

public class RoundRobin {
	private ArrayList<Process> processes;
	private ArrayList<Process> rQ;
	Process pro;
	Integer numProcess = 0, quantum = 0;
	@SuppressWarnings("unchecked")
	public void getInput() {
		processes = new ArrayList<>();
		rQ = new ArrayList<>();
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of processes: ");
		numProcess = scan.nextInt();
		for(int i = 0;i<numProcess;i++) {
			pro = new Process();
			pro.get(i);
			processes.add(pro);
		}
		//Collections.sort(processes, new ArrivalComparator());
		for(int i = 0; i<processes.size();i++) {
			rQ.add(processes.get(i));
		}
		
		System.out.println("Enter the quantum time: ");
		quantum = scan.nextInt();
		schedule();
	}
	public void schedule() {
		Double time = 0.0;
		Process temp;
		while(!rQ.isEmpty()) {
			temp = rQ.get(0);
			System.out.println(time);
			System.out.println(temp.getPid());
			if(temp.getRemainingTime() > 0) {
				if(temp.getRemainingTime() - quantum > 0) {
					temp.setRemainingTime(temp.getRemainingTime() - quantum);
					rQ.remove(temp);
					rQ.add(temp);
					time += quantum;
				}
				else {
					time += temp.getRemainingTime();
					temp.setCompletionTime(time);
					temp.setTatTime(temp.getCompletionTime() - temp.getArrivalTime());
					temp.setWaitingTime(temp.getTatTime() - temp.getBurstTime());
					temp.setRemainingTime(0);
					rQ.remove(temp);
				}
			}
		}
		display();
	}
	public void display() {
		System.out.println("PID\tA.T.\tB.T.\tC.T\tT.A.T\tW.T.");
		Process temp;
		for(int i = 0; i<processes.size();i++) {
			temp = processes.get(i);
			System.out.println(temp.getPid() + "\t" + temp.getArrivalTime() + "\t" + temp.getBurstTime() + "\t" + temp.getCompletionTime() + "\t" + temp.getTatTime() + "\t" + temp.getWaitingTime() + "\n");
		}
	}
	public static void main(String[] args) {
		RoundRobin rr = new RoundRobin();
		rr.getInput();
	}
}
