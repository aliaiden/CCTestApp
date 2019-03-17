package com.alihaider.myapp.model;

public class RestaurantsDataModel {
    String id, name, location, imageURL;
    float rating;
    int reviews;

    public RestaurantsDataModel(String id, String name, String location, String imageURL, float rating, int reviews) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.reviews = reviews;
        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getImageURL() {
        return imageURL;
    }

    public float getRating() {
        return rating;
    }

    public int getReviews() {
        return reviews;
    }
}
