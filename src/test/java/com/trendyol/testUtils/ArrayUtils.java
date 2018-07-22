package com.trendyol.testUtils;

import java.util.Arrays;

public class ArrayUtils {

	private ArrayUtils() {}
	
	public static double getMaxElementOfArray(double[] data) {
		return Arrays.stream(data).max().getAsDouble();
	}
}
