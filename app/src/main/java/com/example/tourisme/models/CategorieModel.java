package com.example.tourisme.models;

public class CategorieModel {
    private  String _id;
    private String name;
    private String description;
    private String image;

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

    public CategorieModel(String id, String title, String description, String image) {
        this._id = id;
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
        this._id = id;
    }

    public CategorieModel(){}

    @Override
    public String toString() {
        return "CategorieModel{" +
                "id_='"+ _id+ '\'' +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
