package com.example.littlemarketplaceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class SignUp1 extends AppCompatActivity {

    private Button Customer;
    private Button Owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up1);
        Customer = findViewById(R.id.customer);
        Owner = findViewById(R.id.owner);

        Owner.setOnClickListener(v -> {
                    Intent intent = new Intent(SignUp1.this, Owner.class);
                    String message = null;
                    intent.putExtra("owner", message);
                    startActivity(intent);
                }

        );
        Customer.setOnClickListener(v -> {
                    Intent intent = new Intent(SignUp1.this, Customer.class);
                    String message1 = null;
                    intent.putExtra("customer", message1);
                    startActivity(intent);
                }

        );
    }
}