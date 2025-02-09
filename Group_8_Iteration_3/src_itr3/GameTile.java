import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * 
 * This class is used to construct one individual panel of the game board. A
 * game board contains rows x cols of these GameTiles.
 * 
 * Each tile is square, and has a center panel (where the player moves) It also
 * has 4 borders (walls) on the N,S,E,W. Finally, it has 4 small corner panels
 * at NE, NW, SE, SW
 * 
 * Walls are implemented by activating the necessary border panels between
 * tiles.
 * 
 * EX: Suppose a tile arrangement like this
 * 
 * 1 2 3 4
 * 
 * Turning on a vertical wall beween these tiles requires activation of: - 1's
 * east border - 1's SE border - 3's NE border - 3's E border - 2's W border -
 * 2's SW border - 4's NW border - 4's W border
 * 
 * - so 8 panels necessary for a wall spanning 2 tiles.
 */

public class GameTile extends JPanel implements MouseListener, MouseMotionListener {

	// store ref to the inputManager. Processes mouse events from this object.
	private InputManager inputManager;

	// the colors for the center tile and it's walls
	private Color tileColor;
	private Color tileHighlightColor;
	private Color wallColor;
	private Color bkgColor;

	// this tile's position on the grid.
	// these are 0-indexed. top left = (0,0) bottom right = (rows, cols)
	private int xcoord;
	private int ycoord;

	// the width and height of this tile
	private int width;
	private int height;
	// this object will contain a center tile panel, which is surrounded on NSEW by
	// wall panels.
	// additional panels for 4 corners
	// combine these in a grid to make a 3x3 square tile.
	// TL T TR
	// L C R
	// BL B BR
	private JPanel topLeftWall;
	private JPanel topWall;
	private JPanel topRightWall;
	private JPanel leftWall;
	private JLayeredPane tile; // <--- center tile. Player moves on these.
	private JPanel rightWall;
	private JPanel bottomLeftWall;
	private JPanel bottomWall;
	private JPanel bottomRightWall;

	// this var will hold the panel for whichever player is standing on the tile
	JPanel playerPanel;

	// container for holding any wall borders that player has legally placed in
	// play.
	ArrayList<JPanel> lockedWalls;

	// using gridBagLayout for 9 panels above
	private GridBagConstraints gbc = new GridBagConstraints();

