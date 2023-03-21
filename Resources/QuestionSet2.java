package TopQuizSystem.Resources;

import TopQuizSystem.Model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionSet2 {
    public static List<Question> getTestData() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question("True or False", "Alexander Graham Bell invented the telephone.",
                new String[]{"True", "False"}, "True", "Science"));

        questions.add(new Question("Multiple Choice", "What is the smallest unit of matter?",
                new String[]{"Molecule", "Atom", "Electron", "Proton"}, "Atom", "Science"));

        questions.add(new Question("True or False", "The boiling point of water is 100 degrees Celsius.",
                new String[]{"True", "False"}, "True", "Science"));

        questions.add(new Question("Short Answer", "What is the chemical symbol for water?",
                null, "H2O", "Science"));

        questions.add(new Question("Multiple Choice", "Which of the following is NOT a state of matter?",
                new String[]{"Solid", "Liquid", "Gas", "Plasma", "Energy"}, "Energy", "Science"));

        questions.add(new Question("True or False", "In the periodic table, Na is the chemical symbol for Sodium.",
                new String[]{"True", "False"}, "True", "Science"));


        return questions;
    }
}

