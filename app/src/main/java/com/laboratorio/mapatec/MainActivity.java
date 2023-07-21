package com.laboratorio.mapatec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    inicioFragment InicioFragment= new inicioFragment();
    buscarFragment BuscarFragment= new buscarFragment();
    MapaFragment MapaFragment= new MapaFragment();
    perfilFragment PerfilFragment= new perfilFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, InicioFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId= item.getItemId();

                    if(itemId==R.id.inicio) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, InicioFragment).commit();
                        return true;
                    }
                    /*else if(itemId== R.id.buscar) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, BuscarFragment).commit();
                        return true;
                    } //NO LO USAMOS*/
                    else if(itemId==R.id.mapa) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, MapaFragment).commit();
                        return true;
                    }//QUITar comentario
                    else if(itemId==R.id.perfil) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, PerfilFragment).commit();
                        return true;
                    }

                return false;
            }
        });

    }
}