	// constructor
	public GameTile(Color tileColor, Color wallColor, Color bkgColor, int xcoord, int ycoord) {
		super(); // create a JPanel

		// Colors
		// the color of the center tiles (player moves on these)
		this.tileColor = tileColor;
		// the color of an activated wall border
		this.wallColor = wallColor;
		// the color of a non-active wall border
		this.bkgColor = bkgColor;
		// the color of a center tile that has been highlighted (player could move to
		// these)
		this.tileHighlightColor = new Color(200, 0, 100); // <-----FIX THIS!

		// zero-indexed.
		this.xcoord = xcoord; // row address
		this.ycoord = ycoord; // column address

		// remove this!! Testing only.
		// Color[] playerColors = GameSettings.GetPlayerColors(1);
		// int rnd = new Random().nextInt(playerColors.length);
		// this.setBackground(playerColors[rnd]);

		// cache ref to the InputManager
		inputManager = GameSettings.GetInputManager();

		// the list that will contain any "locked-in" borders for this tile.
		// gets added to as player places walls in play.
		// locked panels cannot be deactivated by method below.
		lockedWalls = new ArrayList<JPanel>();

		// build square tile, as a 9 cell grid.
		// square center tile, with one wall panel on each side, additional 4 panels at
		// corners. 9 total.
		this.setLayout(new GridBagLayout());

		// set the width/height of the center tile, find the center point
		this.width = GameSettings.getTileWidth();
		this.height = GameSettings.getTileHeight();
		// stretch gridbag layout as necessary to accomodate height/width
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;

		topLeftWall = new JPanel();
		topLeftWall.setName("topLeftWall");
		topLeftWall.setBackground(bkgColor);
		topLeftWall.setOpaque(true);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		topLeftWall.addMouseListener(this);
		topLeftWall.addMouseMotionListener(this);
		this.add(topLeftWall, gbc);

		topWall = new JPanel();
		topWall.setName("topWall");
		topWall.setBackground(bkgColor);
		topWall.setOpaque(true);
		gbc.gridx = 5;
		gbc.gridy = 0;
		gbc.gridwidth = 50;
		gbc.gridheight = 5;
		topWall.addMouseListener(this);
		topWall.addMouseMotionListener(this);
		this.add(topWall, gbc);

		topRightWall = new JPanel();
		topRightWall.setName("topRightWall");
		topRightWall.setBackground(bkgColor);
		topRightWall.setOpaque(true);
		gbc.gridx = 55;
		gbc.gridy = 0;
		gbc.gridwidth = 5;
		gbc.gridheight = 5;
		topRightWall.addMouseListener(this);
		topRightWall.addMouseMotionListener(this);
		this.add(topRightWall, gbc);

		leftWall = new JPanel();
		leftWall.setName("leftWall");
		leftWall.setBackground(bkgColor);
		leftWall.setOpaque(true);
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 5;
		gbc.gridheight = 50;
		leftWall.addMouseListener(this);
		leftWall.addMouseMotionListener(this);
		this.add(leftWall, gbc);

		// CENTER TILE IS A SPECIAL CASE
		// it's a JLayeredPane, allowing multiple panels to be stacked here
		// useful for displaying the "player" panel on the center tile.
		// CENTER TILE IS A SPECIAL CASE!
		// it uses JLayeredPane to display the player panel (if player is here)
		tile = new JLayeredPane();
		tile.setPreferredSize(new Dimension(width, height));
		tile.setBounds(0, 0, width, height); // for layers
		playerPanel = new JPanel(); // <-- empty placeholder. players will be swapped in here later.
		tile.add(playerPanel);
		tile.setName("tile");
		tile.setBackground(tileColor);
		tile.setOpaque(true);
		gbc.gridx = 5;
		gbc.gridy = 5;
		this.add(tile, gbc);

		rightWall = new JPanel();
		rightWall.setName("rightWall");
		rightWall.setBackground(bkgColor);
		rightWall.setOpaque(true);
		gbc.gridx = 55;
		gbc.gridy = 5;
		gbc.gridwidth = 5;
		gbc.gridheight = 50;
		rightWall.addMouseListener(this);
		rightWall.addMouseMotionListener(this);
		this.add(rightWall, gbc);

		bottomLeftWall = new JPanel();
		bottomLeftWall.setName("bottomLeftWall");
		bottomLeftWall.setBackground(bkgColor);
		bottomLeftWall.setOpaque(true);
		gbc.gridx = 0;
		gbc.gridy = 55;
		gbc.gridwidth = 5;
		gbc.gridheight = 5;
		bottomLeftWall.addMouseListener(this);
		bottomLeftWall.addMouseMotionListener(this);
		this.add(bottomLeftWall, gbc);

		bottomWall = new JPanel();
		bottomWall.setName("bottomWall");
		bottomWall.setBackground(bkgColor);
		bottomWall.setOpaque(true);
		gbc.gridx = 5;
		gbc.gridy = 55;
		gbc.gridwidth = 50;
		gbc.gridheight = 5;
		bottomWall.addMouseListener(this);
		bottomWall.addMouseMotionListener(this);
		this.add(bottomWall, gbc);

		bottomRightWall = new JPanel();
		bottomRightWall.setName("bottomRightWall");
		bottomRightWall.setBackground(bkgColor);
		bottomRightWall.setOpaque(true);
		gbc.gridx = 55;
		gbc.gridy = 55;
		gbc.gridwidth = 5;
		gbc.gridheight = 5;
		bottomRightWall.addMouseListener(this);
		bottomRightWall.addMouseMotionListener(this);
		this.add(bottomRightWall, gbc);
	}

	// GETTERS
	public int GetXCoord() {
		return xcoord;
	}

	public int GetYCoord() {
		return ycoord;
	}

	public JPanel GetTopLeft() {
		return topLeftWall;
	}

	public JPanel GetTop() {
		return topWall;
	}

	public JPanel GetTopRight() {
		return topRightWall;
	}

	public JPanel GetLeft() {
		return leftWall;
	}

