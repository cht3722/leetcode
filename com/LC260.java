package com;

public class LC260 {
	public int[] singleNumber(int[] nums) {
		int diff = 0;
		for (int num : nums) {
			diff ^= num;
		}
		diff &= -diff;
		int[] rets = { 0, 0 };
		for (int num : nums) {
			if ((num & diff) == 0) // the bit is not set
			{
				rets[0] ^= num;
			} else // the bit is set
			{
				rets[1] ^= num;
			}
		}
		return rets;
	}
}
