package com.example.tourisme.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.tourisme.R;

import java.util.ArrayList;
import java.util.List;

public class TourismeModel extends ArrayAdapter<String> {
    private ArrayList<String> values;
    private  int resource;
    private Context context;

    public void setValues(ArrayList<String> v){
        this.values =v;
    }

    public void setResource(int res){
        this.resource = res;
    }
    public void setContext(Context contex){
        this.context = contex;
    }
    public ArrayList<String> getValues(){
        return this.values;
    }
    public int getResource(){
        return this.resource;
    }

    public Context getContext(){
        return this.context;
    }
    public TourismeModel(@NonNull Context context, int resource, ArrayList<String> values) {
        super(context, resource,values);
        this.setContext(context);
        this.setResource(resource);
        this.setValues(values);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String tourisme_title= this.getValues().get(position);

        View tourisme_item_view = LayoutInflater.from(this.getContext()).inflate(this.getResource(),parent,false);
        TextView title_detail = tourisme_item_view.findViewById(R.id.title_detail_list);

       title_detail.setText(""+tourisme_title);

        return tourisme_item_view;
    }
}

