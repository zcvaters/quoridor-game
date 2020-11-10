import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class NewGameMenu extends JPanel implements ActionListener {

	// new game
	JLabel newGameHeaderLabel;
	JLabel newGameChooseColorsLabel;
	JButton color1Button;
	JButton color2Button;
	JButton color3Button;
	JPanel colorButtons; // subpanel
	// configurePlayersPanel and subpanels
	JPanel showPlayersPanel;
	JPanel configPlayersPanel;
	String[] comboBoxOptions = { "Human", "Computer(Easy)", "Computer(Hard)" };
	JPanel firstPanel;
	JLabel firstIconLabel;
	JLabel firstNameLabel;
	JTextField firstTextField;
	JComboBox<String> firstComboBox;
	JPanel secondPanel;
	JLabel secondIconLabel;
	JLabel secondNameLabel;
	JTextField secondTextField;
	JComboBox<String> secondComboBox;
	JPanel thirdPanel;
	JLabel thirdIconLabel;
	JLabel thirdNameLabel;
	JTextField thirdTextField;
	JComboBox<String> thirdComboBox;
	JPanel fourthPanel;
	JLabel fourthIconLabel;
	JLabel fourthNameLabel;
	JTextField fourthTextField;
	JComboBox<String> fourthComboBox;
	// sub-panel to hold "play" and "back" buttons on the new game panel
	JPanel playBackButtonsPanel;
	JButton newGameBackButton;
	JButton newGamePlayButton;

	// cache ref for the colors for tiles/walls/players that are selected
	Color tileColorSelected;
	Color wallColorSelected;
	Color bkgColorSelected;
	Color[] playerColorsSelected;

	// constructor
	public NewGameMenu() {

		// build a panel
		super();
		// size to fit frame
		this.setBounds(0, 0, 1000, 1000);
		this.setOpaque(false);

		// the labels for the header and choose colors
		newGameHeaderLabel = new JLabel("NEW GAME");
		EmptyBorder border1 = new EmptyBorder(190, 0, 20, 20);
		newGameHeaderLabel.setBorder(border1);
		newGameHeaderLabel.setAlignmentX(CENTER_ALIGNMENT);
		// newGameHeaderLabel.setPreferredSize(new Dimension(1000, 100));
		// newGameHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		newGameHeaderLabel.setVerticalAlignment(JLabel.CENTER);
		newGameChooseColorsLabel = new JLabel("Please choose a board style");
		EmptyBorder border2 = new EmptyBorder(20, 0, 20, 0);
		newGameChooseColorsLabel.setBorder(border2);
		newGameChooseColorsLabel.setAlignmentX(CENTER_ALIGNMENT);
		// newGameChooseColorsLabel.setPreferredSize(new Dimension(1000, 100));
		// newGameChooseColorsLabel.setHorizontalAlignment(JLabel.CENTER);
		// newGameChooseColorsLabel.setVerticalAlignment(JLabel.CENTER);

		// select color-buttons and listeners
		color1Button = new JButton("ColorSet #1");
		color1Button.setPreferredSize(new Dimension(300, 200));
		color1Button.addActionListener(this);
		color2Button = new JButton("ColorSet #2");
		color2Button.setPreferredSize(new Dimension(300, 200));
		color2Button.addActionListener(this);
		color3Button = new JButton("ColorSet #3");
		color3Button.setPreferredSize(new Dimension(300, 200));
		color3Button.addActionListener(this);
		// put the color buttons in a sub-panel
		colorButtons = new JPanel();
		FlowLayout colorButtonLayout = new FlowLayout();
		colorButtons.setLayout(colorButtonLayout);
		colorButtons.setOpaque(false);

		colorButtons.add(color1Button);
		colorButtons.add(color2Button);
		colorButtons.add(color3Button);
		colorButtons.setVisible(true);

		// configure players panel (hidden until player selects a color style)
		showPlayersPanel = new JPanel(new GridLayout(2, 1)); // gridX4, play button, back button
		showPlayersPanel.setOpaque(false);

		configPlayersPanel = new JPanel();
		configPlayersPanel.setOpaque(false);
		GridLayout configLayout = new GridLayout(2, 2);
		configLayout.setHgap(200);
		// configLayout.setVgap(20);
		configPlayersPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
		configPlayersPanel.setLayout(configLayout);
		// build one sub-panel for each player (four total)
		// icon, ask for name, name textbox, and player type combo box

		firstPanel = new JPanel();
		firstPanel.setOpaque(false);
		firstPanel.setLayout(new GridLayout(5, 1));
		firstIconLabel = new JLabel("Color 1 Here");
		firstIconLabel.setHorizontalAlignment(JLabel.CENTER);
		firstIconLabel.setVerticalAlignment(JLabel.CENTER);
		firstTextField = new JTextField("<<Your Name Here>>", 1);
		firstComboBox = new JComboBox<String>(comboBoxOptions);
		firstComboBox.addActionListener(this);
		firstPanel.add(firstIconLabel);
		firstPanel.add(firstTextField);
		firstPanel.add(firstComboBox);

		secondPanel = new JPanel();
		secondPanel.setOpaque(false);
		secondPanel.setLayout(new GridLayout(5, 1));
		secondIconLabel = new JLabel("Color 2 Here");
		secondIconLabel.setHorizontalAlignment(JLabel.CENTER);
		secondIconLabel.setVerticalAlignment(JLabel.CENTER);
		secondTextField = new JTextField("<<Your Name Here>>", 1);
		// secondTextField.setColumns(12);
		secondComboBox = new JComboBox<String>(comboBoxOptions);
		secondComboBox.addActionListener(this);
		secondPanel.add(secondIconLabel);
		secondPanel.add(secondTextField);
		secondPanel.add(secondComboBox);

		thirdPanel = new JPanel();
		thirdPanel.setOpaque(false);
		thirdPanel.setLayout(new GridLayout(5, 1));
		thirdIconLabel = new JLabel("Color 3 Here");
		thirdIconLabel.setHorizontalAlignment(JLabel.CENTER);
		thirdIconLabel.setVerticalAlignment(JLabel.CENTER);
		thirdTextField = new JTextField("<<Your Name Here>>", 1);
		// thirdTextField.setColumns(12);
		thirdComboBox = new JComboBox<String>(comboBoxOptions);
		thirdComboBox.addActionListener(this);
		thirdPanel.add(thirdIconLabel);
		thirdPanel.add(thirdTextField);
		thirdPanel.add(thirdComboBox);

		fourthPanel = new JPanel();
		fourthPanel.setOpaque(false);
		fourthPanel.setLayout(new GridLayout(5, 1));
		fourthIconLabel = new JLabel("Color 4 Here");
		fourthIconLabel.setHorizontalAlignment(JLabel.CENTER);
		fourthIconLabel.setVerticalAlignment(JLabel.CENTER);
		fourthTextField = new JTextField("<<Your Name Here>>", 1);
		// fourthTextField.setColumns(12);
		fourthComboBox = new JComboBox<String>(comboBoxOptions);
		fourthComboBox.addActionListener(this);
		fourthPanel.add(fourthIconLabel);
		fourthPanel.add(fourthTextField);
		fourthPanel.add(fourthComboBox);

		// add these four panels to the configPlayers panel
		configPlayersPanel.add(firstPanel);
		configPlayersPanel.add(secondPanel);
		configPlayersPanel.add(thirdPanel);
		configPlayersPanel.add(fourthPanel);

		// the "back" button and listener
		newGameBackButton = new JButton("Back");
		newGameBackButton.setAlignmentX(CENTER_ALIGNMENT);
		newGameBackButton.addActionListener(this);
		// the "Let's Play!" button and listener
		newGamePlayButton = new JButton("Let's Play!");
		newGamePlayButton.setAlignmentX(CENTER_ALIGNMENT);
		newGamePlayButton.addActionListener(this);
		// add the buttons to a panel
		playBackButtonsPanel = new JPanel();
		playBackButtonsPanel.setOpaque(false);
		BoxLayout buttonsLayout = new BoxLayout(playBackButtonsPanel, BoxLayout.Y_AXIS);
		playBackButtonsPanel.setLayout(buttonsLayout);
		playBackButtonsPanel.add(newGamePlayButton);
		playBackButtonsPanel.add(newGameBackButton);

		// add configPlayers panel and buttons to showPlayersPanel
		showPlayersPanel.add(configPlayersPanel);
		showPlayersPanel.add(playBackButtonsPanel);

		// hide configPlayersPanel for now. Activated when needed.
		showPlayersPanel.setVisible(false);

		// add components to the panel
		BoxLayout newPanelLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(newPanelLayout);
		this.add(newGameHeaderLabel);
		this.add(newGameChooseColorsLabel);
		this.add(colorButtons);
		this.add(showPlayersPanel);

		// hide this panel, it's made visible when needed
		this.setVisible(false);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// get the object that performed the action, respond accordingly
		Object selected = event.getSource();

		// NEW GAME

		// did the user click on any of the color buttons in the new game panel?
		if (selected.equals(color1Button) || selected.equals(color2Button) || selected.equals(color3Button)) {

			// was it color option #1?
			if (selected.equals(color1Button)) {
				// get the first color palette from Game Settings
				tileColorSelected = GameSettings.getTileColor(0); // <---zero indexed!
				wallColorSelected = GameSettings.getWallColor(0);
				bkgColorSelected = GameSettings.getBkgColor(0);
				playerColorsSelected = GameSettings.getPlayerColors(0);
			}
			// was it color option #2?
			if (selected.equals(color2Button)) {
				// get the second color palette from Game Settings
				tileColorSelected = GameSettings.getTileColor(1);
				wallColorSelected = GameSettings.getWallColor(1);
				bkgColorSelected = GameSettings.getBkgColor(1);
				playerColorsSelected = GameSettings.getPlayerColors(1);
			}
			// was it color option #3?
			if (selected.equals(color3Button)) {
				// get the third color palette from Game Settings
				tileColorSelected = GameSettings.getTileColor(2);
				wallColorSelected = GameSettings.getWallColor(2);
				bkgColorSelected = GameSettings.getBkgColor(2);
				playerColorsSelected = GameSettings.getPlayerColors(2);
			}
			// after setting colors, display the "configure players" options
			DisplayPlayerColors();
			showPlayersPanel.setVisible(true);
		}

		// did user click on a player-type combo box (dropdown menu)?
		if (selected.equals(firstComboBox)) {
			// if combo box was changed to "Computer", generate and display a random name
			String focus = firstComboBox.getSelectedItem().toString();
			if (focus.equals("Computer(Easy)") || focus.equals("Computer(Hard)")) {
				firstTextField.setText(GameSettings.GetRandomName());
			}
			if (focus.equals("Human")) {
				firstTextField.setText("<<Your Name Here>>");
			}
		}

		if (selected.equals(secondComboBox)) {
			// if combo box was changed to "Computer", generate and display a random name
			String focus = secondComboBox.getSelectedItem().toString();
			if (focus.equals("Computer(Easy)") || focus.equals("Computer(Hard)")) {
				secondTextField.setText(GameSettings.GetRandomName());
			}
			if (focus.equals("Human")) {
				secondTextField.setText("<<Your Name Here>>");
			}
		}

		if (selected.equals(thirdComboBox)) {
			// if combo box was changed to "Computer", generate and display a random name
			String focus = thirdComboBox.getSelectedItem().toString();
			if (focus.equals("Computer(Easy)") || focus.equals("Computer(Hard)")) {
				thirdTextField.setText(GameSettings.GetRandomName());
			}
			if (focus.equals("Human")) {
				thirdTextField.setText("<<Your Name Here>>");
			}
		}

		if (selected.equals(fourthComboBox)) {
			// if combo box was changed to "Computer", generate and display a random name
			String focus = fourthComboBox.getSelectedItem().toString();
			if (focus.equals("Computer(Easy)") || focus.equals("Computer(Hard)")) {
				fourthTextField.setText(GameSettings.GetRandomName());
			}
			if (focus.equals("Human")) {
				fourthTextField.setText("<<Your Name Here>>");
			}
		}

		// was "back" requested on new game panel?
		if (selected.equals(newGameBackButton)) {
			// hide this for next time
			showPlayersPanel.setVisible(false);
			GameSettings.GetMainWindow().ShowPanel(GameSettings.GetMainMenu());
		}

		// was "Let's Play" requested on new game panel?
		if (selected.equals(newGamePlayButton)) {

			// compile list of names
			ArrayList<String> playerNames = new ArrayList<String>();
			playerNames.add(firstTextField.getText());
			playerNames.add(secondTextField.getText());
			playerNames.add(thirdTextField.getText());
			playerNames.add(fourthTextField.getText());

			// compile list of types
			ArrayList<String> playerTypes = new ArrayList<String>();
			playerTypes.add(firstComboBox.getSelectedItem().toString());
			playerTypes.add(secondComboBox.getSelectedItem().toString());
			playerTypes.add(thirdComboBox.getSelectedItem().toString());
			playerTypes.add(fourthComboBox.getSelectedItem().toString());

			// generate a random list to determine order of play
			List<Integer> turnOrder = GameSettings.getPlayerIDList();

			// build assets based on settings
			BuildAssets thisBuild = new BuildAssets(tileColorSelected, // <----NEW GAME STARTS HERE
					wallColorSelected, bkgColorSelected, playerNames, playerTypes, playerColorsSelected, turnOrder);
		}
	}

	private void DisplayPlayerColors() {
		firstIconLabel.setBackground(playerColorsSelected[0]);
		firstIconLabel.setOpaque(true);
		secondIconLabel.setBackground(playerColorsSelected[1]);
		secondIconLabel.setOpaque(true);
		thirdIconLabel.setBackground(playerColorsSelected[2]);
		thirdIconLabel.setOpaque(true);
		fourthIconLabel.setBackground(playerColorsSelected[3]);
		fourthIconLabel.setOpaque(true);
	}
}
