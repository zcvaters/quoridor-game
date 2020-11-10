import java.io.*;
import java.util.Arrays;

public class LoadGame {

	private LoadGame() {
	}

	public static void loadGameObjs(String filename) {
		// Create the data objects for us to restore.
		Player[] players = new Player[4];
		GameTile[][] gameTiles = new GameTile[9][9];


		try {
			// Open file to read Save.sav
			FileInputStream saveFile = new FileInputStream("Save1.sav");

			// Create an ObjectInputStream to get objects from save file.
			ObjectInputStream save = new ObjectInputStream(saveFile);

			players = (Player[]) save.readObject(); // Restore Player Objects
			gameTiles = (GameTile[][]) save.readObject(); // Load Game tiles
			int nextTurn = (int) save.readObject(); // Restore next turn

			// Load game
			loadGameBuild(players, gameTiles, nextTurn);

			// Print the values, to see that they've been recovered.
			System.out.println("\nRestored Object Values:");
			System.out.println("Player 1 Names: " + players[0].GetName());
			System.out.println("Player 2 Names: " + players[1].GetName());
			System.out.println("Player 3 Names: " + players[2].GetName());
			System.out.println("Player 4 Names: " + players[3].GetName());
			System.out.println("Next Turn: " + nextTurn);
			System.out.println("Game Tiles Test: " + Arrays.deepToString(gameTiles));
			
			save.close(); // Close File.
			saveFile.close();
		} catch (Exception exc) {
			exc.printStackTrace(); // If there was an error, print the info.
		}



	}

	private static void loadGameBuild(Player[] players, GameTile[][] gameTiles, int nextTurn) {

		BuildAssets loadBuild = new BuildAssets(players, gameTiles, nextTurn);
	}

	/*
	 * Testing purposes, load file. Run this class on its own to check Object
	 * reading.
	 */
	public static void main(String[] args) {
		LoadGame.loadGameObjs("Save1.sav");
	}

}
