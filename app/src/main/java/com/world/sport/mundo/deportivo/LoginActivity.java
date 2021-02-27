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
import com.world.sport.mundo.deportivo.Models.AuthProvider;

public class LoginActivity extends AppCompatActivity {

    EditText mEmail;
    EditText mPassword;

    Button btnLogin;
    TextView btnRegister;
    FirebaseAuth mAuth;
    AuthProvider mProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        mEmail = findViewById(R.id.loginEmail);
        mPassword = findViewById(R.id.loginPassword);

        btnRegister = findViewById(R.id.textViewRegisterActivity);
        btnLogin = findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();
        mProvider = new AuthProvider();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setRegister();

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setLogin();

            }
        });

    }

    private void setLogin() {

        try {
            final String email =  mEmail.getText().toString();
            final String password =  mPassword.getText().toString();

            mProvider.login(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                         Call();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Email o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        catch (Exception e){
            Toast.makeText(this, "LLene los campos por favor", Toast.LENGTH_SHORT).show();
        }

    }

    private void Call() {
        Intent intent = new Intent(LoginActivity.this, Gym.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mProvider.getUserSesion() != null){
            Call();
        }
    }

    private void setRegister() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}