import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/*
 * Save game, streams objects for serializing to a save file.
 */
public class SaveGame {

	// fields here
	static Player[] playersInOrder;
	static GameTile[][] gameTiles;
	static int nextToPlay;

	// constructor
	private SaveGame() {
	}
	
	/*
	 * SaveGameObjs saves the current objs in the game for to rebuild game.
	 * 	@param: String filename, the save file.
	 */
	public static void saveGameObjs(String filename) {
		
		// Instantiate the necessary collections.
		playersInOrder = GameSettings.getPlayers();
		gameTiles = GameSettings.getGameTiles();
		nextToPlay = GameSettings.GetGameController().GetCurrentPlayer().GetTurnPosition() - 1;  //zero indexed.

		try { // Catch errors in I/O if necessary.
				// Open a file to write to Save.sav
			FileOutputStream saveFile = new FileOutputStream(filename);

			// Create an ObjectOutputStream to put objects into save file.
			ObjectOutputStream save = new ObjectOutputStream(saveFile);

			// Saving objects
			save.writeObject(playersInOrder); // Saves Players order
			save.writeObject(gameTiles); // Saves GameTile objects.
			save.writeObject(nextToPlay); // Turn position object

			save.close(); // Close/flush streams
			save.flush(); 
			saveFile.close();
			saveFile.flush();
		} catch (Exception exc) {
			exc.printStackTrace(); // If there was an error, print the info.
		}
	}

}
