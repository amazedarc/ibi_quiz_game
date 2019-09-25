package com.ibi.ibi_game.database;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Database {

    public static final String SERVER_URL="http://game.ibi-africa.com/ibiafric_game/index.php";
//    public static final String SERVER_URL="http://192.168.43.77/ibiafric_game/index.php";
//    public static final String SERVER_PICTURE="http://10.0.2.2/ibiafric_game/files/";
    public static boolean checkNetworkConnection(Context context){

        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());


    }
}