	public JPanel GetCenterTile() { // <-----getter for the center of this GameTile (player stands here)
		return playerPanel;
	}

	public JPanel GetRight() {
		return rightWall;
	}

	public JPanel GetBottomLeft() {
		return bottomLeftWall;
	}

	public JPanel GetBottom() {
		return bottomWall;
	}

	public JPanel GetBottomRight() {
		return bottomRightWall;
	}

	public ArrayList<JPanel> GetLockedWalls() {
		return lockedWalls;
	}

	public Color getWallColor() {
		return this.wallColor;
	}

	public Color getTileColor() {
		return this.tileColor;
	}

	public Color getBkgColor() {
		return this.bkgColor;
	}

	// CHANGE WALL BORDER COLORS (called by Input Manager on mouse over)
	public void ActivateWallPanel(JPanel panelToDisplay) {
		if (panelToDisplay.getBackground() == bkgColor) {
			panelToDisplay.setBackground(wallColor);
		}
	}

	public void DeactivateWallPanel(JPanel panelToRemove) {
		// cannot deactivate locked walls
		if (lockedWalls.contains(panelToRemove)) {
			return;
		}

		if (panelToRemove.getBackground() == wallColor) {
			panelToRemove.setBackground(bkgColor);
		}
	}

	// LOCK CHANGES TO TILE BORDERS
	public void LockWall(JPanel wallToLock) {
		// this method adds this panel to a list of "locked" panels.
		// before activation, deactivation they are checked and color cannot be altered.

		// if the panel is not already on the list, lock it.
		if (!lockedWalls.contains(wallToLock)) {
			lockedWalls.add(wallToLock);
			// System.out.println("Locking " +wallToLock.getName());
		} else {
			// do anything here?
		}
	}

	// MOUSE LISTENER FOR MOVEMENT (not clicking!)
	// tracks "mouseover" events (temporary placing of walls, or temporary move
	// player)
	// selections are locked-in by clicking if user placement/movement is a legal
	// move.

	@Override
	public void mouseMoved(MouseEvent e) {

		// get the sub-panel that detected the movement
		// get the position of the mouse with respect to this sub panel.
		JPanel thisPanel = (JPanel) e.getComponent();
		Point point = e.getPoint();

		// System.out.println("On tile (" +xcoord+ ", " +ycoord+
		// ") At position (" +point.x+ ", " +point.y+
		// ") On panel (" +thisPanel.getName()+ ")");

		// send to InputManager for handling
		inputManager.TrackMouseMovement(thisPanel, point);
	}

	// setters
	public void AddPlayer(Player player) {

		// SEE ALSO RemovePlayer BELOW!

		// size of player icon. move this somewhere else.
		int playerX = 20;
		int playerY = 20;

		// note: playerPanel is defined as a JPanel, it's ok because Player extends
		// JPanel.
		playerPanel = player;
		playerPanel.setBackground(player.GetColor());
		playerPanel.setOpaque(true);
		// params: startX, startY, panelWidth, panelHeight. configured to center the
		// player on the tile.
		playerPanel.setBounds((width - playerX) / 2, (height - playerY) / 2, playerX, playerY);
		tile.add(playerPanel);

	}

	public void RemovePlayer() {
		tile.remove(playerPanel);
	}

	public void TurnOnCenterTile() {
		if (tile.getBackground() == tileColor) { // <--------- FIX THIS!
			tile.setBackground(tileHighlightColor);
		}
	}

	public void TurnOffCenterTile() {
		if (tile.getBackground() == tileHighlightColor) { // <--------- FIX THIS!
			tile.setBackground(tileColor);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// inputManager.ClearTemporaryWalls();
		JPanel thisPanel = (JPanel) e.getComponent();
		if (thisPanel.getName().equals("tile")) {
			this.TurnOnCenterTile();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {

		// inputManager.ClearTemporaryWalls();
		JPanel thisPanel = (JPanel) e.getComponent();
		if (thisPanel.getName().equals("tile")) {
			this.TurnOffCenterTile();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		JPanel thisPanel = (JPanel) e.getComponent();

		inputManager.HandleMouseClick(thisPanel);
		// Activate place (lock?) walls, or move player, here.
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
