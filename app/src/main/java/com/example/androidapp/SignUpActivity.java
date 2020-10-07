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


public class SignUpActivity extends AppCompatActivity {
    private EditText mEmail, fname, sname, pass1, pass2;
    private Button Signup;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private String user_id;


    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("User").child("Passenger");


        mAuth.getInstance();
        Log.d("SignUpActivity", "********");
        Log.d("SignUpActivity", "mAuth" + mAuth);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                Log.d("SignUpActivity", "********");
                Log.d("SignUpActivity", "User " + user);

                if (user != null) {
                    Log.d("SignupActivity", "***********************");
                    Log.d("SignUpActivity", "Logged in already");
                    Intent intent = new Intent(SignUpActivity.this, PassengerMapActivity.class);
                    startActivity(intent);
                    finish();
                    return;

                }
            }
        };


        mEmail = (EditText) findViewById(R.id.SignUpEmail);
        pass1 = (EditText) findViewById(R.id.SignUpPass);
        pass2 = (EditText) findViewById(R.id.SignUpPass2);
        fname = (EditText) findViewById(R.id.FName);
        sname = (EditText) findViewById(R.id.SName);

        Log.d("SignUpActivity", "********");
        Log.d("SignUpActivity", "data" + mEmail.toString() + pass1.toString() + fname.toString() + sname.toString());

        Signup = (Button) findViewById(R.id.signUpSubmit);


        Log.d("SignUpActivity", "********");
        Log.d("SignUpActivity", "Fields set");
        try {
            Signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("SignUpActivity", "********");
                    Log.d("SignUpActivity", " Lister on ");

                    Log.d("SignUpActivity", "********");
                    Log.d("SignUpActivity", "If is true");

                    final String Email = mEmail.getText().toString();
                    final String Password1 = pass1.getText().toString();
                    final String Password2 =  pass2.getText().toString();

                    final String Fname = fname.getText().toString();
                    final String Sname = sname.getText().toString();
                    Log.d("SignUpActivity", "********");
                    Log.d("SignUpActivity", "data " + Email + " "+ Password1 +  " "+Password2 +  " "+ Fname +  " "+ Sname);
                    mAuth = FirebaseAuth.getInstance();

                    if (Password1.equals(Password2)) {
                        mAuth.createUserWithEmailAndPassword(Email, Password1).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, "Sign up Error", Toast.LENGTH_SHORT).show();
                                } else {

                                    Usermodel userData = new Usermodel(Fname, Sname);
                                    user_id = mAuth.getCurrentUser().getUid();
                                    mDatabase.child(user_id).setValue(userData);
                                    Log.d("SignUpActivity", "********");
                                    Log.d("SignUpActivity", "Saved to Database");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user != null) {
                                        Intent intent = new Intent(SignUpActivity.this, PassengerMapActivity.class);
                                        startActivity(intent);
                                        finish();
                                        return;
                                    }
                                }
                            }
                        });
                    } else {
                        Toast.makeText(SignUpActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();

                    }


                }
            });
        } catch (Exception e) {
            Log.d("SignUpActivity", "Error Exception" + e);
        }

    }


}




