package com.laboratorio.mapatec;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class agrega_evento extends AppCompatActivity {

    private EditText editTextHour;
    private EditText editTextPlace;
    private EditText editTextDescription;
    private DatabaseHelper databaseHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_event);

    }
}
