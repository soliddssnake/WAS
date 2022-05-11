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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.compose.WASTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ibrahimengin.was.R
import com.ibrahimengin.was.model.PostListItem
import com.ibrahimengin.was.viewmodel.PostListViewModel

@Composable
fun PostItemRow(postListItem: PostListItem) {

    val pp = rememberAsyncImagePainter(model = postListItem.profilePhotoUrl)

    Column(
        modifier = Modifier.padding(horizontal = 5.dp).fillMaxWidth().wrapContentHeight()
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            ProfileImage(pp, 35.dp, 35.dp)
            Spacer(Modifier.width(5.dp))
            Text(postListItem.username)
        }

        Text(postListItem.explanation, style = MaterialTheme.typography.body1)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Outlined.ThumbUp, contentDescription = stringResource(R.string.like))
            }//TODO
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
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { viewModel.refresh(list) }) {
        LazyColumn(contentPadding = PaddingValues(vertical = 5.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
            items(items = list) { post ->
                PostItemRow(post)
            }
        }
    }
}

@Preview
@Composable
fun PreviewPostItem() {
    WASTheme {
    }
}