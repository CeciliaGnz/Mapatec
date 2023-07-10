package com.laboratorio.mapatec;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_activity extends AppCompatActivity {

    TextView cedula_view;
    String cedula;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seccion_admin);

        cedula_view=findViewById(R.id.cedu_adm);

        Bundle extras = getIntent().getExtras();
        cedula = extras.getString("cedula");
        cedula_view.setText(cedula);

    }
}
