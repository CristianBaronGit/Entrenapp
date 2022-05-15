package com.example.entrenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.entrenapp.db.DbRutina;

public class RegistroRutina extends AppCompatActivity {

    private TextView tviduserrutina, fechaentreno, horaentreno;

    private CheckBox pectoralesmusculo, deltoidesmusculo, brazosmusculo, espaldamusculo, piernasmusculo, gluteosmusculo,
            trapeciosmusculo, abdominalesmusculo;
    private Button btnRegistrarmusculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_rutina);

        fechaentreno= (TextView) findViewById(R.id.tvFechaentrenamiento);
        horaentreno= (TextView) findViewById(R.id.tvHoraentrenamiento);
        tviduserrutina = (TextView) findViewById(R.id.tvIdusuariorutina);
        pectoralesmusculo= findViewById(R.id.cbPectorales);
        deltoidesmusculo= findViewById(R.id.cbDeltoides);
        brazosmusculo=findViewById(R.id.cbBrazos);
        espaldamusculo=findViewById(R.id.cbEspalda);
        piernasmusculo=findViewById(R.id.cbPiernas);
        gluteosmusculo=findViewById(R.id.cbGluteos);
        trapeciosmusculo=findViewById(R.id.cbTrapecios);
        abdominalesmusculo=findViewById(R.id.cbAbdominales);
        btnRegistrarmusculo = (Button) findViewById(R.id.btnguardar);
        String id = getIntent().getStringExtra("id");
        tviduserrutina.setText(id);

    }

    public void Registrar(View view) {
        DbRutina dbRutina = new DbRutina(this);

        String id_user = tviduserrutina.getText().toString();
        String fecha_entreno = fechaentreno.getText().toString();
        String hora_entreno = horaentreno.getText().toString();
        String pectorales = pectoralesmusculo.getText().toString();
        String deltoides = deltoidesmusculo.getText().toString();
        String brazos = brazosmusculo.getText().toString();
        String espalda = espaldamusculo.getText().toString();
        String piernas = piernasmusculo.getText().toString();
        String gluteos = gluteosmusculo.getText().toString();
        String trapecios = trapeciosmusculo.getText().toString();
        String abdominales = abdominalesmusculo.getText().toString();

        if(id_user.isEmpty()){
            tviduserrutina.setError("Debe digitar un Id");
        }
        if(fecha_entreno.isEmpty()){
            fechaentreno.setError("Seleccione una fecha para entrenar");
        }
        if(hora_entreno.isEmpty()){
            horaentreno.setError("Seleccione una hora para entrenar");
        }
        if(!pectoralesmusculo.isChecked() && !deltoidesmusculo.isChecked() && !brazosmusculo.isChecked() && !espaldamusculo.isChecked() &&
                !piernasmusculo.isChecked() && !gluteosmusculo.isChecked() && !trapeciosmusculo.isChecked() && !abdominalesmusculo.isChecked()){
            Toast.makeText(getApplicationContext(),"Debe seleccionar al menos un grupo muscular",Toast.LENGTH_LONG).show();
        }
        if(pectoralesmusculo.isChecked()){
            pectoralesmusculo.setText("Pectorales");
        }
        if(deltoidesmusculo.isChecked()){
            deltoidesmusculo.setText("Deltoides");
        }
        if(brazosmusculo.isChecked()){
            brazosmusculo.setText("Brazos");
        }
        if(espaldamusculo.isChecked()){
            espaldamusculo.setText("Espalda");
        }
        if(piernasmusculo.isChecked()){
            piernasmusculo.setText("Piernas");
        }
        if(gluteosmusculo.isChecked()){
            gluteosmusculo.setText("Gluteos");
        }
        if(trapeciosmusculo.isChecked()){
            trapeciosmusculo.setText("Trapecios");
        }
        if(abdominalesmusculo.isChecked()){
            abdominalesmusculo.setText("Abdominales");
        }
        if(!id_user.isEmpty()) {
            if (fecha_entreno.length() !=0||hora_entreno.length()!=0||!pectorales.isEmpty() || !deltoides.isEmpty() || !brazos.isEmpty() || !espalda.isEmpty() || !piernas.isEmpty() ||
                    !gluteos.isEmpty() || !trapecios.isEmpty() || !abdominales.isEmpty()){

                dbRutina.insertarRutina(tviduserrutina.getText().toString(),
                        fechaentreno.getText().toString(),
                        horaentreno.getText().toString(),
                        pectoralesmusculo.getText().toString(),
                        deltoidesmusculo.getText().toString(),
                        brazosmusculo.getText().toString(),
                        espaldamusculo.getText().toString(),
                        piernasmusculo.getText().toString(),
                        gluteosmusculo.getText().toString(),
                        trapeciosmusculo.getText().toString(),
                        abdominalesmusculo.getText().toString());
                dbRutina.close();
                Toast.makeText(this, "Guardado Exitosamente", Toast.LENGTH_SHORT).show();
                limpiar();
            }
        }
    }

    /*public void Buscar (View view){
        DbHelper admin = new DbHelper(this);
        SQLiteDatabase BaseDeDatos = admin.getReadableDatabase();

        String id_user = idmusculo.getText().toString();

        if(id_user.isEmpty())
        {
            idmusculo.setError("Por favor digite su numero de documento");
        }
        if(!id_user.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery
                    ("select * from t_rutina where id_user =" + id_user, null);
            if(fila.moveToFirst()){
                pectoralesmusculo.setText(fila.getString(1));
                deltoidesmusculo.setText(fila.getString(2));
                brazosmusculo.setText(fila.getString(3));
                espaldamusculo.setText(fila.getString(4));
                piernasmusculo.setText(fila.getString(5));
                gluteosmusculo.setText(fila.getString(6));
                trapeciosmusculo.setText(fila.getString(7));
                abdominalesmusculo.setText(fila.getString(8));
                BaseDeDatos.close();
            }else{
                Toast.makeText(this, "No existe el Usuario Con N° Documento " + id_user, Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        }else{
            Toast.makeText(this, "Debes digitar un N° de documento para buscar", Toast.LENGTH_SHORT).show();
        }

    }*/

    //metodo de seleccionar fecha en boton calendario y mostrarlo en el edittext correspondiente
    public void mostrarCalendario(View view)
    {
        DatePickerDialog d=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                fechaentreno.setText(year+"/"+(month+1)+"/"+dayOfMonth);

            }
        },2022,0,1);
        d.show();
    }

    //metodo de seleccionar hora en boton hora y mostrarlo en el edittext correspondiente
    public void mostrarHora(View view)
    {
        TimePickerDialog t=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                horaentreno.setText(hourOfDay+":"+minute);
            }
        },12,00,true );
        t.show();
    }
    //metodo cambiar de activitis
    public void onClick(View view){
        Intent miIntent=null;
        switch(view.getId()){
            case R.id.btnRutinas:
                //miIntent=new Intent(this, MainActivity2.class);
                break;
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }
    }

    //metodo para limpiar y dejar los campos como nuevos
    private void limpiar(){
        fechaentreno.setText("");
        horaentreno.setText("");
        pectoralesmusculo.setText("");
        deltoidesmusculo.setText("");
        brazosmusculo.setText("");
        espaldamusculo.setText("");
        piernasmusculo.setText("");
        gluteosmusculo.setText("");
        trapeciosmusculo.setText("");
        abdominalesmusculo.setText("");
        fechaentreno.setHint("aaaa/mm/dd");
        horaentreno.setHint("hh:mm");
        pectoralesmusculo.setHint("Pectorales");
        deltoidesmusculo.setHint("Deltoides");
        brazosmusculo.setHint("Brazos");
        espaldamusculo.setHint("Espalda");
        piernasmusculo.setHint("Piernas");
        gluteosmusculo.setHint("Gluteos");
        trapeciosmusculo.setHint("Trapecios");
        abdominalesmusculo.setHint("Abdominales");
        fechaentreno.setError(null);
        horaentreno.setError(null);
        pectoralesmusculo.setChecked(false);
        deltoidesmusculo.setChecked(false);
        brazosmusculo.setChecked(false);
        espaldamusculo.setChecked(false);
        piernasmusculo.setChecked(false);
        gluteosmusculo.setChecked(false);
        trapeciosmusculo.setChecked(false);
        abdominalesmusculo.setChecked(false);
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
            Intent iniciosesion = new Intent (this, RegistroDietas.class);
            iniciosesion.putExtra("id",tviduserrutina.getText().toString());
            startActivity(iniciosesion);
        }else if (id == R.id.itemocerflowrutinaPrincipal){
            Intent iniciosesion = new Intent (this, MainActivity.class);
            iniciosesion.putExtra("id",tviduserrutina.getText().toString());
            startActivity(iniciosesion);
        }else if (id == R.id.itemrutinacerrarsesion){
            Intent iniciosesion = new Intent (this, IngresoUsuario.class);
            iniciosesion.putExtra("id",tviduserrutina.getText().toString());
            startActivity(iniciosesion);
        }else if (id == R.id.itemoverflowrutinanuevoexamenmed){
            Intent iniciosesion = new Intent (this, RegistroExamenmedico.class);
            iniciosesion.putExtra("id",tviduserrutina.getText().toString());
            startActivity(iniciosesion);
        }
        return super.onOptionsItemSelected(item);
    }

}