import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/*
 * BuildGame class.  This game is the factory for building the necessary game assets for a GameState.  
 * 
 * Responsible for taking input from either of TWO sources.
 * 
 * Option 1:  	Build a game from "new game" in main menu.  
 * 				Use new assets provided by menu to build a Game State.  
 * 
 * Option 2:  	Build a game from "load game" save file.
 * 				Use previous assets collected from save file to rebuild the previous GameState.
 * 
 */

public class BuildAssets{
	
	//size of grid. set in const'.
	int rows;
	int cols;
	
	//the colors to be used    (necessary to store here? not!?)
	private Color tileColor;
	private Color wallColor;
	private Color bkgColor;
	
	
	//ref to the object that will handle user input
	InputManager inputManager;
	
	
	//
	//  NEW GAME Constructor	
	public BuildAssets(Color tileColor, 
					Color wallColor,
					Color bkgColor,
					ArrayList<String> playerNames, 
					ArrayList<String> playerTypes,
					Color[] playerColors,
					List<Integer> turnOrder) {
		
		//in order to generate a GameState we need 
		//a board (has colors, size, quantity of tiles, etc, attached)
		//4 players (have name, color, etc attached)
		//an indication of which is the next player.  (player 1 for a new game).
		//since this is a new game, some assets will be given default values from GameSettings
		
		//BOARD
		this.rows = GameSettings.GetRows();
		this.cols = GameSettings.GetCols();
		
		// Build In Game Menu UI.
		InGameUIPanel inGameUIPanel = new InGameUIPanel();
		
		//Build a new game board (returns a JPanel with a grid of configured GameTiles and extra methods)
		GameBoard gameBoard = new GameBoard(tileColor, wallColor, bkgColor, rows, cols);      //<----THE BOARD
		
		//give the game tiles to the InputManager for handling user input
		GameSettings.inputManager.SetGridTiles(gameBoard.GetGrid());
		
		//PLAYERS (4)		
		//get an empty array that will hold 4 configured players, in proper turn order (1, 2, 3, 4).
		Player[] players = new Player[4];
		//loop players.  turn order is currently randomized (from GameSettings)
		//unrandomize it, store new players in proper order (starting with whomever was chosen as first player)
		for(int i = 0; i < turnOrder.size(); i++) {
			
			//NEED TO IMPLEMENT PLAYER STARTING POSITIONS HERE!
			
			//turn
			int playerTurn = turnOrder.get(i);
			//type and difficulty
			String playerType = playerTypes.get(i);
			Boolean isDifficult = false;
			if(playerType == "Human") {
				playerType = "h";
			}else if(playerType == "Computer(Easy)") {
				playerType = "c";
			}else if(playerType == "Computer(Hard)") {
				playerType = "c";
				isDifficult = true;
			}else {
				System.out.println("Player Type Not Identified!!");
			}
			//name
			String playerName = playerNames.get(i);
			//color
			Color playerColor = playerColors[i];
			
			//build a player
			Player newPlayer = new Player(playerTurn, playerType, playerName, playerColor, isDifficult);
			//add it to array in proper ordered position.  -1 for zero indexing.
			players[newPlayer.getTurnPosition() - 1] = newPlayer;
		}
		
		
		//NEXT PLAYER
		int nextPlayer = 0;   //<--new game, so we start with players[0];
		
		// Set Borders
		inGameUIPanel.setSouthBorderBG(players[0].getColor());
		inGameUIPanel.setWestBorderBG(players[1].getColor());
		inGameUIPanel.setNorthBorderBG(players[2].getColor());
		inGameUIPanel.setEastBorderBG(players[3].getColor());
		
		//start the game controller
		GameController game = new GameController(inGameUIPanel, gameBoard, players, nextPlayer);
		inGameUIPanel.middlePanel.add(gameBoard);
		
		
	}
	
	
	
						
						

}
