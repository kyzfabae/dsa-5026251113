package lw04.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        // 1. Read input
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("parcels.txt"));

        int n = scanner.nextInt();
        int[] parcels = new int[n];
        for (int i = 0; i < n; i++) {
            parcels[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = scanner.nextInt();
        }
        scanner.close();

        // 2. Bubble sort stage 1: exactly 2 passes
        bubbleSort(parcels, 0, 2);

        // 3. Copy the shelf right after the second pass
        int[] afterTwoPasses = new int[n];
        for (int i = 0; i < n; i++) {
            afterTwoPasses[i] = parcels[i];
        }

        // 4. Bubble sort stage 2: continue until N - 1 passes in total
        bubbleSort(parcels, 2, n - 1);

        // 5. Display both shelves
        System.out.println("==== After 2 Passes ====");
        printArray(afterTwoPasses);

        System.out.println("==== Fully Sorted ====");
        printArray(parcels);

        // 6. Binary search for each target
        System.out.println("==== Search Results ====");
        for (int i = 0; i < m; i++) {
            int[] result = binarySearch(parcels, targets[i]);
            int index = result[0];
            int steps = result[1];

            if (index != -1) {
                System.out.println(targets[i] + ": FOUND at index " + index + " (steps: " + steps + ")");
            } else {
                System.out.println(targets[i] + ": NOT FOUND (steps: " + steps + ")");
            }
        }
    }

    // Runs bubble sort passes from fromPass (inclusive) to toPass (exclusive)
    static void bubbleSort(int[] a, int fromPass, int toPass) {
        int n = a.length;
        for (int i = fromPass; i < toPass; i++) {
            for (int j = 0; j <= n - 2 - i; j++) {
                if (a[j] < a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    // Binary search on a descending array, returns {index, steps}
    static int[] binarySearch(int[] a, int target) {
        int low = 0;
        int high = a.length - 1;
        int steps = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            steps++;

            if (a[mid] == target) {
                return new int[]{mid, steps};
            } else if (a[mid] > target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new int[]{-1, steps};
    }

    static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if (i < a.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}