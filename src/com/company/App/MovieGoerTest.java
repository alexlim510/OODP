package com.company.App;
import com.company.Entity.Cineplex;
import com.company.Entity.Movie;
import com.company.Utils.Utils;
import com.company.View.MovieGoerUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class MovieGoerTest {

   public static void main(String[] args) {
   	// TODO Auto-generated method stub
      /*try {
         ArrayList<Movie> MovieArray = (ArrayList<Movie>) Utils.getObjectInputStream("movie.txt").readObject();

         Movie[] array = MovieArray.toArray(new Movie[MovieArray.size()]);
         UI.getMovieListingView();
      	
      	 
         ArrayList<Cineplex> CineplexArray = (ArrayList<Cineplex>)Utils.getObjectInputStream("cineplex.txt").readObject();
         Cineplex[] array1 = CineplexArray.toArray(new Cineplex[CineplexArray.size()]);*/
      MovieGoerUI UI = new MovieGoerUI();
         UI.getHomeView();
     /* }
      catch (IOException e) {
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      
      	
      }*/
   }

}
