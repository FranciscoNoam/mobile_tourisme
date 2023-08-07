package com.example.tourisme.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.tourisme.R;
import com.example.tourisme.connexion.ConnexionURL;
import com.example.tourisme.fragment.DetailFragment;
import com.example.tourisme.models.DetailModel;
import com.example.tourisme.models.SousCategorieModel;

import java.util.ArrayList;

public class DetailAdapter extends ArrayAdapter<DetailModel> {
    private ArrayList<DetailModel> values;
    private  int resource;
    private Context context;

    public void setValues(ArrayList<DetailModel> v){
        this.values =v;
    }

    public void setResource(int res){
        this.resource = res;
    }
    public void setContext(Context contex){
        this.context = contex;
    }
    public ArrayList<DetailModel> getValues(){
        return this.values;
    }
    public int getResource(){
        return this.resource;
    }

    public Context getContext(){
        return this.context;
    }


    public DetailAdapter(@NonNull Context context, int resource, ArrayList<DetailModel> values) {
        super(context, resource,values);
        this.setContext(context);
        this.setResource(resource);
        this.setValues(values);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(this.getContext()).inflate(this.getResource(),parent,false);

        String txttitle= this.getValues().get(position).getTitle();
        String txtdescription = this.getValues().get(position).getDescription();
        String txturl_image = this.getValues().get(position).getImage();
        String txt_video = this.getValues().get(position).getVideo();

        TextView title = convertView.findViewById(R.id.title_detail_description);
        WebView descriptionShort = convertView.findViewById(R.id.description_detail_description);
        ImageView image = convertView.findViewById(R.id.image_detail_description);
        VideoView video = convertView.findViewById(R.id.video_detail_description);

        title.setText(txttitle);
        descriptionShort.loadData(txtdescription, "text/html", "UTF-8");
        descriptionShort.setWebViewClient(new WebViewClient());

        if(!txturl_image.trim().isEmpty()){
          //  image.setVisibility(View.VISIBLE);
            Glide.with(convertView)
                    .load(new ConnexionURL().getBaseUrl()+txturl_image)
                    .into(image);
        }

        if(!txt_video.trim().isEmpty()){
            video.setVisibility(View.VISIBLE);
            Uri videoUri = Uri.parse(new ConnexionURL().getBaseUrl()+txt_video);
            video.setVideoURI(videoUri);
            video.start();

        }
        return convertView;
    }
}
