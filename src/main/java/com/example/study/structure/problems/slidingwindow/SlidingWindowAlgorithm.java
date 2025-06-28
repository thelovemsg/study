package com.example.study.structure.problems.slidingwindow;

/**
 * Given an array, return true
 * if there are two elements within a window of size k that are equal.
 */
public class SlidingWindowAlgorithm {

    private static boolean bruteForce(int[] nums, int k) {
        // 모든 L 위치에서 시작
        for (int L = 0; L < nums.length; L++) {
            // R의 범위: L+1부터 min(nums.length-1, L+k)까지
            int maxR = Math.min(nums.length - 1, L + k);

            for (int R = L + 1; R <= maxR; R++) {
                // 같은 값을 찾으면 즉시 true 반환
                if (nums[L] == nums[R]) {
                    return true;
                }
            }
        }

        return false;
    }
}
