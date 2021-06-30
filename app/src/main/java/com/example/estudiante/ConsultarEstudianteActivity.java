package com.example.estudiante;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.estudiante.utilidades.Utilidades;

public class ConsultarEstudianteActivity extends AppCompatActivity {
    EditText id,nombre,apellidop,apellidom,ncontrol,semestre;
    ConexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultar_estudiante);
        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_Estudiante",null,1);
        id = (EditText)findViewById(R.id.Id);
        nombre = (EditText)findViewById(R.id.txtNombre);
        apellidop = (EditText)findViewById(R.id.txtApellidoP);
        apellidom = (EditText)findViewById(R.id.txtapellidoM);
        ncontrol = (EditText)findViewById(R.id.txtnControl);
        semestre = (EditText)findViewById(R.id.txtSemestre);
        Button btn = (Button)findViewById(R.id.btnConsultar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultar();
            }
        });
        Button btn2 = (Button)findViewById(R.id.btnActualizar);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
            }
        });
        Button btn3 = (Button)findViewById(R.id.btnEliminar);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();
            }
        });
    }

    private void eliminar() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {id.getText().toString()};
        db.delete(Utilidades.TABLA_ESTUDIANTE,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Eliminado correctamente",Toast.LENGTH_SHORT).show();
        id.setText("");
        limpiar();
        db.close();

    }

    private void actualizar() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {id.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,nombre.getText().toString());
        values.put(Utilidades.CAMPO_APELLIDOP,apellidop.getText().toString());
        values.put(Utilidades.CAMPO_APELLIDOM,apellidom.getText().toString());
        values.put(Utilidades.CAMPO_NCONTROL,ncontrol.getText().toString());
        values.put(Utilidades.CAMPO_SEMESTRE,semestre.getText().toString());
        db.update(Utilidades.TABLA_ESTUDIANTE,values,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Actualizado correctamente",Toast.LENGTH_SHORT).show();
        limpiar();
        db.close();
    }

    private void consultar() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {id.getText().toString()};
        String[] campos = {Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_APELLIDOP,Utilidades.CAMPO_APELLIDOM,Utilidades.CAMPO_NCONTROL,Utilidades.CAMPO_SEMESTRE};
       
        try {
            Cursor cursor = db.query(Utilidades.TABLA_ESTUDIANTE,campos,Utilidades.CAMPO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            nombre.setText(cursor.getString(0));
            apellidop.setText(cursor.getString(1));
            apellidom.setText(cursor.getString(2));
            ncontrol.setText(cursor.getString(3));
            semestre.setText(cursor.getString(4));
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El Id de estudiante no existe ",Toast.LENGTH_SHORT).show();
            limpiar();
        }
        
    }

    private void limpiar() {
        nombre.setText("");
        apellidop.setText("");
        apellidom.setText("");
        ncontrol.setText("");
        semestre.setText("");
    }
}
