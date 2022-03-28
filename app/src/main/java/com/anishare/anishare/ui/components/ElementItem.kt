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
import com.anishare.anishare.model.AnimeMAL
import com.anishare.anishare.model.UserResponse

@Composable
fun ElementItem(modifier: Modifier = Modifier, data: UserResponse) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Surface {
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()) {
                if (data.item.malID.isNotEmpty()) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = AnimeMAL.mock().main_picture?.medium,
                            placeholder = painterResource(R.drawable.ic_launcher_foreground),
                            fallback = painterResource(id = R.drawable.ic_launcher_background),
                        ),
                        contentDescription = "Background",
                        modifier = Modifier
                            .height(32.dp)
                            .width(32.dp)
                            .clip(CircleShape)
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = data.item.name,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if (data.isFinished) "Watching" else "Ongoing",
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .background(
                               Color.LightGray
                            )
                            .padding(4.dp)
                    )
                    Text(
                        text = data.fromUser,
                        style = MaterialTheme.typography.body1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ElementItemPreview() {
    ElementItem(data = UserResponse.mock())
}