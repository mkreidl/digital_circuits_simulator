fun inverter(input: Wire, output: Wire) {
    input.addAction {
        val not = 1 - input.signal
        output.signal = not
    }
}

fun andGate(a: Wire, b: Wire, output: Wire) = binaryGate(a, b, output) { x, y -> x * y }

fun orGate(a: Wire, b: Wire, output: Wire) = binaryGate(a, b, output) { x, y -> (x + y) % 2 }

fun binaryGate(a: Wire, b: Wire, output: Wire, f: (Int, Int) -> Int) {
    val action: () -> Unit = {
        val result = f(a.signal, b.signal)
        output.signal = result
    }
    a.addAction(action)
    b.addAction(action)
}

fun halfAdder(a: Wire, b: Wire, sum: Wire, carry: Wire) {
    val d = Wire()
    val e = Wire()
    orGate(a, b, d)
    andGate(a, b, carry)
    inverter(carry, e)
    andGate(d, e, sum)
}

fun fullAdder(a: Wire, b: Wire, carryIn: Wire, sum: Wire, carry: Wire) {
    val s = Wire()
    val c1 = Wire()
    val c2 = Wire()
    halfAdder(b, carryIn, s, c1)
    halfAdder(a, s, sum, c2)
    orGate(c1, c2, carry)
}

fun rippleAdder(inputA: List<Wire>, inputB: List<Wire>, sum: List<Wire>, carry: Wire) {
    val carries = inputA.indices.map { if (it == 0) carry else Wire() }
    (0..inputA.size - 2).forEach {
        fullAdder(inputA[it], inputB[it], carries[it + 1], sum[it], carries[it])
    }
    halfAdder(inputA.last(), inputB.last(), sum.last(), carries.last())
}