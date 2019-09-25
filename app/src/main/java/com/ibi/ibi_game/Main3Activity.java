package com.ibi.ibi_game;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.ibi.ibi_game.Adaptateurs.AdaptateurAssersions;
import com.ibi.ibi_game.classes.AlertDialogManager;
import com.ibi.ibi_game.classes.Asserssion1;
import com.ibi.ibi_game.classes.Asserssion2;
import com.ibi.ibi_game.classes.MySingleton;
import com.ibi.ibi_game.classes.PutSession;
import com.ibi.ibi_game.classes.Questionnaire;
import com.ibi.ibi_game.classes.Statistic;
import com.ibi.ibi_game.classes.fonctionSecondaires;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.ConstraintSet.VISIBLE;

public class Main3Activity extends AppCompatActivity {


    private CountDownTimer countDownTimer;
    private int timeValue;
    private static int index = 0, sta, indexFaild = 0, indexSuccess = 0;
    private String stat,fermer;
    private String date1, date2;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    TextView textView, loading, correct, incorrect;
    PutSession putSession;
    AlertDialogManager alert;
    List<Questionnaire> liteQuestionnaire;
    List<Asserssion1> listeAssertion1;
    List<Asserssion2> listeAssertion2;
    List<Statistic> listestatistik1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        date1 = fonctionSecondaires.dateSysteme();
        init();
        recuperationAsserssion1();
        recuperationQuestions();
        traitementDedonnes();
        Intent intent = getIntent();
        stat = intent.getStringExtra("etat");
        fermer=intent.getStringExtra("fermer");
        jeux();

        // index=intent.getExtras().getInt("indexe");

    }

    ////////////////////////////////////////initialisation des variable par leur Id//////////////////////////////////////////////////
    private void init() {

        textView = findViewById(R.id.textQusrtion);
        loading = findViewById(R.id.load_id);
        correct = findViewById(R.id.qcm_correct);
        incorrect = findViewById(R.id.qcm_incorrect);
        recyclerView = findViewById(R.id.recycler_view);
        //layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        GridLayoutManager managerr = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(managerr);
        recyclerView.setHasFixedSize(true);

        liteQuestionnaire = new ArrayList<>();
        listeAssertion1 = new ArrayList<>();
        listeAssertion2 = new ArrayList<>();
        listestatistik1 = new ArrayList<>();
        putSession = new PutSession(this);
    }
/////////////////////////////////////////////////-----------------------------------/////////////////////////////////////////////


////////////////////////////////////////////////recuperation des question dans la BD////////////////////////////////////////////

    public void recuperationQuestions() {

        List<Questionnaire> liste = putSession.getQuestionnaire();
        if (!liteQuestionnaire.isEmpty())
            liteQuestionnaire.clear();
        liteQuestionnaire.addAll(liste);


        if (fonctionSecondaires.checkNetworkConnection(this)) {


            StringRequest stringRequest = new StringRequest(Request.Method.POST, fonctionSecondaires.adresse + "Question.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                Questionnaire questionnaire;


                                Log.d("repp", response);

                                JSONObject jsonObject = new JSONObject(response);


                                JSONArray jsonArray = jsonObject.getJSONArray("response");

                                if (!liteQuestionnaire.isEmpty())
                                    liteQuestionnaire.clear();

                                if (jsonArray != null) {

                                    if (jsonArray.length() > 0) {

                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject js = jsonArray.getJSONObject(i);

                                            questionnaire = new Questionnaire();

                                            questionnaire.setQuestion_id(js.getInt("question_id"));
                                            questionnaire.setAnswer(js.getInt("answer"));
                                            questionnaire.setTitle(js.getString("title"));

                                            liteQuestionnaire.add(questionnaire);

                                        }

                                        Gson gson = new Gson();
                                        String string = gson.toJson(liteQuestionnaire);
                                        putSession.PutInQuestionnaire(string);


                                        System.out.println("sucess");
                                        Log.d("pok", "" + liteQuestionnaire.size());


                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();

                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Log.d("lll","okk");
                    error.printStackTrace();
                }
            });
            MySingleton.getmInstance(this).addToRequestQue(stringRequest);

        }

    }
