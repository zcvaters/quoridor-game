

import java.io.*;
import java.util.ArrayList;

public class LoadGame{
	
	//define vars to hold the objects being loaded
	ArrayList<Player> allPlayers = new ArrayList<Player>();
	ArrayList<GameTile> gameTiles = new ArrayList<GameTile>();
	ArrayList<Integer> turnOrder = new ArrayList<Integer>();
	int nextPlayer = 0;
	
	
	public void LoadFromFile() {
		
		//TO DO
		//add param in method
		//use param to connect with a particular save file name
		
		
		//try/catch block to trap I/O errors.
		try{
		// open file to read from
		FileInputStream saveFile = new FileInputStream("saveFile.sav");
		
		// Create an ObjectInputStream to get objects from save file.
		ObjectInputStream thisSave = new ObjectInputStream(saveFile);
		
		//collect objects from save file.
		//note, these are returned as generic Object
		//need to cast into appropriate type
				
		//allPlayers = (ArrayList<Player>)save.readObject();
		
		//gameTiles = (ArrayList<GameTile>)save.readObject();
		
		//turnOrder = (ArrayList<Integer>)save.readObject();
		
		//nextPlayer = (Integer)save.readObject();
				
		// Close the file.
		thisSave.close(); // This also closes the fileInputStream saveFile.
		}
		catch(Exception exc){
		exc.printStackTrace(); // If there was an error, print the info.
		}
	}	
}