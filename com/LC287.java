package com;

public class LC287 {
	public static int findDuplicate(int[] nums) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		int n = nums.length;
		int slow = n;
		int fast = n;
		slow = nums[slow - 1];
		fast = nums[nums[fast - 1] - 1];
		while (fast != slow) {
			slow = nums[slow - 1];
			fast = nums[nums[fast - 1] - 1];
		}
		slow = n;
		while (slow != fast) {
			slow = nums[slow - 1];
			fast = nums[fast - 1];
		}
		return slow;

	}
}
