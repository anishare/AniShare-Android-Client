package com.anishare.anishare.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.anishare.anishare.R
import com.anishare.anishare.domain.model.AnimeWithAnimeMAL
import com.anishare.anishare.domain.model.UserDataWithAnime

@Composable
fun DetailElementItem(
    userData: UserDataWithAnime? = null,
    animeData: AnimeWithAnimeMAL? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Surface {
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = userData?.animeWithAnimeMAL?.animeMAL?.main_picture?.medium ?: animeData?.animeMAL?.main_picture?.medium,
                        placeholder = painterResource(R.drawable.ic_launcher_foreground),
                        fallback = painterResource(id = R.drawable.ic_launcher_background),
                    ),
                    contentDescription = "Background",
                    modifier = Modifier
                        .height(32.dp)
                        .width(32.dp)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = userData?.animeWithAnimeMAL?.anime?.name ?: animeData?.anime?.name ?: "",
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                    userData?.let {
                        Text(
                            text = if (it.userData.isFinished) "Watching" else "Ongoing",
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier
                                .background(
                                   Color.LightGray
                                )
                                .padding(4.dp)
                        )
                        Text(
                            text = it.userData.fromUser,
                            style = MaterialTheme.typography.body1,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ElementItemPreview() {
    DetailElementItem()
}