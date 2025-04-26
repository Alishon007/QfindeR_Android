package com.example.qfinder.controller;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.qfinder.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retraso de 3 segundos antes de cambiar a LoginFragment
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Agregar o reemplazar el fragmento de login
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(android.R.id.content, new LoginFragment()); // Reemplaza el fragmento en el contenedor raíz
                transaction.commit(); // Commit de la transacción
            }
        }, 2000);
    }
}
