package com.example.entrenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class RegistroDietas extends AppCompatActivity {

    private TextView tviduserdieta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_dietas);
        tviduserdieta = findViewById(R.id.tvDietausuario);
        String id = getIntent().getStringExtra("id");
        tviduserdieta.setText(id);
    }
    //metodo para mostrar y ocultar el menu del actionbar
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflowdieta, menu);
        return true;
    }

    //metodo para asignar las funciones a las opciones del actionbar
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.itemoverflowdietanuevarutina){
            Intent iniciosesion = new Intent (this, RegistroRutina.class);
            iniciosesion.putExtra("id",tviduserdieta.getText().toString());
            startActivity(iniciosesion);
        }else if (id == R.id.itemoverflowdietainicio){
            Intent iniciosesion = new Intent (this, MainActivity.class);
            iniciosesion.putExtra("id",tviduserdieta.getText().toString());
            startActivity(iniciosesion);
        }else if (id == R.id.itemrutinacerrarsesion){
            Intent iniciosesion = new Intent (this, IngresoUsuario.class);
            iniciosesion.putExtra("id",tviduserdieta.getText().toString());
            startActivity(iniciosesion);
        }else if (id == R.id.itemoverflowdietanuevoexamenmed){
            Intent iniciosesion = new Intent (this, RegistroExamenmedico.class);
            iniciosesion.putExtra("id",tviduserdieta.getText().toString());
            startActivity(iniciosesion);
        }
        return super.onOptionsItemSelected(item);
    }
}