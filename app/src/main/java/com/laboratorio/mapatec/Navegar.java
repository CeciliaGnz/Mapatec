package com.laboratorio.mapatec;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Navegar extends AppCompatActivity {

    Button btn_back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navegarr);

        btn_back=(Button) findViewById(R.id.button_back);
    }

    public void Back(){
        inicioFragment InicioFragment= new inicioFragment();

        // Obtener el FragmentManager
        androidx.fragment.app.FragmentManager fragmentManager = getSupportFragmentManager();

        // Reemplazar el contenido actual del contenedor del fragmento con el nuevo fragmento
        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, InicioFragment)
                .commit();
    }
}
