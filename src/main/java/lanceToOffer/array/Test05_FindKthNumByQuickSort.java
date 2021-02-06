package lanceToOffer.array;

/**
 * 求一个数组中的第 k 小 / 大的数
 *
 * 决定小/ 大 的是划分算法，下面的例子均是第k大
 */
public class Test05_FindKthNumByQuickSort {

    /**
     * 基于 BFPRT算法 实现
     *
     * 以后补
     */

    /**
     * 基于 快速排算法 实现
     * @param arr 待排序数组
     * @param left 左指针
     * @param right 右指针
     * @param k 第 k 大
     */
    public int findKth(int[] arr, int left, int right, int k){

        if(arr==null || arr.length<=0){
            System.out.println("输入数据不正确");
            return Integer.MIN_VALUE;
        }
        //首先调用分区算法
        int mid =partition(arr, left, right);

        // 下标是从0开始的，所以k要减1

        if(k-1 == mid){
            //如果第k处刚好在返回的指针处
            return arr[mid];
        }else if(k-1 < mid){
            //如果第k处理在返回的指针的左边，则对左边部分进行递归分区
            return findKth(arr, left, mid-1, k);
        }else{
            //如果第k处理在返回的指针的右边，则对右半部分进行递归分区
            return findKth(arr, mid+1, right, k);
        }
    }

    /**
     * 划分算法（从大到小）
     */
    public static int partition(int[] arr, int left, int right){

        //指定（或者说保存）基准数（一般一开始用左指针指向基准数）
        int temp = arr[left];

        //左指针和右指针相遇前，执行循环
        while(left<right){
            //右指针从右边开始扫面，寻找比基准数要大的数
            while(left<right && temp>=arr[right]){
                right--;
            }
            if(left<right){
                //找到后，与基准数调换位置，变成了右指针指向基准数
                arr[left] = arr[right];
                left++;
            }

            //左指针从左边开始扫描，寻找比基准数字小的是数字
            while(left<right && temp<=arr[left]){
                left++;
            }
            if(left<right){
                //找到后，与基准数调换位置，重新变成了左指针指向基准数
                arr[right] = arr[left];
                right--;
            }
        }

        //当左右指针相遇（left = right），则while循环结束
        //指针位置即为基准数排序的最终位置
        arr[left] = temp;
        //返回指针
        return left;
    }

    /**
     * 补一补快速排序
     */
    public static int[] quickSort(int arr[], int low, int high){
        //先调用划分算法
        int mid = partition(arr, low, high);

        //递归调用
        quickSort(arr, low, mid-1);
        quickSort(arr, mid+1, high);

        return arr;
    }
}
