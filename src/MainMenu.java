import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * 
 * Main Menu class. Builds a main menu panel. Returns the panel to the
 * MainWindow.
 *
 */
public class MainMenu extends JPanel implements ActionListener {

	// main menu
	JPanel mainMenuPanel;
	JLabel mainMenuHeaderLabel;
	JButton newGameButton;
	JButton loadGameButton;
	JButton instructionsButton;
	JButton quitButton;
	JPanel menuButtonsPanel; // subpanel in this main menu panel, to organize buttons
	private List<JButton> mainMenuButtons;

	public MainMenu() {

		// create a JPanel
		super();
		Dimension panelSize = new Dimension(1200, 800);
		this.setPreferredSize(panelSize);

		JLabel gameLabel = new JLabel("Quoridor", SwingConstants.CENTER);
		gameLabel.setBounds(0, 0, 1280, 400);
		gameLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		gameLabel.setFont(MainWindow.orbitron.deriveFont(144f));
		gameLabel.setForeground(Color.black);

		// four buttons (newGame, loadGame, instructions, quit)
		mainMenuButtons = new ArrayList<>();
		// the buttons
		newGameButton = new Buttons("New Game");
		newGameButton.setSize(50, 50);
		loadGameButton = new Buttons("Load Game");

		instructionsButton = new Buttons("Instructions");

		quitButton = new Buttons("Quit");

		mainMenuButtons.add(newGameButton);
		mainMenuButtons.add(loadGameButton);
		mainMenuButtons.add(instructionsButton);
		mainMenuButtons.add(quitButton);

		// add buttons to sub panel (for organization)
		menuButtonsPanel = new JPanel();
		menuButtonsPanel.setBounds(0, 0, 400, 400);
		EmptyBorder border1 = new EmptyBorder(100, 450, 300, 450);
		menuButtonsPanel.setBorder(border1);
		menuButtonsPanel.setLayout(new GridLayout(4, 1));
		menuButtonsPanel.setOpaque(false);
		menuButtonsPanel.add(newGameButton);
		menuButtonsPanel.add(loadGameButton);
		menuButtonsPanel.add(instructionsButton);
		menuButtonsPanel.add(quitButton);
		menuButtonsPanel.setVisible(true);

		// Set button properties
		defineButtonProperties(mainMenuButtons);

		// add components to the panel
		this.setBounds(0, 0, 1200, 800);
		this.setLayout(new BorderLayout());
		this.add(gameLabel, BorderLayout.NORTH);
		this.add(menuButtonsPanel, BorderLayout.CENTER);

		this.setOpaque(false);
		this.setVisible(true);

	}

	/*
	 * Defines the button properties for a collection of buttons
	 * 
	 * @param: List<Jbutton> someButtons
	 */
	public void defineButtonProperties(List<JButton> someButtons) {
		someButtons.forEach(button -> {
			button.setFocusPainted(false);
			button.setForeground(Color.black);
			button.setOpaque(false);
			button.setBorderPainted(false);
			button.setContentAreaFilled(false);
			button.setFont(MainWindow.orbitron);
			button.addActionListener(this);
			button.setHorizontalAlignment(SwingConstants.CENTER);
			button.setAlignmentY(CENTER_ALIGNMENT);
			//button.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		});
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		// get the object that performed the action, respond accordingly
		Object selected = event.getSource();

		// get ref to the mainWindow
		MainWindow mainWindow = GameSettings.GetMainWindow();
		// was 'new game' requested on main menu panel?
		if (selected.equals(newGameButton)) {
			JPanel newGamePanel = (JPanel) GameSettings.GetNewGameMenu();
			mainWindow.ShowPanel(newGamePanel);
		}
		// was 'load game' requested on main menu panel?
		if (selected.equals(loadGameButton)) {
			//update the load game buttons to display file timestamps
			GameSettings.GetLoadGameMenu().ConfigureSaveButtonLabels();
			JPanel loadGamePanel = (JPanel) GameSettings.GetLoadGameMenu();
			mainWindow.ShowPanel(loadGamePanel);
		}
		// was "instructions" requested from main menu?
		if (selected.equals(instructionsButton)) {
			JPanel instructionsPanel = (JPanel) GameSettings.GetInstructionsMenu();
			mainWindow.ShowPanel(instructionsPanel);
		}
		// was quit requested from the main menu?
		if (selected.equals(quitButton)) {
			JPanel quitPanel = (JPanel) GameSettings.GetQuitMenu();
			mainWindow.ShowPanel(quitPanel);
		}
		GameSettings.playButtonSound();
		mainMenuButtons.forEach(e -> e.setForeground(Color.black)); // Fixes persistent hover color on back bttn.

	}

}
