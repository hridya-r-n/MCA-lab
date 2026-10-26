package com.example.registration;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DisplayActivity extends AppCompatActivity {

    TextView tvName, tvEmail, tvPassword, tvRollNo, tvGender;
    ImageView ivProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display); // Make sure this matches your layout file name

        // Window Insets for Edge-to-Edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Link your variables to the IDs in your XML layout
        tvName = findViewById(R.id.displayName);       // Replace with your actual XML IDs
        tvEmail = findViewById(R.id.displayEmail);
        tvPassword = findViewById(R.id.displayPassword);
        tvRollNo = findViewById(R.id.displayRollNo);
        tvGender = findViewById(R.id.displayGender);
        ivProfile = findViewById(R.id.profilePic);

        // 2. Receive the Intent and extract the data using the exact keys from MainActivity
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");
        String rollno = intent.getStringExtra("rollno");
        String gender = intent.getStringExtra("gender");

        // 3. Set the text on your TextViews
        tvName.setText("Name: " + name);
        tvEmail.setText("Email: " + email);
        tvPassword.setText("Password: " + password);
        tvRollNo.setText("Roll No: " + rollno);
        tvGender.setText("Gender: " + gender);

        // 4. Set the ImageView based on the gender
        if (gender != null) {
            if (gender.equals("Male")) {
                // Ensure your image is in res/drawable and named exactly like this
                ivProfile.setImageResource(R.drawable.male_pic);
            } else if (gender.equals("Female")) {
                ivProfile.setImageResource(R.drawable.female_pic);
            }
        }
    }
}