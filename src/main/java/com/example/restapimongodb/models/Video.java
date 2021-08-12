package com.example.restapimongodb.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("videos") //represents collection name inside database in MongoDB
public class Video {

    //POJO class (Plain Old Java Object Class)  //bean //entity

    @Id //states that this POJO class represents a collection("movies") in MongoDB
    // & each document in that collection will have following properties
    private String id;
    private String title;
    private String poster;
    private String director;
    private String stars;
    private String genre;
    private String release;
    private String rating;
    private String story;
    private String backgroundimg;
    private int type;
    private double rent;
    private double buy;
    private boolean isFeatured;

    public Video() {
    }

    public Video(String id, String title, String poster, String director, String stars, String genre, String release, String rating, String story, String backgroundimg, int type, double rent, double buy, boolean isFeatured) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.director = director;
        this.stars = stars;
        this.genre = genre;
        this.release = release;
        this.rating = rating;
        this.story = story;
        this.backgroundimg = backgroundimg;
        this.type = type;
        this.rent = rent;
        this.buy = buy;
        this.isFeatured = isFeatured;
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getBackgroundimg() {
        return backgroundimg;
    }

    public void setBackgroundimg(String backgroundimg) {
        this.backgroundimg = backgroundimg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", poster='" + poster + '\'' +
                ", director='" + director + '\'' +
                ", stars='" + stars + '\'' +
                ", genre='" + genre + '\'' +
                ", release='" + release + '\'' +
                ", rating='" + rating + '\'' +
                ", story='" + story + '\'' +
                ", backgroundimg='" + backgroundimg + '\'' +
                ", type=" + type +
                ", rent=" + rent +
                ", buy=" + buy +
                ", isFeatured=" + isFeatured +
                '}';
    }
}
