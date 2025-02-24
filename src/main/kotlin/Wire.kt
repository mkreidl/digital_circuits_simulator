class Wire {
    var signal: Int = 0
        set(value) = when (value) {
            0, 1 -> {
                field = value
                actions.forEach { it() }
            }

            else -> throw IllegalArgumentException("Value must be 0 or 1 (was $value)")
        }

    private val actions = mutableListOf<() -> Unit>()

    fun addAction(action: () -> Unit) {
        action()
        actions.add(action)
    }
}