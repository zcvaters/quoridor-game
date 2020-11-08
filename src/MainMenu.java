import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
		Dimension panelSize = new Dimension(1000, 1000);
        this.setPreferredSize(panelSize);
						
		//four buttons (newGame, loadGame, instructions, quit)
		
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
		menuButtonsPanel.setBounds(0,0,1000,1000);
		EmptyBorder border1 = new EmptyBorder(300, 0, 400,60 );
		menuButtonsPanel.setBorder(border1);
		menuButtonsPanel.setLayout(new GridLayout(4,1));
		menuButtonsPanel.setOpaque(false);		
		menuButtonsPanel.add(newGameButton);
		menuButtonsPanel.add(loadGameButton);
		menuButtonsPanel.add(instructionsButton);
		menuButtonsPanel.add(quitButton);		
		menuButtonsPanel.setVisible(true);	
		
		
		//add components to the panel
		this.setBounds(0,0,1000,1000);
		this.setLayout(new BorderLayout());
		//this.add(mainMenuHeaderLabel);
		this.add(menuButtonsPanel, BorderLayout.EAST);
		//mainMenuPanel.add(bottomPlaceholder);
		//show this panel
		
		
		//this.setBackground(new Color(110, 245, 245));
		
		this.setOpaque(false);
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
