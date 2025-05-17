/*
 * @lc app=leetcode.cn id=75 lang=kotlin
 * @lcpr version=30204
 *
 * [75] Sort Colors
 */


// @lcpr-template-start

// @lcpr-template-end
// @lc code=start
class Solution {
    fun sortColors(nums: IntArray): Unit {
        var low = 0; 
        var mid = 0; 
        var high = nums.size - 1; 

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid); 
                low++; 
                mid++; 
            }
            else if (nums[mid] == 1) {
                mid++;
            }
            else {
                swap(nums, mid, high);
                high--; 
            }
        }
    }
    fun swap(nums: IntArray, i: Int, j: Int) {
        var temp = nums[i];
        nums[i] = nums[j]; 
        nums[j] = temp; 
    }
}
// @lc code=end



/*
// @lcpr case=start
// [2,0,2,1,1,0]\n
// @lcpr case=end

// @lcpr case=start
// [2,0,1]\n
// @lcpr case=end

 */

