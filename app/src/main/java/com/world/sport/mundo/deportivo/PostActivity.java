package com.world.sport.mundo.deportivo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.world.sport.mundo.deportivo.R;

public class PostActivity extends AppCompatActivity {

    FrameLayout mFrameEspalda;
    FrameLayout mFrameAbdomen;
    FrameLayout mFrameBrazo;
    FrameLayout mFramePierna;
    FrameLayout mMuñeca;

    String texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_activity);



        mFramePierna = findViewById(R.id.frlPerna);
        mFrameBrazo = findViewById(R.id.frlBrazo);
        mFrameAbdomen = findViewById(R.id.frlAbdomen);
        mFrameEspalda = findViewById(R.id.frlEspalda);
        mMuñeca = findViewById(R.id.frlManos);

        mFramePierna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ir("Piernas");
            }
        });

        mFrameBrazo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ir("Brazos");
            }
        });

        mFrameAbdomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ir("Abdomen");
            }
        });

        mFrameEspalda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ir("Espalda");
            }
        });

        mMuñeca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ir("Muñecas");
            }
        });
    }

    private void ir(String send) {
        Intent intent = new Intent(PostActivity.this, EjerciceActivity.class);
        intent.putExtra("send", send);
        startActivity(intent);
    }
}