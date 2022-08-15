package com.habileducation.themovie.android.ui.movieDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.habileducation.themovie.android.R
import com.habileducation.themovie.android.util.TestTag
import com.habileducation.themovie.data.model.remote.MovieDetailAndReview
import com.habileducation.themovie.data.model.remote.ReviewData
import com.habileducation.themovie.android.ui.theme.keyLine2
import com.habileducation.themovie.android.ui.theme.keyLine3
import id.co.vmk.loyal.android.ui.ViewHelper.ImageViewerScreen

/**
 * Created by Annas Surdyanto on 18/11/21.
 *
 */

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieDetailScreenContent(data: MovieDetailAndReview) {
    val isShowDialog = remember { mutableStateOf(false) }
    val reviewIdExpanded = remember { mutableStateOf("") }
    if (isShowDialog.value) {
        data.movieDetail?.posterPath?.let {
            ImageViewerScreen(
                title = data.movieDetail?.title ?: "",
                imageUrl = data.movieDetail?.poster ?: "",
                isShowDialog = isShowDialog
            )
        }
    }
    LazyColumn(modifier = Modifier.padding(keyLine3)) {
        data.movieDetail?.let { movie ->
            item {
                ConstraintLayout(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val (photoRef, overviewRef, releaseDateRef, budgetRef, popularityRef, voteRef) = createRefs()
                    Image(
                        painter =
                        if (movie.posterPath?.isNotEmpty() == true) rememberImagePainter(data = movie.poster) else painterResource(
                            id = R.drawable.default_image
                        ),
                        contentDescription = movie.title,
                        modifier = Modifier
                            .constrainAs(photoRef) { top.linkTo(parent.top) }
                            .clickable { isShowDialog.value = true }
                            .fillMaxWidth()
                            .height(200.dp)
                            .testTag(TestTag.movieDetailIcon),
                        contentScale = ContentScale.Fit
                    )

                    movie.overview?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.constrainAs(overviewRef) {
                                top.linkTo(photoRef.bottom, margin = 12.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                width = Dimension.preferredWrapContent
                            }.testTag(TestTag.movieDetailOverview),
                            textAlign = TextAlign.Justify,
                        )
                    }

                    val addStyle =
                        MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.secondary)
                    Text(
                        text = "Release Date: ${movie.releaseDate}",
                        style = addStyle,
                        modifier = Modifier
                            .constrainAs(releaseDateRef) {
                                top.linkTo(overviewRef.bottom, margin = 12.dp)
                                start.linkTo(parent.start)
                                width = Dimension.preferredWrapContent
                            }.testTag(TestTag.movieDetailReleaseDate)
                    )

                    Text(
                        text = "Budget: ${movie.budget}",
                        style = addStyle,
                        modifier = Modifier
                            .constrainAs(budgetRef) {
                                top.linkTo(releaseDateRef.bottom)
                                start.linkTo(parent.start)
                            }.testTag(TestTag.movieDetailBudget)
                    )

                    Text(
                        text = "Popularity: ${movie.popularity}",
                        style = addStyle,
                        modifier = Modifier
                            .constrainAs(popularityRef) {
                                top.linkTo(releaseDateRef.top)
                                end.linkTo(parent.end)
                            }.testTag(TestTag.movieDetailPopularity)
                    )

                    Text(
                        text = "Vote Agerage: ${movie.voteAverage}",
                        style = addStyle,
                        modifier = Modifier
                            .constrainAs(voteRef) {
                                top.linkTo(budgetRef.top)
                                end.linkTo(parent.end)
                            }.testTag(TestTag.movieDetailVote)
                    )
                }
            }
        }
        item { Spacer(modifier = Modifier.size(keyLine3)) }
        data.review?.let { reviewList ->
            item {
                Text(
                    "Review",
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.SemiBold)
                )
            }
            items(reviewList.reviewData) { review ->
                Column(modifier = Modifier.padding(top = keyLine3)) {
                    Card(
                        elevation = 2.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.surface)
                    ) {
                        ConstraintLayout(
                            modifier = Modifier
                                .background(
                                    MaterialTheme.colors.surface,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .padding(keyLine2)
                        ) {

                            val (authorRef, contentRef, avatarRef, seeMoreRef) = createRefs()

                            Image(
                                painter =
                                if (!review.authorDetails.avatarPath.isNullOrEmpty()) rememberImagePainter(
                                    data = review.authorDetails.avatar
                                ) else painterResource(id = R.drawable.default_profile),
                                contentDescription = review.author,
                                modifier = Modifier
                                    .constrainAs(avatarRef) { top.linkTo(parent.top) }
                                    .size(30.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop,
                            )

                            Text(
                                text = review.author,
                                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.SemiBold),
                                modifier = Modifier.constrainAs(authorRef) {
                                    top.linkTo(parent.top)
                                    start.linkTo(avatarRef.end, margin = keyLine2)
                                },
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 2
                            )

                            Text(
                                text = review.content, style = MaterialTheme.typography.body1,
                                modifier = Modifier.constrainAs(contentRef) {
                                    top.linkTo(avatarRef.bottom, margin = keyLine2)
                                    start.linkTo(avatarRef.start)
                                }.clickable {
                                    showMore(reviewIdExpanded, review)
                                },
                                //textAlign = TextAlign.Justify,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = if (reviewIdExpanded.value.equals(review.id)) 100 else 3
                            )

                            Text(
                                text = if (reviewIdExpanded.value.equals(review.id)) "See less" else "See more",
                                style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.secondary),
                                modifier = Modifier
                                    .constrainAs(seeMoreRef) {
                                        top.linkTo(contentRef.bottom)
                                        start.linkTo(avatarRef.start)
                                    }
                                    .clickable {
                                        showMore(reviewIdExpanded, review)
                                    }
                            )
                        }
                    }
                    Spacer(
                        modifier = Modifier.size(keyLine3)
                    )
                }
            }
        }
        if (data.review?.reviewData.isNullOrEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    Text(
                        text = "No review yet.",
                        style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.secondary),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

private fun showMore(reviewIdExpanded: MutableState<String>, review: ReviewData) {
    if (reviewIdExpanded.value.equals("")) reviewIdExpanded.value = review.id
    else reviewIdExpanded.value = ""
}