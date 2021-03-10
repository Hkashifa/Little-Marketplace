package com.example.littlemarketplaceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText signEmailEditText,signInPasswordEditText;
    private Button SignUpButton;
    private Button SignInButton;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        signEmailEditText=findViewById(R.id.email);
        signInPasswordEditText=findViewById(R.id.password);
        SignUpButton=findViewById(R.id.signup);
        SignInButton=findViewById(R.id.login);

        SignInButton.setOnClickListener(this);
        SignUpButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    { switch(v.getId())
    {
        case R.id.login:
            
           userLogin();
        case R.id.signup:
            Intent intent = new Intent(getApplication(),SignUp1.class);
            startActivity(intent);
            break;
    }

    }

    private void userLogin() {
        String email=signEmailEditText.getText().toString().trim();
        String password= signInPasswordEditText.getText().toString().trim();

        if(email.isEmpty())
        {
            signEmailEditText.setError("Enter an email address");
            signEmailEditText.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            signEmailEditText.setError("Enter a valid email address");
            signEmailEditText.requestFocus();
            return;
        }

        //checking the validity of the password
        if(password.isEmpty())
        {
            signInPasswordEditText.setError("Enter a password");
            signInPasswordEditText.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Intent intent = new Intent(getApplicationContext(),Customer.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }else
                {
                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT);
                }
            }
        });


    }

}