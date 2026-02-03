package com.threewd.soft.util;

public class DBUtil {
 public static boolean isRowAffected(int affectedRows) {
	 if (affectedRows > 0) {
		 return true;
	 }
	 else {
		 return false;
	 }

 }
}
