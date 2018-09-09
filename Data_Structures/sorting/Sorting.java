import java.util.Random;

import Plotter.Plotter;


public class Sorting {


	final static int BUBBLE_VS_QUICK_LENGTH = 12;
	final static int MERGE_VS_QUICK_LENGTH = 15;
	final static int BUBBLE_VS_QUICK_SORTED_LENGTH = 12;
	final static int ARBITRARY_VS_MEDIAN_LENGTH = 16;
	final static double T = 600.0;


    /**
     * A wrapper function for quickSortArbitraryPivot.
     * Sorts a given array using the quick sort algorithm.
     * At each stage the pivot is chosen to be the rightmost element of the subarray.
     *
     * Should run in average complexity of O(nlog(n)), and worst case complexity of O(n^2)
     *
     * @param arr - the array to be sorted
     */
	public static void quickSortArbitraryPivot(double[] arr) {
        quickSortArbitraryPivot(arr, 0, arr.length - 1);
	}

    /**
     * Sorts a given array using the quick sort algorithm.
     * At each stage the pivot is chosen to be the rightmost element of the subarray.
     *
     * Should run in average complexity of O(nlog(n)), and worst case complexity of O(n^2)
     *
     * @param arr - the array to be sorted
     * @param start - sub-array start index
     * @param arr - sub-array end index
     */
	public static void quickSortArbitraryPivot(double[] arr, int start, int end) {

        if (start < end) {
            double pivot = arr[end];
            int i = start - 1;
            for (int j = start; j < end; j++) {
                if (arr[j] <= pivot) {
                    i++;
                    double temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }

            }
            double temp = arr[i + 1];
            arr[i + 1] = pivot;
            arr[end] = temp;
            int q = i + 1;
            quickSortArbitraryPivot(arr, start, q - 1);
            quickSortArbitraryPivot(arr, q + 1, end);
        }
    }

    /**
     * Receives three numbers and returns the middle number.
     *
     * @param a - number #1
     * @param b - number #2
     * @param c - number #3
     */
    public static double findMiddle(double a, double b, double c) {

	    if (a == b) return Math.min(b,c);
        else if (b == c) return Math.min(a,b);

	    if (a > b) {
	        if (b > c) return b;
        }
        else if (c > b) {
	        if (b > a) return b;
        }

        if (b > a) {
            if (a > c) return a;
        }
        else if (c > a) {
            if (a > b) return a;
        }

        if (a > c) {
            if (c > b) return c;
        }
        else if (b > c) {
            if (c > a) return c;
        }

        return -1;
    }

    /**
     * The function finds the median of the most left element, the most right element
     * and the middle element.
     * Then it re-ordering the sub-array such that the smallest of the three numbers
     * is in the left index, the median is in the middle, and the largest number is
     * in the right index.
     * Finally, the function swaps the median and the second-right element in the sub-array,
     * and returns the median number (which is later the pivot).
     *
     * @param a - an unsorted array of numbers
     * @param start - left bound index
     * @param end - right bound index
     */
    public static double medianOfThree(double[] a, int start, int end) {

        double median = findMiddle(a[start], a[end], a[(start+end)/2]);
        double temp1 = a[end - 1];
        double temp2 = a[end];

        a[end] = Math.max(Math.max(a[start], a[(start+end)/2]), a[end]);
        a[start] = Math.min(Math.min(a[start], a[(start+end)/2]), temp2);
        a[end - 1] = median;
        a[(end+start)/2] = temp1;

	    return median;
    }

