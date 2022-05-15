package com.example.entrenapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbExamenmedico extends DbHelper{

    Context context;

    public DbExamenmedico(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public void insertarExamenmedico(String iduser,
                                     String estatura,
                                     String peso,
                                     String imc,
                                     String incapacidad,
                                     String objetivo,
                                     String fecha_registro_medico) {


        try{
            DbHelper admin = new DbHelper(context);
            SQLiteDatabase db = admin.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("iduser", iduser);
            valores.put("estatura", estatura);
            valores.put("peso", peso);
            valores.put("imc", imc);
            valores.put("incapacidad", incapacidad);
            valores.put("objetivo",objetivo);
            valores.put("fecha_registro_medico",fecha_registro_medico);


            db.insert(TABLE_EXAMEN, null ,valores);
        }catch (Exception ex){
            ex.toString();
        }
    }

}

