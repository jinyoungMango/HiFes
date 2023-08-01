package com.ssafy.hifes.ui.participatedfest

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.theme.PrimaryPink
import com.ssafy.hifes.ui.theme.SecondApricot
import myiconpack.Ticketbody
import myiconpack.Tickethead

@Composable
fun Ticket(title: String, date: String, onClick: () -> Unit) {
    var showTitle = titleStringFormatting(title)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(6.dp, 0.dp, 0.dp, 16.dp)
            .clickable {
                onClick()
            }
    ) {
        Box(Modifier.height(100.dp)) {
            Image(
                imageVector = MyIconPack.Ticketbody,
                contentDescription = "티켓 제목",
            )
            Text(
                text = showTitle,
                modifier = Modifier.align(Alignment.Center),
                fontSize = 20.sp,
                color = PrimaryPink,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        Box(
            modifier = Modifier
                .width(100.dp)
                .rotate(270f)
        ) {
            Image(
                imageVector = MyIconPack.Tickethead,
                contentDescription = "티켓 제목",
                modifier = Modifier.align(Alignment.TopCenter)
            )
            Text(
                text = date,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(0.dp, 20.dp, 0.dp, 0.dp),
                fontSize = 15.sp,
                color = SecondApricot,
                textAlign = TextAlign.Center
            )
        }
    }
}

fun titleStringFormatting(title: String): String {
    var resultTitle = StringBuilder()
    var count = 0
    for (char in title) {
        if (count % 9 == 0 && count != 0) {
            resultTitle.append("\n")
        }
        resultTitle.append(char)
        count++
    }
    return resultTitle.toString()
}

@Composable
@Preview
fun TicketPrev() {
    var testString = "1234567891234567891"
    Ticket(testString, "2024.08.01", {})
}