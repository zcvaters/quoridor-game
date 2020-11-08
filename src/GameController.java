
public class GameController {
	
	//the GameState which will store a snapshot of a game in progress
	GameState gameState;
	
	/* Constructor - GameController (the manager responsible for general game play.)
	 * 
	 * @Params:
	 * 		
	 */	

	public GameController(InGameUIPanel inGameUIPanel, GameBoard gameBoard, Player[] players, int nextTurn) {
		//debugging, delete this stuff
		GameTile[][] tiles = gameBoard.GetGrid();
		
		System.out.println("Board is " +tiles[0].length+ "x" +tiles[1].length+ "\n");
		for(int i = 0; i < players.length; i++) {
			System.out.println("Name: "+ players[i].GetName()+ 
							   "\nType: " +players[i].GetType()+
							   "\nColor: " +players[i].GetColor()+
							   "\nOrder: " +players[i].GetTurnPosition()+
							   "\nGameTile: (" +(players[i].GetTile().GetXCoord()+1) + 
							   				", "+(players[i].GetTile().GetYCoord()+1) +  ")\n");
			
		}
		System.out.println("Next Turn is " +nextTurn);
		System.out.println("Next Player is " +players[nextTurn].GetName());
		//
		//
		
		//put the players in position on the board
		for(Player thisPlayer : players) {
			//get the assigned starting tile
			GameTile startTile = thisPlayer.GetTile();
			//put player on board by adding it to the tile for display
			startTile.AddPlayer(thisPlayer);
		}	
		
		
		//show the gameboard!
		GameSettings.GetMainWindow().ShowPanel(inGameUIPanel);			
	}

}
