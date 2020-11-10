import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SaveGame {

	// fields here
	static Player[] playersInOrder;
	static GameTile[][] gameTiles;
	static int nextTurn;

	// constructor
	private SaveGame() {
	}

	public static void saveGameObjs(String filename) {
		Player[] playersInOrder = new Player[4];
		playersInOrder = GameSettings.getPlayers();

		GameTile[][] gameTiles = GameSettings.getGameTiles();
		nextTurn = GameSettings.GetGameController().getNextPlayer();

		try { // Catch errors in I/O if necessary.
				// Open a file to write to Save.sav
			FileOutputStream saveFile = new FileOutputStream(filename);

			// Create an ObjectOutputStream to put objects into save file.
			ObjectOutputStream save = new ObjectOutputStream(saveFile);

			// Saving objects
			save.writeObject(playersInOrder); // Saves Players order
			save.writeObject(gameTiles); // Saves GameTile objects.
			save.writeObject(nextTurn); // Turn position object
			//save.writeObject(players); // Player Objects.

			System.out.println("Saved to " + filename);
			save.close(); // Close file.
			save.flush();
			saveFile.close();
			saveFile.flush();
		} catch (Exception exc) {
			exc.printStackTrace(); // If there was an error, print the info.
		}
	}

}
