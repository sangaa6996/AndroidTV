package com.dev.androidtv.dagger

import com.dev.androidtv.common.ApiMapper
import com.dev.androidtv.data.mapper_impl.MovieApiMapperImpl
import com.dev.androidtv.data.remote.api.MovieApiService
import com.dev.androidtv.data.remote.models.MovieDto
import com.dev.androidtv.data.repository_impl.MovieRepositoyImpl
import com.dev.androidtv.domain.Movie
import com.dev.androidtv.repository.MovieRepository
import com.dev.androidtv.utils.K
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {
    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }
    @Provides
    @Singleton
    fun provideMovieRepository(
        movieApiService: MovieApiService,
        mapper: ApiMapper<List<Movie>, MovieDto>
    ): MovieRepository = MovieRepositoyImpl(
        movieApiService,
        mapper
    )

    @Provides
    @Singleton
    fun provideMovieMapper():ApiMapper<List<Movie>, MovieDto> = MovieApiMapperImpl()

    @Provides
    @Singleton
    fun provideMovieApiService():MovieApiService {
        val contentType = "application/json".toMediaType()
        val retrofit = Retrofit.Builder()
            .baseUrl(K.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build();

        return retrofit.create(MovieApiService::class.java)
    }
}