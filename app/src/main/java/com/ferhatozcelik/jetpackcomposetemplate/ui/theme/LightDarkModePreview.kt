package com.ferhatozcelik.jetpackcomposetemplate.ui.theme

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview


@Preview(
    name = "regular font size light mode",
    group = "regular font size",
    fontScale = 1f,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "regular font size dark mode",
    group = "regular font size",
    fontScale = 1f,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "large font light mode",
    group = "large font size",
    fontScale = 1.3f,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "large font dark mode",
    group = "large font size",
    fontScale = 1.3f,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "max font light mode",
    group = "max font size",
    fontScale = 1.5f,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "max font dark mode",
    group = "max font size",
    fontScale = 1.5f,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
annotation class LightDarkModePreview
