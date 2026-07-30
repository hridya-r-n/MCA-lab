package com.example.implicitintend;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button map,play,email,msg;

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
        map=(Button)findViewById(R.id.map);
        map.setOnClickListener(this);
        email=(Button)findViewById(R.id.email);
        email.setOnClickListener(this);
        play=(Button)findViewById(R.id.play);
        play.setOnClickListener(this);
        msg=(Button)findViewById(R.id.msg);
        msg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        android.content.Intent intent,chooser;
        if(view.getId()==R.id.map){
            intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:10.065206,76.629128"));
            chooser=Intent.createChooser(intent,"Launch Map");
            startActivity(chooser);
        }
        if(view.getId()==R.id.play){
            intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=gov.nasa"));
            chooser=Intent.createChooser(intent,"Google Play");
            startActivity(chooser);
        }
        if(view.getId()==R.id.email){
            intent=new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto"));
            String[] to ={"hridyarnair04@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL,to);
            intent.putExtra(Intent.EXTRA_SUBJECT,"From Emulator");
            intent.putExtra(Intent.EXTRA_TEXT,"My First Test Email");
            intent.setType("message/rfc822");
            chooser=Intent.createChooser(intent,"Email Application");
            startActivity(chooser);
        }
        if(view.getId()==R.id.msg){
            intent=new Intent(Intent.ACTION_SEND);
            Uri imgUri=Uri.parse("android.resource://com.example.implicitintents/drawable"+R.drawable.image);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM,imgUri);
            intent.putExtra(Intent.EXTRA_TEXT,"I have attached an image");
            chooser=Intent.createChooser(intent,"Messenger");
            startActivity(chooser);
        }
    }
}