package com.conduccion.escuela.escuelaconduccion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by francia on 11/10/16.
 */

public class CrearCarro extends AppCompatActivity {

    EditText txtplaca, txtcolor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        txtplaca = (EditText) findViewById(R.id.txt_placa);
        txtcolor = (EditText) findViewById(R.id.txt_color);

        }


    public void crearCarro(View view){

        String tmpplaca = txtplaca.getText().toString();
        String tmpcolor = txtcolor.getText().toString();

        if(tmpplaca.length() != 0 && tmpcolor.length() !=0){
            Intent newIntent = new Intent(getBaseContext(), MainActivity.class);
            newIntent.putExtra("tag_placa",tmpplaca);
            newIntent.putExtra("tag_color",tmpcolor);

            this.setResult(RESULT_OK, newIntent);
            finish();

            Toast.makeText(this, "Se ha guardado el carro", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Tiene campos vacios", Toast.LENGTH_LONG).show();
        }

    }
}
