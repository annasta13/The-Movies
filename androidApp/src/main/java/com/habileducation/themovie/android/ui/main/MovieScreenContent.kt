package com.habileducation.themovie.android.ui.main.popular

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.habileducation.themovie.android.R
import com.habileducation.themovie.android.util.TestTag
import com.habileducation.themovie.data.model.local.Movie
import com.habileducation.themovie.data.model.remote.MovieResponse
import com.habileducation.themovie.data.model.remote.asDomainMovieList
import id.co.vmk.loyal.android.theme.DarkTheme
import id.co.vmk.loyal.android.theme.keyLine2
import id.co.vmk.loyal.android.theme.keyLine3

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieContentScreen(
    movieList: List<Movie>,
    onMovieClicked: (movieId: Long) -> Unit
) {
    Scaffold(
        Modifier.background(MaterialTheme.colors.background)
    ) {
        LazyColumn(contentPadding = PaddingValues(keyLine3)) {
            items(movieList) {
                Column {
                    Card(
                        elevation = 2.dp,
                        modifier = Modifier
                            .background(MaterialTheme.colors.surface)
                            .clickable { onMovieClicked(it.movieId) }
                    ) {
                        ConstraintLayout(modifier = Modifier.padding(keyLine2)) {
                            val (imageRef, titleRef, descRef) = createRefs()
                            Image(
                                painter = if (it.posterPath.isEmpty()) painterResource(id = R.drawable.default_image) else rememberImagePainter(
                                    data = it.poster
                                ),
                                contentDescription = it.originalTitle,
                                modifier = Modifier
                                    .size(130.dp)
                                    .constrainAs(imageRef) {
                                        top.linkTo(parent.top)
                                        start.linkTo(parent.start)
                                    },
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                text = it.originalTitle,
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.constrainAs(titleRef) {
                                    top.linkTo(parent.top)
                                    start.linkTo(imageRef.end, margin = keyLine2)
                                    end.linkTo(parent.end)
                                    width = Dimension.fillToConstraints
                                })
                            Text(
                                text = it.overview,
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier
                                    .constrainAs(descRef) {
                                        top.linkTo(titleRef.bottom)
                                        start.linkTo(titleRef.start)
                                        end.linkTo(parent.end)
                                        bottom.linkTo(imageRef.bottom)
                                        height = Dimension.fillToConstraints
                                        width = Dimension.preferredWrapContent
                                    },
                                textAlign = TextAlign.Start,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 7
                            )
                        }
                    }
                    if (movieList.last().movieId != it.movieId) Spacer(modifier = Modifier.size(8.dp))
                }
            }
        }
    }
}

@Composable
@Preview
fun MovieContentScreenPreview() = DarkTheme {
    MovieContentScreen(
        movieList = MovieResponse.mocked.movieList.asDomainMovieList(),
        onMovieClicked = { })
}