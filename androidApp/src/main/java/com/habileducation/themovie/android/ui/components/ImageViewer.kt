package id.co.vmk.loyal.android.ui.ViewHelper

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.habileducation.themovie.android.R
import com.habileducation.themovie.android.ui.components.PrimaryButton
import com.habileducation.themovie.android.ui.theme.keyLine3

/**
 * Created by Annas Surdyanto on 28/09/21.
 *
 */
@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageViewerScreen(
    title: String,
    imageUrl: String,
    isShowDialog: MutableState<Boolean>,
) {
    if (isShowDialog.value) {
        Dialog(
            onDismissRequest = { isShowDialog.value = false },
            content = {
                Box {
                    ConstraintLayout(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .background(
                                MaterialTheme.colors.surface,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(keyLine3)
                    ) {
                        val (titleRef, imageRef, buttonRef) = createRefs()
                        Text(
                            text = title,
                            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.SemiBold),
                            modifier = Modifier.constrainAs(titleRef) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            })
                        Image(
                            painter =
                            if (imageUrl.isNotEmpty()) rememberImagePainter(data = imageUrl) else painterResource(
                                id = R.drawable.default_image
                            ),
                            contentDescription = "image_view $imageUrl",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(30.dp))
                                .constrainAs(imageRef) {
                                    top.linkTo(titleRef.bottom, margin = keyLine3)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        )
                        PrimaryButton(text = "Dismiss", onClick = { isShowDialog.value = false },
                            modifier = Modifier.constrainAs(buttonRef) {
                                top.linkTo(imageRef.bottom, margin = keyLine3)
                                end.linkTo(parent.end)
                            })
                    }
                }
            }
        )
    }
}