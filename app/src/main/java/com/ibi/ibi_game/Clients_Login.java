package com.ibi.ibi_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Clients_Login extends AppCompatActivity {

    private Spinner spinner;
    private EditText password;
    private Button enter;
    private String url_clients = "http://game.ibi-africa.com/ibiafric_game/clients.php";

    private void init(){
        spinner = findViewById(R.id.spinner_list);
        password = findViewById(R.id.password);
        enter = findViewById(R.id.login);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients__login);
        this.init();
    }

    private void clientView(){

    }
}
