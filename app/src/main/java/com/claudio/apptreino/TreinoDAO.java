package com.claudio.apptreino;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TreinoDAO {

    public static void inserir (Treino treino, Context context){
        ContentValues valores = new ContentValues();
        valores.put("exercicio", treino.exercicio);
        valores.put("repeticao", treino.repeticao);
        valores.put("series", treino.series);
        valores.put("dia", treino.dia);

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("treino", null, valores);


    }

    public static void editar (Treino treino, Context context){
        ContentValues valores = new ContentValues();
        valores.put("exercicio", treino.exercicio);
        valores.put("repeticao", treino.repeticao);
        valores.put("series", treino.series);
        valores.put("dia", treino.dia);

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.update("treino", valores,"id="+treino.id,null);

    }

    public static void excluir (int id, Context context){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("treino", "id= " + id,null);

    }

    public static List<Treino> getTreino(Context context){
        List<Treino> lista = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM treino ORDER BY dia",null);
        if ( cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Treino treino = new Treino();
                treino.id = cursor.getInt( 0);
                treino.exercicio = cursor.getString(1);
                treino.repeticao = cursor.getString(2) ;
                treino.series = cursor.getString(3) ;
                treino.dia = cursor.getString(4) ;
                lista.add( treino );

            }while( cursor.moveToNext() );

        }

        return lista;
    }
    public static Treino getTreinoById(Context context, int id){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM treino WHERE id = " + id,null);
        if ( cursor.getCount()>0){
            cursor.moveToFirst();
                Treino treino = new Treino();
                treino.id = cursor.getInt( 0);
                treino.exercicio = cursor.getString(1);
                treino.repeticao = cursor.getString(2) ;
                treino.series = cursor.getString(3) ;
                treino.dia = cursor.getString(4) ;
            return treino;

        }else{
            return null;
        }


    }
}
