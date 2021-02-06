package lanceToOffer.sort.base.others;

import java.util.Arrays;

/**
 * 计数排序
 *
 * 基本思想：
 *     用一个辅助数组，把原数组元素作为辅助数组的下标，原数组元素出现的次数作为辅助数组的值，例如 temp[i] = m,
 *     表示元素 i 一共出现了 m 次。最后再把临时数组统计的数据从小到大汇总起来，此时汇总起来是数据是有序的
 *     计数排序是一种适合于最大值和最小值的差值不是不是很大的排序，否则辅助数组就会很大。
 *
 * 总结：（K表示临时数组的大小）
 * 时间复杂度：O(n+k)
 * 空间复杂度：O(k)
 * 稳定排序
 */
public class CountSort {
    /**
     * 版本1
     * @param arr 待排数组
     */
    public static void countSort01(int[] arr){
        //鲁棒性
        if(arr==null || arr.length<=1){
            return;
        }

        //1.找到待排数组元素的最大值
        int maxNum = 0;
        for (int i = 0; i < arr.length; i++) {
            if(maxNum < arr[i]){
                maxNum = arr[i];
            }
        }

        //创建一个大小为（maxNum+1）的辅助数组，这个数组把原数组元素值作为下标，值为该元素值出现的次数
        int capicity = maxNum + 1;
        int[] arrTemp = new int[capicity];
        //遍历原数组，记录原数组每个元素出现的次数（为辅助数组填值）
        for (int i = 0; i < arr.length; i++) {//i是原数组的指针（下标），则arr[i]是辅助数组的下标
            arrTemp[arr[i]]++;
        }
        //把临时数组统计好的数据汇总到原数组
        int k = 0;//k是原数组的下标
        for (int i = 0; i < capicity; i++) { //遍历辅助数组，i是辅助数组的下标
            for (int j = arrTemp[i]; j >0 ; j--) { //j是辅助数组每个元素的值
                arr[k++] = i;//当arrTemp[i]>0，则i就是原数组元素的值
            }
        }
    }


    /**
     * 版本2：优化
     * 上面的代码中，我们是根据 max 的大小来创建对应大小的数组，假如原数组只有10个元素，并且最小值为 min = 10000，
     * 最大值为 max = 10005，那我们创建 10005 + 1 大小的数组不是很吃亏，最大值与最小值的差值为 5，
     * 所以我们创建大小为6的临时数组就可以了
     *
     * 也就是说，我们创建的临时数组大小 (max - min + 1)就可以了，然后在把 min作为偏移量。
     */
    public static void countSort02(int[] arr){
        //鲁棒性
        if(arr==null || arr.length<=1){
            return;
        }

        //找到待排数组元素的最大和最小值
        int maxNum = 0, minNum = 0;
        for (int i = 0; i < arr.length; i++) {
            if(maxNum < arr[i]){
                maxNum=arr[i];
            }
            if(minNum > arr[i]){
                minNum=arr[i];
            }
        }

        //创建一个大小为（maxNum - minNum +1）的辅助数组，这个数组把原数组元素值作为下标，值为该元素值出现的次数
        int capicity = maxNum - minNum +1;
        int[] arrTemp = new int[capicity];
        //为辅助数组填值（遍历原数组，记录原数组每个元素出现的次数）
        for (int i = 0; i < arr.length; i++) {//i是原数组的指针（下标），则arr[i]是辅助数组的下标
            arrTemp[arr[i]-minNum]++;
        }

        //把临时数组统计好的数据汇总到原数组
        int k = 0;//k是原数组的下标
        for (int i = 0; i < capicity; i++) { //遍历辅助数组，i是辅助数组的下标
            for (int j = arrTemp[i]; j >0 ; j--) { //j是辅助数组每个元素的值
                arr[k++] = i+minNum;//当arrTemp[i]>0，则i+minNum就是原数组元素的值（minNum是偏移值）
            }
        }
    }
    public static void main(String[] args) {
        int[] arr={8,9,1,7,2,3,5,4,6,0};
        //decompose(arr, 0, 9);
        //countSort01(arr);
        countSort02(arr);
        System.out.println(Arrays.toString(arr));
    }
}
