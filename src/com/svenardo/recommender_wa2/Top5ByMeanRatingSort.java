package com.svenardo.recommender_wa2;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: svenanders
 * Date: 09.09.13
 * Time: 08:37
 * To change this template use File | Settings | File Templates.
 */
public class Top5ByMeanRatingSort implements Comparator<Movies> {

    @Override
    public int compare(Movies o1, Movies o2) {
        return Double.compare(o1.getPercentageFourOrOver(), o2.getPercentageFourOrOver());  //To change body of implemented methods use File | Settings | File Templates.
    }
}

