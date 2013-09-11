package com.svenardo.recommender_wa2;

/**
 * Created with IntelliJ IDEA.
 * User: svenanders
 * Date: 08.09.13
 * Time: 19:23
 * To change this template use File | Settings | File Templates.
 */

public class MovieRating {
    public int id;
    public int userId;
    public int rating;
    public int position;

    public MovieRating(int setId, int setRating, int setUserId, int setPosition) {
        rating = setRating;
        userId = setUserId;
        id = setId;
        position = setPosition;
    }

    public int getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }


    public int getRating() {
        return rating;
    }


}
