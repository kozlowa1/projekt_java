package Grawzycie;

import javax.swing.*;
 
 public class MyFrame extends JFrame {
 
 	public MyFrame() {
 		super("Wireworld");
 
 		JPanel buttonPanel = new ButtonPanel();
 		add(buttonPanel);
 
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		pack();
 		setVisible(true);
 	}
 }