package com.laboratorio.mapatec;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Admin_activity extends AppCompatActivity {

    TextView cedula_view;
    String cedula;
    daoEvento dao;
    Adaptador adapter;
    ArrayList<Evento> lista;
    Evento ev;
    private DatabaseHelper databaseHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seccion_admin);

        dao=new daoEvento(this);
        lista=dao.verTodos();
        adapter=new Adaptador(this,lista,dao);
        ListView list=(ListView)findViewById(R.id.lista);
        list.setAdapter(adapter);


        databaseHelper = new DatabaseHelper(this);

        cedula_view=findViewById(R.id.ced_adm);


        Bundle extras = getIntent().getExtras();
        cedula = extras.getString("cedula");
        cedula_view.setText("ID Adminstrador: "+ cedula);

        Button btnAgregarEvento = findViewById(R.id.buttonAgregar);
        btnAgregarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad para agregar un nuevo evento
                /*Intent intent = new Intent(Admin_activity.this, agrega_evento.class);
                startActivity(intent);*/

                Dialog dialogo=new Dialog(Admin_activity.this);
                dialogo.setTitle("Nuevo registro");
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
                btnAgregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            ev=new Evento(Titulo.getText().toString(),
                                    FechaHora.getText().toString(),
                                    Lugar.getText().toString(),
                                    Descripcion.getText().toString());
                            dao.insertar(ev);
                            adapter.notifyDataSetChanged();
                            dialogo.dismiss();
                        }catch (Exception e){
                            Toast.makeText(getApplication(),"ERROR",Toast.LENGTH_SHORT).show();
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


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //ver vista previa de regristro vista.xml
            }
        });


    }

}
