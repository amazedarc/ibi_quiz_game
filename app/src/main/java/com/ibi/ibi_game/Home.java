package com.ibi.ibi_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Home extends AppCompatActivity {

    private Button home_btn;


    private void init(){

        home_btn = findViewById(R.id.btn_play);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.init();
        this.startPlay();

    }

    private  void startPlay(){
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,StartVideos.class);
        startActivity(intent);
finish();
    }


}


