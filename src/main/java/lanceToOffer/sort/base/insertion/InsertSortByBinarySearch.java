package lanceToOffer.sort.base.insertion;

import java.util.Arrays;

/**
 * 折半插入排序（也就二分查找插入排序）
 *
 * 二分查找插入排序的原理：是直接插入排序的一个优化，区别是：在有序区中查找新元素插入位置时，
 * 为了【减少元素比较次数】提高效率，采用二分查找算法进行插入位置的确定，而元素的移动次数实际上是没有变化的
 *
 * 设数组为a[0…n]。
 * 1.将原序列分成有序区和无序区。a[0…i-1]为有序区，a[i…n] 为无序区。（i从1开始）
 * 2.从无序区中取出第一个元素，即a[i]，使用二分查找算法在有序区中查找要插入的位置索引j。
 * 3.将a[j]到a[i-1]的元素后移，并将a[i]赋值给a[j]。
 * 4.重复步骤2~3，直到无序区元素为0
 *
 * 时间复杂度仍然是O(n^2)，因为虽然比较次数因为折半查找变成了O（log2n），但是移动次数没有变化，仍然是O（n2）
 * 该二分查找优化了比较次数，使比较次数不再依赖排序表的初始状态，使其仅仅依赖问题的规模n（排序表的长度），而移动次数
 * 仍然于排序表的初始状态有关
 */
public class InsertSortByBinarySearch {

    public static void insertBy2(int[] arr){
        //代码鲁棒性
        if(arr==null || arr.length<=1){
            return;
        }

        // 将arr分成有序区和无序区，初始有序区有一个元素,就是第一个
        // i起始为0，是数组的下标
        for (int i = 0; i < arr.length-1; i++) {//趟数，共数组长度-1趟（第一个元素不用排序），注意，i<arr.length-1，一定要减1！！否则会数组越界错（因为下一行代码arr[i+1]）

            //对arr[i+1]元素进行插入排序（先把带插入的数备份到num中）
            int num = arr[i+1];//i是划分有序区和无序区的界限，0到i 为有序区；i+1到(length-1)为无序区

            //调用二叉查找算法进行比较，而不是一个一个地比较，查找到位置index用于放元素arr[i]，
            int index = binarySearch(arr, i, num);

            //找到位置index后，再把index到i的这些位置的元素全部往后挪动一位
            for(int j=i; j>=index; j--){
                arr[j+1] = arr[j];//往后挪一位
            }
            arr[index] = num;
        }
    }

    /**
     * 折半（二分）查找：只能针对有序数列进行查找
     *
     * 返回值：key最终落到数组的下标index
     */
    public static int binarySearch(int[] arr, int maxIndex, int key){
        int low = 0;//左边界
        int high = maxIndex;//右边界
        int mid;

        //以下被注释的代码是原本折半查找算法查找目标key的代码

        //防越界
//        if(key < arr[begin] || key > arr[end] || begin>end){
//            return Integer.MIN_VALUE;
//        }

//        while(begin <= end){
//            ////计算mid,让mid和temp比较，决定key是落在左半区或右半区
//            mid = (begin + end) / 2;
//            if(arr[mid] == key){
//                System.out.println(mid);
//                System.out.println(arr[mid]);
//                return mid;
//            }else if(arr[mid] > key){
//                end = mid - 1;
//            }else {
//                begin = mid + 1;
//            }
//        }
//        //数组中不存在待查元素
//        return Integer.MIN_VALUE;

        //利用折半查找算法找到一个位置high
        while (low <= high){//折半查找过程中，begin下标和high下标逐渐拉近
            //计算mid,让arr[mid]和temp比较，决定temp是落在左半区或右半区
            mid = (low + high) / 2;
            if(arr[mid] > key){
                //落在左半区,更改右边界
                high = mid - 1;
            }else {
                //落在右半区,更改左边界
                low = mid + 1;
            }
        }
        return high + 1;
    }

    /**
     * 另一个版本，没有封装二分查找，直接使用二分查找
     */
    public static void binaryInsertSort(int a[]){	//用二分优化的插入排序
        //依次把每个元素拿来插入到之前的有序子序列(从第二个开始，到最后就行)
        for(int i=0;i<a.length-1;i++){//趟数，每趟插入第i+1个元素---待插入的数
            int temp=a[i+1];//先把待插入的数备份到temp中

            //利用二分算法找到一个位置high
            int low=0;//左边界
            int high=i;//右边界
            int mid;//中间位置
            while(low<=high){
                //计算mid,让mid和temp比较，决定temp是落在左半区或右半区
                mid=(low+high)/2;
                if(a[mid]>temp){//落在左半区,更改右边界
                    high=mid-1;
                }else{//落在右半区,更改左边界
                    low=mid+1;
                }
            }
            //找到了插入的位置index
            int index = high+1;

            //经过上面一段，找到temp将要放置的位置high+1
            for(int j=i;j>=index;j--){//把index到i这些元素全部后挪一个位置
                a[j+1]=a[j];
            }
            //让temp坐在high+1位置
            a[index]=temp;
        }
    }

    public static void main(String[] args) {
        int arr[]={8,9,1,7,2,3,5,4,6,0};
        //insertBy2(arr);
        binaryInsertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
