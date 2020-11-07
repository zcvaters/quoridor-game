import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuitMenu extends JPanel implements ActionListener{

	JLabel quitHeaderLabel;
	JLabel quitTextLabel;
	JButton quitYesButton;
	JButton quitNoButton;
	
	//constructor
	public QuitMenu() {
		
		//build a panel
		super();
		
		//header label at the top
		//Are you sure message
		//yes and no buttons at the bottom
		
		//the quit panel		
		this.setBackground(new Color(255, 255, 255));
		
		//the label for the header
		quitHeaderLabel = new JLabel("QUIT GAME");
		quitHeaderLabel.setBounds(0, 0, 1000, 89);
		quitHeaderLabel.setForeground(new Color(51, 51, 51));
		quitHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		quitHeaderLabel.setVerticalAlignment(JLabel.CENTER);
		
		//"are you sure" message
		quitTextLabel = new JLabel("Are You Sure You Want to Quit?");
		quitTextLabel.setBounds(0, 130, 1000, 138);
		quitTextLabel.setHorizontalAlignment(JLabel.CENTER);
		quitTextLabel.setVerticalAlignment(JLabel.CENTER);
		
		//yes/no buttons and listeners
		quitYesButton = new JButton("Yes");
		quitYesButton.setBounds(150, 400, 700, 200);
		quitYesButton.addActionListener(this);
		quitNoButton = new JButton("No");
		quitNoButton.setBounds(150, 615, 700, 200);
		quitNoButton.addActionListener(this);
		
		this.setLayout(null);
		this.add(quitHeaderLabel);
		this.add(quitTextLabel);
		this.add(quitYesButton);
		this.add(quitNoButton);		
		//hide this panel
		this.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		//get the object that performed the action, respond accordingly		
		Object selected = event.getSource();
		
		//was 'Yes' requested on quit panel?
		if(selected.equals(quitYesButton)) {
			System.exit(0);
		}
		//was 'No' requested on quit panel?
		if(selected.equals(quitNoButton)) {
			GameSettings.GetMainWindow().ShowPanel(GameSettings.GetMainMenu());
		}	
		
	}

}
