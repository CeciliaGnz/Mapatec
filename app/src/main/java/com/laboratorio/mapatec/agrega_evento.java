package com.laboratorio.mapatec;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class agrega_evento extends AppCompatActivity {

    private EditText editTextTitulo;
    private EditText editTextHora;
    private EditText editTextLugar;
    private EditText editTextDescripcion;
    private Button btnAgregar, btnCancelar;
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
        btnAgregar = findViewById(R.id.buttonAgregar);
        btnCancelar = findViewById(R.id.buttonCancelar);

        // Configurar el clic del botón Agregar
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores ingresados por el administrador
                String titulo = editTextTitulo.getText().toString();
                String hora = editTextHora.getText().toString();
                String lugar = editTextLugar.getText().toString();
                String descripcion = editTextDescripcion.getText().toString();

                // Insertar el evento en la base de datos y obtener el ID generado automáticamente
                long newRowId = databaseHelper.insertarEvento(titulo, hora, lugar, descripcion);

                // Obtener el ID del evento generado automáticamente
                String idEvento = "evento" + newRowId;

                // Mostrar el ID del evento en un TextView o en un Toast
                TextView textViewIdEvento = findViewById(R.id.event_id);
                textViewIdEvento.setText("ID: " + idEvento);

                // Mostrar un Toast indicando que el evento se agregó correctamente
                Toast.makeText(agrega_evento.this, "Evento agregado correctamente", Toast.LENGTH_SHORT).show();
                finish();
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