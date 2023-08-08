package com.ssafy.hifes.ui.login

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.SideEffect
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
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.ssafy.hifes.R
import com.ssafy.hifes.ui.common.PermissionDeniedScreen
import com.ssafy.hifes.ui.common.ProfileImg
import com.ssafy.hifes.ui.common.top.TopWithBack
import com.ssafy.hifes.ui.theme.Grey
import com.ssafy.hifes.ui.theme.PrimaryPink
import com.ssafy.hifes.ui.theme.SecondApricot

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LoginDetailScreen(
    navController: NavController
) {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val permissionList: List<String> =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.POST_NOTIFICATIONS
            )

        } else {
            listOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    val permissionState = rememberMultiplePermissionsState(permissions = permissionList )

    if(permissionState.allPermissionsGranted){ //모든 권한 허용
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopWithBack(navController, title = stringResource(R.string.more_info_appbar_title))
            Spacer(modifier = Modifier.size(20.dp))
            ProfileImg(imageUri = imageUri, onImageChange = { uri -> imageUri = uri })
            Spacer(modifier = Modifier.size(40.dp))
            TextFieldNickName()
            Spacer(modifier = Modifier.weight(1f))
            FinishButton(
                { Toast.makeText(it, "test", Toast.LENGTH_LONG).show() }
            )
        }
    }else if(permissionState.shouldShowRationale){//한번 거절했을때
        Toast.makeText(context, "사용을 위해서 허가가 필요합니다!", Toast.LENGTH_LONG).show()
        SideEffect {
            permissionState.launchMultiplePermissionRequest()
        }
    } else{ //최초
        Log.d("권한", "LoginDetailScreen: 최초")
        SideEffect {
            permissionState.launchMultiplePermissionRequest()
        }
        Column {
            PermissionDeniedScreen()
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