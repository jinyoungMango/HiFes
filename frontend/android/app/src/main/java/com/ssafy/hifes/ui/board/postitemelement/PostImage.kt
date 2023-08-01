package com.ssafy.hifes.ui.board.postitemelement

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
import myiconpack.User

@Composable
fun PostImage(
    postData: PostDto,
    userDataId : Int
) {
    var imageUrl : String? = null
    when(postData.postType){
        PostType.ASK.label -> {
            if(userDataId == postData.normalUserId){
                imageUrl = postData.picture
            }else{
                if(postData.hidden != null && postData.hidden!!){
                    imageUrl = null
                }else{
                    imageUrl = postData.picture
                }
            }
        }
        else -> {
            imageUrl = postData.picture
        }
    }
    AsyncImage(
        model = imageUrl,
        contentDescription = "게시글 이미지",
        placeholder = rememberVectorPainter(image = MyIconPack.User),
        modifier = Modifier
            .size(60.dp)
            .clip(RoundedCornerShape(16.dp))
    )

}