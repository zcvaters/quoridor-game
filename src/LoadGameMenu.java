import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
		
		this.setBounds(0,0,1000,1000);
		this.setOpaque(false);
		
		//the label for the header
		loadGameHeaderLabel = new JLabel("Load Game");
		EmptyBorder border1 = new EmptyBorder(190, 40, 0,0 );
		loadGameHeaderLabel.setBorder(border1);
		loadGameHeaderLabel.setAlignmentX(CENTER_ALIGNMENT);
		loadGameHeaderLabel.setFont(new Font("Dialog", Font.BOLD, 35));
		loadGameHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		loadGameHeaderLabel.setVerticalAlignment(JLabel.CENTER);
		
		//placeholder for load game UI (CHANGE THIS!)
		loadGameTextLabel = new JLabel("Put Load Game here....");
		loadGameTextLabel.setPreferredSize(new Dimension(900, 500));
		loadGameTextLabel.setHorizontalAlignment(JLabel.CENTER);
		loadGameTextLabel.setVerticalAlignment(JLabel.CENTER);
		
		//the "back" button and listener
        JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		loadGameBackButton = new JButton("Back");
		buttonPanel.add(loadGameBackButton);
		//instructionsBackButton.setBorderPainted(false);
		//instructionsBackButton.setIcon(new ImageIcon(getClass().getResource("/Assets/back_button.png")));
		loadGameBackButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		//instructionsBackButton.setRolloverEnabled(true);
		//instructionsBackButton.setRolloverIcon(new ImageIcon(getClass().getResource("/Assets/selected_back_button.png")));
		//instructionsBackButton.setSelectedIcon(new ImageIcon(getClass().getResource("/Assets/selected_back_button.png")));
		loadGameBackButton.addActionListener(this);
			
		
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		this.setLayout(boxLayout);
        this.add(loadGameHeaderLabel);
		this.add(loadGameTextLabel);
		this.add(buttonPanel);
		//hide this panel
        this.setVisible(false);	
		
		
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
