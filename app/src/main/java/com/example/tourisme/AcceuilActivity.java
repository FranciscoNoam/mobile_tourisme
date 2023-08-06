package com.example.tourisme;


import androidx.annotation.*;
import androidx.appcompat.app.*;
import androidx.appcompat.widget.SearchView;
import androidx.preference.PreferenceManager;

import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import com.example.tourisme.fragment.*;

public class AcceuilActivity extends AppCompatActivity   implements CategorieFragment.OnCategoryClickListener, SousCategorieFragment.OnSousCategoryClickListener{


    private SiteTouristiqueFragment siteTouristiqueFragment;
    private CategorieFragment categorieFragment;

    private String currentCategorieName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isDarkThemeEnabled = sharedPreferences.getBoolean("theme_preference", false);

        // Appliquez le thème approprié en fonction de la préférence
        if (isDarkThemeEnabled) {
            setTheme(R.style.AppTheme_Dark);
        } else {
            setTheme(R.style.AppTheme);
        }
*/
        loadCategorieFragment();

        setContentView(R.layout.activity_acceuil);


    } // end onCreate


    private void loadCategorieFragment() {
        CategorieFragment categorieFragment = new CategorieFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_acceuil, categorieFragment).commit();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

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
                Intent intentSearch = new Intent(AcceuilActivity.this,SettingActivity.class);
                    startActivity(intentSearch);

                //Toast.makeText(AcceuilActivity.this, "Icon Configuration selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.icon_logout_home:
                showLogoutConfirmDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void showLogoutConfirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AcceuilActivity.this);
        builder.setTitle("Confirmation");
        builder.setMessage("Etes-vous sur de vouloir quitter l'appli ?");
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor=  AcceuilActivity.this.getSharedPreferences("app_state", Context.MODE_PRIVATE).edit();
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
        Bundle bundle = new Bundle();
        bundle.putString("categoryName", categoryName);
        bundle.putString("categoryId", id);
        sousCategorieFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_home, sousCategorieFragment).addToBackStack(null).commit();

    }




    @Override
    public void onSousCategoryClick(String categoryName, String id) {
        getSupportActionBar().setTitle(categoryName);

        SiteTouristiqueFragment siteTouristique = new SiteTouristiqueFragment();
        Bundle bundle = new Bundle();
        bundle.putString("sousCategoryName", categoryName);
        bundle.putString("sousCategoryId", id);
        siteTouristique.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_home, siteTouristique).addToBackStack(null).commit();
    }
}