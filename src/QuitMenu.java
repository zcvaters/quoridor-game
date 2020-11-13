import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		this.setBounds(0, 0, 1000, 1000);
		this.setOpaque(false);

		// the label for the header
		quitHeaderLabel = new JLabel("Quit Game");
		quitHeaderLabel.setFont(MainWindow.orbitron.deriveFont(72f));
		quitHeaderLabel.setForeground(Color.black);
		EmptyBorder border1 = new EmptyBorder(190, 0, 0, 0);
		quitHeaderLabel.setBorder(border1);
		quitHeaderLabel.setAlignmentX(CENTER_ALIGNMENT);
		quitHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		quitHeaderLabel.setVerticalAlignment(JLabel.CENTER);

		// "are you sure" message
		JPanel quitTextPanel = new JPanel();
		quitTextPanel.setPreferredSize(new Dimension(100, 100));
		EmptyBorder border2 = new EmptyBorder(200, 0, 50, 0);
		quitHeaderLabel.setBorder(border2);
		quitTextLabel = new JLabel("Are You Sure You Want to Quit?");
		quitTextLabel.setFont(MainWindow.orbitron.deriveFont(36f));
		quitTextLabel.setForeground(new Color(89, 44, 28));
		quitTextLabel.setHorizontalAlignment(JLabel.CENTER);
		quitTextLabel.setVerticalAlignment(JLabel.CENTER);
		quitTextPanel.add(quitTextLabel);
		quitTextPanel.setOpaque(false);

		// yes/no buttons and listeners
		JPanel buttonPanel = new JPanel();
		quitYesButton = new JButton("Yes");
		quitYesButton.setFont(MainWindow.orbitron.deriveFont(24f));
		quitYesButton.setForeground(Color.black);
		quitYesButton.setOpaque(false);
		quitYesButton.setBorderPainted(false);
		quitYesButton.setContentAreaFilled(false);
		buttonHoverAction(quitYesButton);
		quitYesButton.addActionListener(this);
		quitNoButton = new JButton("No");
		quitNoButton.setFont(MainWindow.orbitron.deriveFont(24f));
		quitNoButton.setForeground(Color.black);
		quitNoButton.setOpaque(false);
		quitNoButton.setBorderPainted(false);
		quitNoButton.setContentAreaFilled(false);
		buttonHoverAction(quitNoButton);
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
