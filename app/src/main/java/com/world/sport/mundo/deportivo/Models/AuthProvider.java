package com.world.sport.mundo.deportivo.Models;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthProvider {
    private FirebaseAuth mAuth;


    public AuthProvider()
    {
        mAuth = FirebaseAuth.getInstance();
    }

    public Task<AuthResult> login(String email, String password)
    {
        return mAuth.signInWithEmailAndPassword(email,password);
    }

    public void logout(){
        if(mAuth != null){
            mAuth.signOut();

        }
    }

    public FirebaseUser getUserSesion()
    {
        if (mAuth.getCurrentUser() != null)
        {
            return mAuth.getCurrentUser();
        }
        else
        {
            return null;
        }

    }
}
