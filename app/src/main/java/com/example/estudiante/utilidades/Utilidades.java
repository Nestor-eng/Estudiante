package com.example.estudiante.utilidades;

public class Utilidades {
    public static final String TABLA_ESTUDIANTE = "Estudiante";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_APELLIDOP = "apellidoP";
    public static final String CAMPO_APELLIDOM = "apellidoM";
    public static final String CAMPO_NCONTROL = "nControl";
    public static final String CAMPO_SEMESTRE = "semestre";
    public static final String CREAR_TABLA_USUARIO="CREATE TABLE "+TABLA_ESTUDIANTE+" ("+CAMPO_ID+" INTEGER,"+CAMPO_NOMBRE+" TEXT,"+CAMPO_APELLIDOP+" TEXT, "+CAMPO_APELLIDOM+" TEXT,"+CAMPO_NCONTROL+" TEXT,"+CAMPO_SEMESTRE+" TEXT)";

}
