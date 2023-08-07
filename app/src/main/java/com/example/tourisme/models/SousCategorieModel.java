package com.example.tourisme.models;

public class SousCategorieModel {
    private  String _id;
    private String name;
    private String description;
    private String image;
    private CategorieModel categorie;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CategorieModel getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieModel categorie) {
        this.categorie = categorie;
    }

    public SousCategorieModel(String id,String title, String description, String image, CategorieModel categorie) {
        this._id = id;
        this.name = title;
        this.description = description;
        this.image = image;
        this.categorie = categorie;
    }

    public SousCategorieModel(String title, String description, String image, CategorieModel categorie) {
        this.name = title;
        this.description = description;
        this.image = image;
        this.categorie = categorie;
    }

    public SousCategorieModel(String title, String description, String image) {
        this.name = title;
        this.description = description;
        this.image = image;
    }

    @Override
    public String toString() {
        return "SousCategorieModel{" +
                "id_='"+ _id+ '\'' +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", categorie=" + categorie +
                '}';
    }
}
