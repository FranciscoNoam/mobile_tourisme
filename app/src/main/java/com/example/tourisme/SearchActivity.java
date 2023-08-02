package com.example.tourisme;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tourisme.models.TourismeModel;
import com.example.tourisme.adapter.TourismeListAdapter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class SearchActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listTourisme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        this.listTourisme = findViewById(R.id.list_tourisme_search);
        ArrayList<TourismeModel> tourismeArray = new ArrayList();
        tourismeArray.add(new TourismeModel("Post 1", "Lorem upsum comme description court", new Date(), ""));
        tourismeArray.add(new TourismeModel("Post 2", "Lorem upsum comme description court", new Date(), ""));
        tourismeArray.add(new TourismeModel("Post 3", "Lorem upsum comme description court", new Date(), ""));
        tourismeArray.add(new TourismeModel("Post 4", "Lorem upsum comme description court", new Date(), ""));
        tourismeArray.add(new TourismeModel("Post 5", "Lorem upsum comme description court", new Date(), ""));
        tourismeArray.add(new TourismeModel("Post 6", "Lorem upsum comme description court", new Date(), ""));

        TourismeListAdapter adapterTourisme = new TourismeListAdapter(SearchActivity.this, R.layout.item_tourisme, tourismeArray);
        this.listTourisme.setAdapter(adapterTourisme);

        this.listTourisme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent detailActivity = new Intent(SearchActivity.this, DetailHomeActivity.class);
                detailActivity.putExtra("title_detail_home", adapterTourisme.getItem(position).getTitle());
                startActivity(detailActivity);
            }
        });

        adapterTourisme.setOnItemClickListener(new TourismeListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TourismeModel tourisme) {
                Intent detailActivity = new Intent(SearchActivity.this, DetailHomeActivity.class);
                detailActivity.putExtra("title_detail_home", tourisme.getTitle());
                startActivity(detailActivity);
            }
        });


        registerForContextMenu(this.listTourisme);
    }



}