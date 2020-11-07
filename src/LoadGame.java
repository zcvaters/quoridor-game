import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class LoadGame {

	private LoadGame() {
	}

	public static void loadGameObjs(String filename) {// TODO: Implement filename lookup in LoadGame
		// Create the data objects for us to restore.
		ArrayList<Player> players = new ArrayList<>();
		GameTile[][] gameTiles = new GameTile[9][9];

		try {
			// Open file to read Save.sav
			FileInputStream saveFile = new FileInputStream("Save.sav");

			// Create an ObjectInputStream to get objects from save file.
			ObjectInputStream save = new ObjectInputStream(saveFile);

			players.addAll((Collection<? extends Player>) save.readObject()); // Restore Player Objects
			gameTiles = (GameTile[][]) save.readObject();

			// TODO: Load all assets needed to game state.

			save.close(); // Close File.
		} catch (Exception exc) {
			exc.printStackTrace(); // If there was an error, print the info.
		}

		// Print the values, to see that they've been recovered.
		System.out.println("\nRestored Object Values:");
		System.out.println("Player 1 Names: " + players.get(0).GetName());
		System.out.println("Player 2 Names: " + players.get(1).GetName());
		System.out.println("Player 3 Names: " + players.get(2).GetName());
		System.out.println("Player 4 Names: " + players.get(3).GetName());

		System.out.println("Game Tiles Test: " + Arrays.deepToString(gameTiles));

	}

	/*
	 * Testing purposes, load file.
	 * Run this class on its own to check Object reading.
	 */
	public static void main(String[] args) {
		LoadGame.loadGameObjs("Save.sav");
	}
}
