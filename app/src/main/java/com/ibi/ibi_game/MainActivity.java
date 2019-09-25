package com.ibi.ibi_game;


import android.content.Intent;
import android.os.Bundle;

import com.ibi.ibi_game.beans.DbQuiz;
import com.ibi.ibi_game.beans.Quiz;
import com.ibi.ibi_game.beans.QuizHelper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private QuizHelper quizHelper;
    private  CountDownTimer countDownTimer;
    private int qid = 0;
    private int currentQuiz=1;
    private DbQuiz currentQuestion;
    private int timeValue;
    private List<DbQuiz> list;
    private TextView qOptionA,qOptionB,qOptionC,qOptionD,qOptionE,qOptionF,loading,question,correct,incorrect,qNumber;


    private  void init(){

        qOptionA =findViewById(R.id.sub_quiz1);
        qOptionB =findViewById(R.id.sub_quiz2);
        qOptionC =findViewById(R.id.sub_quiz3);
        qOptionD =findViewById(R.id.sub_quiz4);
        qOptionE =findViewById(R.id.sub_quiz5);
        qOptionF =findViewById(R.id.sub_quiz6);
        loading =findViewById(R.id.load_id);
        question = findViewById(R.id.text_quiz);
        correct = findViewById(R.id.q_correct);
        incorrect= findViewById(R.id.q_incorrect);
//        buttonNext = findViewById(R.id.next);
        qNumber= findViewById(R.id.qnumber);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.init();
        this.gameLogic();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


            private void gameLogic(){
               qNumber.setText(currentQuiz+"/5");
               quizHelper = new QuizHelper(this);
               quizHelper.getWritableDatabase();

               if(quizHelper.getAllQuestions().size()==0){

                   quizHelper.allQuestion();
               }

               list = quizHelper.getAllQuestions();
                Collections.shuffle(list);
                currentQuestion = list.get(qid);
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
        qOptionA.setText(currentQuestion.getOp1());
        qOptionB.setText(currentQuestion.getOp2());
        qOptionC.setText(currentQuestion.getOp3());
        qOptionD.setText(currentQuestion.getOp4());
        qOptionE.setText(currentQuestion.getOp5());
        qOptionF.setText(currentQuestion.getOp6());
        question.setText(currentQuestion.getQuestion());

        timeValue = 15;

        countDownTimer.cancel();
        countDownTimer.start();

    }

     private void timesUp(){
         Intent intent = new Intent(this, Times_up.class);
         startActivity(intent);
         finish();
     }


    private void disableButton() {
        qOptionA.setEnabled(false);
        qOptionB.setEnabled(false);
        qOptionC.setEnabled(false);
        qOptionE.setEnabled(false);
        qOptionD.setEnabled(false);
        qOptionF.setEnabled(false);
    }

     private void gameWon(){

        Intent intent = new Intent(this,GameWon.class);
        startActivity(intent);
    }

    private void gameLost(){
        Intent intent = new Intent(this,PlayAgain.class);
        startActivity(intent);
        finish();
    }

     public void optionA(View view) {

         if (currentQuestion.getOp1().equals(currentQuestion.getAnswer())) {
             qOptionA.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
             qOptionA.setBackground(getResources().getDrawable(R.drawable.correct_selection));
             if (qid < list.size() - 1) {
                 disableButton();
                 correctAnswer();

                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         next();
                     }
                 },2000);

             }
             else {
                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         gameWon();
                     }
                 },1500);
             }

             }else {

                 qOptionA.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                 qOptionA.setBackground(getResources().getDrawable(R.drawable.incorrect_selection));
                 disableButton();
                 wrongAnswer();
                 gameLost();
             }
         }


    public void optionB(View view) {

        if (currentQuestion.getOp2().equals(currentQuestion.getAnswer())) {
//
            qOptionB.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            qOptionB.setBackground(getResources().getDrawable(R.drawable.correct_selection));
            if (qid < list.size() - 1) {
                disableButton();
                correctAnswer();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        next();
                    }
                },2000);

            } else {
                gameWon();
            }
        } else {


            qOptionB.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
            qOptionB.setBackground(getResources().getDrawable(R.drawable.incorrect_selection));
            disableButton();
            wrongAnswer();

            gameLost();
        }
    }

    public void optionC(View view) {

        if (currentQuestion.getOp3().equals(currentQuestion.getAnswer())) {
            qOptionC.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            qOptionC.setBackground(getResources().getDrawable(R.drawable.correct_selection));
            if (qid <= list.size() -1) {
                disableButton();
                correctAnswer();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        next();
                    }
                },2000);

            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gameWon();
                    }
                },1500);

            }
        } else {

            qOptionC.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
            qOptionC.setBackground(getResources().getDrawable(R.drawable.incorrect_selection));
            disableButton();
            wrongAnswer();

            gameLost();
        }
    }

    public void optionD(View view) {

        if (currentQuestion.getOp4().equals(currentQuestion.getAnswer())) {
            qOptionD.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            qOptionD.setBackground(getResources().getDrawable(R.drawable.correct_selection));
            if (qid < list.size() - 1) {
                disableButton();
                correctAnswer();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        next();
                    }
                },2000);

            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gameWon();
                    }
                },1500);
            }
        } else {

            qOptionD.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
            qOptionD.setBackground(getResources().getDrawable(R.drawable.incorrect_selection));
            disableButton();
            wrongAnswer();
            gameLost();
        }
    }

    public void optionE(View view) {

        if (currentQuestion.getOp5().equals(currentQuestion.getAnswer())) {
            qOptionE.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            qOptionE.setBackground(getResources().getDrawable(R.drawable.correct_selection));
            if (qid < list.size() - 1) {
                disableButton();
                correctAnswer();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        next();
                    }
                },2000);

            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gameWon();
                    }
                },1500);
            }
        } else {

            qOptionE.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
            qOptionE.setBackground(getResources().getDrawable(R.drawable.incorrect_selection));
            disableButton();
            wrongAnswer();

            gameLost();
        }
    }

    public void optionF(View view) {

        if (currentQuestion.getOp6().equals(currentQuestion.getAnswer())) {
            qOptionF.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            qOptionF.setBackground(getResources().getDrawable(R.drawable.correct_selection));
            if (qid < list.size() - 1) {
                disableButton();
                correctAnswer();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        next();
                    }
                },2000);

            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gameWon();
                    }
                },1500);
            }
        } else {
            qOptionF.setBackground(getResources().getDrawable(R.drawable.incorrect_selection));
            disableButton();
            wrongAnswer();

            gameLost();
        }
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
                 currentQuiz++;
                 currentQuestion = list.get(qid);
                 updateQAndO();
                 resetColor();
                 resetBackground();
                 setInvisible();
                 enableButton();

    }

    private void resetColor() {
        qOptionA.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
        qOptionB.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
        qOptionC.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
        qOptionD.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
        qOptionE.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
        qOptionF.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
    }

    private void resetBackground(){
        qOptionA.setBackground(getResources().getDrawable(R.drawable.quiz_container));
        qOptionB.setBackground(getResources().getDrawable(R.drawable.quiz_container));
        qOptionC.setBackground(getResources().getDrawable(R.drawable.quiz_container));
        qOptionD.setBackground(getResources().getDrawable(R.drawable.quiz_container));
        qOptionF.setBackground(getResources().getDrawable(R.drawable.quiz_container));
        qOptionF.setBackground(getResources().getDrawable(R.drawable.quiz_container));
    }

    private void enableButton() {
        qOptionA.setEnabled(true);
        qOptionB.setEnabled(true);
        qOptionC.setEnabled(true);
        qOptionD.setEnabled(true);
        qOptionE.setEnabled(true);
        qOptionF.setEnabled(true);
    }

    private void setInvisible(){
        correct.setVisibility(View.INVISIBLE);
        incorrect.setVisibility(View.INVISIBLE);
    }
}


