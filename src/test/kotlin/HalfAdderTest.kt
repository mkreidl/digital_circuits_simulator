import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class HalfAdderTest {
    private val a = Wire()
    private val b = Wire()
    private val sum = Wire()
    private val carry = Wire()

    @BeforeEach
    fun constructRippleAdder() {
        halfAdder(a, b, sum, carry)
    }

    @Test
    fun add_0_0() {
        a.signal = 0
        b.signal = 0
        assertEquals(0, sum.signal)
        assertEquals(0, carry.signal)
    }

    @Test
    fun add_1_0() {
        a.signal = 1
        b.signal = 0
        assertEquals(1, sum.signal)
        assertEquals(0, carry.signal)
    }

    @Test
    fun add_0_1() {
        a.signal = 0
        b.signal = 1
        assertEquals(1, sum.signal)
        assertEquals(0, carry.signal)
    }

    @Test
    fun add_1_1() {
        a.signal = 1
        b.signal = 1
        assertEquals(0, sum.signal)
        assertEquals(1, carry.signal)
    }
}
