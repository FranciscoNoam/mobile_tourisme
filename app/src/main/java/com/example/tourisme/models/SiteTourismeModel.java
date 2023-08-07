package com.example.tourisme.models;


import java.util.Date;

public class SiteTourismeModel {
    private String _id;
    private String name;
    private String description;
    private  String image;

    private String video;
    private  SousCategorieModel sousCategorie;
    private String contenu;
    private Date createdAt;


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

    public String getId_() {
        return _id;
    }

    public void setId_(String id_) {
        this._id = id_;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public SiteTourismeModel(){}

   /* public SiteTourismeModel(String id, String title, String description, String image, Date createdAt, SousCategorieModel sousCategorie) {
        this._id = id;
        this.name = title;
        this.description = description;
        this.image = image;
        this.createdAt = createdAt;
        this.sousCategorie = sousCategorie;
    }*/


    public SiteTourismeModel(String id_, String name, String description, String image, String video, String contenu) {
        this._id = id_;
        this.name = name;
        this.description = description;
        this.image = image;
        this.video = video;
       // this.sousCategorie = sousCategorie;
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "SiteTourismeModel{" +
                "id_='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", video='" + video + '\'' +
                ", sousCategorie=" + sousCategorie +
                ", contenu='" + contenu + '\'' +
                '}';
    }
}

