package neth.iecal.curbox.ui.fragments.main.reducers.blockertools

import neth.iecal.curbox.data.models.TimeInterval

data class DayItem(
    val name: String,
    val dayIndex: Int,
    var isActive: Boolean,
    val intervals: MutableList<TimeInterval>
)

internal fun copyDayTimeRanges(source: DayItem, targets: List<DayItem>) {
    if (!source.isActive || source.intervals.isEmpty()) return

    val intervals = source.intervals.map { it.copy() }
    targets.filter { it.dayIndex != source.dayIndex }.forEach { target ->
        target.intervals.clear()
        target.intervals.addAll(intervals.map { it.copy() })
        target.isActive = true
    }
}
