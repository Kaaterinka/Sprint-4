package ru.sber.datetime

import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.TemporalAdjusters
import java.util.*


// 1.
fun getZonesWithNonDivisibleByHourOffset() =
    ZoneId.getAvailableZoneIds().filter {
        ZoneId.of(it)
            .rules
            .getOffset(Instant.now()).totalSeconds % 3600 != 0
    }.toSortedSet()

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val lastInMonthDayWeek = mutableListOf<String>()
    for (month in Month.values()) {
        lastInMonthDayWeek.add(
            LocalDate.of(year, month, 1).with(
                TemporalAdjusters.lastDayOfMonth()
            ).dayOfWeek.name
        )
    }
    return lastInMonthDayWeek
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var count = 0
    for (month in Month.values()) {
        if (LocalDate.of(year, month, 13).dayOfWeek == DayOfWeek.FRIDAY)
            count++
    }
    return count
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime) =
    dateTime.format(DateTimeFormatter.ofPattern("dd MMM YYYY, HH:mm").withLocale(Locale.US))




