package com.example.entrenapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.entrenapp.entidades.Rutinas;

import java.util.ArrayList;

public class DbRutina extends DbHelper{

    Context context;

    public DbRutina(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public void insertarRutina(String id_user,
                               String fecha_entreno,
                               String hora_entreno,
                               String pectorales,
                               String deltoides,
                               String brazos,
                               String espalda,
                               String piernas,
                               String gluteos,
                               String trapecios,
                               String abdominales) {
        try{
            DbHelper admin = new DbHelper(context);
            SQLiteDatabase db = admin.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("id_user", id_user);
            valores.put("fecha_entreno", fecha_entreno);
            valores.put("hora_entreno", hora_entreno);
            valores.put("pectorales",pectorales);
            valores.put("deltoides",deltoides);
            valores.put("brazos",brazos);
            valores.put("espalda",espalda);
            valores.put("piernas",piernas);
            valores.put("gluteos",gluteos);
            valores.put("trapecios",trapecios);
            valores.put("abdominales",abdominales);

            db.insert(TABLE_RUTINA, null ,valores);
        }catch (Exception ex){
            ex.toString();
        }
    }
    public ArrayList<Rutinas> mostrarRutinas(){
        DbHelper admin = new DbHelper(context);
        SQLiteDatabase db = admin.getWritableDatabase();

        ArrayList<Rutinas> listaRutinas =  new ArrayList<>();
        Rutinas rutina = null;
        Cursor cursorRutinas = null;

        cursorRutinas = db.rawQuery("select id_registro,fecha_entreno,hora_entreno from " + TABLE_RUTINA+ " ORDER BY fecha_entreno desc ", null);

        if(cursorRutinas.moveToFirst()){
            do{
                rutina = new Rutinas();
                rutina.setId_registro(cursorRutinas.getInt(0));
                rutina.setFecha_entreno(cursorRutinas.getString(1));
                rutina.setHora_entreno(cursorRutinas.getString(2));
                listaRutinas.add(rutina);
            }while (cursorRutinas.moveToNext());
        }
        cursorRutinas.close();
        return listaRutinas;
    }

    /*public Rutinas verRutina(int id){
        DbHelper admin = new DbHelper(context);
        SQLiteDatabase db = admin.getWritableDatabase();

        Rutinas rutina = null;
        Cursor cursorRutinas = null;

        cursorRutinas = db.rawQuery("select id_registro,fecha_entreno,hora_entreno from " + TABLE_RUTINA + " where id_registro = "+ id + " limit 1", null);

        if(cursorRutinas.moveToFirst()){

            rutina = new Rutinas();
            rutina.setId_registro(cursorRutinas.getInt(0));
            rutina.setFecha_entreno(cursorRutinas.getString(1));
            rutina.setHora_entreno(cursorRutinas.getString(2));

        }
        cursorRutinas.close();
        return rutina;
    }*/
}
