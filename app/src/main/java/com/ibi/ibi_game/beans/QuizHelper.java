package com.ibi.ibi_game.beans;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;


public class QuizHelper extends SQLiteOpenHelper {

    private  static  final String DATABASE_NAME = "TQuiz.db";
    public Context context;
    private  static final int DB_VERSION = 4;

    //Table name

       private static final String TABLE_NAME = "TECHQUIZ";
       private static final String UID = "_UID";
       private static final String QUESTION = "QUESTION";
       private static final String ANSWER = "ANSWER";
       private static final String OPT1 = "OPT1";
       private static final String OPT2 = "OPT2";
       private static final String OPT3 = "OPT3";
       private static final String OPT4 = "OPT4";
       private static final String OPT5 = "OPT5";
       private static final String OPT6 = "OPT6";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPT1 + " VARCHAR(255), " + OPT2 + " VARCHAR(255), " + OPT3 + " VARCHAR(255)," + OPT4+" VARCHAR(255), "+OPT5+" VARCHAR(255), "+OPT6 + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    //Drop table query
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public QuizHelper(Context context){
        super(context,DATABASE_NAME, null, DB_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
          db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

         db.execSQL(DROP_TABLE);
         onCreate(db);
    }

    public void allQuestion(){

        ArrayList<DbQuiz> arrayList = new ArrayList();

         arrayList.add(new DbQuiz("Galileo was an Italian astronomer who developed?", "Telescope", "Airoplane", "Electricity", "Train", "Telephone","Telegram","Telescope"));
         arrayList.add(new DbQuiz("Who invented the famous formula E=mc^2", "Albert Einstein", "Galileo", "Sarvesh", "Bill Gates","Elon Musk","Socrate", "Albert Einstein"));
        arrayList.add(new DbQuiz("Who is elected as president of us 2016", "Donald Trump", "John McCain","J.F Kennedy","Hilary Clinton", "John pol", "Barack Obama", "Donald Trump"));
        arrayList.add(new DbQuiz("who has won ballon d'or of 2017 ?", "Lionel Messi", "Cristiano Ronaldo","Modric","Antoine Griezman", "Neymar", "Kaka", "Cristiano Ronaldo"));
        arrayList.add(new DbQuiz("who has won ballon d'or of 2018 ?", "Lionel Messi", "Cristiano Ronaldo","Modric","Antoine Griezman", "Neymar", "Kaka", "Modric"));

        this.addAllQuestion(arrayList);
    }

            private void addAllQuestion(ArrayList<DbQuiz>allQuestions){

                SQLiteDatabase db =this.getWritableDatabase();
                db.beginTransaction();

        try {
            ContentValues contentValues = new ContentValues();
                for(DbQuiz question : allQuestions){
                    contentValues.put(QUESTION,question.getQuestion());
                    contentValues.put(ANSWER,question.getAnswer());
                    contentValues.put(OPT1,question.getOp1());
                    contentValues.put(OPT2,question.getOp2());
                    contentValues.put(OPT3,question.getOp3());
                    contentValues.put(OPT4,question.getOp4());
                    contentValues.put(OPT5,question.getOp5());
                    contentValues.put(OPT6,question.getOp6());
                    db.insert(TABLE_NAME,null,contentValues);
                }
             db.setTransactionSuccessful();
        } finally {
        db.endTransaction();
        db.close();
        }
            }

            public List<DbQuiz> getAllQuestions(){

        List<DbQuiz> questionList = new ArrayList<>();
        SQLiteDatabase db =this.getWritableDatabase();
         db.beginTransaction();
         String column[] ={UID,QUESTION,OPT1,OPT2,OPT3,OPT4,OPT5,OPT6,ANSWER};
        Cursor cursor =db.query(TABLE_NAME,column,null,null,null,null,null);

        while (cursor.moveToNext()){
            DbQuiz question =new DbQuiz();
            question.setId(cursor.getInt(0));
            question.setQuestion(cursor.getString(1));
            question.setOp1(cursor.getString(2));
            question.setOp2(cursor.getString(3));
            question.setOp3(cursor.getString(4));
            question.setOp4(cursor.getString(5));
            question.setOp5(cursor.getString(6));
            question.setOp6(cursor.getString(7));
            question.setAnswer(cursor.getString(8));
            questionList.add(question);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();

          return  questionList;
    }
}
