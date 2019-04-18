import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SJF {
	private ArrayList<Process> processes;
	private ArrayList<Process> rQ;
	Process pro, recent_temp;
	Double time = 0.0, recent = 0.0;
	
	Integer numProcess = 0, quantum = 0;
	@SuppressWarnings("unchecked")
	public void getInput() {
		processes = new ArrayList<>();
		rQ = new ArrayList<>();
		recent_temp = new Process();
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of processes: ");
		numProcess = scan.nextInt();
		for(int i = 0;i<numProcess;i++) {
			pro = new Process();
			pro.get(i);
			processes.add(pro);
		}
		System.out.println(time);
		System.out.println(recent_temp.getArrivalTime());
		Collections.sort(processes, new ArrivalComparator());
		
		time = time + processes.get(0).getArrivalTime();
		rQ.add(processes.get(0));
		
		schedule();
	}
	public void schedule() {
		Process temp;
		
		while(!rQ.isEmpty()) {
			for(int i = 0; i<processes.size();i++) {
				if(time>=processes.get(i).getArrivalTime() && processes.get(i).getCompletionTime()==-1 && !rQ.contains(processes.get(i))) {
					rQ.add(processes.get(i));
				}
			}
			Collections.sort(rQ, new RemainingComparator());
			System.out.println(rQ.get(0).getPid());

			temp = rQ.get(0);
			temp.setRemainingTime(temp.getRemainingTime() - 1);
			if(temp.getRemainingTime() == 0) {
				temp.setCompletionTime(time  + 1);
				temp.setTatTime(temp.getCompletionTime() - temp.getArrivalTime());
				temp.setWaitingTime(temp.getTatTime() - temp.getBurstTime());
				rQ.remove(temp);
			}
			time = time + 1;
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
		SJF sjf = new SJF();
		sjf.getInput();
	}
}
