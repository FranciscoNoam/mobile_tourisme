package com.example.tourisme.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tourisme.API.API;
import com.example.tourisme.R;
import com.example.tourisme.adapter.DetailAdapter;
import com.example.tourisme.connexion.ConnexionURL;
import com.example.tourisme.models.DetailModel;
import com.example.tourisme.notification.PopupNotification;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailFragment_backup extends Fragment {

    private ConnexionURL connexion;
    private TextView error;
    private PopupNotification popupNotification;

    private View view;
    private ListView listView;
    private DetailAdapter adapter;
    private ArrayList<DetailModel> dataArray;
    private ArrayList<DetailModel> list = new ArrayList<>();
    public ArrayList<DetailModel> getDataArray() {
        return dataArray;
    }

    private String sousCategoryId;

    public String getSousCategoryId() {
        return sousCategoryId;
    }

    public void setSousCategoryId(String sousCategoryId) {
        this.sousCategoryId = sousCategoryId;
    }

    public void setDataArray(ArrayList<DetailModel> dataArray) {
        this.dataArray = dataArray;
    }

    public DetailAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(DetailAdapter adapter) {
        this.adapter = adapter;
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView list) {
        this.listView = list;
    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.setView(inflater.inflate(R.layout.fragment_detail, container, false));

       // popupNotification = new PopupNotification(getActivity());
        error = getView().findViewById(R.id.error_newtWork_detail);

        this.setListView(this.getView().findViewById(R.id.list_detail_site));
        Bundle args = getArguments();
        if (args != null) {
            this.setSousCategoryId(args.getString("sousCategoryId"));
            loadFragment();
        }

        loadFragment();
        return this.getView();
    }

    private void loadFragment(){
        connexion = new ConnexionURL();
        API axios = connexion.getApi();

        error.setVisibility(getView().GONE);

       // popupNotification.showLoadingPopup();

        Call<ArrayList<DetailModel>> call = axios.findDetailSiteTouristique(getSousCategoryId());
        call.enqueue(new Callback<ArrayList<DetailModel>>() {
            @Override
            public void onResponse(Call<ArrayList<DetailModel>> call, Response<ArrayList<DetailModel>> response) {

                //popupNotification.hideLoadingPopup();

                if(response.code()==200){
                    list = response.body();

                    setDataArray(list);

                    setAdapter(new DetailAdapter(getActivity(),R.layout.item_detail,getDataArray()));
                    getListView().setAdapter(getAdapter());
                    registerForContextMenu(getListView());

                    //popupNotification.hideLoadingPopup();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DetailModel>> call, Throwable t) {
                System.out.println("tonga Erreur");
                System.out.println(t.getMessage());

                error.setVisibility(getView().VISIBLE);
                error.setText("Erreur de connexion");

            //    popupNotification.hideLoadingPopup();

            }
        });

    }

}