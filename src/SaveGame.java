import java.io.*;
import java.util.ArrayList;

public class SaveGame {

	public static void saveGameObjs() {

		try { // Catch errors in I/O if necessary.
				// Open a file to write to, named SavedObj.sav.
			FileOutputStream saveFile = new FileOutputStream("Save.sav");

			// Create an ObjectOutputStream to put objects into save file.
			ObjectOutputStream save = new ObjectOutputStream(saveFile);

			// Now we do the save.
			ArrayList<Player> players = GameSettings.getPlayers();
			save.writeObject(players);

			// Close the file.
			save.close(); // This also closes saveFile.
		} catch (Exception exc) {
			exc.printStackTrace(); // If there was an error, print the info.
		}
	}
}