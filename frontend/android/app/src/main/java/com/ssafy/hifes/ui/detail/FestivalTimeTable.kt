package com.ssafy.hifes.ui.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssafy.hifes.data.model.TimeTable
import com.ssafy.hifes.ui.detail.map.MarkerDetailDialog
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleDisplay(festivalTimeTable: List<TimeTable>) {
    val (startDate, endDate) = getStartAndEndDate(festivalTimeTable)
    val dates = getDatesInRange(startDate, endDate)

    val (startTime, endTime) = getStartAndEndTime(festivalTimeTable)

    var showDialog by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    if (showDialog) {
        MarkerDetailDialog(title, content, time, onDismissRequest = { showDialog = false })
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.background(Color.Gray.copy(alpha = 0.2f))
        ) {
            Text(
                text = "Time",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .width(64.dp),
                fontWeight = FontWeight.Bold
            )
            dates.forEach { date ->
                Text(
                    text = date.toString(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        for (hour in startTime.hour..endTime.hour) {
            Row {
                Text(
                    text = "$hour:00",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(8.dp)
                        .width(64.dp)
                )
                dates.forEach { date ->
                    val currentProgram = festivalTimeTable.find { program ->
                        val programStartDate = program.startTime.date.toLocalDate()
                        val programEndDate = program.endTime.date.toLocalDate()
                        val programStartTimeHour = program.startTime.time.hour
                        val programEndTimeHour = program.endTime.time.hour

                        date == programStartDate &&
                                hour >= programStartTimeHour &&
                                hour < programEndTimeHour
                    }

                    if (currentProgram != null) {
                        Text(
                            text = currentProgram.programTitle,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .background(Color.Gray.copy(alpha = 0.2f))
                                .padding(4.dp)
                                .weight(1f)
                                .clickable {
                                    title = currentProgram.programTitle
                                    time =
                                        "${currentProgram.startTime.time.hour} : 00 - ${currentProgram.endTime.time.hour} : 00"
                                    content = currentProgram.programOutline
                                    showDialog = true
                                }
                        )
                    } else {
                        Text(
                            text = "",
                            modifier = Modifier
                                .background(Color.White)
                                .padding(4.dp)
                                .weight(1f)
                        )
                    }
                }

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getStartAndEndDate(programs: List<TimeTable>): Pair<LocalDate, LocalDate> {
    val startDates = programs.map { it.startTime.date.toLocalDate() }
    val endDates = programs.map { it.endTime.date.toLocalDate() }

    return Pair(startDates.minOrNull()!!, endDates.maxOrNull()!!)
}

@RequiresApi(Build.VERSION_CODES.O)
fun getStartAndEndTime(programs: List<TimeTable>): Pair<LocalTime, LocalTime> {
    val startTimes = programs.map { it.startTime.time.toLocalTime() }
    val endTimes = programs.map { it.endTime.time.toLocalTime() }

    return Pair(startTimes.minOrNull()!!, endTimes.maxOrNull()!!)
}


@RequiresApi(Build.VERSION_CODES.O)
fun getDatesInRange(start: LocalDate, end: LocalDate): List<LocalDate> {
    val dates = mutableListOf<LocalDate>()
    var currentDate = start
    while (currentDate <= end) {
        dates.add(currentDate)
        currentDate = currentDate.plusDays(1)
    }
    return dates
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, heightDp = 600)
@Composable
fun ScheduleDisplayPreview() {
//    ScheduleDisplay(festivalTimeTable)
}
