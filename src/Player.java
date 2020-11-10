import java.awt.Color;
import javax.swing.JPanel;

/* 
 * Player class.  
 * 
 * This class should be able to build both human and computer players.
 * Once a game has started, there must always be 4 players in existance.
 *  
 * Player's are identified by a unique ID number (1-4)
 * Player's location (a GameTile) will change as game progresses. 
 * 
 */

//the player object is a panel!
public class Player extends JPanel {

	// unique ID number (1-4)
	int turnPosition;
	// type of player, human ("h") or computer ("c")
	String playerType;
	// name of player, displayed on UI
	String playerName;
	// this player's color
	Color playerColor;
	// difficulty setting. true means player is difficult/challenging. false = easy.
	boolean playerIsDifficult; // note: human's have this set to "false/easy" by default (does not matter).

	GameTile playerLocation; // stores the tile player is currently on.

	int playerWallsRemaining;

	/*
	 * Constructor
	 * 
	 * Player can be human or AI (computer) Player can have difficulty. Computer =
	 * hard or easy. Human = doesn't matter, not applied.
	 * 
	 * @Params: turnPosition = unique ID from 1-4. determines who starts. 1,2,3,4,
	 * 1,2,3,4 etc... name = player's name type = human or computer? accepts "h" or
	 * "c". isDifficult = difficulty level for AI play. true = hard/challenging.
	 * false = easy.
	 */
	public Player(int turnPosition, GameTile playerLocation, String type, String name, Color color,
			boolean isDifficult) {

		// set player attributes
		this.turnPosition = turnPosition;
		this.playerLocation = playerLocation;
		this.playerType = type;
		this.playerName = name;
		this.playerColor = color;
		this.playerIsDifficult = isDifficult;

		// new player in 4-player game. Set walls avail to 5.
		this.playerWallsRemaining = 5;
	}

	// Getters
	public int GetTurnPosition() {
		return turnPosition;
	}

	public String GetName() {
		return playerName;
	}

	public String GetType() {
		return playerType;
	}

	public Color GetColor() {
		return playerColor;
	}

	public boolean GetIsDifficult() {
		return playerIsDifficult;
	}

	public GameTile GetTile() {
		return playerLocation;
	}

	// Setters
	public void setTile(GameTile newLocation) {
		playerLocation = newLocation;
	}

}
