package es.studium.myzodiac;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class ZodiacoActivity extends AppCompatActivity {
    Button btnVolver;
    TextView tvEdad;
    TextView tvSigno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zodiaco);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnVolver = findViewById(R.id.btnVolver);
        tvEdad = findViewById(R.id.tvEdad);
        tvSigno = findViewById(R.id.tvSigno);


        String fecha = getIntent().getStringExtra("fecha");
        if (fecha == null) return;

        int dia = Integer.parseInt(fecha.substring(0, 2));
        int mes = Integer.parseInt(fecha.substring(3, 5));
        int anoNacimiento = Integer.parseInt(fecha.substring(6, 10));

        calcularZodiaco(dia, mes);
        calcularEdad(anoNacimiento, mes, dia);

        btnVolver.setOnClickListener(v -> finish());

    }

    private void calcularEdad(int anoNacimiento, int mes, int dia) {
        Calendar hoy = Calendar.getInstance();
        int anoActual = hoy.get(Calendar.YEAR);
        int mesActual = hoy.get(Calendar.MONTH) + 1;
        int diaActual = hoy.get(Calendar.DAY_OF_MONTH);

        int edad = anoActual - anoNacimiento;
        if (mesActual < mes || (mesActual == mes && diaActual < dia)) {
            edad--;
        }
        String textoEdad = getString(R.string.txtEdad, edad);
        tvEdad.setText(textoEdad);
    }

    private void calcularZodiaco(int dia, int mes) {
        String[] listaSignos = getResources().getStringArray(R.array.signo);
        String signo = "";
        switch (mes) {
            case 1:
                signo = (dia <= 20) ? listaSignos[0] : listaSignos[1];
                break;
            case 2:
                signo = (dia <= 18) ? listaSignos[1] : listaSignos[2];
                break;
            case 3:
                signo = (dia <= 20) ? listaSignos[2] : listaSignos[3];
                break;
            case 4:
                signo = (dia <= 20) ? listaSignos[3] : listaSignos[4];
                break;
            case 5:
                signo = (dia <= 20) ? listaSignos[4] : listaSignos[5];
                break;
            case 6:
                signo = (dia <= 20) ? listaSignos[5] : listaSignos[6];
                break;
            case 7:
                signo = (dia <= 22) ? listaSignos[6] : listaSignos[7];
                break;
            case 8:
                signo = (dia <= 22) ? listaSignos[7] : listaSignos[8];
                break;
            case 9:
                signo = (dia <= 22) ? listaSignos[8] : listaSignos[9];
                break;
            case 10:
                signo = (dia <= 22) ? listaSignos[9] : listaSignos[10];
                break;
            case 11:
                signo = (dia <= 21) ? listaSignos[10] : listaSignos[11];
                break;
            case 12:
                signo = (dia <= 21) ? listaSignos[11] : listaSignos[0];
                break;
        }
        String signoComprobado =  getString(R.string.txtSigno,signo);
        tvSigno.setText(signoComprobado);
    }
}