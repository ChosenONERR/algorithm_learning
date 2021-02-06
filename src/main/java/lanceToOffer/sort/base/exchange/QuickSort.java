package lanceToOffer.sort.base.exchange;

import java.util.Arrays;
import java.util.Stack;

/**
 * 快速排序
 *
 * 快速排序的运行时间与划分是否对称有关。
 * 最坏情况下，每次划分过程产生两个区域分别包含n-1个元素和1个元素，其时间复杂度会达到O(n^2)。
 * 在最好的情况下，每次划分所取的基准都恰好是中值，即每次划分都产生两个大小为n/2的区域。此时，快排的时间复杂度为O(nlogn)。
 * 所以基准的选择对快排而言至关重要。快排中基准的选择方式有以下三种：
 * 1、固定基准
 *    如果数组元素是随机的，划分过程不产生极端情况，那么程序的运行时间不会有太大的波动。如果数组元素已经基本有序时，
 *    此时的划分就容易产生最坏的情况，即快速排序变成冒泡排序，时间复杂度为O(n^2)。
 * 2、三位取中
 *    引入背景：
 *    快速排序是通常被认为在同数量级（O(nlog2n)）的排序方法中平均性能最好的。但若初始序列按关键码有序或基本有序时，
 *    快排序反而蜕化为冒泡排序。为改进之，通常以“三者取中法”来选取基准记录，
 *    大致思想：
 *    在快排的过程中，每一次我们要取一个元素作为枢纽值，以这个数字来将序列划分为两部分。在此我们采用三数取中法，
 *    也就是取左端、中间、右端三个数，然后进行排序，将中间数作为枢纽值。
 *    这种算法对于有序的表仍有固定基准值那样的性能
 * 3、随机
 *
 *
 * 快排的优化：
 * 1、序列长度达到一定大小时，使用插入排序。当快排达到一定深度后，划分的区间很小时，再使用快排的效率不高，而相关于
 *    小数组，插入排序的速度会更快。当待排序列的长度达到一定数值后，可以使用插入排序。
 * 2、尾递归优化
 * 3、聚集元素
 * 4、多线程处理快排
 *    分治法的基本思想是将一个规模为n的问题分解为k个规模较小的子问题，这些子问题互相独立且与原问题相同。
 *    求解这些子问题，然后将各子问题的解合并，从而得到的原问题的解。由此，在处理快排的时候，
 *    可以使用多线程提高排序的效率。
 *
 *
 * 复杂度：
 * 平均时间复杂度   O(nlog₂n)
 * 最好情况    	O(nlog₂n)
 * 最坏情况      	O(n²)
 * 空间复杂度   O(1)（原地分区递归版）
 */
public class QuickSort {
    /**
     * 递归版的快速排序（固定基准）：通过把基准插入到合适的位置来实现分治，并递归地对分治后的两个划分继续快排。
     */
    public static void quickSort01(int[] arr, int low, int high){
        //代码鲁棒性
        if(arr==null || arr.length==0 || low<0 || high>=arr.length){
            return;
        }

        //序列长度达到一定大小时，使用插入排序
        if(high-low+1<10){
            //调用插入排序
            //InsertSort(arr, low, high);
        }

        //如果low>=high，表示排序已完成
        if(low >= high){
            return;
        }

        int left = low, right = high;

        //保存基准值（一般以low作为基准值）
        int temp = arr[left];

        while(left<right){
            while (left < right && temp <= arr[right]){
                right--;
            }
            if(left < right){
                arr[left] = arr[right];
                left++;
            }
            while(left < right && temp >= arr[left]){
                left++;
            }
            if(left < right){
                arr[right] = arr[left];
                right--;
            }
        }
        // 每一次划分都能确定某个元素的最终位置
        arr[left] = temp;

        //递归调用
        quickSort01(arr, low, left-1);
        quickSort01(arr, left+1, high);
    }