    /**
     * Partition function for the arbitrary chosen pivot (most right element).
     * The function takes the most right element as the pivot,
     * and re-ordering the sub-array such that all the elements smaller than the pivot
     * is on the left side, all the bigger elements are on the right side, and finally
     * puts the pivot between the two sides, and returns its new index.
     *
     * @param arr - an unsorted array of numbers
     * @param start - sub-array start index
     * @param end - sub-array end index
     */
    public static int partition(double[] arr, int start, int end) {
        double pivot = arr[end - 1];
        int i = start, j = end - 1;
        while (i < j) {
            while (arr[j] > pivot)
                j--;

            while (arr[i] < pivot)
                i++;

            if (i < j) {
                double temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
            else {
                double temp = arr[j + 1];
                arr[j + 1] = pivot;
                arr[end - 1] = temp;
            }
        }
        return j + 1;
    }
	
	/**
     * * A wrapper function for quickSortMedianPivot.
	 * Sorts a given array using the quick sort algorithm.
	 * At each stage the pivot is chosen in the following way:
	 * Choose 3 random elements from the array, the pivot is the median of the 3 elements.
	 * 
	 * Should run in average complexity of O(nlog(n)), and worst case complexity of O(n^2)
	 * 
	 * @param arr - the array to be sorted
	 */
	public static void quickSortMedianPivot(double[] arr){
        quickSortMedianPivot(arr, 0, arr.length - 1);
	}

    /**
     * Sorts a given array using the quick sort algorithm.
     * At each stage the pivot is chosen in the following way:
     * Choose 3 random elements from the array, the pivot is the median of the 3 elements.
     *
     * Should run in average complexity of O(nlog(n)), and worst case complexity of O(n^2)
     *
     * @param arr - the array to be sorted
     * @param start - sub-array start index
     * @param arr - sub-array end index
     */
    public static void quickSortMedianPivot(double[] arr, int start, int end) {

        if (start + 2 < end) {
            double pivot = medianOfThree(arr, start, end);
            int q = partition(arr, start, end);
            quickSortMedianPivot(arr, start, q - 1);
            quickSortMedianPivot(arr, q + 1, end);
        }
        else {
            bubbleSort(arr, start, end);
        }
    }

    /**
     * * A wrapper function for mergeSort.
     * Sorts a given array using the merge sort algorithm.
     *
     * @param arr - the array to be sorted
     */
	public static void mergeSort(double[] arr) {
	    mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * Sorts a given array using the merge sort algorithm.
     *
     * Should run in complexity O(nlog(n)) in the worst case.
     *
     * @param arr - the array to be sorted
     * @param start - sub-array start index
     * @param end - sub-array end index
     */
    public static void mergeSort(double[] arr, int start, int end) {

	    if (start < end - 10) {
	        int q = (start + end) / 2;
	        mergeSort(arr, start, q);
	        mergeSort(arr, q + 1, end);
            merge(arr, start, q, end);
        }
        else {
	        bubbleSort(arr, start, end);
        }
    }
//
    /**
     * The function create two new arrays according to the indexes she receives as arguments,
     * copies the original elements to the smaller arrays, and then inserts them back into the
     * original sub-array in a sorted order.
     *
     * Should run in complexity O(nlog(n)) in the worst case.
     *
     * @param arr - the array to be sorted
     * @param start - the array to be sorted
     * @param q - the array to be sorted
     * @param end - the array to be sorted
     */
    public static void merge(double[] arr, int start, int q, int end) {
        double[] L = new double[q - start + 2];
        double[] R = new double[end - q + 1];

        for (int i = 0; i < L.length - 1; i++) {
            L[i] = arr[start + i];
        }
        for (int i = 0; i < R.length - 1; i++) {
            R[i] = arr[q + 1 + i];
        }
        L[L.length - 1] = Double.POSITIVE_INFINITY;
        R[R.length - 1] = Double.POSITIVE_INFINITY;

        int i = 0, j = 0;
        for (int k = start; k <= end; k++) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
        }

    }

	/**
	 * Sorts a given array using bubble sort.
	 * If at any time the algorithm recognizes no more inversions are needed it should stop.
	 * 
	 * The algorithm should run in complexity O(n^2) in the worst case.
	 * The algorithm should run in complexity O(n) in the best case.
	 * 
	 * @param arr - the array to be sorted
	 */
	public static void bubbleSort(double[] arr){
        bubbleSort(arr, 0, arr.length - 1);
	}

	public static void bubbleSort(double[] arr, int start, int end) {
        if (end - start >= 1) {
            for (int i = start; i < end; i++) {
                boolean isSorted = true;
                for (int j = start + 1; j <= end; j++) {
                    if (arr[j - 1] > arr[j]) {      //if two elements aren't sorted - swap them
                        double temp = arr[j - 1];
                        arr[j - 1] = arr[j];
                        arr[j] = temp;
                        isSorted = false;
                    }
                }
                //that means no swap has been made after a single run on the array,
                // which implies the array is sorted
                if (isSorted) {
                    break;
                }
            }
        }
    }

	public static void main(String[] args) {
		bubbleVsQuick();
//		mergeVsQuick();
//		bubbleVsQuickOnSortedArray();
//		arbitraryPivotVsMedianPivot();
	}

	/**
	 * Compares the selection sort algorithm against quick sort on random arrays
	 */
	public static void bubbleVsQuick(){
		double[] quickTimes = new double[BUBBLE_VS_QUICK_LENGTH];
		double[] bubbleTimes = new double[BUBBLE_VS_QUICK_LENGTH];
		long startTime, endTime;
		Random r = new Random();
		for (int i = 0; i < BUBBLE_VS_QUICK_LENGTH; i++) {
			long sumQuick = 0;
			long sumSelection = 0;
			for(int k = 0; k < T; k++){
				int size = (int)Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = r.nextGaussian() * 5000;
					b[j] = a[j];
				}
				startTime = System.currentTimeMillis();
				quickSortArbitraryPivot(a);
				endTime = System.currentTimeMillis();
				sumQuick += endTime - startTime;
				startTime = System.currentTimeMillis();
				bubbleSort(b);
				endTime = System.currentTimeMillis();
				sumSelection += endTime - startTime;
			}
			quickTimes[i] = sumQuick/T;
			bubbleTimes[i] = sumSelection/T;
		}
		Plotter.plot("quick sort on random array", quickTimes, "bubble sort on random array", bubbleTimes);
	}
	
