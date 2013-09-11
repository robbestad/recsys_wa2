package com.svenardo.recommender_wa2;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: svenanders
 * Date: 09.09.13
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */
public class PercentageSort implements Comparator<Rating> {

    @Override
    public int compare(Rating o1, Rating o2) {
        return Double.compare(o1.getPercentage(), o2.getPercentage());  //To change body of implemented methods use File | Settings | File Templates.
    }
}

