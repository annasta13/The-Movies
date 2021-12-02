package com.habileducation.themovie.util

import com.habileducation.themovie.data.FakeRemoteDataSource
import kotlinx.coroutines.flow.first
import org.junit.Test

/**
 * Created by Annas Surdyanto on 23/11/21.
 *
 */

class JsonConverterTest {
    @Test
    fun testJson() = kotlinx.coroutines.runBlocking {
        val result = FakeRemoteDataSource().fetchMovies("popular").first()
        assert(result.getOrNull()?.movieList?.get(0)?.id == 566525L)
    }

    @Test
    fun readJsonFromAssets(){
        val file = this::class.java.classLoader!!.getResourceAsStream("popular_movies.json").bufferedReader().use { it.readText() }
        print("check file $file")
        val string = file.toString()
        print("check file string $string")
        assert(string.contains("{"))
    }
}