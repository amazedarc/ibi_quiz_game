package com.ibi.ibi_game.classes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class fonctionSecondaires {

    public fonctionSecondaires()
    {

    }

    //public static  String adresse="http://192.168.137.50/Game/";
    public static  String adresse="http://game.ibi-africa.com/Gamek/";


    public static boolean checkNetworkConnection(Context context){

        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());

    }

    public static String dateSysteme(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd-hh:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
