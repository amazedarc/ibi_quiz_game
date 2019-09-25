package com.ibi.ibi_game.beans;

import android.app.Activity;

public class DbQuiz extends Activity {

    private int id;
    private String question;
    private String op1;
    private String op2;
    private String op3;
    private String op4;
    private String op5;
    private String op6;
    private String answer;

    public DbQuiz(String q, String o1, String o2, String o3, String o4, String o5, String o6, String ans){

     question = q;
          op1=o1;
          op2=o2;
          op3=o3;
          op4=o4;
          op5=o5;
          op6=o6;
          answer=ans;
    }

    public DbQuiz(){

         id=0;
         question="";
         op1="";
         op2="";
         op3="";
         op4="";
         op5="";
         op6="";
         answer="";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public String getOp4() {
        return op4;
    }

    public void setOp4(String op4) {
        this.op4 = op4;
    }

    public String getOp5() {
        return op5;
    }

    public void setOp5(String op5) {
        this.op5 = op5;
    }

    public String getOp6() {
        return op6;
    }

    public void setOp6(String op6) {
        this.op6 = op6;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
