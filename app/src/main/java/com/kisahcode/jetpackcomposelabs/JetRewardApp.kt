package com.kisahcode.jetpackcomposelabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kisahcode.jetpackcomposelabs.ui.theme.JetpackComposeLabsTheme

@Composable
fun JetRewardApp(
    modifier: Modifier = Modifier,
) {

}

@Preview(showBackground = true)
@Composable
fun JetHeroesAppPreview() {
    JetpackComposeLabsTheme {
        JetRewardApp()
    }
}