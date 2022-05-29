package com.example.mm340apps;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.widget.ListView;
import androidx.annotation.Nullable;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CamListActivity extends AppCompatActivity {

    ListView camList;
    CamListAdapter camAdapter;
    ArrayList<Camera> cams = new ArrayList<>();
    String camInfo = "https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_cams_list);

        camList = findViewById(R.id.list);
        camAdapter = new CamListAdapter(this, cams);
        camList.setAdapter(camAdapter);

        if (checkConnection()) {
            Log.v("myTag", "Connected");
            getCamInfo(camInfo);
        } else {
            Log.v("myTag", "No Connection");
            Toast.makeText(getApplicationContext(), "Faild, No connection you need to connet to the internet", Toast.LENGTH_SHORT).show();

        }
    }

    private void getCamInfo(String camInfo) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, camInfo, null, response -> {
            Log.v("Cameras", response.toString());

            try {
                JSONArray camArray = response.getJSONArray("Features");
                Log.v("camArray", camArray.toString());

                for (int idx = 1; idx < camArray.length(); idx++) {
                    JSONObject data = camArray.getJSONObject(idx);
                    Log.v("Cams Data", data.toString());//camData
                    JSONArray coordination = data.getJSONArray("PointCoordinate");
                    double[] camCoordinates = {coordination.getDouble(0), coordination.getDouble(1)};
                    JSONArray item = data.getJSONArray("Cameras");
                    Camera cam = new Camera(item.getJSONObject(0).getString("Id"),
                            item.getJSONObject(0).getString("Description"),
                            item.getJSONObject(0).getString("ImageUrl"),
                            camCoordinates);
                    Log.v("Camera ", cam.camPicUrl);
                    cams.add(cam);
                }
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
            Log.v("camArray", "showing cams list" + cams);
            camAdapter.notifyDataSetChanged();
        }, error -> {
            Log.d("JSON", "Error: " + error.getMessage());
            Snackbar.make(camList,
                    "Error...",
                    Snackbar.LENGTH_LONG
            ).show();
        });
        requestQueue.add(jsonObjectRequest);
    }

    private boolean checkConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

}