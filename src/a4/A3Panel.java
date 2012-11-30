package a4;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import a3.AutoComplete;
import a3.Trie;

public class A3Panel extends JPanel implements KeyListener {

	private static final long serialVersionUID = -7696091701690806729L;
	private static final int WIDTH_OF_TEXT_BOX = 20;
	private ArrayList<String> words = new ArrayList<>();
	private Trie suggestions = new Trie();
	private int yIndex = 0;

	private JTextField inputField;
	private JLabel title;
	private JTextArea textArea;
	private JScrollPane scrollArea;

	public A3Panel(String fileName) {

		suggestions = getSuggestionTrieFromFile(fileName);

		// Setting up the layout
		this.setLayout(new GridBagLayout());

		title = new JLabel("Dynamic AutoComplete");
		inputField = new JTextField(WIDTH_OF_TEXT_BOX);
		textArea = new JTextArea(12, WIDTH_OF_TEXT_BOX);
		textArea.setText("/**\n * You can add words to \n * the suggestion list \n"
						+ " * by pressing enter at \n * the end of the word.\n */");
		scrollArea = new JScrollPane(textArea,20,31);

		addComponentToScreen(title);
		addComponentToScreen(inputField);
		addComponentToScreen(scrollArea);
	}

	public void keyReleased(KeyEvent e) {

		// Adds a word to the suggestion list if enter is pressed.
		if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
			suggestions.insert(inputField.getText());
		}

		// Gets the suggestions to display from the trie.
		String prefix = inputField.getText();
		ArrayList<String> matchingSuggestions = suggestions
				.getAllPrefixMatches(prefix);

		// Displays the suggestions on the screen.
		textArea.setText("");
		for (String suggestion : matchingSuggestions) {
			textArea.append(suggestion + "\n");
		}
	}

	/**
	 * Reads the file, and put all the words in a trie.
	 * 
	 * @param fileName
	 * @return
	 */
	private Trie getSuggestionTrieFromFile(String fileName) {
		words = AutoComplete.readWordsFromFile(fileName);
		Trie trie = new Trie();

		for (String word : words) {
			trie.insert(word);
		}
		return trie;
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

			if (b instanceof JTextField) {
				((JTextField) b).addKeyListener(this);
				((JTextField) b)
				.setToolTipText("You can add word to the suggestion list"
						+ " by pressing enter at the end of the word.");
			}
			

		// Adds the component to the screen with the predefined constraints.
		add(b, c);

		// Sets the current row to be occupied. the next element will be on the
		// next row.
		yIndex++;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
