import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveGame {

	private SaveGame() {
	}

	private static ArrayList<Player> players = GameSettings.getPlayers();
	private static GameTile[][] gameTiles = GameSettings.getGameTiles();

	public static void saveGameObjs(String filename) {
		// TODO: Implement save filename, save1, save2, save3.
		try { // Catch errors in I/O if necessary.
				// Open a file to write to Save.sav
			FileOutputStream saveFile = new FileOutputStream(filename);

			// Create an ObjectOutputStream to put objects into save file.
			ObjectOutputStream save = new ObjectOutputStream(saveFile);

			// Saving objects
			save.writeObject(players); // Saves all Player Objects.
			save.writeObject(gameTiles); // Saves GameTile objects.

			// TODO: Save all assets needed to game state.
			
			System.out.println("Saved to " + filename);
			save.close(); // Close file.
		} catch (Exception exc) {
			exc.printStackTrace(); // If there was an error, print the info.
		}
	}
}
