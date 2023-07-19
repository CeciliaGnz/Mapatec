package com.laboratorio.mapatec;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Admin_activity extends AppCompatActivity {

    TextView cedula_view;
    String cedula;

    private List<Evento> eventosList = new ArrayList<>();
    private RecyclerView recyclerView;
    private EventosAdapter eventosAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seccion_admin);

        cedula_view=findViewById(R.id.ced_adm);
        //RecyclerView recyclerView = findViewById(R.id.recyclerView);

        Bundle extras = getIntent().getExtras();
        cedula = extras.getString("cedula");
        cedula_view.setText("ID Adminstrador: "+ cedula);

        Button btnAgregarEvento = findViewById(R.id.buttonAgregar);
        btnAgregarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad para agregar un nuevo evento
                Intent intent = new Intent(Admin_activity.this, agrega_evento.class);
                startActivity(intent);
            }
        });
    }


}
