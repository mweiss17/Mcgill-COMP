package a4posted;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.*;
import java.math.BigInteger;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.JPanel;



public class A1Panel extends JPanel implements ActionListener
{


	JTextField firstArg, secondArg;
	BigInteger arg1Int, arg2Int, resultInt;
	JRadioButton Add, Subtract, Multiply, Divide;
	JButton computeResult;
	JLabel result, label1, label2;
	Boolean AddBoolean = false;
	Boolean SubtractBoolean = false;
	Boolean MultiplyBoolean = false; 
	Boolean DivideBoolean = false;

	//constructor
	public A1Panel()
	{
		//layout initialization
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(gridbag);
		
		//first label
		JLabel label1 = new JLabel("First Argument");
		label1.setFont(new Font("Times", Font.BOLD, 16));
		label1.setBackground(Color.lightGray);
		label1.setOpaque(true);
		label1.setHorizontalAlignment( SwingConstants.CENTER );
		c.gridx = 0;
		c.gridy = 0;
		add(label1, c);

		
		//First Arg textfield
		firstArg = new JTextField(25);
		firstArg.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		add(firstArg, c);

		
		//second label
		JLabel label2 = new JLabel("Second Argument");
		label2.setFont(new Font("Times", Font.BOLD, 16));
		label2.setBackground(new Color(160, 160, 160));
		label2.setOpaque(true);
		label2.setHorizontalAlignment( SwingConstants.CENTER );
		c.gridx = 0;
		c.gridy = 2;
		add(label2, c);
		
		
		//Second Arg textfield
		secondArg = new JTextField(25);
		secondArg.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		add(secondArg, c);
		
		
		
		//***Radio Buttons***//
		
		//ADD radio button
		Add = new JRadioButton("Add");
		Add.addActionListener(this);
		c.gridx = 0;
		c.gridy = 4;
		add(Add, c);
		
		//SUBTRACT radio button
		Subtract = new JRadioButton("Subtract");
		Subtract.addActionListener(this);
		c.gridx = 0;
		c.gridy = 5;
		add(Subtract, c);
		
		//MULTIPLY radio button
		Multiply = new JRadioButton("Multiply");
		Multiply.addActionListener(this);
		c.gridx = 0;
		c.gridy = 6;
		add(Multiply, c);
		
		//DIVISION radio Button
		Divide = new JRadioButton("Divide");
		Divide.addActionListener(this);
		c.gridx = 0;
		c.gridy = 7;
		add(Divide, c);
		
		//button group
		ButtonGroup myButtonGroup = new ButtonGroup();
		myButtonGroup.add(Add);
		myButtonGroup.add(Subtract);
		myButtonGroup.add(Multiply);
		myButtonGroup.add(Divide);
		
		
		//Compute Result button
		computeResult = new JButton("Compute Result");
		computeResult.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 8;
		add(computeResult, c);
		
		
		//Result Label
		result = new JLabel("waiting for you to insert values...");
		c.gridx = 0;
		c.gridy = 9;
		this.add(result, c);
	}
	public void actionPerformed(ActionEvent e) 
	{	
		
		
		if(e.getSource() == computeResult && AddBoolean)
		{
			BigInteger arg1Int = new BigInteger(firstArg.getText());
			BigInteger arg2Int = new BigInteger(secondArg.getText());
			resultInt = arg1Int.add(arg2Int);
			result.setText("The result is: " + resultInt.toString());
		}
		
		else if(e.getSource() == computeResult && SubtractBoolean)
		{
			BigInteger arg1Int = new BigInteger(firstArg.getText());
			BigInteger arg2Int = new BigInteger(secondArg.getText());
			resultInt = arg1Int.subtract(arg2Int);
			result.setText("The result is: " + resultInt.toString()); 
		}
		
		else if (e.getSource() == computeResult && MultiplyBoolean)
		{
			BigInteger arg1Int = new BigInteger(firstArg.getText());
			BigInteger arg2Int = new BigInteger(secondArg.getText());
			resultInt = arg1Int.multiply(arg2Int);
			result.setText("The result is: " + resultInt.toString()); 
		}
		else if (e.getSource() == computeResult && DivideBoolean)
		{
			BigInteger arg1Int = new BigInteger(firstArg.getText());
			BigInteger arg2Int = new BigInteger(secondArg.getText());
			resultInt = arg1Int.divide(arg2Int);
			result.setText("The result is: " + resultInt.toString()); 
		}
		
		else if(e.getSource() == Add)
		{
			AddBoolean = true;
			SubtractBoolean = false;
			MultiplyBoolean = false;
			DivideBoolean = false;
		}
		else if(e.getSource() == Subtract)
		{
			AddBoolean = false;
			SubtractBoolean = true;
			MultiplyBoolean = false;
			DivideBoolean = false;
		}
		else if(e.getSource() == Multiply)
		{
			AddBoolean = false;
			SubtractBoolean = false;
			MultiplyBoolean = true;
			DivideBoolean = false;
		}
		else if(e.getSource() == Divide)
		{
			AddBoolean = false;
			SubtractBoolean = false;
			MultiplyBoolean = false;
			DivideBoolean = true;
		}
	}
}
