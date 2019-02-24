package com.mg.equation.solver

import org.junit.Test
import org.junit.Assert.*

class EquationSolverTest {
    @Test
    fun equationSolveIfDiscriminantIsGreaterThanZero() {
        val result = solve(2.0, 8.0, -192.0)

        assertEquals(result.d, 1600.0, 0.0)
        assertEquals(result.x1!!, 8.0, 0.0)
        assertEquals(result.x2!!, -12.0, 0.0)
    }

    @Test
    fun equationSolveIfDiscriminantIsZero() {
        val result = solve(1.0, -22.0, 121.0)

        assertEquals(result.d, 0.0, 0.0)
        assertEquals(result.x1!!, 11.0, 0.0)
        assertEquals(result.x2!!, 11.0, 0.0)
    }

    @Test
    fun equationSolveIfDiscriminantIsLessThanZero() {
        val result = solve(1.0, -8.0, 72.0)

        assertEquals(result.d, -224.0, 0.0)
        assertNull(result.x1)
        assertNull(result.x2)
    }
}
