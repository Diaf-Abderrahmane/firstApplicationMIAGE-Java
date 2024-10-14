package com.example.firstapplicationmiage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PersonDetailActivity extends AppCompatActivity {

    private TextView textViewName, textViewFirstName, textViewEmail, textViewGender, textViewBirthdate, textViewAddress, textViewPhoneNumber;

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


        // Get the Person object from the Intent
        Intent intent = getIntent();
        Person person = (Person) intent.getSerializableExtra("person");

        if (person != null) {
            // Display the details of the Person
            textViewName.setText(person.getName());
            textViewFirstName.setText(person.getFirstName());
            textViewGender.setText(person.getGender());
            textViewBirthdate.setText(person.getBirthDate());
            textViewEmail.setText(person.getEmail());
            textViewAddress.setText(person.getAddress());
            textViewPhoneNumber.setText(person.getPhoneNumber());
        }
    }
}

