package com.example.mm340apps;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;


public class BtnAdapter extends BaseAdapter{
    private Context btnContext;
    String[] btnList;

    public BtnAdapter (Context x, String[] list){
        btnContext = x;
        btnList = list;
    }

    @Override
    public int getCount() {
        return btnList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Button btnView;
        if (view == null) {
            btnView = new Button(btnContext);
        }
        else
        {
            btnView = (Button) view;
        }
        btnView.setText(btnList[i]);
        btnView.setId(i);
        btnView.setOnClickListener(view1 -> {
                Toast t = Toast.makeText(btnContext, btnList[i], Toast.LENGTH_SHORT);
                t.show();
        });

        if (btnView.getText() == "MOVIES") {
            btnView.setOnClickListener(v -> {
                Intent intent = new Intent(btnView.getContext(), Movies.class);
                btnView.getContext().startActivity(intent);
            });
        }
        else if (btnView.getText() == "TRAFFIC") {
            btnView.setOnClickListener(v -> {
                Intent intent = new Intent(btnView.getContext(), CamListActivity.class);
                btnView.getContext().startActivity(intent);
            });
        }

        return btnView;
    }
}

