package TopQuizSystem.Resources;

import TopQuizSystem.Model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionSet1 {
    public static List<Question> getTestData() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question("Multiple Choice", "Which of these dinosaurs was a herbivore, meaning it ate only plants?",
                new String[]{"Tyrannosaurus Rex", "Velociraptor", "Triceratops", "Spinosaurus"}, "Triceratops", "Dinosaurs"));

        questions.add(new Question("Multiple Choice", "Which dinosaur is known for its long neck?",
                new String[]{"Stegosaurus", "Triceratops", "Brachiosaurus", "Tyrannosaurus Rex"}, "Brachiosaurus", "Dinosaurs"));

        questions.add(new Question("True or False", "The Velociraptor was the size of a large, modern-day bird.",
                new String[]{"True", "False"}, "True", "Dinosaurs"));

        questions.add(new Question("Short Answer", "What type of dinosaur had a series of bony plates along its back?",
                null, "Stegosaurus", "Dinosaurs"));

        questions.add(new Question("Multiple Choice", "Which dinosaur is known for its three-horned face?",
                new String[]{"Triceratops", "Stegosaurus", "Ankylosaurus", "Diplodocus"}, "Triceratops", "Dinosaurs"));

        questions.add(new Question("True or False", "The Pterodactyl was a dinosaur.",
                new String[]{"True", "False"}, "False", "Dinosaurs"));



        return questions;
    }
}

