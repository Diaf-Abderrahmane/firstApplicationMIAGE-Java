package com.example.firstapplicationmiage;

import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.dhaval2404.imagepicker.ImagePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    final Calendar myCalendar= Calendar.getInstance();
    EditText edtBirthDate;
    private List<Person> personList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });

        personList = PersonListHelper.loadPersonList(this);


//        ImagePicker.with(this)
//                .crop()	    			//Crop image(Optional), Check Customization for more option
//                .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
//                .start();

        edtBirthDate=(EditText) findViewById(R.id.edtBirthDate);

        if (getIntent().hasExtra("message")) {
            EditText edtName = findViewById(R.id.edtName);
            String message = getIntent().getStringExtra("message");
            edtName.setText("Hi " + message + " !" + " This message was sent from ActivityThree");
        }

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        edtBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void clearPreferences() {
        // Get SharedPreferences
        SharedPreferences preferences = getSharedPreferences("PersonListPref", MODE_PRIVATE);

        // Clear all data in SharedPreferences
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply(); // or editor.commit(); if you want it to be synchronous
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK) {
//            //Image Uri will not be null for RESULT_OK
//            Uri uri = data != null ? data.getData() : null;
//
//            if (uri != null) {
//                imgProfile.setImageURI(uri);
//            }
//        } else if (resultCode == ImagePicker.RESULT_ERROR) {
//            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
//        }
//    }

    // Birthdate
    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        edtBirthDate.setText(dateFormat.format(myCalendar.getTime()));
    }

    @Override
    protected void onResume() {
        super.onResume();

        EditText edtName = findViewById(R.id.edtName);
        EditText edtFirstName = findViewById(R.id.edtFirstName);
        RadioButton rbHomme = findViewById(R.id.rbHomme);
        RadioButton rbFemme = findViewById(R.id.rbFemme);
        EditText edtBirthDate = findViewById(R.id.edtBirthDate);
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtAddress = findViewById(R.id.edtAddress);
        EditText edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        Button bouton = findViewById(R.id.button);
        TextView tvHello = findViewById(R.id.tvHello);

        edtName.setText("");
        edtFirstName.setText("");
        rbHomme.setChecked(false);
        rbFemme.setChecked(false);
        edtBirthDate.setText("");
        edtEmail.setText("");
        edtAddress.setText("");
        edtPhoneNumber.setText("");
    }

    @SuppressLint("DefaultLocale")
    public void btnToActivityTwo(View view) {

        EditText edtName = findViewById(R.id.edtName);
        EditText edtFirstName = findViewById(R.id.edtFirstName);
        RadioButton rbHomme = findViewById(R.id.rbHomme);
        RadioButton rbFemme = findViewById(R.id.rbFemme);
        EditText edtBirthDate = findViewById(R.id.edtBirthDate);
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtAddress = findViewById(R.id.edtAddress);
        EditText edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        Button bouton = findViewById(R.id.button);
        TextView tvHello = findViewById(R.id.tvHello);


        // 1. Instantiate an AlertDialog.Builder with its constructor.
        AlertDialog.Builder confirmationBuilder = new AlertDialog.Builder(this);

// 2. Chain together various setter methods to set the dialog characteristics.
        confirmationBuilder.setMessage(String.format("Name : %s\nFirst Name : %s", edtName.getText().toString(), edtFirstName.getText().toString()))
                .setTitle(R.string.dialog_title);

        confirmationBuilder.setPositiveButton("Valider", (dialog, which) -> {
            if (edtName.getText().toString().isBlank() || edtFirstName.getText().toString().isBlank() || edtBirthDate.getText().toString().isBlank() || edtEmail.getText().toString().isBlank() || edtAddress.getText().toString().isBlank() || edtPhoneNumber.getText().toString().isBlank()) {
                AlertDialog.Builder fillAgainBuilder = new AlertDialog.Builder(this);
                fillAgainBuilder.setTitle("Erreur");
                fillAgainBuilder.setMessage("Veuillez remplir tous les champs");
                fillAgainBuilder.setIcon(R.drawable.img);
                fillAgainBuilder.show();


            } else if (!Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches()) {

                edtEmail.setError("Veuillez entrer une adresse email valide");
            } else {
                Person person = new Person(edtName.getText().toString(), edtFirstName.getText().toString(), rbHomme.isChecked() ? "Homme" : "Femme", edtBirthDate.getText().toString(), edtEmail.getText().toString(), edtAddress.getText().toString(), edtPhoneNumber.getText().toString());
                personList.add(person);
                PersonListHelper.savePersonList(MainActivity.this, personList);
                Toast.makeText(this, "Personne créée avec succès", Toast.LENGTH_SHORT).show();

                // navigate to persons list
                Intent intent = new Intent(this, PersonsListView.class);
                intent.putExtra("person", person);

                startActivity(intent);

            }
        });
        confirmationBuilder.setNegativeButton("Annuler", (null));

        confirmationBuilder.show();


    }

    @SuppressLint("DefaultLocale")
    public void btnToPersonsList(View view) {
//        EditText edtName = findViewById(R.id.edtName);
//        EditText edtFirstName = findViewById(R.id.edtFirstName);
//        EditText edtEmail = findViewById(R.id.edtEmail);
//
//        Person person = new Person(edtName.getText().toString(), edtFirstName.getText().toString(), null , null, edtEmail.getText().toString(), null, null);
//
//        personList.add(person);
//        PersonListHelper.savePersonList(MainActivity.this, personList);
//
//
//        // navigate to persons list
//        Intent intent = new Intent(this, PersonsListView.class);
//        intent.putExtra("person", person);
//
//        startActivity(intent);
    }

}