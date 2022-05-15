package com.example.entrenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.entrenapp.db.DbDietas;
import com.example.entrenapp.db.DbExamenmedico;

public class RegistroDietas extends AppCompatActivity {

    private TextView tviduserdieta, tvfecharegistrodieta;
    private EditText etdesayuno, etbrunch, etlunch, etonces, etcena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_dietas);
        tviduserdieta = findViewById(R.id.tvDietausuario);
        String id = getIntent().getStringExtra("id");
        tviduserdieta.setText(id);
        tvfecharegistrodieta=findViewById(R.id.tvDietafecha);
        etdesayuno = findViewById(R.id.etDietadesayuno);
        etbrunch = findViewById(R.id.etDietabrunch);
        etlunch = findViewById(R.id.etDietalunch);
        etonces = findViewById(R.id.etDietaonces);
        etcena = findViewById(R.id.etDietaCena);


    }
    //metodo para registrar dieta
    public void RegistrarDieta(View view){
        DbDietas dbdieta= new DbDietas(this);
        String iduserdieta=tviduserdieta.getText().toString();
        String fecha_registro_dieta=tvfecharegistrodieta.getText().toString();
        String desayuno=etdesayuno.getText().toString();
        String brunch=etbrunch.getText().toString();
        String lunch=etlunch.getText().toString();
        String onces=etonces.getText().toString();
        String cena=etcena.getText().toString();

        if(iduserdieta.isEmpty() || desayuno.isEmpty() || brunch.isEmpty() || lunch.isEmpty() || onces.isEmpty() ||
                cena.isEmpty() || fecha_registro_dieta.isEmpty()){
            Toast.makeText(this, "por favor diligencie todos los campos", Toast.LENGTH_SHORT).show();
        }

        if(desayuno.isEmpty()){
            etdesayuno.setError("digite un Desayuno");
        }
        if(brunch.isEmpty()){
            etbrunch.setError("digite un Brunch");
        }
        if(lunch.isEmpty()){
            etlunch.setError("digite un lunch");
        }
        if(onces.isEmpty()){
            etonces.setError("digite unas onces");
        }
        if(cena.isEmpty()){
            etcena.setError("digite una cena");
        }
        if(fecha_registro_dieta.isEmpty())
        {
            tvfecharegistrodieta.setError("Por favor seleccione una Fecha");
            Toast.makeText(this, "Por favor seleccione una Fecha", Toast.LENGTH_SHORT).show();
        }
        if(!iduserdieta.isEmpty()) {
            if(fecha_registro_dieta.length() !=0 && !desayuno.isEmpty() && !brunch.isEmpty() && !lunch.isEmpty() &&
                    !onces.isEmpty() && !cena.isEmpty()){
                tvfecharegistrodieta.setError(null);
                dbdieta.insertarDieta(
                        tviduserdieta.getText().toString(),
                        tvfecharegistrodieta.getText().toString(),
                        etdesayuno.getText().toString(),
                        etbrunch.getText().toString(),
                        etlunch.getText().toString(),
                        etonces.getText().toString(),
                        etcena.getText().toString());

                dbdieta.close();
                Toast.makeText(this, "Guardado Exitosamente", Toast.LENGTH_SHORT).show();
                limpiardieta();
            }
        }

    }
    private void limpiardieta(){
        tvfecharegistrodieta.setError(null);
        etdesayuno.setError(null);
        etbrunch.setError(null);
        etlunch.setError(null);
        etonces.setError(null);
        etcena.setError(null);
        tvfecharegistrodieta.setText("");
        etdesayuno.setText("");
        etbrunch.setText("");
        etlunch.setText("");
        etonces.setText("");
        etcena.setText("");
        tvfecharegistrodieta.setHint("aaaa/mm/dd");
    }
    //metodo de seleccionar fecha en boton calendario y mostrarlo en el edittext correspondiente
    public void mostrarCalendario(View view)
    {
        DatePickerDialog d=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                tvfecharegistrodieta.setText(year+"/"+(month+1)+"/"+dayOfMonth);

            }
        },2022,0,1);
        d.show();
    }

    //metodo cambiar de activitis
    public void onClick(View view){
        Intent miIntent=null;
        switch(view.getId()){
            case R.id.btnDietahistorial:
                miIntent=new Intent(this, ListaDietas.class);
                miIntent.putExtra("id",tviduserdieta.getText().toString());
                break;
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }
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