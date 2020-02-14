package com.database.insta_app_intern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
EditText log,pass;
TextView forget,or;
Button login,register;
String loginid,password,regex = "^(.+)@(.+)$";
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log=(EditText)findViewById(R.id.useremail);
        pass=(EditText)findViewById(R.id.passwrd);
        login=(Button)findViewById(R.id.login);
        register=(Button)findViewById(R.id.register);
        forget=(TextView)findViewById(R.id.forgetpass);
        or=(TextView)findViewById(R.id.or);
// ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                loginid=log.toString();
//                password=pass.toString();
//                mAuth.createUserWithEmailAndPassword(loginid,password)
//                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    // Sign in success, update UI with the signed-in user's information
//                                    Log.d("Success", "createUserWithEmail:success");
//                                    FirebaseUser user = mAuth.getCurrentUser();
//
//
//                                }
//                                else {
//                                    // If sign in fails, display a message to the user.
//                                    Log.w("Error", "createUserWithEmail:failure", task.getException());
//                                    Toast.makeText(MainActivity.this, "Authentication failed.",
//                                            Toast.LENGTH_SHORT).show();
//                                    log.requestFocus();
//
////                                    Intent ii=new Intent(MainActivity.this,MainActivity.class);
////                                    startActivity(ii);
//                                }
//
//                                // ...
//                            }
//                        });
//                forget.setVisibility(View.GONE);
//                login.setVisibility(View.GONE);
//                or.setVisibility(View.GONE);
                Intent i=new Intent(MainActivity.this,registeruser.class);startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginid=log.getText().toString();
                password=pass.getText().toString();
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(loginid);
                if(loginid.isEmpty())
                {
                    log.setError("enter your id");
                    log.requestFocus();
                    //Toast.makeText(MainActivity.this, "empty fields", Toast.LENGTH_SHORT).show();
                }
                if(password.isEmpty())
                {
                    pass.requestFocus();
                    pass.setError("enter password");
                }
                else {
                    if(!matcher.matches())
                    {
                        log.setError("Email id format invalid");
                        log.requestFocus();
                    }
                    else {
                        mAuth.signInWithEmailAndPassword(loginid, password)
                                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d("Error", "signInWithEmail:success");
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            Intent i=new Intent(MainActivity.this,hellouser.class);
                                            startActivity(i);
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w("Error", "signInWithEmail:failure", task.getException());
                                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                            //updateUI(null);
                                        }

                                        // ...
                                    }
                                });
                    }
                }

            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent iii = new Intent(MainActivity.this, hellouser.class);
            startActivity(iii);
        }
    }

    }

