package com.example.tourisme;

import androidx.annotation.*;
import androidx.appcompat.app.*;
import androidx.preference.PreferenceManager;

import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import com.example.tourisme.fragment.*;

public class HomeActivity extends AppCompatActivity  implements CategorieFragment.OnCategoryClickListener, SousCategorieFragment.OnSousCategoryClickListener {

    private SiteTouristiqueFragment siteTouristiqueFragment;
    private CategorieFragment categorieFragment;

    private String currentCategorieName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isDarkThemeEnabled = sharedPreferences.getBoolean("theme_preference", false);

        // Appliquez le thème approprié en fonction de la préférence
        if (isDarkThemeEnabled) {
            setTheme(R.style.AppTheme_Dark);
        } else {
            setTheme(R.style.AppTheme);
        }
*/
        setContentView(R.layout.activity_home);

        loadCategorieFragment();
       // onSousCategoryClickListener("Region ","1");

    } // end onCreate


    private void loadCategorieFragment() {
        CategorieFragment categorieFragment = new CategorieFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_home, categorieFragment).commit();
    }

    /*public void filterFragment(String query) {
        if (siteTouristiqueFragment != null) {
            siteTouristiqueFragment.filter(query);
        }
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

       /* MenuItem menuItem = menu.findItem(R.id.icon_search_home);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Recherche");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterFragment(newText);
                return true;
            }
        }); */
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            // case R.id.icon_search_home:
            //   Intent intentSearch = new Intent(HomeActivity.this,SearchActivity.class);
            //    startActivity(intentSearch);
            //    Toast.makeText(HomeActivity.this, "Icon search selected", Toast.LENGTH_LONG).show();
            //    return true;
            case R.id.icon_setting_home:
                Toast.makeText(HomeActivity.this, "Icon Configuration selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.icon_logout_home:
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

    @Override
    public void onCategoryClick(String categoryName,String id) {
        getSupportActionBar().setTitle(categoryName);

        SousCategorieFragment sousCategorieFragment = new SousCategorieFragment();
        // Pass the category name to SousCategorieFragment
        Bundle bundle = new Bundle();
        bundle.putString("categoryName", categoryName);
        bundle.putString("categoryId", id);
        sousCategorieFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_home, sousCategorieFragment).addToBackStack(null).commit();
       // currentCategorieName = categoryName;

    }


    @Override
    public void onSousCategoryClick(String categoryName, String id) {
        getSupportActionBar().setTitle(categoryName);

        System.out.println("Tonga click name: "+categoryName+"  id: "+id);
        SiteTouristiqueFragment siteTouristique = new SiteTouristiqueFragment();
        // Pass the category name to SousCategorieFragment
        Bundle bundle = new Bundle();
        bundle.putString("sousCategoryName", categoryName);
        bundle.putString("sousCategoryId", id);
        siteTouristique.setArguments(bundle);

          getSupportFragmentManager().beginTransaction().replace(R.id.frame_home, siteTouristique).addToBackStack(null).commit();

    }
}