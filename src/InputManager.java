import java.awt.*;
import java.io.IOException;


//currently top level class.  probably demote this when refactoring.
//this class currently spawns the game board, which in turn spawns tiles.  move this! 
//this class need only handle incoming user input, and respond accordingly.


public class InputManager {	
	
	//store local ref to rows/cols here
	//Input manager uses them a lot
	private int rows;
	private int cols;
	
	private int width;
	private int height;
	private int xCenter;
	private int yCenter;
	
	
	//store local ref to gridtiles here.  better way?
	//use setter below when ready to update.
	private GameTile[][] gridTiles;
	
	//constructor
	public InputManager(){
		
		//input manager sees rows/cols of grid as 0-indexed!
		rows = GameSettings.GetRows() - 1;
		cols = GameSettings.GetCols() - 1;
		
		//get tile width and height settings, calculate the center wrt this tile.
		this.width = GameSettings.getTileWidth();
		this.height = GameSettings.getTileHeight();
		this.xCenter = width/2;
		this.yCenter = height/2;
	}
	
	//Setter.  Tiles provided by GameBoard after spawning.
	public void SetGridTiles(GameTile[][] tiles) {
		gridTiles = tiles;
	}	
		
	//method to track mouse movement during gameplay.
	//this method tracks the mouse while it's moving (hovering) over the game board.
	public void TrackMouseMovement(int rowAddress, int columnAddress, Point point) {
		
		//messages sent here from individual GameTiles.
		//track mouse position within the tile.  
		//if user is close to center of tile, highlight center tile
		//if user is closer to one edge, highlight that edge.
		//don't forget to activate corner walls too.
		
		//check for mouse at center.  special case, handle separately.
		if((point.x > width/20) && (point.x < width-(width/20)) && (point.y > height/20) && (point.y < (height-(height/20)))) {			
			//mouse is at center, let GameGrid know.
			//no further checks			
			HandleMouseInput(rowAddress, columnAddress, "c");
			return;
		}		
		//else mouse is not at center of this tile.		
		//is mouse on left side of tile?
		if(point.x >= 0 && point.x < xCenter) {
			//mouse is on the left
			//is mouse on upper or lower left side?
			if(point.y < yCenter) {
				//mouse is on upper left
				//is mouse more upper, or more left?
				//get distance to top, and distance to left and choose smaller
				int upperDist = point.y - 0;
				int leftDist = point.x - 0;
				if(upperDist < leftDist) {
					//mouse is on upper left, closer to upper
					HandleMouseInput(rowAddress, columnAddress, "ulu");
				}
				else {
					//mouse is on upper left, closer to left
					HandleMouseInput(rowAddress, columnAddress, "ull");
				}
			}
			else {
				//mouse is on lower left
				//is mouse more lower, or more left?
				//get distance to bottom, and distance to left and choose smaller
				int bottomDist = height - point.y;
				int leftDist = point.x - 0;
				if(bottomDist < leftDist) {
					//mouse is on lower left, closer to bottom
					HandleMouseInput(rowAddress, columnAddress, "llb");
				}
				else {
					//mouse is on lower left, closer to left
					HandleMouseInput(rowAddress, columnAddress, "lll");
				}
			}
		}
		//mouse is on the right
		else {			
			//is mouse on upper or lower right side?
			if(point.y < yCenter) {
				//mouse is on upper right
				//is mouse more upper, or more right?
				//get distance to top, and distance to right and choose smaller
				int upperDist = point.y - 0;
				int rightDist = width - point.x;
				if(upperDist < rightDist) {
					//mouse is on upper right, closer to upper
					HandleMouseInput(rowAddress, columnAddress, "uru");
				}
				else {
					//mouse is on upper right, closer to right
					HandleMouseInput(rowAddress, columnAddress, "urr");
				}
			}
			else {
				//mouse is on lower right
				//is mouse more lower, or more right?
				//get distance to bottom, and distance to right and choose smaller
				int bottomDist = height - point.y;
				int rightDist = width - point.x;
				if(bottomDist < rightDist) {
					//mouse is on lower right, closer to bottom
					HandleMouseInput(rowAddress, columnAddress, "lrb");
				}
				else {
					//mouse is on lower right, closer to right
					HandleMouseInput(rowAddress, columnAddress, "lrr");
				}
			}
		}
	}
	
	
	