	/**
	 * Compares the merge sort algorithm against quick sort on random arrays
	 */
	public static void mergeVsQuick(){
		double[] quickTimes = new double[MERGE_VS_QUICK_LENGTH];
		double[] mergeTimes = new double[MERGE_VS_QUICK_LENGTH];
		long startTime, endTime;
		Random r = new Random();
		for (int i = 0; i < MERGE_VS_QUICK_LENGTH; i++) {
			long sumQuick = 0;
			long sumMerge = 0;
			for (int k = 0; k < T; k++) {
				int size = (int)Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = r.nextGaussian() * 5000;
					b[j] = a[j];
				}
				startTime = System.currentTimeMillis();
				quickSortArbitraryPivot(a);
				endTime = System.currentTimeMillis();
				sumQuick += endTime - startTime;
				startTime = System.currentTimeMillis();
				mergeSort(b);
				endTime = System.currentTimeMillis();
				sumMerge += endTime - startTime;
			}
			quickTimes[i] = sumQuick/T;
			mergeTimes[i] = sumMerge/T;
		}
		Plotter.plot("quick sort on random array", quickTimes, "merge sort on random array", mergeTimes);
	}

	/**
	 * Compares the bubble sort algorithm against quick sort on pre-sorted arrays
	 */
	public static void bubbleVsQuickOnSortedArray(){
		double[] quickTimes = new double[BUBBLE_VS_QUICK_SORTED_LENGTH];
		double[] bubbleTimes = new double[BUBBLE_VS_QUICK_SORTED_LENGTH];
		long startTime, endTime;
		for (int i = 0; i < BUBBLE_VS_QUICK_SORTED_LENGTH; i++) {
			long sumQuick = 0;
			long sumBubble = 0;
			for (int k = 0; k < T; k++) {
				int size = (int)Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = j;
					b[j] = j;
				}
				startTime = System.currentTimeMillis();
				quickSortArbitraryPivot(a);
				endTime = System.currentTimeMillis();
				sumQuick += endTime - startTime;
				startTime = System.currentTimeMillis();
				bubbleSort(b);
				endTime = System.currentTimeMillis();
				sumBubble += endTime - startTime;
			}
			quickTimes[i] = sumQuick/T;
			bubbleTimes[i] = sumBubble/T;
		}
		Plotter.plot("quick sort on sorted array", quickTimes, "bubble sort on sorted array", bubbleTimes);
	}

	/**
	 * Compares the quick sort algorithm once with a choice of an arbitrary pivot and once with a choice of a median pivot
	 */
	public static void arbitraryPivotVsMedianPivot(){
		double[] arbitraryTimes = new double[ARBITRARY_VS_MEDIAN_LENGTH];
		double[] medianTimes = new double[ARBITRARY_VS_MEDIAN_LENGTH];
		long startTime, endTime;
		Random r = new Random();
		for (int i = 0; i < ARBITRARY_VS_MEDIAN_LENGTH; i++) {
			long sumArbitrary = 0;
			long sumMedian = 0;
			for (int k = 0; k < T; k++) {
				int size = (int)Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = r.nextGaussian() * 5000;
					b[j] = a[j];
				}
				startTime = System.currentTimeMillis();
				quickSortArbitraryPivot(a);
				endTime = System.currentTimeMillis();
				sumArbitrary += endTime - startTime;
				startTime = System.currentTimeMillis();
				quickSortMedianPivot(b);
				endTime = System.currentTimeMillis();
				sumMedian += endTime - startTime;
			}
			arbitraryTimes[i] = sumArbitrary/T;
			medianTimes[i] = sumMedian/T;
		}
		Plotter.plot("quick sort with an arbitrary pivot", arbitraryTimes, "quick sort with a median pivot", medianTimes);
	}
	
}
