import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
	
	//container to store all available menu panels
	//these are swapped in/out of frame window as needed
	private ArrayList<JPanel> allMenuPanels;
	
	private JLayeredPane mainWindow;
	//used to overlay in-game messages (player's turn, illegal move, etc)
	private InGameMessagePanel messagePanel;
	
	private Dimension frameSize;
	
	//constructor
	public MainWindow() {
		
		//get a frame
		super();
		
		//build master frame to hold all panels, fixed size, no resizing.
		frameSize = new Dimension(1000, 1000);
        this.setSize(frameSize);
        this.setResizable(false);
        this.setLayout(null);   // ?need this?
        
        mainWindow = new JLayeredPane();
        mainWindow.setBounds(0,0,1000,1000);
        mainWindow.setVisible(true);
        this.add(mainWindow);
        
        //add the background image to this frame (wrapped in a JLabel).
        //panels displayed on top (ie: instructions panel) will be transparent except for text/buttons
  		ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/Assets/menuBkg.png"));
  		JLabel frameBackground = new JLabel(backgroundImage);  		
  		frameBackground.setBounds(0,0,1000,1000);
  		//background image is on DEFAULT (bottom) layer.
        mainWindow.add(frameBackground, JLayeredPane.DEFAULT_LAYER);
        
        
        //store a static ref to this window in GameSettings.  
        //can be accessed anywhere by GameSettings.GetMainWindow()
        GameSettings.SetMainWindow(this);
        
        //these are the main menu and associated panels (displayed on PALETTE layer, on top of DEFAULT)
        MainMenu mainMenu = new MainMenu();
        NewGameMenu newGameMenu = new NewGameMenu();
        LoadGameMenu loadGameMenu = new LoadGameMenu();
        InstructionsMenu instructionsMenu = new InstructionsMenu();
        QuitMenu quitMenu = new QuitMenu();
        //this panel is reserved for inGame Messages (displayed on MODAL layer, on top of PALETTE)
        //it has it's own methods for setting text messages. accessible through GameSettings.
        //NOT added to menuPanel array list below (it's not a menu).
        messagePanel = new InGameMessagePanel();
        
        //define array to hold all menu panels
        //this list holds all of the panels that have been created
        //helps with switching between them as necessary (see "ShowPanel()")
        allMenuPanels = new ArrayList<JPanel>();        
        //add created panels to the list (NOT TO THE FRAME!  they are loaded into frame when needed)
        allMenuPanels.add(mainMenu);
        allMenuPanels.add(newGameMenu);
        allMenuPanels.add(loadGameMenu);
        allMenuPanels.add(instructionsMenu);
        allMenuPanels.add(quitMenu);
        
        //build panels (like slides in a slideshow).  these will be swapped (visible/hidden) as user makes selections
        //new game, load game, instructions, quit
        GameSettings.SetMainMenu(mainMenu);
        GameSettings.SetNewGameMenu(newGameMenu);
        GameSettings.SetLoadGameMenu(loadGameMenu);
        GameSettings.SetInstructionsMenu(instructionsMenu);
        GameSettings.SetQuitMenu(quitMenu);        
        //add the message panel
        GameSettings.SetMessagePanel(messagePanel);
        
        //add the mainMenuPanel to the frame, providing user with starting options 
        ShowPanel(GameSettings.GetMainMenu());        
        
        //housekeeping for frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        this.setVisible(true);        		
	}
	
	//use this method to load panels into the main window as necessary.
	//this will remove any other visible panels, and set the param as the visible one.
	public void ShowPanel(JPanel newPanel) {
		
		//hide all visible panels, and remove them from frame
		for(JPanel panel : allMenuPanels) {			
			//set all panels as not-visible
			panel.setVisible(false);
			//remove all panels the main menu frame
			mainWindow.remove(panel);						
		}
		
		//display the requested newPanel in frame
		newPanel.setVisible(true);
		mainWindow.add(newPanel, JLayeredPane.PALETTE_LAYER);		
	}
	
	//used to display in-game messages to the players.
	//ie: "Player 2 has next turn".  or "Illegal Move".
	public void ShowMessage(String message) {
		
		//get the message panel
		//update the message text
		//display the panel
		InGameMessagePanel messagePanel = GameSettings.GetMessagePanel();
		
		messagePanel.SetMessageText(message);
		messagePanel.setVisible(true);
		mainWindow.add(messagePanel, JLayeredPane.MODAL_LAYER);				
		
	}
	
	public void RemoveMessage() {
		
		InGameMessagePanel messagePanel = GameSettings.GetMessagePanel();
		mainWindow.remove(messagePanel);
		//refresh display for whatever was underneath this message
		this.repaint();
	}
		
}
