package com.example.firstapplicationmiage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersonsListView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PersonAdapter personAdapter;
    private List<Person> personList;
    private TextView tvNoContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_persons_list_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainn), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences sharedPreferences = getSharedPreferences("PersonListPref", MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();
        tvNoContacts = findViewById(R.id.tvNoContacts);

        if (allEntries.isEmpty()) {
            // SharedPreferences is empty
            tvNoContacts.setVisibility(View.VISIBLE);
        } else {
            tvNoContacts.setVisibility(View.INVISIBLE);
        }



        recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton fab = findViewById(R.id.fab);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load the person list from SharedPreferences
        personList = PersonListHelper.loadPersonList(this);


        // Initialize and set the adapter
        personAdapter = new PersonAdapter(personList, this);
        recyclerView.setAdapter(personAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Add Person Activity
                Intent intent = new Intent(PersonsListView.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }




}