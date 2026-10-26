package com.example.customadapter;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    String[] titles={"Phone","Messages","Camera","Settings"};
    int[] images={R.drawable.phone,R.drawable.message,R.drawable.camera,R.drawable.settings};
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
        listView=findViewById(R.id.lview);
        CustomAdapter adapter=new CustomAdapter(this,titles,images);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent,view,position,id)->{
            Toast.makeText(MainActivity.this,titles[position],Toast.LENGTH_SHORT).show();
        });
    }
}