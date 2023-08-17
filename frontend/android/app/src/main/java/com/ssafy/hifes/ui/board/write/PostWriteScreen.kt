package com.ssafy.hifes.ui.board.write

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.StepSize
import com.ssafy.hifes.R
import com.ssafy.hifes.data.local.AppPreferences
import com.ssafy.hifes.data.model.PostWriteDto
import com.ssafy.hifes.ui.board.BoardViewModel
import com.ssafy.hifes.ui.board.boardcommon.PostType
import com.ssafy.hifes.ui.common.top.TopWithBack
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Emptystar
import com.ssafy.hifes.ui.iconpack.myiconpack.Filledstar
import com.ssafy.hifes.ui.main.MainViewModel
import com.ssafy.hifes.ui.theme.PrimaryPink
import com.ssafy.hifes.ui.theme.pretendardFamily


private const val TAG = "PostWriteScreen"

@Composable
fun PostWriteScreen(
    navController: NavController,
    viewModel: BoardViewModel,
    mainViewModel: MainViewModel
) {
    val userId = AppPreferences.getUserId()
    val context = LocalContext.current
    val boardType = viewModel.boardType.observeAsState()
    val scrollState = rememberScrollState()
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var rating: Float by remember { mutableStateOf(5f) }
    var isHidden by remember { mutableStateOf(false) }
    var titleText by remember { mutableStateOf("") }
    var contentText by remember { mutableStateOf("") }
    var msgPostWrite = viewModel.msgPostWrite.observeAsState()
    var postWriteState = viewModel.postWriteStateType.observeAsState()
    val selectedFestival = mainViewModel.selectedFestival

    LaunchedEffect(postWriteState.value) {
        Log.d(TAG, "PostWriteScreen: ${postWriteState.value?.label}")
        if (postWriteState.value == PostWriteStateType.SUCCESS) {
            viewModel.initWriteState()
            Toast.makeText(context, "게시글 작성 완료", Toast.LENGTH_LONG).show()
            navController.popBackStack()
        }
    }

    Scaffold(
        topBar = {
            TopWithBack(
                navController,
                title = stringResource(id = R.string.board_write_appbar_title),
                btn = true,
                btnText = stringResource(id = R.string.board_write_finish),
                onClick = {
                    if (titleText == "" || contentText == "") {
                        Toast.makeText(context, "제목과 내용을 채워주세요!", Toast.LENGTH_LONG).show()
                    } else {
                        viewModel.postWrite(
                            context,
                            PostWriteDto(
                                boardType.value!!.label,
                                titleText,
                                contentText,
                                userId!!.toInt(),
                                isHidden,
                                selectedFestival,
                                rating
                            ),
                            imageUri
                        )
                    }
                })
        },
        content = { it ->
            Column(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (boardType.value == PostType.ASK) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp, 5.dp), horizontalAlignment = Alignment.End
                    ) {
                        setHiddenPostWrite(isHidden, { isHidden = !isHidden })
                    }
                    Spacer(modifier = Modifier.size(20.dp))
                }
                if (boardType.value == PostType.REVIEW) {
                    Spacer(modifier = Modifier.size(30.dp))
                    RatingPostWrite(rating, { ratingValue -> rating = ratingValue })
                    Spacer(modifier = Modifier.size(30.dp))
                }
                ImagePostWrite(imageUri, { uri -> imageUri = uri })
                Spacer(modifier = Modifier.size(20.dp))
                Divider()
                Spacer(modifier = Modifier.size(4.dp))
                TextFieldPostTitle(titleText, { it -> titleText = it })
                Spacer(modifier = Modifier.size(4.dp))
                Divider()
                TextFieldPostContent(contentText, { it -> contentText = it })
            }
        }
    )
}

@Composable
fun RatingPostWrite(rating: Float, onValueChange: (Float) -> Unit) {

    RatingBar(
        value = rating,
        onValueChange = {
            onValueChange(it)
        },
        onRatingChanged = {
            Log.d(TAG, "RatingPostWrite: ${it}")
        },
        stepSize = StepSize.HALF,
        size = 40.dp,
        painterEmpty = rememberVectorPainter(MyIconPack.Emptystar),
        painterFilled = rememberVectorPainter(MyIconPack.Filledstar),
        spaceBetween = 0.dp
    )
}

@Composable
fun setHiddenPostWrite(isHidden: Boolean, onValueChange: () -> Unit) {
    val stateText = if (isHidden) "비공개" else "공개"

    Row(verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = stateText,
            modifier = Modifier.weight(1f),
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.size(10.dp))
        Switch(
            checked = isHidden,
            onCheckedChange = {
                onValueChange()
            },
            colors = SwitchDefaults.colors(
                checkedBorderColor = PrimaryPink,
                checkedThumbColor = Color.White,
                checkedTrackColor = PrimaryPink,
                uncheckedThumbColor = PrimaryPink,
                uncheckedBorderColor = PrimaryPink,
                uncheckedTrackColor = Color.White
            )
        )
    }

}

@Composable
fun TextFieldPostTitle(titleText: String, onValueChange: (String) -> Unit) {

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = titleText,
        onValueChange = {
            onValueChange(it)
        },
        textStyle = TextStyle(fontFamily = pretendardFamily, fontWeight = FontWeight.Normal),
        placeholder = {
            Text(
                text = stringResource(id = R.string.board_write_text_field_title_hint),
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Normal
            )
        },
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Transparent,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        )
    )
}

@Composable
fun TextFieldPostContent(contentText: String, onValueChange: (String) -> Unit) {

    TextField(
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(fontFamily = pretendardFamily, fontWeight = FontWeight.Normal),
        value = contentText,
        onValueChange = {
            onValueChange(it)
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.board_write_text_field_content_hint),
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Normal
            )
        },
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Transparent,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        )
    )
}
