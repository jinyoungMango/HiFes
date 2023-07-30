package com.example.hifes.ui.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hifes.R
import com.example.hifes.ui.common.top.TopWithBack
import com.example.hifes.ui.theme.Grey
import com.example.hifes.ui.theme.PrimaryPink
import com.example.hifes.ui.theme.SecondApricot

@Composable
fun LoginDetailScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopWithBack(title = stringResource(R.string.more_info_appbar_title))
        Spacer(modifier = Modifier.size(20.dp))
        ImageAdd()
        Spacer(modifier = Modifier.size(40.dp))
        TextFieldNickName()
        Spacer(modifier = Modifier.weight(1f))
        FinishButton(
            { Toast.makeText(it, "test", Toast.LENGTH_LONG).show() }
        )
    }
}

@Composable
fun ImageAdd() {
    Box() {
        Image(
            painterResource(id = R.drawable.icon_user),
            contentDescription = "profile image",
            modifier = Modifier
                .size(150.dp)
                .background(color = Grey, shape = CircleShape),
        )
        IconButton(
            onClick = { },
            modifier = Modifier
                .background(
                    color = PrimaryPink,
                    shape = CircleShape
                )
                .align(Alignment.BottomEnd)

        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_camera),
                contentDescription = "add profile image",
                tint = colorResource(id = R.color.white)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldNickName() {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text, onValueChange = {
            text = it
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = PrimaryPink),
        label = { Text(text = stringResource(id = R.string.more_info_edittext_hint)) }
    )
}

@Composable
fun FinishButton(onClick: (context: Context) -> Unit) {
    val context = LocalContext.current
    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 60.dp)) {
        Button(
            onClick = {
                onClick(context)
            },
            colors = ButtonDefaults.buttonColors(SecondApricot),
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = SecondApricot,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(40.dp, 0.dp),
        ) {
            Text(
                text = stringResource(id = R.string.more_info_finish_button),
                color = colorResource(R.color.black),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }

}

@Preview
@Composable
fun LoginDetailScreenPrev() {
    LoginDetailScreen(navController = rememberNavController())
}