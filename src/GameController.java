import java.util.ArrayList;

public class GameController {

	// cache ref to players and turn info
	Player[] allPlayers;
	// index for keeping track of turns for players (0-3)
	int nextPlayerIndex;
	Player currentPlayer;

	// store reference to collection of tiles
	GameTile[][] tiles;

	// the GameState which will store a snapshot of a game in progress
	GameState gameState;

	// constructor
	public GameController(InGameUIPanel inGameUIPanel, GameBoard gameBoard, Player[] players, int nextToPlay,
			boolean isNewGame) {

		// copy ref to GameSettings
		GameSettings.SetGameController(this);
		this.allPlayers = players;
		// For a new game, nextPlayer = 0. Then 1, 2, 3, 0, 1, 2, 3...
		this.nextPlayerIndex = nextToPlay;
		this.currentPlayer = allPlayers[nextPlayerIndex];
		setNextPlayer(nextToPlay);

		// cache the tiles
		this.tiles = gameBoard.GetGrid();
    //if this is a new game, place the players on the tiles.
    //otherwise players are already located on a tile.
		if (isNewGame) {
			// put the players in position on the board
			for (Player thisPlayer : players) {
				// get the assigned starting tile
				GameTile startTile = thisPlayer.GetTile();
				// put player on board by adding it to the tile for display
				startTile.AddPlayer(thisPlayer);
			}
		}

		// show the gameboard!
		GameSettings.GetMainWindow().ShowPanel(inGameUIPanel.getMainPanel());
		
		// advance to next turn, pass player list index as ref.
		AdvanceToNextTurn();	
	}

	// GETTERS and setters.  <---------------------------(MOVE THIS TO GAMESETTINGS!)
	public Player GetCurrentPlayer() {
		// return the player taking the current turn
		return currentPlayer;
	}
  
  public int getNextPlayer() {
		return this.nextPlayerIndex;
	}
	
	public void setNextPlayer(int nextPlayer) {
		this.nextPlayerIndex = nextPlayer;
	}
	
	public void AdvanceToNextTurn() {
		
		//before advancing, check to see if the current player has (just) won the game.
		if(currentPlayer.PlayerHasWon()) {
			//print a congratulations.  end game.
			System.out.println("Congratulations " +currentPlayer.GetName()+
								"!!\nYou are the winner!");
			
			//pop up a message for quit/new game here.
			
			//pause the game, lock the controls (activated when player clicks on message)
			GameSettings.SetGameIsPaused(true);
			return;
		}
		
		//get the next player to play
		Player nextPlayer = allPlayers[nextPlayerIndex];				
		//System.out.println("Advancing to next turn. Next player is " +nextPlayer.GetName());
		
		//loop through the tiles, deactivate all highlights
		for(int x = 0; x < GameSettings.GetRows(); x++) {
			for(int y = 0; y < GameSettings.GetCols(); y++) {				
				tiles[x][y].DeactivateTile();				
			}
		}		
		
		
		//pause the game, lock the controls (activated when player clicks on message)
		GameSettings.SetGameIsPaused(true);
		System.out.println("game paused? " +GameSettings.GetGameIsPaused());
		GameSettings.getInGameUIPanel().setMessageLabelText(nextPlayer.GetName() + ", it's your turn!");
		GameSettings.getInGameUIPanel().showMessagelabel();
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
		//GameSettings.getInGameUIPanel().hideMessageLabel();
		
		//get a structure to hold the gametiles that can be reached by player.
		ArrayList<GameTile> legalTiles = new ArrayList<GameTile>();
		
		//Handle AI here
		if(currentPlayer.GetType() == "c") {
			//this is a computer player
			legalTiles = FindLegalTiles(currentPlayer);
			ActivateLegalTiles(legalTiles);
			AIManager.PerformAITurn(currentPlayer, legalTiles);				
		}else {
			//this player is a human
			//allow tracking of input
			GameSettings.SetGameIsPaused(false);		
			//System.out.println("game paused? " +GameSettings.GetGameIsPaused());
			//System.out.println("Starting turn for " +currentPlayer.GetName());
			//it is now "currentPlayer"'s turn
			legalTiles = FindLegalTiles(currentPlayer);
			ActivateLegalTiles(legalTiles);
		}
	}
	
