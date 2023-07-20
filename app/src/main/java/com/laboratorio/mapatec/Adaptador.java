package com.laboratorio.mapatec;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    ArrayList<Evento> lista;
    daoEvento dao;
    Evento v;
    Activity a;

    public Adaptador(Activity a, ArrayList<Evento> lista, daoEvento dao){
        this.lista=lista;
        this.a=a;
        this.dao=dao;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Evento getItem(int i) {
        v=lista.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        v=lista.get(i);
        return v.getId();
    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View vi=view;
        if(vi==null){
            LayoutInflater li= (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi=li.inflate(R.layout.item,null );
        }

        v=lista.get(posicion);

        TextView id=(TextView) vi.findViewById(R.id.view_id);
        TextView title=(TextView) vi.findViewById(R.id.view_title);
        Button edit=(Button)vi.findViewById(R.id.edit);
        Button delete=(Button)vi.findViewById(R.id.delete);

        id.setText(v.getId());
        title.setText(v.getTitulo());
        edit.setTag(posicion);
        delete.setTag(posicion);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //agregarevetno.xml
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialogo para confimar si/no eliminar
            }
        });

        return vi;
    }
}