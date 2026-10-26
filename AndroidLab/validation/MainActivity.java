package com.example.validation;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity implements TextWatcher, View

        .OnClickListener, CompoundButton

        .OnCheckedChangeListener {
    EditText percent;
    CheckBox c;
    LinearLayout ll;
    ToggleButton tb;
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
        percent=findViewById(R.id.num);
        c=findViewById(R.id.cb);
        tb=findViewById(R.id.tb);
        ll=findViewById(R.id.main);
        percent.addTextChangedListener(this);
        c.setOnClickListener(v->{
            if(v.getId()==c.getId())
                if(c.isChecked())
                    Toast.makeText(this,"Terms accepted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this,"Please accept Terms and Conditions",Toast.LENGTH_SHORT).show();
        });
        tb.setOnCheckedChangeListener(this);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        float num=0;
        String s=editable.toString();
        if(s!=null && !s.isEmpty())
            num=Float.parseFloat(s);
        if(num>100)
            editable.replace(0,editable.length(),"100");
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean b) {
        if(b)
            ll.setBackgroundResource(R.drawable.grey);
        else
            ll.setBackgroundResource(R.drawable.white);
    }

    @Override
    public void onClick(View view) {

    }
}