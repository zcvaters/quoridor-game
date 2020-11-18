import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/* InGameUIPanel class. This class contains all the UI elements for the game in a overlay.
 * 
 * Responsible for showing UI elements for the user.
 */
public class InGameUIPanel implements ActionListener {

	// IngameUIPanel settings button
	private JButton settingsButton;

	private JLayeredPane inGameOverlay;

	private JPanel mainPanel;

	// constraints for this layout
	GridBagConstraints gbc = new GridBagConstraints();

	private JPanel uiPanel;
	private JPanel settingsPanel;
	private JButton resumeGameButton;
	private JButton saveGameButton;
	private JButton backToMenuButton;
	private JButton quitGameButton;
	private JPanel saveGamePanel;
	private JButton saveGame1;
	private JButton saveGame2;
	private JButton saveGame3;
	private JLabel savedLabel;
	private JButton backToSettingsButton;
	private JPanel southPlayerSide;
	private JPanel westPlayerSide;
	private JPanel northPlayerSide;
	private JPanel eastPlayerSide;
	private JPanel playerPanels;
	private JPanel playerInfo;
	private JLabel southPlayerDetails;
	private JLabel westPlayerDetails;
	private JLabel northPlayerDetails;
	private JLabel eastPlayerDetails;

	private JPanel southPlayerPanel;

	private JPanel playerInfoPanel;

	private JPanel southPlayerInfoPanel;

	private JPanel westPlayerInfoPanel;

	private JPanel northPlayerInfoPanel;

	private JPanel eastPlayerInfoPanel;

	// constructor
	public InGameUIPanel() {

		// Create layered Pane
		inGameOverlay = new JLayeredPane();
		inGameOverlay.setBounds(0, 0, 1280, 800);
		inGameOverlay.setLayout(null);
		inGameOverlay.setVisible(true);

		// Create Main Panel
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 1280, 800);
		mainPanel.setOpaque(false);
		mainPanel.setVisible(true);
		mainPanel.add(inGameOverlay);

		// UI Panel to be exist on mainPanel
		uiPanel = new JPanel();
		uiPanel.setBounds(0, 0, 1280, 800);
		uiPanel.setLayout(null);
		uiPanel.setOpaque(false);
		uiPanel.setVisible(true);
		settingsButton = new JButton();
		settingsButton.setOpaque(false);
		settingsButton.setContentAreaFilled(false);
		settingsButton.setBorderPainted(false);
		settingsButton.setBounds(1125, 5, 50, 50);
		settingsButton.setIcon(new ImageIcon(getClass().getResource("/Assets/settings_button.png")));
		settingsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				settingsButton.setIcon(new ImageIcon(getClass().getResource("/Assets/settings_button_selected.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				settingsButton.setIcon(new ImageIcon(getClass().getResource("/Assets/settings_button.png")));
			}
		});
		settingsButton.addActionListener(this);
		uiPanel.add(settingsButton, JLayeredPane.PALETTE_LAYER);
		mainPanel.add(uiPanel, JLayeredPane.PALETTE_LAYER);

		// Settings Panel for in Game Settings
		settingsPanel = new JPanel();
		settingsPanel.setLayout(new GridBagLayout());
		settingsPanel.setBounds(0, 0, 1280, 800);
		settingsPanel.setOpaque(false);

