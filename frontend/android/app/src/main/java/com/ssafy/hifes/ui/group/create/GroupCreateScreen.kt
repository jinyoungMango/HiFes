package com.ssafy.hifes.ui.group.create

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ssafy.hifes.data.model.Group
import com.ssafy.hifes.ui.common.ProfileImg
import com.ssafy.hifes.ui.common.top.TopWithBack
import com.ssafy.hifes.ui.group.GroupViewModel
import com.ssafy.hifes.ui.main.MainViewModel
import com.ssafy.hifes.ui.theme.PrimaryPink
import com.ssafy.hifes.ui.theme.pretendardFamily

private const val TAG = "GroupCreateScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupCreateScreen(
    navController: NavController,
    viewModel: GroupViewModel,
    mainViewModel: MainViewModel
) {
    var context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var maxPop by remember { mutableStateOf("1") }
    var tagList by remember { mutableStateOf(listOf<String>()) }
    var selectedFestival = mainViewModel.selectedFestival
    val errMsg = viewModel.errorMsgGroupCreate.observeAsState()
    val groupCreateState = viewModel.createStateType.observeAsState()

    errMsg.value?.getContentIfNotHandled()?.let {
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
    }

    LaunchedEffect(groupCreateState.value) {
        if (groupCreateState.value == GroupCreateStateType.SUCCESS) {
            viewModel.initCreateState()
            Toast.makeText(context, "그룹이 생성되었습니다", Toast.LENGTH_LONG).show()
            navController.popBackStack()
        }
    }


    Scaffold(
        topBar = { TopWithBack(navController, title = "모임 생성", onClick = {}) }

    ) {
        val scrollState = rememberScrollState()

        Column(
            Modifier
                .padding(it)
                .padding(start = 18.dp, end = 18.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileImg(
                imageUri = imageUri,
                onImageChange = { uri -> imageUri = uri }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldWithCaption(
                caption = "모임명",
                title,
                { text ->
                    title = text
                }
            )
            TextFieldWithCaption(
                caption = "모임 설명",
                content,
                { text ->
                    content = text
                }
            )
            DropdownWithCaption(
                caption = "최대 인원",
                selectedOptionText = maxPop,
                { it -> maxPop = it }
            )

            addHashTagWithCaption(
                caption = "태그 작성",
                hashTags = tagList,
                addTag = { newTag ->
                    tagList = tagList + newTag
                },
                onDelete = { index ->
                    val item = tagList[index]
                    tagList = tagList.toMutableList().also { it.remove(item) }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    shape = MaterialTheme.shapes.medium,
                    border = BorderStroke(1.dp, PrimaryPink),
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        text = "취소",
                        fontFamily = pretendardFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(24.dp))
                Button(
                    modifier = Modifier.weight(1f),
                    shape = MaterialTheme.shapes.medium,
                    onClick = {
                        if (title == "" || imageUri == null || content == "") {
                            Toast.makeText(
                                context,
                                "이미지 선택과 모임 이름, 설명은 필수입니다! 값을 입력해주세요",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            viewModel.createGroup(
                                context,
                                imageUri!!,
                                Group(
                                    groupName = title,
                                    groupPic = null,
                                    createdAt = null,
                                    maxPop = maxPop.toInt(),
                                    content = content,
                                    hashtags = tagList,
                                    numOfPeople = null,
                                    festivalId = selectedFestival
                                )
                            )

                        }

                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryPink)
                ) {
                    Text(text = "생성")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun CreatePrev() {
    //GroupCreateScreen(rememberNavController())
}