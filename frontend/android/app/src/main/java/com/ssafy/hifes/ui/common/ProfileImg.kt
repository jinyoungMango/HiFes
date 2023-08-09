package com.ssafy.hifes.ui.common

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Camera

@Composable
fun ProfileImg(
    imageUri: Uri?,
    onImageChange: (Uri)->Unit
) {    val context = LocalContext.current
    val getContent = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if(uri!=null)
            onImageChange(uri)
    }
    // API level 28 이하는 MediaStore.Images.Media.getBitmap 사용 (deprecated)
    // 그 이상부터 ImageDecoder.createSource 사용
    val bitmap = imageUri?.let {
        if(Build.VERSION.SDK_INT<28){
            MediaStore.Images.Media.getBitmap(context.contentResolver, it)
        }else{
            val source = ImageDecoder.createSource(context.contentResolver, it)
            ImageDecoder.decodeBitmap(source)
        }
    }

    Box() {
        if(imageUri == null || bitmap == null){
            Image(
                ColorPainter(Color.Gray),
                contentDescription = "profile image",
                modifier = Modifier
                    .size(150.dp)
                    .padding(8.dp)
                    .clip(CircleShape),
            )
        }else{
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "profile image",
                modifier = Modifier
                    .size(160.dp)
                    .padding(8.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        IconButton(
            onClick = { getContent.launch("image/*") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 24.dp)) {
            Image(
                imageVector = MyIconPack.Camera, // SVG 파일을 지정
                contentDescription = "Search",
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Preview
@Composable
fun ProfilePrev() {
    var uri by remember { mutableStateOf<Uri?>(null) }

    ProfileImg(uri, {})
}