package com.svenardo.recommender_wa2;

/**
 * Created with IntelliJ IDEA.
 * User: svenanders
 * Date: 08.09.13
 * Time: 19:47
 * To change this template use File | Settings | File Templates.
 */
public class Movies {


    public int id;
    public int movieId;
    public String movieName;
    public Float meanRating;
    public int rating;

    public int numberOfRatings;
    public int ratingsFourOrOver;
    public int numberOfRatingsFourOrOver;
    public Float PercentageFourOrOver;


    public Movies(int setId, String setMovieName, int setMovieId) {
        movieName = setMovieName;
        movieId = setMovieId;
        id = setId;
        rating = 0;
        numberOfRatings = 0;
        ratingsFourOrOver = 0;
        numberOfRatingsFourOrOver = 0;
        PercentageFourOrOver = (float) 0;
    }

    public int getId() {
        return id;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings() {
        this.numberOfRatings++;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setRating(int setRating) {
        this.rating += setRating;
    }

    public void setPercentageOfFour() {
        this.PercentageFourOrOver = (((float) this.numberOfRatingsFourOrOver / (float) this.numberOfRatings) * 100);
    }

    public void setRatingsFourOrOver(int setRating) {
        this.ratingsFourOrOver += setRating;
        this.numberOfRatingsFourOrOver++;
    }


    public void setMeanRating() {
        this.meanRating = ((float) this.rating / (float) this.numberOfRatings);
    }

    public float getPercentageFourOrOver() {
        return PercentageFourOrOver;
    }

    public float getMeanRating() {
        return round(this.meanRating, 2);
    }

    public static float round(float targetValue, int roundToDecimalPlaces) {

        int valueInTwoDecimalPlaces = (int) (targetValue * Math.pow(10, roundToDecimalPlaces));
        return (float) (valueInTwoDecimalPlaces / Math.pow(10, roundToDecimalPlaces));
    }

    public int getMovieId() {
        return movieId;
    }

}

