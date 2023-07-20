package com.laboratorio.mapatec;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class eventos extends AppCompatActivity {
    Button btn_back;
    private ListView listViewEventos;
    private daoEvento dao;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);

        btn_back=(Button) findViewById(R.id.button_back);

        dao = new daoEvento(this); // Instanciar el objeto daoEvento para interactuar con la base de datos

        // Obtener la lista de eventos desde la base de datos usando el método verTodos() de daoEvento
        ArrayList<Evento> listaEventos = dao.verTodos();

        // Inicializar el ListView
        listViewEventos = findViewById(R.id.listViewEventos);

        // Crear el adaptador personalizado para el ListView
        EventoListAdapter adapter = new EventoListAdapter(this, listaEventos);

        // Asignar el adaptador al ListView
        listViewEventos.setAdapter(adapter);
    }


    public void Back(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);}
}