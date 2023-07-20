package com.laboratorio.mapatec;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class eventos extends AppCompatActivity {
    Button btn_back;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);

        btn_back=(Button) findViewById(R.id.button_back);



    }

    public void Back(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);}
}