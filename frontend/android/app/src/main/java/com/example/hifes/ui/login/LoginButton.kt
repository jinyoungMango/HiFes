package com.example.hifes.ui.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hifes.R

@Composable
fun LoginButton(color: Color, title: String, onClick: (context: Context) -> Unit, textColor: Int) {
    val context = LocalContext.current
    Button(
        onClick = {
            onClick(context)
        },
        colors = ButtonDefaults.buttonColors(color),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = color, shape = RoundedCornerShape(12.dp))
    ) {
        Text(
            text = title,
            color = colorResource(textColor),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
fun LoginButtonsPrev() {
    Column() {
        LoginButton(
            color = colorResource(R.color.kakao_yellow),
            title = stringResource(R.string.kakao_login),
            onClick = { context ->
                Toast.makeText(context, context.getText(R.string.kakao_login), Toast.LENGTH_LONG)
                    .show()
            },
            textColor = R.color.black
        )
        LoginButton(
            color = colorResource(R.color.naver_green),
            title = stringResource(R.string.naver_login),
            onClick = { context ->
                Toast.makeText(context, context.getText(R.string.naver_login), Toast.LENGTH_LONG)
                    .show()
            },
            textColor = R.color.white
        )
    }
}