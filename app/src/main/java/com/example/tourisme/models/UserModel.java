package com.example.tourisme.models;

public class UserModel {
    private String _id;
    private String name;
    private String email;
    private String password;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserModel(String _id, String name, String email){
        this.set_id(_id);
        this.setName(name);
        this.setEmail(email);
    }

    public UserModel(String _id, String name, String email, String password) {
        this.set_id(_id);
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
    }

    public UserModel() {
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
