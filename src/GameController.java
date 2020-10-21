
public class GameController {
	
	//the GameState which provide info for this controller
	GameState gameState;
	
	/* Constructor - GameController (the manager responsible for general game play.)
	 * 
	 * @Params:
	 * 		
	 */	
	public GameController(GameBoard board, Player[] players, int nextTurn) {
		
		
		//debugging, delete this stuff
		GameTile[][] tiles = board.GetGrid();
		
		System.out.println("Board is " +tiles[0].length+ "x" +tiles[1].length+ "\n");
		for(int i = 0; i < players.length; i++) {
			System.out.println("Name: "+ players[i].getName()+ 
							   "\nType: " +players[i].getType()+
							   "\nColor: " +players[i].getColor()+
							   "\nOrder: " +players[i].getTurnPosition()+ "\n");
		}
		System.out.println("Next Turn is " +nextTurn);
		System.out.println("Next Player is " +players[nextTurn].getName());
		//
		//
		
		//show the gameboard!
		GameSettings.GetMainWindow().ShowPanel(board);
		GameSettings.GetMainWindow().pack();		
	}

}
