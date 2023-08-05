package com.example.tourisme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;

import com.example.tourisme.R;
import com.example.tourisme.models.SiteTourismeModel;

import java.util.ArrayList;

public class SiteTourismeAdapter extends ArrayAdapter<SiteTourismeModel> {

    private OnItemClickListener clickListener;

    // Interface pour le gestionnaire de clic
    public interface OnItemClickListener {
        void onItemClick(SiteTourismeModel tourisme);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    private ArrayList<SiteTourismeModel> values;
    private  int resource;
    private Context context;

    public void setValues(ArrayList<SiteTourismeModel> v){
        this.values =v;
    }

    public void setResource(int res){
        this.resource = res;
    }
    public void setContext(Context contex){
        this.context = contex;
    }
    public ArrayList<SiteTourismeModel> getValues(){
        return this.values;
    }
    public int getResource(){
        return this.resource;
    }

    public Context getContext(){
        return this.context;
    }

    public void setData(ArrayList<SiteTourismeModel> data) {
        this.clear();
        this.addAll(data);
    }
    public SiteTourismeAdapter(@NonNull Context context, int resource, ArrayList<SiteTourismeModel> values) {
        super(context, resource,values);
        this.setContext(context);
        this.setResource(resource);
        this.setValues(values);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-m-dd");

        final SiteTourismeModel tourismeModel = getItem(position);

        String tourisme_title= this.getValues().get(position).getTitle();
        String tourisme_description = this.getValues().get(position).getDescription();
        String tourisme_date = dateFormat.format(this.getValues().get(position).getCreatedAt());
        String tourisme_url_image = this.getValues().get(position).getImage();

        convertView = LayoutInflater.from(this.getContext()).inflate(this.getResource(),parent,false);

        TextView title_detail = convertView.findViewById(R.id.title_detail_list);
        TextView date_detail = convertView.findViewById(R.id.date_detail_list);
        TextView description_detail = convertView.findViewById(R.id.description_detail_list);

        title_detail.setText(tourisme_title);
        date_detail.setText(tourisme_date);
        description_detail.setText(tourisme_description);

       /* convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null && tourismeModel != null) {
                    // Appelez la méthode onItemClick du gestionnaire de clic personnalisé
                    clickListener.onItemClick(tourismeModel);
                }
            }
        });*/


        return convertView;
    }

   /* public void filter(String query) {
        ArrayList<TourismeModel> filteredList = new ArrayList<>();
        if (query.isEmpty()) {
            filteredList.addAll(values); // Afficher toutes les données si le texte de recherche est vide
        } else {
            query = query.toLowerCase();
            for (TourismeModel tourisme : values) {
                if (tourisme.getTitle().toLowerCase().contains(query) || tourisme.getShortDescription().toLowerCase().contains(query)) {
                    filteredList.add(tourisme);
                }
            }
        }
        clear();
        addAll(filteredList);
        notifyDataSetChanged();
    }*/

}
