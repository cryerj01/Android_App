package com.example.androidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private EditText mEmail,  pass1;
    private Button Login;

    private  FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();

        Log.d("LoginActivity", "********");
        Log.d("LoginActivity", "mAuth" + mAuth);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                Log.d("LoginActivity", "********");
                Log.d("LoginActivity", "User " + user);


                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, PassengerMapActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        mEmail = (EditText) findViewById(R.id.LoginEmail);
        pass1 = (EditText) findViewById(R.id.LoginPass);


        Log.d("SignUpActivity", "********");
        Log.d("SignUpActivity", "data" + mEmail.toString() + pass1.toString());

        Login = (Button) findViewById(R.id.LoginSubmit);



        Log.d("SignUpActivity", "********");
        Log.d("SignUpActivity", "Fields set");
        try {
            Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("SignUpActivity", "********");
                    Log.d("SignUpActivity", " Lister on ");

                    Log.d("SignUpActivity", "********");
                    Log.d("SignUpActivity", "If is true");

                    final String Email = mEmail.getText().toString();
                    final String Password = pass1.getText().toString();

                    Log.d("SignUpActivity", "********");
                    Log.d("SignUpActivity", "data " + Email + " "+Password);
                    mAuth = FirebaseAuth.getInstance();


                    mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Sign up Error", Toast.LENGTH_SHORT).show();
                            } else {
//                                String user_id = mAuth.getCurrentUser().getUid();
//                                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("User").child("Passenger").child("user_id");
//                                current_user_db.setValue(true);

                                Log.d("SignUpActivity", "********");
                                Log.d("SignUpActivity", "logged in");
                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user != null) {
                                    Intent intent = new Intent(LoginActivity.this, PassengerMapActivity.class);
                                    startActivity(intent);
                                    finish();
                                    return;
                                }

                            }
                        }
                    });

                }
            });
        }catch (Exception e){
            Log.d("SignUpActivity", "Error Exception"+ e);
        }

    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthStateListener);
    }


}


