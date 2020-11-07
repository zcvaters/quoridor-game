import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * Main Menu class.  Builds a main menu panel.  Returns the panel to the MainWindow.
 *
 */
public class MainMenu extends JPanel implements ActionListener{
	
	//main menu
	JPanel mainMenuPanel;
	JLabel mainMenuHeaderLabel;
	JButton newGameButton;
	JButton loadGameButton;
	JButton instructionsButton;
	JButton quitButton;
	JPanel menuButtonsPanel;  //subpanel in this main menu panel, to organize buttons
	
	public MainMenu(){
		
		//create a JPanel
		super();
		
		System.out.println("Main menu is awake");
		
		//header
		//four buttons (newGame, loadGame, instructions, quit)
		//footer
		
		//the MainMenu panel
		//JPanel mainMenuPanel = new JPanel();
		//mainMenuPanel.setSize(1000, 1000);		
		//mainMenuPanel.setBackground(new Color(39, 44, 54));
		//get background image.  1000X1000 for current frams size
              
		
		//the labels for the header/footer
		//ImageIcon image1;
		//image1= new ImageIcon(getClass().getResource("/Assets/menuBkg.png"));
		//mainMenuHeaderLabel= new JLabel(image1);
		//mainMenuHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		//mainMenuHeaderLabel.setVerticalAlignment(JLabel.CENTER);		
		//JLabel bottomPlaceholder = new JLabel();
		
		
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
		menuButtonsPanel.setOpaque(false);
		//menuButtonsPanel.setSize(200, 200);
		menuButtonsPanel.add(newGameButton);
		menuButtonsPanel.add(loadGameButton);
		menuButtonsPanel.add(instructionsButton);
		menuButtonsPanel.add(quitButton);		
		menuButtonsPanel.setVisible(true);	
		
		
		//add components to the panel
		this.setLayout(new GridLayout(1,1));
		//this.add(mainMenuHeaderLabel);
		this.add(menuButtonsPanel);
		//mainMenuPanel.add(bottomPlaceholder);
		//show this panel 
		this.setVisible(true);	
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		//get the object that performed the action, respond accordingly		
		Object selected = event.getSource();
		
		//get ref to the mainWindow
		MainWindow mainWindow = GameSettings.GetMainWindow();
		
		//was 'new game' requested on main menu panel?
		if(selected.equals(newGameButton))	{
			JPanel newGamePanel = (JPanel)GameSettings.GetNewGameMenu();
			mainWindow.ShowPanel(newGamePanel);
		}		
		//was 'load game' requested on main menu panel?
		if(selected.equals(loadGameButton))	{
			JPanel loadGamePanel = (JPanel)GameSettings.GetLoadGameMenu();
			mainWindow.ShowPanel(loadGamePanel);
		}
		//was "instructions" requested from main menu?
		if(selected.equals(instructionsButton)) {
			JPanel instructionsPanel = (JPanel)GameSettings.GetInstructionsMenu();
			mainWindow.ShowPanel(instructionsPanel);								
		}
		//was quit requested from the main menu?
		if(selected.equals(quitButton)) {
			JPanel quitPanel = (JPanel)GameSettings.GetQuitMenu();
			mainWindow.ShowPanel(quitPanel);
		}
		
	}

}
