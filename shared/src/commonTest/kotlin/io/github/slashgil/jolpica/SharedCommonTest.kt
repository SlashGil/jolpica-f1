package io.github.slashgil.jolpica

import kotlin.test.Test
import kotlin.test.assertTrue

class SharedCommonTest {
    @Test
    fun testExample() {
        assertTrue(getPlatform().name.isNotEmpty(), "Check 'Platform' name")
    }
}
