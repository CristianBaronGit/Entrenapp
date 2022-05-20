package com.example.entrenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.entrenapp.adaptadores.ListaDietasAdapter;
import com.example.entrenapp.adaptadores.ListaRutinasAdapter;
import com.example.entrenapp.db.DbDietas;
import com.example.entrenapp.db.DbRutina;
import com.example.entrenapp.entidades.Dietas;
import com.example.entrenapp.entidades.Rutinas;

import java.util.ArrayList;

public class ListaDietas extends AppCompatActivity {

    private TextView tviduserlistadietas;

    RecyclerView listaDietas;
    ArrayList<Dietas> listaArrayDietas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dietas);
        listaDietas = findViewById(R.id.listaDietas);
        listaDietas.setLayoutManager(new LinearLayoutManager(this));

        DbDietas dbDietas = new DbDietas(this);

        listaArrayDietas = new ArrayList<>();

        ListaDietasAdapter adapter = new ListaDietasAdapter(dbDietas.mostrarDietas());
        listaDietas.setAdapter(adapter);

        tviduserlistadietas=findViewById(R.id.tvIduserlistadieta);
        String id = getIntent().getStringExtra("id");
        tviduserlistadietas.setText(id);
    }

    //metodo para mostrar y ocultar el menu del actionbar
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflowlistadietas, menu);
        return true;
    }

    //metodo para asignar las funciones a las opciones del actionbar
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.itemoverflowlistadietaprincipal){
            Intent iniciosesion = new Intent (this, MainActivity.class);
            iniciosesion.putExtra("id",tviduserlistadietas.getText().toString());
            startActivity(iniciosesion);
        }else if (id == R.id.itemoverflowlistadietacerrarsesion){
            Intent iniciosesion = new Intent (this, IngresoUsuario.class);
            iniciosesion.putExtra("id",tviduserlistadietas.getText().toString());
            startActivity(iniciosesion);
        }else if (id == R.id.itemoverflowlistadietanuevadieta){
            Intent iniciosesion = new Intent (this, RegistroDietas.class);
            iniciosesion.putExtra("id",tviduserlistadietas.getText().toString());
            startActivity(iniciosesion);
        }
        return super.onOptionsItemSelected(item);
    }


}