package com.ferhatozcelik.jetpackcomposetemplate.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.LightDarkModePreview

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GradientCircularProgressIndicator(
            modifier = Modifier.size(64.dp), gradientColors = listOf(
                MaterialTheme.colorScheme.primary, Color.Blue
            )
        )
        Text(
            text = "Loading...",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@LightDarkModePreview
@Composable
fun LoadingScreenPreview() {
    MaterialTheme {
        Surface {
            LoadingScreen()
        }
    }
}



