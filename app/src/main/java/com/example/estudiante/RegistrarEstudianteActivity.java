package com.example.estudiante;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.estudiante.utilidades.Utilidades;

public class RegistrarEstudianteActivity extends AppCompatActivity {
    EditText id,nombre,apellidop,apellidom,ncontrol,semestre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_estudiante);
        id = (EditText) findViewById(R.id.Id);
        nombre = (EditText) findViewById(R.id.txtNombre);
        apellidop = (EditText) findViewById(R.id.txtapellidoP);
        apellidom = (EditText) findViewById(R.id.txtapellidoM);
        ncontrol = (EditText) findViewById(R.id.txtnControl);
        semestre = (EditText) findViewById(R.id.txtSemestre);

    }

    private void registrarEstudiante() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_Estudiante",null,1);

        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID,id.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE,nombre.getText().toString());
        values.put(Utilidades.CAMPO_APELLIDOP,apellidop.getText().toString());
        values.put(Utilidades.CAMPO_APELLIDOM,apellidom.getText().toString());
        values.put(Utilidades.CAMPO_NCONTROL,ncontrol.getText().toString());
        values.put(Utilidades.CAMPO_SEMESTRE,semestre.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_ESTUDIANTE, Utilidades.CAMPO_ID,values);
        Toast.makeText(getApplicationContext(),"Registro existoso" + idResultante, Toast.LENGTH_SHORT).show();
        System.out.println("Registro Exitoso");
        db.close();
    }

    public void guarda(View view) {
        registrarEstudiante();
    }
}
