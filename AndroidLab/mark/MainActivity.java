package com.example.mark;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText sub1,sub2,sub3;
    TextView result;
    ImageView face;
    Button calculate;
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
        sub1=findViewById(R.id.sub1);
        sub2=findViewById(R.id.sub2);
        sub3=findViewById(R.id.sub3);
        calculate=findViewById(R.id.calculate);
        result=findViewById(R.id.res);
        face=findViewById(R.id.face);
        calculate.setOnClickListener(v->{
            double a = Double.parseDouble(sub1.getText().toString());
            double b = Double.parseDouble(sub2.getText().toString());
            double c = Double.parseDouble(sub3.getText().toString());
            double percent = (a+b+c)/3;
            String res = percent+"%";
            result.setText(res);
            if(percent>50){
                face.setImageResource(R.drawable.happy);
            }else{
                face.setImageResource(R.drawable.sad);
            }
        });
    }
}