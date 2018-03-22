package com.lin.java.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 输入一个整型数组，每个元素在0~2之间，其中0，1，2分别代表红、白、蓝。
 * 现要求对数组进行排序，相同颜色的在一起，而且按红白蓝顺序先后排列。要求时间复杂度为O(n)。
 *
 * 这个问题类似快排中partition过程，只是需要用到三个指针：一个前指针begin，一个中指针current，一个后指针end，
 * current指针遍历整个数组序列：
 * current指针所指元素为0时，与begin指针所指的元素交换，而后current++，begin++ ；
 * current指针所指元素为1时，不做任何交换（即球不动），而后current++ ；
 * current指针所指元素为2时，与end指针所指的元素交换，而后，current指针不动，end-- 。
 为什么上述第3点中，current指针所指元素为2时，与end指针所指元素交换之后，current指针不能动呢？因为第三步中current指针所指元素与end指针所指元素交换之前，如果end指针之前指的元素是0，那么与current指针所指元素交换之后，current指针此刻所指的元素是0，此时，current指针能动么？不能动，因为如上述第1点所述，如果current指针所指的元素是0，还得与begin指针所指的元素交换。
 */
public class ThreeColorSort {
    public Integer[] sort(Integer[] array) {
        int low_pos = 0;
        int high_pos = array.length-1;

        for(int i = 0; i < array.length;) {
            if(array[i] == 0) {
                swap(array, i, low_pos);
                low_pos++;
                i++;
            } else if(array[i] == 2 && i<high_pos) {
                swap(array, i, high_pos);
                high_pos--;
            } else if(array[i] == 1 && i<high_pos){
                i++;
            } else if(i>=high_pos){
                break;
            }
        }
        return array;
    }

    private void swap(Integer[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }


    public Integer[] genIntArray(Integer length) {
        Integer[] array = new Integer[length];
        for(int i = 0; i < length; i++) {
            array[i] = new Random().nextInt(3);
        }
        return array;
    }



    public static void main(String[] args) {
        ThreeColorSort tcs = new ThreeColorSort();
        Integer[] array = tcs.genIntArray(10);
        Arrays.stream(array).forEach(System.out::print);
        System.out.println("\n");
        Arrays.stream(tcs.sort(array)).forEach(System.out::print);
    }
}
