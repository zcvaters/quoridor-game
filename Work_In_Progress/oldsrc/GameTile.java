import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.border.*;
//import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;



public class GameTile extends JPanel implements MouseListener, MouseMotionListener{
	
	private int xcoord;  //these are 0 indexed, and correspond to position on the grid.
	private int ycoord;
	
	//configurable, the width and height of this 9-cell grid panel.
	//use appropriate bkg image sizes
	private int width =  60;  
	private int height = 60;  
	
	//coords of the center of this tile, relative to this panel. top-left of this obj is (0,0)
	private int xCenter = width/2;
	private int yCenter = height/2;
	
	//this object will contain a center tile panel, which is surrounded on NSEW by wall panels.
	//additional panels for 4 corners
	//combine these in a grid to make a 3x3 square tile.
	private JLabel topLeftWall;
	private JLabel topWall;
	private JLabel topRightWall;
	private JLabel leftWall;
	private JLabel tile;
	private JLabel rightWall;
	private JLabel bottomLeftWall;
	private JLabel bottomWall;
	private JLabel bottomRightWall;
	
	//background image icons
	ImageIcon horizWall_on;
	ImageIcon vertWall_on;
	ImageIcon cornerWall_on;
	ImageIcon tile_on;
	
	ImageIcon horizWall_off;
	ImageIcon vertWall_off;
	ImageIcon cornerWall_off;
	ImageIcon tile_off;
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	//object which will handle input gathered from listener on this tile.
	//obtained from GameGrid
	InputManager inputManager;
	
	
	//constructor
	public GameTile(int xcoord, int ycoord, BufferedImage[] onImages, BufferedImage[] offImages, InputManager inpMgr)  
	{
		super();  //create a JPanel
						
		this.setSize(width, height);  //size on screen, in pixels
		this.xcoord = xcoord;  //row address
		this.ycoord = ycoord;  //column address
		
		//assign on/highlighted background images to this GameTile
		horizWall_on = new ImageIcon(onImages[0]);
		vertWall_on = new ImageIcon(onImages[1]);
		cornerWall_on = new ImageIcon(onImages[2]);
		tile_on = new ImageIcon(onImages[3]);
		
		//assign off/disabled background images to this GameTile
		horizWall_off = new ImageIcon(offImages[0]);
		vertWall_off = new ImageIcon(offImages[1]);
		cornerWall_off = new ImageIcon(offImages[2]);
		tile_off = new ImageIcon(offImages[3]);
		
		//add a listeners to the tile panel  (tracks movement of cursor)
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		//cache ref to the InputManager which will handle the input from mouse events
		inputManager = inpMgr;
		
		//build tile, as a 9 cell grid.
		//square center tile, with one wall panel on each side, additional 4 panels at corners. 9 total.
		this.setLayout(new GridBagLayout());
		
		topLeftWall = new JLabel("");
		topLeftWall.setName("topLeftWall");
		topLeftWall.setIcon(cornerWall_off);		
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(topLeftWall, gbc);
		
		topWall = new JLabel("");
		topWall.setName("topWall");
		topWall.setIcon(horizWall_off);		
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(topWall, gbc);
		
		topRightWall = new JLabel("");
		topRightWall.setName("topRightWall");
		topRightWall.setIcon(cornerWall_off);		
		gbc.gridx = 10;
		gbc.gridy = 0;
		this.add(topRightWall, gbc);
		
		leftWall = new JLabel("");
		leftWall.setName("leftWall");
		leftWall.setIcon(vertWall_off);		
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(leftWall, gbc);
		
		tile = new JLabel("");
		tile.setName("tile");
		tile.setIcon(tile_off);		
		gbc.gridx = 1;
		gbc.gridy = 1;		
		this.add(tile, gbc);
		
		rightWall = new JLabel("");
		rightWall.setName("rightWall");
		rightWall.setIcon(vertWall_off);		
		gbc.gridx = 10;
		gbc.gridy = 1;
		this.add(rightWall, gbc);
		
		bottomLeftWall = new JLabel("");
		bottomLeftWall.setName("bottomLeftWall");
		bottomLeftWall.setIcon(cornerWall_off);		
		gbc.gridx = 0;
		gbc.gridy = 10;
		this.add(bottomLeftWall, gbc);
		
		bottomWall = new JLabel("");
		bottomWall.setName("bottomWall");
		bottomWall.setIcon(horizWall_off);		
		gbc.gridx = 1;
		gbc.gridy = 10;
		this.add(bottomWall, gbc);
		
		bottomRightWall = new JLabel("");
		bottomRightWall.setName("bottomRightWall");
		bottomRightWall.setIcon(cornerWall_off);		
		gbc.gridx = 10;
		gbc.gridy = 10;
		this.add(bottomRightWall, gbc);		
		
	}
	
