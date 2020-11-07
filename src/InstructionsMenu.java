import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class InstructionsMenu extends JPanel implements ActionListener{

	//instructions
	JLabel instructionsHeaderLabel;
	JTextArea instructionsTextLabel;
	JButton instructionsBackButton;
	
	public InstructionsMenu() {
		
		//build a panel
		super();
		
		//header label at the top
		//instructions label in the center
		//button "Back" at the bottom
		
		//the instructions panel		
		this.setBackground(new Color(9, 132, 227));
		this.setLayout(null);
        
		//the label for the header
		instructionsHeaderLabel = new JLabel("How To Play Quoridor");
		instructionsHeaderLabel.setBounds(10, 12, 978, 52);
		instructionsHeaderLabel.setForeground(new Color(0, 0, 0));
		instructionsHeaderLabel.setBorder(new LineBorder(Color.BLACK, 3, true));
		instructionsHeaderLabel.setBackground(Color.WHITE);
		instructionsHeaderLabel.setFont(new Font("Dialog", Font.BOLD, 35));
		instructionsHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		instructionsHeaderLabel.setVerticalAlignment(JLabel.CENTER);
		
		// instructions text
        instructionsTextLabel = new JTextArea();
        instructionsTextLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.BLACK, Color.DARK_GRAY));
        instructionsTextLabel.setEditable(false);
        instructionsTextLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        instructionsTextLabel.setForeground(Color.BLACK);
        instructionsTextLabel.setRequestFocusEnabled(false);
        instructionsTextLabel.setSelectedTextColor(Color.BLACK);
        instructionsTextLabel.setBackground(new Color(255, 234, 167));
        instructionsTextLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        instructionsTextLabel.setText("Starting a New Game\n  Begin by starting a New Game, choose your set colours these are for the board colour and each pawn\n  colour. You can also choose each players name and if they're a human player or if they're the \n  Computer AI , there must be four players to begin. Computer can be set to Easy or Hard difficulty. \n\n  Click Start to begin play!\n\nStart Of Play\n  Each player starts at one side of the board, each player is given 5 fences. \n  Every player has a unique pawn and a matching side of the board. The players goal is \n  to reach the opposite side they started on. First to get there wins!\n\n  The starting pawn is determined randomly each session.\n  At the start of the match the board is empty except for the starting positions of each pawn.\n\nTaking A Turn\n  A pawn can move one square at a time, horizontally or vertically, forwards or backwards, never diagonally.\n  Fences block a players way and must be moved around by the player.\n  Fences take up two total squares.\n\n  Face to Face pawns can jump over eachother or go in any other direction they choose.\n  Face to Face pawns may also go diagonal if there is a fence behind their opponent.\n  Face to Face pawns may not jump over more than one pawn at a time.\n  Fences are strictly impossible to jump over.\n\nEnd of the Game\n  When a player reaches the opposite side of where they began the game is over, the first player to do \n  so is the Winner!\n");
        instructionsTextLabel.setBounds(10, 76, 975, 622);
        
		
		//the "back" button and listener
		instructionsBackButton = new JButton("");
		instructionsBackButton.setBorderPainted(false);
		instructionsBackButton.setIcon(new ImageIcon(getClass().getResource("/Assets/back_button.png")));
		instructionsBackButton.setBorder(null);
		instructionsBackButton.setBounds(new Rectangle(200, 200, 200, 100));
		instructionsBackButton.setIconTextGap(5);
		instructionsBackButton.setBounds(10, 712, 975, 75);
		instructionsBackButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		instructionsBackButton.setRolloverEnabled(true);
		instructionsBackButton.setRolloverIcon(new ImageIcon(getClass().getResource("/Assets/selected_back_button.png")));
		instructionsBackButton.setSelectedIcon(new ImageIcon(getClass().getResource("/Assets/selected_back_button.png")));
		instructionsBackButton.addActionListener(this);
	    

        this.add(instructionsHeaderLabel);
		this.add(instructionsTextLabel);
		this.add(instructionsBackButton);        
        this.setVisible(false);	
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		//get the object that performed the action, respond accordingly		
		Object selected = event.getSource();
		
		//was 'back' requested on instructions panel?
		if(selected.equals(instructionsBackButton)) {
			GameSettings.GetMainWindow().ShowPanel(GameSettings.GetMainMenu());
		}
		
	}

}
