package com.ibrahimengin.was.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import com.ibrahimengin.was.R

@Composable
fun CustomImage(
    customImageId: Int,
    descriptionImage: String?,
    height: Dp,
    width: Dp,
    paddingStart: Dp? = 0.dp,
    paddingTop: Dp? = 0.dp,
    paddingEnd: Dp? = 0.dp,
    paddingBottom: Dp? = 0.dp
) {
    Image(
        bitmap = ImageBitmap.imageResource(id = customImageId),
        contentDescription = descriptionImage,
        modifier = Modifier.size(width, height)
            .padding(start = paddingStart!!, top = paddingTop!!, end = paddingEnd!!, bottom = paddingBottom!!)
    )
}

@Composable
fun DownloadImage(
    painter: AsyncImagePainter,
    descriptionImage: String?,
    height: Dp,
    width: Dp,
    paddingStart: Dp? = 0.dp,
    paddingTop: Dp? = 0.dp,
    paddingEnd: Dp? = 0.dp,
    paddingBottom: Dp? = 0.dp
) {
    Image(
        painter = painter,
        contentDescription = descriptionImage,
        modifier = Modifier.size(width, height)
            .padding(start = paddingStart!!, top = paddingTop!!, end = paddingEnd!!, bottom = paddingBottom!!),
    )
}

@Composable
fun ProfileImage(
    painter: AsyncImagePainter,
    height: Dp,
    width: Dp
) {
    Image(
        painter = painter,
        contentDescription = stringResource(R.string.profilePhoto),
        modifier = Modifier.size(width, height).clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun PreviewImage() {
    val logo = if (isSystemInDarkTheme()) R.drawable.was_logo_dark else R.drawable.was_logo_light
    CustomImage(logo, null, 300.dp, 300.dp, 25.dp, 0.dp, 25.dp)
}