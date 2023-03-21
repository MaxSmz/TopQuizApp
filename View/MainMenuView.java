package TopQuizSystem.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainMenuView extends JFrame {
    private JButton age5to10Button;
    private JButton age11to15Button;
    private JButton age16to20Button,switchButton;
    private JLabel titleLabel;
    private CardLayout cardLayout;
    private JPanel cards;
    private JCheckBox checkBox1,checkBox2,checkBox3;
    private JTextField firstNameText,lastNameText;

    public MainMenuView() {
        super("Test System");
        setSize(500, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        titleLabel = new JLabel("Welcome to TopQuiz!");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Three age group button.
        age5to10Button = new JButton("5-10 years old");
        age11to15Button = new JButton("11-15 years old");
        age16to20Button = new JButton("16-20 years old");
        age5to10Button.setPreferredSize(new Dimension(300, 25));
        age11to15Button.setPreferredSize(new Dimension(300, 25));
        age16to20Button.setPreferredSize(new Dimension(300, 25));

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        JPanel panel1 = panel1();
        JPanel panel2 = panel2();
        JPanel panel3 = panel3();

        cards.add(panel1,"panel1");
        cards.add(panel2,"panel2");
        cards.add(panel3,"panel3");

        switchButton = new JButton("Continue");
        switchButton.setPreferredSize(new Dimension(100, 25));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(switchButton);

        mainPanel.add(titleLabel,BorderLayout.NORTH);
        mainPanel.add(cards,BorderLayout.CENTER);
        mainPanel.add(buttonPanel,BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void addController(ActionListener controller) {
        switchButton.addActionListener(controller);
        age5to10Button.addActionListener(controller);
        age11to15Button.addActionListener(controller);
        age16to20Button.addActionListener(controller);
    }

    /**
     * Panel1 used to show user registration window.
     * @return
     */
    public JPanel panel1() {
        JPanel panel = new JPanel(new GridLayout(2,1,10,10));
        JLabel label = new JLabel("Please give your First name and Last name: " );
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.RED);

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridLayout(2,2));
        JLabel firstNameLabel = new JLabel("First name: ");
        firstNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lastNameLabel = new JLabel("Last name: ");
        lastNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        firstNameText = new JTextField(20);
        firstNameText.setPreferredSize(new Dimension(200,30));
        firstNameText.setMargin(new Insets(2,2,2,2));

        lastNameText = new JTextField(20);
        lastNameText.setPreferredSize(new Dimension(200,30));
        lastNameText.setMargin(new Insets(2,2,2,2));

        JPanel textPanel1 = new JPanel();
        JPanel textPanel2 = new JPanel();

        textPanel1.add(firstNameText);
        textPanel2.add(lastNameText);

        subPanel.add(firstNameLabel);
        subPanel.add(textPanel1);
        subPanel.add(lastNameLabel);
        subPanel.add(textPanel2);

        panel.add(label);
        panel.add(subPanel);
        return panel;
    }

    /**
     * Panel2 used to show question category selection window.
     * @return
     */
    public JPanel panel2() {
        JPanel panel = new JPanel(new GridLayout(2,1,10,10));
        JLabel label = new JLabel("Please select the question type you would like to answer: " );
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.RED);
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridLayout(1,3));

        checkBox1 = new JCheckBox("Geography");
        checkBox1.setFont(new Font("Arial", Font.PLAIN, 16));
        checkBox2 = new JCheckBox("Science");
        checkBox2.setFont(new Font("Arial", Font.PLAIN, 16));
        checkBox3 = new JCheckBox("Dinosaurs");
        checkBox3.setFont(new Font("Arial", Font.PLAIN, 16));
        checkBox1.setHorizontalAlignment(SwingConstants.CENTER);
        checkBox2.setHorizontalAlignment(SwingConstants.CENTER);
        checkBox3.setHorizontalAlignment(SwingConstants.CENTER);


        subPanel.add(checkBox1);
        subPanel.add(checkBox2);
        subPanel.add(checkBox3);

        panel.add(label);
        panel.add(subPanel);
        return panel;
    }

    /**
     * Panel3 used to show age group selection window.
     * @return
     */
    public JPanel panel3() {
        JPanel panel = new JPanel(new GridLayout(2,1,10,10));
        JLabel label = new JLabel("Please select your age group: " );
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.RED);
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridLayout(3,1));

        JPanel btn1Panel = new JPanel();
        JPanel btn2Panel = new JPanel();
        JPanel btn3Panel = new JPanel();

        btn1Panel.add(age5to10Button);
        btn2Panel.add(age11to15Button);
        btn3Panel.add(age16to20Button);

        subPanel.add(btn1Panel);
        subPanel.add(btn2Panel);
        subPanel.add(btn3Panel);

        panel.add(label);
        panel.add(subPanel);
        return panel;
    }


    public List<String> getQuestionCategory() {
        List<String> types = new ArrayList<>();
        if (checkBox1.isSelected()) {
            types.add("Geography");
        }
        if (checkBox2.isSelected()) {
            types.add("Science");
        }
        if (checkBox3.isSelected()) {
            types.add("Dinosaurs");
        }
        return types;
    }

    public JCheckBox getCheckBox1() {
        return checkBox1;
    }

    public JCheckBox getCheckBox2() {
        return checkBox2;
    }

    public JCheckBox getCheckBox3() {
        return checkBox3;
    }

    public String getFirstName() {
        return firstNameText.getText();
    }

    public String getLastName() {
        return lastNameText.getText();
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getCards() {
        return cards;
    }

    public JButton getSwitchButton() {
        return switchButton;
    }

    public JButton getAge5to10Button() {
        return age5to10Button;
    }

    public JButton getAge11to15Button() {
        return age11to15Button;
    }

    public JButton getAge16to20Button() {
        return age16to20Button;
    }
}