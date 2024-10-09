package com.example.kopapirollo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    private Button Ko;
    private Button Ollo;
    private Button Papir;
    private ImageView te_valasztasod;
    private ImageView gep_valasztasa;
    private TextView kiirat;
    private Random random = new Random();
    private int te = 0;
    private int gep = 0;
    private int te_nyert = 0;
    private int gep_nyert = 0;
    private AlertDialog alertDialog;

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
        if(te==gep)  Toast.makeText(this, "Döntetlen", Toast.LENGTH_SHORT).show();
        else if((te == 0 && gep ==1)|| (te==1 && gep ==0) ||(te==2 && gep ==0)){
            Toast.makeText(this, "Te nyertél", Toast.LENGTH_SHORT).show();
            te_nyert++;
        }
        else {
            Toast.makeText(this, "A gép nyert", Toast.LENGTH_SHORT).show();
            gep_nyert++;
        }
        kiirat.setText("Eredmény Ember:"+Integer.valueOf(te_nyert) +"Computer:"+Integer.valueOf(gep_nyert));
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
    }
    public void init(){
        Ko = findViewById(R.id.ko);
        Papir = findViewById(R.id.papir);
        Ollo = findViewById(R.id.ollo);
        kiirat = findViewById(R.id.text3);
        te_valasztasod = findViewById(R.id.kep1);
        gep_valasztasa = findViewById(R.id.kep2);
    }
    public void ujJatek(){
        te = 0;
        gep = 0;
        te_nyert = 0;
        gep_nyert = 0;
        kiirat.setText("Eredmény Ember:0 Computer: 0");
        te_valasztasod.setImageResource(R.drawable.rock);
        gep_valasztasa.setImageResource(R.drawable.rock);
    }
}