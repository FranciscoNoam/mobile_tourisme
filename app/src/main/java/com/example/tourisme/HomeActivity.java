package com.example.tourisme;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

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

public class HomeActivity extends AppCompatActivity {

    private ListView listTourisme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.listTourisme = findViewById(R.id.list_tourisme);
        ArrayList<TourismeModel> tourismeArray = new ArrayList();
        tourismeArray.add(new TourismeModel("Post 1", "Lorem upsum comme description court", new Date(), ""));
        tourismeArray.add(new TourismeModel("Post 2", "Lorem upsum comme description court", new Date(), ""));
        tourismeArray.add(new TourismeModel("Post 3", "Lorem upsum comme description court", new Date(), ""));
        tourismeArray.add(new TourismeModel("Post 4", "Lorem upsum comme description court", new Date(), ""));
        tourismeArray.add(new TourismeModel("Post 5", "Lorem upsum comme description court", new Date(), ""));
        tourismeArray.add(new TourismeModel("Post 6", "Lorem upsum comme description court", new Date(), ""));

        TourismeListAdapter adapterTourisme = new TourismeListAdapter(HomeActivity.this, R.layout.item_tourisme, tourismeArray);
        this.listTourisme.setAdapter(adapterTourisme);

        this.listTourisme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent detailActivity = new Intent(HomeActivity.this, DetailHomeActivity.class);
                detailActivity.putExtra("title_detail_home", adapterTourisme.getItem(position).getTitle());
                startActivity(detailActivity);
            }
        });

        adapterTourisme.setOnItemClickListener(new TourismeListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TourismeModel tourisme) {
                Intent detailActivity = new Intent(HomeActivity.this, DetailHomeActivity.class);
                detailActivity.putExtra("title_detail_home", tourisme.getTitle());
                startActivity(detailActivity);
            }
        });


        registerForContextMenu(this.listTourisme);

    } // end onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.icon_search_home:
                Toast.makeText(HomeActivity.this, "Icon search selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.icon_setting_home:
                Toast.makeText(HomeActivity.this, "Icon Configuration selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.icon_logout_home:
                //finish();
                //Toast.makeText(HomeActivity.this,"Icon Se déconnecter selected",Toast.LENGTH_LONG).show();
                showLogoutConfirmDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showLogoutConfirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Confirmation");
        builder.setMessage("Etes-vous sur de vouloir quitter l'appli ?");
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor=  HomeActivity.this.getSharedPreferences("app_state", Context.MODE_PRIVATE).edit();
                editor.remove("is_authentificated").apply();
                finish();
            }
        });

        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setNeutralButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}