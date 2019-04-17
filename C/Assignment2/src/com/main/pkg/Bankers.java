package com.main.pkg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bankers {
	private ArrayList<ArrayList<Integer>> allocated, need;
	private ArrayList<Integer> temp;
	private ArrayList<Integer> pre_avail, total_avail;
	int p, r, tem;
	Scanner scan;
	public Bankers() {
	}
	public void getInput() {
		scan = new Scanner(System.in);
		System.out.println("Welcome to Banker's Algorithm Program");
		System.out.println("Enter the number of processes: ");
		p = scan.nextInt();
		System.out.println("Enter the number of resources: ");
		r = scan.nextInt();
		
		total_avail = new ArrayList<>();
		System.out.println("Enter the total number of available resources: ");
		for(int i = 0; i<r;i++) {
			System.out.print("Enter amount for resource " + String.valueOf(i) + ": ");
			tem = scan.nextInt();
			total_avail.add(tem);
		}
		System.out.println("Enter the allocation matrix: ");
		/*
		 * Initialization of Matrices
		 */
		allocated = new ArrayList<ArrayList<Integer>>();	//Initialize Allocated Matrix
		need = new ArrayList<ArrayList<Integer>>();	//Initialize Need Matrix
		temp = new ArrayList<>();
		/*
		 * Getting all input matrices from user
		 */
		for(int i = 0; i<p;i++) {
			temp.clear();
			for(int j = 0;j<r;j++) {
				System.out.print("Enter " + String.valueOf(i) + ", " + String.valueOf(j) + ": ");
				tem = scan.nextInt();
				temp.add(tem);
				pre_avail.set(j, pre_avail.get(j) + tem);	//Computing sum for remaining resources here itself
			}
			allocated.add(temp);
		}
		/*
		 * Finding the remaining resources available
		 */
		for(int i = 0;i<r;i++) {
			pre_avail.set(i, total_avail.get(i) - pre_avail.get(i)); //pre_avail[i] has sum of allocated resources
		}
		/*
		 * Getting need matrix input from user;
		 */
		for(int i = 0; i<p;i++) {
			temp.clear();
			for(int j = 0;j<r;j++) {
				System.out.print("Enter " + String.valueOf(i) + ", " + String.valueOf(j) + ": ");
				tem = scan.nextInt();
				temp.add(tem);
			}
			need.add(temp);
		}
		
		processMatrices();	//Function giving the final output
	}
	public void testInput() {
		p = 3;
		r = 3;
		allocated = new ArrayList<ArrayList<Integer>>();
		need = new ArrayList<ArrayList<Integer>>();
		temp = new ArrayList<>();
		pre_avail = new ArrayList<>();
		total_avail = new ArrayList<>();
		total_avail.add(7);
		total_avail.add(7);
		total_avail.add(10);
		pre_avail.add(1);
		pre_avail.add(1);
		pre_avail.add(0);
		
		
		temp = new ArrayList<>();
		temp.add(2);
		temp.add(2);
		temp.add(3);
		allocated.add(temp);
		temp = new ArrayList<>();
		temp.add(2);
		temp.add(0);
		temp.add(3);
		allocated.add(temp);
		temp = new ArrayList<>();
		temp.add(1);
		temp.add(2);
		temp.add(4);
		allocated.add(temp);
		
		
		
		temp = new ArrayList<>();
		temp.add(3);
		temp.add(6);
		temp.add(8);
		need.add(temp);
		temp = new ArrayList<>();
		temp.add(4);
		temp.add(3);
		temp.add(3);
		need.add(temp);
		temp = new ArrayList<>();
		temp.add(3);
		temp.add(4);
		temp.add(4);
		need.add(temp);
	}
	public void processMatrices() {
		testInput();
		ArrayList<ArrayList<Integer>> rem_need = new ArrayList<ArrayList<Integer>>();	//Initialize remaining need matrix
		/*
		 * Computing Remaining Need Matrix
		 */
		for(int i = 0;i<p;i++) {
			temp = new ArrayList<>();
			for(int j = 0;j<r;j++) {
				temp.add(need.get(i).get(j) - allocated.get(i).get(j));
			}
			rem_need.add(temp);
		}
		/*
		 * Start of Actual Banker's Algorithm
		 */
		boolean deadlock = false, exec = true;
		ArrayList<String> executed = new ArrayList<>();
		for(int i = 0; i<rem_need.size();i++) {
			executed.add(String.valueOf(i));
		}
		int i, j, count = 0;
		i = 0;
		j = 0;
		
		while(deadlock == false && executed.size()>0) {
			/*
			 * Process wise starting with process i and resource j
			 */

			exec = true;
			for(j=0;j<r;j++) {
				if(rem_need.get(i).get(j) > pre_avail.get(j)) {
					exec = false;
					break;
				}
			}
			
			if(exec == true) {
				count = 0;
				for(j=0;j<r;j++) {
					pre_avail.set(j, pre_avail.get(j) + allocated.get(i).get(j));
				}
				System.out.println("Executed Process " + String.valueOf(i));
				executed.remove(String.valueOf(i));
			}
			else {
				if(count == executed.size()) {
					deadlock = true;
				}
				count = count + 1;
			}
			
			i++;
			if(i >= rem_need.size()) {
				i = Integer.parseInt(executed.get(0));
			}
		}
		if(deadlock == true) {
			System.out.println("Deadlock detected!");
		}
	}
	public static void main(String[] args) {
		Bankers bank = new Bankers();
		bank.processMatrices();
	}
}
