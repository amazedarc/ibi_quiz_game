package com.ibi.ibi_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PlayAgain extends AppCompatActivity {

    private com.airbnb.lottie.LottieAnimationView replay;

    private void init(){

        replay = findViewById(R.id.play_again1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_again);
        this.init();
        this.playAgain();
    }

    private void  playAgain(){
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PlayAgain.this,Mainpage.class);
                startActivity(intent);
            }
        });
    }
}
