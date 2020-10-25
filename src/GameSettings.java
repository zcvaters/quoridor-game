import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameSettings {
	
	//store ref to the main UI window (a frame)
	//this frame will display a variety of panels depending on user needs
	static MainWindow mainWindow;
	
	//store reference to object which manages all incoming user input
	static InputManager inputManager;
	
	//the number of rows for game board
	static int rows;
	//the number of cols for game board
	static int cols;
	//the size of the game tile
	static int tileWidth;
	static int tileHeight;
	
	
	//COLORS   
	public static final Color tileColor1 = new Color(85,27,20);
	public static final Color wallColor1 = new Color(109,61,20);
	public static final Color bkgColor1 = new Color(0,0,0);
	public static final Color player1Color1 = new Color(137,185,184);
	public static final Color player2Color1 = new Color(88, 66, 47);
	public static final Color player3Color1 = new Color(238, 180, 89);
	public static final Color player4Color1 = new Color(205,197,180);
	
	public static final Color tileColor2 = new Color(201,232,215);
	public static final Color wallColor2 = new Color(235,110,0);
	public static final Color bkgColor2 = new Color(63,83,230);
	public static final Color player1Color2 = new Color(230,223,125);
	public static final Color player2Color2 = new Color(130,225,230);
	public static final Color player3Color2 = new Color(230,172,129);
	public static final Color player4Color2 = new Color(209,151,230);
	
	public static final Color tileColor3 = new Color(255,255,255);
	public static final Color wallColor3 = new Color(7,26,200);
	public static final Color bkgColor3 = new Color(0,0,0);
	public static final Color player1Color3 = new Color(216,191,216);
	public static final Color player2Color3 = new Color(210,105,30);
	public static final Color player3Color3 = new Color(135,206,235);
	public static final Color player4Color3 = new Color(255,250,205);
	
	//tile color options
	static Color[] tileColors = {tileColor1, tileColor2, tileColor3};
	//wall color options
	static Color[] wallColors = {wallColor1, wallColor2, wallColor3};
	//background color options
	static Color[] bkgColors = {bkgColor1, bkgColor2, bkgColor3};
	
	//player colors (3 sets of four players each, in a 2D array)
	static Color[][] playerColors = {
							{player1Color1, player2Color1, player3Color1, player4Color1},
							{player1Color2, player2Color2, player3Color2, player4Color2},
							{player1Color3, player2Color3, player3Color3, player4Color3}
							};
	
	//store collection of names that AI can use
	static String[] computerNames;
	
	//a collection of 1, 2, 3, 4 in a random order.  used to assign ID's.
	static List<Integer> playerIDList;
	
	/* Constructor - GameSettings
	 * 
	 * Intended as a top-level class to provide access for static variables.
	 * Any class can access these attributes like this 
	 *   GameSettings.GetInputManager();
	 *   GameSettings.GetPlayerColors(index);
	*/
	public GameSettings() {
		
		//set tile size (includes 1/2 wall width on each side)
		//recommend 90x90 if using a 1000x1000 frame.
		tileWidth = 80;
		tileHeight = 80;
		
		//set grid size for play
		rows = 9;
		cols = 9;
		
		//create names
		computerNames = new String[]{"Robyn", "Yousuf", "Shannon", "Dave", "Lemar", "Oscar", "Winnie"};
		
		//create a random list for assigning player ID's
		playerIDList = new ArrayList<>();
		playerIDList = buildPlayerIDList();
		
		//build and store an input manager to interface with user (mouse move/click)
		inputManager = new InputManager();
		
		//create and store a MainWindow frame to begin gameplay (starts at main menu).
		mainWindow = new MainWindow();
	}
	
	//getters
	public static InputManager GetInputManager() {
		return inputManager;
	}
	
	public static MainWindow GetMainWindow() {
		return mainWindow;
	}
	
	public static int GetRows() {
		return rows;
	}
	
	public static int GetCols() {
		return cols;
	}
	
	public static int getTileWidth() {
		return tileWidth;
	}

	public static int getTileHeight() {
		return tileHeight;
	}
	
	//get computer-generated name
	public static String GetRandomName() {
		int rnd = new Random().nextInt(computerNames.length);
	    return computerNames[rnd];
	}
	
	//get player ID List (randomized 1-4)
	public static List<Integer> getPlayerIDList(){
		return playerIDList;
	}	
	
	//returns a single color
	//use index 0, 1, or 2
	public static Color getTileColor(int tileIndex) {
		return tileColors[tileIndex];
	}
	
	//returns a single color
	//use index 0, 1, or 2
	public static Color getWallColor(int wallIndex) {
		return wallColors[wallIndex];
	}
	
	//returns a single color
	//use index 0, 1, or 2
	public static Color getBkgColor(int bkgIndex) {
		return bkgColors[bkgIndex];
	}
	
	//this returns an array of 4 colors
	//use index 0, 1, or 2
	public static Color[] getPlayerColors(int playersIndex) {
		return playerColors[playersIndex];
	}
	
	
	//helper methods
	private List<Integer> buildPlayerIDList(){
		//randomize the numbers 1-4 inside an array.  use collections->shuffle().
		//ex:  [1, 3, 4, 2]   or   [2, 1, 4, 3]  etc...				
		List<Integer> sourceList = Arrays.asList(1, 2, 3, 4);
		Collections.shuffle(sourceList);
		//return the shuffled source list.
		return sourceList;		
	}

}
