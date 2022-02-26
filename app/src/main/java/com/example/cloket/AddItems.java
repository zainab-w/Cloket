package com.example.cloket;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

//import com.github.dhaval2404.imagepicker.ImagePicker;

public class AddItems extends AppCompatActivity {
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    Button additembtn;
    EditText  itemDescTV, itemNameTV;
    TextView date;
    ImageView selectedImg;
    Uri image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        setTitle("Add New Items");

        //btnView = findViewById(R.id.btnView);
        additembtn = findViewById(R.id.additembtn);
        selectedImg = findViewById(R.id.selectedImg);
        itemNameTV = findViewById(R.id.itemNameTV);
        itemDescTV = findViewById(R.id.itemDescTV);
        date = findViewById(R.id.date);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Items");
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendar();
            }
        });

        selectedImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });



        additembtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                FirebaseStorage storage = FirebaseStorage.getInstance();

                StorageReference imageRef = storage.getReference().child("images/" + image.getLastPathSegment()) ;
                UploadTask uploadTask = imageRef.putFile(image);

                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {

                        String error = exception.getLocalizedMessage();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri)
                            {
                                String strName = itemNameTV.getText().toString().trim();
                                String strDescription = itemDescTV.getText().toString().trim();
                                String strDate = date.getText().toString().trim();
                                String strImage = uri.toString().trim();
                                HashMap<String, Object> hashMap = new HashMap<>();

                                hashMap.put("item name","" + strName);
                                hashMap.put("item description","" + strDescription);
                                hashMap.put("item date added","" + strDate);
                                hashMap.put("item image","" + strImage);

                                DatabaseReference ref = FirebaseDatabase.getInstance()
                                        .getReference("Items");
                                ItemsModel model = new ItemsModel(strName, strDescription, strDate, strImage);
                                ref.child(""+strName).setValue(hashMap)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(AddItems.this,"Item : "  + strName + "saved!",
                                                        Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(AddItems.this, ImageView.class));
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(AddItems.this,
                                                        "Items added unsuccessfully!"
                                                        ,Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                //FirebaseDatabase database = FirebaseDatabase.getInstance();
                                //DatabaseReference myRef = database.getReference("Items");
//                                myRef.child(new Date().getTime() + "").setValue(model);



                            }
                        });

                    }
                });

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            image = data.getData();
            selectedImg.setImageURI(image);

        }
    }
    //https://github.com/Dhaval2404/ImagePicker
    //https://github.com/bumptech/glide
    //https://firebase.google.com/docs/reference/unity/class/firebase/storage/storage-exception

    //uploading img
    //https://firebase.google.com/docs/storage/android/upload-files#upload_from_a_local_file
    private void selectImage()
    {
        ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    private void showCalendar() {
        final DatePickerDialog calendar = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date.setText(month + "/" + dayOfMonth + "/" + year);
            }
        }, 2021, 0, 0);
        calendar.show();
    }
    //https://www.youtube.com/watch?v=yp0ZahAXbzo


    @Override
    public void setTitle(CharSequence title)
    {
        super.setTitle(title);
        TextView tv = new TextView(this);
        tv.setText(title);
    }


}