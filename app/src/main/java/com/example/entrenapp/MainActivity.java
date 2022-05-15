package com.example.entrenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tviduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tviduser=findViewById(R.id.tvIdUsermain);
        String id = getIntent().getStringExtra("id");
        tviduser.setText(id);
    }
    public void onClick(View view){
        Intent miIntent=null;
        switch(view.getId()){
            case R.id.btnexamed:
                miIntent=new Intent(this, RegistroExamenmedico.class);
                miIntent.putExtra("id",tviduser.getText().toString());
                break;
            case R.id.btnexafi:
                miIntent=new Intent(this, RegistroExamenmedico.class);
                miIntent.putExtra("id",tviduser.getText().toString());
                break;
            case R.id.btnrutina:
                miIntent=new Intent(this,RegistroRutina.class);
                miIntent.putExtra("id",tviduser.getText().toString());
                break;
            case R.id.btndieta:
                miIntent=new Intent(this, RegistroDietas.class);
                miIntent.putExtra("id",tviduser.getText().toString());
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }
    }
}