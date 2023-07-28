package com.example.hifes.ui.detail.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.hifes.R


@Preview
@Composable
fun MarkerDetailDialog() {
    Dialog(onDismissRequest = {}) {
        Surface(
            modifier = Modifier
                .width(200.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(12.dp),
            color = Color.White
        ) {
            CustomDialog(title = "title", message = "message") {

            }
        }
    }
}

@Composable
fun CustomDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = Color.Black,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Divider(color = Color.Black)
        Spacer(modifier = Modifier.height(12.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = message,
            color = Color.Black,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { onDismiss() },
            shape = CircleShape,
            modifier = Modifier.size(34.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.main)
            ),
        ) {
            Text(
                text = "X",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }

}