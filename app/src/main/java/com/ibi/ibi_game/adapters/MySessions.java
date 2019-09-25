package com.ibi.ibi_game.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MySessions  {

    private SharedPreferences preferences;

    public MySessions(Context context) {
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setQuestions(String string){
        this.preferences.edit().putString("questions",string).commit();
    }


    public List<Questions> getQuestions(){

        List<Questions> list_questions = new ArrayList<>();
        List<Answers> list_answers = new ArrayList<>();
        String string=this.preferences.getString("questions","");

        Log.d("test",string);

        try {
              Questions questions;
              Answers answers;
              Categories categories;
              TypesQuestions typesQuestions;
              JSONArray jsonArray = new JSONArray(string);

              if(!list_questions.isEmpty()){
                      list_questions.clear();
              }

              if(jsonArray != null){

                  if(jsonArray.length() >0){

                      for(int i=0;i<jsonArray.length();i++){
                          questions = new Questions();
                          categories = new Categories();
                          typesQuestions = new TypesQuestions();

                          JSONObject js = jsonArray.getJSONObject(i);
                          JSONArray jo = js.getJSONArray("options");
                          JSONObject jc = js.getJSONObject("category");
                          JSONObject jt = js.getJSONObject("type");

                          if(jo!=null){
                              for(int j = 0; j<jo.length();j++)
                              {
                                  answers=new Answers();
                                  JSONObject ja = jo.getJSONObject(j);
                                  answers.setAnswer_id(ja.getInt("answer_id"));
                                  answers.setAnswers_name(ja.getString("answers_name"));
                                  // add list of answers
                                  list_answers.add(answers);

                                  Log.d("bjrrrrr",String.valueOf(answers.getAnswer_id()));
                                  Log.d("bjrrrr",answers.getAnswers_name());


                              }
                          }

                          questions.setQuestion_id(js.getInt("question_id"));
                          questions.setTitle(js.getString("title"));
                          questions.setExplanation(js.getString("explanation"));
                          questions.setAnswer(js.getInt("answer"));
                          categories.setCategory_id(jc.getInt("category_id"));
                          categories.setCategory_name(jc.getString("category_name"));
                          categories.setDescription(jc.getString("description"));
                          typesQuestions.setType_id(jt.getInt("type_id"));
                          typesQuestions.setType_name(jt.getString("type_name"));
                          questions.setOptions(list_answers);


                          questions.setCategory(categories);
                          questions.setType(typesQuestions);

                          list_questions.add(questions);
                      }
                      Log.d("ansssss", String.valueOf(list_questions));
                  }
              }
        }catch (JSONException e){
            e.printStackTrace();
        }

        return list_questions;
    }
}
