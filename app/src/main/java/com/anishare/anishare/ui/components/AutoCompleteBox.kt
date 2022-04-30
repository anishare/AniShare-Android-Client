package com.anishare.anishare.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp

@Composable
fun <T> AutoCompleteBox(
    modifier: Modifier = Modifier,
    query: String,
    queryLabel: String,
    onQueryChanged: (String) -> Unit = {},
    predictions: List<T>,
    onClearClick: () -> Unit = {},
    onItemClick: (T) -> Unit = {},
    itemContent: @Composable (T) -> Unit = {},
) {
    val view = LocalView.current
    var isSearching by remember { mutableStateOf(false) }
    
    Column(
        modifier = modifier.heightIn(max = TextFieldDefaults.MinHeight * 6)
    ) {
        QuerySearch(
            query = query,
            label = queryLabel,
            onQueryChanged = onQueryChanged,
            onDoneActionClick = {
                view.clearFocus()
            },
            onClearClick = {
                onClearClick()
            },
            onFocusChanged = {
                isSearching = it.hasFocus
            }
        )

        if (isSearching) {
            AnimatedVisibility(visible = isSearching) {
                LazyColumn(
                    modifier = Modifier.border(
                        border = BorderStroke(2.dp, MaterialTheme.colors.onBackground),
                        shape = RoundedCornerShape(8.dp)
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(predictions) { prediction ->
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .clickable {
                                    view.clearFocus()
                                    onItemClick(prediction)
                                }
                        ) {
                            itemContent(prediction)
                        }
                    }
                }
            }
        }
    }
}