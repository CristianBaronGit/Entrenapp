package com.example.entrenapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.entrenapp.entidades.Dietas;
import com.example.entrenapp.entidades.Rutinas;

import java.util.ArrayList;

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
    public ArrayList<Dietas> mostrarDietas(){
        DbHelper admin = new DbHelper(context);
        SQLiteDatabase db = admin.getWritableDatabase();

        ArrayList<Dietas> listaDietas =  new ArrayList<>();
        Dietas dieta = null;
        Cursor cursorDietas = null;

        cursorDietas = db.rawQuery("select id_dieta,fecha_registro_dieta from " + TABLE_DIETAS+ " ORDER BY id_dieta desc ", null);

        if(cursorDietas.moveToFirst()){
            do{
                dieta = new Dietas();
                dieta.setId_dieta(cursorDietas.getString(0));
                dieta.setFecha_registro_dieta(cursorDietas.getString(1));
                listaDietas.add(dieta);
            }while (cursorDietas.moveToNext());
        }
        cursorDietas.close();
        return listaDietas;
    }
}

