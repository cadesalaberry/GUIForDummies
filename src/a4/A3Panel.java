package a4;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import a3.AutoComplete;
import a3.Trie;

public class A3Panel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = -7696091701690806729L;
	private static final int WIDTH_OF_TEXT_BOX = 15;
	private ArrayList<String> words = new ArrayList<>();
	private Trie suggestions = new Trie();
	private int yIndex = 0;
	
	private JTextField inputField;
	private JButton getSuggestionsButton;
	private JTextField infoField;
	private JTextArea textArea;
	private JScrollPane scrollArea;
	
	public A3Panel(String fileName) {
		
		suggestions = getSuggestionTrieFromFile(fileName);
		
		// Setting up the layout
		this.setLayout(new GridBagLayout());
		
		inputField = new JTextField(WIDTH_OF_TEXT_BOX);
		getSuggestionsButton = new JButton("Get Suggestions");
		infoField = new JTextField("^ Type in a word above ^" ,WIDTH_OF_TEXT_BOX);
		infoField.setEditable(false);
		textArea = new JTextArea(10,WIDTH_OF_TEXT_BOX);
		scrollArea = new JScrollPane(textArea);
		
		addComponentToScreen(inputField);
		addComponentToScreen(getSuggestionsButton);
		addComponentToScreen(infoField);
		addComponentToScreen(scrollArea);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setText(getStringRepresentationOf(suggestions));
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
		
		for(String word : words){
			trie.insert(word);
		}
		return trie;
	}

	private String getStringRepresentationOf(Trie trie){
		String result = "";
		
		for (String suggestion : trie.getAllPrefixMatches("")){
			result += suggestion + "\n";
		}
		return result;
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

		
		if (b instanceof AbstractButton) {
			// Makes the action on the button detectable.
			((AbstractButton) b).addActionListener(this);
		}

		// Adds the component to the screen with the predefined constraints.
		add(b, c);

		// Sets the current row to be occupied. the next element will be on the
		// next row.
		yIndex++;
	}
}
