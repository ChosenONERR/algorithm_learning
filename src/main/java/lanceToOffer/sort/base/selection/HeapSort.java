package lanceToOffer.sort.base.selection;

import java.util.Arrays;

/**
 * 堆排序
 * 思想：把二维数组看成一棵完全二叉树，且满足分支节点大于（小于）左右孩子节点，称之为堆，前者为大顶堆，后者为小顶堆。
 *       以大顶堆为例，堆排序的过程就是将待排序的序列构造成一个堆，选出堆中最大的移走，再把剩余的元素调整成堆，
 *       再找出最大的再移走，重复直至有序。
 *       （堆的逻辑结构是一棵完全二叉树，而在存储结构上是一个数组。数组元素的顺序是完全二叉树的层序遍历）
 *
 *  代码实现思路：
 *      两个过程：
 *      1、建堆
 *      2、堆顶与堆的最后一个元素j交换位置，并再次调用过程1（重新建堆）
 *
 *      对于节点的访问：0<i<(n-1) ---> 因为是数组，所以i从0开始
 *      1、父节点i 的 左子节点在位置：2i+1
 *      2、父节点i 的 右子节点在位置：2i+2
 *      3、子节点i 的 父节点在位置：(i-1)/2
 *
 * 时间复杂度：
 * 1.建立堆的过程, 从length/2 一直处理到0, 时间复杂度为O(n);
 * 2.调整堆的过程是沿着堆的父子节点进行调整, 执行次数为堆的深度, 时间复杂度为O(logn)
 * 3.堆排序的过程由n次第2步完成, 时间复杂度为O(nlogn)
 * 空间复杂度：O(1)
 * 不稳定
 */
public class HeapSort {
    /**
     * 调整并再次建堆
     */
    public static void heapSort(int[] arr){
        for (int i = arr.length-1; i > 0; i--) {//i 取值为 1~n-1
            //建堆，i为待排数组的长度-1（长度是变化的，因为每一次建堆都会选择出一个最大元素出堆（出堆是放到数组末尾，不再参与排序））
            maxHeapify(arr, i);
            //输出堆顶
            //System.out.print(arr[0] + " ");
            //把堆顶元素与堆最后一个元素交换位置（堆顶是最大元素，此处相当于把最大元素放到数组末尾），循环重新建堆
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
        }
    }

    /**
     * 将数组堆化
     * 首先，数组长度为n，所以数组最后一个元素的序号为(n-1)，因为序号是从0开始的
     * i = (n-1)/2，则最后一个节点(n-1)是第i个节点的孩子。则从以下网上的第一个非叶子节点i 开始即可。无需从最后一个叶子节点开始。
     * 叶子节点可以看作已符合堆要求的节点，根节点就是它自己且自己以下值为最大。
     * @param arr 数组
     * @param n 数组大小（实际大小-1）
     */
    private static void maxHeapify(int[] arr, int n) {
        //定义最大孩子
        int maxChild;
        //i=(n-1)/2，从第一个非叶子节点开始即可
        for (int i = (n-1)/2; i >=0 ; i--) {
            //1.先比较孩子
            //用maxChild记录最大孩子，默认是左孩子(2*i+1)最大
            maxChild = 2*i + 1;
            if(maxChild!=n && arr[maxChild]<arr[maxChild+1]){
                //若右孩子存在，且右孩子更大
                maxChild++;//maxChild = (2*i+1)
            }

            //2.再比较父亲和孩子
            //如果孩子比父节点大，则交换
            if(arr[i] < arr[maxChild]){
                int temp = arr[i];
                arr[i] = arr[maxChild];
                arr[maxChild] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int arr[]={8,9,1,7,2,3,5,4,6,0};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
