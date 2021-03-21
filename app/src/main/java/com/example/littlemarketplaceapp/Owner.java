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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;

public class Owner extends AppCompatActivity implements View.OnClickListener {

    private EditText signEmailEditText, signInPasswordEditText;
    private EditText Fullname;
    private EditText Username;
    private EditText Mobile;

    private Button SignUpButton;
    private Button Back;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);
        signEmailEditText=findViewById(R.id.email);
        signInPasswordEditText=findViewById(R.id.conpassword);

        Fullname=findViewById(R.id.fullname);
        Username=findViewById(R.id.susername);
        Mobile= findViewById(R.id.mobile);


        SignUpButton=findViewById(R.id.gotoshop);
        Back=findViewById(R.id.oback);

        Back.setOnClickListener(this);
        SignUpButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    { switch(v.getId())
    {
        case R.id.gotoshop:
            UserRegister();
        case R.id.oback:
            Intent intent = new Intent(getApplication(),MainActivity.class);
            startActivity(intent);
            break;
    }

    }

    private void UserRegister() {
        String email=signEmailEditText.getText().toString().trim();
        String password= signInPasswordEditText.getText().toString().trim();
        String fullname= Fullname.getText().toString().trim();
        String username= Username.getText().toString().trim();
        String mobile= Mobile.getText().toString().trim();
        //String key= databaseReference.push().getKey();

       /* ForOwner sowner = new ForOwner(fullname,username,email,mobile,password);
        databaseReference.child(key).setValue(sowner);
        Toast.makeText(getApplicationContext(),"Registration complete",Toast.LENGTH_SHORT);
*/
        //send data to shop activity

        Intent intent = new Intent(this, Shop.class);
        intent.putExtra("emaili",email);
        intent.putExtra("passwordi",password);
        intent.putExtra("fullnamei",fullname);
        intent.putExtra("usernamei",username);
        intent.putExtra("mobilei",mobile);
        startActivity(intent);


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
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Registration is Successful",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(),"User is already Registered",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Error : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }

}

