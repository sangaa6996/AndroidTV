package com.dev.androidtv.repository

import com.dev.androidtv.domain.Movie
import com.dev.androidtv.utils.Response
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchDiscoverMovie(): Flow<Response<List<Movie>>>
    fun fetchTrendingMovie(): Flow<Response<List<Movie>>>
}