package com.claudio.apptreino;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {
    private static final int VERSAO = 1;
    private static final String NOME ="AppTreino";

    public Banco(Context context){
        super(context, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS treino ( " +
                "     id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ," +
                "     exercicio TEXT NOT NULL ," +
                "     repeticao TEXT," +
                "     series TEXT," +
                "     dia TEXT ) "  );




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