		settingsPanel.setVisible(false);
		initializeSettingsOverlay();
		setSaveGamePanel();
		//playerSidePanels();
		playerStatsPanel();
	}

	public void initializeSettingsOverlay() {
		resumeGameButton = new JButton("Resume");
		resumeGameButton.setFont(MainWindow.orbitron.deriveFont(15f));
		resumeGameButton.setForeground(Color.black);
		resumeGameButton.setVisible(true);
		resumeGameButton.addActionListener(this);
		resumeGameButton.setBounds(0, 0, 60, 60);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		settingsPanel.add(resumeGameButton, gbc);

		saveGameButton = new JButton("Save Game");
		saveGameButton.setFont(MainWindow.orbitron.deriveFont(15f));
		saveGameButton.setForeground(Color.black);
		saveGameButton.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		settingsPanel.add(saveGameButton, gbc);

		backToMenuButton = new JButton("Back To Main Menu");
		backToMenuButton.setFont(MainWindow.orbitron.deriveFont(15f));
		backToMenuButton.setForeground(Color.black);
		backToMenuButton.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.BOTH;
		settingsPanel.add(backToMenuButton, gbc);

		quitGameButton = new JButton("Quit Game");
		quitGameButton.setFont(MainWindow.orbitron.deriveFont(15f));
		quitGameButton.setForeground(Color.black);
		quitGameButton.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.BOTH;
		settingsPanel.add(quitGameButton, gbc);
		inGameOverlay.add(settingsPanel, JLayeredPane.PALETTE_LAYER);

	}
	
	/*
	 * Sets Name tags for each side of the border for each player.
	 */
	public void setPlayerDetails(String player1, String player2, String player3, String player4) {
		this.getSouthPlayerDetails().setText(player1);
		this.getWestPlayerDetails().setText(player2);
		this.getNorthPlayerDetails().setText(player3);
		this.getEastPlayerDetails().setText(player4);
	}
	
	public void setSavePanelBG(Color bkgColor) {
		this.getSaveGamePanel().setBackground(bkgColor);
	}

	public void setSaveGamePanel() {
		saveGamePanel = new JPanel(new GridBagLayout());
		saveGamePanel.setBounds(380, 420, 250, 125);
		saveGamePanel.setOpaque(false);

		JLabel instructSaveLabel = new JLabel("To save a Game. Choose a save Slot", SwingConstants.CENTER);
		instructSaveLabel.setFont(MainWindow.orbitron.deriveFont(11f));
		instructSaveLabel.setOpaque(true);
		instructSaveLabel.setForeground(Color.BLACK);
		instructSaveLabel.setBackground(Color.white);
		gbc.gridx = 2;
		gbc.gridy = 0;
		saveGamePanel.add(instructSaveLabel, gbc);

		saveGame1 = new JButton("Save 1");
		saveGame1.setFont(MainWindow.orbitron.deriveFont(15f));
		saveGame1.setForeground(Color.black);
		saveGame1.setBounds(0, 0, 60, 60);
		gbc.gridx = 2;
		gbc.gridy = 1;
		saveGame1.addActionListener(this);
		saveGamePanel.add(saveGame1, gbc);

		saveGame2 = new JButton("Save 2");
		saveGame2.setFont(MainWindow.orbitron.deriveFont(15f));
		saveGame2.setForeground(Color.black);
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		saveGame2.addActionListener(this);
		saveGamePanel.add(saveGame2, gbc);

		saveGame3 = new JButton("Save 3");
		saveGame3.setFont(MainWindow.orbitron.deriveFont(15f));
		saveGame3.setForeground(Color.black);
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.CENTER;
		saveGame3.addActionListener(this);
		saveGamePanel.add(saveGame3, gbc);

		savedLabel = new JLabel();
		gbc.gridx = 2;
		gbc.gridy = 4;
		savedLabel.setVisible(true);
		saveGamePanel.add(savedLabel, gbc);

		backToSettingsButton = new JButton("Back");
		backToSettingsButton.setFont(MainWindow.orbitron.deriveFont(15f));
		backToSettingsButton.setForeground(Color.black);
		gbc.gridx = 2;
		gbc.gridy = 5;
		backToSettingsButton.addActionListener(this);
		saveGamePanel.add(backToSettingsButton, gbc);

		inGameOverlay.add(saveGamePanel, JLayeredPane.POPUP_LAYER);
		saveGamePanel.setVisible(false);
	}

	public JPanel getSaveGamePanel() {
		return saveGamePanel;
	}

	public void setSaveGamePanel(JPanel saveGamePanel) {
		this.saveGamePanel = saveGamePanel;
	}

	/*
	public void playerInfo() {
		playerInfo = new JPanel(new BorderLayout());
		playerInfo.setBounds(800, 100, 960, 900);
		playerInfo.setOpaque(false);
		playerInfo.setVisible(true);
		
		southPlayerPanel = new JPanel();
		playerPanels.setBounds(900, 100, 200, 800);
		southPlayerPanel.setBackground(Color.red);
		southPlayerDetails = new JLabel("", SwingConstants.CENTER);
		playerInfo.add(southPlayerDetails, BorderLayout.SOUTH);

		westPlayerDetails = new JLabel("", SwingConstants.CENTER);
		playerInfo.add(westPlayerDetails, BorderLayout.WEST);

		northPlayerDetails = new JLabel("", SwingConstants.CENTER);
		playerInfo.add(northPlayerDetails, BorderLayout.NORTH);

		eastPlayerDetails = new JLabel("", SwingConstants.CENTER);
		playerInfo.add(eastPlayerDetails, BorderLayout.EAST);
		
		inGameOverlay.add(playerInfo, JLayeredPane.PALETTE_LAYER);
		//inGameOverlay.add(playerInfo, JLayeredPane.PALETTE_LAYER);
	}
	*/
	public void playerStatsPanel() {
		playerInfoPanel = new JPanel();
		playerInfoPanel.setLayout(new GridLayout(4, 1));
		playerInfoPanel.setBounds(725, 25, 400, 600);
		playerInfoPanel.setOpaque(false);
		
		southPlayerInfoPanel = new JPanel();
		southPlayerInfoPanel.setBounds(0, 0, 400, 201);
		southPlayerInfoPanel.setBackground(Color.blue);
		playerInfoPanel.add(southPlayerInfoPanel);
		
		westPlayerInfoPanel = new JPanel();
		westPlayerInfoPanel.setBounds(0, 0, 400, 200);
		westPlayerInfoPanel.setBackground(Color.pink);
		playerInfoPanel.add(westPlayerInfoPanel);
		
		northPlayerInfoPanel = new JPanel();
		northPlayerInfoPanel.setBounds(0, 0, 400, 200);
		northPlayerInfoPanel.setBackground(Color.gray);
		playerInfoPanel.add(northPlayerInfoPanel);
		
		eastPlayerInfoPanel = new JPanel();
		eastPlayerInfoPanel.setBounds(0, 0, 400, 200);
		eastPlayerInfoPanel.setBackground(Color.cyan);
		playerInfoPanel.add(eastPlayerInfoPanel);
		
		inGameOverlay.add(playerInfoPanel, JLayeredPane.PALETTE_LAYER);
	}
	
	public void southPlayerInfoPanel(Color color, String name) {
		this.southPlayerInfoPanel.setBackground(color);
		this.southPlayerInfoPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		southPlayerDetails = new JLabel(name);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(5, 5, 0, 0);
		southPlayerDetails.setForeground(Color.black);
		southPlayerDetails.setFont(MainWindow.orbitron.deriveFont(16f));
		
		this.southPlayerInfoPanel.add(southPlayerDetails, gbc);
	}
	
	public void westPlayerInfoPanel(Color color, String name) {
		this.westPlayerInfoPanel.setBackground(color);
		this.westPlayerInfoPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		westPlayerDetails = new JLabel(name);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(5, 5, 0, 0);
		westPlayerDetails.setForeground(Color.black);
		westPlayerDetails.setFont(MainWindow.orbitron.deriveFont(16f));
		
		this.westPlayerInfoPanel.add(westPlayerDetails, gbc);
	}
	
	public void northPlayerInfoPanel(Color color, String name) {
		this.northPlayerInfoPanel.setBackground(color);
		this.northPlayerInfoPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		northPlayerDetails = new JLabel(name);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(5, 5, 0, 0);
		northPlayerDetails.setForeground(Color.black);
		northPlayerDetails.setFont(MainWindow.orbitron.deriveFont(16f));
		
		this.northPlayerInfoPanel.add(northPlayerDetails, gbc);
	}
	
	public void eastPlayerInfoPanel(Color color, String name) {
		this.eastPlayerInfoPanel.setBackground(color);
		this.eastPlayerInfoPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		eastPlayerDetails = new JLabel(name);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(5, 5, 0, 0);
		eastPlayerDetails.setForeground(Color.black);
		eastPlayerDetails.setFont(MainWindow.orbitron.deriveFont(16f));
		
		this.eastPlayerInfoPanel.add(eastPlayerDetails, gbc);
	}

	public JLabel getSouthPlayerDetails() {
		return southPlayerDetails;
	}

	public void setSouthPlayerDetails(JLabel southPlayerDetails) {
		this.southPlayerDetails = southPlayerDetails;
	}

	public JLabel getWestPlayerDetails() {
		return westPlayerDetails;
	}

	public void setWestPlayerDetails(JLabel westPlayerDetails) {
		this.westPlayerDetails = westPlayerDetails;
	}

	public JLabel getNorthPlayerDetails() {
		return northPlayerDetails;
	}

	public void setNorthPlayerDetails(JLabel northPlayerDetails) {
		this.northPlayerDetails = northPlayerDetails;
	}

	public JLabel getEastPlayerDetails() {
		return eastPlayerDetails;
	}

	public void setEastPlayerDetails(JLabel eastPlayerDetails) {
		this.eastPlayerDetails = eastPlayerDetails;
	}

	public JPanel getWestPlayerSide() {
		return westPlayerSide;
	}

	public void setWestPlayerSide(JPanel westPlayerSide) {
		this.westPlayerSide = westPlayerSide;
	}

	public JPanel getEastPlayerSide() {
		return eastPlayerSide;
	}

	public void setEastPlayerSide(JPanel eastPlayerSide) {
		this.eastPlayerSide = eastPlayerSide;
	}

	public JPanel getNorthPlayerSide() {
		return northPlayerSide;
	}

	public void setNorthPlayerSide(JPanel northPlayerSide) {
		this.northPlayerSide = northPlayerSide;
	}

	public JPanel getSouthPlayerSide() {
		return southPlayerSide;
	}

	public void setSouthPlayerSide(JPanel southPlayerSide) {
		this.southPlayerSide = southPlayerSide;
	}

	public void settingsOverlay() {
		settingsPanel.setVisible(true);
	}

	public void setGameBoard(JPanel gameBoard) {
		gameBoard.setBounds(15, 25, 700, 700);
		inGameOverlay.add(gameBoard, JLayeredPane.DEFAULT_LAYER);
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public JLayeredPane getInGameOverlay() {
		return inGameOverlay;
	}

	public void setInGameOverlay(JLayeredPane inGameOverlay) {
		this.inGameOverlay = inGameOverlay;
	}

	/*
	 * Action Listener Implementations for InGameUIPanel.
	 */
	public void actionPerformed(ActionEvent event) {

		// Get the object that the event happened
		Object selected = event.getSource();

		// Settings Panel Action Listeners Control Flow
		if (selected.equals(settingsButton)) {
			// Selected button was settings button

			if (settingsPanel.isVisible()) {
				// If the settings panel is visible, toggle it off and game board on
				// saveGamePanel.setVisible(false);
				settingsPanel.setVisible(false);
			} else {
				// The settings panel is not visible set visible
				settingsPanel.setVisible(true);
				// gameBoard.setEnabled(false);
			}

			if (this.saveGamePanel.isVisible()) {
				saveGamePanel.setVisible(false);
				settingsPanel.setVisible(false);
			}

		}
		if (selected.equals(resumeGameButton)) {
			settingsPanel.setVisible(false);
		}
		if (selected.equals(backToMenuButton)) {
			// If back to menu is selected, toggle this panel off, show mainMenu Panel
			inGameOverlay.setVisible(false);
			settingsPanel.setVisible(false);
			mainPanel.setVisible(false);
			GameSettings.GetMainWindow().ShowPanel(GameSettings.GetMainMenu());
		}
		if (selected.equals(quitGameButton)) {
			System.exit(0);
		}

		/************************************************************************************/

		/* Save Game Panel Action Listeners Control Flow */
		if (selected.equals(saveGameButton)) {
			this.settingsPanel.setVisible(false);
			this.saveGamePanel.setVisible(true);
		}
		if (selected.equals(saveGame1)) {
			SaveGame.saveGameObjs("Save1.sav");
			saveGame1.setText("Saved To Save 1 Slot");
			saveGame1.setBackground(Color.GREEN);
			saveGame1.setEnabled(false);
		}
		if (selected.equals(saveGame2)) {
			SaveGame.saveGameObjs("Save2.sav");
			saveGame2.setText("Saved to Save 2 Slot");
			saveGame2.setBackground(Color.GREEN);
			saveGame2.setEnabled(false);
		}
		if (selected.equals(saveGame3)) {
			SaveGame.saveGameObjs("Save3.sav");
			saveGame3.setText("Saved to Save 3 Slot");
			saveGame3.setBackground(Color.GREEN);
			saveGame3.setEnabled(false);
		}
		if (selected.equals(backToSettingsButton)) {
			this.saveGamePanel.setVisible(false);
			this.settingsPanel.setVisible(true);
			this.setSaveGamePanel();
		}
		/**************************************************************************************/

		GameSettings.playButtonSound();

	}

}
