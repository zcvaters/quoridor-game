import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InGameMessagePanel extends JPanel implements ActionListener {
	
	private JLabel messageLabel;
	private JButton okButton;
	
	public InGameMessagePanel() {
		
		//get a panel
		super();
		//set size
		Dimension panelSize = new Dimension(200, 100);
        this.setPreferredSize(panelSize);
        this.setBounds(725,625,400,100);
        this.setOpaque(false);
        
        //get text
        String messageText = "Default message text.";
        messageLabel = new JLabel(messageText);
        EmptyBorder border1 = new EmptyBorder(20, 0, 20,0 );
		messageLabel.setBorder(border1);
        messageLabel.setAlignmentX(CENTER_ALIGNMENT);
        //get button
        okButton = new JButton(" OK ");
        okButton.setAlignmentX(CENTER_ALIGNMENT);
        okButton.addActionListener(this);
        
        //add text and button
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		this.setLayout(boxLayout);
        this.add(messageLabel);
        this.add(okButton);
        //hide panel
        this.setVisible(false);
	}
	
	public void SetMessageText(String message) {
		messageLabel.setText(message);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		//get the object that performed the action, respond accordingly		
		Object selected = event.getSource();
		
		//did user click "ok" on message panel?
		if(selected.equals(okButton)) {			
			//gameplay can continue
			GameSettings.GetGameController().BeginTurn();
		}
		
	}

}
