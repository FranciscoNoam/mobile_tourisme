package com.example.tourisme.models;


import java.util.Date;

public class SiteTourismeModel {
    private String id;
    private String title;
    private String description;
    private  String image;
    private Date createdAt;
    private  SousCategorieModel sousCategorie;

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public SousCategorieModel getSousCategorie() {
        return sousCategorie;
    }

    public void setSousCategorie(SousCategorieModel sousCategorie) {
        this.sousCategorie = sousCategorie;
    }

    public SiteTourismeModel(){}

    public SiteTourismeModel(String id, String title, String description, String image, Date createdAt, SousCategorieModel sousCategorie) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.createdAt = createdAt;
        this.sousCategorie = sousCategorie;
    }

    @Override
    public String toString() {
        return "SiteTourismeModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", createdAt=" + createdAt +
                ", sousCategorie=" + sousCategorie +
                '}';
    }
}

