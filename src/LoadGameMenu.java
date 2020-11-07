import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoadGameMenu extends JPanel implements ActionListener{

	//load game
	JLabel loadGameHeaderLabel;
	JLabel loadGameTextLabel;
	JButton loadGameBackButton;	
	
	public LoadGameMenu() {
		
		//build a panel
		super();
		
		//header label at the top
		//load game stuff in the center
		//button "Back" at the bottom
					
		this.setBackground(new Color(255, 255, 255));
		
		//the label for the header
		loadGameHeaderLabel = new JLabel("LOAD GAME");
		loadGameHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		loadGameHeaderLabel.setVerticalAlignment(JLabel.CENTER);
		
		//placeholder for load game UI (CHANGE THIS!)
		loadGameTextLabel = new JLabel("Put Load Game here....");
		loadGameTextLabel.setHorizontalAlignment(JLabel.CENTER);
		loadGameTextLabel.setVerticalAlignment(JLabel.CENTER);
		
		//the "back" button and listener
		loadGameBackButton = new JButton("Back");
		loadGameBackButton.addActionListener(this);
		
		//add components to the panel
		this.setLayout(new BorderLayout());
		this.add(loadGameHeaderLabel, BorderLayout.NORTH);
		this.add(loadGameTextLabel, BorderLayout.CENTER);		
		this.add(loadGameBackButton, BorderLayout.SOUTH);		
		//hide this panel
		this.setVisible(true);		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		//get the object that performed the action, respond accordingly		
		Object selected = event.getSource();
		
		//was 'back' requested on load game panel?
		if(selected.equals(loadGameBackButton)) {
			GameSettings.GetMainWindow().ShowPanel(GameSettings.GetMainMenu());
		}
		
	}

}
