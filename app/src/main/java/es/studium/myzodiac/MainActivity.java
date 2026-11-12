package es.studium.myzodiac;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button btnAceptar;
    DatePicker calendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        calendario = findViewById(R.id.datePickerCalendario);
        btnAceptar = findViewById(R.id.btnAceptar);

        btnAceptar.setOnClickListener(v -> {
            int dia = calendario.getDayOfMonth();
            int mes = calendario.getMonth() + 1;
            int year = calendario.getYear();

            String fechaSeleccionada = String.format(Locale.getDefault(), "%02d/%02d/%d", dia, mes, year);
            Intent intent = new Intent(MainActivity.this, ZodiacoActivity.class);
            intent.putExtra("fecha", fechaSeleccionada);
            startActivity(intent);

        });
    }


}