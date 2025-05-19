class Solution {
    fun isZeroArray(nums: IntArray, queries: Array<IntArray>): Boolean {
        val n = nums.size
        val coverage = IntArray(n)

        // Mark all the ranges in the coverage array
        for (query in queries) {
            val l = query[0]
            val r = query[1]
            for (i in l..r) {
                coverage[i] += 1
            }
        }

        // Check if we can cover all demands
        for (i in 0 until n) {
            if (nums[i] > coverage[i]) {
                return false
            }
        }
        return true
    }
}
