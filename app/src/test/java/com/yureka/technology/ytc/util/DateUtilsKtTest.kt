package com.yureka.technology.ytc.util

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * Created on 12/3/20.
 */

class DateUtilsKtTest {

    @Test
    fun testWeekCount() {

        val date8DayAgo = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_WEEK, -8)
        }.time

        assertEquals(
            countWeekPassedFromDate(date8DayAgo), 1
        )


    }

}