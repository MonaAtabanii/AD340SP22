package com.example.mm340apps.oldFiles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mm340apps.R;
import com.squareup.picasso.Picasso;

public class MoviesAdapter_old extends RecyclerView.Adapter<MoviesAdapter_old.MyViewHolder> {

    Context context;
    String[][] zmList;

    public MoviesAdapter_old(Context context, String[][] zmList) {
        this.context = context;
        this.zmList = zmList;
    }

    public MoviesAdapter_old(String[][] zmList) {
        this.zmList = zmList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View v = LayoutInflater.from(context).inflate(R.layout.activity_movies_list, parent, false);

        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.zmName.setText(zmList[i][0]);
        holder.zmYear.setText(zmList[i][1]);
        Picasso img = Picasso.get();
        img.load(zmList[i][3]).into(holder.zmPic);

    }

    @Override
    public int getItemCount() {
        return zmList.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView zmName;
        TextView zmYear;
        ImageView zmPic;

        public MyViewHolder(@NonNull View v) {
            super(v);
            zmPic = v.findViewById(R.id.m_pic);
            zmName = v.findViewById(R.id.m_name);
            zmYear = v.findViewById(R.id.m_year);
        }
    }

   /* v.setOnClickListener(view -> {
        Bundle b = new Bundle();
        b.putStringArray("movie", new String[]{
                zmList[holder.v.getAdapterPosition()][0],
                zmList[holder.getAdapterPosition()][1],
                zmList[holder.getAdapterPosition()][2],
                zmList[holder.getAdapterPosition()][3],
                zmList[holder.getAdapterPosition()][4]});
        Intent intent = new Intent(holder.v.getContext(), MovieDetails.class);
        intent.putExtras(b);
        view.getContext().startActivity(intent);
    });*/
}
