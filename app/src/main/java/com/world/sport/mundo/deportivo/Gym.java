package com.world.sport.mundo.deportivo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.world.sport.mundo.deportivo.Models.AuthProvider;

public class Gym extends AppCompatActivity {

    AuthProvider mProvider;
    Button btnIda;

    TextView mExit;
    TextView mAutor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gym_activity);

        mProvider = new AuthProvider();

        mExit = findViewById(R.id.logout);
        mAutor = findViewById(R.id.txtAutor);

        btnIda = findViewById(R.id.btnContinue);

        btnIda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Gym.this, PostActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProvider.logout();
                Intent intent = new Intent(Gym.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        mAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Gym.this, AutorActivity.class);
                startActivity(intent);
            }
        });
    }
}