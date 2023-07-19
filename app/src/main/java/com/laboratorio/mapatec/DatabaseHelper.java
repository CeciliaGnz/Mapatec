package com.laboratorio.mapatec;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "admin_databas.db";
    private static final int DATABASE_VERSION = 1;

    //TABLA DE ADMINISTRADORES
    private static final String TABLE_NAME = "administrators";
    private static final String COLUMN_CEDULA = "cedula";
    private static final String COLUMN_PASSWORD = "password";


    //TABLE DE EVENTOS
    private static final String TABLE_EVENTS = "events";
    private static final String COLUMN_EVENT_ID = "event_id";
    private static final String COLUMN_EVENT_TITLE = "titulo";
    private static final String COLUMN_HOUR = "hora";
    private static final String COLUMN_PLACE = "lugar";
    private static final String COLUMN_DESCRIPTION = "descripcion";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    // Creamos la tabla ADM
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_CEDULA + " TEXT PRIMARY KEY, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createTableQuery);

        //Creamos la tabla de eventos
        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
                + COLUMN_EVENT_ID + " TEXT PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_EVENT_TITLE + " TEXT,"
                + COLUMN_HOUR + " TEXT,"
                + COLUMN_PLACE + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT" + ")";
        db.execSQL(CREATE_EVENTS_TABLE);

        // Insertarmos los datos de administradores

        //Administrador 1
        ContentValues values = new ContentValues();
        values.put(COLUMN_CEDULA, "8-990-1469");
        values.put(COLUMN_PASSWORD, "admin123");
        db.insert(TABLE_NAME, null, values);

        // Administrador 2
        values.put(COLUMN_CEDULA, "8-942-347");
        values.put(COLUMN_PASSWORD, "seguro123");
        db.insert(TABLE_NAME, null, values);

        // Administrador 3
        values.put(COLUMN_CEDULA, "1-234-5678");
        values.put(COLUMN_PASSWORD, "admin321");
        db.insert(TABLE_NAME, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    //Get utilizados en perfilFragment porque son privados
    public String getPasswordColumnName() {
        return COLUMN_PASSWORD;
    }

    public String getCedulaColumnName() {
        return COLUMN_CEDULA;
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    //TABLE EVENTOS
    public long insertarEvento(String titulo, String hora, String lugar, String descripcion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Insertar los valores en la tabla de eventos
        values.put(COLUMN_EVENT_TITLE, titulo);
        values.put(COLUMN_HOUR, hora);
        values.put(COLUMN_PLACE, lugar);
        values.put(COLUMN_DESCRIPTION, descripcion);

        // Insertar el nuevo evento y obtener el ID de la nueva fila
        long newRowId = db.insert(TABLE_EVENTS, null, values);
        db.close();

        // Devolver el ID de la nueva fila
        return newRowId;
    }

    public int getEventosCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT COUNT(*) FROM " + TABLE_EVENTS;
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToFirst();
        int eventosCount = cursor.getInt(0);
        cursor.close();
        db.close();
        return eventosCount;
    }

}
