import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
	
	private String save1ButtonText;
	private String save2ButtonText;
	private String save3ButtonText;

	public LoadGameMenu() {

		// build a panel
		super();

		// header label at the top
		// load game stuff in the center
		// button "Back" at the bottom

		this.setBounds(0, 0, 1200, 800);
		this.setOpaque(false);		
		
		//add appropriate text for save button labels
		ConfigureSaveButtonLabels();

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

		loadGameSave1 = new Buttons(save1ButtonText);
		loadGameSave1.setContentAreaFilled(false);
		loadGameSave1.setBorderPainted(false);
		loadGameSave1.setFont(MainWindow.orbitron.deriveFont(24f));
		loadGameSave1.setForeground(Color.black);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = buttonInsets;
		loadGameSave1.addActionListener(this);
		buttonPanel.add(loadGameSave1, gbc);

		loadGameSave2 = new Buttons(save2ButtonText);
		loadGameSave2.setContentAreaFilled(false);
		loadGameSave2.setBorderPainted(false);
		loadGameSave2.setFont(MainWindow.orbitron.deriveFont(24f));
		loadGameSave2.setForeground(Color.black);
		gbc.gridx = 0;
		gbc.gridy = 1;
		loadGameSave2.addActionListener(this);
		buttonPanel.add(loadGameSave2, gbc);

		loadGameSave3 = new Buttons(save3ButtonText);
		loadGameSave3.setContentAreaFilled(false);
		loadGameSave3.setBorderPainted(false);
		loadGameSave3.setFont(MainWindow.orbitron.deriveFont(24f));
		loadGameSave3.setForeground(Color.black);
		gbc.gridx = 0;
		gbc.gridy = 2;
		loadGameSave3.addActionListener(this);
		buttonPanel.add(loadGameSave3, gbc);

		loadGameBackButton = new Buttons("Back");
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
	
	public void ConfigureSaveButtonLabels() {
		//this method checks for the existance of any prev save files.
		//if they exist, configure the button label with the creation timestamp
		//if they do not exist, mark slot as [EMPTY].
		boolean save1Exists = new File("Save1.sav").exists();
		//System.out.println("Save 1 exists = " +save1Exists);
		boolean save2Exists = new File("Save2.sav").exists();
		//System.out.println("Save 2 exists = " +save2Exists);
		boolean save3Exists = new File("Save3.sav").exists();
		//System.out.println("Save 3 exists = " +save3Exists);
		
		
		String save1Name = "Save1.sav";
		String save2Name = "Save2.sav";
		String save3Name = "Save3.sav";

        File save1File = new File(save1Name);
        File save2File = new File(save2Name);
        File save3File = new File(save3Name);
        
        Path save1Path = save1File.toPath();
        Path save2Path = save2File.toPath();
        Path save3Path = save3File.toPath();
        
        String save1TimeStamp = "";
        String save2TimeStamp = "";
        String save3TimeStamp = "";
        
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm:ss");
        
        if(save1Exists) {
        	BasicFileAttributes fileAttrib = null;
    		try {
    			fileAttrib = Files.readAttributes(save1Path,
    			        BasicFileAttributes.class);
    		} catch (IOException e) {
    			System.out.println("Cannot read Save1 attributes!");
    			e.printStackTrace();
    		}

    		FileTime creationTime = fileAttrib.lastModifiedTime();
    		LocalDateTime localCreationTime = creationTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    		save1TimeStamp = localCreationTime.format(formatter);        	
        }
        else {
        	save1TimeStamp = "[EMPTY]";
        }
        
        if(save2Exists) {
        	BasicFileAttributes fileAttrib = null;
    		try {
    			fileAttrib = Files.readAttributes(save2Path,
    			        BasicFileAttributes.class);
    		} catch (IOException e) {
    			System.out.println("Cannot read Save2 attributes!");
    			e.printStackTrace();
    		}

    		FileTime creationTime = fileAttrib.lastModifiedTime();
    		LocalDateTime localCreationTime = creationTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    		save2TimeStamp = localCreationTime.format(formatter);        	
        }
        else {
        	save2TimeStamp = "[EMPTY]";
        }
        
        if(save3Exists) {
        	BasicFileAttributes fileAttrib = null;
    		try {
    			fileAttrib = Files.readAttributes(save3Path,
    			        BasicFileAttributes.class);
    		} catch (IOException e) {
    			System.out.println("Cannot read Save3 attributes!");
    			e.printStackTrace();
    		}

    		FileTime creationTime = fileAttrib.lastModifiedTime();
    		LocalDateTime localCreationTime = creationTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    		save3TimeStamp = localCreationTime.format(formatter);        	
        }
        else {
        	save3TimeStamp = "[EMPTY]";
        }
		
        //configure text for buttons
		save1ButtonText = "Slot 1: " +save1TimeStamp;
		save2ButtonText = "Slot 2: " +save2TimeStamp;
		save3ButtonText = "Slot 3: " +save3TimeStamp;
		
		//apply text to buttons
		if(loadGameSave1 != null) {
			loadGameSave1.setText(save1ButtonText);
		}
		if(loadGameSave2 != null) {
			loadGameSave2.setText(save2ButtonText);
		}
		if(loadGameSave3 != null) {
			loadGameSave3.setText(save3ButtonText);
		}
		
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
			selectionLabel.setText("");
			GameSettings.GetMainWindow().ShowPanel(GameSettings.GetMainMenu());
		}
		GameSettings.playButtonSound();
		buttons.forEach(button -> button.setForeground(Color.black));

	}

}
