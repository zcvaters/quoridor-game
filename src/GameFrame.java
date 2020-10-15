import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{
    public GameFrame()
    {
        super("Quoridor");
        Dimension frameSize = new Dimension(1000, 1000);
        setSize(frameSize);
        setPreferredSize(frameSize);
        Menu newMenu = new Menu();
        add(newMenu);


        setVisible(true);
    }


    public static void main(String[] args)
    {
        GameFrame newFrame = new GameFrame();
    }
}
