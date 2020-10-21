import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.border.*;
//import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;



public class GameTile extends JPanel implements MouseListener, MouseMotionListener{
	
	//store ref to the inputManager.  Processes mouse events from this object.
	InputManager inputManager;
	
	//the colors for the center tile and it's walls
	Color tileColor;
	Color tileHighlightColor;
	Color wallColor;
	Color bkgColor;
	
	//this tile's position on the grid.  
	//these are 0-indexed.  top left = (0,0)    bottom right = (rows, cols)
	private int xcoord;  
	private int ycoord;	
	
	//the width and height of this tile
	private int width;
	private int height;	 
	
	//coords of the center of this tile, relative to this tile panel. 
	//top-left of this obj is (0,0)
	private int xCenter;
	private int yCenter;
	
	//this object will contain a center tile panel, which is surrounded on NSEW by wall panels.
	//additional panels for 4 corners
	//combine these in a grid to make a 3x3 square tile.
	//	TL	T	TR
	//	L	C	R
	//	BL	B	BR
	private JPanel topLeftWall;
	private JPanel topWall;
	private JPanel topRightWall;
	private JPanel leftWall;
	private JPanel tile;		//<--- center tile.  Player moves on these.
	private JPanel rightWall;
	private JPanel bottomLeftWall;
	private JPanel bottomWall;
	private JPanel bottomRightWall;	
	
	//using gridBagLayout for 9 panels above
	private GridBagConstraints gbc = new GridBagConstraints();	
		
	
	//constructor
	public GameTile(Color tileColor, Color wallColor, Color bkgColor, int xcoord, int ycoord)  
	{		
		super();  //create a JPanel			
		
		//Colors
		//the color of the center tiles (player moves on these)
		this.tileColor = tileColor;
		//the color of an activated wall border
		this.wallColor = wallColor;
		//the color of a non-active wall border
		this.bkgColor = bkgColor;
		//the color of a center tile that has been highlighted (player could move to these)
		this.tileHighlightColor = new Color(200, 0, 100);	//<-----FIX THIS!					
		
		//zero-indexed.
		this.xcoord = xcoord;  //row address
		this.ycoord = ycoord;  //column address		
		
		//remove this!! Testing only.
		//Color[] playerColors = GameSettings.GetPlayerColors(1);
		//int rnd = new Random().nextInt(playerColors.length);
	    //this.setBackground(playerColors[rnd]);		
		
		//cache ref to the InputManager
		inputManager = GameSettings.GetInputManager();
		
		//add a listeners to the tile panel  (tracks movement of cursor)
		this.addMouseListener(this);
		this.addMouseMotionListener(this);		
		
		//build square tile, as a 9 cell grid.
		//square center tile, with one wall panel on each side, additional 4 panels at corners. 9 total.
		this.setLayout(new GridBagLayout());
		
		//set the width/height of the center tile, find the center point
		this.width = GameSettings.getTileWidth();
		this.height = GameSettings.getTileHeight();
		this.xCenter = width/2;
		this.yCenter = height/2;		
		//stretch gridbag layout as necessary to accomodate height/width
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		
		topLeftWall = new JPanel();
		topLeftWall.setName("topLeftWall");
		topLeftWall.setBackground(bkgColor);
		topLeftWall.setOpaque(true);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		this.add(topLeftWall, gbc);
		
		topWall = new JPanel();
		topWall.setName("topWall");
		topWall.setBackground(bkgColor);
		topWall.setOpaque(true);
		gbc.gridx = 5;
		gbc.gridy = 0;
		gbc.gridwidth = 50;
		gbc.gridheight = 5;
		this.add(topWall, gbc);
		
		topRightWall = new JPanel();
		topRightWall.setName("topRightWall");
		topRightWall.setBackground(bkgColor);
		topRightWall.setOpaque(true);
		gbc.gridx = 55;
		gbc.gridy = 0;
		gbc.gridwidth = 5;
		gbc.gridheight = 5;
		this.add(topRightWall, gbc);
		
		leftWall = new JPanel();		
		leftWall.setName("leftWall");
		leftWall.setBackground(bkgColor);
		leftWall.setOpaque(true);	
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 5;
		gbc.gridheight = 50;
		this.add(leftWall, gbc);
		
		tile = new JPanel();
		tile.setPreferredSize(new Dimension(width,height));
		tile.setName("tile");
		tile.setBackground(tileColor);
		tile.setOpaque(true);		
		gbc.gridx = 5;
		gbc.gridy = 5;		
		this.add(tile, gbc);
		
		rightWall = new JPanel();
		rightWall.setName("rightWall");
		rightWall.setBackground(bkgColor);
		rightWall.setOpaque(true);		
		gbc.gridx = 55;
		gbc.gridy = 5;
		gbc.gridwidth = 5;
		gbc.gridheight = 50;
		this.add(rightWall, gbc);
		
		bottomLeftWall = new JPanel();
		bottomLeftWall.setName("bottomLeftWall");
		bottomLeftWall.setBackground(bkgColor);
		bottomLeftWall.setOpaque(true);
		gbc.gridx = 0;
		gbc.gridy = 55;
		gbc.gridwidth = 5;
		gbc.gridheight = 5;
		this.add(bottomLeftWall, gbc);
		
		bottomWall = new JPanel();
		bottomWall.setName("bottomWall");
		bottomWall.setBackground(bkgColor);
		bottomWall.setOpaque(true);		
		gbc.gridx = 5;
		gbc.gridy = 55;
		gbc.gridwidth = 50;
		gbc.gridheight = 5;		
		this.add(bottomWall, gbc);
		
		bottomRightWall = new JPanel();
		bottomRightWall.setName("bottomRightWall");
		bottomRightWall.setBackground(bkgColor);
		bottomRightWall.setOpaque(true);		
		gbc.gridx = 55;
		gbc.gridy = 55;
		gbc.gridwidth = 5;
		gbc.gridheight = 5;
		this.add(bottomRightWall, gbc);
	}
	
	
	
