package com.example.tourisme.fragment;

public class SiteTouristiqueBackup {
    /*
     private View view;

    private ListView listView;
    private SousCategorieAdapter adapter;
    private ArrayList<SousCategorieModel> dataArray;
    private ListView listTourisme;
    private SiteTourismeAdapter adapterTourisme;
    private ArrayList<SiteTourismeModel> originalList;
    private ArrayList<SiteTourismeModel> filteredList;


    public ListView getListTourisme() {
        return listTourisme;
    }

    public void setListTourisme(ListView listTourisme) {
        this.listTourisme = listTourisme;
    }

    public SiteTourismeAdapter getAdapterTourisme() {
        return adapterTourisme;
    }

    public void setAdapterTourisme(SiteTourismeAdapter adapterTourisme) {
        this.adapterTourisme = adapterTourisme;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        this.setView(inflater.inflate(R.layout.fragment_site_touristique, container, false));


        this.setListTourisme(this.getView().findViewById(R.id.list_tourisme));

        ArrayList<SiteTourismeModel> tourismeArray = new ArrayList();
        tourismeArray.add(new SiteTourismeModel("Post 1", "Lorem upsum comme description court", new Date(), ""));
        tourismeArray.add(new SiteTourismeModel("Post 2", "Lorem upsum comme description court", new Date(), ""));
        tourismeArray.add(new SiteTourismeModel("Post 3", "Lorem upsum comme description court", new Date(), ""));
        tourismeArray.add(new SiteTourismeModel("Post 4", "Lorem upsum comme description court", new Date(), ""));
        tourismeArray.add(new SiteTourismeModel("Post 5", "Lorem upsum comme description court", new Date(), ""));
        tourismeArray.add(new SiteTourismeModel("Post 6", "Lorem upsum comme description court", new Date(), ""));

        this.setAdapterTourisme(new SiteTourismeAdapter(getActivity(), R.layout.item_tourisme, tourismeArray));
        this.getListTourisme().setAdapter(this.getAdapterTourisme());
        this.originalList = tourismeArray;
        this.filteredList = new ArrayList<>(originalList);

        this.getListTourisme().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent detailActivity = new Intent(getActivity(), DetailHomeActivity.class);
                detailActivity.putExtra("title_detail_home", getAdapterTourisme().getItem(position).getTitle());
                startActivity(detailActivity);
            }
        });



         this.getAdapterTourisme().setOnItemClickListener(new SiteTourismeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SiteTourismeModel tourisme) {
                Intent detailActivity = new Intent(getActivity(), DetailHomeActivity.class);
                detailActivity.putExtra("title_detail_home", tourisme.getTitle());
                startActivity(detailActivity);
            }
        });


        registerForContextMenu(this.getListTourisme());


        return this.getView();
    }

    public void filter(String query) {
        filteredList.clear();
        if(query.isEmpty()){
            this.setAdapterTourisme(new SiteTourismeAdapter(getActivity(), R.layout.item_tourisme, originalList));
            this.getListTourisme().setAdapter(this.getAdapterTourisme());

            adapterTourisme.setData(originalList); // Mise à jour des données de l'adaptateur avec la liste filtrée
            adapterTourisme.notifyDataSetChanged();
        } else {
            for (SiteTourismeModel model : originalList) {
                if (model.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(model);
                }
            }

            adapterTourisme.setData(filteredList); // Mise à jour des données de l'adaptateur avec la liste filtrée
            adapterTourisme.notifyDataSetChanged();
        }


    }


    */
}
