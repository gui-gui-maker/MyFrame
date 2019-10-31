package com.khnt.rtbox.tools;

public class Test {

	// 插入
	public static void insertSort(int[] param) {
		for (int j = 1; j < param.length; j++) {
			int i = j - 1;
			int key = param[j];
			while (i >= 0 && param[i] > key) {
				param[i + 1] = param[i];
				param[i] = key;
				i = i - 1;
			}
		}
	}

	// 分治
	public static void dc(int[] param, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			dc(param, p, q);
			dc(param, q + 1, r);
			merge(param, p, q, r);
		}
	}

	public static void merge(int[] param, int p, int q, int r) {
		int[] temp = new int[r - p + 1];
		int lIdx = p;
		int rIdx = q + 1;

		int newIdx = 0;
		while (lIdx <= q && rIdx <= r) {
			if (param[lIdx] < param[rIdx]) {
				temp[newIdx] = param[lIdx];
				lIdx++;
			} else {
				temp[newIdx] = param[rIdx];
				rIdx++;
			}
			newIdx++;
		}

		while (lIdx <= q) {
			temp[newIdx] = param[lIdx];
			newIdx++;
			lIdx++;
		}

		while (rIdx <= r) {
			temp[newIdx] = param[rIdx];
			newIdx++;
			rIdx++;
		}

		for (int i = 0; i < temp.length; i++) {
			param[p + i] = temp[i];
		}

	}

	public static void printArray(int[] param) {
		for (int p : param) {
			System.out.print(p);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] org = new int[] { 8, 5, 3, 1, 6, 7, 2, 4, 9 };
		int[] org1 = new int[] { 5, 4, 2, 7, 9, 1, 3, 8, 6 };
		System.out.print("插入排序前:");
		printArray(org);
		insertSort(org);
		System.out.print("插入排序后:");
		printArray(org);
		System.out.print("分治排序前:");
		printArray(org1);
		dc(org1, 0, org1.length - 1);
		System.out.print("分治排序后:");
		printArray(org1);
	}

}
