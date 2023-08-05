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


public class CategorieFragment extends Fragment {

    private ConnexionURL connexion;
    public OnCategoryClickListener categoryClickListener;
    public interface OnCategoryClickListener {
        void onCategoryClick(String categoryName,String id);
    }

    private SearchView searchView;


    private View view;
    private ListView listView;
    private CategorieAdapter adapter;
    private  ArrayList<CategorieModel> dataArray;
    private ArrayList<CategorieModel> list = new ArrayList<>();
    public ArrayList<CategorieModel> getDataArray() {
        return dataArray;
    }

    public void setDataArray(ArrayList<CategorieModel> dataArray) {
        this.dataArray = dataArray;
    }

    public CategorieAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(CategorieAdapter adapter) {
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnCategoryClickListener) {
            categoryClickListener = (OnCategoryClickListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnCategoryClickListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        this.setView(inflater.inflate(R.layout.fragment_categorie, container, false));

        this.setListView(this.getView().findViewById(R.id.list_categorie));


        searchView = this.getView().findViewById(R.id.search_categorie);
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

        loadFragment();
        return this.getView();
    }


    private void loadFragment(){
        connexion = new ConnexionURL();
        API axios = connexion.getApi();

        Call<ArrayList<CategorieModel>> call = axios.findAll();
        call.enqueue(new Callback<ArrayList<CategorieModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CategorieModel>> call, Response<ArrayList<CategorieModel>> response) {


                if(response.code()==200){
                    list = response.body();

                    setDataArray(list);

                    setAdapter(new CategorieAdapter(getActivity(),R.layout.item_categorie,getDataArray()));
                    getListView().setAdapter(getAdapter());
                    registerForContextMenu(getListView());

                    getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            categoryClickListener.onCategoryClick(getAdapter().getItem(position).getTitle(),getAdapter().getItem(position).getId());

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CategorieModel>> call, Throwable t) {
                System.out.println("tonga Erreur");
                System.out.println(t.getMessage());
            }
        });

    }

    private void filterList(String text){
        ArrayList<CategorieModel> listes = new ArrayList<>();
        for (CategorieModel item: list){
         if(item.getTitle().toLowerCase().contains(text.toLowerCase())){
             listes.add(item);
         }
        }

        setAdapter(new CategorieAdapter(getActivity(),R.layout.item_categorie,listes));
        getListView().setAdapter(getAdapter());
        registerForContextMenu(getListView());

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                categoryClickListener.onCategoryClick(getAdapter().getItem(position).getTitle(),getAdapter().getItem(position).getId());
            }
        });
    }


}