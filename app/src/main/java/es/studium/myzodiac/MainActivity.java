package es.studium.myzodiac;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

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
            int mes = calendario.getMonth();
            int anio = calendario.getYear();

            Calendar hoy = Calendar.getInstance();
            Calendar elegida = (Calendar) hoy.clone();
            elegida.set(anio, mes, dia);

            Log.d("MyZodiac", "elegida=" + elegida.getTimeInMillis() + " hoy=" + hoy.getTimeInMillis());
            if (elegida.getTimeInMillis() > hoy.getTimeInMillis()) {
                //Log.d("MyZodiac", "Fecha futura: mostrando aviso");

                // Toast.makeText(this, R.string.errFechaFutura, Toast.LENGTH_LONG).show();
                Snackbar.make(v, R.string.errFechaFutura, Snackbar.LENGTH_LONG).show();
                return;
            }

            Intent intent = new Intent(MainActivity.this, ZodiacoActivity.class);
            intent.putExtra("dia", dia);
            intent.putExtra("mes", mes + 1);
            intent.putExtra("anio", anio);
            startActivity(intent);
        });
    }


}