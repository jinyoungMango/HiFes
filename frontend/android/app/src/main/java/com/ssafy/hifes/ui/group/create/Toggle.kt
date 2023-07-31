package com.example.hifes.ui.group.create

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ssafy.hifes.ui.theme.PrimaryPink

@Composable
fun Toggle() {
    var isChecked by remember { mutableStateOf(false) }

    Switch(
        checked = isChecked,
        onCheckedChange = { isChecked = !isChecked },
        colors = SwitchDefaults.colors(checkedTrackColor = PrimaryPink, uncheckedTrackColor = Color.White, uncheckedBorderColor = Color.LightGray)
        )

}

@Preview
@Composable
fun TogglePrev() {
    Toggle()
}