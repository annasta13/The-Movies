package com.habileducation.themovie.util

import java.io.InputStreamReader

/**
 * Created by aydbtiko on 6/10/2021.
 *
 */
class JsonResponseBuilder {

    fun getJsonContent(fileName: String): String {
        return InputStreamReader(javaClass.classLoader!!.getResourceAsStream(fileName)).use { it.readText() }
    }

}