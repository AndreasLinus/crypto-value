package com.ferhatozcelik.jetpackcomposetemplate.ui.home.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ferhatozcelik.jetpackcomposetemplate.R
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.LightDarkModePreview


@Composable
fun CatSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    offText: String = "Dogs",
    onText: String = "Cats"
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = offText, color = MaterialTheme.colorScheme.onSurface)
        Spacer(modifier = Modifier.width(8.dp))
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
            thumbContent = {
                Icon(
                    painter = if (checked) painterResource(id = R.drawable.round_pets_24) else painterResource(
                        id = R.drawable.round_cruelty_free_24
                    ),
                    contentDescription = "Cat Icon",
                    modifier = Modifier.size(SwitchDefaults.IconSize),
                    tint = if (checked) Color.White else Color.Gray
                )
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                uncheckedThumbColor = Color.LightGray,
                checkedTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                uncheckedTrackColor = Color.LightGray.copy(alpha = 0.5f),
                checkedBorderColor = Color.Transparent,
                uncheckedBorderColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = onText, color = MaterialTheme.colorScheme.onSurface)
    }
}

@LightDarkModePreview
@Composable
fun CatSwitchPreviewOn() {
    MaterialTheme {
        Surface {
            Column {
                CatSwitch(checked = true, onCheckedChange = {})
            }
        }
    }
}

@LightDarkModePreview
@Composable
fun CatSwitchPreviewOff() {
    MaterialTheme {
        Surface {
            Column {
                CatSwitch(checked = false, onCheckedChange = {})
            }
        }
    }
}


