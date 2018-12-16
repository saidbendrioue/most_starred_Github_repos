package com.example.said.most_starred_github_repos;

import android.graphics.Bitmap;

public class Repository {
    String name;
    String description;
    int stars;
    String owner;
    Bitmap avatar;

    public Repository(String name, String description, int stars, String owner, Bitmap avatar) {
        this.name = name;
        this.description = description;
        this.stars = stars;
        this.owner = owner;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }


}
