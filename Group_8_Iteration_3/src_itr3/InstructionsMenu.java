import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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
		this.setBounds(0, 0, 1000, 1000);
		this.setOpaque(false);

		// the label for the header
		instructionsHeaderLabel = new JLabel("How To Play");
		EmptyBorder border1 = new EmptyBorder(190, 0, 0, 0);
		instructionsHeaderLabel.setBorder(border1);
		instructionsHeaderLabel.setAlignmentX(CENTER_ALIGNMENT);
		instructionsHeaderLabel.setFont(MainWindow.orbitron.deriveFont(30f));
		instructionsHeaderLabel.setForeground(Color.black);
		instructionsHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		instructionsHeaderLabel.setVerticalAlignment(JLabel.CENTER);

		// instructions text
		JPanel instructionsPanel = new JPanel();
		EmptyBorder border2 = new EmptyBorder(50, 15, 0, 15);
		instructionsPanel.setOpaque(false);
		instructionsPanel.setBorder(border2);
		JTextArea instructionsText = new JTextArea();
		instructionsText.setFont(new Font("Dialog", Font.PLAIN, 14));
		instructionsText.setText(GetInstructionsText());
		JScrollPane instructionsScrollPane = new JScrollPane(instructionsText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		instructionsScrollPane.setOpaque(false);
		instructionsScrollPane.setPreferredSize(new Dimension(900, 500));
		instructionsPanel.add(instructionsScrollPane);

		// the "back" button and listener
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		instructionsBackButton = new JButton("Back");
		instructionsBackButton.setForeground(Color.black);
		instructionsBackButton.setContentAreaFilled(false);
		instructionsBackButton.setBorderPainted(false);
		instructionsBackButton.setAlignmentX(CENTER_ALIGNMENT);
		instructionsBackButton.setFont(MainWindow.orbitron.deriveFont(30f));
		buttonHoverAction(instructionsBackButton);
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
				+ "Begin by starting a New Game, choose your set colours these are for the board colour and each pawn colour.\n"
				+ "You can also choose each players name and if they're a human player or if they're the computer AI.\n"
				+ "There must be four players to begin. Computer can be set to Easy or Hard difficulty. \n"
				+ "Click Start to begin play!\n\n" + "Start Of Play\n\n"
				+ "Each player starts at one side of the board, each player is given 5 fences. \n"
				+ "Every player has a unique pawn and a matching side of the board. \n"
				+ "The players goal is to reach the opposite side they started on. First to get there wins!\n"
				+ "The starting pawn is determined randomly each session.\n"
				+ "At the start of the match the board is empty except for the starting positions of each pawn.\n\n"
				+ "Taking A Turn\n\n"
				+ "A pawn can move one square at a time, horizontally or vertically, forwards or backwards, never diagonally.\n"
				+ "Fences block a players way and must be moved around by the player.  Fences take up two total squares.\n"
				+ "Face to Face pawns can jump over eachother or go in any other direction they choose.\n"
				+ "Face to Face pawns may also go diagonal if there is a fence behind their opponent.\n"
				+ "Face to Face pawns may not jump over more than one pawn at a time.\n"
				+ "Fences are strictly impossible to jump over.\n\n" + "End of the Game\n\n"
				+ "When a player reaches the opposite side of where they began the game is over.\n"
				+ "The first player to do so is the Winner!\n\n";
		return text;
	}

	public void buttonHoverAction(JButton button) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setForeground(new Color(140, 15, 15));
				GameSettings.playButtonSound();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setForeground(Color.black);
			}

		});
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// get the object that performed the action, respond accordingly
		Object selected = event.getSource();

		// was 'back' requested on instructions panel?
		if (selected.equals(instructionsBackButton)) {
			GameSettings.GetMainWindow().ShowPanel(GameSettings.GetMainMenu());
		}
		GameSettings.playButtonSound();
	}

}
