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

        // Obtener la cantidad de eventos existentes en la tabla de eventos
        String countQuery = "SELECT COUNT(*) FROM eventos";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToFirst();
        int eventosCount = cursor.getInt(0);
        cursor.close();

// Generar el ID del evento
        String idEvento = "evento_" + (eventosCount + 1);

// Obtener los valores ingresados por el administrador desde los EditText
        String titulo = editTextTitulo.getText().toString();
        String hora = editTextHora.getText().toString();
        String lugar = editTextLugar.getText().toString();
        String descripcion = editTextDescripcion.getText().toString();

// Guardar el evento en la base de datos
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_evento", idEvento);
        values.put("titulo", titulo);
        values.put("hora", hora);
        values.put("lugar", lugar);
        values.put("descripcion", descripcion);
        db.insert("eventos", null, values);
        db.close();

// Volver a la actividad Admin_activity
        Intent resultIntent = new Intent();
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
