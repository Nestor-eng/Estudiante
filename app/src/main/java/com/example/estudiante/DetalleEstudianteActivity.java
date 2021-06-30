package com.example.estudiante;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.estudiante.entidades.Estudiante;

public class DetalleEstudianteActivity extends AppCompatActivity {
    TextView id,nombre,apellidop,apellidom,ncontrol,semestre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_estudiante);

        id = (TextView)findViewById(R.id.txtId);
        nombre = (TextView)findViewById(R.id.txtNombre);
        apellidop = (TextView)findViewById(R.id.txtApellidoP);
        apellidom = (TextView)findViewById(R.id.txtApellidoM);
        ncontrol = (TextView)findViewById(R.id.txtnControl);
        semestre = (TextView)findViewById(R.id.txtSemestre);
        Bundle objetoenviado = getIntent().getExtras();
        Estudiante estudiante = null;

        if(objetoenviado != null){
            estudiante = (Estudiante) objetoenviado.getSerializable("estudiante");
            id.setText(estudiante.getId().toString());
            nombre.setText(estudiante.getNombre());
            apellidop.setText(estudiante.getApellidoP());
            apellidom.setText(estudiante.getApellidoM());
            ncontrol.setText(estudiante.getnControl());
            semestre.setText(estudiante.getSemestre());
        }
    }
}
