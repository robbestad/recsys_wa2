package com.svenardo.recommender_wa2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: svenanders
 * Date: 08.09. 13
 * Time: 19:00
 * To change this template use File | Settings | File Templates.
 */
public class recommender_wa2 {

    /**
     * Populate movie objects and rating objects
     * by fetching data from the
     * included CSV file
     * <p/>
     * Deliverables
     * <p/>
     * There are 4 deliverables for this assignment. Each deliverable represents a different analysis of the data @
     * provided to you. For each deliverable, you will submit a list of the top 5 movies as ranked by a particular
     * metric. The 4 metrics are:
     * <p/>
     * Mean Rating: Calculate the mean rating for each movie, order with the highest rating listed first, and submit
     * the top 5.
     * <p/>
     * % of ratings 4+: Calculate the percentage of ratings for each movie that are 4 or higher. Order with the
     * highest percentage first, and submit the top 5.
     * <p/>
     * Rating Count: Count the number of ratings for each movie, order with the most number of ratings first, and
     * submit the top 5.
     * <p/>
     * Top 5 Star Wars: Calculate movies that most often occur with Star Wars: Episode IV - A New Hope (1977) using
     * the "x + y / x" method described in class. In other words, for each movie, calculate the percentage of people
     * who rated that movie and Star Wars: Episode IV - A New Hope. Order with the highest percentage first, and
     * submit the top 5.
     *
     * Amended...:(
     * Top 5 Star Wars: Calculate movies that most often occur with Star Wars: Episode IV - A New Hope (1977) using
     * the "x + y / x" method described in class. In other words, for each movie, calculate the percentage of Star Wars
     * raters who also rated that movie. Order with the highest percentage first, and submit the top 5.
     *
     * In order to check your calculations, the values for Raiders of the Lost Ark are:

     Mean: 2.91
     Rating Count: 11
     % of 4+: 27.2%
     Association with Star Wars Episode IV: 50.0%

     */
    public static void main(String[] args) {

        int i = 0;
        int numberOfLines = 5;
        ArrayList<Movies> moviesList = new ArrayList<Movies>();
        ArrayList<MovieRating> movieRatings = new ArrayList<MovieRating>();
        ArrayList<User> userList = new ArrayList<User>();


        String csvFile = "recsys-data-WA1_rating_matrix.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] csvSplit = line.split(cvsSplitBy);

                if (i == 0) {
                    /*
                    Populate moviesList with all the movie names
                    */
                    for (int j = 1; j < csvSplit.length; j++) {

                        String[] out = csvSplit[j].split(": ");
                        Integer movieId = Integer.parseInt(out[0]);
                        moviesList.add(new Movies(j, out[1], movieId));

                    }

                } else {
                    /*
                    Populate MovieRating with all the movie names
                    */

                    for (int j = 1; j < csvSplit.length; j++) {

                        Integer userId = Integer.parseInt(csvSplit[0]);
                        if (j == 1) userList.add(new User(userId));

                        String rating = csvSplit[j];

                        if (!rating.isEmpty()) {
                            movieRatings.add(new MovieRating(j, Integer.parseInt(rating), userId, i));

                            for (User user : userList) {
                                if (user.getUserId() == userId) {
                                    user.addRating(Integer.parseInt(rating), j);
                                }
                            }
                        }
                    }
                }
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        /*
        Set mean ratings
        and % of Four ratings

         */
        for (Movies mList : moviesList) {
            for (MovieRating rList : movieRatings) {
                if (mList.getId() == rList.getId()) {
                    mList.setRating(rList.getRating());
                    mList.setNumberOfRatings();
                    mList.setMeanRating();
                    if (rList.getRating() >= 4) {
                        mList.setRatingsFourOrOver(rList.getRating());

                    }
                }
            }
        }

        /* Set ratings for four or over */
        for (Movies mList : moviesList) {
            mList.setPercentageOfFour();
        }



        /* Output 1 */
        System.out.println("Top 5 by mean rating");
        Collections.sort(moviesList, new Top5ByMeanRatingSort());
        Collections.reverse(moviesList);

        i = 0;
        try {
            PrintWriter writer = new PrintWriter("deliverable1.txt", "UTF-8");
            for (Movies mList : moviesList) {
                if (i < 5) {
                    System.out.println(mList.getMovieId());
                    writer.println(mList.getMovieId());
                }
                i++;
            }
            writer.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        /* Output 2 */
        System.out.println("Top 5 by % of Four or over");
        Collections.sort(moviesList, new PercentageOfFourSort());
        Collections.reverse(moviesList);

        i = 0;
        try {
            PrintWriter writer = new PrintWriter("deliverable2.txt", "UTF-8");
            for (Movies mList : moviesList) {
                if (i < 5) {
                    System.out.println(mList.getMovieId());
                    writer.println(mList.getMovieId());
                }
                i++;
            }
            writer.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



        /* Output 3 - number of ratings */
        System.out.println("Top 5 by number of ratings");

        Collections.sort(moviesList, new NumberOfRatingsSort());
        Collections.reverse(moviesList);

        String data = "";

        i = 0;
        for (Movies mList : moviesList) {
            if (i < numberOfLines) {
                System.out.println(mList.getMovieId());
                data += (mList.getMovieId());
                if (i < numberOfLines - 1) data += "\n";
            }
            i++;
        }
        writeFile("deliverable3.txt", data);


        /* Output 4 */
        ArrayList<Rating> outputMovieList = new ArrayList<Rating>();

        for (Movies allMovies : moviesList) {
            int ratedMovie = 0;
            int ratedBoth = 0;
            int position = 0;
            boolean oneSet = false;
            boolean twoSet = false;
            int posOne = 0;
            int posTwo = 0;
            boolean createEntry = true;
            int movieToCompare=1;  // Star Wars is 1
            int ratedY=0;
            int ratedX=0;

            for (MovieRating ratings : movieRatings) {

                if (ratings.getId() == allMovies.getId() || ratings.getId() == movieToCompare) {

                    if (ratings.getId() == allMovies.getId()) {
                        oneSet = true;
                        posOne = ratings.getPosition();
                        ratedY++;
                    }

                    if (ratings.getId() == 1) {
                        posTwo = ratings.getPosition();
                        twoSet = true;
                        ratedX++;
                    }
                    ;

                    if (oneSet && twoSet && (posOne == posTwo)) {
                        ratedBoth += 1;
                        oneSet = false;
                        twoSet = false;
                    }
                    position++;

                }


            }
            float simple = (float)ratedBoth /(float)ratedX;
            float percentage = (float) ratedBoth / (float) allMovies.getNumberOfRatings();

            /*
            The key question we are trying to solve here is how to compute the Star Wars
            association metric for Raiders movie.

            We have 2 options:
            Percentage of Star Wars raters who also rated the Raiders movie.
            Percentage of Raiders raters who also rated the Star Wars movie.

            Association metric for Option #1 is 50%:
            Number of users who rated StarWars = 16
            Number of users who rated both StarWars and Raiders = 8
            Association metric value = 8/16 = 50%

            Association metric for Option #2 is 72.7%:
            Number of users who rated Raiders = 11
            Number of users who rated both StarWars and Raiders = 8
            Association metric value = 8/11 = 72.7%

            Option #1 is aligned with prof. Konstat instructions, according to Paolo's comment.
            Option #2 is aligned with the value 72.7% provided by the instructors.

            Here:

            var simple = Option #1
            var percentage = Option #2
             */


            if(allMovies.getMovieId() == 1198){
                System.out.println("Raiders (should be 50%): "+simple+" "+allMovies.getMovieId()+" "+allMovies.getMovieName());

            }
            outputMovieList.add(new Rating(allMovies.getMovieId(), simple, allMovies.getId()));
        }
        Collections.sort(outputMovieList, new PercentageSort());
        Collections.reverse(outputMovieList);
        System.out.println("Top 5 by number of percentage of Star Wars");

        data = "";

        i = 0;
        for (Rating mList : outputMovieList) {
            if (i > 0 && i < numberOfLines + 1) {
                System.out.println(mList.getMovieId());
                data += (mList.getMovieId());
                if (i < numberOfLines) data += "\n";
            }
            i++;
        }
        writeFile("deliverable4.txt", data);


            /*
            System.out.println("id "+allMovies.getMovieName() +
                    " rtd "+allMovies.getNumberOfRatings()+
                    " ratedBoth: "+ratedBoth+
                    " perc: "+
                    percentage);
            */


    }

    static public void writeFile(String fileName, String data) {
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println(data);
            writer.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    Movies s;

}

