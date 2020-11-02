import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import java.awt.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class MainWindow extends JFrame implements ActionListener {
	
	
	//main menu
	JPanel mainMenuPanel;
	JLabel mainMenuHeaderLabel;
	JButton newGameButton;
	JButton loadGameButton;
	JButton instructionsButton;
	JButton quitButton;
	JPanel menuButtonsPanel;  //subpanel in this main menu panel, to organize buttons
	
	//new game
	JPanel newGamePanel;
	JLabel newGameHeaderLabel;
	JLabel newGameChooseColorsLabel;
	JButton color1Button;
	JButton color2Button;
	JButton color3Button;
	JPanel colorButtons; //subpanel
	//configurePlayersPanel and subpanels
	JPanel showPlayersPanel;
	JPanel configPlayersPanel;
	String[] comboBoxOptions = {"Human", "Computer(Easy)", "Computer(Hard)"};
	JPanel firstPanel;
	JLabel firstIconLabel;
	JLabel firstNameLabel;
	JTextField firstTextField;
	JComboBox firstComboBox;
	JPanel secondPanel;
	JLabel secondIconLabel;
	JLabel secondNameLabel;
	JTextField secondTextField;
	JComboBox secondComboBox;
	JPanel thirdPanel;
	JLabel thirdIconLabel;
	JLabel thirdNameLabel;
	JTextField thirdTextField;
	JComboBox thirdComboBox;
	JPanel fourthPanel;
	JLabel fourthIconLabel;
	JLabel fourthNameLabel;
	JTextField fourthTextField;
	JComboBox fourthComboBox;
	//sub-panel to hold "play" and "back" buttons on the new game panel
	JPanel playBackButtonsPanel;	
	JButton newGameBackButton;
	JButton newGamePlayButton;
	
	
	//load game
	JPanel loadGamePanel;
	JLabel loadGameHeaderLabel;
	JLabel loadGameTextLabel;
	JButton loadGameBackButton;	
	
	//instructions
	JPanel instructionsPanel;
	JLabel instructionsHeaderLabel;
	JTextArea instructionsTextLabel;
	JButton instructionsBackButton;
	
	//quit game
	JPanel quitPanel;
	JLabel quitHeaderLabel;
	JLabel quitTextLabel;
	JButton quitYesButton;
	JButton quitNoButton;
	
	
	
	//container to store all available menu panels
	//these are swapped in/out of frame window as needed
	ArrayList<JPanel> allMenuPanels;
	
	//store the colors for tiles/walls/players that are selected
	Color tileColorSelected;
	Color wallColorSelected;
	Color bkgColorSelected;
	Color[] playerColorsSelected;
	private ImageIcon image1;
	

	//constructor
	public MainWindow() {
		
		//build master frame to hold all panels, fixed size, no resizing.
		Dimension frameSize = new Dimension(1000, 1000);
        setSize(frameSize);
        setResizable(false);
        
        //build panels (like slides in a slideshow).  these will be swapped (visible/hidden) as user makes selections
        //new game, load game, instructions, quit
        
        BuildMainMenuPanel();        
        BuildNewGamePanel();
        BuildLoadGamePanel();      
        BuildInstructionsPanel();
        BuildQuitPanel();
        
        //define array to hold all menu panels
        //this list holds all of the panels that have been created
        //helps with switching between them as necessary (see "ShowPanel()")
        allMenuPanels = new ArrayList<JPanel>();        
        //add created panels to the list (NOT TO THE FRAME!  they are loaded into frame when needed)
        allMenuPanels.add(mainMenuPanel);
        allMenuPanels.add(newGamePanel);
        allMenuPanels.add(loadGamePanel);
        allMenuPanels.add(instructionsPanel);
        allMenuPanels.add(quitPanel);
        
        //add the mainMenuPanel to the frame, providing user with starting options 
        ShowPanel(mainMenuPanel);        
        
        //housekeeping for frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        setVisible(true);
        		
	}
	
	//use this method to load panels into the main window as necessary.
	//this will remove any other visible panels, and set the param as the visible one.
	public void ShowPanel(JPanel newPanel) {
		
		//hide all visible panels, and remove them from frame
		for(JPanel panel : allMenuPanels) {			
			//set all panels as not-visible
			panel.setVisible(false);
			//remove all panels the main menu frame
			this.remove(panel);						
		}
		
		//display the requested newPanel in frame
		newPanel.setVisible(true);
		this.add(newPanel);
	}
	

	
	private void BuildMainMenuPanel() {
		
		//header
		//four buttons (newGame, loadGame, instructions, quit)
		//footer
		
		//the MainMenu panel
		mainMenuPanel = new JPanel();
		//mainMenuPanel.setSize(1000, 1000);
		mainMenuPanel.setLayout(getLayout());
		mainMenuPanel.setBackground(new Color(39, 44, 54));
		
		//the labels for the header/footer
		image1= new ImageIcon(getClass().getResource("/Assets/final.png"));
		mainMenuHeaderLabel= new JLabel(image1);
		mainMenuHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		mainMenuHeaderLabel.setVerticalAlignment(JLabel.CENTER);		
		JLabel bottomPlaceholder = new JLabel();
		
		
		//the buttons		
		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(this);
		loadGameButton = new JButton("Load Game");
		loadGameButton.addActionListener(this);
		instructionsButton = new JButton("Instructions");
		instructionsButton.addActionListener(this);
		quitButton = new JButton("Quit");
		quitButton.addActionListener(this);
		
		//add buttons to sub panel (for organization)
		menuButtonsPanel = new JPanel();
		//menuButtonsPanel.setSize(200, 200);
		menuButtonsPanel.add(newGameButton);
		menuButtonsPanel.add(loadGameButton);
		menuButtonsPanel.add(instructionsButton);
		menuButtonsPanel.add(quitButton);		
		menuButtonsPanel.setVisible(true);	
		
		
		//add components to the panel
		mainMenuPanel.setLayout(new GridLayout(3,1));
		mainMenuPanel.add(mainMenuHeaderLabel);
		mainMenuPanel.add(menuButtonsPanel);
		mainMenuPanel.add(bottomPlaceholder);
		//show this panel 
		mainMenuPanel.setVisible(true);		
	}
	
	private void BuildNewGamePanel() {
		//choose colors
		//choose players
		
		//the new game panel (layout options set at end of this method)
		newGamePanel = new JPanel();
		newGamePanel.setBackground(new Color(250, 245, 245));
		
		//the labels for the header and choose colors
		newGameHeaderLabel = new JLabel("NEW GAME");
		EmptyBorder border1 = new EmptyBorder(20, 0, 20,0 );
		newGameHeaderLabel.setBorder(border1);
		newGameHeaderLabel.setAlignmentX(CENTER_ALIGNMENT);
		//newGameHeaderLabel.setPreferredSize(new Dimension(1000, 100));		
		//newGameHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		newGameHeaderLabel.setVerticalAlignment(JLabel.CENTER);
		newGameChooseColorsLabel = new JLabel("Please choose a board style");
		EmptyBorder border2 = new EmptyBorder(20, 0, 20, 0 );
		newGameChooseColorsLabel.setBorder(border2);
		newGameChooseColorsLabel.setAlignmentX(CENTER_ALIGNMENT);
		//newGameChooseColorsLabel.setPreferredSize(new Dimension(1000, 100));
		//newGameChooseColorsLabel.setHorizontalAlignment(JLabel.CENTER);
		//newGameChooseColorsLabel.setVerticalAlignment(JLabel.CENTER);
		
		//select color-buttons and listeners
		color1Button = new JButton("ColorSet #1");		
		color1Button.setPreferredSize(new Dimension(300, 200));
		color1Button.addActionListener(this);
		color2Button = new JButton("ColorSet #2");
		color2Button.setPreferredSize(new Dimension(300, 200));
		color2Button.addActionListener(this);
		color3Button = new JButton("ColorSet #3");
		color3Button.setPreferredSize(new Dimension(300, 200));
		color3Button.addActionListener(this);
		//put the color buttons in a sub-panel		
		colorButtons = new JPanel();
		FlowLayout colorButtonLayout = new FlowLayout();
		colorButtons.setLayout(colorButtonLayout);
		colorButtons.setOpaque(false);
		
		colorButtons.add(color1Button);		
		colorButtons.add(color2Button);		
		colorButtons.add(color3Button);
		colorButtons.setVisible(true);
		
		//configure players panel (hidden until player selects a color style)
		showPlayersPanel = new JPanel(new GridLayout(2,1)); //gridX4, play button, back button
		showPlayersPanel.setOpaque(false);
		
		configPlayersPanel = new JPanel();
		configPlayersPanel.setOpaque(false);
		GridLayout configLayout = new GridLayout(2,2);
		configLayout.setHgap(200);
		//configLayout.setVgap(20);
		configPlayersPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
		configPlayersPanel.setLayout(configLayout);
		//build one sub-panel for each player (four total)
		//icon, ask for name, name textbox, and player type combo box
		
		firstPanel = new JPanel();
		firstPanel.setOpaque(false);
		firstPanel.setLayout(new GridLayout(5,1));
		firstIconLabel = new JLabel("Color 1 Here");		
		firstIconLabel.setHorizontalAlignment(JLabel.CENTER);
		firstIconLabel.setVerticalAlignment(JLabel.CENTER);				
		firstTextField = new JTextField("<<Your Name Here>>", 1);		
		firstComboBox = new JComboBox(comboBoxOptions);
		firstComboBox.addActionListener(this);
		firstPanel.add(firstIconLabel);		
		firstPanel.add(firstTextField);
		firstPanel.add(firstComboBox);
		
		secondPanel = new JPanel();
		secondPanel.setOpaque(false);
		secondPanel.setLayout(new GridLayout(5,1));		
		secondIconLabel = new JLabel("Color 2 Here");
		secondIconLabel.setHorizontalAlignment(JLabel.CENTER);
		secondIconLabel.setVerticalAlignment(JLabel.CENTER);				
		secondTextField = new JTextField("<<Your Name Here>>", 1);
		//secondTextField.setColumns(12);
		secondComboBox = new JComboBox(comboBoxOptions);
		secondComboBox.addActionListener(this);
		secondPanel.add(secondIconLabel);		
		secondPanel.add(secondTextField);
		secondPanel.add(secondComboBox);
		
		thirdPanel = new JPanel();
		thirdPanel.setOpaque(false);
		thirdPanel.setLayout(new GridLayout(5,1));		
		thirdIconLabel = new JLabel("Color 3 Here");
		thirdIconLabel.setHorizontalAlignment(JLabel.CENTER);
		thirdIconLabel.setVerticalAlignment(JLabel.CENTER);			
		thirdTextField = new JTextField("<<Your Name Here>>", 1);
		//thirdTextField.setColumns(12);
		thirdComboBox = new JComboBox(comboBoxOptions);
		thirdComboBox.addActionListener(this);
		thirdPanel.add(thirdIconLabel);		
		thirdPanel.add(thirdTextField);
		thirdPanel.add(thirdComboBox);
		
		fourthPanel = new JPanel();
		fourthPanel.setOpaque(false);
		fourthPanel.setLayout(new GridLayout(5,1));		
		fourthIconLabel = new JLabel("Color 4 Here");
		fourthIconLabel.setHorizontalAlignment(JLabel.CENTER);
		fourthIconLabel.setVerticalAlignment(JLabel.CENTER);				
		fourthTextField = new JTextField("<<Your Name Here>>", 1);
		//fourthTextField.setColumns(12);
		fourthComboBox = new JComboBox(comboBoxOptions);
		fourthComboBox.addActionListener(this);
		fourthPanel.add(fourthIconLabel);		
		fourthPanel.add(fourthTextField);
		fourthPanel.add(fourthComboBox);		
		
		//add these four panels to the configPlayers panel
		configPlayersPanel.add(firstPanel);
		configPlayersPanel.add(secondPanel);
		configPlayersPanel.add(thirdPanel);
		configPlayersPanel.add(fourthPanel);
		
		//the "back" button and listener		
		newGameBackButton = new JButton("Back");
		newGameBackButton.setAlignmentX(CENTER_ALIGNMENT);		
		newGameBackButton.addActionListener(this);
		//the "Let's Play!" button and listener		
		newGamePlayButton = new JButton("Let's Play!");
		newGamePlayButton.setAlignmentX(CENTER_ALIGNMENT);		
		newGamePlayButton.addActionListener(this);
		//add the buttons to a panel
		playBackButtonsPanel = new JPanel();
		playBackButtonsPanel.setOpaque(false);
		BoxLayout buttonsLayout = new BoxLayout(playBackButtonsPanel, BoxLayout.Y_AXIS);
		playBackButtonsPanel.setLayout(buttonsLayout);
		playBackButtonsPanel.add(newGamePlayButton);
		playBackButtonsPanel.add(newGameBackButton);		
		
		//add configPlayers panel and buttons to showPlayersPanel
		showPlayersPanel.add(configPlayersPanel);
		showPlayersPanel.add(playBackButtonsPanel);				
		
		//hide configPlayersPanel for now.  Activated when needed.
		showPlayersPanel.setVisible(false);
		
		//add components to the panel
		BoxLayout newPanelLayout = new BoxLayout(newGamePanel, BoxLayout.Y_AXIS); 
		newGamePanel.setLayout(newPanelLayout);
		newGamePanel.add(newGameHeaderLabel);
		newGamePanel.add(newGameChooseColorsLabel);
		newGamePanel.add(colorButtons);		
		newGamePanel.add(showPlayersPanel);
		
		//hide this panel
		newGamePanel.setVisible(false);		
	}
	
	private void BuildLoadGamePanel() {
		//header label at the top
		//load game stuff in the center
		//button "Back" at the bottom
		
		//the load game panel
		loadGamePanel = new JPanel();
		loadGamePanel.setBackground(new Color(255, 255, 255));
		
		//the label for the header
		loadGameHeaderLabel = new JLabel("LOAD GAME");
		loadGameHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		loadGameHeaderLabel.setVerticalAlignment(JLabel.CENTER);
		
		//placeholder for load game UI (CHANGE THIS!)
		loadGameTextLabel = new JLabel("Put Load Game here....");
		loadGameTextLabel.setHorizontalAlignment(JLabel.CENTER);
		loadGameTextLabel.setVerticalAlignment(JLabel.CENTER);
		
		//the "back" button and listener
		loadGameBackButton = new JButton("Back");
		loadGameBackButton.addActionListener(this);
		
		//add components to the panel
		loadGamePanel.setLayout(new BorderLayout());
		loadGamePanel.add(loadGameHeaderLabel, BorderLayout.NORTH);
		loadGamePanel.add(loadGameTextLabel, BorderLayout.CENTER);		
		loadGamePanel.add(loadGameBackButton, BorderLayout.SOUTH);		
		//hide this panel
		loadGamePanel.setVisible(true);		
		
	}	
		
	
	private void BuildInstructionsPanel() {
		//header label at the top
		//instructions label in the center
		//button "Back" at the bottom
		
		//the instructions panel
		instructionsPanel = new JPanel();
		instructionsPanel.setBackground(new Color(9, 132, 227));
		instructionsPanel.setLayout(null);

        
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
        instructionsPanel.add(instructionsTextLabel);
		
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
	    
		instructionsPanel.add(instructionsBackButton);
        instructionsPanel.add(instructionsHeaderLabel);
        
        instructionsPanel.setVisible(false);	
	}
	

	private void BuildQuitPanel() {
		//header label at the top
		//Are you sure message
		//yes and no buttons at the bottom
		
		//the quit panel
		quitPanel = new JPanel();
		quitPanel.setBackground(new Color(255, 255, 255));
		
		//the label for the header
		quitHeaderLabel = new JLabel("QUIT GAME");
		quitHeaderLabel.setBounds(0, 0, 1000, 89);
		quitHeaderLabel.setForeground(new Color(51, 51, 51));
		quitHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		quitHeaderLabel.setVerticalAlignment(JLabel.CENTER);
		
		//"are you sure" message
		quitTextLabel = new JLabel("Are You Sure You Want to Quit?");
		quitTextLabel.setBounds(0, 130, 1000, 138);
		quitTextLabel.setHorizontalAlignment(JLabel.CENTER);
		quitTextLabel.setVerticalAlignment(JLabel.CENTER);
		
		//yes/no buttons and listeners
		quitYesButton = new JButton("Yes");
		quitYesButton.setBounds(150, 400, 700, 200);
		quitYesButton.addActionListener(this);
		quitNoButton = new JButton("No");
		quitNoButton.setBounds(150, 615, 700, 200);
		quitNoButton.addActionListener(this);
		quitPanel.setLayout(null);
		quitPanel.add(quitHeaderLabel);
		quitPanel.add(quitTextLabel);
		quitPanel.add(quitYesButton);
		quitPanel.add(quitNoButton);
		
		//hide this panel
		quitPanel.setVisible(false);
	}
	
	
	public void actionPerformed(ActionEvent event) {
		
		//get the object that performed the action, respond accordingly		
		Object selected = event.getSource();
		
		//MAIN MENU
		
		//was 'new game' requested on main menu panel?
		if(selected.equals(newGameButton))	{
			ShowPanel(newGamePanel);
		}		
		//was 'load game' requested on main menu panel?
		if(selected.equals(loadGameButton))	{
			ShowPanel(loadGamePanel);
		}
		//was "instructions" requested from main menu?
		if(selected.equals(instructionsButton)) {			
			ShowPanel(instructionsPanel);								
		}
		//was quit requested from the main menu?
		if(selected.equals(quitButton)) {
			ShowPanel(quitPanel);
		}
		
		//NEW GAME
		
		//did the user click on any of the color buttons in the new game panel?
		if(selected.equals(color1Button) || selected.equals(color2Button) || selected.equals(color3Button)) {
			
			//was it color option #1?
			if(selected.equals(color1Button)) {
				//get the first color palette from Game Settings
				tileColorSelected = GameSettings.getTileColor(0);  //<---zero indexed!
				wallColorSelected = GameSettings.getWallColor(0);
				bkgColorSelected = GameSettings.getBkgColor(0);
				playerColorsSelected = GameSettings.getPlayerColors(0);				
			}
			//was it color option #2?
			if(selected.equals(color2Button)) {
				//get the second color palette from Game Settings
				tileColorSelected = GameSettings.getTileColor(1);
				wallColorSelected = GameSettings.getWallColor(1);
				bkgColorSelected = GameSettings.getBkgColor(1);
				playerColorsSelected = GameSettings.getPlayerColors(1);				
			}
			//was it color option #3?
			if(selected.equals(color3Button)) {
				//get the third color palette from Game Settings
				tileColorSelected = GameSettings.getTileColor(2);
				wallColorSelected = GameSettings.getWallColor(2);
				bkgColorSelected = GameSettings.getBkgColor(2);
				playerColorsSelected = GameSettings.getPlayerColors(2);	
			}
			//after setting colors, display the "configure players" options
			DisplayPlayerColors();
			showPlayersPanel.setVisible(true);		
		}
				
		//did user click on a player-type combo box (dropdown menu)?
		if(selected.equals(firstComboBox)) {
			//if combo box was changed to "Computer", generate and display a random name
			String focus = firstComboBox.getSelectedItem().toString();
			if(focus.equals("Computer(Easy)") || 
					focus.equals("Computer(Hard)")) {
				firstTextField.setText(GameSettings.GetRandomName());
			}
			if(focus.equals("Human")) {
				firstTextField.setText("<<Your Name Here>>");
			}
		}
		
		if(selected.equals(secondComboBox)) {
			//if combo box was changed to "Computer", generate and display a random name
			String focus = secondComboBox.getSelectedItem().toString();
			if(focus.equals("Computer(Easy)") || 
					focus.equals("Computer(Hard)")) {
				secondTextField.setText(GameSettings.GetRandomName());
			}
			if(focus.equals("Human")) {
				secondTextField.setText("<<Your Name Here>>");
			}				
		}
		
		if(selected.equals(thirdComboBox)) {
			//if combo box was changed to "Computer", generate and display a random name
			String focus = thirdComboBox.getSelectedItem().toString();
			if(focus.equals("Computer(Easy)") || 
					focus.equals("Computer(Hard)")) {
				thirdTextField.setText(GameSettings.GetRandomName());
			}
			if(focus.equals("Human")) {
				thirdTextField.setText("<<Your Name Here>>");
			}				
		}
		
		if(selected.equals(fourthComboBox)) {
			//if combo box was changed to "Computer", generate and display a random name
			String focus = fourthComboBox.getSelectedItem().toString();
			if(focus.equals("Computer(Easy)") || 
					focus.equals("Computer(Hard)")) {
				fourthTextField.setText(GameSettings.GetRandomName());
			}
			if(focus.equals("Human")) {
				fourthTextField.setText("<<Your Name Here>>");
			}			
		}
		
		//was "back" requested on new game panel?
		if(selected.equals(newGameBackButton)) {
			//hide this for next time
			showPlayersPanel.setVisible(false);	
			ShowPanel(mainMenuPanel);
		}
		
		
		
		/*
		 * LETS PLAY!!
		 * LETS PLAY!!
		 * LETS PLAY!!
		 *  
		 */
		
		//was "Let's Play" requested on new game panel?
		if(selected.equals(newGamePlayButton)) {
			
			//maybe move this somewhere else?
			
			//compile list of names
			ArrayList<String> playerNames = new ArrayList<String>();
			playerNames.add(firstTextField.getText());
			playerNames.add(secondTextField.getText());
			playerNames.add(thirdTextField.getText());
			playerNames.add(fourthTextField.getText());
			//compile list of types
			ArrayList<String> playerTypes = new ArrayList<String>();
			playerTypes.add(firstComboBox.getSelectedItem().toString());
			playerTypes.add(secondComboBox.getSelectedItem().toString());
			playerTypes.add(thirdComboBox.getSelectedItem().toString());
			playerTypes.add(fourthComboBox.getSelectedItem().toString());
			//generate a random list to determine order of play
			List<Integer> turnOrder = GameSettings.getPlayerIDList();
			
			//build assets based on settings
			BuildAssets thisBuild = new BuildAssets(tileColorSelected,              //<----NEW GAME STARTS HERE
													wallColorSelected, 
													bkgColorSelected,
													playerNames, 
													playerTypes, 
													playerColorsSelected,
													turnOrder);
			
			/*
			//display to console (remove this)
			System.out.println("User is ready to play.");
			System.out.println("\nTile color is " +tileColorSelected.toString());
			System.out.println("\nWall color is " +wallColorSelected.toString());
			System.out.println("\nBackground color is " +bkgColorSelected.toString());
			System.out.println("\nPlayers are :\n");
			
			for(int i = 0; i < playerNames.size(); i++) {
				System.out.println("Name: "+ playerNames.get(i)+ 
								   "\nType: " +playerTypes.get(i)+
								   "\nColor: " +playerColorsSelected[i]+
								   "\nOrder: " +turnOrder.get(i)+ "\n");
			}
			*/			
			
		}
		
		//LOAD GAME
		
		//was 'back' requested on load game panel?
		if(selected.equals(loadGameBackButton)) {
			ShowPanel(mainMenuPanel);
		}
		
		
		//INSTRUCTIONS
		
		//was 'back' requested on instructions panel?
		if(selected.equals(instructionsBackButton)) {
			ShowPanel(mainMenuPanel);
		}
		
		//QUIT
		
		//was 'Yes' requested on quit panel?
		if(selected.equals(quitYesButton)) {
			System.exit(0);
		}
		//was 'No' requested on quit panel?
		if(selected.equals(quitNoButton)) {
			ShowPanel(mainMenuPanel);
		}
	}
	
	private void DisplayPlayerColors() {
		firstIconLabel.setBackground(playerColorsSelected[0]);
		firstIconLabel.setOpaque(true);
		secondIconLabel.setBackground(playerColorsSelected[1]);
		secondIconLabel.setOpaque(true);
		thirdIconLabel.setBackground(playerColorsSelected[2]);
		thirdIconLabel.setOpaque(true);
		fourthIconLabel.setBackground(playerColorsSelected[3]);
		fourthIconLabel.setOpaque(true);
	}
	
}
