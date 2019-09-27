package com.ibi.ibi_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Times_up extends AppCompatActivity {

    private com.airbnb.lottie.LottieAnimationView replay;

     private void init(){
         replay= findViewById(R.id.replay);
     }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times_up);
        this.init();
        this.restartGame();
    }

    private void restartGame(){

        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Times_up.this,Mainpage.class);
                startActivity(intent);
finish();
            }
        });
    }
}
