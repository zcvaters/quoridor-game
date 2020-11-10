import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

	public MainMenu() {

		// create a JPanel
		super();
		Dimension panelSize = new Dimension(1000, 1000);
		this.setPreferredSize(panelSize);
		
		JLabel gameLabel = new JLabel("Quoridor", SwingConstants.CENTER);
		gameLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		gameLabel.setFont(MainWindow.orbitron.deriveFont(144f));
		gameLabel.setForeground(Color.black);
		
		// four buttons (newGame, loadGame, instructions, quit)

		// the buttons
		newGameButton = new JButton("New Game");
		newGameButton.setFocusPainted(false);
		newGameButton.setOpaque(false);
		newGameButton.setBorderPainted(false);
		newGameButton.setContentAreaFilled(false);
		newGameButton.setFont(MainWindow.orbitron);
		defineButtonProperties(newGameButton);
		newGameButton.addActionListener(this);
	
		loadGameButton = new JButton("Load Game");
		loadGameButton.setOpaque(false);
		loadGameButton.setFont(MainWindow.orbitron);
		defineButtonProperties(loadGameButton);
		loadGameButton.addActionListener(this);
		
		instructionsButton = new JButton("Instructions");
		instructionsButton.setFont(MainWindow.orbitron);
		defineButtonProperties(instructionsButton);
		instructionsButton.addActionListener(this);
		quitButton = new JButton("Quit");
		quitButton.setFont(MainWindow.orbitron);
		defineButtonProperties(quitButton);
		quitButton.addActionListener(this);

		// add buttons to sub panel (for organization)
		menuButtonsPanel = new JPanel();
		menuButtonsPanel.setBounds(0, 0, 1000, 1000);
		EmptyBorder border1 = new EmptyBorder(200, 0, 400, 60);
		menuButtonsPanel.setBorder(border1);
		menuButtonsPanel.setLayout(new GridLayout(4, 1));
		menuButtonsPanel.setOpaque(false);
		menuButtonsPanel.add(newGameButton);
		menuButtonsPanel.add(loadGameButton );
		menuButtonsPanel.add(instructionsButton );
		menuButtonsPanel.add(quitButton);
		menuButtonsPanel.setVisible(true);

		// add components to the panel
		this.setBounds(0, 0, 1000, 1000);
		this.setLayout(new BorderLayout());
		// this.add(mainMenuHeaderLabel);
		this.add(gameLabel, BorderLayout.NORTH);
		this.add(menuButtonsPanel, BorderLayout.EAST);
		// mainMenuPanel.add(bottomPlaceholder);
		// show this panel

		// this.setBackground(new Color(110, 245, 245));

		this.setOpaque(false);
		this.setVisible(true);

	}
	
	public void defineButtonProperties(JButton button) {
		button.setFocusPainted(false);
		button.setForeground(Color.black);
		button.setOpaque(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFont(MainWindow.orbitron);
		button.addMouseListener(new MouseAdapter() {
			Color color  = newGameButton.getForeground();
			public void mouseEntered(MouseEvent e) {
				button.setForeground(new Color(140, 15, 15));
			}
			public void mouseExited(MouseEvent e) {
				button.setForeground(Color.black);
			}
		});
		button.setHorizontalAlignment(SwingConstants.RIGHT);
		button.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
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

	}

}
