package com.example.mm340apps;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

public class MoviesAdapter extends ArrayAdapter<String[]> {

    private Context context;
    private String[][] zmList;
    public MoviesAdapter(@NonNull Context context, String[][] movies) {
        super(context, -1, movies);
        this.context = context;
        this.zmList = movies;
    }

    @NonNull
    @Override
    public View getView(int i, @Nullable View myView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_movies_list, parent, false);

        TextView zmName = v.findViewById(R.id.m_name);
        TextView zmYear = v.findViewById(R.id.m_year);
        ImageView zmPic = v.findViewById(R.id.m_pic);

        zmName.setText(zmList[i][0]);
        zmYear.setText(zmList[i][1]);
        Picasso.get().load(zmList[i][3]).placeholder(R.drawable.ic_launcher_foreground).into(zmPic);

        v.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), MoviesDetails.class);
            intent.putExtra("Movie", zmList[i]);
            v.getContext().startActivity(intent);
        });
        return v;
    }
}