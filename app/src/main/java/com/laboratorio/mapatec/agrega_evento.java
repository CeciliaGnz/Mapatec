package com.laboratorio.mapatec;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class agrega_evento extends AppCompatActivity {

    private EditText editTextTitulo;
    private EditText editTextHora;
    private EditText editTextLugar;
    private EditText editTextDescripcion;
    private DatabaseHelper databaseHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_event);

        databaseHelper = new DatabaseHelper(this);

        // Obtener referencias a los elementos de la interfaz de usuario
        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextHora = findViewById(R.id.editTextHora);
        editTextLugar = findViewById(R.id.editTextLugar);
        editTextDescripcion = findViewById(R.id.editTextDescripcion);
        Button btnAgregar = findViewById(R.id.b);
        Button btnCancelar = findViewById(R.id.btnCancelar);

        // Configurar el clic del botón Agregar
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores ingresados por el administrador
                String titulo = editTextTitulo.getText().toString();
                String hora = editTextHora.getText().toString();
                String lugar = editTextLugar.getText().toString();
                String descripcion = editTextDescripcion.getText().toString();

                // Insertar el evento en la base de datos
                long newRowId = databaseHelper.insertarEvento(titulo, hora, lugar, descripcion);

                if (newRowId != -1) {
                    // La inserción fue exitosa
                    Toast.makeText(agrega_evento.this, "Evento agregado correctamente", Toast.LENGTH_SHORT).show();
                    finish(); // Cerrar la actividad y regresar a la anterior
                } else {
                    // Hubo un error en la inserción
                    Toast.makeText(agrega_evento.this, "Error al agregar el evento", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Configurar el clic del botón Cancelar
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cerrar la actividad y regresar a la anterior
                finish();
            }
        });
    }
}
