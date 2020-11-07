import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class LoadGame{

public static void main(String[] arg){

// Create the data objects for us to restore.	
	ArrayList<Player> players = new ArrayList<>();


// Wrap all in a try/catch block to trap I/O errors.
	try{
		// Open file to read from, named SavedObj.sav.
		FileInputStream saveFile = new FileInputStream("Save.sav");

		// Create an ObjectInputStream to get objects from save file.
		ObjectInputStream save = new ObjectInputStream(saveFile);

		// Now we do the restore.
		// readObject() returns a generic Object, we cast those back
		// into their original class type.
		// For primitive types, use the corresponding reference class.

		players.addAll((Collection<? extends Player>) save.readObject());

		// Close the file.
		save.close(); // This also closes saveFile.
	}
	catch(Exception exc){
		exc.printStackTrace(); // If there was an error, print the info.
	}

	// Print the values, to see that they've been recovered.
	System.out.println("\nRestored Object Values:");
	System.out.println("Player 1 Names: " + players.get(0).GetName());
	System.out.println("Player 2 Names: " + players.get(1).GetName());
	System.out.println("Player 3 Names: " + players.get(2).GetName());
	System.out.println("Player 4 Names: " + players.get(3).GetName());
	
	


	// All done.
	}
}