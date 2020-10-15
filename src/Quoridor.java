import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;

//Driver class for Quoridor Board Game


public class Quoridor extends MainPanel{

	public Quoridor()throws IOException
	{
		setLayout(new BorderLayout());
		InputManager runGame = new InputManager(9, 9);
		GameBoard newGame = new GameBoard(9, 9, runGame);
		add(newGame);
	}

	
}