	private ArrayList<GameTile> FindLegalTiles(Player currentPlayer) {
		
		//move this to GameBoard class?
		
		//find a collection of tiles that the player can move to, and return them.
		
		ArrayList<GameTile> legalTiles = new ArrayList<GameTile>();
		
		//get the 2D array of gametiles
		GameTile[][] tiles = GameSettings.getGameTiles();
		
		//get the number of rows and cols on gameboard
		//we need this zero indexed, so subtract one.
		//int rows = GameSettings.GetRows() - 1; 
		//int cols = GameSettings.GetCols() - 1;
		//get the gameTile that the player is standing on		
		GameTile location = currentPlayer.GetTile();
		//get the x, y, address of this tile
		int rowAddress = location.GetXCoord();
		int colAddress = location.GetYCoord();		
		
		//System.out.println(currentPlayer.GetName() + " is on tile (" +rowAddress+ ", " +colAddress+ ")");
		
		//does player have north option?
		if(location.HasNorthTile() && !location.HasNorthWall()) {
			
			//get the north tile
			GameTile northTile = tiles[rowAddress-1][colAddress];			
			//see if another player is on north tile
			Boolean opponentOnNorth = (northTile.playerIsHere) ? true : false;
			if(opponentOnNorth) {
				//see if northTile has a north tile and no north wall
				//player can possibly jump over opponent
				if(northTile.HasNorthTile() && !northTile.HasNorthWall()) {
					GameTile jumpTile = tiles[rowAddress-2][colAddress];
					//if there is no player present on the jump tile, activate it
					if(!jumpTile.PlayerIsHere()) {
						legalTiles.add(jumpTile);						
					}					
				}
				else {
					//see if northTile has a north wall (player may move diagonally)
					if(northTile.HasNorthWall()) {
						GameTile cornerTile;
						//check north east corner
						if(northTile.HasEastTile() && !northTile.HasEastWall()) {
							cornerTile = tiles[rowAddress-1][colAddress+1];
							//if there is no player present on the corner tile, activate it
							if(!cornerTile.PlayerIsHere()) {
								legalTiles.add(cornerTile);
							}
						}
						//check north west corner
						if(northTile.HasWestTile() && !northTile.HasWestWall()) {
							cornerTile = tiles[rowAddress-1][colAddress-1];
							//if there is no player present on the corner tile, activate it
							if(!cornerTile.PlayerIsHere()) {
								legalTiles.add(cornerTile);
							}
						}
					}					
				}
			}else {
				//north tile is reachable, and is empty
				legalTiles.add(northTile);
			}			
		}
		
		//does player have east option?
		if(location.HasEastTile() && !location.HasEastWall()) {
			
			//get the east tile
			GameTile eastTile = tiles[rowAddress][colAddress+1];			
			//see if another player is on east tile
			Boolean opponentOnEast = (eastTile.playerIsHere) ? true : false;
			if(opponentOnEast) {
				//see if eastTile has a east tile and no east wall
				//player can possibly jump over opponent
				if(eastTile.HasEastTile() && !eastTile.HasEastWall()) {
					GameTile jumpTile = tiles[rowAddress][colAddress+2];
					//if there is no player present on the jump tile, activate it
					if(!jumpTile.PlayerIsHere()) {
						legalTiles.add(jumpTile);
					}					
				}
				else {
					//see if eastTile has a east wall (player may move diagonally)
					if(eastTile.HasEastWall()) {
						GameTile cornerTile;
						//check north east corner
						if(eastTile.HasNorthTile() && !eastTile.HasNorthWall()) {
							cornerTile = tiles[rowAddress-1][colAddress+1];
							//if there is no player present on the corner tile, activate it
							if(!cornerTile.PlayerIsHere()) {
								legalTiles.add(cornerTile);
							}
						}
						//check south east corner
						if(eastTile.HasSouthTile() && !eastTile.HasSouthWall()) {
							cornerTile = tiles[rowAddress+1][colAddress+1];
							//if there is no player present on the corner tile, activate it
							if(!cornerTile.PlayerIsHere()) {
								legalTiles.add(cornerTile);
							}
						}
					}					
				}
			}else {
				//east tile is reachable, and is empty
				legalTiles.add(eastTile);
			}			
		}
				
		//does player have south option?
		if(location.HasSouthTile() && !location.HasSouthWall()) {
			
			//get the south tile
			GameTile southTile = tiles[rowAddress+1][colAddress];			
			//see if another player is on south tile
			Boolean opponentOnSouth = (southTile.playerIsHere) ? true : false;
			if(opponentOnSouth) {
				//see if southTile has a south tile and no south wall
				//player can possibly jump over opponent
				if(southTile.HasSouthTile() && !southTile.HasSouthWall()) {
					GameTile jumpTile = tiles[rowAddress+2][colAddress];
					//if there is no player present on the jump tile, activate it
					if(!jumpTile.PlayerIsHere()) {
						legalTiles.add(jumpTile);
					}					
				}
				else {
					//see if southTile has a south wall (player may move diagonally)
					if(southTile.HasSouthWall()) {
						GameTile cornerTile;
						//check south east corner
						if(southTile.HasEastTile() && !southTile.HasEastWall()) {
							cornerTile = tiles[rowAddress+1][colAddress+1];
							//if there is no player present on the corner tile, activate it
							if(!cornerTile.PlayerIsHere()) {
								legalTiles.add(cornerTile);
							}
						}
						//check south west corner
						if(southTile.HasWestTile() && !southTile.HasWestWall()) {
							cornerTile = tiles[rowAddress+1][colAddress-1];
							//if there is no player present on the corner tile, activate it
							if(!cornerTile.PlayerIsHere()) {
								legalTiles.add(cornerTile);
							}
						}
					}					
				}
			}else {
				//north tile is reachable, and is empty
				legalTiles.add(southTile);
			}			
		}
				
		//does player have west option?
		if(location.HasWestTile() && !location.HasWestWall()) {
			
			//get the west tile
			GameTile westTile = tiles[rowAddress][colAddress-1];			
			//see if another player is on west tile
			Boolean opponentOnWest = (westTile.playerIsHere) ? true : false;
			if(opponentOnWest) {
				//see if westTile has a west tile and no west wall
				//player can possibly jump over opponent
				if(westTile.HasWestTile() && !westTile.HasWestWall()) {
					GameTile jumpTile = tiles[rowAddress][colAddress-2];
					//if there is no player present on the jump tile, activate it
					if(!jumpTile.PlayerIsHere()) {
						legalTiles.add(jumpTile);
					}					
				}
				else {
					//see if westTile has a west wall (player may move diagonally)
					if(westTile.HasWestWall()) {
						GameTile cornerTile;
						//check north west corner
						if(westTile.HasNorthTile() && !westTile.HasNorthWall()) {
							cornerTile = tiles[rowAddress-1][colAddress-1];
							//if there is no player present on the corner tile, activate it
							if(!cornerTile.PlayerIsHere()) {
								legalTiles.add(cornerTile);
							}
						}
						//check south west corner
						if(westTile.HasSouthTile() && !westTile.HasSouthWall()) {
							cornerTile = tiles[rowAddress+1][colAddress-1];
							//if there is no player present on the corner tile, activate it
							if(!cornerTile.PlayerIsHere()) {
								legalTiles.add(cornerTile);
							}
						}
					}					
				}
			}else {
				//west tile is reachable, and is empty
				legalTiles.add(westTile);
			}			
		}
		
		//return the collection of legal tiles
		return legalTiles;
	}
	
	private void ActivateLegalTiles(ArrayList<GameTile> reachableTiles) {
		for(GameTile tile : reachableTiles) {
			tile.ActivateTile();
		}
	}
}
