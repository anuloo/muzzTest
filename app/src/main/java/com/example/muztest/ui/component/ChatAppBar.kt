package com.example.muztest.ui.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.muztest.ui.theme.AvenirBlack
import com.example.muztest.ui.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatAppBar(
    context: Context,
    title: String = "Sarah",
    onUserProfilePictureClick: (() -> Unit)? = null,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
        ),
        title = {
        Row {
            Surface(
                modifier = Modifier
                    .size(50.dp)
                    .padding(10.dp),
                shape = CircleShape,
                color = Color.LightGray
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .clickable { onUserProfilePictureClick?.invoke() })

            }
            Column(
                modifier = Modifier
                    .align(CenterVertically),
            ) {
                Text(
                    text = title,
                    style = AvenirBlack,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    },
        modifier = Modifier.statusBarsPadding(),
        navigationIcon = {
            IconButton(onClick = {
                Toast.makeText(
                    context,
                    "Back Clicked.\n(Not Available)",
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                Icon(
                    imageVector = Icons.Filled.ChevronLeft,
                    tint = PrimaryColor,
                    contentDescription = null,
                    modifier = Modifier
                        .offset((-15).dp)
                        .fillMaxHeight()
                        .aspectRatio(1f)
                )
            }
        },
        actions = {

            IconButton(
                onClick = {
                    Toast.makeText(
                        context,
                        "Menu Clicked.\n(Not Available)",
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                Icon(
                    imageVector = Icons.Filled.MoreHoriz,
                    tint = Color.LightGray,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                )
            }
        })
}

@Preview(showBackground = true)
@Composable
fun ChatAppBarPreview() {
    ChatAppBar(
        context = LocalContext.current
    )
}