	//Params:
    //  xAddress = x-coord of GameTile		yAddress = ycoord of GameTile
    //	locationCode is where on the tile the mouse currently resides (quadrant and closest border)
    //  locationCode's:		ulu = upper-left, upper		ull = upper-left, left
    //						uru = upper-right, upper	urr = upper-right, right
    //						llb = lower-left, bottom	lll = lower-left, left
    //						lrb = lower-right, bottom	lrr = lower-right, right
    //						c = center tile.
    public void HandleMouseInput(int x, int y, String locationCode) { 
    	
    	//clear temp walls and center tile highlight
    	gridTiles[x][y].TurnOffWall("all");
    	gridTiles[x][y].TurnOffCenterTile();
		TurnOffNeighbouringWalls(x, y);
		
    	switch(locationCode) {
    	
	    	case "c":	    		
	    		//highlight tile.
	    		gridTiles[x][y].TurnOnWall("c");
	    		break; 
	        case "ulu":
	        	//if no north neighbour, return
	        	if(x == 0) {
	        		return;
	        	}
	        	//if no west neighbour, return.
	        	if(y == 0) {
	        		return;
	        	}
	        	//light-up top wall/corner on this tile, and top on west neighbour
	        	//also bottom on north neighbour, and bottom on nw neighbour.
	        	//this tile
	        	gridTiles[x][y].TurnOnWall("t");
	        	gridTiles[x][y].TurnOnWall("tl");
        		//west
	        	gridTiles[x][y-1].TurnOnWall("tr");
	        	gridTiles[x][y-1].TurnOnWall("t");
        		//north
	        	gridTiles[x-1][y].TurnOnWall("b");
	        	gridTiles[x-1][y].TurnOnWall("bl");
        		//northwest
	        	gridTiles[x-1][y-1].TurnOnWall("br");
	        	gridTiles[x-1][y-1].TurnOnWall("b");
	            break;
	        case "ull":
	        	//if no north neighbour, return
	        	if(x == 0) {
	        		return;
	        	}
	        	//if no west neighbour, return.
	        	if(y == 0) {
	        		return;
	        	}
	        	//light-up left wall/corner on this tile, and right on west neighbour
	        	//also left on north neighbour, and right on nw neighbour.	        	
	        	//this tile
	        	gridTiles[x][y].TurnOnWall("l");
	        	gridTiles[x][y].TurnOnWall("tl");
        		//west
	        	gridTiles[x][y-1].TurnOnWall("r");
	        	gridTiles[x][y-1].TurnOnWall("tr");
        		//north
	        	gridTiles[x-1][y].TurnOnWall("l");
	        	gridTiles[x-1][y].TurnOnWall("bl");
        		//northwest
	        	gridTiles[x-1][y-1].TurnOnWall("r");
	        	gridTiles[x-1][y-1].TurnOnWall("br");
        		break;
	        case "uru":
	        	//if no north neighbour, return
	        	if(x == 0) {
	        		return;
	        	}
	        	//if no east neighbour, return.
	        	if(y == cols) {
	        		return;
	        	}
	        	//light-up top wall/corner on this tile, and bottom on north neighbour
	        	//also top on east neighbour, and bottom on ne neighbour.	        	
	        	//this tile
	        	gridTiles[x][y].TurnOnWall("t");
	        	gridTiles[x][y].TurnOnWall("tr");
        		//north
	        	gridTiles[x-1][y].TurnOnWall("b");
	        	gridTiles[x-1][y].TurnOnWall("br");
        		//east
	        	gridTiles[x][y+1].TurnOnWall("t");
	        	gridTiles[x][y+1].TurnOnWall("tl");
        		//northeast
	        	gridTiles[x-1][y+1].TurnOnWall("b");
	        	gridTiles[x-1][y+1].TurnOnWall("bl");
        		break;
	        case "urr":
	        	//if no north neighbour, return
	        	if(x == 0) {
	        		return;
	        	}
	        	//if no east neighbour, return.
	        	if(y == cols) {
	        		return;
	        	}
	        	//light-up right wall/corner on this tile, and right on north neighbour
	        	//also left on east neighbour, and left on ne neighbour.	        	
	        	//this tile
	        	gridTiles[x][y].TurnOnWall("r");
	        	gridTiles[x][y].TurnOnWall("tr");
        		//north
	        	gridTiles[x-1][y].TurnOnWall("r");
	        	gridTiles[x-1][y].TurnOnWall("br");
        		//east
	        	gridTiles[x][y+1].TurnOnWall("l");
	        	gridTiles[x][y+1].TurnOnWall("tl");
        		//northeast
	        	gridTiles[x-1][y+1].TurnOnWall("l");
	        	gridTiles[x-1][y+1].TurnOnWall("bl");
        		break;
	        case "llb":
	        	//if no south neighbour, return
	        	if(x == rows) {
	        		return;
	        	}
	        	//if no west neighbour, return.
	        	if(y == 0) {
	        		return;
	        	}
	        	//light-up bottom wall/corner on this tile, and top on south neighbour
	        	//also bottom on west neighbour, and top on sw neighbour.	        	
	        	//this tile
	        	gridTiles[x][y].TurnOnWall("b");
	        	gridTiles[x][y].TurnOnWall("bl");
        		//south
	        	gridTiles[x+1][y].TurnOnWall("t");
	        	gridTiles[x+1][y].TurnOnWall("tl");
        		//west
	        	gridTiles[x][y-1].TurnOnWall("b");
	        	gridTiles[x][y-1].TurnOnWall("br");
        		//southwest
	        	gridTiles[x+1][y-1].TurnOnWall("t");
	        	gridTiles[x+1][y-1].TurnOnWall("tr");
        		break;
	        case "lll":
	        	//if no south neighbour, return
	        	if(x == rows) {
	        		return;
	        	}
	        	//if no west neighbour, return.
	        	if(y == 0) {
	        		return;
	        	}
	        	//light-up left wall/corner on this tile, and left on south neighbour
	        	//also right on west neighbour, and right on sw neighbour.	        	
	        	//this tile
	        	gridTiles[x][y].TurnOnWall("l");
	        	gridTiles[x][y].TurnOnWall("bl");
        		//south
	        	gridTiles[x+1][y].TurnOnWall("l");
	        	gridTiles[x+1][y].TurnOnWall("tl");
        		//west
	        	gridTiles[x][y-1].TurnOnWall("r");
	        	gridTiles[x][y-1].TurnOnWall("br");
        		//southwest
	        	gridTiles[x+1][y-1].TurnOnWall("r");
	        	gridTiles[x+1][y-1].TurnOnWall("tr");
        		break;
	        case "lrb":
	        	//if no south neighbour, return
	        	if(x == rows) {
	        		return;
	        	}
	        	//if no east neighbour, return.
	        	if(y == cols) {
	        		return;
	        	}
	        	//light-up bottom wall/corner on this tile, and top on south neighbour
	        	//also bottom on east neighbour, and top on se neighbour.	        	
	        	//this tile
	        	gridTiles[x][y].TurnOnWall("b");
	        	gridTiles[x][y].TurnOnWall("br");
        		//south
	        	gridTiles[x+1][y].TurnOnWall("t");
	        	gridTiles[x+1][y].TurnOnWall("tr");
        		//east
	        	gridTiles[x][y+1].TurnOnWall("b");
	        	gridTiles[x][y+1].TurnOnWall("bl");
        		//southeast
	        	gridTiles[x+1][y+1].TurnOnWall("t");
	        	gridTiles[x+1][y+1].TurnOnWall("tl");
        		break;
	        case "lrr":
	        	//if no south neighbour, return
	        	if(x == rows) {
	        		return;
	        	}
	        	//if no east neighbour, return.
	        	if(y == cols) {
	        		return;
	        	}
	        	//light-up right wall/corner on this tile, and right on south neighbour
	        	//also left on east neighbour, and left on se neighbour.	        	
	        	//this tile
	        	gridTiles[x][y].TurnOnWall("r");
	        	gridTiles[x][y].TurnOnWall("br");
        		//south
	        	gridTiles[x+1][y].TurnOnWall("r");
	        	gridTiles[x+1][y].TurnOnWall("tr");
        		//east
	        	gridTiles[x][y+1].TurnOnWall("l");
	        	gridTiles[x][y+1].TurnOnWall("bl");
        		//southeast
	        	gridTiles[x+1][y+1].TurnOnWall("l");
	        	gridTiles[x+1][y+1].TurnOnWall("tl");
        		break;	            
	            
	        default: 
	            System.out.println("no match for locationCode");
    	}
    }
    
