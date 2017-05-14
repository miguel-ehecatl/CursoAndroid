package com.miguel.mywatchlist.auxiliares;

import com.miguel.mywatchlist.modelos.ModeloMovie;
import com.miguel.mywatchlist.modelos.MovieCredits;
import com.miguel.mywatchlist.modelos.MovieDetail;
import com.miguel.mywatchlist.modelos.MovieVideo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Creado por Miguel Ángel Hernández Muñoz
 * para Servicios de Software Ehecatl con fecha 14/04/2017.
 */

public interface TheMovieDBClient  {
    @GET("/3/search/movie")
    Call<ModeloMovie> getMovie(@Query("api_key")String api,
                               @Query("query") String busqueda,
                               @Query("language") String idioma);

    @GET("/3/movie/{movie_id}")
    Call<MovieDetail> getMovieDetail(@Path("movie_id") String id,
                                     @Query("api_key")String api,
                                     @Query("language") String idioma);

    @GET("/3/movie/{movie_id}/videos")
    Call<MovieVideo> getMovieVideo(@Path("movie_id") String id,
                                    @Query("api_key")String api,
                                    @Query("language") String idioma);

    @GET("/3/movie/{movie_id}/credits")
    Call<MovieCredits> getMovieCredits(@Path("movie_id") String id,
                                       @Query("api_key")String api);

    @GET("/3/movie/top_rated")
    Call<ModeloMovie> getTopMovies(@Query("api_key")String api,
                               @Query("language") String idioma);
}
