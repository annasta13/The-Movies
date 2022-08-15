package com.habileducation.themovie.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.habileducation.themovie.android.R
import com.habileducation.themovie.android.ui.theme.keyLine2
import com.habileducation.themovie.data.model.local.Movie

/**
 * Created by Annas Surdyanto on 15/08/22.
 *
 */

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieItemView(
    onMovieClicked: (movieId: Long) -> Unit,
    movie: Movie
) {
    Card(
        elevation = 2.dp,
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .clickable { onMovieClicked(movie.movieId) }
            .padding(bottom = keyLine2)
    ) {
        ConstraintLayout(modifier = Modifier.padding(keyLine2)) {
            val (imageRef, titleRef, descRef) = createRefs()
            Image(
                painter = if (movie.posterPath.isEmpty()) painterResource(id = R.drawable.default_image) else rememberImagePainter(
                    data = movie.poster
                ),
                contentDescription = movie.originalTitle,
                modifier = Modifier
                    .size(130.dp)
                    .constrainAs(imageRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                contentScale = ContentScale.Fit
            )
            Text(
                text = movie.originalTitle,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.constrainAs(titleRef) {
                    top.linkTo(parent.top)
                    start.linkTo(imageRef.end, margin = keyLine2)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                })
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .constrainAs(descRef) {
                        top.linkTo(titleRef.bottom)
                        start.linkTo(titleRef.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(imageRef.bottom)
                        width = Dimension.preferredWrapContent
                    },
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 7
            )
        }
    }
}