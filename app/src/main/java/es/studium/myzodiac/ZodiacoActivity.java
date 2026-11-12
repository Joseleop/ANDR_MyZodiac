package es.studium.myzodiac;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
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
    ImageView imgSigno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zodiaco);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvEdad = findViewById(R.id.tvEdad);
        tvSigno = findViewById(R.id.tvSigno);
        imgSigno = findViewById(R.id.imgSigno);
        btnVolver = findViewById(R.id.btnVolver);


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
        int indexSigno = 0;

        TypedArray listaIconos = getResources().obtainTypedArray(R.array.iconosSignos);

        switch (mes) {
            case 1:
                indexSigno = (dia <= 20) ? 0 : 1;
                break;
            case 2:
                indexSigno = (dia <= 18) ? 1 : 2;
                break;
            case 3:
                indexSigno = (dia <= 20) ? 2 : 3;
                break;
            case 4:
                indexSigno = (dia <= 20) ? 3 : 4;
                break;
            case 5:
                indexSigno = (dia <= 20) ? 4 : 5;
                break;
            case 6:
                indexSigno = (dia <= 20) ? 5 : 6;
                break;
            case 7:
                indexSigno = (dia <= 22) ? 6 : 7;
                break;
            case 8:
                indexSigno = (dia <= 22) ? 7 : 8;
                break;
            case 9:
                indexSigno = (dia <= 22) ? 8 : 9;
                break;
            case 10:
                indexSigno = (dia <= 22) ? 9 : 10;
                break;
            case 11:
                indexSigno = (dia <= 21) ? 10 : 11;
                break;
            case 12:
                indexSigno = (dia <= 21) ? 11 : 0;
                break;
        }
        String signo = listaSignos[indexSigno];
        String signoComprobado = getString(R.string.txtSigno, signo);
        tvSigno.setText(signoComprobado);

        int drawableId = listaIconos.getResourceId(indexSigno, 0);
        imgSigno.setImageResource(drawableId);

        listaIconos.recycle();
    }
}