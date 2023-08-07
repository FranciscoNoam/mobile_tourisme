package com.example.tourisme.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tourisme.API.API;
import com.example.tourisme.DetailActiviy;
import com.example.tourisme.R;
import com.example.tourisme.adapter.SiteTourismeAdapter;
import com.example.tourisme.connexion.ConnexionURL;
import com.example.tourisme.models.SiteTourismeModel;
import com.example.tourisme.notification.PopupNotification;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SiteTouristiqueFragment extends Fragment {
    private ConnexionURL connexion;

    private TextView error;
    private PopupNotification popupNotification;

    private String sousCategory;

    private View view;

    private ListView listView;
    private SiteTourismeAdapter adapter;
    private ArrayList<SiteTourismeModel> dataArray;
    private ArrayList<SiteTourismeModel> filteredList;

    private SearchView searchView;
    private ArrayList<SiteTourismeModel> list = new ArrayList<>();

    public String getSousCategoryId() {
        return sousCategory;
    }

    public void setSousCategoryId(String categoryId) {
        this.sousCategory = categoryId;
    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public SiteTourismeAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(SiteTourismeAdapter adapter) {
        this.adapter = adapter;
    }

    public ArrayList<SiteTourismeModel> getDataArray() {
        return dataArray;
    }

    public void setDataArray(ArrayList<SiteTourismeModel> dataArray) {
        this.dataArray = dataArray;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        this.setView(inflater.inflate(R.layout.fragment_site_touristique, container, false));

        popupNotification = new PopupNotification(getActivity());

        this.setListView(this.getView().findViewById(R.id.list_tourisme));
        searchView = this.getView().findViewById(R.id.search_tourisme);
        searchView.clearFocus();

        error = getView().findViewById(R.id.error_newtWork_site_touristique);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        Bundle args = getArguments();
        if (args != null) {
            this.setSousCategoryId(args.getString("sousCategoryId"));
            loadFragment();
        }


        return this.getView();
    }


    private void loadFragment(){
        connexion = new ConnexionURL();
        API axios = connexion.getApi();
        error.setVisibility(getView().GONE);

        popupNotification.showLoadingPopup();

        System.out.println("tonga+++++++++++++++ "+getSousCategoryId());
        Call<ArrayList<SiteTourismeModel>> call = axios.findSiteTouristique(getSousCategoryId());
        call.enqueue(new Callback<ArrayList<SiteTourismeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<SiteTourismeModel>> call, Response<ArrayList<SiteTourismeModel>> response) {

                if(response.code()==200){

                    list = response.body();
                    setDataArray(list);

                    setAdapter(new SiteTourismeAdapter(getActivity(),R.layout.item_tourisme,getDataArray()));
                    getListView().setAdapter(getAdapter());
                    registerForContextMenu(getListView());

                    getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override

                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                            Intent detailActivity = new Intent(getActivity(), DetailActiviy.class);

                            detailActivity.putExtra("title_detail_site", getAdapter().getItem(position).getName());
                            detailActivity.putExtra("description_detail_site", getAdapter().getItem(position).getDescription());
                            detailActivity.putExtra("image_detail_site", getAdapter().getItem(position).getImage());
                            detailActivity.putExtra("video_detail_site", getAdapter().getItem(position).getVideo());
                            detailActivity.putExtra("contenu_detail_site", getAdapter().getItem(position).getContenu());
                            detailActivity.putExtra("id_detail_site", getAdapter().getItem(position).getId());

                            startActivity(detailActivity);
                        }
                    });
                }

                popupNotification.hideLoadingPopup();

            }

            @Override
            public void onFailure(Call<ArrayList<SiteTourismeModel>> call, Throwable t) {
                System.out.println("tonga Erreur site touristique");
                System.out.println(t.getMessage());

                error.setVisibility(getView().VISIBLE);
                error.setText("Erreur de connexion");

                popupNotification.hideLoadingPopup();

            }
        });

    }

    private void filterList(String text) {
        ArrayList<SiteTourismeModel> listes = new ArrayList<>();
        for (SiteTourismeModel item : list) {
            if (item.getName().toLowerCase().contains(text.toLowerCase()) || item.getDescription().toLowerCase().contains(text.toLowerCase())) {
                listes.add(item);
            }
        }
        setAdapter(new SiteTourismeAdapter(getActivity(),R.layout.item_tourisme,listes));
        getListView().setAdapter(getAdapter());
        registerForContextMenu(getListView());

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent detailActivity = new Intent(getActivity(), DetailActiviy.class);
                detailActivity.putExtra("title_detail_home", getAdapter().getItem(position).getName());

                startActivity(detailActivity);
            }
        });
    }

}