import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class FenceUIManager {

	private JPanel mainPane;

	private List<JPanel> southFences;
	private List<JPanel> westFences;
	private List<JPanel> northFences;
	private List<JPanel> eastFences;
	private List<JPanel> allFences;

	private JPanel southFencesPanel;

	private JPanel westFencesPanel;

	private JPanel northFencesPanel;

	private JPanel eastFencesPanel;

	/*
	 * New game Fence UI Manager
	 * 
	 */
	public FenceUIManager() {

		mainPane = new JPanel();
		mainPane.setLayout(null);
		mainPane.setBounds(0, 0, 1200, 800);
		mainPane.setVisible(true);
		mainPane.setOpaque(false);
		southFences = new ArrayList<>();
		westFences = new ArrayList<>();
		northFences = new ArrayList<>();
		eastFences = new ArrayList<>();
	}

	/* returns collection of all fences combined */
	public List<JPanel> getAllFences() {
		return allFences;
	}

	public void setAllFences(List<JPanel> allFences) {
		this.allFences = allFences;
	}

	/*
	 * Fences are stored in a collection of their respective side. Function removes
	 * one of these fences, at random.
	 * 
	 * @param: ArrayList<JPanel> fences
	 */
	public void removePlayerFence(Player players) {
		int wallsRemaining = players.GetWallsRemaining();
		Player[] player = GameSettings.getPlayers();
		if (players.GetTurnPosition() == player[0].GetTurnPosition()) {

			for (int i = southFences.size() - 1; i >= wallsRemaining; i--) {
				if (southFences.get(i).isVisible()) {
					southFences.get(i).setVisible(false);
				}

			}
		}
		if (players.GetTurnPosition() == player[1].GetTurnPosition()) {
			for (int i = westFences.size() - 1; i >= wallsRemaining; i--) {
				if (westFences.get(i).isVisible()) {
					westFences.get(i).setVisible(false);
				}

			}
		}
		if (players.GetTurnPosition() == player[2].GetTurnPosition()) {
			for (int i = northFences.size() - 1; i >= wallsRemaining; i--) {
				if (northFences.get(i).isVisible()) {
					northFences.get(i).setVisible(false);
				}

			}
		}
		if (players.GetTurnPosition() == player[3].GetTurnPosition()) {
			for (int i = eastFences.size() - 1; i >= wallsRemaining; i--) {
				if (eastFences.get(i).isVisible()) {
					eastFences.get(i).setVisible(false);
				}

			}
		}
	}

	public void setWestFencesPanel(JPanel westFencesPanel) {
		this.westFencesPanel = westFencesPanel;
	}

	public void removeMultipleFences(List<JPanel> fences, int amount) {
		// TODO If needed.
	}

	// Creates South Player Fences
	public void createSouthPlayerFences() {
		southFencesPanel = new JPanel();
		southFencesPanel.setLayout(null);
		southFencesPanel.setOpaque(true);
		JPanel southFence1 = new JPanel();
		southFence1.setLayout(null);
		southFence1.setBounds(0, 0, 15, 50);
		southFence1.setBackground(Color.blue);
		southFencesPanel.add(southFence1);
		southFences.add(southFence1);
		JPanel southFence2 = new JPanel();
		southFence2.setLayout(null);
		southFence2.setBounds(25, 0, 15, 50);
		southFence2.setBackground(Color.blue);
		southFencesPanel.add(southFence2);
		southFences.add(southFence2);
		JPanel southFence3 = new JPanel();
		southFence3.setLayout(null);
		southFence3.setBounds(50, 0, 15, 50);
		southFence3.setBackground(Color.blue);
		southFencesPanel.add(southFence3);

		southFences.add(southFence3);
		JPanel southFence4 = new JPanel();
		southFence4.setBounds(75, 0, 15, 50);
		southFence4.setBackground(Color.blue);
		southFencesPanel.add(southFence4);
		southFences.add(southFence4);
		JPanel southFence5 = new JPanel();
		southFence5.setLayout(null);
		southFence5.setBounds(100, 0, 15, 50);
		southFence5.setBackground(Color.blue);
		southFencesPanel.add(southFence5);
		southFences.add(southFence5);

		southFencesPanel.setOpaque(false);
		setAllFencesAttributes(southFences);
	}

	public void createWestPlayerFences() {
		westFencesPanel = new JPanel();
		westFencesPanel.setLayout(null);
		westFencesPanel.setOpaque(true);
		JPanel westFence1 = new JPanel();
		westFence1.setLayout(null);
		westFence1.setBounds(0, 0, 15, 50);
		westFence1.setBackground(Color.blue);
		westFencesPanel.add(westFence1);
		westFences.add(westFence1);
		JPanel westFence2 = new JPanel();
		westFence2.setLayout(null);
		westFence2.setBounds(25, 0, 15, 50);
		westFence2.setBackground(Color.blue);
		westFencesPanel.add(westFence2);
		westFences.add(westFence2);
		JPanel westFence3 = new JPanel();
		westFence3.setLayout(null);
		westFence3.setBounds(50, 0, 15, 50);
		westFence3.setBackground(Color.blue);
		westFencesPanel.add(westFence3);

		westFences.add(westFence3);
		JPanel westFence4 = new JPanel();
		westFence4.setBounds(75, 0, 15, 50);
		westFence4.setBackground(Color.blue);
		westFencesPanel.add(westFence4);
		westFences.add(westFence4);
		JPanel westFence5 = new JPanel();
		westFence5.setLayout(null);
		westFence5.setBounds(100, 0, 15, 50);
		westFence5.setBackground(Color.blue);
		westFencesPanel.add(westFence5);
		westFences.add(westFence5);

		westFencesPanel.setOpaque(false);
		setAllFencesAttributes(westFences);
	}

	public void createNorthPlayerFences() {
		northFencesPanel = new JPanel();
		northFencesPanel.setLayout(null);
		northFencesPanel.setOpaque(true);
		JPanel northFence1 = new JPanel();
		northFence1.setLayout(null);
		northFence1.setBounds(0, 0, 15, 50);
		northFence1.setBackground(Color.blue);
		northFencesPanel.add(northFence1);
		northFences.add(northFence1);
		JPanel northFence2 = new JPanel();
		northFence2.setLayout(null);
		northFence2.setBounds(25, 0, 15, 50);
		northFence2.setBackground(Color.blue);
		northFencesPanel.add(northFence2);
		northFences.add(northFence2);
		JPanel northFence3 = new JPanel();
		northFence3.setLayout(null);
		northFence3.setBounds(50, 0, 15, 50);
		northFence3.setBackground(Color.blue);
		northFencesPanel.add(northFence3);

		northFences.add(northFence3);
		JPanel northFence4 = new JPanel();
		northFence4.setBounds(75, 0, 15, 50);
		northFence4.setBackground(Color.blue);
		northFencesPanel.add(northFence4);
		northFences.add(northFence4);
		JPanel northFence5 = new JPanel();
		northFence5.setLayout(null);
		northFence5.setBounds(100, 0, 15, 50);
		northFence5.setBackground(Color.blue);
		northFencesPanel.add(northFence5);
		northFences.add(northFence5);

		northFencesPanel.setOpaque(false);
		setAllFencesAttributes(northFences);
	}

	public void createEastPlayerFences() {
		eastFencesPanel = new JPanel();
		eastFencesPanel.setLayout(null);
		eastFencesPanel.setOpaque(true);
		JPanel eastFence1 = new JPanel();
		eastFence1.setLayout(null);
		eastFence1.setBounds(0, 0, 15, 50);
		eastFence1.setBackground(Color.blue);
		eastFencesPanel.add(eastFence1);
		eastFences.add(eastFence1);
		JPanel eastFence2 = new JPanel();
		eastFence2.setLayout(null);
		eastFence2.setBounds(25, 0, 15, 50);
		eastFence2.setBackground(Color.blue);
		eastFencesPanel.add(eastFence2);
		eastFences.add(eastFence2);
		JPanel eastFence3 = new JPanel();
		eastFence3.setLayout(null);
		eastFence3.setBounds(50, 0, 15, 50);
		eastFence3.setBackground(Color.blue);
		eastFencesPanel.add(eastFence3);
		eastFences.add(eastFence3);
		JPanel eastFence4 = new JPanel();
		eastFence4.setBounds(75, 0, 15, 50);
		eastFence4.setBackground(Color.blue);
		eastFencesPanel.add(eastFence4);
		eastFences.add(eastFence4);
		JPanel eastFence5 = new JPanel();
		eastFence5.setLayout(null);
		eastFence5.setBounds(100, 0, 15, 50);
		eastFence5.setBackground(Color.blue);
		eastFencesPanel.add(eastFence5);
		eastFences.add(eastFence5);

		eastFencesPanel.setOpaque(false);
		setAllFencesAttributes(eastFences);
	}



	public void setEastFencesPanel(JPanel eastFencesPanel) {
		this.eastFencesPanel = eastFencesPanel;
	}

	public void setEastFences(List<JPanel> eastFences) {
		this.eastFences = eastFences;
	}

	public void setNorthFencesPanel(JPanel northFencesPanel) {
		this.northFencesPanel = northFencesPanel;
	}

	public void setAllFencesAttributes(List<JPanel> fences) {
		GameTile[][] tiles = GameSettings.getGameTiles();
		Color fenceColors = tiles[0][0].getWallColor();
		fences.forEach(fence -> {
			fence.setBackground(fenceColors);
			fence.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		});
	}

	public void setWestFences(List<JPanel> westFences) {
		this.westFences = westFences;
	}
	
	public void setSouthFences(List<JPanel> southFences) {
		this.southFences = southFences;
	}
	
	public JPanel getSouthFencesPanel() {
		return southFencesPanel;
	}
	
	// Packages all fences into one collection
	public void packageAllFences() {
		allFences = new ArrayList<>();
		allFences.add(southFencesPanel);
		allFences.add(westFencesPanel);
		allFences.add(northFencesPanel);
		allFences.add(eastFencesPanel);
	}

	public List<JPanel> getWestFences() {
		return westFences;
	}

	public void setSouthFencesPanel(JPanel southFencesPanel) {
		this.southFencesPanel = southFencesPanel;
	}

	public List<JPanel> getSouthFences() {
		return southFences;
	}

	public List<JPanel> getNorthFences() {
		return northFences;
	}

	public void setNorthFences(List<JPanel> northFences) {
		this.northFences = northFences;
	}

	public List<JPanel> getEastFences() {
		return eastFences;
	}
	public JPanel getNorthFencesPanel() {
		return northFencesPanel;
	}
	
	public JPanel getEastFencesPanel() {
		return eastFencesPanel;
	}
	
	public JPanel getWestFencesPanel() {
		return westFencesPanel;
	}
	
}
