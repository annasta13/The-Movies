package com.habileducation.themovie.util

import com.habileducation.themovie.data.model.remote.MovieResponse
import io.ktor.http.ContentType.Application.Json
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

/**
 * Created by Annas Surdyanto on 13/09/21.
 *
 */

expect val testCoroutineContext: CoroutineContext
expect fun runBlocking(block: suspend CoroutineScope.() -> Unit)