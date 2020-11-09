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
public class Player extends JPanel{
	
	//unique ID number (1-4)
	private int turnPosition;
	//type of player, human ("h") or computer ("c")
	private String playerType;
	//name of player, displayed on UI
	private String playerName;
	//this player's color
	private Color playerColor;
	//difficulty setting.  true means player is difficult/challenging.  false = easy.
	private boolean playerIsDifficult;  //note:  human's have this set to "false/easy" by default (does not matter).
		
	private GameTile playerLocation;  //stores the tile player is currently on.
	
	private int playerWallsRemaining;
	
	//player is trying to reach north, east, south, or west side of board.
	private String playerGoal;
		
	/* Constructor 
	 * 
	 * Player can be human or AI (computer)
	 * Player can have difficulty.  Computer = hard or easy.   Human = doesn't matter, not applied.
	 * 
	 * @Params:		turnPosition = 	unique ID from 1-4.  determines who starts. 1,2,3,4,  1,2,3,4   etc...
	 * 				name = 	player's name
	 * 				type = 	human or computer?  accepts "h" or "c".
	 * 				isDifficult = 	difficulty level for AI play.  true = hard/challenging.  false = easy.
	 */
	public Player(int turnPosition, GameTile playerLocation, String type, String name, Color color, boolean isDifficult) {
		
		//set player attributes
		this.turnPosition = turnPosition;
		this.playerLocation = playerLocation;
		SetPlayerGoal(playerLocation);
		this.playerType = type;
		this.playerName = name;
		this.playerColor = color;
		this.playerIsDifficult = isDifficult;
		
		//new player in 4-player game.  Set walls avail to 5.
		this.playerWallsRemaining = 5;		
	}
	
	//Getters
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
	
	public int GetWallsRemaining() {
		return playerWallsRemaining;
	}
	
	public String GetPlayerGoal() {
		//returns north, east, south, or west
		return playerGoal;
	}
	
	//Setters
	public void setTile(GameTile newLocation) {
		playerLocation = newLocation;
	}
	
	public void setWallsRemaining(int numWalls) {
		playerWallsRemaining = numWalls;
	}
	
	//HELPERS
	private void SetPlayerGoal(GameTile startTile) {
		//if the player is on the bottom, their goal is "north"
		//if player is on left, goal is "east"
		//if player is on top, goal is "south"
		//if player is on right, goal is "west"
		
		if(startTile.GetXCoord() == GameSettings.GetRows()-1) {
			//player is on bottom, moving north
			playerGoal = "north";
		}
		if(startTile.GetXCoord() == 0) {
			//player is on top, moving south
			playerGoal = "south";
		}
		if(startTile.GetYCoord() == GameSettings.GetCols()-1) {
			//player is on right, moving west
			playerGoal = "west";
		}
		if(startTile.GetYCoord() == 0) {
			//player is on left, moving east
			playerGoal = "east";
		}
		
	}

}
