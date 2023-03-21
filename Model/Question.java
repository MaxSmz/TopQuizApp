package TopQuizSystem.Model;

public class Question {
    private String type;
    private String content;
    private String[] options;
    private String answer;
    private String category;

    public Question(String type, String content, String[] options, String answer,String category) {
        this.type = type;
        this.content = content;
        this.options = options;
        this.answer = answer;
        this.category = category;
    }
    public String getCategory(){
        return category;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public String[] getOptions() {
        return options;
    }

    public String getAnswer() {
        return answer;
    }
}