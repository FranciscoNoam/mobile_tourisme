package com.example.tourisme.models;

public class CategorieModel {
    private  String id;
    private String title;
    private String description;
    private String image;

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

    public CategorieModel(String id, String title, String description, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public CategorieModel(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }
    public CategorieModel(){}

    @Override
    public String toString() {
        return "CategorieModel{" +
                "id='"+ id+ '\'' +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
