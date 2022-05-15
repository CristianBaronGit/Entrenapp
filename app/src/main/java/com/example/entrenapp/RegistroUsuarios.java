package com.example.entrenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.entrenapp.db.DbHelper;
import com.example.entrenapp.db.DbUsuarios;

public class RegistroUsuarios extends AppCompatActivity {

    private EditText id_user, nombre_user, apellido_user, telefono_user, password_user,password_user2;
    private Button btnRegistraruser,btnNuevouser,btnActualizaruser,btnEliminaruser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);

        id_user = (EditText) findViewById(R.id.etIdUser);
        nombre_user=findViewById(R.id.etnombre);
        apellido_user=findViewById(R.id.etapellido);
        telefono_user=findViewById(R.id.ettelefono);
        password_user=findViewById(R.id.etpassword);
        password_user2=findViewById(R.id.etpassword2);
        btnRegistraruser = (Button) findViewById(R.id.btnregistrarusuario);
        btnNuevouser=(Button) findViewById(R.id.btnregistrarusuario);
    }

    public void Registrar(View view) {
        DbUsuarios dbUsuarios = new DbUsuarios(this);

        String id = id_user.getText().toString();
        String nombre = nombre_user.getText().toString();
        String apellido = apellido_user.getText().toString();
        String telefono = telefono_user.getText().toString();
        String password = password_user.getText().toString();
        String password2 = password_user2.getText().toString();

        if(id.isEmpty()){
            id_user.setError("Por favor digite su numero de documento");
        }
        if(nombre.isEmpty()) {
            nombre_user.setError("Por favor digite su nombre");
        }
        if(apellido.isEmpty()) {
            apellido_user.setError("Por favor digite su apellido");
        }
        if(telefono.isEmpty()) {
            telefono_user.setError("Por favor digite numero telefonico");
        }
        if(password.isEmpty()) {
            password_user.setError("Por favor digite una Contraseña");
        }
        if(password2.isEmpty()) {
            password_user2.setError("Por favor digite la confirmacion de Contraseña");
        }

        if (!id.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !telefono.isEmpty() && !password.isEmpty() && !password2.isEmpty()){
            if (!password.isEmpty()){
                if (password.equals(password2)) {
                    dbUsuarios.insertarUsuario(id_user.getText().toString(), nombre_user.getText().toString(),apellido_user.getText().toString(),telefono_user.getText().toString(),password_user.getText().toString());
                    dbUsuarios.close();
                    Toast.makeText(this, "Registro Exitoso del Usuario " + nombre + " " + apellido, Toast.LENGTH_SHORT).show();
                    btnRegistraruser.setVisibility(View.INVISIBLE);
                    //btnNuevouser.setVisibility(View.VISIBLE);
                    limpiar();
                } else {
                    // Cuando la condiccion es falsa se presenta un mensaje de error
                    Toast.makeText(getApplicationContext(), "Las contraseñas ingresadas no coinciden", Toast.LENGTH_LONG).show();
                }
            }

        }else{

            Toast.makeText(this,"Debes diligenciar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    //Metodo para consultar un Usuario
    public void Buscar (View view){
        DbHelper admin = new DbHelper(this);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String id = id_user.getText().toString();

        if(id.isEmpty())
        {
            id_user.setError("Por favor digite su numero de documento");
        }
        if(!id.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery
                    ("select nombre, apellido, telefono from t_usuarios where id =" + id, null);
            if(fila.moveToFirst()){
                nombre_user.setText(fila.getString(0));
                apellido_user.setText(fila.getString(1));
                telefono_user.setText(fila.getString(2));
                password_user.setVisibility(View.INVISIBLE);
                password_user2.setVisibility(View.INVISIBLE);
                BaseDeDatos.close();
                //btnActualizaruser.setVisibility(View.VISIBLE);
                //btnEliminaruser.setVisibility(View.VISIBLE);
                btnRegistraruser.setVisibility(View.INVISIBLE);
            }else{
                limpiar();
                Toast.makeText(this, "No existe el Usuario Con N° Documento " + id, Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        }else{
            Toast.makeText(this, "Debes digitar un N° de documento para buscar", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiar(){
        id_user.setText("");
        nombre_user.setText("");
        apellido_user.setText("");
        telefono_user.setText("");
        password_user.setText("");
        password_user2.setText("");
    }
    private void noerrorset(){
        id_user.setError(null);
        nombre_user.setError(null);
        apellido_user.setError(null);
        telefono_user.setError(null);
        password_user.setError(null);
        password_user2.setError(null);
    }

    //metodo para moverse entre activitis
    public void onClick(View view){
        Intent miIntent=null;
        switch(view.getId()){
            case R.id.btnvolverregistro:
                miIntent=new Intent(this, IngresoUsuario.class);
                break;
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }
    }

}