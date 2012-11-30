package a4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.math.*;

public class A1Panel extends JPanel implements ActionListener {

	private int yIndex = 0;
	private final int WIDTH_OF_TEXT_BOX = 20;

	private enum Operator {
		ADD, DIVIDE, MULTIPLY, NONE, SUBTRACT
	};

	// Text Fields.
	JTextField firstField;
	JTextField secondField;

	// Labels.
	JLabel firstLabel;
	JLabel secondLabel;
	JLabel result;

	// The radio buttons to use.
	ButtonGroup group = new ButtonGroup();
	JRadioButton add;
	JRadioButton subtract;
	JRadioButton multiply;
	JRadioButton divide;
	Operator operator = Operator.NONE;

	// The useless compute button.
	JButton compute;

	public A1Panel() {
		// Setting up the layout
		this.setLayout(new GridBagLayout());

		// Defines the components of the GUI in-order.
		firstLabel = new JLabel("First Number: ");
		firstField = new JTextField("Enter a value", WIDTH_OF_TEXT_BOX);
		secondLabel = new JLabel("Second Number: ");
		secondField = new JTextField("Enter a value", WIDTH_OF_TEXT_BOX);
		add = new JRadioButton("Add");
		subtract = new JRadioButton("Substract");
		multiply = new JRadioButton("Multiply");
		divide = new JRadioButton("Divide");
		compute = new JButton("Compute");
		result = new JLabel("Enter values in the fields.");

		addComponentToScreen(firstLabel);
		addComponentToScreen(firstField);
		addComponentToScreen(secondLabel);
		addComponentToScreen(secondField);
		addComponentToScreen(add);
		addComponentToScreen(subtract);
		addComponentToScreen(multiply);
		addComponentToScreen(divide);
		addComponentToScreen(compute);
		addComponentToScreen(result);

	}

	public void actionPerformed(ActionEvent e) {

		// Gets the button that generated the event.
		Object button = e.getSource();

		if (button instanceof JRadioButton) {
			operator = getOperatorAccordingToButton(button);

		}
		
		// Initialises the two integers to zero.
		BigInteger fistBigInteger = new BigInteger("0");
		BigInteger secondBigInteger = new BigInteger("0");

		// Does the computation if the input is valid.
		try {

			fistBigInteger = new BigInteger(firstField.getText());
			secondBigInteger = new BigInteger(secondField.getText());

			result.setText(getComputationResult(operator, fistBigInteger,
					secondBigInteger));

		} catch (Exception exception) {
			result.setText("Check Input.");
		}

	}

	/**
	 * Adds the component to the Graphical User Interface. Each component will
	 * be added above the other.
	 * 
	 * @param b
	 */
	public void addComponentToScreen(Component b) {

		// Defines the graphical constraints.
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = yIndex;

		// Groups all the RadioButtons together.
		if (b instanceof JRadioButton) {
			group.add((JRadioButton) b);

		}
		// Makes the action on the button detectable.
		if (b instanceof AbstractButton) {
			((AbstractButton) b).addActionListener(this);
		}

		// Adds the component to the screen with the predefined constraints.
		add(b, c);

		// Sets the current row to be occupied. the next element will be on the
		// next row.
		yIndex++;
	}

	/**
	 * Determines what value the operator should be assigned according to the
	 * source object.
	 * 
	 * @param src
	 * @return
	 */
	private Operator getOperatorAccordingToButton(Object src) {

		Operator operator;

		if (src.equals(add)) {
			operator = Operator.ADD;

		} else if (src.equals(subtract)) {
			operator = Operator.SUBTRACT;

		} else if (src.equals(multiply)) {
			operator = Operator.MULTIPLY;

		} else if (src.equals(divide)) {
			operator = Operator.DIVIDE;
		} else {
			operator = Operator.NONE;
		}

		return operator;
	}

	private String getComputationResult(Operator operator, BigInteger first,
			BigInteger second) {
		String result = "";
		switch (operator) {
		case ADD:
			result = first.add(second).toString();
			break;

		case SUBTRACT:
			result = first.subtract(second).toString();
			break;

		case MULTIPLY:
			result = first.multiply(second).toString();
			break;

		case DIVIDE:
			result = first.divide(second).toString();
			break;

		case NONE:
			result = "Select an operation.";
			break;
		}

		return result;
	}
}