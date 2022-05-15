package com.example.entrenapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbUsuarios extends DbHelper{

    Context context;

    public DbUsuarios(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public void insertarUsuario(String id,
                                String nombre,
                                String apellido,
                                String telefono,
                                String password) {
        try{
            DbHelper admin = new DbHelper(context);
            SQLiteDatabase db = admin.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("id", id);
            valores.put("nombre",nombre);
            valores.put("apellido",apellido);
            valores.put("telefono",telefono);
            valores.put("password",password);

            db.insert(TABLE_USUARIOS, null ,valores);
        }catch (Exception ex){
            ex.toString();

        }
    }

}

