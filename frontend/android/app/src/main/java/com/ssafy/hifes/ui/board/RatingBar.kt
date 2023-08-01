package com.ssafy.hifes.ui.board

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Emptystar
import com.ssafy.hifes.ui.iconpack.myiconpack.Filledstar
import com.ssafy.hifes.ui.iconpack.myiconpack.Halffilledstar

@Composable
fun RatingBar(
    rating : Float
) {
    val filledStarCount = rating.toInt()/1
    val halfStarCount = if(rating-filledStarCount==0f) 0 else 1
    val emptyStarCount = 5-filledStarCount-halfStarCount

    Row(){
        for(star : Int in 0 until filledStarCount){
            Icon(imageVector = MyIconPack.Filledstar, contentDescription = "채워진 별")
        }
        for(star : Int in 0 until halfStarCount){
            Icon(imageVector = MyIconPack.Halffilledstar, contentDescription = "반만 채워진 별")
        }
        for(star : Int in 0 until emptyStarCount){
            Icon(imageVector = MyIconPack.Emptystar, contentDescription = "빈 별")
        }
    }

}

@Composable
@Preview
fun PreviewRatingBar(){
    Column() {
        RatingBar(rating = 4.5f)
        RatingBar(rating = 5.0f)
        RatingBar(rating = 0.0f)
        RatingBar(rating = 3.0f)
        RatingBar(rating = 2.5f)
    }
}