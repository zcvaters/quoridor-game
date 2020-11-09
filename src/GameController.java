
public class GameController {
	
	//cache ref to players and turn info
	Player[] allPlayers;
	//index for keeping track of turns for players (0-3)
	int nextPlayerIndex;
	Player currentPlayer;
	
	//store reference to collection of tiles
	GameTile[][] tiles;
	
	//the GameState which will store a snapshot of a game in progress
	GameState gameState;
	
	//constructor
	public GameController(InGameUIPanel inGameUIPanel, GameBoard gameBoard, Player[] players, int nextToPlay) {
		
		//copy ref to GameSettings
		GameSettings.SetGameController(this);
		this.allPlayers = players;
		//For a new game, nextPlayer = 0.  Then 1, 2, 3, 0, 1, 2, 3...
		this.nextPlayerIndex = nextToPlay;
		this.currentPlayer = allPlayers[nextPlayerIndex];
		
		//cache the tiles
		this.tiles = gameBoard.GetGrid();
		
		
						
		//put the players in position on the board
		for(Player thisPlayer : players) {
			//get the assigned starting tile
			GameTile startTile = thisPlayer.GetTile();
			//put player on board by adding it to the tile for display
			startTile.AddPlayer(thisPlayer);
		}	
		
		
		//show the gameboard!
		GameSettings.GetMainWindow().ShowPanel(inGameUIPanel);
		//advance to next turn, pass player list index as ref.
		//AdvanceToNextTurn();
	}
	
	//GETTERS
	public Player GetCurrentPlayer() {
		//return the player taking the current turn
		return currentPlayer;
	}
	
	/*
	public void AdvanceToNextTurn() {
		
		//get the next player to play
		Player nextPlayer = allPlayers[nextPlayerIndex];				
		System.out.println("Advancing to next turn. Next player is " +nextPlayer.GetName());
		
		//loop through the tiles, deactivate all highlights
		for(int x = 0; x < GameSettings.GetRows(); x++) {
			for(int y = 0; y < GameSettings.GetCols(); y++) {				
				tiles[x][y].DeactivateTile();				
			}
		}
		
		//pause the game, lock the controls (activated when player clicks on message)
		GameSettings.SetGameIsPaused(true);
		System.out.println("game paused? " +GameSettings.GetGameIsPaused());
		GameSettings.GetMainWindow().ShowMessage(nextPlayer.GetName() + ", it's your turn!");
		currentPlayer = nextPlayer;	
		
		//increment the nextPlayerIndex, so the turns will advance through different players
		nextPlayerIndex++;
		//if past end, loop back to start
		if(nextPlayerIndex >3) {
			nextPlayerIndex = 0;
		}
	}
	
	public void BeginTurn() {
		//remove the popup notification
		GameSettings.GetMainWindow().RemoveMessage();
		//allow tracking of input
		GameSettings.SetGameIsPaused(false);		
		System.out.println("game paused? " +GameSettings.GetGameIsPaused());
		System.out.println("Starting turn for " +currentPlayer.GetName());
		//it is now "currentPlayer"'s turn
		ActivateLegalTiles(currentPlayer);
		
		
	}
	
	private void ActivateLegalTiles(Player currentPlayer) {
		
		//move this to GameBoard class?
		
		//get the 2D array of gametiles
		GameTile[][] tiles = GameSettings.getGameTiles();
		
		//get the number of rows and cols on gameboard
		//we need this zero indexed, so subtract one.
		int rows = GameSettings.GetRows() - 1; 
		int cols = GameSettings.GetCols() - 1;
		//get the gameTile that the player is standing on		
		GameTile location = currentPlayer.GetTile();
		//get the x, y, address of this tile
		int rowAddress = location.GetXCoord();
		int colAddress = location.GetYCoord();		
		
		System.out.println(currentPlayer.GetName() + " is on tile (" +rowAddress+ ", " +colAddress+ ")");
		
		//does player have north?
		if(location.HasNorth()) {
			tiles[rowAddress-1][colAddress].ActivateTile();
		}
		//does player have east?
		if(location.HasEast()) {
			tiles[rowAddress][colAddress+1].ActivateTile();
		}
		//does player have south?
		if(location.HasSouth()) {
			tiles[rowAddress+1][colAddress].ActivateTile();
		}
		//does player have west?
		if(location.HasWest()) {
			tiles[rowAddress][colAddress-1].ActivateTile();
		}
		
		
		
	}
	
	*/
	
	

}
