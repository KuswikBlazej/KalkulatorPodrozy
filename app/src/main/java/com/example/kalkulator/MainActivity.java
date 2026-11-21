
package com.example.kalkulator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText dystans, spalanie, pasazerowie;
    RadioGroup grupaPaliwa;
    Button oblicz;
    TextView wynik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dystans = findViewById(R.id.dystans);
        spalanie = findViewById(R.id.spalanie);
        pasazerowie = findViewById(R.id.pasazerowie);

        grupaPaliwa = findViewById(R.id.grupaPaliwa);
        oblicz = findViewById(R.id.oblicz);
        wynik = findViewById(R.id.wynik);


        oblicz.setOnClickListener(v -> {

                String tekstDystans = dystans.getText().toString();

                String tekstSpalanie = spalanie.getText().toString();

                String tekstPasazerowie = pasazerowie.getText().toString();

                Boolean Error = false;

                if (tekstDystans.isEmpty() ||
                        tekstSpalanie.isEmpty() ||
                        tekstPasazerowie.isEmpty()) {
                    Error = true;
                }

                double km = Double.parseDouble(tekstDystans);
                double litry = Double.parseDouble(tekstSpalanie);
                int osoby = Integer.parseInt(tekstPasazerowie);

                if (!Double.isFinite(km) ||
                        !Double.isFinite(litry) ||
                        km <= 0 || litry <= 0 || osoby <= 0) {
                    Error = true;
                }

                double cenaPaliwa = 0.0;

                int wybranePaliwo = grupaPaliwa.getCheckedRadioButtonId();

                if (wybranePaliwo == R.id.benzyna) {
                    cenaPaliwa = 6.50;
                } else if (wybranePaliwo == R.id.diesel) {
                    cenaPaliwa = 6.70;
                } else if (wybranePaliwo == R.id.lpg) {
                    cenaPaliwa = 3.10;
                } else{
                    Error = true;
                }
                if(Error ==false){
                    double zuzytePaliwo = km * litry / 100;

                    double kosztCalkowity = zuzytePaliwo * cenaPaliwa;

                    double kosztNaOsobe = kosztCalkowity / osoby;

                    String rezultat = String.format(
                            Locale.getDefault(),
                            "Koszt całkowity: %.2f zł\nKoszt na osobę: %.2f zł",
                            kosztCalkowity,
                            kosztNaOsobe
                    );
                    wynik.setText(rezultat);
                    wynik.setTextColor(Color.GREEN);

                }
                else{
                    wynik.setText("Niepoprawne dane wejściowe!");
                    wynik.setTextColor(Color.RED);
                }





        });
    }
}
