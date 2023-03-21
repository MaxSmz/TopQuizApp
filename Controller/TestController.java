package TopQuizSystem.Controller;

import TopQuizSystem.Model.Question;
import TopQuizSystem.Resources.QuestionSet1;
import TopQuizSystem.Resources.QuestionSet2;
import TopQuizSystem.Resources.QuestionSet3;
import TopQuizSystem.View.MainMenuView;
import TopQuizSystem.View.QuestionView;
import TopQuizSystem.View.ScoreView;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;

public class TestController implements ActionListener {
    private MainMenuView mainMenuView;
    private QuestionView questionView;
    private ScoreView scoreView;
    private int ageGroup;
    private int questionIndex;
    private List<Question> questions;
    private List<Integer> answers;
    private int score;
    private HashMap<String, Integer> scores_questionType,scores_questionCategory;
    private DefaultCategoryDataset dataset_barChart;
    private DefaultPieDataset dataset_pieChart;
    private String firstName, lastName;
    private List<String> questionCategory;
    private int fullScore;
    private int currPanel = 1;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "535676640";


    public TestController() {
        mainMenuView = new MainMenuView();
        mainMenuView.addController(this);
        questionView = new QuestionView();
        questionView.addController(this);
        scoreView = new ScoreView();
        scoreView.addController(this);
        scores_questionType = new HashMap<String,Integer>();
        scores_questionCategory = new HashMap<String,Integer>();
        dataset_barChart = new DefaultCategoryDataset();
        dataset_pieChart = new DefaultPieDataset();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainMenuView.getSwitchButton()) {
            firstName = mainMenuView.getFirstName();
            lastName = mainMenuView.getLastName();
            if( (currPanel == 1 && validateName()) || (currPanel == 2 && validateQuestionType())){
                if (currPanel == 2){
                    questionCategory = mainMenuView.getQuestionCategory();
                }
                currPanel++;
                if (currPanel > 3) {
                    currPanel = 1;
                }
                mainMenuView.getSwitchButton().setVisible(currPanel != 3);
                mainMenuView.getCardLayout().next(mainMenuView.getCards());
            }
        } else if (e.getSource() == mainMenuView.getAge5to10Button()) {
            ageGroup = 5;
            loadQuestions();
            hideMainMenu();
            showQuestion(0);
        } else if (e.getSource() == mainMenuView.getAge11to15Button()) {
            ageGroup = 11;
            loadQuestions();
            hideMainMenu();
            showQuestion(0);
        } else if (e.getSource() == mainMenuView.getAge16to20Button()) {
            ageGroup = 16;
            loadQuestions();
            hideMainMenu();
            showQuestion(0);
        } else if (e.getSource() == questionView.getNextButton()) {
            if (validateAnswer()) {
                saveAnswer();
                questionIndex++;
                if (questionIndex >= questions.size()) {
                    hideQuestionView();
                    showScore();
                } else {
                    showQuestion(questionIndex);
                }
            }
        } else if (e.getSource() == questionView.getBackButton()) {
            if (questionIndex > 0) {
                questionIndex--;
                showQuestion(questionIndex);
            }
        } else if (e.getSource() == scoreView.getRestartButton()) {
            reset();
            mainMenuView.setVisible(true);
        } else if (e.getSource() == scoreView.getQuitButton()) {
            System.exit(0);
        } else if (e.getSource() == scoreView.getSwitchButton()) {
            scoreView.getCardLayout().next(scoreView.getCards());
        }
    }

    private void loadQuestions() {
        /**
         *         For the purpose of using an executable jar file to run the system,
         *         I put Question data in QuestionSet1 class,
         *         which can get questions just like get from MySql database by the commented code below.
         */

//        questions = new ArrayList<>();
//        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
//            String query = "SELECT * FROM questions WHERE age_group <= ? and category in (?,?,?) ORDER BY RAND() LIMIT 10";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setInt(1, ageGroup);
//            statement.setString(2, questionCategory.contains("Dinosaurs") ? "Dinosaurs" : "");
//            statement.setString(3, questionCategory.contains("Science") ? "Science" : "");
//            statement.setString(4, questionCategory.contains("Geography") ? "Geography" : "");
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                String type = resultSet.getString("type");
//                String content = resultSet.getString("content");
//                String[] options = resultSet.getString("options").split(",");
//                String answer = resultSet.getString("answer");
//                String category = resultSet.getString("category");
//                questions.add(new Question(type, content, options, answer,category));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        questions = new ArrayList<>();
        if (questionCategory.contains("Dinosaurs")){
            questions.addAll(QuestionSet1.getTestData());
        }
        if (questionCategory.contains("Science")){
            questions.addAll(QuestionSet2.getTestData());
        }
        if (questionCategory.contains("Geography")){
            questions.addAll(QuestionSet3.getTestData());
        }

        Collections.shuffle(questions);
        answers = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            answers.add(0);
            if (Objects.equals(questions.get(i).getType(), "Short Answer")) {
                fullScore += 5;
            } else {
                fullScore += 1;
            }
        }

        scores_questionType.put("Multiple Choice",0);
        scores_questionType.put("True or False",0);
        scores_questionType.put("Short Answer",0);

        scores_questionCategory.put("Science",0);
        scores_questionCategory.put("Dinosaurs",0);
        scores_questionCategory.put("Geography",0);
    }

    private void hideMainMenu() {
        mainMenuView.setVisible(false);
    }

    private void hideQuestionView() {
        questionView.setVisible(false);
    }

    private void hideScoreView() {
        scoreView.setVisible(false);
    }

    private void showQuestion(int index) {
        Question question = questions.get(index);
        questionView.setQuestion(question);
        questionView.setQuestionIndex(index);
        questionView.setTotalQuestions(questions.size());
        questionView.addQuestion();
        questionView.setContent(question.getContent());
        questionView.setBackEnabled(index > 0);
        questionView.setNextEnabled(index <= questions.size() - 1);
        questionView.setVisible(true);
    }

    private boolean validateQuestionType() {
        if (!mainMenuView.getCheckBox1().isSelected()
                && !mainMenuView.getCheckBox2().isSelected()
                && !mainMenuView.getCheckBox3().isSelected()
        ) {
            JOptionPane.showMessageDialog(mainMenuView, "Please select at least one option to continue.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean validateName() {
        if(Objects.equals(firstName, "") || Objects.equals(lastName, "") ) {
            JOptionPane.showMessageDialog(mainMenuView,
                    "Please enter your name.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean validateAnswer() {
        if (questions.get(questionIndex).getType().equals("Multiple Choice")) {
            if (!questionView.getCheckBox1().isSelected()
                    && !questionView.getCheckBox2().isSelected()
                    && !questionView.getCheckBox3().isSelected()
                    && !questionView.getCheckBox4().isSelected()
            ) {
                JOptionPane.showMessageDialog(questionView, "Please select an answer.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else if (questions.get(questionIndex).getType().equals("Short Answer")) {
            if (questionView.getAnswerField().getText().equals("")) {
                JOptionPane.showMessageDialog(questionView, "Please enter an answer.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else if (questions.get(questionIndex).getType().equals("True or False")) {
            if (!questionView.getRadioBtn1().isSelected() && !questionView.getRadioBtn2().isSelected()) {
                JOptionPane.showMessageDialog(questionView, "Please select an answer.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    private void saveAnswer() {
        Question question = questions.get(questionIndex);
        if (Objects.equals(question.getType(), "Multiple Choice")) {
            scores_questionType.put("Multiple Choice", scores_questionType.get("Multiple Choice") - answers.get(questionIndex));
            int answer = questionView.calculateMultipleChoiceScore();
            answers.set(questionIndex, answer);
            scores_questionType.put("Multiple Choice", scores_questionType.get("Multiple Choice") + answer);
        } else if (Objects.equals(question.getType(), "True or False")) {
            scores_questionType.put("True or False", scores_questionType.get("True or False") - answers.get(questionIndex));
            int answer = questionView.calculateTrueOrFalseScore();
            answers.set(questionIndex, answer);
            scores_questionType.put("True or False", scores_questionType.get("True or False") + answer);
        } else if (Objects.equals(question.getType(), "Short Answer")) {
            scores_questionType.put("Short Answer", scores_questionType.get("Short Answer") - answers.get(questionIndex));
            int answer = questionView.calculateShortAnswerScore();
            answers.set(questionIndex, answer);
            scores_questionType.put("Short Answer", scores_questionType.get("Short Answer") + answer);
        }
    }

    private void calculateQuestionCategoryScore() {
        for (int i = 0; i < questions.size(); i++) {
            String category = questions.get(i).getCategory();
            if (Objects.equals(category, "Geography")) {
                scores_questionCategory.put("Geography", scores_questionCategory.get("Geography") + answers.get(i));
            } else if (Objects.equals(category, "Dinosaurs")){
                scores_questionCategory.put("Dinosaurs", scores_questionCategory.get("Dinosaurs") + answers.get(i));
            } else if(Objects.equals(category, "Science")) {
                scores_questionCategory.put("Science", scores_questionCategory.get("Science") + answers.get(i));
            }
        }
    }

    private int calculateTotalScore(HashMap<String, Integer> scores) {
        Collection<Integer> values = scores.values();
        int sum = 0;
        for (Integer value : values) {
            sum += value;
        }
        return sum;
    }

    private void showScore() {
        calculateQuestionCategoryScore();
        for (String type : scores_questionCategory.keySet()) {
            dataset_pieChart.setValue(type, scores_questionCategory.get(type));
            System.out.println(scores_questionCategory.get(type));
        }
        for (String type : scores_questionType.keySet()) {
            dataset_barChart.addValue(scores_questionType.get(type), "score", type);
        }
        score = calculateTotalScore(scores_questionType);
        scoreView.setFullScore(fullScore);
        scoreView.setScore(score);
        scoreView.setFirstName(firstName);
        scoreView.setLastName(lastName);
        scoreView.updateName();
        scoreView.setDataset_questionType(dataset_barChart);
        scoreView.setDataset_questionCategory(dataset_pieChart);
        scoreView.showChart1();
        scoreView.showChart2();
        scoreView.setVisible(true);
    }

    private void reset() {
        questionIndex = 0;
        questions = null;
        answers = null;
        fullScore = 0;
        scores_questionType.clear();
        scores_questionCategory.clear();
        dataset_barChart.clear();
        dataset_pieChart.clear();
        mainMenuView.getCardLayout().show(mainMenuView.getCards(), "panel2");
        currPanel = 2;
        mainMenuView.getSwitchButton().setVisible(true);
        hideScoreView();
        hideQuestionView();
    }

    public static void main(String[] args) {
        new TestController();
    }
}