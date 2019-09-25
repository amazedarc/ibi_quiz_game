package com.ibi.ibi_game.adapters;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {

    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context context;

    private MySingleton(Context ctx){
        context=ctx;
        requestQueue=getRequestQueue();

    }

    public static synchronized MySingleton  getmInstance(Context context) {

        if (mInstance==null){

            mInstance=new MySingleton(context);
        }
        return mInstance;
    }

    public<T> void addToRequestQue(Request<T> request){

        getRequestQueue().add(request);
    }

    public static void setmInstance(MySingleton mInstance) {
        MySingleton.mInstance = mInstance;
    }

    public RequestQueue getRequestQueue() {

        if(requestQueue==null)
            requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        return requestQueue;
    }

    public void setRequestQueue(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        MySingleton.context = context;
    }
}