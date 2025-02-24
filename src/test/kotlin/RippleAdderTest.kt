import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RippleAdderTest {
    private val inputA = listOf(Wire(), Wire())
    private val inputB = listOf(Wire(), Wire())
    private val sum = listOf(Wire(), Wire())
    private val carry = Wire()

    @BeforeEach
    fun constructRippleAdder() {
        rippleAdder(inputA, inputB, sum, carry)
    }

    @Test
    fun adding_zeros_does_not_change_anything() {
        inputA[1].signal = 1  // one
        assertEquals(1, sum[1].signal)
        assertEquals(0, sum[0].signal)
        assertEquals(0, carry.signal)
    }

    @Test
    fun one_plus_one_is_two() {
        inputA[1].signal = 1  // one
        inputB[1].signal = 1  // one
        assertEquals(0, sum[1].signal)
        assertEquals(1, sum[0].signal)
        assertEquals(0, carry.signal)
    }

    @Test
    fun one_plus_two_is_three() {
        inputA[1].signal = 1  // one
        inputB[0].signal = 1  // two
        assertEquals(1, sum[1].signal)
        assertEquals(1, sum[0].signal)
        assertEquals(0, carry.signal)
    }

    @Test
    fun two_plus_two_is_zero_and_carry_is_set() {
        inputA[0].signal = 1  // two
        inputB[0].signal = 1  // two
        assertEquals(0, sum[1].signal)
        assertEquals(0, sum[0].signal)
        assertEquals(1, carry.signal)
    }
}