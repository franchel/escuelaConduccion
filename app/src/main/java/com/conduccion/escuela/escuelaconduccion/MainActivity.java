package com.conduccion.escuela.escuelaconduccion;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private static final int ENTER_DATA_REQUEST_CODE=1;
    private CarroCursorAdapter customAdapter;
    private CarroDatabaseHelper databaseHelper;
    private ListView listView;
    private static final String TAG = MainActivity.class.getSimpleName();


    public void eliminarCarro(View view){
        int resul;
        resul = databaseHelper.deleteROW(view.getTag().toString());
        startActivity(new Intent(getBaseContext(), MainActivity.class));

        if(resul == 1){
            Toast.makeText(this, "Se ha eliminado el carro",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "No Se ha eliminado el carro",Toast.LENGTH_LONG).show();
        }
    }


    public void modificarCarro(View view){
        TextView lbl_placa, lbl_color;
        lbl_placa = (TextView) findViewById(R.id.cell_carro);
        lbl_color = (TextView) findViewById(R.id.cell_color);


        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra("id",view.getTag().toString());
        intent.putExtra("placal", lbl_placa.getText());
        intent.putExtra("colorl", lbl_color.getText());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new CarroDatabaseHelper(this);

        listView = (ListView) findViewById(R.id.list_data);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                customAdapter = new CarroCursorAdapter(MainActivity.this, databaseHelper.getAllData());
                listView.setAdapter(customAdapter);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id=item.getItemId();
        if(id == R.id.action_add_carro){
            startActivityForResult(new Intent(this,CrearCarro.class), ENTER_DATA_REQUEST_CODE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == ENTER_DATA_REQUEST_CODE && resultCode== RESULT_OK){
            databaseHelper.insetData(data.getExtras().getString("tag_placa"), data.getExtras().getString("tag_color"));
            customAdapter = new CarroCursorAdapter(this, null);
            customAdapter.changeCursor(databaseHelper.getAllData());
            startActivity(new Intent(getBaseContext(), MainActivity.class));
        }
    }

}
