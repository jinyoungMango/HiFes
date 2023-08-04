package com.ssafy.hifes.ui.board.boarddetail.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssafy.hifes.R
import com.ssafy.hifes.ui.board.BoardViewModel
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Writecomment
import com.ssafy.hifes.ui.theme.PrimaryPink
import com.ssafy.hifes.ui.theme.pretendardFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentWriteComponent(
    viewModel: BoardViewModel,
    onGloballyPositioned:(LayoutCoordinates) -> Unit,
) {
    var text by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .background(color = Color.White)
            .onGloballyPositioned(onGloballyPositioned)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.size(6.dp))
        Row (verticalAlignment = Alignment.CenterVertically){
            Spacer(modifier = Modifier.size(18.dp))
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = PrimaryPink),
                placeholder = { Text(text = stringResource(id = R.string.board_detail_write_comment_hint)) },
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.weight(1f),
                textStyle = TextStyle(fontFamily = pretendardFamily, fontWeight = FontWeight.Normal)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Icon(
                imageVector = MyIconPack.Writecomment,
                contentDescription = "댓글 쓰기",
                tint = Color.Unspecified,
                modifier = Modifier.size(34.dp)
            )
            Spacer(modifier = Modifier.size(18.dp))
        }
        Spacer(modifier = Modifier.size(6.dp))
    }

}

@Composable
@Preview
fun PreviewCommentWriteComponent() {
  //  CommentWriteComponent(viewModel = BoardViewModel())
}