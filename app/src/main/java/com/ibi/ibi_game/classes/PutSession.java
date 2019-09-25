package com.ibi.ibi_game.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PutSession {

    private SharedPreferences preferences;

    public PutSession(Context context) {
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }
    //////////////////////////////mettre la liste des quetionnaires dans la session////////////////////////////////////////////
    public void PutInQuestionnaire(String string)
    {
        this.preferences.edit().putString("Questionnaire",string).commit();
    }


    /////////////////////////////mettre la liste de statistic dans la session/////////////////////////////////////////////////
    public void PutInStatistic(String string)
    {
        this.preferences.edit().putString("Statistic",string).commit();
    }

    /////////////////////////////mettre la liste du type choi multiple dans la session/////////////////////////////////////////////////
    public void PutInAsserssion1(String string)
    {
        this.preferences.edit().putString("Asserssion1",string).commit();
    }

    ////////////////////////////recuperation des statistic dans la session///////////////////////////////////////////////////
    public List<Statistic> getStatistics()
    {
        List<Statistic>liste_Statistic=new ArrayList<>();
        String string=this.preferences.getString("Statistic","");

        try {

            Statistic statistic;

            JSONArray jsonArray=new JSONArray(string);

            if (!liste_Statistic.isEmpty())
                liste_Statistic.clear();

            if (jsonArray != null) {

                if (jsonArray.length() > 0) {

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject js = jsonArray.getJSONObject(i);

                        statistic=new Statistic();

                        statistic.setQuestion_id(js.getInt("question_id"));
                        statistic.setStatut(js.getInt("statut"));
                        statistic.setDatee1(js.getString("datee1"));
                        statistic.setDatee2(js.getString("datee2"));


                        liste_Statistic.add(statistic);


                    }

                }
            }


        } catch (JSONException e) {
            e.printStackTrace();

        }

        return liste_Statistic;
    }


    /////////////////////////////recuperation des questionnaire dans la session//////////////////////////////////////////////
    public List<Questionnaire> getQuestionnaire(){

        List<Questionnaire>liste_Questionnaire=new ArrayList<>();
        String string=this.preferences.getString("Questionnaire","");

        try {

            Questionnaire questionnaire;

            JSONArray jsonArray=new JSONArray(string);

            if (!liste_Questionnaire.isEmpty())
                liste_Questionnaire.clear();

            if (jsonArray != null) {

                if (jsonArray.length() > 0) {

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject js = jsonArray.getJSONObject(i);

                        questionnaire=new Questionnaire();

                        questionnaire.setQuestion_id(js.getInt("question_id"));
                        questionnaire.setAnswer(js.getInt("answer"));
                        questionnaire.setTitle(js.getString("title"));

                      liste_Questionnaire.add(questionnaire);


                    }

                }
            }


        } catch (JSONException e) {
            e.printStackTrace();

        }

        return liste_Questionnaire;
    }

    /////////////////////////////recuperation des asserssion1 dans la session//////////////////////////////////////////////
    public List<Asserssion1> getAsserssion1(){

        List<Asserssion1>liste_Asserssion1=new ArrayList<>();
        String string=this.preferences.getString("Asserssion1","");

        try {

            Asserssion1 Ass1;

            JSONArray jsonArray=new JSONArray(string);

            if (!liste_Asserssion1.isEmpty())
                liste_Asserssion1.clear();

            if (jsonArray != null) {

                if (jsonArray.length() > 0) {

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject js = jsonArray.getJSONObject(i);

                        Ass1=new Asserssion1();

                        Ass1.setAnsw_id(js.getInt("answ_id"));
                        Ass1.setQuestion_id(js.getInt("question_id"));
                        Ass1.setAnswers_name(js.getString("answers_name"));

                        liste_Asserssion1.add(Ass1);


                    }

                }
            }


        } catch (JSONException e) {
            e.printStackTrace();

        }

        return liste_Asserssion1;
    }
}
