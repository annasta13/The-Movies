package com.habileducation.themovie.util

/**
 * Created by Annas Surdyanto on 15/08/22.
 *
 */
actual class FileReader actual constructor(){
    actual fun readFile(fileName: String): String {
        return this::class.java.classLoader!!.getResourceAsStream(fileName).bufferedReader()
            .use { it.readText() }
    }
}