    //put this somewhere else when refactoring?
    public void TurnOffNeighbouringWalls(int x, int y) {
    	
    	//NOTE:  0,0 is top left.   (rows, cols) is bottom right.  
    	//check if neighbour exists, if it exists turn off it's walls.    	
    	//topleft
    	if(x > 0 && y > 0) {
    		gridTiles[x-1][y-1].TurnOffWall("all");
    	}
    	//top
    	if(x > 0) {
    		gridTiles[x-1][y].TurnOffWall("all");
    	}
    	//topright
    	if(x > 0 && y < cols) {
    		gridTiles[x-1][y+1].TurnOffWall("all");
    	}
    	//left
    	if(y > 0) {
    		gridTiles[x][y-1].TurnOffWall("all");
    	}
    	//right
    	if(y < cols) {
    		gridTiles[x][y+1].TurnOffWall("all");    		
    	}
    	//bottomleft
    	if(x < rows && y > 0) {
    		gridTiles[x+1][y-1].TurnOffWall("all");
    	}
    	//bottom
    	if(x < rows) {
    		gridTiles[x+1][y].TurnOffWall("all");
    	}
    	//bottomright
    	if(x < rows && y < cols) {
    		gridTiles[x+1][y+1].TurnOffWall("all");
    	}
    }
}
