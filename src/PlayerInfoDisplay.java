import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class PlayerInfoDisplay extends JPanel{	
	
	// Constructor
	public PlayerInfoDisplay() {
		
		// get a JPanel, which we will add the 4 playerinfo sub-panels
		super();
		// set the layout for the playerInfo panel to include a 1x4 table (single
		// column)
		BorderLayout thisLayout = new BorderLayout();
		this.setLayout(thisLayout);
		this.setVisible(true);
		this.setOpaque(false);
		Border panelBorder = BorderFactory.createLineBorder(Color.BLACK, 8);
		this.setBorder(panelBorder);		
		
		//add a title
		JLabel titleLabel = new JLabel("Quoridor");
		titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 10, 50, 10));
		titleLabel.setBackground(Color.WHITE);
		titleLabel.setFont(MainWindow.orbitron.deriveFont(30f));
		titleLabel.setForeground(Color.black);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.CENTER);
		titleLabel.setOpaque(true);
		

		// get the players
		Player[] players = GameSettings.getPlayers();

		// get the wall color that is being used (from one of the tiles)
		GameTile[][] tiles = GameSettings.getGameTiles();
		Color wallColor = tiles[1][1].getWallColor();

		// build the four player info panels (to be stored in 'this' panel)
		for (Player player : players) {
			int playerTurnOrder = player.GetTurnPosition();
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
			JLabel blankSpaceLabel = new JLabel();
			blankSpaceLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
			// subpanel to hold wall display for player
			JPanel playerWallPanel = new JPanel();
			//set east/west players to have vertical wall layout
			//set north/south players to have horizontal wall layout			
			if(playerTurnOrder == 1 || playerTurnOrder == 3) {
				playerWallPanel.setLayout(new BoxLayout(playerWallPanel, BoxLayout.LINE_AXIS));
				playerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
			}
			else {
				playerWallPanel.setLayout(new BoxLayout(playerWallPanel, BoxLayout.LINE_AXIS));
				playerPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 10, 0));
			}
			playerWallPanel.setOpaque(false);
			
			// for each wall in player inventory, add a label to this panel (horizontally)
			for (int i = 0; i < player.GetWallsRemaining(); i++) {
				// CONFIGURE WALL ICON DISPLAY HERE
				JLabel wallLabel = new JLabel();
				wallLabel.setPreferredSize(new Dimension(10, 50));
				wallLabel.setMinimumSize(new Dimension(10, 50));
				wallLabel.setMaximumSize(new Dimension(10, 50));
				wallLabel.setAlignmentX(CENTER_ALIGNMENT);
				wallLabel.setBackground(wallColor);
				wallLabel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
				Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
				wallLabel.setBorder(border);				
				wallLabel.setOpaque(true);
				JLabel spacerLabel = new JLabel();
				spacerLabel.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));
				playerWallPanel.add(wallLabel);
				playerWallPanel.add(spacerLabel);
			}
			
			// player's subpanels have now been populated. add name and walls panel
			playerPanel.add(playerNameLabel);
			playerPanel.add(blankSpaceLabel);
			playerPanel.add(playerWallPanel);
			
			//add the title layer to the NORTH of border layout
			this.add(titleLabel, BorderLayout.CENTER);
			//add to border layout by switching on the player's position in array
			
			switch(playerTurnOrder) {
			  case 1:
				  this.add(playerPanel, BorderLayout.SOUTH);
			    break;
			  case 2:
				  this.add(playerPanel, BorderLayout.WEST);
			    break;
			  case 3:
				  this.add(playerPanel, BorderLayout.NORTH);
			    break;
			  case 4:
				  this.add(playerPanel, BorderLayout.EAST);
			    break;
			  default:
			    System.out.println("playerTurnOrder not found for PlayerInfoDisplay!");
			}			
		}
	}
	
	
	/*
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
	}*/
	
	
	
	
	
}
