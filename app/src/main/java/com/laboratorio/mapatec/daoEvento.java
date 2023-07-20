package com.laboratorio.mapatec;


import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoEvento {

    SQLiteDatabase cx;
    ArrayList<Evento>lista=null;
    Evento v;
    Context ct;

    String  nombreBD="BDEventos";
    String tabla= "create table if not exists evento(id_evento integer primary key autoincrement, titulo text, fechahora text, lugar text, descripcion text)";

    public daoEvento(Context v) {
        this.ct = v;
        cx = v.openOrCreateDatabase(nombreBD, v.MODE_PRIVATE, null);
    }

    public boolean insertar(Evento v){
        ContentValues contenedor=new ContentValues();
        contenedor.put("titulo",v.getTitulo());
        contenedor.put("fechahora",v.getFechaHora());
        contenedor.put("lugar",v.getLugar());
        contenedor.put("descripcion",v.getDescripcion());

        return (cx.insert("evento",null,contenedor))>0;
    }

    public boolean eliminar (int id_evento){
        return true;
    }

    public boolean editar(Evento v){
        return true;
    }

    public ArrayList<Evento> verTodos(){
        lista.clear();
        Cursor cursor=cx.rawQuery("select * from evento", null);

        if (cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                lista.add(new Evento(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)));
            }while (cursor.moveToNext());

        }
        if(this.lista==null){
            return new ArrayList<Evento>();
        }
        return lista;
    }

    public Evento verUno(int posicion){
        Cursor cursor=cx.rawQuery("select * from evento", null);
        cursor.moveToPosition(posicion);
        v=new Evento(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));
        return v;
    }
}