import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
		
		//get a JPanel
		super();
		
		//size of player icon.  configure as necessary. keep smaller than game tile.
		int playerX = 20;
		int playerY = 20;
		
		//need to know size of the tile (player is displayed on top of tile).
		int tileWidth = GameSettings.getTileWidth();
		int tileHeight = GameSettings.getTileHeight();
		
		//set bounds for panel. configured to center the player on the tile.
		//params:  startX, startY, panelWidth, panelHeight.  
		this.setBounds((tileWidth-playerX)/2,(tileHeight-playerY)/2,playerX, playerY);
		
		//set panel background to player color
		this.setBackground(color);
		
		//create a label to display player's first initial
		//if no name provided, use "X"
		char playerInitial = (name.charAt(0) == '<') ? 'X' : name.charAt(0);		 
		JLabel nameLabel = new JLabel(Character.toString(playerInitial));		
	    
	    //set GridBagLayout with no constraints to have label in center.
	    this.setLayout(new GridBagLayout());
		this.add(nameLabel);
		
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

	public boolean IsDifficult() {
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
	
	public Boolean PlayerHasWon() {
		//check victory condition
		//initially false.  If player reached goal, set to true.
		Boolean playerHasWon = false;
		switch (playerGoal) {

		case "north":
			//has player reached top row?
			if(playerLocation.GetXCoord() == 0) {
				playerHasWon = true;
			}
			break;
					
		case "east":
			//has player reached east col?
			if(playerLocation.GetYCoord() == GameSettings.GetCols() - 1) {
				playerHasWon = true;
			}
			break;

		case "west":
			//has player reached west col?
			if(playerLocation.GetYCoord() == 0) {
				playerHasWon = true;
			}
			break;
		
		case "south":
			//has player reached bottom row?
			if(playerLocation.GetXCoord() == GameSettings.GetRows() - 1) {
				playerHasWon = true;
			}			
			break;
			
		default:
			System.out.println("Player goal is not assigned!!  Cannot check victory conditions on Player Class.");			
		}
		
		//return the result
		return playerHasWon;
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
