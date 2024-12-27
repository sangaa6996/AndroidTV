package com.dev.androidtv.data.repository_impl

import com.dev.androidtv.common.ApiMapper
import com.dev.androidtv.data.remote.api.MovieApiService
import com.dev.androidtv.data.remote.models.MovieDto
import com.dev.androidtv.domain.Movie
import com.dev.androidtv.repository.MovieRepository
import com.dev.androidtv.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MovieRepositoyImpl(
    private val movieApiService: MovieApiService,
    private val apiMapper: ApiMapper<List<Movie>,MovieDto>
):MovieRepository {
    override fun fetchDiscoverMovie(): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading());
        val movieDto = movieApiService.fetchDiscoverMovie()
        apiMapper.mapToDomain(movieDto).apply {
            emit(Response.Success(this))

        }
    }.catch {
        emit(Response.Error(it))
    }

    override fun fetchTrendingMovie(): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading());
        val movieDto = movieApiService.fetchTrendingMovie()
        apiMapper.mapToDomain(movieDto).apply {
            emit(Response.Success(this))

        }
    }.catch {
        emit(Response.Error(it))
    }
}