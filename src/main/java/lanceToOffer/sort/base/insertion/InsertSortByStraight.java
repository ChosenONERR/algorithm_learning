package lanceToOffer.sort.base.insertion;

import java.util.Arrays;

/**
 * 直接插入排序
 *
 * 算法描述：（两个过程：比较和移动（是移动不是交换）：比较是为了获得插入位置，移动是为了插入）
 * 一般来说，插入排序都在数组上实现，基于“比较”和“移动”两个动作进行。具体算法描述如下：
 *     1.从第一个元素开始，该元素可以认为已经被排序
 *     2.取出下一个元素，在已经排序的元素序列中从后向前扫描
 *     3.如果该元素（已排序）大于新元素，将该元素移到下一位置
 *     4.重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
 *     5.将新元素插入到该位置后
 *     6.重复步骤2~5
 *复杂度分析：
 * 平均时间复杂度  O(n²)
 * 最好情况 O(n)
 * 最坏情况 O(n²)
 * 空间复杂度 O(1)
 *
 * 插入排序所需的时间取决于输入元素的初始顺序（元素的比较次数和移动次数都依赖于排序表的初始状态）。
 * 例如，对一个很大且其中的元素已经有序(或接近有序)的数组进行排序，将会比随机顺序的数组或是逆序数组进行排序要快得多。
 */
public class InsertSortByStraight {
    public static void straightInsert(int[] arr){
        //i表示未排序的数组元素下标
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];//用num保存将要进行比较的新元素arr[i]
            int j;//j表示已排序数组的下标，j初始=0，默认数组第一个元素已经排好序了
            for(j=i; j>0 && num<arr[j-1]; j--){
                //如果num<arr[j-1]，也就是从后往前遍历已排序的数组，若当前元素大于新元素，则往后挪【从小到大】
                arr[j] = arr[j-1];
            }
            //如果num>=arr[j-1]，则不往后移，元素直接放入arr[j]（P.S:此处是大于等于而不是大于，这样保证了配许的稳定性）
            arr[j] = num;
        }
    }

    public static void main(String[] args) {
        int[] arr={8,9,1,7,2,3,5,4,6,0};
        straightInsert(arr);
        System.out.println(Arrays.toString(arr));
    }
}
