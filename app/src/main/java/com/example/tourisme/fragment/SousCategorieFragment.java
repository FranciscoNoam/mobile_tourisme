package com.example.tourisme.fragment;
import android.content.*;
import android.os.*;

import androidx.annotation.*;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.*;
import android.widget.*;

import com.example.tourisme.API.API;
import com.example.tourisme.*;
import com.example.tourisme.adapter.*;
import com.example.tourisme.connexion.*;
import com.example.tourisme.models.*;

import java.util.*;

import retrofit2.*;


public class SousCategorieFragment extends Fragment {
    private ConnexionURL connexion;


    public SousCategorieFragment.OnSousCategoryClickListener sousCategoryClickListener;
    public interface OnSousCategoryClickListener {
        void onSousCategoryClick(String categoryName,String id);
    }

    private String categoryId;

    private View view;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    private ListView listView;
    private SousCategorieAdapter adapter;
    private ArrayList<SousCategorieModel> dataArray;
    private SearchView searchView;
    private ArrayList<SousCategorieModel> list = new ArrayList<>();
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

    public SousCategorieAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(SousCategorieAdapter adapter) {
        this.adapter = adapter;
    }

    public ArrayList<SousCategorieModel> getDataArray() {
        return dataArray;
    }

    public void setDataArray(ArrayList<SousCategorieModel> dataArray) {
        this.dataArray = dataArray;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnSousCategoryClickListener) {
            sousCategoryClickListener = (OnSousCategoryClickListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnCategoryClickListener");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.setView(inflater.inflate(R.layout.fragment_sous_categorie, container, false));
        this.setListView(this.getView().findViewById(R.id.list_sous_categorie));


        searchView = this.getView().findViewById(R.id.search_sous_categorie);
        searchView.clearFocus();
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
            this.setCategoryId(args.getString("categoryId"));
            loadFragment();
        }

        return this.getView();
    }

    private void loadFragment(){
        connexion = new ConnexionURL();
        API axios = connexion.getApi();

        Call<ArrayList<SousCategorieModel>> call = axios.findSousCategorie(getCategoryId());
        call.enqueue(new Callback<ArrayList<SousCategorieModel>>() {
            @Override
            public void onResponse(Call<ArrayList<SousCategorieModel>> call, Response<ArrayList<SousCategorieModel>> response) {

                if(response.code()==200){
                    list = response.body();

                    setDataArray(list);

                    setAdapter(new SousCategorieAdapter(getActivity(),R.layout.item_sous_categorie,getDataArray()));
                    getListView().setAdapter(getAdapter());
                    registerForContextMenu(getListView());

                    getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                           sousCategoryClickListener.onSousCategoryClick(getAdapter().getItem(position).getTitle(),getAdapter().getItem(position).getId());

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SousCategorieModel>> call, Throwable t) {
                System.out.println("tonga Erreur");
                System.out.println(t.getMessage());
            }
        });
    }

    private void filterList(String text) {
        ArrayList<SousCategorieModel> listes = new ArrayList<>();
        for (SousCategorieModel item : list) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                listes.add(item);
            }
        }
        setAdapter(new SousCategorieAdapter(getActivity(),R.layout.item_sous_categorie,listes));
        getListView().setAdapter(getAdapter());
        registerForContextMenu(getListView());

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                sousCategoryClickListener.onSousCategoryClick(getAdapter().getItem(position).getTitle(),getAdapter().getItem(position).getId());

            }
        });
    }
}