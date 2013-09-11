package com.svenardo.recommender_wa2;

/**
 * Created with IntelliJ IDEA.
 * User: svenanders
 * Date: 09.09.13
 * Time: 21:56
 * To change this template use File | Settings | File Templates.
 */
public class Rating {
    public int movieId;
    public Float percentage;
    public int Id;

    public Rating(int setMovieId, float setPercentage, int setId) {
        movieId = setMovieId;
        percentage = setPercentage;
        Id = setId;
    }

    public int getMovieId() {
        return movieId;
    }

    public float getPercentage() {
        return percentage;
    }

}
