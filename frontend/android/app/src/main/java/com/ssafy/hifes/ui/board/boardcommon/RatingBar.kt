package com.ssafy.hifes.ui.board.boardcommon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Emptystar
import com.ssafy.hifes.ui.iconpack.myiconpack.Filledstar
import com.ssafy.hifes.ui.iconpack.myiconpack.Halffilledstar
import com.ssafy.hifes.ui.theme.PrimaryPink

@Composable
fun RatingBar(
    rating : Float,
    starSize : Int,
    starColor : Color
) {
    val filledStarCount = rating.toInt()/1
    val halfStarCount = if(rating-filledStarCount==0f) 0 else 1
    val emptyStarCount = 5-filledStarCount-halfStarCount

    Row(){
        for(star : Int in 0 until filledStarCount){
            Icon(
                imageVector = MyIconPack.Filledstar,
                contentDescription = "채워진 별",
                modifier = Modifier.size(starSize.dp),
                tint = starColor
            )
        }
        for(star : Int in 0 until halfStarCount){
            Icon(
                imageVector = MyIconPack.Halffilledstar,
                contentDescription = "반만 채워진 별",
                modifier = Modifier.size(starSize.dp),
                tint = starColor
            )
        }
        for(star : Int in 0 until emptyStarCount){
            Icon(
                imageVector = MyIconPack.Emptystar,
                contentDescription = "빈 별",
                modifier = Modifier.size(starSize.dp),
                tint = starColor
            )
        }
    }

}

@Composable
@Preview
fun PreviewRatingBar(){
    Column() {
        RatingBar(rating = 4.5f, starSize = 10, starColor = PrimaryPink)
        RatingBar(rating = 5.0f, starSize = 15, starColor = Color.Black)
        RatingBar(rating = 0.0f, starSize = 5, starColor = PrimaryPink)
        RatingBar(rating = 3.0f, starSize = 5, starColor = PrimaryPink)
        RatingBar(rating = 2.5f, starSize = 5, starColor = PrimaryPink)
    }
}