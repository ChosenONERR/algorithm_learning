package lanceToOffer.sort.base.others;

import java.util.Arrays;

/**
 * 归并排序
 *
 * 算法思想：
 *      分治法。分治法的基本思想是将一个规模为n的问题分解为k个规模较小的子问题，这些子问题互相独立且与原问题相同。
 *      归并排序算法是将两个（或两个以上）有序表合并成一个新的有序表，即把待排序序列分为若干个子序列，
 *      每个子序列是有序的。然后再把有序子序列合并为整体有序序列。
 * 归并排序可通过两种方式实现:
 *     1、自上而下的递归
 *          ① 分解 -- 将当前区间一分为二，即求分裂点 mid = (low + high)/2;
 *          ② 求解 -- 递归地对两个子区间a[low...mid] 和 a[mid+1...high]进行归并排序。递归的终结条件是子区间长度为1。
 *          ③ 合并 -- 将已排序的两个子区间a[low...mid]和 a[mid+1...high]归并为一个有序的区间a[low...high]。
 *     2、自下而上的迭代
 * 平均时间复杂度   O(nlog₂n)
 * 最好情况   O(nlog₂n)
 * 最坏情况   O(nlog₂n)
 * 空间复杂度   O(n)
 *
 * 从效率上看，归并排序可算是排序算法中的”佼佼者”. 假设数组长度为n，那么拆分数组共需logn，,
 * 又每步都是一个普通的合并子数组的过程， 时间复杂度为O(n)， 故其综合时间复杂度为O(nlogn)。另一方面，
 * 归并排序多次递归过程中拆分的子数组需要保存在内存空间， 其空间复杂度为O(n)。
 *
 * 归并排序最吸引人的性质是它能够保证将任意长度为N的数组排序所需时间和NlogN成正比，
 * 它的主要缺点则是他所需的额外空间和N成正比
 */
public class MergeSort {

    //arrTemp01 归并所需的辅助数组
    private static int[] arrTemp01;

    /**
     * mergeAndSort01方式的排序入口
     */
    public static void sortMain(int[] arr){
        //一次性分配空间,大小和待排数组长度一样
        arrTemp01 = new int[arr.length];
        //从上往下，也就是先分解
        decompose(arr, 0, arr.length-1);
    }

    /**
     * mergeAndSort01 归并两个待排区间（归并 = 排序 + 合并）
     *      采用全局辅助数组，空间复杂度小
     *
     * @param arr 待排数组
     * @param start 第1个待合并区间的起始地址
     * @param mid 第1个待合并区间的结束地址
     * @param end 第2个待合并区间的结束地址
     */
    public static void mergeAndSort01(int[] arr, int start, int mid, int end){
        //指针i记录第1个待合并区间的元素位置
        int i = start;
        //指针j记录第2个待合并区间的元素位置
        int j = mid + 1;

        //先用辅助数组保存两个区间的元素
        for(int k=start; k<=end; k++){
            arrTemp01[k] = arr[k];
        }

        //边合并，边排序
        for(int k=start; k<=end; k++){
            //以下注释代码是错误写法，判断的顺序不对，会导致数组越界异常
//            if(arr[i] < arr[j]){
//                arr[k] = arrTemp01[i++];
//            }else if(arr[i] > arr[j]){
//                arr[k] = arrTemp01[j++];
//            }else if(i>mid){
//                arr[k] = arrTemp01[j++];
//            }else {
//                arr[k] = arrTemp01[i++];
//            }

            //以下四个判断顺序不要乱
            if(i > mid){ //i已经超出第1个待合并区间，则取第二个待合并区间
                arr[k] = arrTemp01[j++];
            }else if(j > end){ //j已经超出第2个待合并区间，则取第一个待合并区间
                arr[k] = arrTemp01[i++];
            }else if(arr[i] > arr[j]){//指针均没超出，右半边的当前元素小于左半边的当前元素(取右半边的元素)
                arr[k] = arrTemp01[j++];
            }else {//指针均没超出，右半边的当前元素大于等于左半边的当前元素(取左半边的元素)
                arr[k] = arrTemp01[i++];
            }
        }
    }

    /**
     * mergeAndSort02 归并两个待排区间（归并 = 排序 + 合并）
     *      没有采用全局辅助数组，而是每次归并都创建一次辅助数组，导致空间复杂度会很高，不推荐
     *
     * 自上而下的递归A：合并且排序
     * @param arr 待排数组
     * @param start 第1个待合并区间的起始地址
     * @param mid 第1个待合并区间的结束地址
     * @param end 第2个待合并区间的结束地址
     */
    public static void mergeAndSort02(int[] arr, int start, int mid, int end){

        int[] arrTemp02 = new int[end - start +1];
        //指针i记录第1个待合并区间的元素位置
        int i = start;
        //指针j记录第2个待合并区间的元素位置
        int j = mid+1;
        //指针k记录临时数组位置
        int k = 0;

        //开始排序
        while(i<=mid && j<=end){ //i或者j都还没有超出指定待排区间
            if(arr[i] <= arr[j]){//别忘了这里是小于等于，不是小于
                arrTemp02[k++] = arr[i++];
            }else {
                arrTemp02[k++] = arr[j++];
            }
        }
        while(i<=mid){ //j已经超出第2个待合并区间
            arrTemp02[k++] = arr[i++];
        }
        while(j<=end){ //i已经超出第1个待合并区间区间
            arrTemp02[k++] = arr[j++];
        }
        // 将排序后的元素，全部都整合到数组a中。
        for (int l = 0; l < k; l++) {
            arr[start+l] = arrTemp02[l];
        }
    }

    /**
     * 自上而下的递归B：分解（2路归并排序）
     */
    public static void decompose(int[] arr, int left, int right){
        //鲁棒性
        if(arr==null || arr.length<=1){
            return;
        }
        //递归结束点
        if(left >= right){
            return;
        }

        //2路递归分解
        int mid = (left + right)/2;
        decompose(arr, left, mid);
        decompose(arr, mid+1, right);
        //合并且排序
        mergeAndSort01(arr, left, mid, right);
        //mergeAndSort02(arr, left, mid, right);
    }

    public static void main(String[] args) {
        int[] arr={8,9,1,7,2,3,5,4,6,0};
        //decompose(arr, 0, 9);
        sortMain(arr);
        System.out.println(Arrays.toString(arr));
    }
}
