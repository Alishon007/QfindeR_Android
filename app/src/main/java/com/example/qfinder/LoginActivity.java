package com.example.qfinder;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.qfinder.controller.LoginFragment;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cargar el fragmento de login en esta actividad
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new LoginFragment())
                .commit();
    }
}
