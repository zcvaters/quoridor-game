import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LoadGameMenu extends JPanel implements ActionListener {

	// load game
	JLabel loadGameHeaderLabel;
	JLabel loadGameTextLabel;
	JButton loadGameBackButton;
	private JButton loadGameSave1;
	private JButton loadGameSave2;
	private JButton loadGameSave3;
	private JButton loadGamePlayButton;
	private JLabel selectionLabel;

	public LoadGameMenu() {

		// build a panel
		super();

		// header label at the top
		// load game stuff in the center
		// button "Back" at the bottom

		this.setBounds(0, 0, 1000, 1000);
		this.setOpaque(false);

		// the label for the header
		loadGameHeaderLabel = new JLabel("Load Game");
		EmptyBorder border1 = new EmptyBorder(190, 40, 0, 0);
		loadGameHeaderLabel.setBorder(border1);
		loadGameHeaderLabel.setAlignmentX(CENTER_ALIGNMENT);
		loadGameHeaderLabel.setFont(new Font("Dialog", Font.BOLD, 35));
		loadGameHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		loadGameHeaderLabel.setVerticalAlignment(JLabel.CENTER);

		// the "back" button and listener
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		Insets buttonInsets = new Insets(10, 10, 0, 0);

		loadGameSave1 = new JButton("Save 1");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = buttonInsets;
		loadGameSave1.addActionListener(this);
		buttonPanel.add(loadGameSave1, gbc);

		loadGameSave2 = new JButton("Save 2");
		gbc.gridx = 0;
		gbc.gridy = 1;
		loadGameSave2.addActionListener(this);
		buttonPanel.add(loadGameSave2, gbc);

		loadGameSave3 = new JButton("Save 3");
		gbc.gridx = 0;
		gbc.gridy = 2;
		loadGameSave3.addActionListener(this);
		buttonPanel.add(loadGameSave3, gbc);

		loadGamePlayButton = new JButton("Start Game");
		gbc.gridx = 0;
		gbc.gridy = 3;
		loadGamePlayButton.addActionListener(this);
		buttonPanel.add(loadGamePlayButton, gbc);

		loadGameBackButton = new JButton("Back");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		buttonPanel.add(loadGameBackButton, gbc);
		buttonPanel.setOpaque(false);
		loadGameBackButton.addActionListener(this);

		selectionLabel = new JLabel();
		gbc.gridx = 0;
		gbc.gridy = 5;
		selectionLabel.setText("");
		buttonPanel.add(selectionLabel, gbc);

		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxLayout);
		this.add(loadGameHeaderLabel);
		this.add(buttonPanel, Component.CENTER_ALIGNMENT);
		// hide this panel
		this.setVisible(false);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// get the object that performed the action, respond accordingly
		Object selected = event.getSource();

		String filename = "";
		if (selected.equals(loadGameSave1)) {
			selectionLabel.setText("Current Selection: Save 1.");
			filename = "Save1.sav";
		}
		if (selected.equals(loadGameSave2)) {

			selectionLabel.setText("Current Selection: Save 2.");
			filename = "Save2.sav";
		}
		if (selected.equals(loadGameSave3)) {
			selectionLabel.setText("Current Selection: Save 3.");
			filename = "Save3.sav";
		}
		if (selected.equals(loadGamePlayButton)) {
			LoadGame.loadGameObjs(filename);
		}

		// was 'back' requested on load game panel?
		if (selected.equals(loadGameBackButton)) {
			GameSettings.GetMainWindow().ShowPanel(GameSettings.GetMainMenu());
		}

	}

}
