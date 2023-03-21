package TopQuizSystem.Resources;

import TopQuizSystem.Model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionSet3 {
    public static List<Question> getTestData() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question("Multiple Choice", "What is the capital city of France?",
                new String[]{"Berlin", "Paris", "London", "Rome"}, "Paris", "Geography"));

        questions.add(new Question("Short Answer", "What is the tallest mountain in the world?",
                null, "Mount Everest", "Geography"));

        questions.add(new Question("Short Answer", "Which is the largest ocean on earth?",
                null, "Pacific", "Geography"));

        questions.add(new Question("Multiple Choice", "Which continent is the largest?",
                new String[]{"Africa", "Asia", "North America", "Europe"}, "Asia", "Geography"));

        questions.add(new Question("True or False", "Australia is both a continent and a country.",
                new String[]{"True", "False"}, "True", "Geography"));

        questions.add(new Question("Short Answer", "What is the longest river in the world?",
                null, "Nile", "Geography"));

        questions.add(new Question("Multiple Choice", "Which of the following countries is NOT a part of Scandinavia?",
                new String[]{"Sweden", "Norway", "Denmark", "Finland"}, "Finland", "Geography"));

        questions.add(new Question("True or False", "The equator is an imaginary line that divides the Earth into Northern and Southern Hemispheres.",
                new String[]{"True", "False"}, "True", "Geography"));



        return questions;
    }
}

