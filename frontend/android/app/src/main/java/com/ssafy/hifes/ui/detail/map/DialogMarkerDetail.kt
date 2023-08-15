package com.ssafy.hifes.ui.detail.map

import android.util.Log
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ssafy.hifes.R
import com.ssafy.hifes.ui.theme.PrimaryPink
import com.ssafy.hifes.ui.theme.pretendardFamily


private const val TAG = "DialogMarkerDetail_하이페스"

@Composable
fun MarkerDetailDialog(title: String, content: String, time : String = "", onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = Modifier
                .width(220.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(12.dp),
            color = Color.White
        ) {
            CustomDialog(title = title, time = time, message = content, onDismiss = onDismissRequest)

        }
    }
}

@Composable
fun CustomDialog(
    title: String,
    message: String,
    time: String = "",
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
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color.Black)
        if (!time.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = time,
                color = Color.DarkGray,
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message,
            color = Color.Black,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(20.dp))

        Surface(
            shape = CircleShape,
            modifier = Modifier
                .size(34.dp)
                .clickable {
                    onDismiss()
                },
            color = PrimaryPink
        ) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = "Close",
                modifier = Modifier.size(16.dp),
                tint = Color.White
            )
        }
    }

}

@Preview
@Composable
fun MarkerPreview() {
    MarkerDetailDialog("title", "content", onDismissRequest = {})
}