//////////////////////////////////////////////////////--------------------------//////////////////////////////////////////////////////////////////////////////////


    /////////////////////////////////////////////////////////////////////recuperation des assertion1 dans BD//////////////////////////////////////////////////////////
    public void recuperationAsserssion1() {

        List<Asserssion1> liste = putSession.getAsserssion1();
        if (!listeAssertion1.isEmpty())
            listeAssertion1.clear();
        listeAssertion1.addAll(liste);


        if (fonctionSecondaires.checkNetworkConnection(this)) {


            StringRequest stringRequest = new StringRequest(Request.Method.POST, fonctionSecondaires.adresse + "Assertion.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                Asserssion1 Ass1;


                                Log.d("repp2", response);

                                JSONObject jsonObject = new JSONObject(response);


                                JSONArray jsonArray = jsonObject.getJSONArray("response");

                                if (!listeAssertion1.isEmpty())
                                    listeAssertion1.clear();

                                if (jsonArray != null) {

                                    if (jsonArray.length() > 0) {

                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject js = jsonArray.getJSONObject(i);

                                            Ass1 = new Asserssion1();

                                            Ass1.setAnsw_id(js.getInt("answ_id"));
                                            Ass1.setQuestion_id(js.getInt("question_id"));
                                            Ass1.setAnswers_name(js.getString("answers_name"));

                                            listeAssertion1.add(Ass1);

                                        }

                                        Gson gson = new Gson();
                                        String string = gson.toJson(listeAssertion1);
                                        putSession.PutInAsserssion1(string);


                                        System.out.println("sucessAss");
                                        Log.d("pok", "" + listeAssertion1.size());


                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();

                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Log.d("lll","okk");
                    error.printStackTrace();
                }
            });
            MySingleton.getmInstance(this).addToRequestQue(stringRequest);

        }
    }

    ////////////////////////////////////////////////////////////////////////////--------------------////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////traitement de donnees a afficher///////////////////////////////////////////////////////////////////////
    public void traitementDedonnes() {

        // compteurDemunite();
        Asserssion2 asserssion2;
        textView.setText(liteQuestionnaire.get(index).title + " indexe   " + index + "  id  " + liteQuestionnaire.get(index).question_id + "  " + indexSuccess);
        int num = liteQuestionnaire.get(index).question_id;

        for (int i = 0; i < listeAssertion1.size(); i++) {
            int l = listeAssertion1.get(i).getQuestion_id();
            asserssion2 = new Asserssion2();
            if (liteQuestionnaire.get(index).getQuestion_id() == (listeAssertion1.get(i).getQuestion_id())) {
                asserssion2.setAnsw_id2(listeAssertion1.get(i).getAnsw_id());
                asserssion2.setAnsw_quest(liteQuestionnaire.get(index).getAnswer());
                asserssion2.setAnswers_name2(listeAssertion1.get(i).getAnswers_name());
                listeAssertion2.add(asserssion2);
            }
        }
        AdaptateurAssersions adaptateurAssersions = new AdaptateurAssersions(Main3Activity.this, listeAssertion2);

        recyclerView.setAdapter(adaptateurAssersions);

    }
