package com.example.registration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText n,e,p,r;
    RadioGroup gender;
    Button sub;
    SharedPreferences sp;
    String name,em,pwd,roll,gen;
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
        n=(EditText)findViewById(R.id.name);
        e=(EditText) findViewById(R.id.email);
        p=(EditText) findViewById(R.id.password);
        r=(EditText) findViewById(R.id.rollno);
        gender=(RadioGroup) findViewById(R.id.gender);
        sub=(Button) findViewById(R.id.submit);
        sub.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        name = n.getText().toString();
        em= e.getText().toString();
        pwd = p.getText().toString();
        roll= r.getText().toString();
        int selgender=gender.getCheckedRadioButtonId();
        if (selgender == R.id.male) {
            gen = "Male";
        } else {
            gen = "Female";
        }
        Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("email",em);
        intent.putExtra("password",pwd);
        intent.putExtra("rollno",roll);
        intent.putExtra("gender",gen);
        startActivity(intent);

    }
}