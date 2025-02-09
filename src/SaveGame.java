import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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
		
		//before saving, turn off any exising goalTiles that have been displayed
		//deactivate the goal tile highights for the next player.
		ArrayList<GameTile> goalTiles = GameSettings.getGoalTiles();
		
		for(GameTile thisTile : goalTiles) {
			thisTile.DeactivateGoalPanel();
		}
		goalTiles.clear();
		GameSettings.setGoalTiles(goalTiles);
		
		// Instantiate the necessary collections.
		playersInOrder = GameSettings.getPlayers();
		gameTiles = GameSettings.getGameTiles();
		GameSettings.GetGameController();
		nextToPlay = GameController.GetCurrentPlayer().GetTurnPosition() - 1;  //zero indexed.
		
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
