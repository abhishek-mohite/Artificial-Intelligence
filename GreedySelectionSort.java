import java.util.Arrays;

public class GreedySelectionSort {

    // Function to perform greedy selection sort
    static void greedySelectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                // Find the index of the minimum element in the unsorted part of the array
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the minimum element with the first element of the unsorted part
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        System.out.println("Original array: " + Arrays.toString(arr));

        // Perform greedy selection sort
        greedySelectionSort(arr);

        System.out.println("Sorted array using Greedy Selection Sort: " + Arrays.toString(arr));
    }
}

