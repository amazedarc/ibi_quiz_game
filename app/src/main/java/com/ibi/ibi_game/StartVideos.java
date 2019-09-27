package com.ibi.ibi_game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;


public class StartVideos extends AppCompatActivity {

    private VideoView videoView;
    private  static  int STORAGE_PERMISSION_CODE = 99;
    private ImageView game;
    private  void init(){

        videoView = findViewById(R.id.video_view);
        game = findViewById(R.id.btn_play1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_videos);
        this.init();
        this.storagePermission();
        this.configureVideoView();
    }

    private void storagePermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            return;
        }
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == STORAGE_PERMISSION_CODE){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                Toast.makeText(this,"Permission granted",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void configureVideoView() {

        videoView = new VideoView(this);
        setContentView(videoView);
        String url = "http://game.ibi-africa.com/uploads/videos/20190927093343-2019-09-27videos093339.mp4";
        videoView.setVideoURI(Uri.parse(url));
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        videoView.setLayoutParams(new FrameLayout.LayoutParams(metrics.widthPixels,metrics.heightPixels));
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            public void onCompletion(MediaPlayer mp) {
                jump();
            }});
        videoView.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gamePlay();
            }
        },2000);


        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(StartVideos.this,Mainpage.class);
                startActivity(intent);
                return false;
            }
        });
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartVideos.this,Mainpage.class);
                startActivity(intent);
            }
        });
    }

    private void jump()
    {
        if(isFinishing())
            return;
        startActivity(new Intent(this, Mainpage.class));
        finish();
    }
      private void gamePlay(){

        game.setVisibility(View.VISIBLE);
      }

}
