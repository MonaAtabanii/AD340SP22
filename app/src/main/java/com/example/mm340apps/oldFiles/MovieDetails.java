package com.example.mm340apps.oldFiles;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mm340apps.R;
import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_details);

        TextView zmName = findViewById(R.id.m_name);
        TextView zmYear = findViewById(R.id.m_year);
        ImageView zmPic = findViewById(R.id.m_pic);
        TextView zmDirector = findViewById(R.id.m_director);
        TextView zmDescription = findViewById(R.id.m_description);

        Bundle b = this.getIntent().getExtras();
        String[] movie = b.getStringArray("movie");
        zmName.setText(movie[0]);
        zmYear.setText(movie[1]);
        zmDirector.setText(movie[2]);
        Picasso pic = Picasso.get() ;
        pic.setIndicatorsEnabled(true);
        pic.load(movie[3]).into(zmPic);
        zmDescription.setText(movie[4]);
        getSupportActionBar().setTitle(movie[0]+" Details");


    }
}
