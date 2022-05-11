package com.muzafferatmaca.a2doyuncalismasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class OyunEkrani extends AppCompatActivity {

    TextView textViewSkor, oyunaBasla;
    ImageView sariDaire, anaKarakter, siyahKare, kirmiziUcgen;
    ConstraintLayout constraintLayout;
    //pozisyonlar

    int anakarakterX;
    int anakarakterY;
    int sariDaireX;
    int sariDaireY;
    int siyahKareX;
    int siyahKareY;
    int kirmiziUcgenX;
    int kirmiziUcgenY;

    int skor = 0;

    //kontroller

    boolean dokunmaKontrol = false;
    boolean baslangicKontrol = false;

    //hızlar

    int anakarakterHiz;
    int sariDaireHiz;
    int siyahKareHiz;
    int kirmiziUcgenHiz;

    Timer timer = new Timer();
    Handler handler = new Handler();

    //boyutlar

    int ekranGenislik;
    int ekranYuksekligi;
    int anakarakterGenisligi;
    int anakarakterYuksekligi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun_ekrani);

        textViewSkor = findViewById(R.id.textViewSkor);
        oyunaBasla = findViewById(R.id.oyunaBasla);
        sariDaire = findViewById(R.id.sariDaire);
        anaKarakter = findViewById(R.id.anaKarakter);
        siyahKare = findViewById(R.id.siyahKare);
        kirmiziUcgen = findViewById(R.id.kirmiziUcgen);
        constraintLayout = findViewById(R.id.cl);

        //cisimleri ekranın dışına çıkarma

        siyahKare.setX(-100);
        siyahKare.setY(-100);
        sariDaire.setX(-100);
        sariDaire.setY(-100);
        kirmiziUcgen.setX(-100);
        kirmiziUcgen.setY(-100);

        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (baslangicKontrol) {

                    if (event.getAction() == MotionEvent.ACTION_DOWN) {

                        dokunmaKontrol = true;

                    }

                    if (event.getAction() == MotionEvent.ACTION_UP) {

                        dokunmaKontrol = false;

                    }

                } else {

                    baslangicKontrol = true;

                    oyunaBasla.setVisibility(View.INVISIBLE);

                    anakarakterX = (int) anaKarakter.getX();
                    anakarakterY = (int) anaKarakter.getY();

                    anakarakterGenisligi = anaKarakter.getWidth();
                    anakarakterYuksekligi = anaKarakter.getHeight();
                    ekranGenislik = constraintLayout.getWidth();
                    ekranYuksekligi = constraintLayout.getHeight();

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            handler.post(new Runnable() {
                                @Override
                                public void run() {

                                    anakarakterHareketEttirme();
                                    cisimlerinHareketi();
                                    carpismaKontrolu();


                                }
                            });

                        }
                    }, 0, 20);

                }


                return true;
            }
        });

    }

    public void anakarakterHareketEttirme() {

        anakarakterHiz = Math.round(ekranYuksekligi / 30);


        if (dokunmaKontrol) {
            anakarakterY -= anakarakterHiz;
        } else {
            anakarakterY += 20;
        }

        if (anakarakterY <= 0) {
            anakarakterY = 0;
        }

        if (anakarakterY >= ekranYuksekligi - anakarakterYuksekligi) {
            anakarakterY = ekranYuksekligi - anakarakterYuksekligi;
        }

        anaKarakter.setY(anakarakterY);

    }

    public void cisimlerinHareketi() {

        sariDaireHiz = Math.round(ekranGenislik / 60);
        siyahKareHiz = Math.round(ekranGenislik / 50);
        kirmiziUcgenHiz = Math.round(ekranGenislik / 40);

        siyahKareX -= siyahKareHiz;

        if (siyahKareX < 0) {
            siyahKareX = ekranGenislik + 20;
            siyahKareY = (int) Math.floor(Math.random() * ekranYuksekligi);

        }

        siyahKare.setX(siyahKareX);
        siyahKare.setY(siyahKareY);

        sariDaireX -= sariDaireHiz;

        if (sariDaireX < 0) {
            sariDaireX = ekranGenislik + 20;
            sariDaireY = (int) Math.floor(Math.random() * ekranYuksekligi);

        }

        sariDaire.setX(sariDaireX);
        sariDaire.setY(sariDaireY);

        kirmiziUcgenX -= kirmiziUcgenHiz;

        if (kirmiziUcgenX < 0) {
            kirmiziUcgenX = ekranGenislik + 20;
            kirmiziUcgenY = (int) Math.floor(Math.random() * ekranYuksekligi);

        }

        kirmiziUcgen.setX(kirmiziUcgenX);
        kirmiziUcgen.setY(kirmiziUcgenY);

    }

    public void carpismaKontrolu() {

        int sariDaireMerkezX = sariDaireX + sariDaire.getWidth() / 2;
        int sariDaireMerkezY = sariDaireY + sariDaire.getHeight() / 2;

        if (0 <= sariDaireMerkezX && sariDaireMerkezX <= anakarakterGenisligi && anakarakterY <= sariDaireMerkezY && sariDaireY <= anakarakterY + anakarakterYuksekligi) {

            skor += 20;
            sariDaireX = -10;

        }

        int kirmiziUcgenMerkezX = kirmiziUcgenX + kirmiziUcgen.getWidth() / 2;
        int kirmiziUcgenMerkezY = kirmiziUcgenY + kirmiziUcgen.getHeight() / 2;

        if (0 <= kirmiziUcgenMerkezX && kirmiziUcgenMerkezX <= anakarakterGenisligi && anakarakterY <= kirmiziUcgenMerkezY && kirmiziUcgenY <= anakarakterY + anakarakterYuksekligi) {

            skor += 50;
            kirmiziUcgenX = -10;

        }

        int siyahKareMerkezX = siyahKareX + siyahKare.getWidth() / 2;
        int siyahKareMerkezY = siyahKareY + siyahKare.getHeight() / 2;

        if (0 <= siyahKareMerkezX && siyahKareMerkezX <= anakarakterGenisligi && anakarakterY <= siyahKareMerkezY && siyahKareY <= anakarakterY + anakarakterYuksekligi) {

            siyahKareX = -10;

            timer.cancel();
            timer = null;

            Intent intent = new Intent(OyunEkrani.this,SonucEkrani.class);
            intent.putExtra("skor",skor);
            startActivity(intent);

        }

        textViewSkor.setText(String.valueOf(skor));


    }

}