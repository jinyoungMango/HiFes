package com.ssafy.hifes.ui.home.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ssafy.hifes.R
import com.ssafy.hifes.ui.common.ChipsSelectable
import com.ssafy.hifes.ui.home.HomeAppBar
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Imagenotfound
import com.ssafy.hifes.ui.iconpack.myiconpack.Imagenotfoundmedium
import com.ssafy.hifes.ui.theme.pretendardFamily


@Composable
fun HomeFestivalSearchScreen(navController: NavController) {
    Scaffold(topBar = {
        HomeAppBar(navController)
    }
    ) {
        Column(
            modifier = Modifier
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

            }
            SearchGrid()
        }
    }
}

@Composable
fun SearchGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(10) {
            SearchCard()
        }
    }
}

@Composable
fun SearchCard() {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color.White),
        border = BorderStroke(0.5.dp, Color.Gray)
    ) {
        AsyncImage(
            model = "",
            contentDescription = "Card Image",
            error = rememberVectorPainter(image = MyIconPack.Imagenotfoundmedium),
            placeholder = rememberVectorPainter(image = MyIconPack.Imagenotfoundmedium),
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Title",
            modifier = Modifier.padding(start = 4.dp),
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )
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

@Preview
@Composable
fun SearchPreview() {
    HomeFestivalSearchScreen(rememberNavController())
}