////////////////////////////////////////////////////////////////////-------------------------/////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////afficher presk vide sur l'activite/////////// ////////////////////////////////////////////////////
    public void breake1() {
        textView.setText("félicitation!!!...preparez vous à la suivante");
        listeAssertion2.clear();
    }

    public void breake2() {
        textView.setText("vous avez echoué la question...preparez vous à la suivante ");
        listeAssertion2.clear();
    }

    ///////////////////////////////////////////////////////////////----------------------------//////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////fonctions secondaires/////////////////////////////////////////////////////////////////////////////////////
    private void timesUp() {
        Intent intent = new Intent(this, Times_up.class);
        startActivity(intent);
        finish();

    }

    private void correctAnswer() {

        correct.setVisibility(VISIBLE);
        correct.setText("correct");

    }

    private void wrongAnswer() {

        incorrect.setVisibility(VISIBLE);
        incorrect.setText("Incorrect");

    }

    private void gameWon() {

        Intent intent = new Intent(this, GameWon.class);
        startActivity(intent);
        finish();
    }

    private void gameLost() {
        Intent intent = new Intent(this, PlayAgain.class);
        startActivity(intent);
        finish();
    }

    private void setInvisible() {
        //incorrect.setVisibility(INVISIBLE);
        correct.setText("");
        correct.setBackground(getResources().getDrawable(R.color.contener));

        incorrect.setText("");
        incorrect.setBackground(getResources().getDrawable(R.color.contener));
    }

    private void updateQAndO() {

        timeValue = 15;
        countDownTimer.cancel();
        countDownTimer.start();

    }
    ///////////////////////////////////////////////////////////////////////////------------------------////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////fonction pour compte le temps de jeux/////////////////////////////////////////////////////
    public void compteurDemunite() {

        countDownTimer = new CountDownTimer(17000, 1000) {
            public void onTick(long millisUntilFinished) {
                loading.setText(String.valueOf(timeValue));
                timeValue -= 1;
                if (timeValue == 0) {
                    textView.setEnabled(false);
                    timesUp();
                }

            }


            public void onFinish() {
                timesUp();
            }
        }.start();
        updateQAndO();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        countDownTimer.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        index=0;
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }
    /////////////////////////////////////////////////////////////////////////////--------------------------///////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////fonction qui gere le processus du jeux///////////////////////////////////////////////////////////////////
    public void jeux() {

        if (stat.equals("null")) {
            compteurDemunite();

        }
        else if(fermer.equals("ok"))
        {
            index=0;
            Intent intent=new Intent(this, Home.class);
            startActivity(intent);
            finish();
        }
        else if (stat.equals("false")) {

           // if (index != 9) {
                //index++;
                //indexFaild++;

                sta = 0;
                statisticc();

                index=0;
                indexSuccess=0;
                listeAssertion2.clear();
                gameLost();

            /*breake2();
            wrongAnswer();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    sta = 0;
                    setInvisible();
                    statisticc();
                    listeAssertion2.clear();
                    traitementDedonnes();
                    compteurDemunite();
                    gameLost();

                }
            }, 2000);

        }
        else if(indexFaild==3)
        {
            indexFaild=0;
            index=0;
            gameLost();
        }
        else if(index==9)
        {
            index=0;
            gameLost();
        }*/
           // }

            } else if (stat.equals("true")) {

                indexSuccess++;


                sta = 1;

                if (indexSuccess == 3 && index != 8) {

                    statisticc();
                    index = index + 1;
                    listeAssertion2.clear();
                    indexSuccess = 0;
                    gameWon();
                } else if (indexSuccess == 3 && index == 8){

                    statisticc();
                    listeAssertion2.clear();
                    indexSuccess = 0;
                    index = 0;
                    gameWon();
                } else if (index != 8) {
                    statisticc();
                    index++;
                    breake1();
                    correctAnswer();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listeAssertion2.clear();
                            setInvisible();
                            traitementDedonnes();
                            compteurDemunite();

                        }
                    }, 2000);

                }

            }
        }
///////////////////////////////////////////////////////////////////////////////////---------------------///////////////////////////////////////////////////////////////////


        ///////////////////////////////////////////////////////////////fonction pour recuperer les statistiqies///////////////////////////////////////////////////////////////////
        public void statisticc ()
        {
            date2 = fonctionSecondaires.dateSysteme();
            Statistic statistic = new Statistic();

            statistic.setQuestion_id(liteQuestionnaire.get(index).getQuestion_id());
            statistic.setStatut(sta);
            statistic.setDatee1(date1);
            statistic.setDatee2(date2);


            List<Statistic> liste = putSession.getStatistics();
            if (!listestatistik1.isEmpty()) {
                listestatistik1.clear();
                listestatistik1.addAll(liste);
                listestatistik1.add(statistic);

                Gson gson = new Gson();
                String string = gson.toJson(listestatistik1);
                putSession.PutInStatistic(string);
                System.out.println("sstaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + putSession.getStatistics().size());
            } else {
                listestatistik1.addAll(liste);
                listestatistik1.add(statistic);

                Gson gson = new Gson();
                String string = gson.toJson(listestatistik1);
                putSession.PutInStatistic(string);
                System.out.println("sstaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + putSession.getStatistics().size());
            }


        }



        /////////////////////////////////////////////////////--------------------------------////////////////////////////////////////////////////////////////////////////////
    }

