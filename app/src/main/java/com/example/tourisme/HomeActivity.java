package com.example.tourisme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.tourisme.models.TourismeModel;
import com.example.tourisme.adapter.TourismeListAdapter;
import java.util.ArrayList;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    private ListView listTourisme;
    private TourismeListAdapter adapterTourisme;
    private ArrayList<TourismeModel> originalList;
    private ArrayList<TourismeModel> filteredList;


    public ListView getListTourisme() {
        return listTourisme;
    }

    public void setListTourisme(ListView listTourisme) {
        this.listTourisme = listTourisme;
    }

    public TourismeListAdapter getAdapterTourisme() {
        return adapterTourisme;
    }

    public void setAdapterTourisme(TourismeListAdapter adapterTourisme) {
        this.adapterTourisme = adapterTourisme;
    }

    // List View object
    ListView listView;

    // Define array adapter for ListView
    ArrayAdapter<String> adapter;

    // Define array List for List View data
    ArrayList<String> mylist;


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

        this.setAdapterTourisme(new TourismeListAdapter(HomeActivity.this, R.layout.item_tourisme, tourismeArray));
        this.listTourisme.setAdapter(this.getAdapterTourisme());
        this.originalList = tourismeArray;

        this.listTourisme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent detailActivity = new Intent(HomeActivity.this, DetailHomeActivity.class);
                detailActivity.putExtra("title_detail_home", getAdapterTourisme().getItem(position).getTitle());
                startActivity(detailActivity);
            }
        });

        this.getAdapterTourisme().setOnItemClickListener(new TourismeListAdapter.OnItemClickListener() {
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
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem menuItem = menu.findItem(R.id.icon_search_home);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Recherche");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    setAdapterTourisme(new TourismeListAdapter(HomeActivity.this, R.layout.item_tourisme, originalList));
                    listTourisme.setAdapter(getAdapterTourisme());
                    listTourisme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            Intent detailActivity = new Intent(HomeActivity.this, DetailHomeActivity.class);
                            detailActivity.putExtra("title_detail_home", getAdapterTourisme().getItem(position).getTitle());
                            startActivity(detailActivity);
                        }
                    });

                    getAdapterTourisme().setOnItemClickListener(new TourismeListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(TourismeModel tourisme) {
                            Intent detailActivity = new Intent(HomeActivity.this, DetailHomeActivity.class);
                            detailActivity.putExtra("title_detail_home", tourisme.getTitle());
                            startActivity(detailActivity);
                        }
                    });
                } else {
                    filterData(newText);
                }
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void filterData(String query) {
        this.filteredList = new ArrayList<>();
        for (TourismeModel model : originalList) {
            if (model.getTitle().toLowerCase().contains(query.toLowerCase())) {
                this.filteredList.add(model);
            }
        }
        //adapterTourisme.clear();
        //adapterTourisme.addAll(filteredList);
        this.setAdapterTourisme(new TourismeListAdapter(HomeActivity.this, R.layout.item_tourisme, filteredList));
        this.listTourisme.setAdapter(this.getAdapterTourisme());

        this.listTourisme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent detailActivity = new Intent(HomeActivity.this, DetailHomeActivity.class);
                detailActivity.putExtra("title_detail_home", getAdapterTourisme().getItem(position).getTitle());
                startActivity(detailActivity);
            }
        });

        this.getAdapterTourisme().setOnItemClickListener(new TourismeListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TourismeModel tourisme) {
                Intent detailActivity = new Intent(HomeActivity.this, DetailHomeActivity.class);
                detailActivity.putExtra("title_detail_home", tourisme.getTitle());
                startActivity(detailActivity);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            /* case R.id.icon_search_home:
               Intent intentSearch = new Intent(HomeActivity.this,SearchActivity.class);
                startActivity(intentSearch);
                Toast.makeText(HomeActivity.this, "Icon search selected", Toast.LENGTH_LONG).show();
                return true; */
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