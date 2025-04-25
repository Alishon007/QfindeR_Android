package com.example.qfinder;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.MenuItem;

import com.example.qfinder.controller.Login;
import com.example.qfinder.databinding.ActivityMenuQfinderBinding;

public class MenuQfinder extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuQfinderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializar ViewBinding
        binding = ActivityMenuQfinderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar Toolbar
        setSupportActionBar(binding.appBarMenuQfinder.toolbar);

        // Recibir datos desde Login (si vienen)
        Intent intent = getIntent();
        if (intent != null) {
            String nombreCompleto = intent.getStringExtra("nombre");
            String usuario = intent.getStringExtra("usuario");
            String contacto = intent.getStringExtra("contacto");
            String email = intent.getStringExtra("email");

            // Pasar los datos al PerfilFragment usando un Bundle
            Bundle bundle = new Bundle();
            bundle.putString("nombre", nombreCompleto);
            bundle.putString("usuario", usuario);
            bundle.putString("contacto", contacto);
            bundle.putString("email", email);

            // Configurar el NavController
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_qfinder);

            // Navegar al fragmento de perfil y pasar los datos
            navController.navigate(R.id.nav_perfil, bundle);
        }

        // Configurar destinos principales del Drawer
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_perfil, R.id.nav_notas, R.id.nav_registro_paciente,
                R.id.nav_perfil_paciente, R.id.nav_recordatorio)
                .setOpenableLayout(binding.drawerLayout)
                .build();

        // Configurar la navegación para la Toolbar y el Drawer
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_qfinder);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Manejar clics en los ítems del menú, incluyendo "Cerrar sesión"
        binding.navView.setNavigationItemSelectedListener(menuItem -> {
            boolean handled = false;

            if (menuItem.getItemId() == R.id.nav_logout) {
                // Cerrar sesión: ir a Login y limpiar pila de actividades
                Intent logoutIntent = new Intent(MenuQfinder.this, Login.class);
                logoutIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(logoutIntent);
                finish();
                handled = true;
            } else {
                // Navegar a otro destino normalmente
                handled = NavigationUI.onNavDestinationSelected(menuItem,
                        Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_qfinder));
            }

            // Cerrar el Drawer si se manejó la navegación
            if (handled) {
                binding.drawerLayout.closeDrawers();
            }

            return handled;
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_qfinder);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }
}
