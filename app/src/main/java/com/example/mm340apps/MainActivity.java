package com.example.mm340apps;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    EditText username;
    Button info, cities, movies, parks, traffic, nusic, food, hw1;
    Toast t;
    String[] btnList = {"CITIES", "MOVIES", "PARKS", "TRAFFIC", "MUSIC", "LOCATION"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = findViewById(R.id.username);
        info = findViewById(R.id.info);
        hw1 = findViewById(R.id.goHW1);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(username)) {
                    t = Toast.makeText(getApplicationContext(), "Enter username", Toast.LENGTH_SHORT);
                    username.setError("username is required");
                    t.show();
                } else {
                    t = Toast.makeText(getApplicationContext(), "user name been sent", Toast.LENGTH_SHORT);
                    t.show();
                }

            }
        });


        hw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HW1.class);
                startActivity(intent);
            }
        });



        GridView btnGridView = (GridView) findViewById(R.id.btn_gridview);
        //final BtnAdapter btnAdapter = new BtnAdapter(this, btnList);
        btnGridView.setAdapter(new BtnAdapter(this, btnList));
    }

    private boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
}


