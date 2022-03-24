package com.anishare.anishare.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.anishare.anishare.R
import com.anishare.anishare.model.AnimeDTO
import com.anishare.anishare.model.AnimeMAL
import com.anishare.anishare.model.MainPicture

@Composable
fun ElementItem(modifier: Modifier = Modifier, data: AnimeDTO, malData: AnimeMAL? = null) {
    Card(
        modifier = modifier,
        elevation = 4.dp,
        ) {
        if (malData != null) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = malData.main_picture?.medium,
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    fallback = painterResource(id = R.drawable.ic_launcher_background)
                ),
                contentDescription = "Background",
                modifier = Modifier.fillMaxWidth()
            )
        }
        Text(text = data.name)
    }
}

@Preview(showBackground = true)
@Composable
fun ElementItemPreview() {
    ElementItem(
        data = AnimeDTO.mock(),
        malData = AnimeMAL.mock()
    )
}