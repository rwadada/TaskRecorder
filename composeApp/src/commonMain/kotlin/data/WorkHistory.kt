package data

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlin.time.Duration

data class WorkHistory(
    val title: String,
    val startDateTime: Instant,
    val endDateTime: Instant
) {
    private val duration: Duration = endDateTime - startDateTime
    private val totalMinutes: Long = duration.inWholeMinutes
    
    val hours: Long = totalMinutes / 60
    val minutes: Long = totalMinutes % 60

    companion object {
        fun create(
            title: String,
            startDateTime: LocalDateTime,
            endDateTime: LocalDateTime
        ): WorkHistory =
            WorkHistory(
                title,
                startDateTime.toInstant(TimeZone.currentSystemDefault()),
                endDateTime.toInstant(TimeZone.currentSystemDefault())
            )
    }
}