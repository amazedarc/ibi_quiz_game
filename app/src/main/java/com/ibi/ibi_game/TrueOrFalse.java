package com.ibi.ibi_game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ibi.ibi_game.adapters.MySessions;
import com.ibi.ibi_game.adapters.Questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrueOrFalse extends AppCompatActivity {
    private TextView quiz, true_ans, false_ans, type, loading, correct, incorrect;
    private int true_answer, false_answer;
    private Questions questions;
    private CountDownTimer countDownTimer;
    private MySessions mySessions;
    private List<Questions> list;
    private List<Questions> list1;
    private List<Questions> list2;
    private List<Questions> list3;
    private List <Questions> main_list;
    private int timeValue;
    private int qid = 0;
    private int aid = 0;
    private int index = 0;
    private int correct_answer = 0;
    private int wrong_answer = 0;

    private void init() {
        quiz = findViewById(R.id.tf_quiz);
        true_ans = findViewById(R.id.t_quiz1);
        false_ans = findViewById(R.id.f_quiz2);
        type = findViewById(R.id.text_type);
        loading = findViewById(R.id.load_id1);
        correct = findViewById(R.id.q_correct1);
        incorrect = findViewById(R.id.q_incorrect1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_or_false);

        this.init();
        this.displayQuestion();
    }

    private void displayQuestion() {

        mySessions = new MySessions(this);
        mySessions.getQuestions();
        list = mySessions.getQuestions();
        list1 = list.subList(0,3);
        list2 = list.subList(3,6);
        Log.d("list1", String.valueOf(list1));
        Log.d("list2", String.valueOf(list));
        Log.d("list_main", String.valueOf(list3));
        questions = list1.get(qid);
        countDownTimer = new CountDownTimer(17000, 1000) {
            public void onTick(long millisUntilFinished) {
                loading.setText(String.valueOf(timeValue));
                timeValue -= 1;
                if (timeValue == 0) {
                    disableButton();
                    timesUp();
                }
            }

            public void onFinish() {
                timesUp();
            }
        }.start();
        updateQAndO();
    }

    private void updateQAndO() {

        quiz.setText(questions.getTitle());
        type.setText(questions.getType().getType_name());
        true_ans.setText(questions.getOptions().get(index).getAnswers_name());
        index++;
        false_ans.setText(questions.getOptions().get(index).getAnswers_name());
        true_answer = questions.getOptions().get(aid).getAnswer_id();
        aid++;
        false_answer = questions.getOptions().get(aid).getAnswer_id();
        timeValue = 15;
        countDownTimer.cancel();
        countDownTimer.start();

    }

    public void answerA(View view) {
        if (true_answer == questions.getAnswer()) {
            true_ans.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            true_ans.setBackground(getResources().getDrawable(R.drawable.correct_selection));
            if (qid < list1.size() - 1) {
                disableButton();
                correctAnswer();
                correct_answer++;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        next();
                    }
                },2000);
            } else  if(correct_answer==3){
                gameWon();
            }else{
                gameLost();
            }
        } else {
            true_ans.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            true_ans.setBackground(getResources().getDrawable(R.drawable.incorrect_selection));
            disableButton();
            wrongAnswer();
            Log.d("taaa",String.valueOf(wrong_answer));
            gameLost();
        }


    }

    public void answerB(View view){
        if(false_answer == questions.getAnswer()){
            false_ans.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            false_ans.setBackground(getResources().getDrawable(R.drawable.correct_selection));
            correct_answer++;
            if (qid < list1.size() - 1) {
                disableButton();
                correctAnswer();
                Log.d("correct",String.valueOf(correct_answer));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        next();
                    }
                },2000);
            } else {
                if(correct_answer==3){
                    gameWon();
                }else{
                    gameLost();
                }

            }
        } else {
            false_ans.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
            false_ans.setBackground(getResources().getDrawable(R.drawable.incorrect_selection));
            disableButton();
            wrongAnswer();
            Log.d("taaxxxaa",String.valueOf(wrong_answer));
            gameLost();

        }
    }

    private void timesUp(){
        Intent intent = new Intent(this, Times_up.class);
        startActivity(intent);
        finish();
    }

    private void gameWon(){

        Intent intent = new Intent(this,GameWon.class);
        startActivity(intent);
        finish();
    }

    private void gameLost(){
        Intent intent = new Intent(this,PlayAgain.class);
        startActivity(intent);
        finish();
    }
    private void correctAnswer(){

        correct.setVisibility(View.VISIBLE);
        correct.setText("correct");

    }

    private void wrongAnswer(){
        incorrect.setVisibility(View.VISIBLE);
        incorrect.setText("Incorrect");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        countDownTimer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }
    private void next(){

        qid++;
        aid++;
        index--;
        questions = list1.get(qid);
        updateQAndO();
        resetColor();
        resetBackground();
        setInvisible();
        enableButton();

    }

    private void resetColor() {
        true_ans.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
        false_ans.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));

    }

    private void resetBackground(){
        true_ans.setBackground(getResources().getDrawable(R.drawable.quiz_container));
        false_ans.setBackground(getResources().getDrawable(R.drawable.quiz_container));

    }

    private void enableButton() {
        true_ans.setEnabled(true);
        false_ans.setEnabled(true);

    }

    private  void disableButton(){
        true_ans.setEnabled(false);
        false_ans.setEnabled(false);
    }

    private void setInvisible(){
        correct.setVisibility(View.INVISIBLE);
        incorrect.setVisibility(View.INVISIBLE);
    }

}
