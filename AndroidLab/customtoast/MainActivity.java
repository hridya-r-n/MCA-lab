package com.example.customtoast;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        login=(Button)findViewById(R.id.login);
        login.setOnClickListener((View.OnClickListener) this);
        user = findViewById(R.id.username);
        pwd = findViewById(R.id.pwd);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.login){
            Toast t1;
            if(user.getText().toString().equals("user123")&&pwd.getText().toString().equals("pass1234")) {
                LayoutInflater Lin = getLayoutInflater();
                View appear = Lin.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_id));
                t1 = Toast.makeText(this, "", Toast.LENGTH_LONG);
                t1.setGravity(Gravity.TOP, 0, 200);
                t1.setView(appear);
                t1.show();
            }else{
                Toast.makeText(this,"Invalid Login",Toast.LENGTH_LONG).show();
            }
        }
    }
}