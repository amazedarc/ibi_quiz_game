package com.ibi.ibi_game.adapters;

import java.io.Serializable;

public class Answers implements Serializable {

    private int answer_id;
    private Questions questions;
    private String answers_name;

    public int getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }

    public String getAnswers_name() {
        return answers_name;
    }

    public void setAnswers_name(String answers_name) {
        this.answers_name = answers_name;
    }
}
