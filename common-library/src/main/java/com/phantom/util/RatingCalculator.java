package com.phantom.util;

public class RatingCalculator {

    public static  double updateRating(double oldRating, double newRating, int totalReviews) {

        if (totalReviews <= 0) {
            return Math.min(Math.round(newRating * 10.0) / 10.0, 5.0);
        }

        double updatedRating =
                ((oldRating * totalReviews) + newRating) / (totalReviews + 1);

        return Math.min(Math.round(updatedRating * 10.0) / 10.0, 5.0);
    }
}
