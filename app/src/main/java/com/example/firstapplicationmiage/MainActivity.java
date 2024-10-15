package com.example.firstapplicationmiage;

import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewPerson;
    private Uri imageUri;

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


        // Check if this is the first launch
        SharedPreferences preferences = getSharedPreferences("PersonListPref", MODE_PRIVATE);
        boolean isFirstLaunch = preferences.getBoolean("is_first_launch", true);

        if (isFirstLaunch) {
            clearPreferences(); // Clear preferences only on the first launch
            preferences.edit().putBoolean("is_first_launch", false).apply(); // Set the flag to false
        }


        imageViewPerson = findViewById(R.id.imageViewPerson);
        Button buttonAddImage = findViewById(R.id.buttonAddImage);

        // Set OnClickListener for ImageView or Button
        buttonAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImagePickerDialog();
            }
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
        imageViewPerson = findViewById(R.id.imageViewPerson);


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

                Person person = new Person(edtName.getText().toString(), edtFirstName.getText().toString(), rbHomme.isChecked() ? "Homme" : "Femme", edtBirthDate.getText().toString(), edtEmail.getText().toString(), edtAddress.getText().toString(), edtPhoneNumber.getText().toString(), imageUri != null ? imageUri.toString() : null);
                personList.add(person);
                PersonListHelper.savePersonList(MainActivity.this, personList);
                Toast.makeText(this, "Personne créée avec succès", Toast.LENGTH_SHORT).show();

                // navigate to persons list
                Intent intent = new Intent(this, PersonsListView.class);
                intent.putExtra("person", person);
                if (imageUri != null) {
                    intent.putExtra("imageUri", imageUri.toString());
                }
                startActivity(intent);

            }
        });
        confirmationBuilder.setNegativeButton("Annuler", (null));

        confirmationBuilder.show();


    }

    private void showImagePickerDialog() {
        // Intent to open camera or gallery
        Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Intent chooserIntent = Intent.createChooser(pickIntent, "Select or Take a new Picture");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { takePhotoIntent });

        launcher.launch(chooserIntent);

    }

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    // Handle image selected from gallery
                    if (result.getData().getData() != null) {
                        imageUri = result.getData().getData();
                        imageViewPerson.setImageURI(imageUri); // Display image in ImageView
                    }
                    // Handle image from camera
                    else if (result.getData().getExtras() != null) {
                        Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                        imageViewPerson.setImageBitmap(bitmap); // Display camera image

                        // Save the image to a file and get its URI
                        Uri tempUri = getImageUriFromBitmap(this, bitmap);
                        imageUri = tempUri;
                    }
                }
            });

    private Uri getImageUriFromBitmap(Activity activity, Bitmap bitmap) {
        // Save bitmap to cache directory
        File tempDir = activity.getCacheDir();
        File tempFile = new File(tempDir, "temp_image.jpg");
        try {
            FileOutputStream fos = new FileOutputStream(tempFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
            return Uri.fromFile(tempFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}