package entity;

public class Answer {
    private int id;
    private String content;
    private entity.Question question;
    private boolean isCorrect;

    public Answer() {
    }

    public Answer(int id, String content, entity.Question question, boolean isCorrect) {
        this.id = id;
        this.content = content;
        this.question = question;
        this.isCorrect = isCorrect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public entity.Question getQuestion() {
        return question;
    }

    public void setQuestion(entity.Question question) {
        this.question = question;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}

