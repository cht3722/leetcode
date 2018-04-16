package com;

public class LC795 {
	public static void main(String[] args) {
		numSubarrayBoundedMax(new int[] { 2, 1, 4, 3 }, 2, 3);
		System.out.println("a");
	}

	public static int numSubarrayBoundedMax(int[] A, int L, int R) {
		int s = 0;
		int result = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] > R) {
				result += helper(A, L, R, s, i - 1);
				s = i + 1;
			}
		}
		result += helper(A, L, R, s, A.length - 1);
		return result;
	}

	public static int helper(int[] A, int L, int R, int s, int e) {
		if (s > e) {
			return 0;
		}
		int start = s;
		int sum = 0;
		for (int i = 1; i <= e - s + 1; i++) {
			sum += i;
		}
		for (int i = s; i <= e; i++) {
			if (A[i] >= L) {
				sum -= helperII(A, L, R, start, i - 1);
				start = i + 1;
			}
		}
		sum -= helperII(A, L, R, start, e);
		return sum;

	}

	public  static  int helperII(int[] A, int L, int R, int s, int e) {
		if (s > e) {
			return 0;
		}
		int sum = 0;
		for (int i = 1; i <= e - s + 1; i++) {
			sum += i;
		}
		return sum;
	}

}
