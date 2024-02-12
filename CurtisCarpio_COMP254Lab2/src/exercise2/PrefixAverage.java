package exercise2;

public class PrefixAverage {

	/**
	 * Returns an array a such that, for all j, a[j] equals the average of x[0],
	 * ..., x[j].
	 */
	public static double[] prefixAverage1(double[] x) {
		int n = x.length;
		double[] a = new double[n]; // filled with zeros by default
		for (int j = 0; j < n; j++) {
			double total = 0; // begin computing x[0] + ... + x[j]
			for (int i = 0; i <= j; i++)
				total += x[i];
			a[j] = total / (j + 1); // record the average
		}
		return a;
	}

	/**
	 * Returns an array a such that, for all j, a[j] equals the average of x[0],
	 * ..., x[j].
	 */
	public static double[] prefixAverage2(double[] x) {
		int n = x.length;
		double[] a = new double[n]; // filled with zeros by default
		double total = 0; // compute prefix sum as x[0] + x[1] + ...
		for (int j = 0; j < n; j++) {
			total += x[j]; // update prefix sum to include x[j]
			a[j] = total / (j + 1); // compute average based on current sum
		}
		return a;
	}

	private static double[] generateArray(int x) {
		double[] myArray = new double[x];
		for (int i = 0; i < x; i++) {
			myArray[i] = i;
		}
		return myArray;
	}

	// experimental tests (partially borrowed from StringExperiment)
	public static void main(String[] args) {
		int n = 50000; // starting value
		int trials = 10;
		try {
			if (args.length > 0)
				trials = Integer.parseInt(args[0]);
			if (args.length > 1)
				n = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
		}
		int start = n; // remember the original starting value

		System.out.println("Testing prefixAverage2...");
		for (int t = 0; t < trials; t++) {
			double[] testArray = generateArray(n);	//generate the testing array
			long startTime = System.currentTimeMillis();
			double[] temp = prefixAverage2(testArray);
			long endTime = System.currentTimeMillis();
			long elapsed = endTime - startTime;
			System.out.println(String.format("n: %9d took %12d milliseconds", n, elapsed));
			n *= 2; // double the problem size
		}

		System.out.println("Testing prefixAverage1...");
		n = start; // restore n to its start value
		for (int t = 0; t < trials; t++) {
			double[] testArray = generateArray(n);	//generate the testing array
			long startTime = System.currentTimeMillis();
			double[] temp = prefixAverage1(testArray);
			long endTime = System.currentTimeMillis();
			long elapsed = endTime - startTime;
			System.out.println(String.format("n: %9d took %12d milliseconds", n, elapsed));
			n *= 2; // double the problem size
		}
	}

}