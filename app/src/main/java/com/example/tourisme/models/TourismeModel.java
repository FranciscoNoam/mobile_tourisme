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
import java.util.Date;
import java.util.List;

public class TourismeModel{

    private String title;
    private String shortDescription;
    private Date date_publish;
    private  String url_image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Date getDate_publish() {
        return date_publish;
    }

    public void setDate_publish(Date date_publish) {
        this.date_publish = date_publish;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public TourismeModel(){}
    public TourismeModel(String title, String shortDescription, Date date_publish, String url_image) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.date_publish = date_publish;
        this.url_image = url_image;
    }
}

