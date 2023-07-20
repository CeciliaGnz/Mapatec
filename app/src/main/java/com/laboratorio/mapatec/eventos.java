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
    private ArrayList<Evento> lista;
    private ListView listViewEventos;
    private Adaptador eventosAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);

        btn_back=(Button) findViewById(R.id.button_back);

        // Obtener la lista de eventos desde la base de datos o cualquier otra fuente de datos
        // En este ejemplo, suponemos que ya tienes una lista de eventos (listaEventos)

        // Inicializar el ListView y el adaptador
        listViewEventos = findViewById(R.id.listViewEventos);
        eventosAdapter = new Adaptador(this, lista);

        // Asignar el adaptador al ListView
        listViewEventos.setAdapter(eventosAdapter);
    }

    public void Back(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);}
}