import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LoadGameMenu extends JPanel implements ActionListener {

	// load game
	JLabel loadGameHeaderLabel;
	JLabel loadGameTextLabel;
	JButton loadGameBackButton;
	private JButton loadGameSave1;
	private JButton loadGameSave2;
	private JButton loadGameSave3;
	private JLabel selectionLabel;
	private String filename;
	private ArrayList<JButton> buttons;
	private JLabel instructionsLoadGameLabel;

	public LoadGameMenu() {

		// build a panel
		super();

		// header label at the top
		// load game stuff in the center
		// button "Back" at the bottom

		this.setBounds(0, 0, 1200, 800);
		this.setOpaque(false);

		// the label for the header
		loadGameHeaderLabel = new JLabel("Load Game");
		loadGameHeaderLabel.setFont(MainWindow.orbitron.deriveFont(72f));
		loadGameHeaderLabel.setForeground(Color.black);
		EmptyBorder border1 = new EmptyBorder(50, 40, 20, 0);
		loadGameHeaderLabel.setBorder(border1);
		loadGameHeaderLabel.setAlignmentX(CENTER_ALIGNMENT);
		loadGameHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		loadGameHeaderLabel.setVerticalAlignment(JLabel.CENTER);

		instructionsLoadGameLabel = new JLabel("Please select a save file");
		instructionsLoadGameLabel.setFont(MainWindow.orbitron.deriveFont(32f));
		instructionsLoadGameLabel.setForeground(Color.black);
		instructionsLoadGameLabel.setAlignmentX(CENTER_ALIGNMENT);
		instructionsLoadGameLabel.setHorizontalAlignment(JLabel.CENTER);
		instructionsLoadGameLabel.setVerticalAlignment(JLabel.CENTER);
		
		
		// the "back" button and listener
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		Insets buttonInsets = new Insets(10, 10, 0, 0);

		loadGameSave1 = new JButton("Save 1");
		loadGameSave1.setContentAreaFilled(false);
		loadGameSave1.setBorderPainted(false);
		loadGameSave1.setFont(MainWindow.orbitron.deriveFont(24f));
		loadGameSave1.setForeground(Color.black);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = buttonInsets;
		loadGameSave1.addActionListener(this);
		buttonPanel.add(loadGameSave1, gbc);

		loadGameSave2 = new JButton("Save 2");
		loadGameSave2.setContentAreaFilled(false);
		loadGameSave2.setBorderPainted(false);
		loadGameSave2.setFont(MainWindow.orbitron.deriveFont(24f));
		loadGameSave2.setForeground(Color.black);
		gbc.gridx = 0;
		gbc.gridy = 1;
		loadGameSave2.addActionListener(this);
		buttonPanel.add(loadGameSave2, gbc);

		loadGameSave3 = new JButton("Save 3");
		loadGameSave3.setContentAreaFilled(false);
		loadGameSave3.setBorderPainted(false);
		loadGameSave3.setFont(MainWindow.orbitron.deriveFont(24f));
		loadGameSave3.setForeground(Color.black);
		gbc.gridx = 0;
		gbc.gridy = 2;
		loadGameSave3.addActionListener(this);
		buttonPanel.add(loadGameSave3, gbc);

		loadGameBackButton = new JButton("Back");
		loadGameBackButton.setContentAreaFilled(false);
		loadGameBackButton.setBorderPainted(false);
		loadGameBackButton.setFont(MainWindow.orbitron.deriveFont(24f));
		loadGameBackButton.setForeground(Color.black);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		buttonPanel.add(loadGameBackButton, gbc);
		buttonPanel.setOpaque(false);
		loadGameBackButton.addActionListener(this);

		buttonHoverAction(loadGameSave1);
		buttonHoverAction(loadGameSave2);
		buttonHoverAction(loadGameSave3);
		buttonHoverAction(loadGameBackButton);

		selectionLabel = new JLabel("", SwingConstants.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 5;
		buttonPanel.add(selectionLabel, gbc);

		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxLayout);
		this.add(loadGameHeaderLabel);
		this.add(instructionsLoadGameLabel);
		this.add(buttonPanel, Component.CENTER_ALIGNMENT);
		// hide this panel
		this.setVisible(false);

		buttons = new ArrayList<>();
		buttons.add(loadGameSave1);
		buttons.add(loadGameSave2);
		buttons.add(loadGameSave3);
		buttons.add(loadGameBackButton);
	}

	public JLabel getSelectionLabel() {
		return selectionLabel;
	}

	public void setSelectionLabel(String label, Color col) {
		selectionLabel.setText(label);
		selectionLabel.setForeground(col);
	}

	public void buttonHoverAction(JButton button) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setForeground(new Color(140, 15, 15));
				GameSettings.playButtonSound();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setForeground(Color.black);
			}

		});
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// get the object that performed the action, respond accordingly
		Object selected = event.getSource();

		if (selected.equals(loadGameSave1)) {
			filename = "Save1.sav";
			LoadGame.loadGameObjs(filename);
		}
		if (selected.equals(loadGameSave2)) {

			filename = "Save2.sav";
			LoadGame.loadGameObjs(filename);
		}
		if (selected.equals(loadGameSave3)) {
			filename = "Save3.sav";
			LoadGame.loadGameObjs(filename);
		}

		// was 'back' requested on load game panel?
		if (selected.equals(loadGameBackButton)) {
			GameSettings.GetMainWindow().ShowPanel(GameSettings.GetMainMenu());
		}
		GameSettings.playButtonSound();
		buttons.forEach(button -> button.setForeground(Color.black));

	}

}
