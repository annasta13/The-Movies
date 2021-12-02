package com.habileducation.themovie.data.factory

import com.habileducation.movie.data.source.AppDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

/**
 * Created by Annas Surdyanto on 06/09/21.
 *
 */


actual class DatabaseFactory {
    actual fun createDatabase(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, "appDatabase.db")
    }
}