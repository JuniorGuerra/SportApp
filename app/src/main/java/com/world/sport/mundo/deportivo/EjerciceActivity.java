package com.world.sport.mundo.deportivo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.world.sport.mundo.deportivo.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class EjerciceActivity extends AppCompatActivity {

    String tipe;

    CircleImageView mNext;
    CircleImageView mBack;
    TextView mTitle;
    TextView mInformation;
    ImageView mImage;

    Button btnNext;
    Button btnBack;


    DatabaseReference mDatabase;

    int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercice_activity);

        tipe = getIntent().getStringExtra("send");


        mNext = findViewById(R.id.cImgViewNext);
        mBack = findViewById(R.id.cImgViewBack);
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnnext);

        mTitle = findViewById(R.id.txtTitle);
        mInformation = findViewById(R.id.txtInformation);
        mImage = findViewById(R.id.imgSport);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        loadData();

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next();
            }
        });

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = id + 1;
                loadData();
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id = id - 1;

                if(id > 0){
                    loadData();
                }
                else{
                    Toast.makeText(EjerciceActivity.this, "Ya no puede volver", Toast.LENGTH_SHORT).show();
                    id = 1;
                }


            }
        });btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = id + 1;
                loadData();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id = id - 1;

                if(id > 0){
                    loadData();
                }
                else{
                    Toast.makeText(EjerciceActivity.this, "Ya no puede volver", Toast.LENGTH_SHORT).show();
                    id = 1;
                }
            }
        });
    }

    private void loadData() {

        mDatabase.child("Deportes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    final String data = String.valueOf(id).toString();
                    mDatabase.child("Deportes").child(tipe).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.exists()){

                                mDatabase.child("Deportes").child(tipe).child(data).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        if(snapshot.exists()){
                                            try {

                                                String title = snapshot.child("Title").getValue().toString();
                                                String information = snapshot.child("Informacion").getValue().toString();
                                                String image = snapshot.child("img").getValue().toString();

                                                showData(title, information, image);

                                            }
                                            catch (Exception e){
                                                Toast.makeText(EjerciceActivity.this, "Error en:" + e, Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                        else{
                                            Toast.makeText(EjerciceActivity.this, "No hay mas ejercicios", Toast.LENGTH_SHORT).show();
                                            id = id - 1;
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
                else{
                    Toast.makeText(EjerciceActivity.this, "Deporte error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void showData(String title, String information, String url) {

        mInformation.setText(information);
        mTitle.setText(title);
        //Mostrar imagen en el imageView

        Glide.with(EjerciceActivity.this)
                .asGif()
                .load(url)
                .into(mImage);

        //Picasso.get().load(image).into(mImage);

    }

    private void back() {

    }

    private void next() {

    }
}