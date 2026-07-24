package com.example.loginvalidation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText user,pwd;
    Button login;
    String u="user123";
    String p="1234";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        login=(Button)findViewById(R.id.loginButton);
        login.setOnClickListener((View.OnClickListener) this);
    }
    public void onClick(View v){
        if(v.getId()==R.id.loginButton){
            user=(EditText) findViewById(R.id.username);
            pwd=(EditText) findViewById(R.id.password);
            String a=user.getText().toString();
            String b=pwd.getText().toString();
            if(a.equals(u)&&b.equals(p))
                Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_LONG).show();
        }
    }
}