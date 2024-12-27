package com.dev.androidtv.data.remote.api

import com.dev.androidtv.BuildConfig
import com.dev.androidtv.data.remote.models.MovieDto
import com.dev.androidtv.utils.K
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET(K.MOVIE_ENDPOINT)
    suspend fun fetchDiscoverMovie(
        @Query("api_key") apiKey: String = BuildConfig.apiKey,
        @Query("include_adult") includeAdult: Boolean = false
    ):MovieDto

    @GET(K.TRENDING_MOVIE_ENDPOINT)
    suspend fun fetchTrendingMovie(
        @Query("api_key") apiKey: String = BuildConfig.apiKey,
        @Query("include_adult") includeAdult: Boolean = false
    ):MovieDto
}