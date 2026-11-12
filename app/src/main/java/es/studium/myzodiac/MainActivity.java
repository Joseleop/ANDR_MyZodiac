package es.studium.myzodiac;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    Button btnAceptar;
    CalendarView calendario;
    String fechaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        calendario = findViewById(R.id.calendarView);
        btnAceptar = findViewById(R.id.btnAceptar);

        final Calendar calendar = Calendar.getInstance();
        fechaSeleccionada = String.format(Locale.getDefault(), "%02d/%02d/%d",
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.YEAR));

        calendario.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            fechaSeleccionada = String.format(Locale.getDefault(), "%02d/%02d/%d", dayOfMonth, month + 1, year);
        });

        btnAceptar.setOnClickListener(v -> {
            if (fechaSeleccionada.isEmpty()) {
                Toast.makeText(MainActivity.this, "Por favor, selecciona una fecha", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(MainActivity.this, "Fecha: " + fechaSeleccionada, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ZodiacoActivity.class);
                intent.putExtra("fecha",fechaSeleccionada);
                startActivity(intent);
            }
        });
    }


}