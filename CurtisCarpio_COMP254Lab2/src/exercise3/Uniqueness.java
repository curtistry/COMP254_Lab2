package exercise3;

import java.util.Arrays;

class Uniqueness {

	/** Returns true if there are no duplicate elements in the array. */
	public static boolean unique1(int[] data) {
		int n = data.length;
		for (int j = 0; j < n - 1; j++)
			for (int k = j + 1; k < n; k++)
				if (data[j] == data[k])
					return false; // found duplicate pair
		return true; // if we reach this, elements are unique
	}

	/** Returns true if there are no duplicate elements in the array. */
	public static boolean unique2(int[] data) {
		int n = data.length;
		int[] temp = Arrays.copyOf(data, n); // make copy of data
		Arrays.sort(temp); // and sort the copy
		for (int j = 0; j < n - 1; j++)
			if (temp[j] == temp[j + 1]) // check neighboring entries
				return false; // found duplicate pair
		return true; // if we reach this, elements are unique
	}

	private static int[] generateArray(int x) {
		int[] myArray = new int[x];
		for (int i = 0; i < x; i++) {
			myArray[i] = i;
		}
		return myArray;
	}

	// testing
	public static void main(String[] args) {
		int n = 50000; // starting value

		long targetTime = 60000; // one minute
		long threshold = 10000; // max. value the runtime can be from a minute (10 seconds)

		boolean success = false;
		do {
			int[] testArray = generateArray(n);
			long startTime = System.currentTimeMillis();
			boolean temp = unique2(testArray);
			long endTime = System.currentTimeMillis();
			long elapsed = endTime - startTime;
			long distance = targetTime - elapsed; // negative value means runtime is above a minute.
			System.out.println(
					String.format("n: %9d took %12d milliseconds %12d ms away from 1 minute", n, elapsed, distance));

			if (elapsed <= targetTime && elapsed > (targetTime - threshold)) {
				// a closest value has been reached
				System.out.println("Largest value of 'n' which has a runtime closest to 1 minute: " + n);
				success = true;
			} else if (elapsed > targetTime) {
				// runtime too long/n too large
				n /= 2;
			} else if (elapsed < (targetTime - threshold)) {
				// runtime too short/n too small
				n *= 1.5;
			}
		} while (success == false);
	}
}
