package algorithms.sort;

public class MergeSort {
  private static int counter_check = 0;
  private static int counter_permutation = 0;

  void merge(int[] arr, int l, int m, int r) {

    int size_l = m - l + 1;
    int size_r = r - m;

    int[] left = new int[size_l];
    int[] right = new int[size_r];

    // Copy
    for (int i=0; i < size_l; ++i) {
      left[i] = arr[l + i];
      counter_check++;
    }
    for (int j=0; j < size_r; ++j) {
      right[j] = arr[m + 1 + j];
      counter_check++;
    }
    // Merge
    int i = 0, j = 0;
    // Initial index of merged sub-array
    int k = l;
    while (i < size_l && j < size_r) {
      if (left[i] <= right[j]) {
        arr[k] = left[i++];
      } else {
        arr[k] = right[j++];
      }
      k++;
    }

    while (i < size_l) {
      arr[k++] = left[i++];
    }

    while (j < size_r) {
      arr[k++] = right[j++];
    }
  }

  void sort(int[] arr, int l, int r) {
    if (l < r) {
      int m = (l+r)/2;
      sort(arr, l, m);
      sort(arr, m+1, r);
      merge(arr, l, m, r);
    }
  }

  public static void main(String[] args) {
    int[] arr = Utils.create_random_data(10);
    Utils.printArray("Source array", arr);
    new MergeSort().sort(arr, 0, arr.length-1);
    Utils.printArray("Sorted array", arr);
    System.out.printf("Checks count: %d\nPermutations count: %d\n", counter_check, counter_permutation);
    System.out.printf("Total: %d\n", counter_check * 2 + counter_permutation * 4);

    System.out.printf("Sort(len) :      merge(sort(1/2) + sort 1/2)\n");

    for (int i=0; i < 10; i++) {
      int[] arr1 = Utils.create_random_data(500000);
      int[] arr2 = Utils.create_random_data(500000);
      int[] arr3 = new int[arr1.length + arr2.length];
      System.arraycopy(arr1, 0, arr3, 0, arr1.length);
      System.arraycopy(arr2, 0, arr3, arr1.length, arr2.length);
//        Utils.printArray("\narr1: ", arr1);
//        Utils.printArray("\narr2: ", arr2);
//        Utils.printArray("\narr3: ", arr3);
      long time1_start = System.currentTimeMillis();
      new MergeSort().sort(arr1, 0, arr1.length-1);
      long time1_finish = System.currentTimeMillis();

      long time2_start = System.currentTimeMillis();
      new MergeSort().sort(arr2, 0, arr2.length-1);
      long time2_finish = System.currentTimeMillis();

      long time3_start = System.currentTimeMillis();
      new MergeSort().sort(arr3, 0, arr3.length-1);
      long time3_finish = System.currentTimeMillis();

      System.out.printf("%5d\t\t\t%5d\t\t+\t\t%5d\t\t(%d)\n",
              time3_finish - time3_start,
              time1_finish - time1_start,
              time2_finish - time2_start,
              time1_finish - time1_start + time2_finish - time2_start
      );

    }

  }
}
