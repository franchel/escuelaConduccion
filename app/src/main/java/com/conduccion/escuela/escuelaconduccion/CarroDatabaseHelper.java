package com.conduccion.escuela.escuelaconduccion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by francia on 9/10/16.
 */

public class CarroDatabaseHelper {

    private static final String TAG = CarroDatabaseHelper.class.getSimpleName();


    //Configuracion de la base de datos
    private static final int DATABASE_VERSION =2;
    private static final String DATABASE_PLACA ="carro_database.db";

    //Configuracion de la tabla
    private static final String TABLE_NAME ="carro";
    private static final String COLUMN_ID ="_id";
    private static final String COLUMN_PLACA ="placa";
    private static final String COLUMN_COLOR ="color";

    private DatabaseOpenHelper opneHelper;
    private SQLiteDatabase datebase;

    //esto es una clase contenedora
    //DatabaseOpneHelper realiza CRUD base de datos

    public CarroDatabaseHelper(Context aContext){
        opneHelper = new DatabaseOpenHelper(aContext);
        datebase= opneHelper.getWritableDatabase();
    }

    public void insetData(String placa, String color){
        //contentValues ecita errores en sql

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PLACA, placa);
        contentValues.put(COLUMN_COLOR, color);

        datebase.insert(TABLE_NAME,null,contentValues);
    }

    public int deleteROW(String id){
        return datebase.delete(TABLE_NAME,COLUMN_ID+"="+id,null);
    }

    public int updateRow(String id, String placa, String color){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PLACA, placa);
        contentValues.put(COLUMN_COLOR, color);

        return datebase.update(TABLE_NAME, contentValues, COLUMN_ID+"="+id, null);
    }

    public Cursor getAllData(){
        String buildSQL ="SELECT * FROM "+TABLE_NAME;
        Log.d(TAG,"Listado de carros: "+buildSQL);

        return datebase.rawQuery(buildSQL,null);
    }



    private class DatabaseOpenHelper extends SQLiteOpenHelper{
        public DatabaseOpenHelper(Context aContext){
            super(aContext, DATABASE_PLACA, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sQLiteDatabase){
        //crea la tabla
            String buildSQL ="CREATE TABLE "+TABLE_NAME+"( "+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    COLUMN_PLACA+" TEXT NOT NULL, "+COLUMN_COLOR+" TEXT NOT NULL);";
            System.out.println(buildSQL);
            Log.d(TAG, "nuevo carro "+buildSQL);

            sQLiteDatabase.execSQL(buildSQL);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int oldVersion, int newVersion){
            // aqui se actualiza la tabla
            String buildSQL = "DROP TABLE IF EXISTS "+TABLE_NAME;
            Log.d(TAG, "Eliminar tabla "+buildSQL);
            sQLiteDatabase.execSQL(buildSQL);
            onCreate(sQLiteDatabase);

        }
    }


}
