package com.muzafferatmaca.a2doyuncalismasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SonucEkrani extends AppCompatActivity {

    TextView toplamSkor,yuksekSkor;
    Button buttonRepeat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc_ekrani);

        toplamSkor = findViewById(R.id.toplamSkor);
        yuksekSkor = findViewById(R.id.yuksekSkor);
        buttonRepeat = findViewById(R.id.buttonRepeat);

        int skor = getIntent().getIntExtra("skor",0);
        toplamSkor.setText(String.valueOf(skor));

        SharedPreferences sharedPreferences = getSharedPreferences("Sonuc", Context.MODE_PRIVATE);
        int enYuksekSkor = sharedPreferences.getInt("enYuksekSkor",0);

        if (skor > enYuksekSkor){

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("enYuksekSkor",skor);
            editor.commit();
            yuksekSkor.setText(String.valueOf(skor));


        }else {
            yuksekSkor.setText(String.valueOf(enYuksekSkor));

        }


        buttonRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SonucEkrani.this,MainActivity.class));
                finish();

            }
        });
    }
}