package com.ibrahimengin.was.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ModeComment
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.WASTheme
import com.ibrahimengin.was.R
import com.ibrahimengin.was.model.PostListItem
import com.ibrahimengin.was.viewmodel.PostListViewModel

@Composable
fun PostItemRow(postListItem: PostListItem) {

    Column(
        modifier = Modifier.padding(horizontal = 5.dp).fillMaxWidth().wrapContentHeight()
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            CustomImage(R.drawable.was_logo, null, 50.dp, 50.dp)
            Text(postListItem.username)
        }

        Text(postListItem.explanation, style = MaterialTheme.typography.body1)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Outlined.ThumbUp, contentDescription = stringResource(R.string.like))
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.ModeComment,
                    contentDescription = stringResource(R.string.comment)
                )
            }
        }

    }
}

@Composable
fun PostListLazyView(viewModel: PostListViewModel) {
    val list = viewModel.postList
    LazyColumn {
        items(items = list) { post ->
            PostItemRow(post)
        }
    }
}

@Preview
@Composable
fun PreviewPostItem() {
    WASTheme {
    }
}