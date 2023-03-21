package TopQuizSystem.View;

import TopQuizSystem.Model.Question;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class QuestionView extends JFrame {
    private JLabel contentLabel;
    private JTextField answerField;
    private JButton nextButton;
    private JButton backButton;
    private JLabel progressLabel,hintPanel,categoryLabel;
    private JPanel panel,questionPanel, topPanel,topBigPanel,titlePanel;
    private Question question;
    private int questionIndex,totalQuestions;
    private JCheckBox checkBox1,checkBox2,checkBox3,checkBox4;
    private JRadioButton radioBtn1,radioBtn2;
    private List<String> answers;
    private JProgressBar progressBar;


    public QuestionView() {
        setTitle("TopQuiz Test");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 'bigPanel' is set as content panel, it contains 'topBigPanel' and 'panel'.
        JPanel bigPanel = new JPanel(new BorderLayout());

        // 'panel' is used to contain 'question content label', 'question panel' and 'hintPanel'.
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBorder(new EtchedBorder(EtchedBorder.RAISED));

        // 'topBigPanel' contains 'topPanel' and 'progressBar'.
        topBigPanel = new JPanel(new GridLayout(2, 1, 1, 1));
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true); // Show percentage.

        // 'topPanel' contains 'Next button', 'question progress label' and 'Back button'.
        topPanel = new JPanel();
        progressLabel = new JLabel();
        nextButton = new JButton("Next");
        backButton = new JButton("Back");
        topPanel.setLayout(new GridLayout(1, 3, 1,1 ));
        topPanel.add(backButton);
        topPanel.add(progressLabel);
        topPanel.add(nextButton);
        topPanel.add(progressBar);

        topBigPanel.add(topPanel);
        topBigPanel.add(progressBar);
        topBigPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));

        bigPanel.add(panel,BorderLayout.CENTER);
        bigPanel.add(topBigPanel,BorderLayout.NORTH);

        setContentPane(bigPanel);
        setVisible(false);
        setLocationRelativeTo(null);
    }

    /**
     * Set question title, question options, question hint and progress bar to the panel.
     */
    public void addQuestion() {
        removeQuestion();
        answers = Arrays.asList(question.getAnswer().split(","));

        // 'titlePanel' used to show question category and question title.
        titlePanel = new JPanel(new GridLayout(2,1));
        categoryLabel = new JLabel("["+ question.getCategory() +"]");
        categoryLabel.setForeground(Color.ORANGE);
        categoryLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // 'contentLabel' used to show question title.
        contentLabel = new JLabel(question.getContent());
        contentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        titlePanel.add(categoryLabel);
        titlePanel.add(contentLabel);

        // 'questionPanel' used to show question options or input text frame.
        questionPanel = new JPanel();
        questionPanel.setName("questionPanel");

        // 'hintPanel' used to show the message related to the question.
        hintPanel = new JLabel();
        hintPanel.setHorizontalAlignment(SwingConstants.CENTER);
        hintPanel.setBorder(new TitledBorder(new LineBorder(Color.black, 1), "Hint"));

        // 'progressLabel' used to show the progress of question.
        progressLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        progressLabel.setText("Question " + (questionIndex + 1) + " of " + totalQuestions);
        progressLabel.setHorizontalAlignment(SwingConstants.CENTER);

        if (question.getType().equals("Multiple Choice")) {
            hintPanel.setText("<html>This is a Multiple Choice question.<br>" +
                    "Please select all the choices you think are correct.<br>" +
                    "No points are awarded for multiple or wrong choices.</html>");
            String option1 = question.getOptions()[0];
            checkBox1 = new JCheckBox(option1);
            checkBox1.setFont(new Font("Arial", Font.PLAIN, 16));
            if (answers.contains(option1)) {
                checkBox1.setActionCommand("1");
            } else {
                checkBox1.setActionCommand("0");
            }
            questionPanel.add(checkBox1);

            String option2 = question.getOptions()[1];
            checkBox2 = new JCheckBox(option2);
            checkBox2.setFont(new Font("Arial", Font.PLAIN, 16));
            if (answers.contains(option2)) {
                checkBox2.setActionCommand("1");
            } else {
                checkBox2.setActionCommand("0");
            }
            questionPanel.add(checkBox2);

            String option3 = question.getOptions()[2];
            checkBox3 = new JCheckBox(option3);
            checkBox3.setFont(new Font("Arial", Font.PLAIN, 16));
            if (answers.contains(option3)) {
                checkBox3.setActionCommand("1");
            } else {
                checkBox3.setActionCommand("0");
            }
            questionPanel.add(checkBox3);

            String option4 = question.getOptions()[3];
            checkBox4 = new JCheckBox(option4);
            checkBox4.setFont(new Font("Arial", Font.PLAIN, 16));
            if (answers.contains(option4)) {
                checkBox4.setActionCommand("1");
            } else {
                checkBox4.setActionCommand("0");
            }
            questionPanel.add(checkBox4);

        } else if (question.getType().equals("True or False")) {
            hintPanel.setText("<html>This is a True or False question.<br>" +
                    "Please select one choice you think are correct.</html>");
            String option1 = question.getOptions()[0];
            radioBtn1 = new JRadioButton(option1);
            radioBtn1.setFont(new Font("Arial", Font.PLAIN, 16));
            if (answers.contains(option1)) {
                radioBtn1.setActionCommand("1");
            } else {
                radioBtn1.setActionCommand("0");
            }
            questionPanel.add(radioBtn1);

            String option2 = question.getOptions()[1];
            radioBtn2 = new JRadioButton(option2);
            radioBtn2.setFont(new Font("Arial", Font.PLAIN, 16));
            if (answers.contains(option2)) {
                radioBtn2.setActionCommand("1");
            } else {
                radioBtn2.setActionCommand("0");
            }
            questionPanel.add(radioBtn2);

            ButtonGroup group = new ButtonGroup();
            group.add(radioBtn1);
            group.add(radioBtn2);

        } else if (question.getType().equals("Short Answer")) {
            hintPanel.setText("<html>This is a Short Answer question.<br>" +
                    "Please fill in what you think is the right answer.</html>");
            answerField = new JTextField(20);
            questionPanel.add(answerField);
        }

        double curNum = (double) (questionIndex+1)/totalQuestions *100;
        progressBar.setValue((int)(curNum));

        panel.add(titlePanel);
        panel.add(questionPanel);
        panel.add(hintPanel);
    }

    /**
     * Delete all components, update layout and refresh the panel.
     */
    public void removeQuestion() {
        panel.removeAll(); // 删除所有组件
        panel.revalidate(); // 更新布局
        panel.repaint(); // 刷新界面
    }

    /**
     * Add actionListener for the button.
     * @param controller
     */
    public void addController(ActionListener controller) {
        nextButton.addActionListener(controller);
        backButton.addActionListener(controller);
    }

