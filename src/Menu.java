import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends MainPanel{
    private JPanel topPanel;
    private JButton button1, button2, button3, button4;
    private Color background;
    Icon quoridorTitleIcon, newGameIcon, loadGameIcon, howToPlayIcon, settingsIcon;
    private JLabel quoridorTitle;

    public Menu(){
        background = new Color(253, 186, 154);
        setLayout(new BorderLayout(0, 0));
        topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        Dimension panelSizes = new Dimension(1000,1000);
        topPanel.setSize(1000, 1000);
        topPanel.setPreferredSize(panelSizes);
        topPanel.setBackground(background);
        topPanel.setVisible(true);
        add(topPanel);



        setMenu();



        setVisible(true);
    }


    public void setMenu (){
        // Import Image Assets for Menu Icons.
        newGameIcon = new ImageIcon("Assets/MainMenu/NewGameButton.png");
        loadGameIcon = new ImageIcon("Assets/MainMenu/LoadGameButton.png");
        howToPlayIcon = new ImageIcon("Assets/MainMenu/HowToPlayButton.png");
        settingsIcon = new ImageIcon("Assets/MainMenu/SettingsButton.png");
        quoridorTitleIcon = new ImageIcon("Assets/MainMenu/Quoridor_title.png");

        // Creating a GridBagConstraint to order components.
        GridBagConstraints gbc = new GridBagConstraints();
        quoridorTitle = new JLabel(quoridorTitleIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;

        topPanel.add(quoridorTitle);
        gbc.insets = new Insets(10, 10, 10, 10);

        button1 = new JButton(newGameIcon);
        gbc.gridx = 0;
        gbc.gridy = -1;

        gbc.gridheight = newGameIcon.getIconHeight();
        gbc.gridwidth = newGameIcon.getIconWidth();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        button1.setBackground(background);
        button1.setForeground(background);
        button1.setBorderPainted(false);
        button1.setFocusPainted(false);
        button1.setContentAreaFilled(false);
        //button1.setContentAreaFilled(false);
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("New Game");
                topPanel.setVisible(false);
                topPanel.removeAll();
                SetUpGame newGame = new SetUpGame();
                topPanel.add(newGame);
                topPanel.setVisible(true);
                // Go to next panel, set up game.
            }
        });
        topPanel.add(button1, gbc);

        button2 = new JButton(loadGameIcon);
        gbc.gridx = 0;
        gbc.gridy = -2;

        button2.setBackground(background);
        button2.setForeground(background);
        button2.setBorderPainted(false);
        button2.setFocusPainted(false);
        button2.setContentAreaFilled(false);
        //button1.setContentAreaFilled(false);
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Load Game");
                // Go to next panel, resume game files.
            }
        });
        topPanel.add(button2, gbc);
        button3 = new JButton(howToPlayIcon);
        gbc.gridx = 0;
        gbc.gridy = -3;

        button3.setBackground(background);
        button3.setForeground(background);
        button3.setBorderPainted(false);
        button3.setFocusPainted(false);
        button3.setContentAreaFilled(false);
        //button1.setContentAreaFilled(false);
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("How To Play");
                // Go to next panel, how to play.
            }
        });

        topPanel.add(button3, gbc);

        button4 = new JButton(settingsIcon);
        gbc.gridx = 0;
        gbc.gridy = -4;

        button4.setBackground(background);
        button4.setForeground(background);
        button4.setBorderPainted(false);
        button4.setFocusPainted(false);
        button4.setContentAreaFilled(false);
        button4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Settings");
                // Go to next panel, settings.
            }
        });
        //button1.setContentAreaFilled(false);

        topPanel.add(button4, gbc);

    }
    public static void main(String[] args)
    {
        Menu buttons = new Menu();

        //pane.getContentPane().add(buttons);
        //pane.setVisible(true);
    }

}
