package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class loginto_account extends AppCompatActivity {
Button Sign_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginto_account);
        Sign_btn=(Button) findViewById(R.id.sign_btn);
        Sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(loginto_account.this,sign_in.class);
                startActivity(intent);
            }
        });
    }
}