package lanceToOffer.sort.base.selection;

/**
 * 基本思想：
 *  选择排序（Selection sort）是一种简单直观的排序算法。它的工作原理如下。首先在未排序序列中找到最小（大）元素，
 *  存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，
 *  直到所有元素均排序完毕。
 *
 *  选择排序的主要优点与数据移动有关。如果某个元素位于正确的最终位置上，则它不会被移动。选择排序每次交换一对元素，
 *  它们当中至少有一个将被移到其最终位置上，因此对 n个元素的表进行排序总共进行至多 n-1 次交换。
 *  在所有的完全依靠交换去移动元素的排序方法中，选择排序属于非常好的一种。
 *
 *  代码步骤：
 * 1.从未排序序列中，找到关键字最小的元素
 * 2.如果最小元素不是未排序序列的第一个元素，将其和未排序序列第一个元素互换
 * 3.重复1、2步，直到排序结束。
 *
 * 时间复杂度：O(n²)
 * 最好情况：O(n²)
 * 最坏情况：O(n²)
 * 空间复杂度：O(n²)
 * 稳定性：不稳定
 *
 * 选择排序的简单和直观名副其实，这也造就了它”出了名的慢性子”，无论是哪种情况，哪怕原数组已排序完成，
 * 它也将花费将近n²/2次遍历来确认一遍。即便是这样，它的排序结果也还是不稳定的。 唯一值得高兴的是，
 * 它并不耗费额外的内存空间。
 */
public class SimpleSelectionSort {
    /**
     * 从小到大
     */
    public static void simpleSelectionSort(int[] arr){
        if(arr==null || arr.length<2){
            return;
        }
        /**
         * 代码思路：从0-(n-1)的位置上找到最小值，和0位置交换；从1-(n-1)的位置上找到最小值，和1位置交换；以此类推...得到从小到大的排序数组
         */
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;//用变量minIndex记录最值下标
            for(int j=i+1; j<arr.length; j++){
                if(arr[minIndex] > arr[j]){
                    //前后比较，定位最值
                    minIndex = j;
                }
            }
            // 交换
            if(i != minIndex){
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
}