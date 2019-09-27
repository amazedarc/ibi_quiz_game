package com.ibi.ibi_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class GameWon extends AppCompatActivity {
    private com.airbnb.lottie.LottieAnimationView play,gift;
    private ImageView radom_img;

    private void init(){
        play = findViewById(R.id.play_again);
        radom_img = findViewById(R.id.img_random);
        gift = findViewById(R.id.gift);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_won);

        this.init();
        this.restartGame();
        this.radomImage();

    }

    private void restartGame(){

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameWon.this,EndOfLevelOne.class);
                startActivity(intent);
            }
        });
        gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gift.setVisibility(View.INVISIBLE);
                radom_img.setVisibility(View.VISIBLE);
            }
        });
    }

    private void radomImage(){



        int resId[]={R.drawable.image_0,R.drawable.image_1,R.drawable.image_3};
        Random rand = new Random();
        int index = rand.nextInt((resId.length- 1) - 0 + 1) + 0;
        radom_img.setImageResource(resId[index]);

    }
}
