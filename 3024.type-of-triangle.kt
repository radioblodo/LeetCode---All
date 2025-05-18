/*
 * @lc app=leetcode.cn id=3024 lang=kotlin
 * @lcpr version=30204
 *
 * [3024] Type of Triangle
 */


// @lcpr-template-start

// @lcpr-template-end
// @lc code=start
class Solution {
    fun canFormTriangle(nums: IntArray): Boolean {
        if (nums[0] + nums[1] > nums[2]) {
            if (nums[0] + nums[2] > nums[1]) {
                if (nums[1] + nums[2] > nums[0]) {
                    return true 
                }
                return false 
            }
            return false
        }
        return false
    }
    fun triangleType(nums: IntArray): String {
        if (canFormTriangle(nums)) {
            val uniqueList = nums.distinct()
            if (uniqueList.size == 1) {
                return "equilateral"
            }
            else if (uniqueList.size == 2) {
                return "isosceles"
            }
            else {
                return "scalene"
            }
        }
        else {
            return "none"
        }
        
    }
}
// @lc code=end



/*
// @lcpr case=start
// [3,3,3]\n
// @lcpr case=end

// @lcpr case=start
// [3,4,5]\n
// @lcpr case=end

 */

