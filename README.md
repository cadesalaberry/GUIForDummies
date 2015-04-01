`GUIForDummies`
===============

A small project to start working on swing UI.


## Introduction

This assignment will give you some experience with `Swing`, the Java framework for graphical user interfaces.
The basic idea of the assignment is simple. You will write two panel classes that provide an interface for the problem in Assignment 1 and either of Assignments 2 or 3. Below is screenshot of one possible solution, where all three Assignments were used.


![Screenshot of possible solution](https://raw.github.com/cadesalaberry/GUIForDummies/master/assets/example.png)


## Question 1 (70 points)

Modify the `A1Panel.java` class such that the panel has the following:

• two `JTextField` components for the user to enter the arguments (base 10); these fields should have initial values of `0`; the fields should be accompanied by argument labels such as shown above
• four `JRadioButton` components which determine which operator is applied to the two input values. These buttons must be grouped together so only one of them is selected at a time. You will need to read the Java tutorials to see how this is done. http://docs.oracle.com/javase/tutorial/uiswing/components/index.html
• a **compute result** button that the user clicks to compute the result of the selected operator and the two arguments.
• a `JLabel` which displays the result of the operation.

Your panel class implements `ActionListener` and so it should have a single `actionPerformed()` method. In particular, there must be a single listener – namely the `A1Panel` itself. This listener should be defined such that when the user presses the **compute result** button, the selected operation is computed on the two arguments and displayed.

Of the 70 points in this question, 40 points will be given for setting up the components in the panel and 30 points will be given for the listener.

*To simplify your task, use the Java `BigInteger` class. You should also restrict yourself to base 10 only.*


## Question 2 (30 points)

Modify either `A2Panel.java` or `A3Panel.java` (but not both) so that it provides an interface for the solution code.

• For the Assignment 2 panel, the user should enter the string to be parsed in a `JTextField`. When the user hits an “evaluate” button, a listener should evaluate whether the parsed string is a valid statement or not. If the string is valid, then the field that announces the result should be green; otherwise the field that announces the result should be red. See the example above. The listener also
must display the number of tokens in the input string.
Note that the listener code must invoke methods from the classes from the Assignment 2 in order to decide whether the statement is valid or not, and how many tokens there are.

• For the Assignment 3 panel, the user should enter a prefix into a text field. The set of all matching prefixes then should be displayed. Use a `JTextField` for the user to enter the prefix. To display the matching words with the given prefix, I suggest you use a `JTextArea`. When the user enters a prefix (or re-enters a different prefix), the listener should compute and display in that text area. It is enough that the text in your `JTextArea` consists of the words separated by line feeds ( `\n` ) characters.

Because the list of matching words might be very long, you should use a `JScrollPane` to display it. The scroll pane is not shown in the above example because the list of matching words for that prefix “l” is not long enough to fill the
text area.

Note that the constructor for **A3Panel** takes a String as input, namely the path to the file that contains all the words. You will need to change the path to this file in
**GUI.java**.

Of the 30 points for this question, 10 points are for setting up the components in the panel and 20 are for the listener.


## Result

![Screenshot of implementation](https://raw.github.com/cadesalaberry/GUIForDummies/master/assets/screenshot.png)