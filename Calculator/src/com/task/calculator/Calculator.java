package com.task.calculator;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Calculator extends JFrame implements ActionListener {
	JPanel[] panels = new JPanel[5];
	JButton[] buttons = new JButton[19];
	boolean[] operation = new boolean[4];
	double[] operands = { 0, 0 };
	JTextArea result = new JTextArea(1, 20);
	Font font = new Font("TimesRoman", Font.ITALIC, 15);
	String[] myButtons = 
		{ 	"7", "8", "9", "+",
			"4", "5", "6", "-",
			"1", "2", "3", "*",
			".", "/", "C", "v",
			"+/-", "=", "0" };
	public Calculator() {
		super("This is a calculator");
		try {
			UIManager.setLookAndFeel(
			"com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			System.out.println("error" + e);
		}
		setSize(380, 250);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		GridLayout gridLayout = new GridLayout(5, 5);
		setLayout(gridLayout);
		for (int i = 0; i < 4; i++)
			operation[i] = false;

		for (int i = 0; i < 5; i++)
			panels[i] = new JPanel();
		panels[0].setLayout(new FlowLayout(FlowLayout.CENTER));
		for (int i = 1; i < 5; i++)
			panels[i].setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
		for (int i = 0; i < 19; i++) {
			buttons[i] = new JButton();
			buttons[i].setText(myButtons[i]);
			buttons[i].setFont(font);
			buttons[i].addActionListener(this);
		}
		result.setFont(font);
		result.setEditable(false);
		result.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		result.setPreferredSize(new Dimension(300, 35));
		for (int i = 0; i < 14; i++)
			buttons[i].setPreferredSize(new Dimension(45, 40));
		for (int i = 14; i < 18; i++)
			buttons[i].setPreferredSize(new Dimension(100, 40));
		    buttons[18].setPreferredSize(new Dimension(90, 40));
		    panels[0].add(result);
		    add(panels[0]);
		for(int i = 0; i < 4; i++)
		    panels[1].add(buttons[i]);	 
	        panels[1].add(buttons[14]);
	        add(panels[1]);
	    for(int i = 4; i < 8; i++)
	        panels[2].add(buttons[i]);
	        panels[2].add(buttons[15]);
            add(panels[2]);
         for(int i = 8; i < 12; i++)
        	 panels[3].add(buttons[i]);
	         panels[3].add(buttons[16]);
	         add(panels[3]);
	         panels[4].add(buttons[18]);
         for(int i = 12; i < 14; i++)	 
        	 panels[4].add(buttons[i]);
	         panels[4].add(buttons[17]);
	         add(panels[4]);	
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		 if(actionEvent.getSource() == buttons[0])			 
             result.append("7");
         if(actionEvent.getSource() == buttons[1])
             result.append("8");
         if(actionEvent.getSource() == buttons[2])
             result.append("9");
         if(actionEvent.getSource() == buttons[3]) { 
             operands[0] = Double.parseDouble(result.getText());
             operation[0] = true;
             result.setText("");
         }
         if(actionEvent.getSource() == buttons[4])
             result.append("4");
         if(actionEvent.getSource() == buttons[5])
             result.append("5");
         if(actionEvent.getSource() == buttons[6])
             result.append("6");
         if(actionEvent.getSource() == buttons[7]) { 
             operands[0] = Double.parseDouble(result.getText());
             operation[1] = true;
             result.setText("");
         }
         if(actionEvent.getSource() == buttons[8])
             result.append("1");
         if(actionEvent.getSource() == buttons[9])
             result.append("2");
         if(actionEvent.getSource() == buttons[10])
             result.append("3");
         if(actionEvent.getSource() == buttons[11]) {
             operands[0] = Double.parseDouble(result.getText());
             operation[2] = true;
             result.setText("");
         }
         if(actionEvent.getSource() == buttons[12])
             result.append(".");
         if(actionEvent.getSource() == buttons[13]) {
             operands[0] = Double.parseDouble(result.getText());
             operation[3] = true;
             result.setText("");
         }
         if(actionEvent.getSource() == buttons[14])
             clear();
         if(actionEvent.getSource() == buttons[15])
             getSqrt();
         if(actionEvent.getSource() == buttons[16])
        	 negative();
         if(actionEvent.getSource() == buttons[17])
             calculate();
         if(actionEvent.getSource() == buttons[18])
             result.append("0");
	}
    public void calculate() {
        double res = 0;
        operands[1] = Double.parseDouble(result.getText());
        String temp0 = Double.toString(operands[0]);
         String temp1 = Double.toString(operands[1]);
        try {
            if(temp0.contains("-")) {
                String[] temp00 = temp0.split("-", 2);
                operands[0] = (Double.parseDouble(temp00[1]) * -1);
            }
            if(temp1.contains("-")) {
                String[] temp11 = temp1.split("-", 2);
                operands[1] = (Double.parseDouble(temp11[1]) * -1);
            }
        } catch(ArrayIndexOutOfBoundsException e) {
        }
        try {
            if(operation[2] == true)
                res = operands[0] * operands[1];
            else if(operation[3] == true)
                res = operands[0] / operands[1];
            else if(operation[0] == true)
                res = operands[0] + operands[1];
            else if(operation[1] == true)
                res = operands[0] - operands[1];
            result.setText(Double.toString(res));
            for(int i = 0; i < 4; i++)
                operation[i] = false;
        } catch(NumberFormatException e) {
        }
    }
    public void clear() {
            result.setText("");
            for(int i = 0; i < 4; i++)
            	operation[i] = false;
            for(int i = 0; i < 2; i++)
            	operands[i] = 0;
    }
    public void negative () { 
        try {
            double value = Double.parseDouble(result.getText());
            if(value != 0) {
                value = value * (-1);
                result.setText(Double.toString(value));
            }
        } catch(NumberFormatException e) {
        }
    }
    public void getSqrt() {
        try {
            double value = Math.sqrt(Double.parseDouble(result.getText()));
            result.setText(Double.toString(value));
        } catch(NumberFormatException e) {
        }
    }
}