    /**
     * 非递归版快速排序
     * 因为递归的本质是栈，所以我们非递归实现的过程中，可以借助栈来保存中间变量就可以实现非递归了。
     * 在这里中间变量也就是通过Partition函数划分区间之后分成左右两部分的【首尾指针】，只需要保存这两部分的首尾指针即可
     */
    //A 主方法
    public static void quickSort02(int[] arr){
        //鲁棒性
        if(arr==null || arr.length<1){
            return;
        }
        //创建栈
        Stack<Integer> stack = new Stack<>();

        //初始状态的左右指针入栈
        stack.push(0);//左
        stack.push(arr.length-1);//右
        while(!stack.isEmpty()){
            //出栈进行划分
            int right = stack.pop();
            int left = stack.pop();
            int midIndex = partiton(arr, left, right);

            //用栈保存中间变量
            if(midIndex-1>left){//下一个区间的左右指针还未相遇
                stack.push(left);
                stack.push(midIndex-1);
            }
            if(midIndex+1<right){//下一个区间的左右指针还未相遇
                stack.push(midIndex+1);
                stack.push(right);
            }
        }
    }
    //B 划分算法
    public static int partiton(int[] arr, int low, int high){
        //代码鲁棒性
        if(arr==null || arr.length==0 || low<0 || high>=arr.length){
            return Integer.MIN_VALUE;
        }

        //如果low>=high，表示排序已完成
        if(low >= high){
            return Integer.MIN_VALUE;
        }
        int left = low;
        int right = high;
        //保存基准值
        int temp = arr[left];

        while(left < right){
            while (left<right && temp <= arr[right]){
                right--;
            }
            if(left < right){
                arr[left] = arr[right];
                left++;
            }
            while (left<right && temp >= arr[left]){
                left++;
            }
            if(left < right){
                arr[right] = arr[left];
                right--;
            }
        }
        arr[left] = temp;
        return left;//返回基准值的位置
    }


    /**
     * 三位取中法 A主方法
     * @param arr 待排数组
     * @param low 左指针
     * @param high 右指针
     */
    public static void threeMidSort(int[] arr, int low, int high){
        if(low < high){
            //使用 三位取中法 获取基准值的位置，并把基准值放到（high-1）的位置
            dealPivot(arr, low, high);
            //获得基准值
            int pivot = arr[high - 1];
            //左指针i
            int left = low;
            //右指针right
            int right = high-1;
           while (true){
               //左指针寻找比基准值大的数
               while(arr[++left] < pivot){//（left最大增加到right-1（因为arr[high-1] = pivot），所以不用对left进行限制）
                   //什么都不做，找到比基准值大的数，指针left自然就停止下来了
               }
               //右指针寻找比基准值小的数
               while (arr[--right] > pivot && right>low){//（right最小只能减少到low+1，）
                   //什么都不做,找到比基准值小的数，指针right自然就停止下来了
               }
               //如果左指针和右指针还没相遇，那么交换他们的位置
               if(left<right){
                   swap(arr, left, right);
               }else {
                   //左右指针相遇则中断循环
                   break;
               }
           }
            //如果左指针和右指针相遇的位置(i=j)在基准值的左边（其实i一定在左边），那么交换：相遇位置和基准值的位置
            if(left<high){
                swap(arr, left, high-1);
            }
            //分治法递归调用
            threeMidSort(arr, low, left-1);
            threeMidSort(arr, left+1, high);
        }
    }
    /**
     * 三位取中法 B取基准值
     */
    public static void dealPivot(int[] arr, int low, int high){
        //获取mid
        int mid = (low + high)/2;
        //比较low和high
        if(arr[low] > arr[high]){
            swap(arr, low, high);
        }
        //比较low和mid
        if(arr[low] > arr[mid]){
            swap(arr, low, mid);
        }
        //比较high和mid
        if(arr[mid] > arr[high]){
            swap(arr, mid, high);
        }

        //此刻基准值在mid位置中了，把基准值放到right-1位置上
        swap(arr, mid, high-1);
    }

    /**
     * 三位取中法 C交换方法
     */
    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        int[] arr={8,9,1,7,2,3,5,4,6,0};
        //quickSort01(arr, 0, 9);
        //quickSort02(arr);
//        threeMidSort(arr, 0, 9);
        quickSort01(arr,0, 9);
        System.out.println(Arrays.toString(arr));
    }
}
