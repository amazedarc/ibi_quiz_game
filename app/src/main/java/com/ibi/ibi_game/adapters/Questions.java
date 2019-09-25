package com.ibi.ibi_game.adapters;

import java.io.Serializable;
import java.util.List;

public class Questions implements Serializable {
    private  int question_id;
    private Categories category;
    private TypesQuestions type;
    private String title;
    private int answer;
    private String explanation;
    private List<Answers> options;

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public TypesQuestions getType() {
        return type;
    }

    public void setType(TypesQuestions type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public List<Answers> getOptions() {
        return options;
    }

    public void setOptions(List<Answers> options) {
        this.options = options;
    }
}
