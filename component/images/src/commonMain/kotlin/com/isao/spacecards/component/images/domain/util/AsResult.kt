package com.isao.spacecards.component.images.domain.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

// The extension function for Flow that wraps the Flow's contents in a Result
fun <T> Flow<T>.asResult(): Flow<Result<T>> = this
  .map { Result.success(it) }
  .catch { emit(Result.failure(it)) }
