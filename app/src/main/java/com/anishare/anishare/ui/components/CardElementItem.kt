package com.anishare.anishare.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anishare.anishare.R
import com.anishare.anishare.domain.model.AnimeMALNode
import com.anishare.anishare.domain.model.UserData

@Composable
fun CardElementItem(
    modifier: Modifier = Modifier,
    userData: UserData? = null,
    malNode: AnimeMALNode? = null,
    onClick: (() -> Unit)? = null,
    selected: Boolean? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .selectable(
                selected = selected ?: false,
                onClick = onClick ?: {}
            )
        ,
        shape = RoundedCornerShape(15.dp),
        border = if (selected == true) BorderStroke(1.dp, MaterialTheme.colors.primary) else null,
        elevation = 5.dp
    ) {
        Box(modifier = Modifier.height(250.dp).width(150.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(
                        malNode?.node?.main_picture?.medium ?:
                        "https://cdn.myanimelist.net/images/anime/1105/119414l.jpg"
                    )
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "Test",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                (userData?.item?.name ?: malNode?.node?.title)?.let {
                    Text(
                        text = it,
                        style = TextStyle(color = Color.White, fontSize = 16.sp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardElemeneItem() {
    CardElementItem(userData = UserData.mock())
}