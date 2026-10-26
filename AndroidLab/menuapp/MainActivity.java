package com.example.menuapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
    }
    @Override

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.fact){
            showFactorial();
            return true;
        }
        if(item.getItemId()==R.id.gender) {
            showGender();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void showFactorial(){
        setContentView(R.layout.activity_factorial);
        EditText num=findViewById(R.id.num);
        Button btn=findViewById(R.id.btn);
        btn.setOnClickListener(v->{
            int n=Integer.parseInt(num.getText().toString());
            long fact=1;
            for(int i=1;i<=n;i++)
                fact*=i;
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Factorial of "+n+" is "+fact);
            builder.setPositiveButton("YES",null);
            builder.setNegativeButton("NO",null);
            AlertDialog a=builder.create();
            a.show();
        });
    }
    void showGender(){
        setContentView(R.layout.activity_gender);
        RadioGroup gen=findViewById(R.id.rg);
        ImageView profile=findViewById(R.id.profile);
        gen.setOnCheckedChangeListener((g,checked)->{
            if(checked==R.id.male)
                profile.setImageResource(R.drawable.male);
            else
                profile.setImageResource(R.drawable.female);
        });
    }
}