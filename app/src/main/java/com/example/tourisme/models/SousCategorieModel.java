package com.example.tourisme.models;

public class SousCategorieModel {
    private  String id;
    private String title;
    private String description;
    private String image;
    private CategorieModel categorie;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.categorie = categorie;
    }

    public SousCategorieModel(String title, String description, String image, CategorieModel categorie) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.categorie = categorie;
    }

    public SousCategorieModel(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    @Override
    public String toString() {
        return "SousCategorieModel{" +
                "id='"+ id+ '\'' +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", categorie=" + categorie +
                '}';
    }
}
