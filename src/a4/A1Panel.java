package a4;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

import java.math.*;

//public enum Operator{ADD,SUBTRACT,MULTIPLY,DIVIDE}

public class A1Panel extends JPanel implements ActionListener
{
	int yIndex = 0;
	
	//Terxt Fields
	JTextField first;
	JTextField second;

	//Various labels
	JLabel firstLabel;
	JLabel secondLabel;
	JLabel result;

	//Computation values
	BigInteger argOne;
	BigInteger argTwo;
	BigInteger argResult;

	//For the action performed
	Boolean addBoolean = false;
	Boolean subtractBoolean = false;
	Boolean multiplyBoolean = false;
	Boolean divideBoolean = false;

	//The buttons to use
	JRadioButton addButton;
	JRadioButton subtractButton;
	JRadioButton multiplyButton;
	JRadioButton divideButton;
	JButton compute;
	

	public A1Panel()
	{
		//Setting up the layout
		this.setLayout(new GridBagLayout());

		//First Arguement label
		firstLabel = new JLabel("First Number: ");
		addJButton(firstLabel);

		//First text field for the first arguement
		first = new JTextField(50);
		addJButton(first);

		//Second Arguement label
		secondLabel = new JLabel("Second Number: ");
		addJButton(secondLabel);

		//Second arguement field
		second = new JTextField(50);
		addJButton(second);

		//Listeners for the two text fields
		first.addActionListener(this);
		second.addActionListener(this);

		//Initialising the add Radio Button
		addButton = new JRadioButton("Add");
		addJButton(addButton);

		//Initialising the subtract Radio Button
		subtractButton = new JRadioButton("Substract");
		addJButton(subtractButton);

		//Initialising the multiply Radio Button
		multiplyButton = new JRadioButton("Multiply");
		addJButton(multiplyButton);

		//Initialising the subtract Radio Button
		divideButton = new JRadioButton("Divide");
		addJButton(divideButton);

		//The compute button
		compute = new JButton("Compute");
		addJButton(compute);

		//Add action listener for the compute button
		compute.addActionListener(this);
	
		//Add the Radio Buttons to a button group
		ButtonGroup group = new ButtonGroup();
		group.add(addButton);
		group.add(subtractButton);
		group.add(multiplyButton);
		group.add(divideButton);

		//Adding an action listener for each Radio button
		addButton.addActionListener(this);
		subtractButton.addActionListener(this);
		multiplyButton.addActionListener(this);
		divideButton.addActionListener(this);

		//The result label
		result = new JLabel("0");
		addJButton(result);
	}

	public void addJButton(Component b){
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = yIndex;
		add(b, c);
		yIndex++;
	}

	public void actionPerformed(ActionEvent e) 
	{
		argOne = new BigInteger(first.getText());
		argTwo = new BigInteger(second.getText());	
		
		//These if/else if statements are checking to see which Radio Button is selected
			if (e.equals(addButton))
			{
				addBoolean = true;
				subtractBoolean = false;
				multiplyBoolean = false;
				divideBoolean = false;
			}

		else if (e.equals(subtractButton))
			{
				addBoolean = false;
				subtractBoolean = true;
				multiplyBoolean = false;
				divideBoolean = false;
			}	
			
		else if (e.equals(multiplyButton))
			{
				addBoolean = false;
				subtractBoolean = false;
				multiplyBoolean = true;
				divideBoolean = false;
			}

		else if (e.equals(divideButton))
			{
				addBoolean = false;
				subtractBoolean = false;
				multiplyBoolean = false;
				divideBoolean = true;
			}
		//These else if statements will perform the actual computation and display in the result label based on which radio button was pressed
		else if (e.getSource() == compute && addBoolean)
		{
		
			argResult = argOne.add(argTwo);
			result.setText(argResult.toString());
		}

		else if (e.getSource() == compute && subtractBoolean)
		{
			
			argResult = argOne.subtract(argTwo);
			result.setText(argResult.toString());
		}

		else if (e.getSource() == compute && multiplyBoolean)
		{

			argResult = argOne.multiply(argTwo);
			result.setText(argResult.toString());
		}

		else if (e.getSource() == compute && divideBoolean)
		{

			argResult = argOne.divide(argTwo);
			result.setText(argResult.toString());
		}
	}
}