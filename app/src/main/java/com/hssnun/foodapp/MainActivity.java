package com.hssnun.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText mail,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mail=findViewById(R.id.mail_id);
        password=findViewById(R.id.editText);
        login=findViewById(R.id.button_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s_mail=mail.getText().toString();
                String s_password=password.getText().toString();


                if(TextUtils.isEmpty(s_mail) || TextUtils.isEmpty(s_password)){
                    Toast.makeText(MainActivity.this,"all fields are required !",Toast.LENGTH_SHORT).show();
                }
                else if (s_password.length()<6){
                    Toast.makeText(MainActivity.this,"password must be at least 6 caracters",Toast.LENGTH_SHORT).show();

                }
                else {
                    login(s_mail, s_password);
                }
            }
        });

            }

            public  void login(String s_mail,String s_password){

                mAuth.signInWithEmailAndPassword(s_mail,s_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(MainActivity.this,CompteActivity.class));

                        }

                        else {
                            Toast.makeText(MainActivity.this,"Authentication failed",Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }

    }
