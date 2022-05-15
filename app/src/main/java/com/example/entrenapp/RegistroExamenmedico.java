package com.example.entrenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.entrenapp.db.DbExamenmedico;
import com.example.entrenapp.db.DbHelper;

public class RegistroExamenmedico extends AppCompatActivity {

    private TextView tviduserexamenmed,fechaexamenmedico, incapacidadtitulo ;
    private EditText estatura, peso, imc, incapacidad, objetivo;
    Switch switchincapacidad;
    private Button botonguardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_examenmedico);
        tviduserexamenmed = (TextView) findViewById(R.id.tvIduserexamenmedico);
        fechaexamenmedico = (TextView) findViewById(R.id.tvFechaexamenmedico);
        estatura = (EditText) findViewById(R.id.etEstaturaexamenmedico);
        peso = (EditText) findViewById(R.id.etPesoexamenmedico);
        imc = (EditText) findViewById(R.id.etImcexamenmedico);
        incapacidad = (EditText) findViewById(R.id.etIncapacidadexamenmedico);
        objetivo = (EditText) findViewById(R.id.etObjetivoexamenmedico);
        incapacidadtitulo = (TextView) findViewById(R.id.tvIncapacidadtitulo);
        botonguardar = (Button) findViewById(R.id.btnGuardarExamenmedico);
        String id = getIntent().getStringExtra("id");
        tviduserexamenmed.setText(id);

        //activar o descativar boton anterior con el switch
        switchincapacidad = (Switch) findViewById(R.id.switch1);
        switchincapacidad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (switchincapacidad.isChecked()){
                    incapacidadtitulo.setVisibility(View.VISIBLE);
                    incapacidad.setVisibility(View.VISIBLE);
                }else{
                    incapacidadtitulo.setVisibility(View.INVISIBLE);
                    incapacidad.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    //metodo de seleccionar fecha en boton calendario y mostrarlo en el edittext correspondiente
    public void mostrarCalendario(View view)
    {
        DatePickerDialog d=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                fechaexamenmedico.setText(year+"/"+(month+1)+"/"+dayOfMonth);

            }
        },2022,0,1);
        d.show();
    }

    //metodo para asignar al boton para validar vacios
    public void validaryagregar(View v)
    {
        DbExamenmedico dbExamenmedico= new DbExamenmedico(this);
        String iduserem=tviduserexamenmed.getText().toString();
        String estaturaem=estatura.getText().toString();
        String pesoem=peso.getText().toString();
        String imcem=imc.getText().toString();
        String incapacidadem=incapacidad.getText().toString();
        String objetivoem=objetivo.getText().toString();
        String fechaem=fechaexamenmedico.getText().toString();


        if(iduserem.isEmpty() || estaturaem.isEmpty() || pesoem.isEmpty() || imcem.isEmpty() || incapacidadem.isEmpty() ||
                objetivoem.isEmpty() || fechaem.isEmpty()){
            Toast.makeText(this, "por favor diligencie todos los campos", Toast.LENGTH_SHORT).show();
        }
        if(iduserem.isEmpty()){
            tviduserexamenmed.setError("digite su usuario");
        }
        if(estaturaem.isEmpty()){
            estatura.setError("digite su estatura");
        }
        if(pesoem.isEmpty()){
            peso.setError("digite su peso");
        }
        if(imcem.isEmpty()){
            imc.setError("digite su imc");
        }
        if(objetivoem.isEmpty()){
            objetivo.setError("digite su objetivo");
        }
        if(fechaem.isEmpty())
        {
            fechaexamenmedico.setError("Por favor seleccione una Fecha");
            Toast.makeText(this, "Por favor seleccione una Fecha", Toast.LENGTH_SHORT).show();
        }
        if(fechaem.length() !=0 && !iduserem.isEmpty() && !estaturaem.isEmpty() && !pesoem.isEmpty() && !imcem.isEmpty() &&
                !objetivoem.isEmpty())
        {
            fechaexamenmedico.setError(null);
            dbExamenmedico.insertarExamenmedico(
                    tviduserexamenmed.getText().toString(),
                    estatura.getText().toString(),
                    peso.getText().toString(),
                    imc.getText().toString(),
                    incapacidad.getText().toString(),
                    objetivo.getText().toString(),
                    fechaexamenmedico.getText().toString());

            dbExamenmedico.close();
            limpiar();
            Toast.makeText(this, "Guardado Exitosamente", Toast.LENGTH_SHORT).show();
        }


    }

    public void Buscar (View view){
        DbHelper admin = new DbHelper(this);
        SQLiteDatabase BaseDeDatos = admin.getReadableDatabase();

        String iduserem=tviduserexamenmed.getText().toString();

        if(iduserem.isEmpty())
        {
            tviduserexamenmed.setError("Por favor digite su numero de documento");
        }
        if(!iduserem.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery
                    ("select *  from t_examenmedico where iduser =" + iduserem, null);
            if(fila.moveToFirst()){
                estatura.setText(fila.getString(2));
                peso.setText(fila.getString(3));
                imc.setText(fila.getString(4));
                incapacidad.setText(fila.getString(5));
                objetivo.setText(fila.getString(6));
                fechaexamenmedico.setText(fila.getString(7));
                BaseDeDatos.close();
                botonguardar.setVisibility(View.INVISIBLE);
                incapacidadtitulo.setVisibility(View.VISIBLE);
                incapacidad.setVisibility(View.VISIBLE);

            }else{
                Toast.makeText(this, "No existe Registro de fecha Con id " + iduserem, Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        }else{
            limpiar();
            Toast.makeText(this, "Debes digitar un N° de documento para buscar", Toast.LENGTH_SHORT).show();
        }

    }
    public void inicio(View view){
        botonguardar.setVisibility(View.VISIBLE);
        incapacidadtitulo.setVisibility(View.INVISIBLE);
        incapacidad.setVisibility(View.INVISIBLE);
        limpiar();
    }

    public void limpiar(){
        estatura.setText("");
        peso.setText("");
        imc.setText("");
        incapacidad.setText("");
        objetivo.setText("");
        fechaexamenmedico.setText("");
        estatura.setText("Estatura");
        peso.setText("Peso");
        imc.setText("IMC");
        fechaexamenmedico.setHint("aaaa/mm/dd");
    }

    //metodo para mostrar y ocultar el menu del actionbar
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflowexamenmed, menu);
        return true;
    }

    //metodo para asignar las funciones a las opciones del actionbar
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.itemoverflowexamenmednuevarutina){
            Intent iniciosesion = new Intent (this, RegistroRutina.class);
            iniciosesion.putExtra("id",tviduserexamenmed.getText().toString());
            startActivity(iniciosesion);
        }else if (id == R.id.itemoverflowexamenmedinicio){
            Intent iniciosesion = new Intent (this, MainActivity.class);
            iniciosesion.putExtra("id",tviduserexamenmed.getText().toString());
            startActivity(iniciosesion);
        }else if (id == R.id.itemoverflowexamenmedcerrarsesion){
            Intent iniciosesion = new Intent (this, IngresoUsuario.class);
            iniciosesion.putExtra("id",tviduserexamenmed.getText().toString());
            startActivity(iniciosesion);
        }else if (id == R.id.itemoverflowexamenmednuevadieta){
            Intent iniciosesion = new Intent (this, RegistroDietas.class);
            iniciosesion.putExtra("id",tviduserexamenmed.getText().toString());
            startActivity(iniciosesion);

        }
        return super.onOptionsItemSelected(item);
    }
}