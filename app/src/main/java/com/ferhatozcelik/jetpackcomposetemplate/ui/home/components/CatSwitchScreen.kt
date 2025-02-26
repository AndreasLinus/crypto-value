package com.ferhatozcelik.jetpackcomposetemplate.ui.home.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.ferhatozcelik.jetpackcomposetemplate.R
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.LightDarkModePreview

@Composable
fun CatSwitchScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isChecked by remember { mutableStateOf(false) }

        CatSwitch(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
        )
    }
}

@Composable
fun CatSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        thumbContent = {
            Icon(
                painter = if (checked) painterResource(id = R.drawable.round_pets_24) else painterResource(id = R.drawable.round_cruelty_free_24) ,
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
}

@LightDarkModePreview
@Composable
fun CatSwitchPreview() {
    MaterialTheme {
        Column {
            CatSwitch(checked = true, onCheckedChange = {})
            CatSwitch(checked = false, onCheckedChange = {})
        }
    }
}

