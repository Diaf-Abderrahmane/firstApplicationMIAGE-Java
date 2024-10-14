package com.example.firstapplicationmiage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_two);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        Person person = (Person) intent.getSerializableExtra("person");

        String name = getIntent().getStringExtra("name");
        String firstName = getIntent().getStringExtra("firstName");
        String birthDate = getIntent().getStringExtra("birthDate");
        String email = getIntent().getStringExtra("email");
        String address = getIntent().getStringExtra("address");
        String phoneNumber = getIntent().getStringExtra("phoneNumber");



        TextView tvResultName = findViewById(R.id.tvResultName);
        TextView tvResultFirstName = findViewById(R.id.tvResultFirstName);
        TextView tvResultBirthdate = findViewById(R.id.tvResultBirthdate);
        TextView tvResultEmail = findViewById(R.id.tvResultEmail);
        TextView tvResultAddress = findViewById(R.id.tvResultAddress);
        TextView tvResultPhoneNumber = findViewById(R.id.tvResultPhoneNumber);

        if (person != null) {
            tvResultName.setText(person.getName());
            tvResultEmail.setText(person.getEmail());
            tvResultFirstName.setText(person.getFirstName());
            tvResultBirthdate.setText(person.getBirthDate());
            tvResultAddress.setText(person.getAddress());
            tvResultPhoneNumber.setText(person.getPhoneNumber());
        }



    }
}