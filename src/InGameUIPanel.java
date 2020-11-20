import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


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
	private JLabel settingsPanel;
	private JButton resumeGameButton;
	private JButton saveGameButton;
	private JButton backToMenuButton;
	private JButton quitGameButton;
	private JLabel saveGamePanel;
	private JButton saveGame1;
	private JButton saveGame2;
	private JButton saveGame3;
	private JLabel savedLabel;
	private JButton backToSettingsButton;
	private JPanel southPlayerSide;
	private JPanel westPlayerSide;
	private JPanel northPlayerSide;
	private JPanel eastPlayerSide;
	private JLabel southPlayerDetails;
	private JLabel westPlayerDetails;
	private JLabel northPlayerDetails;
	private JLabel eastPlayerDetails;

	private JPanel playerInfoPanel;

	private JPanel southPlayerInfoPanel;

	private JPanel westPlayerInfoPanel;

	private JPanel northPlayerInfoPanel;

	private JPanel eastPlayerInfoPanel;
	private FenceUIManager fences;

	private JLabel frameLabel;

	private JLabel messageLabel;

	private Buttons okButton;

	private LocalTime time;

	private JLabel lastSaveTime;
	
	DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("hh:mm a").toFormatter();

	private JLabel winnerLabel;

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
		settingsButton = new Buttons("");
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
		ImageIcon inGameSettingsPanelBG = new ImageIcon(getClass().getResource("/Assets/inGameSettingsPanel_BG.png"));
		settingsPanel = new JLabel(inGameSettingsPanelBG);
		settingsPanel.setLayout(new GridBagLayout());
		settingsPanel.setBounds(240, 200, 300, 300);
		settingsPanel.setOpaque(false);

		settingsPanel.setVisible(false);
		initializeSettingsOverlay();
		setSaveGamePanel();
		// playerSidePanels();
		playerStatsPanel();
		
		messagePanel();
		frameLabel.setVisible(true);

		this.fences = new FenceUIManager();
		GameSettings.setFencesUIManager(fences);
		Player[] players = GameSettings.getPlayers();
		
		this.southPlayerInfoPanel(players[0].GetColor(), players[0].GetName());
		this.westPlayerInfoPanel(players[1].GetColor(), players[1].GetName());
		this.northPlayerInfoPanel(players[2].GetColor(), players[2].GetName());
		this.eastPlayerInfoPanel(players[3].GetColor(), players[3].GetName());
		
		fences.removePlayerFence(players[0]);
		fences.removePlayerFence(players[1]);
		fences.removePlayerFence(players[2]);
		fences.removePlayerFence(players[3]);

	}

	public void initializeSettingsOverlay() {
		//resumeGameButton = new JButton("Resume");
		resumeGameButton = new Buttons("Resume");
		resumeGameButton.setFont(MainWindow.orbitron.deriveFont(24f));
		resumeGameButton.setVisible(true);
		resumeGameButton.setContentAreaFilled(false);
		resumeGameButton.setOpaque(false);
		resumeGameButton.addActionListener(this);
		resumeGameButton.setBounds(0, 0, 60, 60);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		settingsPanel.add(resumeGameButton, gbc);

		saveGameButton = new Buttons("Save Game");
		saveGameButton.setFont(MainWindow.orbitron.deriveFont(24f));
		saveGameButton.setOpaque(false);
		saveGameButton.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		settingsPanel.add(saveGameButton, gbc);

		backToMenuButton = new Buttons("Main Menu");
		backToMenuButton.setFont(MainWindow.orbitron.deriveFont(24f));
		backToMenuButton.setOpaque(false);
		backToMenuButton.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.BOTH;
		settingsPanel.add(backToMenuButton, gbc);

		quitGameButton = new Buttons("Quit Game");
		quitGameButton.setFont(MainWindow.orbitron.deriveFont(24f));
		quitGameButton.setOpaque(false);
		quitGameButton.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.BOTH;
		settingsPanel.add(quitGameButton, gbc);
		inGameOverlay.add(settingsPanel, JLayeredPane.PALETTE_LAYER);

	}
	
	public void messagePanel() {
		//get a panel
		ImageIcon panelFrame = new ImageIcon(getClass().getResource("/Assets/inGameMsgPanel_frame.png"));
		frameLabel = new JLabel(panelFrame);
		//set size
        frameLabel.setBounds(760,520,400,200);
        
        //get text
        String messageText = "Default message text.";
        messageLabel = new JLabel(messageText);
        messageLabel.setFont(MainWindow.orbitron.deriveFont(15f));
        messageLabel.setForeground(Color.black);
        EmptyBorder border1 = new EmptyBorder(20, 0, 20,0 );
		messageLabel.setBorder(border1);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //get button
        okButton = new Buttons("OK");
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        okButton.addActionListener(this);
        
        winnerLabel = new JLabel();
        winnerLabel.setFont(MainWindow.orbitron.deriveFont(12f));
        winnerLabel.setForeground(Color.black);
        winnerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lastSaveTime = new JLabel();
        lastSaveTime.setFont(MainWindow.orbitron.deriveFont(15f));
        lastSaveTime.setForeground(Color.black);
        lastSaveTime.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        lastSaveTime.setAlignmentX(Component.CENTER_ALIGNMENT);
        setLastSaveTime();
        
        //add text and button
        BoxLayout boxLayout = new BoxLayout(frameLabel, BoxLayout.PAGE_AXIS);
        frameLabel.setLayout(boxLayout);
        frameLabel.add(messageLabel);
        frameLabel.add(okButton);
        frameLabel.add(winnerLabel);
        frameLabel.add(lastSaveTime);
        //hide panel
        frameLabel.setVisible(false);
        
        inGameOverlay.add(frameLabel, JLayeredPane.DRAG_LAYER);
	}
	


	public void setLastSaveTime() {
		lastSaveTime.setText("Last Save in this Session: " + ((time == null) ? "No save." : time.format(formatter)));
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
		ImageIcon inGameSavingPanelBG = new ImageIcon(getClass().getResource("/Assets/inGameSettingsPanel_GameSave_BG.png"));
		saveGamePanel = new JLabel(inGameSavingPanelBG);
		saveGamePanel.setLayout(new GridBagLayout());
		saveGamePanel.setBounds(240, 200, 300, 300);
		saveGamePanel.setOpaque(false);

		JLabel instructSaveLabel = new JLabel("To save a Game. Choose a save Slot", SwingConstants.CENTER);
		instructSaveLabel.setFont(MainWindow.orbitron.deriveFont(12f));
		instructSaveLabel.setOpaque(false);
		instructSaveLabel.setForeground(Color.black);
		gbc.gridx = 2;
		gbc.gridy = 0;
		saveGamePanel.add(instructSaveLabel, gbc);

		//saveGame1 = new JButton("Save 1");
		saveGame1 = new Buttons("Save 1");
		saveGame1.setFont(MainWindow.orbitron.deriveFont(24f));
		saveGame1.setOpaque(false);
		//saveGame1.setBounds(0, 0, 100, 60);
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 0, 0, 0);
		saveGame1.addActionListener(this);
		saveGamePanel.add(saveGame1, gbc);

		saveGame2 = new Buttons("Save 2");
		saveGame2.setFont(MainWindow.orbitron.deriveFont(24f));
		saveGame2.setOpaque(false);
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		saveGame2.addActionListener(this);
		saveGamePanel.add(saveGame2, gbc);

		saveGame3 = new Buttons("Save 3");
		saveGame3.setFont(MainWindow.orbitron.deriveFont(24f));
		saveGame3.setOpaque(false);
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

		backToSettingsButton = new Buttons("Back");
		backToSettingsButton.setFont(MainWindow.orbitron.deriveFont(24f));
		backToSettingsButton.setOpaque(false);
		gbc.gridx = 2;
		gbc.gridy = 5;
		backToSettingsButton.addActionListener(this);
		saveGamePanel.add(backToSettingsButton, gbc);

		inGameOverlay.add(saveGamePanel, JLayeredPane.POPUP_LAYER);
		saveGamePanel.setVisible(false);
	}

	public JLabel getSaveGamePanel() {
		return saveGamePanel;
	}

	public void setSaveGamePanel(JLabel saveGamePanel) {
		this.saveGamePanel = saveGamePanel;
	}

	public void playerStatsPanel() {
		playerInfoPanel = new JPanel();
		playerInfoPanel.setLayout(new GridLayout(4, 1));
		playerInfoPanel.setBounds(800, 70, 300, 400);
		playerInfoPanel.setOpaque(false);

		southPlayerInfoPanel = new JPanel();
		southPlayerInfoPanel.setBounds(0, 0, 300, 100);
		southPlayerInfoPanel.setBackground(Color.blue);
		playerInfoPanel.add(southPlayerInfoPanel);

		westPlayerInfoPanel = new JPanel();
		westPlayerInfoPanel.setBounds(0, 0, 300, 100);
		westPlayerInfoPanel.setBackground(Color.pink);
		playerInfoPanel.add(westPlayerInfoPanel);

		northPlayerInfoPanel = new JPanel();
		northPlayerInfoPanel.setBounds(0, 0, 300, 100);
		northPlayerInfoPanel.setBackground(Color.gray);
		playerInfoPanel.add(northPlayerInfoPanel);

		eastPlayerInfoPanel = new JPanel();
		eastPlayerInfoPanel.setBounds(0, 0, 300, 100);
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

		fences.createSouthPlayerFences();
		JPanel fencesSouth = fences.getSouthFencesPanel();
		fencesSouth.setBounds(825, 110, 300, 200);
		inGameOverlay.add(fencesSouth, JLayeredPane.DRAG_LAYER);
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

		fences.createWestPlayerFences();
		JPanel fencesWest = fences.getWestFencesPanel();
		fencesWest.setBounds(825, 210, 300, 200);
		inGameOverlay.add(fencesWest, JLayeredPane.DRAG_LAYER);
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

		fences.createNorthPlayerFences();
		JPanel fencesNorth = fences.getNorthFencesPanel();
		fencesNorth.setBounds(825, 310, 300, 200);
		inGameOverlay.add(fencesNorth, JLayeredPane.DRAG_LAYER);
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

		fences.createEastPlayerFences();
		JPanel fencesEast = fences.getEastFencesPanel();
		fencesEast.setBounds(825, 410, 300, 200);
		inGameOverlay.add(fencesEast, JLayeredPane.DRAG_LAYER);
	}
	
	public void hideMessageLabel() {
		this.messageLabel.setVisible(false);
		this.okButton.setVisible(false);
	}
	
	public void showMessagelabel() {
		this.messageLabel.setVisible(true);
		this.okButton.setVisible(true);
	}
	
	public Buttons getOkButton() {
		return okButton;
	}

	public void setOkButton(Buttons okButton) {
		this.okButton = okButton;
	}
	public JLabel getFrameLabel() {
		return frameLabel;
	}

	public void setFrameLabel(JLabel frameLabel) {
		this.frameLabel = frameLabel;
	}
	
	public JLabel getMessageLabel() {
		return messageLabel;
	}

	public void setMessageLabel(JLabel messageLabel) {
		this.messageLabel = messageLabel;
	}
	
	public void setMessageLabelText(String text) {
		this.messageLabel.setText(text);
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
		gameBoard.setBounds(10, 10, 730, 730);
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

	public JLabel getWinnerLabel() {
		return winnerLabel;
	}

	public void setWinnerLabel(JLabel winnerLabel) {
		this.winnerLabel = winnerLabel;
	}
	/*
	 * Action Listener Implementations for InGameUIPanel.
	 */
	public void actionPerformed(ActionEvent event) {

		// Get the object that the event happened
		Object selected = event.getSource();
		
		/* Message Panel */
		if(selected.equals(okButton)) {			
			//gameplay can continue
			GameSettings.GetGameController().BeginTurn();
			okButton.setEnabled(false);
		}

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
			GameSettings.SetGameIsPaused(true);

		}
		if (selected.equals(resumeGameButton)) {
			settingsPanel.setVisible(false);
			GameSettings.SetGameIsPaused(false);
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
			saveGame1.setText("Saved: Slot 1");
			time = LocalTime.now();
			this.setLastSaveTime();
		}
		if (selected.equals(saveGame2)) {
			SaveGame.saveGameObjs("Save2.sav");
			saveGame2.setText("Saved: Slot 2");
			time = LocalTime.now();
			this.setLastSaveTime();
		}
		if (selected.equals(saveGame3)) {
			SaveGame.saveGameObjs("Save3.sav");
			saveGame3.setText("Saved: Slot 3");
			time = LocalTime.now();
			this.setLastSaveTime();
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
