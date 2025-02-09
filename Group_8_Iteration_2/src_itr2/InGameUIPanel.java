import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class InGameUIPanel extends JPanel implements ActionListener{
	
	//the outer (border) panels surrounding the gameboard 
	private JPanel nwCornerPanel, neCornerPanel, seCornerPanel, swCornerPanel;
	private JPanel southGameBoardBorder, westGameBoardBorder, northGameBoardBorder, eastGameBoardBorder;
	
	// IngameUIPanel settings button
	private JButton settingsButton;
	
	//the center panel, holds the gameboard
	private JPanel middlePanel;

	//constraints for this layout
	GridBagConstraints gbc = new GridBagConstraints();
	
	//constructor
	public InGameUIPanel() {
		super(); // Creates a panel
		
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
	public void alignedPerimeterBorders() {
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
		
		
		// North West border square.
		nwCornerPanel = new JPanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(nwCornerPanel, gbc);

		// West Border Of Game Board
		westGameBoardBorder = new JPanel();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		westGameBoardBorder.setMinimumSize(outerBorderY);
		westGameBoardBorder.setPreferredSize(outerBorderY);
		this.add(westGameBoardBorder, gbc);
		
		// Center Panel to house game board.
		middlePanel = new JPanel();		
		gbc.gridx = 1;
		gbc.gridy = 1;
		//stretch gridbag layout as necessary to accomodate height/width
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		//align to center
		gbc.anchor = GridBagConstraints.SOUTH;
		//add the middle panel
		this.add(middlePanel, gbc);
		
		// North East border Panel.
		neCornerPanel = new JPanel();
		neCornerPanel.setLayout(new GridBagLayout());
		neCornerPanel.setPreferredSize(new Dimension(99, 99));
		neCornerPanel.setMaximumSize(new Dimension(99, 99));
		// Create settings button.
		settingsButton = new JButton();
		// Set icon to a cog icon
		settingsButton.addActionListener(this);
		settingsButton.setIcon(new ImageIcon(getClass().getResource("/Assets/inGameUiPanel_settingButton.png"))); // Insert icon for settings button
		settingsButton.setContentAreaFilled(false);
		settingsButton.setFocusPainted(false);
		gbc.gridx = 0;
		gbc.gridx = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		// add button to the North East panel
		neCornerPanel.add(settingsButton, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(neCornerPanel, gbc);
		
		// East Border of Game Board
		eastGameBoardBorder = new JPanel();
		gbc.gridx = 2;
		gbc.gridy = 1;
		eastGameBoardBorder.setMinimumSize(outerBorderY);
		eastGameBoardBorder.setPreferredSize(outerBorderY);
		this.add(eastGameBoardBorder, gbc);
		
		// South West Border of the Grid Panel.
		swCornerPanel = new JPanel();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(swCornerPanel, gbc);
		
		// South Border of the game board.
		southGameBoardBorder = new JPanel();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		southGameBoardBorder.setMinimumSize(outerBorderX);
		southGameBoardBorder.setPreferredSize(outerBorderX);
		this.add(southGameBoardBorder, gbc);
		
		// South East border panel.
		seCornerPanel = new JPanel();
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(seCornerPanel, gbc);
		
		
	}
	
	//Getters
	public JPanel GetMiddlePanel() {
		return middlePanel;
	}
	
	public JPanel[] GetCornerPanels() {
		JPanel[] cornerPanels = {nwCornerPanel, neCornerPanel, seCornerPanel, swCornerPanel};
		return cornerPanels;
	}
	
	//Helpers
	
	/*
	 *  Sets South border panel background color.
	 *  	@param: Color c 
	 *  	@returns: void
	 */
	public void setSouthBorderBG(Color c) {
		this.southGameBoardBorder.setBackground(c);
	}
	
	/*
	 *  Sets West border panel background color.
	 *  	@param: Color c
	 *  	@returns: void
	 */
	public void setWestBorderBG(Color c) {
		this.westGameBoardBorder.setBackground(c);
	}
	
	/*
	 *  Sets the North border panel background color.
	 *  	@param: Color c
	 *  	@returns: void
	 */
	public void setNorthBorderBG(Color c) {
		this.northGameBoardBorder.setBackground(c);
	}
	
	/*
	 * 	Sets the East border panel background color.
	 * 		@param: Color c
	 * 		@returns: void
	 */
	public void setEastBorderBG(Color c) {
		this.eastGameBoardBorder.setBackground(c);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO: Implement settings panel.
		System.out.println("Settings");
		
	}
	
	

}