	public void TurnOffCenterTile() {
		
		if(tile.getIcon().equals(tile_on)) {  
			tile.setIcon(tile_off);  		
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
            	if(topLeftWall.getIcon().equals(cornerWall_off)) {  
    				topLeftWall.setIcon(cornerWall_on);  		
    			}                
                break; 
            case "t":
            	if(topWall.getIcon().equals(horizWall_off)) {  
    				topWall.setIcon(horizWall_on);  		
    			}
            	break; 
            case "tr": 
            	if(topRightWall.getIcon().equals(cornerWall_off)) {  
    				topRightWall.setIcon(cornerWall_on);  		
    			}
                break;
            case "l": 
            	if(leftWall.getIcon().equals(vertWall_off)) {  
    				leftWall.setIcon(vertWall_on);  		
    			}
            	break; 
            case "c": 
            	if(tile.getIcon().equals(tile_off)) {  
    				tile.setIcon(tile_on);  		
    			} 
                break; 
            case "r": 
            	if(rightWall.getIcon().equals(vertWall_off)) {  
    				rightWall.setIcon(vertWall_on);  		
    			} 
                break;
            case "bl": 
            	if(bottomLeftWall.getIcon().equals(cornerWall_off)) {  
    				bottomLeftWall.setIcon(cornerWall_on);  		
    			}
                break; 
            case "b": 
            	if(bottomWall.getIcon().equals(horizWall_off)) {  
    				bottomWall.setIcon(horizWall_on);  		
    			}
                break; 
            case "br": 
            	if(bottomRightWall.getIcon().equals(cornerWall_off)) {  
    				bottomRightWall.setIcon(cornerWall_on);  		
    			} 
                break;
            default: 
                System.out.println("Can't turn on.  No match for gridCode"); 
        }		
	}
	
	//@Params:				a = all  
	//	tl= top-left		t = top			tr = top-right
	//	l = left							r = right
	//	bl = bottom-left	b = bottom		br = bottom-right
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
            	if(topLeftWall.getIcon().equals(cornerWall_on)) {  
    				topLeftWall.setIcon(cornerWall_off);  		
    			}                
                break; 
            case "t":
            	if(topWall.getIcon().equals(horizWall_on)) {  
    				topWall.setIcon(horizWall_off);  		
    			}
            	break; 
            case "tr": 
            	if(topRightWall.getIcon().equals(cornerWall_on)) {  
    				topRightWall.setIcon(cornerWall_off);  		
    			}
                break;
            case "l": 
            	if(leftWall.getIcon().equals(vertWall_on)) {  
    				leftWall.setIcon(vertWall_off);  		
    			}
            	break;            
            case "r": 
            	if(rightWall.getIcon().equals(vertWall_on)) {  
    				rightWall.setIcon(vertWall_off);  		
    			} 
                break;
            case "bl": 
            	if(bottomLeftWall.getIcon().equals(cornerWall_on)) {  
    				bottomLeftWall.setIcon(cornerWall_off);  		
    			}
                break; 
            case "b": 
            	if(bottomWall.getIcon().equals(horizWall_on)) {  
    				bottomWall.setIcon(horizWall_off);  		
    			}
                break; 
            case "br": 
            	if(bottomRightWall.getIcon().equals(cornerWall_on)) {  
    				bottomRightWall.setIcon(cornerWall_off);  		
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
		
		inputManager.TrackMouseMovement(xcoord, ycoord, width, height, xCenter, yCenter, point);
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		
		TurnOffWall("all");
		TurnOffCenterTile();
		inputManager.TurnOffNeighbouringWalls(xcoord, ycoord);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
		// Activate place (lock?) walls, or move player, here.
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
					
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
