package com.habileducation.themovie.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * Created by Annas Surdyanto on 13/09/21.
 *
 */

actual val testCoroutineContext: CoroutineContext = Dispatchers.Main
actual fun runBlocking(block: suspend CoroutineScope.() -> Unit) =
    kotlinx.coroutines.runBlocking(testCoroutineContext) { this.block() }