package com.laboratorio.mapatec;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class reset extends AppCompatActivity {

    Button btn_si, btn_no;
    TextView cedula_view, new_pass;
    String cedula;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_passw);


        cedula_view = findViewById(R.id.cedu_adm);
        new_pass=findViewById(R.id.new_pass);
        btn_si = findViewById(R.id.btn_si);
        btn_no = findViewById(R.id.btn_no);

        // Obtén la cédula del Intent
        Bundle extras = getIntent().getExtras();
            cedula = extras.getString("cedula");
            cedula_view.setText(cedula);

        btn_si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = new_pass.getText().toString();

                // Actualizar la contraseña en la base de datos
                updatePasswordInDatabase(newPassword);

                // Mostrar mensaje de éxito o redirigir a otra actividad
                Toast.makeText(reset.this, "Contraseña restablecida con éxito", Toast.LENGTH_SHORT).show();

                //Dirigiendo a la actividad de administrador
                Intent intent = new Intent(reset.this, Admin_activity.class);
                intent.putExtra("cedula", cedula);
                startActivity(intent);
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Dirigiendo a la actividad de administrador
                Toast.makeText(reset.this, "Cambio cancelado, dirigiendo al menú de administrador", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(reset.this, Admin_activity.class);
                intent.putExtra("cedula", cedula);
                startActivity(intent);
            }
        });
    }

    private void updatePasswordInDatabase(String newPassword) {
        // Obtener una instancia de la base de datos
        DatabaseHelper databaseHelper = new DatabaseHelper(reset.this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        // Definir los valores a actualizar
        ContentValues values = new ContentValues();
        values.put(databaseHelper.getPasswordColumnName(), newPassword);

        // Definir la cláusula WHERE para filtrar por cédula
        String whereClause = databaseHelper.getCedulaColumnName() + " = ?";
        String[] whereArgs = {cedula};

        // Actualizar la contraseña en la base de datos
        db.update(databaseHelper.getTableName(), values, whereClause, whereArgs);

        // Cerrar la conexión con la base de datos
        db.close();
    }

}
