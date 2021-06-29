package com.example.estudiante;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_Estudiantes",null,1);
        /*Button btn = (Button) findViewById(R.id.btnRegistrar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),RegistrarEstudianteActivity.class);
                startActivity(intent);
            }
        });*/
    }

    public void abrir(View view) {
        Intent miIntent = null;
        switch (view.getId()){
            case R.id.btnRegistrar:
                miIntent = new Intent(MainActivity.this,RegistrarEstudianteActivity.class);
                break;
        }
        if (miIntent != null){
            startActivity(miIntent);
        }
    }
}