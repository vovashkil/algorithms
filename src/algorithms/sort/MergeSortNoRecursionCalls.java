package algorithms.sort;

public class MergeSortNoRecursionCalls {
    private static int counter_check = 0;
    private static int counter_permutation = 0;

    void mergeSort(int[] arr) {

        int[] temp = new int[arr.length];
        int k;

        // we broke down the array to arrays with length(size) == 1
        int size = 1, left_l, left_r, right_l, right_r;
        boolean left_branch = true;

        while (size < arr.length) {
            left_l = 0;
            k = 0;

            while (left_l + size < arr.length) {

                left_r = left_l + size - 1;
                right_l = left_r + 1;

                right_r = right_l + size - 1;
                if (right_r >= arr.length) {
                    right_r = arr.length - 1;
                }

                int i = left_l;
                int j = right_l;

                while (i <= left_r && j <= right_r) {
                    if (arr[i] <= arr[j]) {
                        if (left_branch) {
                            counter_permutation++;
                        }
                        counter_check++;
                        temp[k] = arr[i];
                        k++;
                        i++;
                        left_branch = true;
                    } else {
                        if (!left_branch) {
                            counter_permutation++;
                        }
                        counter_check++;
                        temp[k] = arr[j];
                        k++;
                        j++;
                        left_branch = false;
                    }
                }

                while (i <= left_r) {
                    temp[k] = arr[i];
                    k++;
                    i++;
                }

                while (j <= right_r) {
                    temp[k] = arr[j];
                    k++;
                    j++;
                }

                left_l = right_r + 1;

            }

            System.arraycopy(temp, 0, arr, 0, left_l);

//            System.out.printf("Size=%d, ", size);
//            Utils.printArray("Being sorted array: \n", arr);

            size = size * 2;

        }

    }

    public static void main(String[] args) {
        int[] arr = Utils.create_random_data(21);
        Utils.printArray("Source array", arr);
        new MergeSortNoRecursionCalls().mergeSort(arr);
        Utils.printArray("\nSorted array", arr);

        System.out.printf("Checks count: %d\nPermutations count: %d\n", counter_check, counter_permutation);


        System.out.printf("Sort(len) :      merge(sort(1/2) + sort 1/2)\n");
        int len_time = 0;
        int len_time_half1 = 0;
        int len_time_half2 = 0;
        int iterations = 0;

        for (int i = 0; i < 10; i++) {
            int[] arr1 = Utils.create_random_data(500000);
            int[] arr2 = Utils.create_random_data(500000);
            int[] arr3 = new int[arr1.length + arr2.length];
            System.arraycopy(arr1, 0, arr3, 0, arr1.length);
            System.arraycopy(arr2, 0, arr3, arr1.length, arr2.length);
//        Utils.printArray("\narr1: ", arr1);
//        Utils.printArray("\narr2: ", arr2);
//        Utils.printArray("\narr3: ", arr3);
            long time1_start = System.currentTimeMillis();
            new MergeSortNoRecursionCalls().mergeSort(arr1);
            long time1_finish = System.currentTimeMillis();

            long time2_start = System.currentTimeMillis();
            new MergeSortNoRecursionCalls().mergeSort(arr2);
            long time2_finish = System.currentTimeMillis();

            long time3_start = System.currentTimeMillis();
            new MergeSortNoRecursionCalls().mergeSort(arr3);
            long time3_finish = System.currentTimeMillis();

            System.out.printf("%5d\t\t\t%5d\t\t+\t\t%5d\t\t(%d)\n",
                    time3_finish - time3_start,
                    time1_finish - time1_start,
                    time2_finish - time2_start,
                    time1_finish - time1_start + time2_finish - time2_start
            );
            iterations = i;
        }


    }


}
