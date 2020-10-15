import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Set;

public class SetUpGame extends MainPanel{
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private GridBagConstraints gbc;

    public SetUpGame()
    {
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        panel1.setMinimumSize(new Dimension(1000, 1000));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        panel2.setAlignmentX(0.5f);
        panel2.setMaximumSize(new Dimension(1000, 500));
        panel2.setMinimumSize(new Dimension(1000, 500));
        panel2.setPreferredSize(new Dimension(1000, 700));
        panel1.add(panel2, BorderLayout.CENTER);
        button1 = new JButton();
        button1.setDoubleBuffered(false);
        button1.setText("1");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.ipadx = 100;
        gbc.ipady = 100;
        gbc.insets = new Insets(0, 50, 200, 50);
        panel2.add(button1, gbc);
        button2 = new JButton();
        button2.setText("2");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 100;
        gbc.insets = new Insets(0, 50, 200, 50);
        panel2.add(button2, gbc);
        JButton a3Button = new JButton();
        a3Button.setText("3");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 100;
        gbc.insets = new Insets(0, 50, 200, 50);
        panel2.add(a3Button, gbc);
        JButton a4Button = new JButton();
        a4Button.setText("4");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 100;
        gbc.insets = new Insets(0, 50, 200, 50);
        panel2.add(a4Button, gbc);
        button5 = new JButton();
        button5.setHorizontalAlignment(0);
        button5.setText("Back To Menu");
        button5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                removeAll();
                add(new Menu());

                revalidate();
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 50;
        gbc.ipady = 50;
        gbc.insets = new Insets(50, 0, 0, 0);
        panel2.add(button5, gbc);
        button6 = new JButton();
        button6.setName("");
        button6.setText("Next");
        button6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                removeAll();
                try {
                    add(new Quoridor());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                revalidate();
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 50;
        gbc.ipady = 50;
        gbc.insets = new Insets(50, 0, 0, 0);
        panel2.add(button6, gbc);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout(0, 0));
        panel3.setPreferredSize(new Dimension(1000, 300));
        panel1.add(panel3, BorderLayout.NORTH);
        final JLabel label1 = new JLabel();
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setIconTextGap(4);
        label1.setInheritsPopupMenu(true);
        label1.setText("Click On Number Of Human players");
        panel3.add(label1, BorderLayout.CENTER);
        add(panel1);


    }



}
