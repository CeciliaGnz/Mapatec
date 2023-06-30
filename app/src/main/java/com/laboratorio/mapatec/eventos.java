package com.laboratorio.mapatec;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class eventos extends AppCompatActivity {
    inicioFragment InicioFragment= new inicioFragment();
    androidx.fragment.app.FragmentManager fragmentManager = getSupportFragmentManager();// Obtener el FragmentManager
    Button btn_back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);

        btn_back=(Button) findViewById(R.id.button_back);
    }

    public void Back(){

        // Reemplazar el contenido actual del contenedor del fragmento con el nuevo fragmento
        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, InicioFragment)
                .commit();
    }
}