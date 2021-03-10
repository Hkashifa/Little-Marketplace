package com.example.littlemarketplaceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Shop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Intent intent = getIntent();
        String emaila=intent.getExtras().getString("emaili");
        String passworda=intent.getExtras().getString("passwordi");
        String fullnamea=intent.getExtras().getString("fullnamei");
        String usernamea=intent.getExtras().getString("usernamei");
        String mobilea=intent.getExtras().getString("mobilei");


    }
}