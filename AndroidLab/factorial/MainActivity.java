package com.example.factorial;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText n;
    Button sb;
    TextView res;
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
        sb=(Button) findViewById(R.id.submit);
        sb.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        n=(EditText)findViewById(R.id.num);
        res=(TextView) findViewById(R.id.result);
        int num=Integer.parseInt(String.valueOf(n.getText()));
        int f=1;
        for(int i=1;i<=num;i++) {
            f *= i;
        }
        res.setText(String.valueOf(f));
    }
}