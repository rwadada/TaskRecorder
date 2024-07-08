package ui.extension

import data.WorkHistory

fun WorkHistory.toDisplayDurationString(): String =
    "${
        hours.toString().padStart(2, '0')
    }:${
        minutes.toString().padStart(2, '0')
    }"