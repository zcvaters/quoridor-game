import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainPanel extends JPanel {


    public MainPanel()
    {
        //super("Quoridor");

        setLayout(new BorderLayout());
        Dimension frameSize = new Dimension(1000, 1000);
        setPreferredSize(frameSize);
        setSize(frameSize);

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);
        setVisible(true);
    }

    //public static void main(String [] args)
    //{
        //MainPanel main = new MainPanel();
    //}
}
