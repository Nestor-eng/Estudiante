package com.example.estudiante;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.estudiante.entidades.Estudiante;
import com.example.estudiante.utilidades.Utilidades;

import java.util.ArrayList;

public class ConsultarListViewActivity extends AppCompatActivity {
    ListView listViewPersona;
    ArrayList<String>ListaPersonas;
    ArrayList<Estudiante>personasLists;
    ConexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_listview);
        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_Estudiante",null,1);
        listViewPersona = (ListView)findViewById(R.id.listViewPersonas);
        consultarListaPersonas();
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ListaPersonas);
        listViewPersona.setAdapter(adaptador);
        listViewPersona.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             String informacion = "ID: "+ personasLists.get(position).getId()+"\n";
                    informacion = "Nombre: "+ personasLists.get(position).getNombre()+"\n";
                    informacion = "Apellido Paterno: "+ personasLists.get(position).getApellidoP()+"\n";
                    informacion = "Apellido Materno: "+ personasLists.get(position).getApellidoM()+"\n";
                    informacion = "Numero de Control: "+ personasLists.get(position).getnControl()+"\n";
                    informacion = "Semestre: "+ personasLists.get(position).getSemestre()+"\n";
                Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_SHORT).show();
                Estudiante estudiante = personasLists.get(position);
                Intent intent = new Intent(ConsultarListViewActivity.this,DetalleEstudianteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("estudiante",estudiante);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    public void consultarListaPersonas(){
        SQLiteDatabase db = conn.getReadableDatabase();
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
