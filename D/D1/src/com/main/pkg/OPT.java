package com.main.pkg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class OPT {
	LinkedList<Integer> cache, dist;
	ArrayList<Integer> rs;
	int fault = 0;
	int hit = 0;
	int max = 0;
	int max_ind = 0;
	int infinite = 10000000;
	public void getIterations(ArrayList<Integer> rs, int capacity) {
		cache = new LinkedList<>();
		dist = new LinkedList<>();
		this.rs = rs;
		System.out.println("PgNo.\tLRUAllocation");
		for(int i = 0; i<rs.size();i++) {
			System.out.print(rs.get(i));
			if(cache.size() <= 0) {
				cache.add(rs.get(i));
				if(getFutureDistance(rs.get(i), i) + 1== 0) {
					dist.add(max_ind, infinite);
				}
				else {
					dist.add(max_ind, getFutureDistance(rs.get(i), i)+ 1);
				}
				fault = fault + 1;
			}
			else {
				if(cache.contains(rs.get(i))) {
					hit = hit + 1;
					decrementDistances();
					dist.remove(cache.indexOf(rs.get(i)));
					if(getFutureDistance(rs.get(i), i) + 1== 0) {
						dist.add(cache.indexOf(rs.get(i)), infinite);
					}else {
					dist.add(cache.indexOf(rs.get(i)), getFutureDistance(rs.get(i), i) + 1);
					}
					fault = fault + 1;
				}
				else {
					if(cache.size() < capacity) {
						cache.push(rs.get(i));
						decrementDistances();
						if(getFutureDistance(rs.get(i), i) + 1 == 0) {
							dist.push(infinite);
						}
						else {
							dist.push(getFutureDistance(rs.get(i), i) + 1);
						}
						fault = fault + 1;
					}
					else {
						decrementDistances();
						max = 0;
						max_ind = 0;
						for(int j = 0; j<dist.size();j++) {
							if(dist.get(j) > max) {
								max = dist.get(j);
								max_ind = j;
							}
						}
						dist.remove(max_ind);
						cache.remove(max_ind);
						cache.add(max_ind, rs.get(i));
						if(getFutureDistance(rs.get(i), i)+ 1 == 0) {
							dist.add(max_ind, infinite);
						}
						else {
							dist.add(max_ind, getFutureDistance(rs.get(i), i) + 1);
						}
					}
				}
			}
			System.out.print("\t" + displayCache() + "\t" + displayDist());
			System.out.println();
		}
		System.out.println("Total number of faults: " + fault);
		System.out.println("Hit Ratio: " + (Double.valueOf(hit)/Double.valueOf(rs.size())));

	}
	public void decrementDistances() {
		for(int i = 0;i<dist.size();i++) {
			if(dist.get(i) != infinite) {
				dist.set(i, dist.get(i) - 1);
			}
		}
	}
	public int getFutureDistance(int val, int ind) {
		List<Integer> subList = rs.subList(ind+1, rs.size());
		return subList.indexOf(val);
	}
	public String displayCache() {
		String ret = "";
		for(int i = 0;i<cache.size();i++) {
			ret = ret + String.valueOf(cache.get(i)) + " ";
		}
		return ret;
	}
	public String displayDist() {
		String ret = "";
		for(int i = 0;i<dist.size();i++) {
			ret = ret + String.valueOf(dist.get(i)) + " ";
		}
		return ret;
	}
	public static void main(String[] args) {
		OPT opt = new OPT();
		ArrayList<Integer> rs = new ArrayList<>();
		int capacity, size;
		/*
		 * For runtime process
		 */
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the capacity of Main Memory/RAM: ");
		capacity = scan.nextInt();
		System.out.print("Enter the size of reference string: ");
		size = scan.nextInt();
		System.out.println("Enter the reference string: ");
		Integer x;
		for(int i = 0;i<size;i++) {
			x = scan.nextInt();
			rs.add(x);
		}
		
		
		/*
		 * For testing purpose
		 */
		/*
		int[] elements = {2, 3, 2, 1, 5, 2, 4, 5, 3, 2, 5, 2};
		for(int i = 0; i < elements.length;i ++) {
			rs.add(elements[i]);
		}
		capacity = 3;
		*/
		opt.getIterations(rs, capacity);
	}
}
