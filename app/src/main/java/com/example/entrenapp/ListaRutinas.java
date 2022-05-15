package com.example.entrenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.entrenapp.adaptadores.ListaRutinasAdapter;
import com.example.entrenapp.db.DbRutina;
import com.example.entrenapp.entidades.Rutinas;

import java.util.ArrayList;

public class ListaRutinas extends AppCompatActivity {

    RecyclerView listaRutinas;
    ArrayList<Rutinas> listaArrayRutinas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_rutinas);
        listaRutinas = findViewById(R.id.listaRutinas);
        listaRutinas.setLayoutManager(new LinearLayoutManager(this));

        DbRutina dbRutina = new DbRutina(this);

        listaArrayRutinas = new ArrayList<>();

        ListaRutinasAdapter adapter = new ListaRutinasAdapter(dbRutina.mostrarRutinas());
        listaRutinas.setAdapter(adapter);
    }

    public void onClick(View view){
        Intent miIntent=null;
        /*switch(view.getId()){
            case R.id.btnnuevoregistro:
                miIntent=new Intent(MainActivity2.this, MainActivity.class);
                break;
        }*/
        if (miIntent!=null){
            startActivity(miIntent);
        }
    }
    //metodo para mostrar y ocultar el menu del actionbar
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflowrutina, menu);
        return true;
    }

    //metodo para asignar las funciones a las opciones del actionbar
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.itemoverflowrutinanuevadieta){
            Intent iniciosesion = new Intent (this, RegistroRutina.class);
            startActivity(iniciosesion);
        }else if (id == R.id.itemocerflowrutinaPrincipal){
            Intent iniciosesion = new Intent (this, MainActivity.class);
            startActivity(iniciosesion);
        }
        return super.onOptionsItemSelected(item);
    }
}