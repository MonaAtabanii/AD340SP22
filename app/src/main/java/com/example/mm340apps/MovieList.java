package com.example.mm340apps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieList {
    private String zmName;
    private String zmYear;
    private String zmPic;

    public MovieList(String zmName, String zmYear, String zmPic) {
        this.zmName = zmName;
        this.zmYear = zmYear;
        this.zmPic = zmPic;
    }

    public String getName() {
        return zmName;
    }

    public String getYear() {
        return zmYear;
    }

    public String getPic() {
        return zmPic;
    }


    public void setName(String zmName) {
        this.zmName = zmName;
    }

    public void setYear(String zmYear) {
        this.zmYear = zmYear;
    }

    public void setPic(String zmPic) {
        this.zmPic = zmPic;
    }

}
