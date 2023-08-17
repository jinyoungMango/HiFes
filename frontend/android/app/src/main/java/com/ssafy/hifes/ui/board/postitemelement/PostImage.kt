package com.ssafy.hifes.ui.board.postitemelement

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ssafy.hifes.data.model.PostDto
import com.ssafy.hifes.ui.board.boardcommon.PostType
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Imagenotfound

private const val TAG = "PostImage"
@Composable
fun PostImage(
    postData: PostDto,
    userDataId : Int
) {
    Log.d(TAG, "PostImage: ${postData.createdBy} ${userDataId} : ${postData.createdBy == userDataId}")
    var imageUrl : String? = null
    when(postData.postType){
        PostType.ASK.label -> {
            if(userDataId == postData.createdBy){
                imageUrl = postData.imagePath
            }else{
                if(postData.isHidden != null && postData.isHidden!!){
                    imageUrl = null
                }else{
                    imageUrl = postData.imagePath
                }
            }
        }
        else -> {
            imageUrl = postData.imagePath
        }
    }
    if(imageUrl != null){
        AsyncImage(
            model = imageUrl,
            contentDescription = "게시글 이미지",
            placeholder = rememberVectorPainter(image = MyIconPack.Imagenotfound),
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(16.dp)),
            error = rememberVectorPainter(image = MyIconPack.Imagenotfound)
        )
    }
}