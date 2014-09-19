package com.aentropi.util;

public class PageUtil {
	
	public static int getPageCount(int count, int pageSize) {
		if (count <= pageSize) {
			return 1;
		} else if (count%pageSize == 0) {
			return count/pageSize;
		} else {
			return count/pageSize +1;
		}
	}
}
