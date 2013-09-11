package com.svenardo.recommender_wa2;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: svenanders
 * Date: 09.09.13
 * Time: 10:53
 * To change this template use File | Settings | File Templates.
 */
public class User {

    int userId;
    ArrayList<MovieRating> movieRatings = new ArrayList<MovieRating>();

    public User(int setUserId) {
        this.userId = setUserId;
    }

    public void addRating(int setRating, int setMovieId) {
        movieRatings.add(new MovieRating(setMovieId, setRating, this.userId, setMovieId));
    }

    public int getUserId() {
        return userId;
    }

    public int numberWhoHaveRatedTwoMovies(int movieId, int otherMovieId) {
        int number = 0;
        for (MovieRating m : movieRatings) {
            if (m.getId() == movieId) number++;
            if (m.getId() == otherMovieId) number++;
        }
        return number;
    }

    public int numberWhoHaveNotRatedMovie(int movieId) {
        int size = movieRatings.size();
        for (MovieRating m : movieRatings) {
            if (m.getId() == movieId) size--;
        }
        return size;
    }

    public int numberWhoHaveRatedMovieWithAnother(int movieId, int otherMovieId) {
        boolean ratedFirst = false;
        boolean ratedOther = false;
        for (MovieRating m : movieRatings) {
            if (m.getId() == movieId) ratedFirst = true;
            if (m.getId() == otherMovieId) ratedOther = true;
        }
        if (ratedFirst && ratedOther) return 1;
        else return 0;
    }

}
