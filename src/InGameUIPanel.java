import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class InGameUIPanel extends JPanel implements ActionListener {

	// the outer (border) panels surrounding the gameboard
	private JPanel nwCornerPanel, neCornerPanel, seCornerPanel, swCornerPanel;
	private JPanel southGameBoardBorder, westGameBoardBorder, northGameBoardBorder, eastGameBoardBorder;

	// IngameUIPanel settings button
	private JButton settingsButton;

	// the center panel, holds the gameboard
	private JPanel middlePanel;

	// constraints for this layout
	GridBagConstraints gbc = new GridBagConstraints();
	private JPanel settingsPanel;
	private JButton saveGameButton;
	private JButton backToMenuButton;
	private JButton quitButton;
	private JPanel saveGamePanel;
	private JButton saveGame1;
	private JButton saveGame2;
	private JButton saveGame3;
	private JButton backToSettingsButton;
	JLabel savedLabel;

	// constructor
	public InGameUIPanel() {
		super(); // Creates a panel

		this.setMinimumSize(new Dimension(1000, 1000));
		this.setPreferredSize(new Dimension(1000, 1000));
		setLayout(new GridBagLayout());
		alignedPerimeterBorders();

	}

	/**
	 * This method is used to align all borders on the GridPanel perimeter. Using
	 * GridBagLayout and GridBagConstraints. These panels are indicators for the
	 * players of the game.
	 * 
	 */
	public void alignedPerimeterBorders() {
		// Outer Border Dimension
		Dimension outerBorderX = new Dimension(800, 100);
		Dimension outerBorderY = new Dimension(100, 800);

		// North Border above Game Board.
		northGameBoardBorder = new JPanel();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		northGameBoardBorder.setMinimumSize(outerBorderX);
		northGameBoardBorder.setPreferredSize(outerBorderX);
		this.add(northGameBoardBorder, gbc);

		// North West border square.
		nwCornerPanel = new JPanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(nwCornerPanel, gbc);

		// West Border Of Game Board
		westGameBoardBorder = new JPanel();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		westGameBoardBorder.setMinimumSize(outerBorderY);
		westGameBoardBorder.setPreferredSize(outerBorderY);
		this.add(westGameBoardBorder, gbc);

		// Center Panel to house game board.
		middlePanel = new JPanel();
		gbc.gridx = 1;
		gbc.gridy = 1;
		// stretch gridbag layout as necessary to accomodate height/width
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		// align to center
		gbc.anchor = GridBagConstraints.SOUTH;
		// add the middle panel
		this.add(middlePanel, gbc);

		// North East border Panel.
		neCornerPanel = new JPanel();
		neCornerPanel.setLayout(new GridBagLayout());
		neCornerPanel.setPreferredSize(new Dimension(99, 99));
		neCornerPanel.setMaximumSize(new Dimension(99, 99));
		// Create settings button.
		settingsButton = new JButton();
		// Set icon to a cog icon
		settingsButton.addActionListener(this);
		settingsButton.setIcon(new ImageIcon(getClass().getResource("/Assets/inGameUiPanel_settingButton.png"))); // Insert
																													// icon
																													// for
																													// settings
																													// button
		settingsButton.setContentAreaFilled(false);
		settingsButton.setFocusPainted(false);
		gbc.gridx = 0;
		gbc.gridx = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		// add button to the North East panel
		neCornerPanel.add(settingsButton, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(neCornerPanel, gbc);

		// East Border of Game Board
		eastGameBoardBorder = new JPanel();
		gbc.gridx = 2;
		gbc.gridy = 1;
		eastGameBoardBorder.setMinimumSize(outerBorderY);
		eastGameBoardBorder.setPreferredSize(outerBorderY);
		this.add(eastGameBoardBorder, gbc);

		// South West Border of the Grid Panel.
		swCornerPanel = new JPanel();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(swCornerPanel, gbc);

		// South Border of the game board.
		southGameBoardBorder = new JPanel();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		southGameBoardBorder.setMinimumSize(outerBorderX);
		southGameBoardBorder.setPreferredSize(outerBorderX);
		this.add(southGameBoardBorder, gbc);

		// South East border panel.
		seCornerPanel = new JPanel();
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(seCornerPanel, gbc);

		setSettingsPanel();
	}

	// Panel Methods
	public void setSettingsPanel() {
		settingsPanel = new JPanel(new GridBagLayout());
		Insets buttonInsets = new Insets(50, 50, 50, 50);
		settingsPanel.setVisible(false);
		gbc.gridx = 1;
		gbc.gridy = 1;

		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.SOUTH;
		this.add(settingsPanel, gbc);
		saveGameButton = new JButton("Save Game");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = buttonInsets;
		saveGameButton.addActionListener(this);
		settingsPanel.add(saveGameButton, gbc);
		backToMenuButton = new JButton("Back To Main Menu");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = buttonInsets;
		backToMenuButton.addActionListener(this);
		settingsPanel.add(backToMenuButton, gbc);
		quitButton = new JButton("Quit Game");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = buttonInsets;
		quitButton.addActionListener(this);
		settingsPanel.add(quitButton, gbc);
		settingsButton.setVisible(true);

		setSaveGamePanel();
	}

	public void setSaveGamePanel() {
		saveGamePanel = new JPanel(new GridBagLayout());
		Insets buttonInsets = new Insets(20, 50, 20, 50);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.SOUTH;
		this.add(saveGamePanel, gbc);

		JLabel instructSaveLabel = new JLabel("To save a Game. Choose a save file below:", SwingConstants.CENTER);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		this.saveGamePanel.add(instructSaveLabel, gbc);

		saveGame1 = new JButton("Save 1");
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = buttonInsets;
		saveGame1.addActionListener(this);
		this.saveGamePanel.add(saveGame1, gbc);

		saveGame2 = new JButton("Save 2");
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.weightx = 0.75;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = buttonInsets;
		saveGame2.addActionListener(this);
		this.saveGamePanel.add(saveGame2, gbc);

		saveGame3 = new JButton("Save 3");
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = buttonInsets;
		saveGame3.addActionListener(this);
		this.saveGamePanel.add(saveGame3, gbc);

		savedLabel = new JLabel();
		gbc.gridx = 2;
		gbc.gridy = 4;
		savedLabel.setVisible(true);
		this.saveGamePanel.add(savedLabel, gbc);

		backToSettingsButton = new JButton("Back");
		gbc.gridx = 2;
		gbc.gridy = 5;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		backToSettingsButton.addActionListener(this);
		this.saveGamePanel.add(backToSettingsButton, gbc);
		saveGamePanel.setVisible(false);
	}

	// Getters
	public JPanel getMiddlePanel() {
		return middlePanel;
	}

	public JPanel[] getCornerPanels() {
		JPanel[] cornerPanels = { nwCornerPanel, neCornerPanel, seCornerPanel, swCornerPanel };
		return cornerPanels;
	}

	// Helpers

	/*
	 * Sets South border panel background color.
	 * 
	 * @param: Color c
	 * 
	 * @returns: void
	 */
	public void setSouthBorderBG(Color c) {
		this.southGameBoardBorder.setBackground(c);
	}

	/*
	 * Sets West border panel background color.
	 * 
	 * @param: Color c
	 * 
	 * @returns: void
	 */
	public void setWestBorderBG(Color c) {
		this.westGameBoardBorder.setBackground(c);
	}

	/*
	 * Sets the North border panel background color.
	 * 
	 * @param: Color c
	 * 
	 * @returns: void
	 */
	public void setNorthBorderBG(Color c) {
		this.northGameBoardBorder.setBackground(c);
	}

	/*
	 * Sets the East border panel background color.
	 * 
	 * @param: Color c
	 * 
	 * @returns: void
	 */
	public void setEastBorderBG(Color c) {
		this.eastGameBoardBorder.setBackground(c);
	}

	public void setSettingsPanelBG(Color bkgColor) {
		settingsPanel.setBackground(bkgColor);
	}

	/*
	 * Action Listener Implementations for InGameUIPanel.
	 */
	public void actionPerformed(ActionEvent event) {

		// Get the object that the event happened
		Object selected = event.getSource();

		/* Settings Panel Action Listeners Control Flow */
		if (selected.equals(settingsButton)) {
			// Selected button was settings button

			if (this.settingsPanel.isVisible()) {
				// If the settings panel is visible, toggle it off and game board on
				
				saveGamePanel.setVisible(false);
				middlePanel.setVisible(true);
			} else {
				// The settings panel is not visible set visible
				settingsPanel.setVisible(false);
				this.settingsPanel.setVisible(true);
				this.middlePanel.setVisible(false);
			}
			
			if(this.saveGamePanel.isVisible()) {
				saveGamePanel.setVisible(false);
			}

		}

		if (selected.equals(backToMenuButton)) {
			// If back to menu is selected, toggle this panel off, show mainMenu Panel
			this.setVisible(false);
			GameSettings.GetMainWindow().ShowPanel(GameSettings.GetMainWindow().mainMenuPanel);
		}
		if (selected.equals(quitButton)) {
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
		}
		if (selected.equals(saveGame2)) {
			SaveGame.saveGameObjs("Save2.sav");
			saveGame2.setText("Saved to Save 2 Slot");
			saveGame2.setBackground(Color.GREEN);
		}
		if (selected.equals(saveGame3)) {
			SaveGame.saveGameObjs("Save3.sav");
			saveGame3.setText("Saved to Save 3 Slot");
			saveGame3.setBackground(Color.GREEN);
		}
		if (selected.equals(backToSettingsButton)) {
			this.saveGamePanel.setVisible(false);
			this.settingsPanel.setVisible(true);
			this.setSaveGamePanel();
		}
		/**************************************************************************************/

	}

}
