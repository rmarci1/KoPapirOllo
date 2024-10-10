package com.example.kopapirollo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageButton Ko;
    private ImageButton Ollo;
    private ImageButton Papir;
    private ImageView te_valasztasod;
    private ImageView gep_valasztasa;
    private Random random = new Random();

    private TextView dontetlen_text;
    private int te = 0;
    private int gep = 0;
    private int dontetlen_count = 0;
    private int te_nyert = 0;
    private int gep_nyert = 0;
    private AlertDialog alertDialog;

    private ImageView te1;
    private ImageView te2;
    private ImageView te3;
    private ImageView gep1;
    private ImageView gep2;
    private ImageView gep3;

    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        Ko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                te_valasztasod.setImageResource(R.drawable.rock);
                te = 0;
                gep = random.nextInt(3);
                szamol();
            }
        });
        Ollo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                te_valasztasod.setImageResource(R.drawable.scissors);
                te = 1;
                gep = random.nextInt(3);
                szamol();
            }
        });
        Papir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                te_valasztasod.setImageResource(R.drawable.paper);
                te = 2;
                gep = random.nextInt(3);
                szamol();
            }
        });

    }
    public void szamol(){
        if(gep ==0) gep_valasztasa.setImageResource(R.drawable.rock);
        if(gep ==1) gep_valasztasa.setImageResource(R.drawable.scissors);
        if(gep ==2) gep_valasztasa.setImageResource(R.drawable.paper);
        if(te==gep)
        {
            Toast.makeText(this, "Döntetlen", Toast.LENGTH_SHORT).show();
            dontetlen_count++;
            dontetlen_text.setText("Döntetlenek száma: "+Integer.valueOf(dontetlen_count));
        }
        else if((te == 0 && gep ==1)|| (te==1 && gep ==2) ||(te==2 && gep ==0)){
            Toast.makeText(this, "Te nyertél", Toast.LENGTH_SHORT).show();
            te_nyert++;
            if(te_nyert==1){
                gep3.setImageResource(R.drawable.heart1);
            }
            else if(te_nyert==2) {
                gep2.setImageResource(R.drawable.heart1);
            }
            else if(te_nyert==3){
                gep1.setImageResource(R.drawable.heart1);
            }

        }
        else {
            Toast.makeText(this, "A gép nyert", Toast.LENGTH_SHORT).show();
            gep_nyert++;
            if (gep_nyert == 1) {
                te1.setImageResource(R.drawable.heart1);
            } else if (gep_nyert == 2) {
                te2.setImageResource(R.drawable.heart1);
            } else if (gep_nyert == 3) {
                te3.setImageResource(R.drawable.heart1);
            }
        }
        if(gep_nyert ==3 || te_nyert ==3){
            vege();
        }
    }
    public void vege(){

        if(te_nyert==3){
            title = "Győzelem!";
        }
        else{
            title = "Vereség";
        }
        alertDialog = new AlertDialog.Builder(this).
                setTitle(title).
                setMessage("Szeretnél új játékot játszani?").
                setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ujJatek();
                    }
                }).
                setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).create();
        alertDialog.show();
    }
    public void init(){
        te1 = findViewById(R.id.te1);
        te2 = findViewById(R.id.te2);
        te3 = findViewById(R.id.te3);
        gep1 = findViewById(R.id.gep);
        gep2 = findViewById(R.id.gep2);
        gep3 = findViewById(R.id.gep3);
        dontetlen_text = findViewById(R.id.dontetlen);
        Ko = findViewById(R.id.ko);
        Papir = findViewById(R.id.papir);
        Ollo = findViewById(R.id.ollo);
        te_valasztasod = findViewById(R.id.kep1);
        gep_valasztasa = findViewById(R.id.kep2);
    }
    public void ujJatek(){
        te = 0;
        gep = 0;
        te_nyert = 0;
        gep_nyert = 0;
        dontetlen_count = 0;
        dontetlen_text.setText("Döntetlenek száma: 0");
        te1.setImageResource(R.drawable.heart2);
        te2.setImageResource(R.drawable.heart2);
        te3.setImageResource(R.drawable.heart2);
        gep1.setImageResource(R.drawable.heart2);
        gep2.setImageResource(R.drawable.heart2);
        gep3.setImageResource(R.drawable.heart2);
        te_valasztasod.setImageResource(R.drawable.rock);
        gep_valasztasa.setImageResource(R.drawable.rock);
    }
}