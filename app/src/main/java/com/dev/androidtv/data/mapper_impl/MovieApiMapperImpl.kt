package com.dev.androidtv.data.mapper_impl

import com.dev.androidtv.common.ApiMapper
import com.dev.androidtv.data.remote.models.MovieDto
import com.dev.androidtv.domain.Movie
import com.dev.androidtv.utils.GenreConstants

class MovieApiMapperImpl:ApiMapper<List<Movie>,MovieDto> {
    override fun mapToDomain(apiDto: MovieDto): List<Movie> {
        return apiDto.results?.map { result ->
            Movie(
                backdropPath = formatEmptyValue(result?.backdropPath),
                genreIds = formatGenre(result?.genreIds),
                id = result?.id ?: 0,
                originalLanguage = formatEmptyValue(result.originalLanguage),
                originalTitle = formatEmptyValue(result.originalTitle),
                overview = formatEmptyValue(result.overview),
                popularity = result?.popularity ?: 0.0,
                posterPath = formatEmptyValue(result.posterPath),
                adult = result.adult ?: false,
                releaseDate = formatEmptyValue(result.releaseDate),
                title = formatEmptyValue(result.title),
                video = result.video ?:false,
                voteAverage = result?.voteAverage ?: 0.0,
                voteCount = result?.voteCount ?: 0,
            )
        } ?: emptyList()
    }

    private fun formatEmptyValue(value: String?, default: String = ""):String{
        if(value.isNullOrEmpty()) return "Unknown $default"
        return value;
    }

    private fun formatGenre(genreIds: List<Int?>?): List<String>{
        return genreIds?.map { GenreConstants.getGenreNameById(it ?: 0) } ?: emptyList()
    }
}