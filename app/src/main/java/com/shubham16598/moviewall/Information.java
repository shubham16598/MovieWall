package com.shubham16598.moviewall;

/**
 * Created by shubham16598 on 1/7/17.
 */

public class Information {
    public String imageID;
    public String synopsis;
    public Float rating;
    public String backPoster;
    public String releaseDate;
    public String originalTitle;

    public String getImageID() {
        return imageID;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public Float getRating() {
        return rating;
    }

    public String getBackPoster() {
        return backPoster;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    @Override
    public String toString() {
        return "Information{" +
                "imageID='" + imageID + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", rating='" + rating + '\'' +
                ", backPoster='" + backPoster + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }

}
