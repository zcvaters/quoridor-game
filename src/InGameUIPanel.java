import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class InGameUIPanel extends JPanel{
	
	private JPanel nwCornerPanel, neCornerPanel, seCornerPanel, swCornerPanel;
	private JPanel southGameBoardBorder, westGameBoardBorder, northGameBoardBorder, eastGameBoardBorder;
	JPanel middlePanel;
	GridBagConstraints gbc = new GridBagConstraints();
	
	
	public InGameUIPanel()
	{
		super(); // Creates a frame
		
		this.setMinimumSize(new Dimension(1000, 1000));
		this.setPreferredSize(new Dimension(1000, 1000));
		setLayout(new GridBagLayout());
		alignedPerimeterBorders();
		
	}

	/**
	 * This method is used to align all borders on the GridPanel perimeter.
	 * Using GridBagLayout and GridBagConstraints. These panels are indicators
	 * for the players of the game.
	 * 
	 */
	public void alignedPerimeterBorders()
	{
		// Outer Border Dimension
		Dimension outerBorderX = new Dimension(800, 100);
		Dimension outerBorderY = new Dimension(100, 800);
		
		// North Border above Game Board.
		northGameBoardBorder = new JPanel();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		northGameBoardBorder.setMinimumSize(outerBorderX);
		northGameBoardBorder.setPreferredSize(outerBorderX);
		this.add(northGameBoardBorder, gbc);
		
		
		// Top Left border square.
		nwCornerPanel = new JPanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		nwCornerPanel.setBackground(Color.black);
		this.add(nwCornerPanel, gbc);

		// West Border Of Game Board
		westGameBoardBorder = new JPanel();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		westGameBoardBorder.setMinimumSize(outerBorderY);
		westGameBoardBorder.setPreferredSize(outerBorderY);
		this.add(westGameBoardBorder, gbc);
		
		middlePanel = new JPanel();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(middlePanel, gbc);
		
		
		// North East border square.
		neCornerPanel = new JPanel();
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		neCornerPanel.setBackground(Color.black);
		this.add(neCornerPanel, gbc);
		
		// East Border of Game Board
		eastGameBoardBorder = new JPanel();
		gbc.gridx = 2;
		gbc.gridy = 1;
		eastGameBoardBorder.setMinimumSize(outerBorderY);
		eastGameBoardBorder.setPreferredSize(outerBorderY);
		this.add(eastGameBoardBorder, gbc);
		
		// Bottom Left Border of the Grid Panel.
		swCornerPanel = new JPanel();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.BOTH;
		swCornerPanel.setBackground(Color.black);
		this.add(swCornerPanel, gbc);
		
		// Bottom Border of the Grid Panel.
		southGameBoardBorder = new JPanel();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		southGameBoardBorder.setMinimumSize(outerBorderX);
		southGameBoardBorder.setPreferredSize(outerBorderX);
		this.add(southGameBoardBorder, gbc);
		
		// Bottom Right border square.
		seCornerPanel = new JPanel();
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.BOTH;
		seCornerPanel.setBackground(Color.black);
		this.add(seCornerPanel, gbc);
		
		
	}
	
	public void setSouthBorderBG(Color c)
	{
		this.southGameBoardBorder.setBackground(c);
	}
	
	public void setWestBorderBG(Color c)
	{
		this.westGameBoardBorder.setBackground(c);
	}
	
	public void setNorthBorderBG(Color c)
	{
		this.northGameBoardBorder.setBackground(c);
	}
	
	public void setEastBorderBG(Color c)
	{
		this.eastGameBoardBorder.setBackground(c);
	}
	
	

}
