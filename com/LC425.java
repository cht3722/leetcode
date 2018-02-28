package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC425 {
	public static class Node {
		Map<Character, Node> map;
		char c;
		List<Integer> list;

		public Node(char c) {
			this.c = c;
			this.map = new HashMap<>();
			this.list = new ArrayList<>();
		}
	}

	public List<List<String>> wordSquares(String[] words) {
		Map<Character, Node> map = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			String s = words[i];
			char c = s.charAt(0);
			if (!map.containsKey(c)) {
				map.put(c, new Node(c));
			}
			map.get(c).list.add(i);
			buildTrie(s, map.get(c), i);
		}
		List<List<String>> res = new ArrayList<>();
		List<String> path = new ArrayList<>();
		int len = words[0].length();
		char[][] board = new char[len][len];
		for (String s : words) {
			path.add(s);
			for (int j = 0; j < s.length(); j++) {
				board[j][0] = s.charAt(j);
			}
			helper(res, path, 1, board, len, map, words);
			path.remove(path.size() - 1);
		}
		return res;
	}

	private void helper(List<List<String>> res, List<String> path, int level, char[][] board, int len,
			Map<Character, Node> map, String[] words) {
		if (level == len) {
			res.add(new ArrayList<String>(path));
			return;
		}
		Node node = map.get(board[level][0]);
		if (node == null) {
			return;
		}
		for (int i = 1; i < level; i++) {
			if (!node.map.containsKey(board[level][i])) {
				return;
			}
			node = node.map.get(board[level][i]);
		}

		List<Integer> list = node.list;
		for (int i : list) {
			String s = words[i];
			path.add(s);
			for (int j = 0; j < s.length(); j++) {
				board[j][level] = s.charAt(j);
			}
			helper(res, path, level + 1, board, len, map, words);
			path.remove(path.size() - 1);
		}

	}

	private void buildTrie(String s, Node node, int idx) {
		for (int i = 1; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!node.map.containsKey(c)) {
				node.map.put(c, new Node(c));
			}
			node = node.map.get(c);
			node.list.add(idx);
		}
	}

}
