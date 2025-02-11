package com.ferhatozcelik.jetpackcomposetemplate.ui.home.components

import android.provider.MediaStore.Images
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ferhatozcelik.jetpackcomposetemplate.R
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.LightDarkModePreview

@Composable
fun CurrencySelectorSwitch() {
    Card(modifier = Modifier.background(MaterialTheme.colorScheme.onSurface)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.flag_for_sweden_svgrepo_com),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "SEK", color = MaterialTheme.colorScheme.onSurface)
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = true,
                onCheckedChange = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "USD", color = MaterialTheme.colorScheme.onSurface)
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.united_states_svgrepo_com),
                contentDescription = null
            )
        }
    }
}

@LightDarkModePreview
@Composable
fun CurrencySelectorSwitchPreview() {
    MaterialTheme {
        Surface {
            CurrencySelectorSwitch()
        }
    }
}