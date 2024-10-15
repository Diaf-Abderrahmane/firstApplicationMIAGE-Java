package com.example.firstapplicationmiage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PersonDetailActivity extends AppCompatActivity {

    private TextView textViewName, textViewFirstName, textViewEmail, textViewGender, textViewBirthdate, textViewAddress, textViewPhoneNumber;
    private ImageView imageViewPerson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);

        textViewName = findViewById(R.id.tvResultName);
        textViewFirstName = findViewById(R.id.tvResultFirstName);
        textViewGender= findViewById(R.id.tvResultGender);
        textViewBirthdate = findViewById(R.id.tvResultBirthdate);
        textViewEmail = findViewById(R.id.tvResultEmail);
        textViewAddress = findViewById(R.id.tvResultAddress);
        textViewPhoneNumber = findViewById(R.id.tvResultPhoneNumber);

        imageViewPerson = findViewById(R.id.imageViewPerson);



        // Get the Person object from the Intent
        Intent intent = getIntent();
        Person person = (Person) intent.getSerializableExtra("person");
        String imageUriString = person.getImageUri();




        if (person != null) {
            // Display the details of the Person
            textViewName.setText(person.getName());
            textViewFirstName.setText(person.getFirstName());
            textViewGender.setText(person.getGender());
            textViewBirthdate.setText(person.getBirthDate());
            textViewEmail.setText(person.getEmail());
            textViewAddress.setText(person.getAddress());
            textViewPhoneNumber.setText(person.getPhoneNumber());

            if (imageUriString != null) {
                Uri imageUri = Uri.parse(person.getImageUri());
                imageViewPerson.setImageURI(imageUri); // Set the image to ImageView
            } else {
                imageViewPerson.setImageResource(com.github.dhaval2404.imagepicker.R.drawable.ic_photo_black_48dp); // Set a default image if no URI is provided
            }

        }
    }
}

