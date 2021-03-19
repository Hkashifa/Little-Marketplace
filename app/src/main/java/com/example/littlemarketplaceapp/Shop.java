package com.example.littlemarketplaceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Shop extends AppCompatActivity {

    private ImageButton Logoimage;
    private ImageButton Cover;
    private EditText ShopnameEditText;
    private TextView ShowShopName;
    private Button SaveButton;
    DatabaseReference databaseReference1;
    private FirebaseAuth mAuth;

    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    StorageReference storageReference1 = FirebaseStorage.getInstance().getReference();
    Uri Logo;
    Uri coverUri;
    private Uri uri;
    boolean coverOrLogo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Intent intent = getIntent();
        String emaila = intent.getExtras().getString("emaili");
        String passworda = intent.getExtras().getString("passwordi");
        String fullnamea = intent.getExtras().getString("fullnamei");
        String usernamea = intent.getExtras().getString("usernamei");
        String mobilea = intent.getExtras().getString("mobilei");
        String Shopname;
        Logoimage = findViewById(R.id.shoplogobutton);
        Cover = findViewById(R.id.coverphotobutton);
        ShowShopName = findViewById(R.id.shopname);
        ShopnameEditText = findViewById(R.id.shopnameedittext);

        Shopname = ShopnameEditText.getText().toString().trim();
        String key = databaseReference1.push().getKey();

        //Saves Owner's Data
        SaveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ForOwner s_owner = new ForOwner(fullnamea, usernamea, emaila, mobilea, passworda, Shopname);
                databaseReference1.child(key).setValue(s_owner);
                Toast.makeText(getApplicationContext(), "Registration complete", Toast.LENGTH_SHORT);


            }
        });
        //Uploads the Logo
        Logoimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open Gallery
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);
            }
        });
        @Override
        protected void onActivityResult ( int requestCode, int resultCode,
        @androidx.annotation.Nullable Intent data)
        {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 1000) {
                Uri imageUri1 = data.getData();
                Logoimage.setImageURI(imageUri1);
                uploadImageToFirebase(imageUri1, 1);
            }
        }

        //Uploads the Cover photo
        Cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open Gallery
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 2000);
            }
        });
        @Override
        protected void onActivityResult ( int requestCode, int resultCode,
        @androidx.annotation.Nullable Intent data)
        {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 2000) {
                Uri imageUri2 = data.getData();
                Cover.setImageURI(imageUri2);
                uploadImageToFirebase(imageUri2, 0);
            }
        }


    }

    private void uploadImageToFirebase(Uri imageUri1, boolean coverOrLogo) {
        //upload image to firebase
        StorageReference fileRef;
        if (coverOrLogo == 1) {
            fileRef = storageReference.child("logo.jpg");
        }
        if (coverOrLogo == 0) {
            fileRef = storageReference.child("cover.jpg");
        }

        fileRef.putFile(imageUri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.putFile(imageUri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        public void onSuccess (Uri uri)
                        {
                            if (coverOrLogo == 1) {
                                Picasso.get().load(uri).into(Logo);
                            }
                            if (coverOrLogo == 0) {
                                Picasso.get().load(uri).into(Cover);
                            }
                        }
                    }
                })
                Toast.makeText(Shop.this, "ImageUploaded", Toast.LENGTH_SHORT);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Shop.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
            }
        })
    }
}