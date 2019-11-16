package com.company.TopMovies;

/**
 * Abstract class for Showing top5 movies
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-12
 */
public abstract class Top5CurrentMovies extends CurrentShowingMovies {
    /**
     * Abstract print method which the subclasses override to display their own top 5 movies.
     */
    public abstract void printTop5Movies();
}
