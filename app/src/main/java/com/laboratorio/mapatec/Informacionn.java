package com.laboratorio.mapatec;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Informacionn extends AppCompatActivity {
    Button btn_back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion);

        btn_back=(Button) findViewById(R.id.button_back);
    }
}
