import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
	
	//container to store all available menu panels
	//these are swapped in/out of frame window as needed
	ArrayList<JPanel> allMenuPanels;

	//constructor
	public MainWindow() {
		
		//build master frame to hold all panels, fixed size, no resizing.
		Dimension frameSize = new Dimension(1000, 1000);
        setSize(frameSize);
        setResizable(false);
        
        //store a static ref to this window in GameSettings.  
        //can be accessed anywhere by GameSettings.GetMainWindow()
        GameSettings.SetMainWindow(this);
        
        MainMenu mainMenu = new MainMenu();
        NewGameMenu newGameMenu = new NewGameMenu();
        LoadGameMenu loadGameMenu = new LoadGameMenu();
        InstructionsMenu instructionsMenu = new InstructionsMenu();
        QuitMenu quitMenu = new QuitMenu();
        
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
        
        //add the mainMenuPanel to the frame, providing user with starting options 
        ShowPanel(GameSettings.GetMainMenu());        
        
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
	
}
