package io.github.slashgil.jolpica.domain.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> (suspend () -> T).asResultFlow(): Flow<Result<T>> = flow {
    emit(Result.Loading)
    try {
        val data = this@asResultFlow()
        emit(Result.Success(data))
    } catch (e: Throwable) {
        emit(Result.Error(e))
    }
}
