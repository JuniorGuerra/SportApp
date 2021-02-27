package com.world.sport.mundo.deportivo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.world.sport.mundo.deportivo.R;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    EditText mUsername;
    EditText mEmail;
    EditText mPassword;
    Button btnRegister;
    TextView mVolver2;

    CircleImageView mVolver;

    //Variables de los datos

    String name;
    String email;
    String password;

    //Registrar usuario

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        mUsername = (EditText)findViewById(R.id.registerUsername);
        mEmail = (EditText)findViewById(R.id.registerEmail);
        mPassword = (EditText)findViewById(R.id.registerPassword);

        mVolver = findViewById(R.id.imgVolver);
        mVolver2 = findViewById(R.id.textBack);

        mVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mVolver2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnRegister = (Button)findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = mUsername.getText().toString();
                email = mEmail.getText().toString();
                password = mPassword.getText().toString();

                if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                    if(password.length() >= 6){
                        registerUser();
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Mas de 6 caracteres", Toast.LENGTH_SHORT).show();
                    }
                    
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void registerUser(){
        //Crear usuario a firebase
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //obtener el id del usuario
                    String id = mAuth.getCurrentUser().getUid();

                    //Crear mapa de valores
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("mail", email);
                    map.put("password", password);

                    //crear datos en DatabseRealtime de firebase;
                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                Intent intent = new Intent(RegisterActivity.this, Gym.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, "No se pudieron crear los datos", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(RegisterActivity.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}