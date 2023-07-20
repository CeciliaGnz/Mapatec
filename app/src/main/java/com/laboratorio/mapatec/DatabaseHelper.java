package com.laboratorio.mapatec;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "admin_databas.db";
    private static final int DATABASE_VERSION = 1;

    //TABLA DE ADMINISTRADORES
    private static final String TABLE_NAME = "administrators";
    private static final String COLUMN_CEDULA = "cedula";
    private static final String COLUMN_PASSWORD = "password";

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

}
