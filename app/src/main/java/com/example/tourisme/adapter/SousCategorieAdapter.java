package com.example.tourisme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.example.tourisme.R;
import com.example.tourisme.connexion.ConnexionURL;
import com.example.tourisme.fragment.CategorieFragment;
import com.example.tourisme.models.CategorieModel;
import com.example.tourisme.models.SousCategorieModel;

import java.util.ArrayList;
import java.util.List;

public class SousCategorieAdapter extends ArrayAdapter<SousCategorieModel> {

    private ArrayList<SousCategorieModel> values;
    private  int resource;
    private Context context;

    public void setValues(ArrayList<SousCategorieModel> v){
        this.values =v;
    }

    public void setResource(int res){
        this.resource = res;
    }
    public void setContext(Context contex){
        this.context = contex;
    }
    public ArrayList<SousCategorieModel> getValues(){
        return this.values;
    }
    public int getResource(){
        return this.resource;
    }

    public Context getContext(){
        return this.context;
    }


    public SousCategorieAdapter(@NonNull Context context, int resource,ArrayList<SousCategorieModel> values) {
        super(context, resource,values);
        this.setContext(context);
        this.setResource(resource);
        this.setValues(values);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final SousCategorieModel sousCategorieModelModel = getItem(position);
        convertView = LayoutInflater.from(this.getContext()).inflate(this.getResource(),parent,false);

        String txttitle= this.getValues().get(position).getName();
        String txtdescription = this.getValues().get(position).getDescription();
        String txturl_image = this.getValues().get(position).getImage();


        TextView title = convertView.findViewById(R.id.title_sous_categorie);
        ImageView image = convertView.findViewById(R.id.image_sous_categorie);
        TextView description = convertView.findViewById(R.id.description_sous_categorie);

        title.setText(txttitle);
        description.setText(txtdescription);
        Glide.with(convertView)
                .load(new ConnexionURL().getBaseUrl()+txturl_image)
                .into(image);

        return convertView;
    }


}
