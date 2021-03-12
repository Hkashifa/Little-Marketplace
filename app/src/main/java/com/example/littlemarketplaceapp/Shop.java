package com.example.littlemarketplaceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Shop extends AppCompatActivity {

    private ImageButton Logoimage;
    private ImageButton Cover;
    private EditText ShopnameEditText;
    private TextView ShowShopName;
    private Button SaveButton;
    DatabaseReference databaseReference1;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Intent intent = getIntent();
        String emaila=intent.getExtras().getString("emaili");
        String passworda=intent.getExtras().getString("passwordi");
        String fullnamea=intent.getExtras().getString("fullnamei");
        String usernamea=intent.getExtras().getString("usernamei");
        String mobilea = intent.getExtras().getString("mobilei");
        String Shopname;
        Logoimage = findViewById(R.id.shoplogobutton);
        Cover = findViewById(R.id.coverphotobutton);
        ShowShopName = findViewById(R.id.shopname);
        ShopnameEditText = findViewById(R.id.shopnameedittext);

        Shopname = ShopnameEditText.getText().toString().trim();
        String key = databaseReference1.push().getKey();

        //Uploads the images


        //Saves Owner's Data
        SaveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ForOwner s_owner = new ForOwner(fullnamea, usernamea, emaila, mobilea, passworda, Shopname);
                databaseReference1.child(key).setValue(s_owner);
                Toast.makeText(getApplicationContext(), "Registration complete", Toast.LENGTH_SHORT);


            }
        });




    }
}