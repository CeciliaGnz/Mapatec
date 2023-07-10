package com.laboratorio.mapatec;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
                String newPassword = newPasswordEditText.getText().toString();

                // Actualizar la contraseña en la base de datos
                updatePasswordInDatabase(newPassword);

                // Mostrar mensaje de éxito o redirigir a otra actividad
                Toast.makeText(ResetPasswordActivity.this, "Contraseña restablecida con éxito", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updatePasswordInDatabase(String newPassword) {
        // Obtener una instancia de la base de datos
        DatabaseHelper databaseHelper = new DatabaseHelper(ResetPasswordActivity.this);
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
}
