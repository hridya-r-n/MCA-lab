package com.example.calculator;

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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText n1,n2;
    Button a,s,m,d;
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
        a=(Button) findViewById(R.id.add);
        a.setOnClickListener(this);
        s=(Button) findViewById(R.id.sub);
        s.setOnClickListener(this);
        m=(Button) findViewById(R.id.mul);
        m.setOnClickListener(this);
        d=(Button) findViewById(R.id.div);
        d.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        n1=(EditText) findViewById(R.id.num1);
        n2=(EditText) findViewById(R.id.num2);
        int num1=Integer.parseInt(String.valueOf(n1.getText()));
        int num2=Integer.parseInt(String.valueOf(n2.getText()));
        res=(TextView) findViewById(R.id.res);
        if(view.getId()==R.id.add){
            res.setText(String.valueOf((num1+num2)));
        }
        else if(view.getId()==R.id.sub){
            res.setText(String.valueOf((num1-num2)));
        }
        else if(view.getId()==R.id.mul){
            res.setText(String.valueOf((num1*num2)));
        }
        else if(view.getId()==R.id.div){
            res.setText(String.valueOf((num1/num2)));
        }
    }
}