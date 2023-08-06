package com.example.tourisme.models;

public class DetailModel {
    private String id;
    private String title;
    private  String description;
    private String image;
    private String video;

    private SiteTourismeModel siteTouristique;

    public SiteTourismeModel getSiteTouristique() {
        return siteTouristique;
    }

    public void setSiteTouristique(SiteTourismeModel siteTouristique) {
        this.siteTouristique = siteTouristique;
    }

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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public DetailModel(String title, String description, String image, String video) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.video = video;
    }

    public DetailModel(String id, String title, String description, String image, String video) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.video = video;
    }

    public DetailModel(String id, String title, String description, String image, String video, SiteTourismeModel siteTouristique) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.video = video;
        this.siteTouristique = siteTouristique;
    }

    @Override
    public String toString() {
        return "DetailModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", video='" + video + '\'' +
                ", siteTouristique=" + siteTouristique +
                '}';
    }
}
