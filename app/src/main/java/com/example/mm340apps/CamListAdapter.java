package com.example.mm340apps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class CamListAdapter extends ArrayAdapter<Camera> {
private final Context context;
private ArrayList<Camera> tCams;

        public CamListAdapter(@NonNull Context context, @NonNull ArrayList<Camera> tCams) {
                super(context, -1, tCams);
                this.context = context;
                this.tCams = tCams;
        }

        @NonNull
        @Override
        public View getView(int i, @Nullable View v, @NonNull ViewGroup parent) {
                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.activity_traffic_cam, parent, false);
                TextView camId = view.findViewById(R.id.camId);
                camId.setText(context.getString(R.string.camId) + tCams.get(i).camId);
                ImageView camPic = view.findViewById(R.id.camPic);
                TextView camDesc = view.findViewById(R.id.camDescription);
                String camPicUrl = "https://www.seattle.gov/trafficcams/images/" + tCams.get(i).camPicUrl;
                Picasso.get().load(camPicUrl).placeholder(R.drawable.ic_launcher_foreground).into(camPic);
                camDesc.setText(tCams.get(i).camDescription);
                System.out.println(tCams);
        return view;

        }


/*public class TrafficCamListAdapter extends RecyclerView.Adapter<TrafficCamListAdapter.ViewHolder> {

    ArrayList<Camera> cameras;

    public TrafficCamListAdapter(ArrayList<Camera> camData) {
        cameras = camData;
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView camLocation;
        private final ImageView camImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            camLocation = itemView.findViewById(R.id.trafficCam_location);
            camImage = itemView.findViewById(R.id.trafficCam_img);
        }

        public TextView getCamLocation() {
            return camLocation;
        };

        public ImageView getCamImage() {
            return camImage;
        };
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_cams, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrafficCamListAdapter.ViewHolder holder, int position) {
        holder.getCamLocation().setText(cameras.get(position).getDescription());

        Picasso.get()
                .load(cameras.get(position).getImageUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.getCamImage());
        // Log.d("CAMERA URL: ", cameras.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return cameras.size();
    }*/
}