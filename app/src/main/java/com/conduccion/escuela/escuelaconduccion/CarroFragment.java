package com.conduccion.escuela.escuelaconduccion;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by francia on 10/10/16.
 */

public class CarroFragment extends PreferenceFragment {

    LinearLayout linearLayout;

    @Override
    public void onCreate(Bundle saveInstancesState){
        super.onCreate(saveInstancesState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.carro_fragment, container, false);
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    public void onclickDelete(View view){
        System.out.println("view.getTag() = " + view.getTag());
    }


    public void onclickUpdate(View view){
        System.out.println("view.getTag() = " + view.getTag());
    }


}
