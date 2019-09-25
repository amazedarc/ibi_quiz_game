package com.ibi.ibi_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndOfLevelOne extends AppCompatActivity {

    private TextView yes, no;

    private void init(){
        yes= findViewById(R.id.btn_yes);
        no = findViewById(R.id.btn_no);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_level_one);
        this.init();
        this.routing();
    }

    private void routing(){
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndOfLevelOne.this,LevelTwo.class);
                startActivity(intent);
                finish();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndOfLevelOne.this,Mainpage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
