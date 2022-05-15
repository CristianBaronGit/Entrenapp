package com.example.entrenapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbDietas extends DbHelper{

    Context context;

    public DbDietas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public void insertarDieta(String id_user,
                              String fecha_registro_dieta,
                              String desayuno,
                              String brunch,
                              String lunch,
                              String onces,
                              String cena) {
        try{
            DbHelper admin = new DbHelper(context);
            SQLiteDatabase db = admin.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("id_user", id_user);
            valores.put("fecha_registro_dieta",fecha_registro_dieta);
            valores.put("desayuno",desayuno);
            valores.put("brunch",brunch);
            valores.put("lunch",lunch);
            valores.put("onces",onces);
            valores.put("cena",cena);

            db.insert(TABLE_DIETAS, null ,valores);
        }catch (Exception ex){
            ex.toString();

        }
    }
}