	public void TurnOffCenterTile() {		
		if(tile.getBackground() == tileHighlightColor) {               //<--------- FIX THIS!
			tile.setBackground(tileColor);		
		} 
	}
		
	//@Params:				 
	//	tl= top-left		t = top			tr = top-right
	//	l = left			c = center		r = right
	//	bl = bottom-left	b = bottom		br = bottom-right
	public void TurnOnWall(String gridCode) {
		
		switch(gridCode) 
        { 	        
            case "tl":
            	if(topLeftWall.getBackground() == bkgColor) {  
    				topLeftWall.setBackground(wallColor);  		
    			}                
                break; 
            case "t":
            	if(topWall.getBackground() == bkgColor) {  
    				topWall.setBackground(wallColor);  		
    			}
            	break; 
            case "tr": 
            	if(topRightWall.getBackground() == bkgColor) {  
            		topRightWall.setBackground(wallColor); 		
    			}
                break;
            case "l": 
            	if(leftWall.getBackground() == bkgColor) {  
    				leftWall.setBackground(wallColor); 		
    			}
            	break; 
            case "c": 
            	if(tile.getBackground() == tileColor) {    //<----FIX THIS!
    				tile.setBackground(tileHighlightColor);		
    			} 
                break; 
            case "r": 
            	if(rightWall.getBackground() == bkgColor) {  
    				rightWall.setBackground(wallColor);		
    			} 
                break;
            case "bl": 
            	if(bottomLeftWall.getBackground() == bkgColor) {  
    				bottomLeftWall.setBackground(wallColor); 		
    			}
                break; 
            case "b": 
            	if(bottomWall.getBackground() == bkgColor) {  
    				bottomWall.setBackground(wallColor);  		
    			}
                break; 
            case "br": 
            	if(bottomRightWall.getBackground() == bkgColor) {  
    				bottomRightWall.setBackground(wallColor); 		
    			} 
                break;
            default: 
                System.out.println("Can't activate wall.  No match for gridCode"); 
        }		
	}	
	
	//@Params:				a = all  
	//	tl= top-left		t = top			tr = top-right
	//	l = left							r = right
	//	bl = bottom-left	b = bottom		br = bottom-right
	//
	//note:  center tile handled separately.
	public void TurnOffWall(String gridCode) {		
		
		switch(gridCode) 
        { 
        	case "all":
        		TurnOffWall("tl");
        		TurnOffWall("t");
        		TurnOffWall("tr");
        		TurnOffWall("l");        		
        		TurnOffWall("r");
        		TurnOffWall("bl");
        		TurnOffWall("b");
        		TurnOffWall("br");
        		break; 
            case "tl":
            	if(topLeftWall.getBackground() == wallColor) {  
    				topLeftWall.setBackground(bkgColor); 		
    			}                
                break; 
            case "t":
            	if(topWall.getBackground() == wallColor) {  
    				topWall.setBackground(bkgColor);  		
    			}
            	break; 
            case "tr": 
            	if(topRightWall.getBackground() == wallColor) {  
    				topRightWall.setBackground(bkgColor); 		
    			}
                break;
            case "l": 
            	if(leftWall.getBackground() == wallColor) {  
    				leftWall.setBackground(bkgColor);  		
    			}
            	break;            
            case "r": 
            	if(rightWall.getBackground() == wallColor) {  
    				rightWall.setBackground(bkgColor);  		
    			} 
                break;
            case "bl": 
            	if(bottomLeftWall.getBackground() == wallColor) {  
    				bottomLeftWall.setBackground(bkgColor);  		
    			}
                break; 
            case "b": 
            	if(bottomWall.getBackground() == wallColor) {  
    				bottomWall.setBackground(bkgColor);  		
    			}
                break; 
            case "br": 
            	if(bottomRightWall.getBackground() == wallColor) {  
    				bottomRightWall.setBackground(bkgColor);  		
    			} 
                break;
            default: 
                System.out.println("Can't turn off.  No match for gridCode"); 
        }		
	}
		
	
	@Override
	public void mouseMoved(MouseEvent e) {
		
		//get mouse position
		Point point = e.getPoint();		
		//System.out.println("On tile (" +xcoord+ ", " +ycoord+ ")   At position (" +point.x+ ", " +point.y+ ")");
		
		//send to InputManager for handling
		inputManager.TrackMouseMovement(xcoord, ycoord, point);
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		
		TurnOffWall("all");
		TurnOffCenterTile();
		//send to InputManager for handling
		inputManager.TurnOffNeighbouringWalls(xcoord, ycoord);
	}


	@Override
	public void mouseClicked(MouseEvent e) {		
		// Activate place (lock?) walls, or move player, here.		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		//System.out.println("On tile (" +xcoord+ ", " +ycoord+ ")");
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