//    /**
//     * Used to get specific component by the name.
//     * @param container The container the component belongs to.
//     * @param name The component's name.
//     * @return
//     */
//    public Component getComponentByName(Container container, String name) {
//        Component[] components = container.getComponents();
//        for (Component component : components) {
//            if (component.getName() != null && component.getName().equals(name)) {
//                return component;
//            }
//            if (component instanceof Container) {
//                Component subComponent = getComponentByName((Container) component, name);
//                if (subComponent != null) {
//                    return subComponent;
//                }
//            }
//        }
//        return null;
//    }
//
//    /**
//     *
//     * @param options
//     */
//    public void setOptions(String[] options) {
//        JPanel optionPanel = (JPanel) this.getComponentByName(this,"optionsPanel");
//        if (optionPanel != null) {
//            Component[] optionButtons = optionPanel.getComponents();
//            for (int i = 0; i < optionButtons.length; i++) {
//                if (optionButtons[i] instanceof JRadioButton) {
//                    ((JRadioButton) optionButtons[i]).setText(options[i]);
//                }
//            }
//        }
//
//    }




    /**
     * Check if the answer of multiple choice question lead to score 0.
     * @param checkBox
     * @return
     */
    public boolean checkAnswer(JCheckBox checkBox) {
        if (Objects.equals(checkBox.getActionCommand(), "0")) {
            return false;
        }
        return true;
    }

    /**
     * Return a boolean list shows which check box are selected.
     * @return
     */
    public boolean[] checkIfCheckBoxSelected() {
        return new boolean[]{checkBox1.isSelected(),checkBox2.isSelected(),checkBox3.isSelected(),checkBox4.isSelected()};
    }

    /**
     * Return a boolean list shows which radio button are selected.
     * @return
     */
    public boolean[] checkIfRadioButtonSelected() {
        return new boolean[]{radioBtn1.isSelected(),radioBtn2.isSelected()};
    }

    /**
     * Calculate score of the multiple choice question,
     * user get one point for correct choices,
     * but don't get any points for multiple or wrong choices.
     * @return
     */
    public int calculateMultipleChoiceScore() {
        boolean[] booleans = checkIfCheckBoxSelected();
        boolean isAnswerCorrect = true;
        int score = 0;

        if (booleans[0]) {
            isAnswerCorrect = checkAnswer(checkBox1);
            score += Integer.parseInt(checkBox1.getActionCommand());
        }
        if (booleans[1]) {
            isAnswerCorrect = checkAnswer(checkBox2);
            score += Integer.parseInt(checkBox2.getActionCommand());
        }
        if (booleans[2]) {
            isAnswerCorrect = checkAnswer(checkBox3);
            score += Integer.parseInt(checkBox3.getActionCommand());
        }
        if (booleans[3]) {
            isAnswerCorrect = checkAnswer(checkBox4);
            score += Integer.parseInt(checkBox4.getActionCommand());
        }

        if (!isAnswerCorrect) {
            return 0;
        }

        return score;
    }

    /**
     * Calculate score of the true or false question.
     * @return
     */
    public int calculateTrueOrFalseScore() {
        boolean[] booleans = checkIfRadioButtonSelected();
        if (booleans[0]) {
            return Integer.parseInt(radioBtn1.getActionCommand());
        } else if (booleans[1]) {
            return Integer.parseInt(radioBtn2.getActionCommand());
        }
        return 0;
    }

    /**
     * Calculate score of the short answer question.
     * @return
     */
    public int calculateShortAnswerScore() {
        if (Objects.equals(question.getAnswer(),answerField.getText())) {
            return 5;
        } else {
            return 0;
        }
    }

    /**
     * This method reads the image file from the question bank and resizes image.
     * @param image
     * @param width
     * @param height
     * @return buffered image object
     */
    public BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }

    /**
     * The method uses the resized image and fits into a label for display.
     * @param filename
     * @param width
     * @param height
     * @exception IOException for the image filename
     * @return JLabel with the resized image
     */
    private JLabel setDisplayPic(String filename, int width , int height){
        BufferedImage displayPic;
        JLabel labelHint = null ;
        try {
            displayPic = ImageIO.read(new File(filename));
            displayPic = resize(displayPic,width,height);
            labelHint = new JLabel(new ImageIcon(displayPic));
            return labelHint;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return labelHint;
    }

    // Setter and getter methods.

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setQuestionIndex(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public void setContent(String content) {
        contentLabel.setText(content);
    }

    public void setBackEnabled(boolean enabled) {
        backButton.setEnabled(enabled);
    }

    public void setNextEnabled(boolean enabled) {
        nextButton.setEnabled(enabled);
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public JButton getBackButton() {
        return backButton;
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

    public JCheckBox getCheckBox4() {
        return checkBox4;
    }

    public JRadioButton getRadioBtn1() {
        return radioBtn1;
    }

    public JRadioButton getRadioBtn2() {
        return radioBtn2;
    }

    public JTextField getAnswerField() {
        return answerField;
    }

    public Question getQuestion() {
        return question;
    }

    public JPanel getPanel() {
        return panel;
    }

}
