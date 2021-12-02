package com.habileducation.themovie.data.factory

import android.content.Context
import com.habileducation.movie.data.source.AppDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

/**
 * Created by Annas Surdyanto on 06/09/21.
 *
 */

actual class DatabaseFactory(private val context: Context) {
    actual fun createDatabase(): SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, context, "appDatabase.db")
    }
}