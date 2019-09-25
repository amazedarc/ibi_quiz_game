package com.ibi.ibi_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.ibi.ibi_game.adapters.Answers;
import com.ibi.ibi_game.adapters.Categories;
import com.ibi.ibi_game.adapters.MySessions;
import com.ibi.ibi_game.adapters.MySingleton;
import com.ibi.ibi_game.adapters.Questions;
import com.ibi.ibi_game.adapters.TypesQuestions;
import com.ibi.ibi_game.database.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mainpage extends AppCompatActivity {

    private ImageView qcmImageView, tImageView;
    private MySessions mySessions;
    private List<Questions> list_questions;
    private List<Answers> list_answers;
    private int index=0;


    private void init() {
        mySessions= new MySessions(this);
        list_questions  = new ArrayList<>();
        list_answers = new ArrayList<>();
        qcmImageView = findViewById(R.id.img_1);
        tImageView = findViewById(R.id.img_2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        this.init();
        this.gettingAnswer();
        this.routing();

    }

    private void routing() {
        qcmImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainpage.this, Main3Activity.class);
                startActivity(intent);
            }
        });

        tImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainpage.this, TrueOrFalse.class);
                startActivity(intent);
            }
        });

    }

    private void gettingAnswer(){

        Log.d("xxxxxx", "salut");

        List<Questions> list = mySessions.getQuestions();
        if(!list_questions.isEmpty())
            list_questions.clear();
        list_questions.addAll(list);

        Log.d("test1",String.valueOf(list_questions.size()));
        if(Database.checkNetworkConnection(this)){

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Database.SERVER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d("retour",response);

                            try {
                                Answers answers;
                                Questions questions;
                                Categories categories;
                                TypesQuestions typesQuestions;
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("questions");

                                if(!list_questions.isEmpty())
                                    list_questions.clear();
                                if(jsonArray != null){
                                    if(jsonArray.length()>0){
                                        for(int i = 0; i<jsonArray.length();i++){

                                            questions = new Questions();

                                            categories = new Categories();
                                            typesQuestions = new TypesQuestions();

                                            JSONObject js = jsonArray.getJSONObject(i);
                                            JSONArray jo = js.getJSONArray("answers");
                                            JSONObject jc = js.getJSONObject("category");
                                            JSONObject jt = js.getJSONObject("type");

                                            if(jo!=null){
                                                for(int j = 0; j<jo.length();j++)
                                                {
                                                    answers = new Answers();
                                                    JSONObject ja = jo.getJSONObject(j);
                                                    answers.setAnswer_id(ja.getInt("answer_id"));
                                                    answers.setAnswers_name(ja.getString("answers_name"));
                                                    list_answers.add(answers);

                                                    Log.d("ans",String.valueOf(answers));
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
                                        Gson gson= new Gson();
                                        String string = gson.toJson(list_questions);
                                        mySessions.setQuestions(string);
                                        Log.d("test2",string);
                                    }
                                }
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("eeeeeeeeee","Error response");                      }
            }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("task", "getQuestions");
                    return params;
                }
            };
            MySingleton.getmInstance(this).addToRequestQue(stringRequest);
        }
    }


}