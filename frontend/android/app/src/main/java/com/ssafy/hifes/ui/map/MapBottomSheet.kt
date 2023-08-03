package com.ssafy.hifes.ui.map

import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun MapBottomPrev() {
    MapBottomSheet()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapBottomSheet() {
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(false)

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            modalBottomSheetState.show()
        }
    }

    ModalBottomSheet(
        onDismissRequest = {  },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = Color.White
    ) {
        MapScreenContent()
    }
}
