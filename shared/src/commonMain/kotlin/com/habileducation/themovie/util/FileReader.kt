package com.habileducation.themovie.util

/**
 * Created by Annas Surdyanto on 15/08/22.
 *
 */

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect class FileReader() {
    fun readFile(fileName: String): String
}