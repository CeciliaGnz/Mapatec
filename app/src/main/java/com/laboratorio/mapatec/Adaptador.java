package com.laboratorio.mapatec;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    ArrayList<Evento> lista;
    daoEvento dao;
    Evento v;
    Activity a;
    int id=0;

    public Adaptador(Activity a, ArrayList<Evento> lista, daoEvento dao){
        this.lista=lista;
        this.a=a;
        this.dao=dao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

        // Convert the ID to string and set it as text for the TextView
        id.setText(String.valueOf(v.getId()));

        title.setText(v.getTitulo());
        edit.setTag(posicion);
        delete.setTag(posicion);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //agregarevetno.xml
                int pos=Integer.parseInt(view.getTag().toString());
                Dialog dialogo=new Dialog(a);
                dialogo.setTitle("Editar registro");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.agregar_event);
                dialogo.show();
                // Obtener referencias a los elementos de la interfaz de usuario
                final EditText Titulo =(EditText)dialogo.findViewById(R.id.editTextTitulo);
                final EditText FechaHora = (EditText)dialogo.findViewById(R.id.editTextHora);
                final EditText Lugar = (EditText)dialogo.findViewById(R.id.editTextLugar);
                final EditText Descripcion = (EditText)dialogo.findViewById(R.id.editTextDescripcion);
                final Button btnAgregar = (Button)dialogo.findViewById(R.id.buttonAgregar);
                final Button btnCancelar = (Button)dialogo.findViewById(R.id.buttonCancelar);

                v=lista.get(pos);
                setId(v.getId());
                Titulo.setText(v.getTitulo());
                FechaHora.setText(v.getFechaHora());
                Lugar.setText(v.getLugar());
                Descripcion.setText(v.getDescripcion());

                btnAgregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            v=new Evento(getId(),Titulo.getText().toString(),
                                    FechaHora.getText().toString(),
                                    Lugar.getText().toString(),
                                    Descripcion.getText().toString());
                            dao.editar(v);
                            lista=dao.verTodos();
                            notifyDataSetChanged();
                            dialogo.dismiss();
                        }catch (Exception e){
                            Toast.makeText(a,"ERROR",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogo.dismiss();
                    }
                });
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