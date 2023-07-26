package com.example.tourisme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tourisme.models.TourismeModel;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String email_home = getIntent().getStringExtra("email");
        ListView listTourisme = findViewById(R.id.list_tourisme);


        ArrayList<String> postArray = new ArrayList<String>();
        postArray.add("Post 1");
        postArray.add("Post 2");
        postArray.add("Post 3");
        postArray.add("Post 4");

        // on adapte la liste par rapport à la list view
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(Home.this,android.R.layout.simple_list_item_1,postArray);
        TourismeModel adapterTourisme = new TourismeModel(Home.this, R.layout.item_tourisme,postArray);

        listTourisme.setAdapter(adapterTourisme);

       /*
        TextView title_home = findViewById(R.id.title_home);
        title_home.setText("Bienvenu "+email_home);*/
    }
}