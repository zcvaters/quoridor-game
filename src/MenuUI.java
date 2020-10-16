import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuUI extends JFrame implements ActionListener {
	
	
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
	JLabel newGameChoosePlayersLabel;
	JButton onePlayerButton;
	JButton twoPlayerButton;
	JButton threePlayerButton;
	JButton fourPlayerButton;
	JButton newGameBackButton;
	
	//load game
	JPanel loadGamePanel;
	JLabel loadGameHeaderLabel;
	JLabel loadGameTextLabel;
	JButton loadGameBackButton;	
	
	//instructions
	JPanel instructionsPanel;
	JLabel instructionsHeaderLabel;
	JLabel instructionsTextLabel;
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

	//constructor
	public MenuUI() {
		
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
	
	//could possibly make this public if we wanted call this from somewhere else (ex: to pause the game while playing)
	private void ShowPanel(JPanel newPanel) {
		
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
		mainMenuPanel.setBackground(new Color(255, 255, 255));
		
		//the labels for the header/footer
		mainMenuHeaderLabel = new JLabel("MAIN MENU");		
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
		
		//the new game panel
		newGamePanel = new JPanel();
		newGamePanel.setBackground(new Color(255, 255, 255));
		
		//the labels for the header and choose colors
		newGameHeaderLabel = new JLabel("NEW GAME");
		newGameHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		newGameHeaderLabel.setVerticalAlignment(JLabel.CENTER);
		newGameChooseColorsLabel = new JLabel("Please select a color option");
		newGameChooseColorsLabel.setHorizontalAlignment(JLabel.CENTER);
		newGameChooseColorsLabel.setVerticalAlignment(JLabel.CENTER);
		
		//select color-buttons and listeners
		color1Button = new JButton("ColorSet #1");
		color1Button.addActionListener(this);
		color2Button = new JButton("ColorSet #2");
		color2Button.addActionListener(this);
		color3Button = new JButton("ColorSet #3");
		color3Button.addActionListener(this);
		//put the color buttons in a sub-panel
		JPanel colorButtons = new JPanel();
		colorButtons.add(color1Button);
		colorButtons.add(color2Button);
		colorButtons.add(color3Button);
		colorButtons.setVisible(true);
		
		//labels and buttons/listeners for choose human players
		newGameChoosePlayersLabel = new JLabel("Please select the number of players");
		newGameChoosePlayersLabel.setHorizontalAlignment(JLabel.CENTER);
		newGameChoosePlayersLabel.setVerticalAlignment(JLabel.CENTER);
		onePlayerButton = new JButton("One Player");
		onePlayerButton.addActionListener(this);
		twoPlayerButton = new JButton("Two Players");
		twoPlayerButton.addActionListener(this);
		threePlayerButton = new JButton("Three Players");
		threePlayerButton.addActionListener(this);
		fourPlayerButton = new JButton("Four Players");
		fourPlayerButton.addActionListener(this);
		//put the player buttons in a sub-panel
		JPanel playerButtons = new JPanel();
		playerButtons.add(onePlayerButton);
		playerButtons.add(twoPlayerButton);
		playerButtons.add(threePlayerButton);
		playerButtons.add(fourPlayerButton);
		playerButtons.setVisible(true);
				
		//the "back" button and listener
		newGameBackButton = new JButton("Back");
		newGameBackButton.addActionListener(this);
		
		//add components to the panel
		newGamePanel.setLayout(new GridLayout(6, 1));
		newGamePanel.add(newGameHeaderLabel);
		newGamePanel.add(newGameChooseColorsLabel);
		newGamePanel.add(colorButtons);
		newGamePanel.add(newGameChoosePlayersLabel);
		newGamePanel.add(playerButtons);
		newGamePanel.add(newGameBackButton);
		
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
		instructionsPanel.setBackground(new Color(255, 255, 255));
		
		//the label for the header
		instructionsHeaderLabel = new JLabel("INSTRUCTIONS");
		instructionsHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		instructionsHeaderLabel.setVerticalAlignment(JLabel.CENTER);
		
		//the main text for instructions
		instructionsTextLabel = new JLabel("This is the text for the instructions....");
		instructionsTextLabel.setHorizontalAlignment(JLabel.CENTER);
		instructionsTextLabel.setVerticalAlignment(JLabel.CENTER);
		
		//the "back" button and listener
		instructionsBackButton = new JButton("Back");
		instructionsBackButton.addActionListener(this);
		
		//add components to the panel
		instructionsPanel.setLayout(new BorderLayout());
		instructionsPanel.add(instructionsHeaderLabel, BorderLayout.NORTH);
		instructionsPanel.add(instructionsTextLabel, BorderLayout.CENTER);
		instructionsPanel.add(instructionsBackButton, BorderLayout.SOUTH);		
		//hide this panel
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
		quitHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		quitHeaderLabel.setVerticalAlignment(JLabel.CENTER);
		
		//"are you sure" message
		quitTextLabel = new JLabel("Are You Sure You Want to Quit?");
		quitTextLabel.setHorizontalAlignment(JLabel.CENTER);
		quitTextLabel.setVerticalAlignment(JLabel.CENTER);
		
		//yes/no buttons and listeners
		quitYesButton = new JButton("Yes");
		quitYesButton.addActionListener(this);
		quitNoButton = new JButton("No");
		quitNoButton.addActionListener(this);
		
		//add components to the panel
		quitPanel.setLayout(new GridLayout(4, 1));
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
		
		//did user select a color option on new game panel?
		if(selected.equals(color1Button)) {
			//PUT CODE HERE FOR SETTING COLOR OPTIONS
			System.out.println("User selected option #1 for colors");
		}
		if(selected.equals(color2Button)) {
			//PUT CODE HERE FOR SETTING COLOR OPTIONS
			System.out.println("User selected option #2 for colors");
		}
		if(selected.equals(color3Button)) {
			//PUT CODE HERE FOR SETTING COLOR OPTIONS
			System.out.println("User selected option #3 for colors");
		}
		
		//did user select the number of human players?
		if(selected.equals(onePlayerButton)) {
			//PUT CODE HERE FOR SETTING ONE PLAYER OPTIONS
			System.out.println("User selected one human player");
		}
		if(selected.equals(twoPlayerButton)) {
			//PUT CODE HERE FOR SETTING TWO PLAYER OPTIONS
			System.out.println("User selected two human players");
		}
		if(selected.equals(threePlayerButton)) {
			//PUT CODE HERE FOR SETTING THREE PLAYER OPTIONS
			System.out.println("User selected three human players");
		}
		if(selected.equals(fourPlayerButton)) {
			//PUT CODE HERE FOR SETTING FOUR PLAYER OPTIONS
			System.out.println("User selected four human players");
		}
		
		//was "back" requested on new game panel?
		if(selected.equals(newGameBackButton)) {
			ShowPanel(mainMenuPanel);
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
	
	
}
