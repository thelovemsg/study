package com.example.study.structure.problems.kadane;

import java.util.Arrays;

/**
 * Find a non-empty subarray with the largest sum;
 * "손해보는 과거는 과감히 버려라!"
 */
public class KadanesAlgorithm {

    private static int bruteForce(int[] nums) {
        int maxSum = nums[0];

        for (int i = 0; i < nums.length; i++) {
            int curSum = 0;
            for (int j = i; j < nums.length; j++) {
                curSum += nums[j];
                maxSum = Math.max(curSum, maxSum);
            }
        }

        return maxSum;
    }

    /**
     * 0이 되어버린
     */
    private static int kadanes(int[] nums) {
        int maxSum = nums[0];
        int curSum = 0; // 원형에서는 데이터를 선택적으로 하기 때문임.

        for (int num : nums) {
            curSum = Math.max(curSum, 0);
            curSum += num;
            maxSum = Math.max(curSum, maxSum);
        }

        return maxSum;
    }

    // 중간에 현재값 더한거랑 여태 값 더한거랑 비교해서 curSum을 계속 실시간으로 보면서,
    // 만약 현재의 값이 이전값보다 크면 그냥 날리고 새롭게 maxSum을 만듬
    private static int kadanesMax(int[] nums) {
        int maxSum = nums[0];
        int curSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            curSum = Math.max(nums[i], curSum + nums[i]);
            maxSum = Math.max(curSum, maxSum);
        }

        return maxSum;
    }

    // Kadane 변형 (최소 부분배열)
    // 중간에 현재값 더한거랑 여태 값 더한거랑 비교해서 curSum을 계속 실시간으로 보면서,
    // 만약 현재의 값이 이전값보다 작으면 그냥 날리고 새롭게 maxMin을 만듬
    private static int kadanesMin(int[] nums) {
        int minSum = nums[0];
        int curSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            curSum = Math.min(nums[i], curSum + nums[i]);
            minSum = Math.min(minSum, curSum);
        }

        return minSum;
    }

    private static int[] slidingWindow(int[] nums) {
        int maxSum = nums[0];
        int curSum = 0;
        int maxL = 0;
        int maxR = 0;
        int L = 0;

        for (int R = 0; R < nums.length; R++) {
            if(curSum < 0) {
                curSum = 0;
                L = R;
            }

            curSum += nums[R];
            if(curSum > maxSum) {
                maxSum = curSum;
                maxL = L;
                maxR = R;
            }
        }
        return new int[]{maxL, maxR};
    }

    public static void main(String[] args) {
        int[] nums = {1,-5,78,-1000,-2,5, 100};

        int result = bruteForce(nums);

        System.out.println("result = " + result);

        int max = kadanesMax(nums);
        int sum = Arrays.stream(nums).sum();
        int min = kadanesMin(nums);

        int circularMax = sum - min;

        System.out.println("circularMax = " + circularMax);
        System.out.println("max = " + max);


    }
}
