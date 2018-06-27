package optimalAlgorithm;

import java.util.Arrays;

/*
 *Created by William on 2018/6/27 0027
 * 冒泡排序
 */
public class bubbleSort {
    /**
     * （1）基础算法，从左到右一直交换，每次排好左右边的一个数，无论数据状况如何时间复杂度都是O（N平方）
     */
    public static void bubbleSort1(int[] arr) {
        for (int end = arr.length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    /**
     *  （2）设置一个标志，如果这一趟发生了交换，则为true，否则为false。明显如果有一趟没有发生交换，
     *  说明排序已经完成。数据是有序的时候，时间复杂度是O（N）；
     */
    public static void bubbleSort2(int[] arr) {
        boolean flag = true;
        int end = arr.length;
        while (flag) {
            flag = false;
            for (int i = 0; i < end - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    flag = true;
                }
            }
            end--;
        }
    }

    /**
     *  （3）再进一步做优化。比如，现在有一个包含1000个数的数组，仅前面100个无序，
     *  后面900个都已排好序且都大于前面100个数字，那么在第一趟遍历后，最后发生交换的位置必定小于100，
     *  且这个位置之后的数据必定已经有序了，也就是这个位置以后的数据不需要再排序了，于是记录下这位置，
     *  第二次只要从数组头部遍历到这个位置就可以了。如果是对于上面的冒泡排序算法2来说，
     *  虽然也只排序100次，但是前面的100次排序每次都要对后面的900个数据进行比较，而对于现在的排序算法3，
     *  只需要有一次比较后面的900个数据，之后就会设置尾边界，保证后面的900个数据不再被排序。
     */
    public static void bubbleSort3(int[] arr){
        boolean flag = true;
        int end = arr.length;
        int record = 0;
        while (flag) {
            flag = false;
            for (int i = 0; i < end - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    flag = true;
                    record = i;
                }
            }
            end = record;
        }
    }


    public static void main(String[] args) {
        int[] array1 = arrayGenerator(10001);
        int[] array2 = array1;
        bubbleSort3(array1);
        Arrays.sort(array2);
        System.out.println(sortJudge(array1, array2));
    }

    public static int[] arrayGenerator(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * 100 - 50);
        }
        return arr;
    }

    public static String sortJudge(int[] arrayRight, int[] arraySort){
        for(int i =0; i< arrayRight.length;i++){
            if (arrayRight[i] != arraySort[i])  return "不对，fuck you";
        }
        return "恭喜对了";
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
    public static void printArray(int[] arr){
        for(int i : arr){
            System.out.print(i+"  ");
        }
    }
}
