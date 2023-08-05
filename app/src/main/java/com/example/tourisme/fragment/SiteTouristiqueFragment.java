package com.example.tourisme.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tourisme.API.API;
import com.example.tourisme.DetailHomeActivity;
import com.example.tourisme.R;
import com.example.tourisme.adapter.SiteTourismeAdapter;
import com.example.tourisme.connexion.ConnexionURL;
import com.example.tourisme.models.SiteTourismeModel;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SiteTouristiqueFragment extends Fragment {
    private ConnexionURL connexion;
    private String sousCategory;

    private View view;

    private ListView listView;
    private SiteTourismeAdapter adapter;
    private ArrayList<SiteTourismeModel> dataArray;
    private ArrayList<SiteTourismeModel> filteredList;

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


        this.setListView(this.getView().findViewById(R.id.list_tourisme));

        /*ArrayList<SiteTourismeModel> tourismeArray = new ArrayList();
        tourismeArray.add(new SiteTourismeModel("Post 1", "Lorem upsum comme description court", new Date(), ""));
        tourismeArray.add(new SiteTourismeModel("Post 2", "Lorem upsum comme description court", new Date(), ""));
        tourismeArray.add(new SiteTourismeModel("Post 3", "Lorem upsum comme description court", new Date(), ""));
        tourismeArray.add(new SiteTourismeModel("Post 4", "Lorem upsum comme description court", new Date(), ""));
        tourismeArray.add(new SiteTourismeModel("Post 5", "Lorem upsum comme description court", new Date(), ""));
        tourismeArray.add(new SiteTourismeModel("Post 6", "Lorem upsum comme description court", new Date(), ""));

        this.setAdapter(new SiteTourismeAdapter(getActivity(), R.layout.item_tourisme, tourismeArray));
        this.getListView().setAdapter(this.getAdapter());
        this.dataArray = tourismeArray;
        this.filteredList = new ArrayList<>(dataArray);

        this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent detailActivity = new Intent(getActivity(), DetailHomeActivity.class);
                detailActivity.putExtra("title_detail_home", getAdapter().getItem(position).getTitle());
                startActivity(detailActivity);
            }
        });



         this.getAdapter().setOnItemClickListener(new SiteTourismeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SiteTourismeModel tourisme) {
                Intent detailActivity = new Intent(getActivity(), DetailHomeActivity.class);
                detailActivity.putExtra("title_detail_home", tourisme.getTitle());
                startActivity(detailActivity);
            }
        });
        registerForContextMenu(this.getListView());
*/

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

        Call<ArrayList<SiteTourismeModel>> call = axios.findSiteTouristique(getSousCategoryId());
        call.enqueue(new Callback<ArrayList<SiteTourismeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<SiteTourismeModel>> call, Response<ArrayList<SiteTourismeModel>> response) {
                ArrayList<SiteTourismeModel> list = new ArrayList<>();

                if(response.code()==200){

                    list = response.body();
                    setDataArray(list);

                    setAdapter(new SiteTourismeAdapter(getActivity(),R.layout.item_tourisme,getDataArray()));
                    getListView().setAdapter(getAdapter());
                    registerForContextMenu(getListView());

                    getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override

                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            Intent detailActivity = new Intent(getActivity(), DetailHomeActivity.class);
                            detailActivity.putExtra("title_detail_home", getAdapter().getItem(position).getTitle());

                            startActivity(detailActivity);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SiteTourismeModel>> call, Throwable t) {
                System.out.println("tonga Erreur");
                System.out.println(t.getMessage());
            }
        });

    }

    public void filter(String query) {
        filteredList.clear();
        if(query.isEmpty()){
            this.setAdapter(new SiteTourismeAdapter(getActivity(), R.layout.item_tourisme, dataArray));
            this.getListView().setAdapter(this.getAdapter());

            adapter.setData(dataArray); // Mise à jour des données de l'adaptateur avec la liste filtrée
            adapter.notifyDataSetChanged();
        } else {
            for (SiteTourismeModel model : dataArray) {
                if (model.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(model);
                }
            }

            adapter.setData(filteredList); // Mise à jour des données de l'adaptateur avec la liste filtrée
            adapter.notifyDataSetChanged();
        }


    }


}