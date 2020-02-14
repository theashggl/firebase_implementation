package com.database.insta_app_intern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registeruser extends AppCompatActivity {
EditText email,name,pass,phone,college,course,year;
Button register,login;
Spinner higherstudy;
boolean a=false,b=false,c=false;
String regex = "^(.+)@(.+)$",number="[1234]",phoneregex="\\d{10}";

private FirebaseAuth mAuth;
DatabaseReference databaseReference;
protected String nameuser,emailuser,password,phoneuser,collegeuser,courseuser,yearuser,highstudy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeruser);
        databaseReference= FirebaseDatabase.getInstance().getReference("userdetails");
        email=(EditText)findViewById(R.id.useremail);
        name=(EditText)findViewById(R.id.username);
        mAuth=FirebaseAuth.getInstance();
        pass=(EditText)findViewById(R.id.passwrd);
        phone=(EditText)findViewById(R.id.whatismobilenumber);
        college=(EditText)findViewById(R.id.college);
        course=(EditText)findViewById(R.id.course);
        year=(EditText)findViewById(R.id.year);
        higherstudy=(Spinner)findViewById(R.id.spinner);
        register=(Button)findViewById(R.id.register);
        login=(Button)findViewById(R.id.mainactivityintent);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 nameuser=name.getText().toString().trim();
                emailuser=email.getText().toString().trim();
                password=pass.getText().toString();
                phoneuser=phone.getText().toString();
                collegeuser=college.getText().toString();
                courseuser=course.getText().toString();
                yearuser=year.getText().toString();
                if(nameuser.isEmpty())
                {
                    name.setError("Please enter name");
                    name.requestFocus();
                }
                if(emailuser.isEmpty())
                {
                    email.setError("Please enter email");
                    email.requestFocus();
                }
                if(password.isEmpty())
                {
                    pass.setError("Please enter password");
                    pass.requestFocus();
                }if(phoneuser.isEmpty())
                {
                    phone.setError("Please enter phone number");
                    phone.requestFocus();
                }if(collegeuser.isEmpty())
                {
                    college.setError("Please enter college name");
                    college.requestFocus();
                }if(courseuser.isEmpty())
                {
                    course.setError("Please enter course");
                    course.requestFocus();
                }if(yearuser.isEmpty())
                {
                    year.setError("Please enter year");
                    year.requestFocus();
                }
                else{
                Pattern pattern = Pattern.compile(regex),
                        yearofuser=Pattern.compile(number),
                        phno=Pattern.compile(phoneregex);
                Matcher matcher = pattern.matcher(emailuser),
                        matchyear=yearofuser.matcher(yearuser),
                        matchphone=phno.matcher(phoneuser);
                if(!matcher.matches())
                {
                    email.setError("Email id format invalid");
                    email.requestFocus();
                }
                else {
                    a=true;
                }
                if(!matchyear.matches())
                {
                    year.setError("Please enter valid year number");
                    year.requestFocus();
                }
                else {
                    b=true;
                }
                if(!matchphone.matches())
                {
                    phone.setError("Enter exactly 10 digits");
                    phone.requestFocus();
                }
                else {
                    c=true;
                }
                }
                //boolean spin;
                int position=higherstudy.getSelectedItemPosition();
                if(position!=0)
                {
                    //spin=false;
                    highstudy=higherstudy.getSelectedItem().toString();
                }
                else
               highstudy=null;
                if(a&&b) {
                    mAuth.createUserWithEmailAndPassword(emailuser, password)
                            .addOnCompleteListener(registeruser.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("Success", "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent i = new Intent(registeruser.this, hellouser.class);
                                        startActivity(i);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("Error", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(registeruser.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
//                                    updateUI(null);
                                    }

                                    // ...
                                }
                            });
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registeruser.this,MainActivity.class));
            }
        });
        String id=databaseReference.push().getKey();
        userdetails newuser=new userdetails(nameuser,phoneuser,collegeuser,courseuser,yearuser,highstudy);
        databaseReference.child(id).setValue(newuser);
    }
}
