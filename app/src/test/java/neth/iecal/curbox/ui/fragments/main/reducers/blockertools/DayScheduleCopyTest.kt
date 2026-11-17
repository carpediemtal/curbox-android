package neth.iecal.curbox.ui.fragments.main.reducers.blockertools

import neth.iecal.curbox.data.models.TimeInterval
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DayScheduleCopyTest {
    private fun monday() = DayItem("Monday", 1, true, mutableListOf(
        TimeInterval(9, 0, 11, 55),
        TimeInterval(14, 5, 17, 25),
        TimeInterval(19, 0, 20, 30)
    ))

    @Test
    fun replacesSelectedWeekdaysAndEnablesThemWithoutChangingOtherDays() {
        val source = monday()
        val weekdays = (2..5).map { DayItem("Day $it", it, false, mutableListOf(TimeInterval())) }
        val saturday = DayItem("Saturday", 6, false, mutableListOf(TimeInterval(10, 0, 12, 0)))

        copyDayTimeRanges(source, weekdays)

        weekdays.forEach {
            assertTrue(it.isActive)
            assertEquals(source.intervals, it.intervals)
        }
        assertFalse(saturday.isActive)
        assertEquals(listOf(TimeInterval(10, 0, 12, 0)), saturday.intervals)
        assertEquals(monday(), source)
    }

    @Test
    fun laterEditsCannotChangeSourceOrOtherCopies() {
        val source = monday()
        val tuesday = DayItem("Tuesday", 2, true, mutableListOf())
        val wednesday = DayItem("Wednesday", 3, true, mutableListOf())
        copyDayTimeRanges(source, listOf(source, tuesday, wednesday))

        tuesday.intervals[0].startHour = 8
        tuesday.intervals.removeAt(1)
        source.intervals[2].endMinute = 45

        assertEquals(9, source.intervals[0].startHour)
        assertEquals(3, source.intervals.size)
        assertEquals(monday().intervals, wednesday.intervals)
        assertEquals(30, tuesday.intervals[1].endMinute)
    }

    @Test
    fun disabledOrEmptySourceDoesNotEraseDestination() {
        val source = monday().apply { isActive = false }
        val target = DayItem("Tuesday", 2, false, mutableListOf(TimeInterval(8, 0, 9, 0)))
        copyDayTimeRanges(source, listOf(target))
        source.isActive = true
        source.intervals.clear()
        copyDayTimeRanges(source, listOf(target))

        assertFalse(target.isActive)
        assertEquals(listOf(TimeInterval(8, 0, 9, 0)), target.intervals)
    }

    @Test
    fun emptySelectionAndSourceSelectionLeaveScheduleUnchanged() {
        val source = monday()
        copyDayTimeRanges(source, emptyList())
        copyDayTimeRanges(source, listOf(source))
        assertEquals(monday(), source)
    }
}
