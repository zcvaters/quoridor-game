import java.awt.Color;
import java.util.ArrayList;

/*
 * The GameState holds a list of assets and attributes that define a game in progress (possibly just starting). 
 */



//TESTING GITHUB.  EDITED FILE!!  DELETE THIS COMMENT!



public class GameState {
	
	//can be 1, 2, or 3.  
	//Provides reference to which colour set to use for tiles/walls/player
	//int gameColorsId;
	
	//dont need colors?  players and tiles will have their own color attribs!
	
	//no Turn Order. 1,2,3,4...1,2,3,4...
	
	//list of player objects.  Must contain 4 Players.
	Player[] players;
	//the tile set that the game is using
	GameTile[][] gameTiles;
	//the number of turn that have been played
	int turnCount;
	//the player who has the next turn (playerID 1, if a new game)
	Player nextPlayer;	
	
	
	/* Constructor - GameState (all assets needed to start/resume game)
	 * 
	 * @Params:		players = 	a collection of 4 pre-built players 
	 * 				gameTiles = 	human or computer?  accepts "h" or "c".
	 * 				isDifficult = 	difficulty level for AI play.  true = hard/challenging.  false = easy.
	 */	
	public GameState(Color tileColor, Color wallColor,  int turnNum, Player next) {
		
		///FIX THIS UP!
		
		
		
	}		

	public Player[] getPlayers() {
		return players;
	}	

	public GameTile[][] getGameTiles() {
		return gameTiles;
	}	

	public int getTurnCount() {
		return turnCount;
	}	

	public Player getNextPlayer() {
		return nextPlayer;
	}
	

	public void setPlayers(Player[] players) {
		this.players = players;
	}
	
	public void setGameTiles(GameTile[][] gameTiles) {
		this.gameTiles = gameTiles;
	}
	
	public void setTurnCount(int turnCount) {
		this.turnCount = turnCount;
	}
	
	public void setNextPlayer(Player nextPlayer) {
		this.nextPlayer = nextPlayer;
	}
}


