package com.example.tourisme;


import androidx.annotation.*;
import androidx.appcompat.app.*;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.preference.PreferenceManager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import com.example.tourisme.fragment.*;
import com.example.tourisme.notification.AppNotification;

public class AcceuilActivity extends AppCompatActivity   implements CategorieFragment.OnCategoryClickListener, SousCategorieFragment.OnSousCategoryClickListener{

    public SharedPreferences sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isDarkThemeEnabled = sharedPreferences.getBoolean("theme_preference", false);

        if (isDarkThemeEnabled) {
            setTheme(R.style.AppTheme_Dark);
        } else {
            setTheme(R.style.AppTheme);
        }
        String tmp = getIntent().getStringExtra("name");
        System.out.println("tonga eto: "+tmp);

        setContentView(R.layout.activity_acceuil);

        new AppNotification().createNotificationChannels(this);


        showNotification(tmp);
        loadCategorieFragment();

    } // end onCreate

    private void showNotification(String name_) {
        sharedPreference  = this.getSharedPreferences("app_state", Context.MODE_PRIVATE);

        Boolean isConnected = sharedPreference.getBoolean("is_authentificated",false);
        String name = sharedPreference.getString("name",null);

        if(name_!=null){
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel1")
                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentTitle("Coucou "+name)
                    .setContentText("Bienvenu Sur la  l'application de mise en valeur des cites touristique de Madagascar")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setAutoCancel(true);

            Intent intent = new Intent(this, AcceuilActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    this,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE // Utilisez FLAG_IMMUTABLE ici
            );

            builder.setContentIntent(pendingIntent);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

            notificationManager.notify(1, builder.build());
        }

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel Name";
            String description = "Description du canal";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("channel1", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


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

            case R.id.icon_setting_home:
               // Toast.makeText(AcceuilActivity.this, "Icon Configuration selected OKKKKK", Toast.LENGTH_LONG).show();
                Intent intentSearch = new Intent(AcceuilActivity.this,PreferenceActivity.class);
                startActivity(intentSearch);
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

        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        Button neutralButton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);

        positiveButton.setTextColor(getResources().getColor(R.color.purple_700));
        negativeButton.setTextColor(getResources().getColor(R.color.purple_700));
        neutralButton.setTextColor(getResources().getColor(R.color.purple_700));


    }

    @Override
    public void onCategoryClick(String categoryName,String id) {
        getSupportActionBar().setTitle(categoryName);

        SousCategorieFragment sousCategorieFragment = new SousCategorieFragment();
        Bundle bundle = new Bundle();
        bundle.putString("categoryName", categoryName);
        bundle.putString("categoryId", id);
        sousCategorieFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_acceuil, sousCategorieFragment).addToBackStack(null).commit();

    }


    @Override
    public void onSousCategoryClick(String categoryName, String id) {
        getSupportActionBar().setTitle(categoryName);

        SiteTouristiqueFragment siteTouristique = new SiteTouristiqueFragment();
        Bundle bundle = new Bundle();
        bundle.putString("sousCategoryName", categoryName);
        bundle.putString("sousCategoryId", id);
        siteTouristique.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_acceuil, siteTouristique).addToBackStack(null).commit();

    }
}