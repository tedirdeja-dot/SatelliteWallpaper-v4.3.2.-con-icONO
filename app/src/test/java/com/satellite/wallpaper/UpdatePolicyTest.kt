package com.satellite.wallpaper
import org.junit.Assert.*
import org.junit.Test

class UpdatePolicyTest {
    @Test fun firstImageIsDue() {
        for (m in UpdatePolicy.minutes) assertTrue(UpdatePolicy.due(1_000L, 0L, m))
    }
    @Test fun intervalIsStrictForEveryOption() {
        val last = 1_000L
        for (m in UpdatePolicy.minutes.filter { it > 0 }) {
            val boundary = last + m * 60_000L
            assertFalse(UpdatePolicy.due(boundary, last, m))
            assertTrue(UpdatePolicy.due(boundary + 1L, last, m))
            if (m > 0) assertFalse(UpdatePolicy.due(boundary - 1L, last, m))
        }
    }
    @Test fun zeroAlwaysAllowsAnEventEvenInSameMillisecond() {
        assertTrue(UpdatePolicy.due(9_000L, 9_000L, 0))
        assertTrue(UpdatePolicy.due(9_001L, 9_000L, 0))
    }
    @Test fun clockRollbackDoesNotBlockForever() {
        assertTrue(UpdatePolicy.due(1_000L, 9_000L, 60))
    }
    @Test(expected = IllegalArgumentException::class)
    fun rejectsUnsupportedInterval() { UpdatePolicy.due(2L, 1L, 7) }
}
