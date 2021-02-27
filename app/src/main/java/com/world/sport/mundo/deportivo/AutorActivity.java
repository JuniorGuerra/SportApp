package com.world.sport.mundo.deportivo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.world.sport.mundo.deportivo.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class AutorActivity extends AppCompatActivity {

    CircleImageView volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autor_activity);

        volver = findViewById(R.id.volver);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}