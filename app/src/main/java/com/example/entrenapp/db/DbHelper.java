package com.example.entrenapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NOMBRE="entrenapp.db";
    public static final String TABLE_USUARIOS ="t_usuarios";
    public static final String TABLE_DIETAS ="t_dietas";
    public static final String TABLE_RUTINA="t_rutina";
    public static final String TABLE_EXAMEN="t_examenmedico";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table "+ TABLE_USUARIOS +"(" +
                "id INTEGER PRIMARY KEY NOT NULL," +
                "nombre TEXT NOT NULL," +
                "apellido TEXT NOT NULL," +
                "telefono INT NOT NULL," +
                "password TEXT NOT NULL)");

        sqLiteDatabase.execSQL("create table " + TABLE_DIETAS + "(" +
                "id_dieta INTEGER primary key autoincrement NOT NULL," +
                " id_user INTEGER," +
                " fecha_registro_dieta String," +
                " desayuno String," +
                " brunch String," +
                " lunch String," +
                " onces String," +
                " cena String )");

        sqLiteDatabase.execSQL("create table " + TABLE_RUTINA+ "(" +
                "id_registro INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                "id_user TEXT,"+
                "fecha_entreno TEXT,"+
                "hora_entreno TEXT,"+
                "pectorales TEXT ," +
                "deltoides TEXT, " +
                "brazos TEXT ," +
                "espalda TEXT ," +
                "piernas TEXT ," +
                "gluteos TEXT ," +
                "trapecios TEXT ," +
                "abdominales TEXT )");

        sqLiteDatabase.execSQL("create table " + TABLE_EXAMEN+ "(" +
                "id_registro INTEGER PRIMARY KEY autoincrement NOT NULL ,"+
                "iduser text NOT NULL," +
                "estatura TEXT ," +
                "peso TEXT," +
                "imc TEXT,"+
                "incapacidad Text," +
                "objetivo TEXT,"+
                "fecha_registro_medico TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table "+ TABLE_USUARIOS );
        sqLiteDatabase.execSQL("drop table "+ TABLE_RUTINA );
        sqLiteDatabase.execSQL("drop table "+ TABLE_EXAMEN );
        sqLiteDatabase.execSQL("drop table "+ TABLE_DIETAS );
        onCreate(sqLiteDatabase);
    }

    public Cursor ConsultarUsuPas(String id, String password) throws SQLException {
        Cursor mcursor=null;
        mcursor=this.getReadableDatabase().query("t_usuarios", new String[]{"id","nombre","apellido","telefono","password"},"id like '"+id+"' and password like '"+password+"'",null,null,null,null);
        return mcursor;
    }

    public Cursor TraerNombreUsu(String id) throws  SQLException{
        Cursor traercursor=null;
        traercursor=this.getReadableDatabase().query("t_usuarios", new String[]{"id","nombre","apellido","telefono","password"},"id like '"+id+"'",null,null,null,null);
        return traercursor;
    }


}

