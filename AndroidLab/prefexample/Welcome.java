package com.example.prefexample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class Welcome extends AppCompatActivity implements View.OnClickListener {
    Button prev;
    TextView t;
    SharedPreferences sp;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        prev=(Button) findViewById(R.id.previous);
        prev.setOnClickListener(this);
        sp=getSharedPreferences("Data",MODE_PRIVATE);
        t=(TextView) findViewById(R.id.welcome);
        if(sp.contains("Name")&&sp.contains("Email"))
            t.setText("Welcome, "+sp.getString("Name","")+"\n"+sp.getString("Email",""));
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }
}