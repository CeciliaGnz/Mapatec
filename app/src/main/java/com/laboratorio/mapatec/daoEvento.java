package com.laboratorio.mapatec;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoEvento {

    SQLiteDatabase cx;
    ArrayList<Evento>lista;
    Evento v;
    Context ct;

    String  nombreBD="BDEventos";
    String tabla= "create table if not exist evento(id_evento integer primary key autoincrement, titulo text, fecha y hora text, lugar text, descripcion text)";

    public daoEvento(Context v) {
        this.ct = v;
        cx = v.openOrCreateDatabase(nombreBD, Context.MODE_WORLD_WRITEABLE, null);
    }

    public boolean insertar(Evento v){
        return true;
    }

    public boolean eliminar (int id_evento){
        return true;
    }

    public boolean editar(Evento v){
        return true;
    }

    public ArrayList<Evento> verTodos(){
        return lista;
    }

    public Evento verUno(int id_evento){
        return v;
    }
}
