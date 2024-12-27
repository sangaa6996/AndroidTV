package com.dev.androidtv.utils

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

suspend fun <T> Flow<Response<T>>.collectAndHandle(
    onError:(Throwable?) -> Unit = {
        Log.e("collectAndHandle","collectAndHandle: error", it)
    },
    onLoading: () -> Unit = {},
    stateReducer: (T) -> Unit,
){
    collect { response ->
        when (response) {
            is Response.Error -> {
                onError(response.error)
            }

            is Response.Success -> {
                stateReducer(response.data)
            }

            is Response.Loading -> {
                onLoading()
            }
        }
    }
}