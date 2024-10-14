package com.example.firstapplicationmiage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityThree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_three);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvMessage = findViewById(R.id.tvMessage);
        Button button = findViewById(R.id.button);

        String message = getIntent().getStringExtra("message");

        tvMessage.setText("Hi " + message + " !" + " This message was sent from MainActivity");
    }

    @SuppressLint("DefaultLocale")
    public void btnToMainActivity(View view) {
        EditText edtMessage = findViewById(R.id.edtMessage);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("message", edtMessage.getText().toString());
        startActivity(intent);
        finish();
    }
}