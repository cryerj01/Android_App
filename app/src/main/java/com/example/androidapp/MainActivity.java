package com.example.androidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d("MainActivity", "***********************");
        Log.d("MainActivity", "Hello World");

        mAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        Log.d("MainActivity", "********");
        Log.d("MainActivity", "mAuth " + mAuth);


        FirebaseUser user = mAuth.getCurrentUser();
        Log.d("MainActivity", "********");
        Log.d("SignUpActivity", "User " + user);

        if (user != null) {
            Log.d("MainActivity", "***********************");
            Log.d("MainActivity", "Logged in already");
            Intent intent = new Intent(MainActivity.this, PassengerMapActivity.class);
            startActivity(intent);
            finish();
            return;


        }

    }




    public void onSignUpPage(View v) {
        Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
        startActivity(intent);
        finish();
        Log.d("MainActivity", "***********************");
        Log.d("MainActivity", "Sign up page redirect");
        return;

    }

    public void onLoginPage(View v) {
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
        Log.d("MainActivity", "***********************");
        Log.d("MainActivity", "Sign up page redirect");
        return;
    }



}

