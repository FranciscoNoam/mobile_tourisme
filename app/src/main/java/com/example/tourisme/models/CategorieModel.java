package com.example.tourisme.models;

public class CategorieModel {
    private  String id_;
    private String name;
    private String description;
    private String image;

    public String getId() {
        return id_;
    }

    public void setId(String id) {
        this.id_ = id;
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

    public CategorieModel(String id, String title, String description, String image) {
        this.id_ = id;
        this.name = title;
        this.description = description;
        this.image = image;
    }

    public CategorieModel(String title, String description, String image) {
        this.name = title;
        this.description = description;
        this.image = image;
    }

    public CategorieModel(String id, String name) {
        this.name = name;
        this.id_ = id;
    }

    public CategorieModel(){}

    @Override
    public String toString() {
        return "CategorieModel{" +
                "id='"+ id_+ '\'' +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
