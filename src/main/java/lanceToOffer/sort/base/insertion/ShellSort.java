package lanceToOffer.sort.base.insertion;

import java.util.Arrays;

/**
 * 希尔排序（缩小增量排序）
 *
 * 思想：
 * 将待排序数组按照步长gap（也叫增量）进行分组，然后将每组的元素利用直接插入排序的方法进行排序；每次再将gap折半减小，
 * 循环上述操作；当gap=1时，利用直接插入，完成排序
 * 例如：
 * 初始数组：{8,9,1,7,2,3,5,4,6,0}
 * 第一轮：先计算步长，初始为数组长度 / 2 = 5，然后按步长进行分组，得到5组，分别是：{8，3}、{9，5}、{1，4}、{7，6}、{2，0}
 *         然后对每一组进行直接插入排序，得到{3，8}、{5，9}、{1，4}、{6，7}、{0，2}
 *         然后数组整体变为{3，5，1，6，0，8，9，4，7，2}，如此一来，该数组与初始数组相比，“更加有序”
 * 第二轮：再计算步长，为上一轮的步长 / 2 = 5/2 = 2，然后按照步长进行分组，得到2组，分别是{3，1，0，9，7}、{5，6，8，4，2}
 *         然后对每一组进行直接插入排序，得到{0，1，3，7，9}、{2，4，5，6，8}
 *         然后数组的整体变为{0，2，1，4，3，5，7，6，9，8}
 * ............不断进行，直至步长=1的时候为最后一轮，此时每一组只有一个元素，相当于直接对数组整体进行直接插入排序。
 *
 * 总结：
 * 希尔排序的思想而已看出，实际上每一轮的分组排序，都是为了得到一个相对更加有序的数组，
 * 待排序列的初始状态越有序，对插入排序更好
 *
 * 时间复杂度：
 */
public class ShellSort {
    public static void shellSort(int[] arr){
        //控制步长（步长初始为数组长度/2，而后每次都/2，直至增量step=1，包括step=1）
        for (int step = arr.length /2; step>0; step/=2){
            //下面两个for循环组合在一起，就是对各个组进行直接插入排序
            for (int i = step; i < arr.length; i++) {
                //j是比较的浮标
                int j = i;
                //保存待排元素
                int temp = arr[j];
                //下面的wile循环是对当前步长组进行直接插入排序，其中 j-step 表示和j 同组的隔壁的元素
                while(j-step>=0 && temp<arr[j-step]){
                    arr[j] = arr[j-step];//把元素往后挪
                    j = j-step;
                }
                //得到待排元素的最终位置j，把待排元素插入
                arr[j] = temp;
            }
        }
    }
    public static void main(String[] args) {
        int arr[]={8,9,1,7,2,3,5,4,6,0};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
