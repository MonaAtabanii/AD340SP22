package com.example.mm340apps;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class MoviesDetails extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_details);

        TextView zmName = findViewById(R.id.m_name);
        TextView zmYear = findViewById(R.id.m_year);
        ImageView zmPic = findViewById(R.id.m_pic);
        TextView zmDirector = findViewById(R.id.m_director);
        TextView zmDescription = findViewById(R.id.m_description);

        String[] movieDetails = getIntent().getStringArrayExtra("Movie");

        zmName.setText(movieDetails[0]);
        zmYear.setText(movieDetails[1]);
        Picasso.get().load(movieDetails[3]).placeholder(R.drawable.ic_launcher_foreground).into(zmPic);
        zmDirector.setText("Director: " + movieDetails[2]);
        zmDescription.setText(movieDetails[4]);
        zmDescription.setMovementMethod(new ScrollingMovementMethod());
    }
}