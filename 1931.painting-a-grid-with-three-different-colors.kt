/*
 * @lc app=leetcode.cn id=1931 lang=kotlin
 * @lcpr version=30204
 *
 * [1931] Painting a Grid With Three Different Colors
 */


// @lcpr-template-start

// @lcpr-template-end
// @lc code=start

class Solution {

    private var gridHeight = 0
    private val MOD = 1_000_000_007

    fun colorTheGrid(m: Int, n: Int): Int {
        gridHeight = m
        val maxStateValue = Math.pow(3.0, m.toDouble()).toInt()  // 3^m

        val validStates = mutableSetOf<Int>()
        var dpStateCounts = IntArray(maxStateValue)

        // Initialize valid states and their counts
        for (state in 0 until maxStateValue) {
            if (isValidState(state)) {
                validStates.add(state)
                dpStateCounts[state] = 1
            }
        }

        // Build transition map
        val validTransitions = mutableMapOf<Int, MutableList<Int>>()
        for (i in validStates) {
            for (j in validStates) {
                if (isTransitionValid(i, j)) {
                    validTransitions.computeIfAbsent(i) { mutableListOf() }.add(j)
                }
            }
        }

        // Dynamic programming column by column
        repeat(n - 1) {
            val nextStateCounts = IntArray(maxStateValue)
            for (currentState in validStates) {
                val transitions = validTransitions[currentState] ?: emptyList()
                for (nextState in transitions) {
                    nextStateCounts[currentState] = (nextStateCounts[currentState] + dpStateCounts[nextState]) % MOD
                }
            }
            dpStateCounts = nextStateCounts
        }

        // Sum up all valid end states
        return dpStateCounts.fold(0) { acc, count -> (acc + count) % MOD }
    }

    // Check intra-column validity (no vertical adjacent same colors)
    private fun isValidState(state: Int): Boolean {
        var currentState = state
        var lastColor = -1
        repeat(gridHeight) {
            val currentColor = currentState % 3
            if (currentColor == lastColor) return false
            lastColor = currentColor
            currentState /= 3
        }
        return true
    }

    // Check inter-column validity (no horizontal adjacent same colors)
    private fun isTransitionValid(stateOne: Int, stateTwo: Int): Boolean {
        var s1 = stateOne
        var s2 = stateTwo
        repeat(gridHeight) {
            if (s1 % 3 == s2 % 3) return false
            s1 /= 3
            s2 /= 3
        }
        return true
    }
}



// @lc code=end



/*
// @lcpr case=start
// 1\n1\n
// @lcpr case=end

// @lcpr case=start
// 1\n2\n
// @lcpr case=end

// @lcpr case=start
// 5\n5\n
// @lcpr case=end

 */

