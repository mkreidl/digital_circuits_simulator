import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WireTest {

    private val wire = Wire()

    @Test
    fun one_and_zero_are_allowed_values_on_a_wire() {
        wire.signal = 1
        assertEquals(1, wire.signal)
        wire.signal = 0
        assertEquals(0, wire.signal)
    }

    @Test
    fun values_bigger_than_one_are_not_allowed() {
        val exception = assertThrows<IllegalArgumentException> { wire.signal = 2 }
        assertEquals("Value must be 0 or 1 (was 2)", exception.message)
    }

    @Test
    fun negative_values_are_not_allowed() {
        val exception = assertThrows<IllegalArgumentException> { wire.signal = -1 }
        assertEquals("Value must be 0 or 1 (was -1)", exception.message)
    }
}