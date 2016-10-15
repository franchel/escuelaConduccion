package com.conduccion.escuela.escuelaconduccion;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.conduccion.escuela.escuelaconduccion.R;

/**
 * Created by francia on 10/10/16.
 */

public class CarroCursorAdapter extends CursorAdapter {


    public CarroCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        //se crea la vista por primera vez
        //se usa UI FRagment para iniciar la vista

        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate(R.layout.carro_cell, parent, false);

        return retView;


    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView placa = (TextView) view.findViewById(R.id.cell_carro);
        placa.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));

        TextView color = (TextView) view.findViewById(R.id.cell_color);
        color.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));

        TextView delete_id = (TextView) view.findViewById(R.id.cell_boton_eliminar);
        delete_id.setTag(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))));


        TextView update_id = (TextView) view.findViewById(R.id.cell_boton_modificar);
        update_id.setTag(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))));


    }
}
