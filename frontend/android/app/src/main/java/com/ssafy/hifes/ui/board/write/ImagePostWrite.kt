package com.ssafy.hifes.ui.board.write

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Imageadd

private const val TAG = "ImagePostWrite"
@Composable
fun ImagePostWrite(
    imageUri : Uri?,
    onImageChange : (Uri)->Unit
) {
    val context = LocalContext.current
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

    if(imageUri == null || bitmap == null){
        Image(
            modifier = Modifier.clickable {
                getContent.launch("image/*")
            },
            imageVector = MyIconPack.Imageadd,
            contentDescription = ""
        )
    }else{
        Image(
            modifier = Modifier
                .clickable {
                    getContent.launch("image/*")
                }
                .padding(10.dp, 0.dp),
            bitmap = bitmap.asImageBitmap(),
            contentDescription = ""
        )
    }

}