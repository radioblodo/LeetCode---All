class Solution {
    private val MOD = 1_000_000_007

    fun lengthAfterTransformations(s: String, t: Int, nums: List<Int>): Int {
        val m = 26
        val cnt = IntArray(m)
        for (c in s) cnt[c - 'a']++

        val matrix = Array(m) { IntArray(m) }
        for (i in 0 until m) {
            val num = nums[i]
            for (j in 1..num) {
                matrix[i][(i + j) % m] = 1
            }
        }

        val factor = matPow(matrix, t)
        val result = vectorMatrixMultiply(cnt, factor)

        return result.fold(0L) { acc, v -> (acc + v) % MOD }.toInt()
    }

    private fun matMul(a: Array<IntArray>, b: Array<IntArray>): Array<IntArray> {
        val n = a.size
        val q = b[0].size
        val res = Array(n) { IntArray(q) }
        for (i in 0 until n) {
            for (k in 0 until n) {
                if (a[i][k] == 0) continue
                for (j in 0 until q) {
                    res[i][j] = ((res[i][j] + 1L * a[i][k] * b[k][j]) % MOD).toInt()
                }
            }
        }
        return res
    }

    private fun matPow(mat: Array<IntArray>, power: Int): Array<IntArray> {
        val m = mat.size
        var res = Array(m) { i -> IntArray(m) { j -> if (i == j) 1 else 0 } }
        var base = mat
        var p = power
        while (p > 0) {
            if (p and 1 != 0) res = matMul(res, base)
            base = matMul(base, base)
            p = p shr 1
        }
        return res
    }


    private fun vectorMatrixMultiply(vector: IntArray, matrix: Array<IntArray>): IntArray {
        val n = matrix.size
        val result = IntArray(n)
        for (i in 0 until n) {
            var sum = 0L
            for (j in 0 until n) {
                sum = (sum + 1L * vector[j] * matrix[j][i]) % MOD
            }
            result[i] = sum.toInt()
        }
        return result
    }
}
