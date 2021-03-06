package com.dakshpokar.c1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GanttChart {
	private JFrame frm;
	private ProcessList processList = new ProcessList();
	public GanttChart() {
		
	}
	public void getFrame(ProcessList p) {
		System.out.println("Test Gantt");
		processList = p;
		frm = new JFrame();
		frm.getContentPane().setLayout(null);
		frm.setBounds(100, 100, 500, 500);
		JPanel p2 = new JPanel();

		Integer width = 50;
		Integer Max = 180;
		Integer Min = 0;
		Double timeline = 0.0;
		for(Process process : processList.getProcesses()){
			System.out.println("here!");
			JPanel p1 = new JPanel();
			JLabel l1 = new JLabel();
			JLabel pn1 = new JLabel(process.getName());
			
			pn1.setForeground(Color.white);
			pn1.setHorizontalAlignment(SwingConstants.CENTER);
			pn1.setVerticalAlignment(SwingConstants.CENTER);
			p1.setBounds(width,50,(int)Math.round(process.getExecTime() * 10),70);
			pn1.setBounds(0,0, (int)Math.round(process.getExecTime() * 10),70);
			l1.setBounds(width, 50+75, width, 75);
			if(Math.round(timeline) == 0){
				l1.setText("0");
			}else{
			l1.setText(String.valueOf((int)Math.round(timeline)));
			}
			p1.setBackground(new Color(Min + (int)(Math.random() * ((Max - Min) + 1)),Min + (int)(Math.random() * ((Max - Min) + 1)),Min + (int)(Math.random() * ((Max - Min) + 1))));
		
			p1.add(pn1);
			width = width + (int)Math.round(process.getExecTime() * 10) + 1;
			frm.getContentPane().add(p1);
			frm.getContentPane().add(l1);
			timeline = timeline + process.getExecTime();
		}
		JLabel l1 = new JLabel();
		l1.setBounds(width, 50+75, width, 75);
		l1.setText(String.valueOf((int)Math.round(timeline)));
		frm.getContentPane().add(l1);

		frm.setVisible(true);
		frm.show();
	}
}
