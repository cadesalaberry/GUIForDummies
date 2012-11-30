package a4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.math.*;

public class A1Panel extends JPanel implements ActionListener, KeyListener {

	private static final long serialVersionUID = 7581649428792952125L;

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
	JTextField result;

	// The radio buttons to use.
	ButtonGroup group = new ButtonGroup();
	JRadioButton add;
	JRadioButton subtract;
	JRadioButton multiply;
	JRadioButton divide;
	Operator operator = Operator.NONE;

	// The close to useless compute button.
	JButton evaluate;

	public A1Panel() {
		// Setting up the layout
		this.setLayout(new GridBagLayout());

		// Defines the components of the GUI in-order.
		firstLabel = new JLabel("First Number: ");
		firstField = new JTextField(WIDTH_OF_TEXT_BOX);
		secondLabel = new JLabel("Second Number: ");
		secondField = new JTextField(WIDTH_OF_TEXT_BOX);
		add = new JRadioButton("Add");
		subtract = new JRadioButton("Substract");
		multiply = new JRadioButton("Multiply");
		divide = new JRadioButton("Divide");
		evaluate = new JButton("Evaluate");
		result = new JTextField("Enter values in the fields.");
		result.setEditable(false);

		addComponentToScreen(firstLabel);
		addComponentToScreen(firstField);
		addComponentToScreen(secondLabel);
		addComponentToScreen(secondField);
		addComponentToScreen(add);
		addComponentToScreen(subtract);
		addComponentToScreen(multiply);
		addComponentToScreen(divide);
		addComponentToScreen(evaluate);
		addComponentToScreen(result);

	}

	public void actionPerformed(ActionEvent e) {

		// Changes the operator if a RadioButton is touched.
		if (e.getSource() instanceof JRadioButton) {
			operator = getOperatorAccordingToButton(e.getSource());
		}

		refreshComputationResult();
	}

	/**
	 * Does the computation if the input is valid. Displays a warning otherwise.
	 */
	private void refreshComputationResult() {
		//
		try {

			BigInteger fistBigInteger = new BigInteger(firstField.getText());
			BigInteger secondBigInteger = new BigInteger(secondField.getText());

			String str = getComputationResult(operator, fistBigInteger,
					secondBigInteger);
			result.setText(str);

		} catch (Exception exception) {
			result.setText("Please check your input.");
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

		// Makes the action on the button detectable.
		if (b instanceof AbstractButton) {
			((AbstractButton) b).addActionListener(this);
			// Groups all the RadioButtons together.
			if (b instanceof JRadioButton) {
				group.add((JRadioButton) b);

			}
		} else if (b instanceof JTextField) {
			((JTextField) b).addKeyListener(this);
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
			result = "Please select an operation.";
			break;
		}

		return result;
	}

	public void keyReleased(KeyEvent e) {
		refreshComputationResult();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}