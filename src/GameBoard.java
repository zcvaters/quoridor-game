import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;


public class GameBoard extends JFrame implements ActionListener{
	
	//note:  this would eventually have to be changed to "extends JPanel", so that it can be nested in a higher-level frame with menus, etc.
	//Frames cannot be nested!	
		
	
	//panel to display the gridTile objs in a grid-style layout.
	JPanel gridPanel;
	
	//two-dim array, or table, to store the game tiles. [row][col].
	GameTile [] [] grid;
	
	//size of grid. set in const'.
	int rows;
	int cols;
	
	InputManager inputManager;
	
	//background images
	BufferedImage horizWall_on;
	BufferedImage vertWall_on;
	BufferedImage cornerWall_on;
	BufferedImage tile_on;
	
	BufferedImage horizWall_off;
	BufferedImage vertWall_off;
	BufferedImage cornerWall_off;
	BufferedImage tile_off;
	
	
	//the images are stored, in order, here.
	//horizWall, vertWall, cornerWall, Tile.
	BufferedImage [] images_off;
	BufferedImage [] images_on;
	
	//constructor
	public GameBoard(int rows, int cols, InputManager inpMgr) throws IOException {
		
		super();  //creates a frame.
		
		//get size
		this.rows = rows;
		this.cols = cols;
		this.setSize(540, 560);
		
		//throws I/O Exception on missing files
		GetBackgroundImages();
		
		//build panel
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(rows, cols));
		//gridPanel.setSize(540, 540);
		
		
		//build a grid of GameTiles inside the panel.
		grid = new GameTile[rows][cols];
		for(int x = 0; x < rows; x++) {
			for(int y = 0; y < cols; y++) {
				//spawn a gametile
				grid[x][y] = new GameTile(x, y, images_on, images_off, inpMgr);
				//add it to the panel
				gridPanel.add(grid[x][y]);
			}
		}		
		
		
		//add panel to the main game window/frame
		getContentPane().setLayout(new BorderLayout());		
		getContentPane().add(gridPanel, BorderLayout.CENTER);
		
		// housekeeping
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	// read images from class-path
    private void GetBackgroundImages() throws IOException {
    	
    	//"ON" state (active walls, highlighted center tile)
    	//horizontal walls     	
    	horizWall_on = ImageIO.read(getClass().getResource("/horizWall_on.png"));
        //vertical walls
    	vertWall_on = ImageIO.read(getClass().getResource("/vertWall_on.png"));
        //corner walls
    	cornerWall_on = ImageIO.read(getClass().getResource("/cornerWall_on.png"));
        //game tile
    	tile_on = ImageIO.read(getClass().getResource("/tile_on.png"));
        
        //"OFF" state (deactivated walls, normal center tile)
    	//horizontal walls
    	horizWall_off = ImageIO.read(getClass().getResource("/horizWall_off.png"));
        //vertical walls
    	vertWall_off = ImageIO.read(getClass().getResource("/vertWall_off.png"));
        //corner walls
    	cornerWall_off = ImageIO.read(getClass().getResource("/cornerWall_off.png"));
        //game tile
    	tile_off = ImageIO.read(getClass().getResource("/tile_off.png"));
        
        //add to array for "on" images
        images_on = new BufferedImage[4];
        images_on[0] = horizWall_on;
        images_on[1] = vertWall_on;
        images_on[2] = cornerWall_on;
        images_on[3] = tile_on;
        //addd to array for "off" images
        images_off = new BufferedImage[4];
        images_off[0] = horizWall_off;
        images_off[1] = vertWall_off;
        images_off[2] = cornerWall_off;
        images_off[3] = tile_off;        
	}
	       
       
    
    public GameTile[][] GetGrid() {
    	return grid;
    }
    
    public GameTile GetGameTile(int rowAddress, int colAddress) {
    	return grid[rowAddress][colAddress];
    }
    
	public int GetNumRows() {
		return rows;
	}
	
	public int GetNumCols() {
		return cols; 
	}	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

	
