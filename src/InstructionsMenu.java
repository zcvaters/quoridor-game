import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class InstructionsMenu extends JPanel implements ActionListener {

	// instructions
	JLabel instructionsHeaderLabel;
	JTextArea instructionsTextLabel;
	JButton instructionsBackButton;

	public InstructionsMenu() {

		// build a panel
		super();

		// header label at the top
		// instructions label in the center
		// button "Back" at the bottom

		// the instructions panel
		// size to fit frame
		this.setBounds(0, 0, 1200, 800);
		this.setOpaque(false);

		// the label for the header
		instructionsHeaderLabel = new JLabel("How To Play");
		EmptyBorder border1 = new EmptyBorder(20, 15, 0, 15);
		instructionsHeaderLabel.setBorder(border1);
		instructionsHeaderLabel.setAlignmentX(CENTER_ALIGNMENT);
		instructionsHeaderLabel.setFont(MainWindow.orbitron.deriveFont(30f));
		instructionsHeaderLabel.setForeground(Color.black);
		instructionsHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		instructionsHeaderLabel.setVerticalAlignment(JLabel.CENTER);

		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		java.net.URL helpURL = getClass().getResource("/Assets/instructions.html");
		if (helpURL != null) {
		    try {
		        editorPane.setPage(helpURL);
		    } catch (IOException e) {
		        System.err.println("Attempted to read a bad URL: " + helpURL);
		    }
		} else {
		    System.err.println("Couldn't find file: TextSamplerDemoHelp.html");
		}

		//Put the editor pane in a scroll pane.
		JScrollPane editorScrollPane = new JScrollPane(editorPane);
		editorScrollPane.setVerticalScrollBarPolicy(
		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setPreferredSize(new Dimension(250, 145));
		editorScrollPane.setMinimumSize(new Dimension(10, 10));
		// instructions text
		JPanel instructionsPanel = new JPanel();
		EmptyBorder border2 = new EmptyBorder(50, 15, 0, 15);
		instructionsPanel.setOpaque(false);
		instructionsPanel.setBorder(border2);
		//JTextArea instructionsText = new JTextArea();
		//instructionsText.setFont(new Font("Dialog", Font.PLAIN, 14));
		//instructionsText.setText(GetInstructionsText());
		JScrollPane instructionsScrollPane = new JScrollPane(editorPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		instructionsScrollPane.setOpaque(false);
		instructionsScrollPane.setPreferredSize(new Dimension(900, 500));
		editorPane.setPreferredSize(new Dimension(900, 500));
		editorPane.setOpaque(false);
		instructionsPanel.add(editorPane);

		// the "back" button and listener
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		instructionsBackButton = new Buttons("Back");
		instructionsBackButton.setForeground(Color.black);
		instructionsBackButton.setContentAreaFilled(false);
		instructionsBackButton.setBorderPainted(false);
		instructionsBackButton.setAlignmentX(CENTER_ALIGNMENT);
		instructionsBackButton.setFont(MainWindow.orbitron.deriveFont(30f));
		buttonPanel.add(instructionsBackButton);
		instructionsBackButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		instructionsBackButton.addActionListener(this);

		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		this.setLayout(boxLayout);
		this.add(instructionsHeaderLabel);
		this.add(instructionsPanel);
		this.add(buttonPanel);
		this.setVisible(false);
	}

	private String GetInstructionsText() {
		String text = "\nStarting a New Game\n\n"
				+ "Begin by starting a New Game, choose your colour set. Each colour set modifies the board colour and each pawn colour.\n"
				+ "You can also choose each players name and if they're a human player or if they're are a computer AI.\n"
				+ "There must be four players to begin. Each Computer AI can be set to Easy or Hard difficulty. \n"
				+ "Click Let's Play when you're all set up to begin play!\n\n" + "Start Of Play\n\n"
				+ "Each player starts at one side of the board, each player is given 5 fences. \n"
				+ "Every player has a unique pawn and a different goal to win the game. \n"
				+ "The players goal is to reach the opposite side they started on. First to get there wins!\n"
				+ "The starting pawn is determined randomly each session.\n"
				+ "At the start of the match the board is empty except for the starting positions of each pawn.\n\n"
				+ "Taking A Turn\n\n"
				+ "A pawn can move one square at a time, horizontally or vertically, forwards or backwards, never diagonally.\n"
				+ "Fences block a players path, Fences never move once they're placed. Fences take up two total squares.\n"
				+ "Face to Face pawns can jump over eachother or go in any other direction they choose.\n"
				+ "Face to Face pawns may also go diagonal if there is a fence behind their opponent.\n"
				+ "Face to Face pawns may not jump over more than one pawn at a time.\n"
				+ "Fences are strictly impossible to jump over.\n\n" + "End of the Game\n\n"
				+ "When a player reaches the opposite side of where they began the game is over.\n"
				+ "The first player to do so is the Winner!\n\n";
		return text;
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		// get the object that performed the action, respond accordingly
		Object selected = event.getSource();

		// was 'back' requested on instructions panel?
		if (selected.equals(instructionsBackButton)) {
			GameSettings.GetMainWindow().ShowPanel(GameSettings.GetMainMenu());
		}
	}

}
