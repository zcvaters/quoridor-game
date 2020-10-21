import java.awt.Color;

/* 
 * Player class.  2 Constructors.
 * 
 * This class should be able to build both human and computer players.
 * Once a game has started, there must always be 4 players in existance.
 * 
 * There are two constructors (one for requesting human player, one for AI player)
 * Player's are identified by a unique ID number (1-4)
 * Player's location will change as game progresses. 
 * 
 */

  
public class Player {
	
	//unique ID number (1-4)
	int turnPosition;
	//type of player, human ("h") or computer ("c")
	String playerType;
	//name of player, displayed on UI
	String playerName;
	//this player's color
	Color playerColor;
	//difficulty setting.  true means player is difficult/challenging.  false = easy.
	boolean playerIsDifficult;  //note:  human's have this set to "false/easy" by default (does not matter).
	
	//**this needs to be implemented.  
	int[][] playerLocation;  //stores a (row,col) address for the player.
	//the colour of the player
	
	
	
	int playerWallsRemaining;
	
	
	/* Constructor - Human Player (no difficulty level, default to easy)
	 * 
	 * @Params:		id = 	unique ID from 1-4.
	 * 				name = 	player's name
	 * 				type = 	human or computer?  accepts "h" or "c".
	 */
	public Player(int id, String type, String name, Color color) {
		
		//call other constructor, set isDifficult param to false
		this(id, type, name, color, false);
	}
	
	
	/* Constructor - AI Player (difficulty level can be overriden)
	 * 
	 * @Params:		turnPosition = 	unique ID from 1-4.  determines who starts. 1, then 2, then 3, etc...
	 * 				name = 	player's name
	 * 				type = 	human or computer?  accepts "h" or "c".
	 * 				isDifficult = 	difficulty level for AI play.  true = hard/challenging.  false = easy.
	 */
	public Player(int turnPosition, String type, String name, Color color, boolean isDifficult) {
		
		//set player attributes
		this.turnPosition = turnPosition;
		this.playerType = type;
		this.playerName = name;
		this.playerColor = color;
		this.playerIsDifficult = isDifficult;
		
		//new player in 4-player game.  Set walls avail to 5.
		this.playerWallsRemaining = 5;		
	}
	
	//Getters
	public int getTurnPosition() {
		return turnPosition;
	}
	
	public String getName() {
		return playerName;
	}

	public String getType() {
		return playerType;
	}
	
	public Color getColor() {
		return playerColor;
	}

	public boolean getIsDifficult() {
		return playerIsDifficult;
	}

	public int[][] getLocation() {
		return playerLocation;
	}
	
	//Setters
	public void setLocation(int[][] newLocation) {
		playerLocation = newLocation;
	}	

}
