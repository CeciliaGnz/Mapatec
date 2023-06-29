package com.laboratorio.mapatec;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class eventos extends AppCompatActivity {

    inicioFragment InicioFragment= new inicioFragment();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);
    }

    public void Back(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, InicioFragment).commit();
    }
}