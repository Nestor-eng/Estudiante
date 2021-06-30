package com.example.estudiante;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.estudiante.entidades.Estudiante;
import com.example.estudiante.utilidades.Utilidades;

import java.util.ArrayList;

public class ConsultaComboActivity extends AppCompatActivity {
    Spinner comboPersonas;
    TextView identificador,nombre,apellidop,apellidom,ncontrol,semestre;
    ArrayList<String>ListaPersonas;
    ArrayList<Estudiante>personasLists;
    ConexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_combo);
        conn = new ConexionSQLiteHelper(this,"bd_Estudiante",null,1);
        comboPersonas = (Spinner)findViewById(R.id.comboPersonas);
        identificador = (TextView)findViewById(R.id.txtId);
        nombre = (TextView)findViewById(R.id.txtNombre);
        apellidop = (TextView)findViewById(R.id.txtApellidoP);
        apellidom = (TextView)findViewById(R.id.txtapellidoM);
        ncontrol = (TextView)findViewById(R.id.txtnControl);
        semestre = (TextView)findViewById(R.id.txtSemestre);
        consultarListaPersonas();
        ArrayAdapter<CharSequence>adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item,ListaPersonas);
        comboPersonas.setAdapter(adaptador);
        comboPersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    identificador.setText(personasLists.get(position-1).getId().toString());
                    nombre.setText(personasLists.get(position-1).getNombre());
                    apellidop.setText(personasLists.get(position-1).getApellidoP());
                    apellidom.setText(personasLists.get(position-1).getApellidoM());
                    ncontrol.setText(personasLists.get(position-1).getnControl());
                    semestre.setText(personasLists.get(position-1).getSemestre());
                }else
                {
                    nombre.setText("");
                    apellidop.setText("");
                    apellidom.setText("");
                    ncontrol.setText("");
                    semestre.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void consultarListaPersonas() {
        SQLiteDatabase db = conn.getWritableDatabase();
        Estudiante persona = null;
        personasLists = new ArrayList<Estudiante>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_ESTUDIANTE,null);
        while (cursor.moveToNext()){
            persona = new Estudiante();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setApellidoP(cursor.getString(2));
            persona.setApellidoM(cursor.getString(3));
            persona.setnControl(cursor.getString(4));
            persona.setSemestre(cursor.getString(5));
            Log.i("Id",persona.getId().toString());
            Log.i("Nombre",persona.getNombre());
            Log.i("Apellido Paterno",persona.getApellidoP());
            Log.i("ApellidoM",persona.getApellidoM());
            Log.i("Numero de control",persona.getnControl());
            Log.i("Semestre",persona.getSemestre());
            personasLists.add(persona);

        }
        obtenerLista();

    }
    public void obtenerLista(){
        ListaPersonas = new ArrayList<String>();
        ListaPersonas.add("Seleccione");
        for (int i = 0; i<personasLists.size();i++){
            ListaPersonas.add(personasLists.get(i).getId()+" - "+personasLists.get(i).getNombre());
        }
    }
}
