import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class PlayerInfoDisplay extends JPanel{	
	
	// Constructor
	public PlayerInfoDisplay() {

		// get a JPanel, which we will add the 4 playerinfo sub-panels
		super();
		// set the layout for the playerInfo panel to include a 1x4 table (single
		// column)
		BoxLayout thisLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(thisLayout);
		this.setVisible(true);
		this.setOpaque(false);
		Border panelBorder = BorderFactory.createLineBorder(Color.BLACK, 8);
		this.setBorder(panelBorder);
		
		//add a title
		JLabel titleLabel = new JLabel("Quoridor");
		titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 50, 10));
		titleLabel.setBackground(Color.white);
		titleLabel.setFont(MainWindow.orbitron.deriveFont(50f));
		titleLabel.setForeground(Color.black);
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		titleLabel.setOpaque(true);
		this.add(titleLabel);

		// get the players
		Player[] players = GameSettings.getPlayers();

		// get the wall color that is being used (from one of the tiles)
		GameTile[][] tiles = GameSettings.getGameTiles();
		Color wallColor = tiles[1][1].getWallColor();

		// build the four player info panels (to be stored in 'this' panel)
		for (Player player : players) {
			// build player panel
			JPanel playerPanel = new JPanel();
			BoxLayout playerLayout = new BoxLayout(playerPanel, BoxLayout.Y_AXIS);
			playerPanel.setLayout(playerLayout);
			playerPanel.setOpaque(false);
			// label to hold name
			JLabel playerNameLabel = new JLabel(player.GetName());
			playerNameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			playerNameLabel.setBackground(player.GetColor());
			playerNameLabel.setFont(MainWindow.orbitron.deriveFont(15f));
			playerNameLabel.setForeground(Color.black);
			playerNameLabel.setAlignmentX(CENTER_ALIGNMENT);
			playerNameLabel.setOpaque(true);
			// subpanel to hold wall display for player
			JPanel playerWallPanel = new JPanel();
			playerWallPanel.setOpaque(false);
			// for each wall in player inventory, add a label to this panel (horizontally)
			for (int i = 0; i < player.GetWallsRemaining(); i++) {
				// CONFIGURE WALL ICON DISPLAY HERE
				JLabel wallLabel = new JLabel();
				wallLabel.setPreferredSize(new Dimension(50, 10));
				wallLabel.setBackground(wallColor);
				Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
				wallLabel.setBorder(border);
				wallLabel.setOpaque(true);
				playerWallPanel.add(wallLabel);
			}
			// player's subpanels have now been populated. add name and #walls panel
			playerPanel.add(playerNameLabel);
			playerPanel.add(playerWallPanel);
			// add players panel to 'this' panel
			this.add(playerPanel);
		}
	}
}
