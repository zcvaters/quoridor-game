import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenuTest extends JFrame {
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton a3Button;
    private JButton a4Button;
    private JButton button5;
    private JButton button6;

    public MainMenuTest() {
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

    public static void main(String[] args) {
        MainMenuTest asdasd = new MainMenuTest();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
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
        button1.setLabel("1");
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
        a3Button = new JButton();
        a3Button.setText("3");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 100;
        gbc.insets = new Insets(0, 50, 200, 50);
        panel2.add(a3Button, gbc);
        a4Button = new JButton();
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
        button5.setLabel("Back To Menu");
        button5.setText("Back To Menu");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 50;
        gbc.ipady = 50;
        gbc.insets = new Insets(50, 0, 0, 0);
        panel2.add(button5, gbc);
        button6 = new JButton();
        button6.setLabel("Next");
        button6.setName("");
        button6.setText("Next");
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
        Font label1Font = this.$$$getFont$$$(null, -1, 48, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setIconTextGap(4);
        label1.setInheritsPopupMenu(true);
        label1.setText("Click On Number Of Human players");
        panel3.add(label1, BorderLayout.CENTER);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
