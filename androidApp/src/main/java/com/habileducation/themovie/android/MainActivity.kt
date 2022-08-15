package com.habileducation.themovie.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.habileducation.themovie.android.ui.navGraph.NavGraph
import dagger.hilt.android.AndroidEntryPoint
import com.habileducation.themovie.android.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                NavGraph()
            }
        }
    }
}
