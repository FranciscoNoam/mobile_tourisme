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

import com.bumptech.glide.Glide;
import com.example.tourisme.R;
import com.example.tourisme.connexion.ConnexionURL;
import com.example.tourisme.models.CategorieModel;

import java.util.ArrayList;

public class CategorieAdapter extends ArrayAdapter<CategorieModel> {

    private ArrayList<CategorieModel> values;
    private  int resource;
    private Context context;

    public void setValues(ArrayList<CategorieModel> v){
        this.values =v;
    }

    public void setResource(int res){
        this.resource = res;
    }
    public void setContext(Context contex){
        this.context = contex;
    }
    public ArrayList<CategorieModel> getValues(){
        return this.values;
    }
    public int getResource(){
        return this.resource;
    }

    public Context getContext(){
        return this.context;
    }


    public CategorieAdapter(@NonNull Context context, int resource,ArrayList<CategorieModel> values) {
        super(context, resource,values);
        this.setContext(context);
        this.setResource(resource);
        this.setValues(values);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final CategorieModel tourismeModel = getItem(position);

        String txttitle= this.getValues().get(position).getTitle();
        String txtdescription = this.getValues().get(position).getDescription();
        String txturl_image = this.getValues().get(position).getImage();
//"http://localhost:8080/upload/image.png"
        convertView = LayoutInflater.from(this.getContext()).inflate(this.getResource(),parent,false);

        TextView title = convertView.findViewById(R.id.title_categorie);
        ImageView image = convertView.findViewById(R.id.image_categorie);
        TextView description = convertView.findViewById(R.id.description_categorie);

        title.setText(txttitle);
        description.setText(txtdescription);
        Glide.with(convertView)
                .load(new ConnexionURL().getBaseUrl()+txturl_image)
                .into(image);


        return convertView;
    }

}
