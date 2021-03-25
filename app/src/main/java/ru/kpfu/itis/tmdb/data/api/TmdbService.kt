package ru.kpfu.itis.tmdb.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.kpfu.itis.tmdb.data.api.response.DetailsResponse
import ru.kpfu.itis.tmdb.data.api.response.MovieResponse

interface TmdbService {

    @GET("movie/popular")
    suspend fun getPopularMovies():MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("region") region: String
    ): MovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("region") region: String
    ): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("region") region: String
    ): MovieResponse

    @GET("tv/on_the_air")
    suspend fun getTvOnTheAir(): MovieResponse

    @GET("tv/airing_today")
    suspend fun getTvAiringToday(): MovieResponse

    @GET("tv/popular")
    suspend fun getTvPopular(): MovieResponse

    @GET("tv/top_rated")
    suspend fun getTvTopRated(): MovieResponse

    @GET("search/multi")
    suspend fun getMultiSearch(
        @Query("query") searchQuery: String
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getDetails(
        @Path("movie_id") movie_id: Int,
        @Query("region") region: String
    ): DetailsResponse
}