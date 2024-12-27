package com.dev.androidtv.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.dev.androidtv.R
import com.dev.androidtv.domain.Movie
import com.dev.androidtv.utils.K

@Composable
fun TopContent(
    modifier: Modifier = Modifier,
    movie: Movie,
    onMovieClick:(id:Int) -> Unit
) {
    val imgRequest = ImageRequest.Builder(LocalContext.current)
        .data("${K.BASE_IMAGE_URL}${movie.posterPath}")
        .crossfade(true)
        .build()
    Box(modifier = modifier
        .fillMaxWidth()
        .clickable {
            onMovieClick(movie.id)
        }){
        AsyncImage(
            model = imgRequest,
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
            onError = {
                it.result.throwable.printStackTrace()
            },
            placeholder = painterResource(id = R.drawable.bg_image_movie))
    }
}