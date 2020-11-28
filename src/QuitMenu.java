import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class QuitMenu extends JPanel implements ActionListener {

	JLabel quitHeaderLabel;
	JLabel quitTextLabel;
	JButton quitYesButton;
	JButton quitNoButton;

	// constructor
	public QuitMenu() {

		// build a panel
		super();

		// header label at the top
		// Are you sure message
		// yes and no buttons at the bottom

		// the quit panel
		this.setBounds(0, 0, 1200, 800);
		this.setOpaque(false);

		// the label for the header
		quitHeaderLabel = new JLabel("Quit Game");
		quitHeaderLabel.setFont(MainWindow.orbitron.deriveFont(72f));
		quitHeaderLabel.setForeground(Color.black);
		EmptyBorder border1 = new EmptyBorder(200, 0, 0, 0);
		quitHeaderLabel.setBorder(border1);
		quitHeaderLabel.setAlignmentX(CENTER_ALIGNMENT);
		quitHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		quitHeaderLabel.setVerticalAlignment(JLabel.CENTER);

		// "are you sure" message
		JPanel quitTextPanel = new JPanel();
		quitTextPanel.setPreferredSize(new Dimension(100, 100));
		EmptyBorder border2 = new EmptyBorder(100, 0, 25, 0);
		quitHeaderLabel.setBorder(border2);
		quitTextLabel = new JLabel("Are You Sure You Want to Quit?");
		quitTextLabel.setFont(MainWindow.orbitron.deriveFont(36f));
		quitTextLabel.setForeground(Color.black);
		quitTextLabel.setVerticalAlignment(JLabel.CENTER);
		quitTextPanel.add(quitTextLabel);
		quitTextPanel.setOpaque(false);

		// yes/no buttons and listeners
		JPanel buttonPanel = new JPanel();
		quitYesButton = new Buttons("Yes");
		quitYesButton.setFont(MainWindow.orbitron.deriveFont(24f));
		quitYesButton.addActionListener(this);
		quitNoButton = new Buttons("No");
		quitNoButton.setFont(MainWindow.orbitron.deriveFont(24f));
		quitNoButton.addActionListener(this);
		buttonPanel.add(quitYesButton);
		buttonPanel.add(quitNoButton);
		buttonPanel.setOpaque(false);

		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		this.setLayout(boxLayout);
		this.add(quitHeaderLabel);
		this.add(quitTextPanel);
		this.add(buttonPanel);
		// hide this panel
		this.setVisible(false);
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		// get the object that performed the action, respond accordingly
		Object selected = event.getSource();

		// was 'Yes' requested on quit panel?
		if (selected.equals(quitYesButton)) {
			GameSettings.playButtonSound();
			System.exit(0);
		}
		// was 'No' requested on quit panel?
		if (selected.equals(quitNoButton)) {
			GameSettings.GetMainWindow().ShowPanel(GameSettings.GetMainMenu());
		}
		GameSettings.playButtonSound();

	}

}
