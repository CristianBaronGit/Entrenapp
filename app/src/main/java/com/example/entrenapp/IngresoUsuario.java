package com.example.entrenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.entrenapp.db.DbUsuarios;

public class IngresoUsuario extends AppCompatActivity {

    private EditText userlogin, passwordlogin;
    private Button btningresaruser, btnregistraruser;
    DbUsuarios dbUsuarios = new DbUsuarios(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_usuario);

        userlogin = (EditText) findViewById(R.id.etiduseringreso);
        passwordlogin = (EditText) findViewById(R.id.etpasswordingreso);
        btnregistraruser= (Button) findViewById(R.id.btnregistroingreso);
        btnregistraruser.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i= new Intent(getApplicationContext(),RegistroUsuarios.class);
                startActivity(i);
            }
        });

       btningresaruser = (Button) findViewById(R.id.btningresoingreso);
       btningresaruser.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String id = userlogin.getText().toString();
               String password = passwordlogin.getText().toString();

               try {
                   Cursor cursor=dbUsuarios.ConsultarUsuPas(userlogin.getText().toString(),passwordlogin.getText().toString());
                   if(id.isEmpty()){
                       userlogin.setError("Digite su Usuario");
                   }
                   if(password.isEmpty()){
                       passwordlogin.setError("Digite su Contraseña");
                   }
                   if(cursor.getCount()>0){
                       Cursor fila=dbUsuarios.TraerNombreUsu(userlogin.getText().toString());
                       if(cursor.moveToFirst()){
                           Intent miIntent=null;
                           switch (view.getId()){
                               case R.id.btningresoingreso:
                                   miIntent=new Intent( getApplicationContext(), MainActivity.class);
                                   miIntent.putExtra("id",userlogin.getText().toString());
                                   break;
                           }
                           if(miIntent!=null){
                               startActivity(miIntent);
                           }
                       }
                   }else{
                       Toast.makeText(getApplicationContext(),"Usuario o Contraseña incorrectos",
                               Toast.LENGTH_LONG).show();
                   }
                   userlogin.setText("");
                   passwordlogin.setText("");
                   userlogin.findFocus();
               }catch (SQLException e){
                   e.printStackTrace();
               }
           }
       });
    }


}