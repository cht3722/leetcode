package com;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC203 {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		if (prerequisites == null || prerequisites.length == 0) {
			return true;
		}
		boolean[] start = new boolean[numCourses];
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for (int[] i : prerequisites) {
			if (!map.containsKey(i[1])) {
				map.put(i[1], new HashSet<Integer>());
			}
			map.get(i[1]).add(i[0]);
			start[i[0]] = true;
		}
		Set<Integer> used = new HashSet<>();
		Set<Integer> path = new HashSet<>();
		for (int i = 0; i < start.length; i++) {
			if (start[i]) {
				continue;
			}
			boolean res = helper(used, path, i, map);
			if (!res) {
				return false;
			}
		}
		if (used.size() < numCourses) {
			return false;
		}
		return true;
	}

	private boolean helper(Set<Integer> used, Set<Integer> path, int i, Map<Integer, Set<Integer>> map) {
		if (path.contains(i)) {
			return false;
		}
		if (used.contains(i)) {
			return true;
		}
		used.add(i);
		boolean res = true;
		path.add(i);
		Set<Integer> set = map.get(i);
		if (set == null || set.isEmpty()) {
			path.remove(i);
			return true;
		}
		for (int j : set) {
			res = res && helper(used, path, j, map);
			if (!res) {
				break;
			}
		}
		path.remove(i);
		return res;
	}

}
