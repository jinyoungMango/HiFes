package com.ssafy.hifes.ui.home.search

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ssafy.hifes.R
import com.ssafy.hifes.data.model.OrganizedFestivalDto
import com.ssafy.hifes.ui.HifesDestinations
import com.ssafy.hifes.ui.common.ChipsSelectable
import com.ssafy.hifes.ui.home.HomeAppBar
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Imagenotfoundmedium
import com.ssafy.hifes.ui.main.MainViewModel
import com.ssafy.hifes.ui.map.StarScore
import com.ssafy.hifes.ui.theme.pretendardFamily
import com.ssafy.hifes.util.CommonUtils
import kotlinx.coroutines.launch


private const val TAG = "SearchScree_하이페스"

@Composable
fun HomeFestivalSearchScreen(navController: NavController, viewModel: MainViewModel) {
    val festivalList = viewModel.searchFestivalList.observeAsState()
    var selectedChip by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    BackHandler(true) {
        coroutineScope.launch {
            viewModel.initSearchKeyword()
            navController.popBackStack()
        }
    }

    Scaffold(topBar = {
        HomeAppBar(navController, viewModel) { keyword ->
            viewModel.searchFestivalList(keyword = keyword)
            navController.navigate(HifesDestinations.HOME_SEARCH) {
                popUpTo(HifesDestinations.HOME_SEARCH) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ChipsSelectable(
                listOf(
                    R.string.home_search_chip_all,
                    R.string.home_search_chip_subject,
                    R.string.home_search_chip_address,
                ).map { stringResource(id = it) }
            ) { index ->
                selectedChip = index
            }
            festivalList.value?.let { festivals ->
                SearchGrid(
                    navController,
                    festivals,
                    selectedChip,
                    viewModel
                )
            }
        }
    }
}

@Composable
fun SearchGrid(
    navController: NavController,
    festivalList: List<OrganizedFestivalDto>,
    selectedChip: Int,
    viewModel: MainViewModel
) {
    val filteredList = when (selectedChip) {
        0 -> festivalList
        1 -> festivalList.filter { festival ->
            festival.searchType == "title"
        }

        2 -> festivalList.filter { festival ->
            festival.searchType == "address"
        }

        else -> festivalList
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(filteredList.size) { index ->
            SearchCard(filteredList[index]) { festivalId ->
                viewModel.getFestivalInfo(festivalId)
                navController.navigate(HifesDestinations.FESTIVAL_DETAIL)
            }
        }
    }
}

@Composable
fun SearchCard(festival: OrganizedFestivalDto, onClick: (Int) -> Unit) {
    Card(
        modifier = Modifier.clickable {
            onClick(festival.festivalId)
        },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color.White),
        border = BorderStroke(0.5.dp, Color.Gray)
    ) {
        AsyncImage(
            model = festival.fesPosterPath,
            contentDescription = "Card Image",
            error = rememberVectorPainter(image = MyIconPack.Imagenotfoundmedium),
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = festival.fesTitle,
            modifier = Modifier.padding(start = 12.dp),
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.size(8.dp))
        val date = "${
            CommonUtils.formatFestivalDateToString(festival.fesStartDate)
        } ~ ${CommonUtils.formatFestivalDateToString(festival.fesEndDate)}"
        Text(
            text = date,
            modifier = Modifier.padding(start = 12.dp),
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.size(8.dp))
        StarScore(score = festival.avgRating, Modifier.padding(start = 12.dp))
        Spacer(modifier = Modifier.size(8.dp))

    }
}

@Composable
fun SearchTypeText(type: String) {
    Text(
        text = type,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    )
}
