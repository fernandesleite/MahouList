package me.fernandesleite.mahoulist.core.extension

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import me.fernandesleite.mahoulist.core.util.Response
import retrofit2.HttpException
import java.io.IOException

object NetworkExtensions {
    fun <T : Any> Flow<T>.getResponse(): Flow<Response<T>> = flow {
        try {
            onStart { emit(Response.Loading()) }
            collect { value -> emit(Response.Success(data = value)) }
        } catch (error: Throwable) {
            when (error) {
                is HttpException -> {
                    emit(Response.NetworkError(error.code(), error.message()))
                }

                is IOException -> {
                    emit(Response.Error(error.message ?: "Generic Error"))
                }
            }
        }
    }

    fun <T : Any> Response<T>.onErrorDo(callback: (errorMessage: String) -> Unit) = run {
        if (this is Response.Error || this is Response.NetworkError) {
            this.message?.let { callback(message) }
        }
    }
}