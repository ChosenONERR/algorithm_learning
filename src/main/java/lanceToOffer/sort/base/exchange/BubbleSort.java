package lanceToOffer.sort.base.exchange;

/**
 * 冒泡排序
 * 思想：相邻元素比较，最值的往后放（比较并交换）。（就像水里的泡泡往上升一样，越往上的泡泡越大）
 *
 * 代码思路：
 *  （1）有n个元素比较，就要进行(n-1)轮比较
 *  （2）第一轮有n个元素需要比较，所以比较次数为n-1次，其后每经过一轮，都能确定最值的最终位置(最大的那个数已经放到
 *      了最后，无需再比较)，所以往后每一轮的比较次数-1（也就是第i轮则比较次数-i）
 *
 * 复杂度：
 * 平均时间复杂度：O(n²)
 * 最好情况：O(n²)
 * 最坏情况：O(n²)
 * 空间复杂度：O(1)
 *
 * 冒泡排序是最容易实现的排序, 最坏的情况是每次都需要交换, 共需遍历并交换将近n²/2次, 时间复杂度为O(n²).
 * 最佳的情况是内循环遍历一次后发现排序是对的, 因此退出循环, 时间复杂度为O(n). 平均来讲, 时间复杂度为O(n²).
 * 由于冒泡排序中只有缓存的temp变量需要内存空间, 因此空间复杂度为常量O(1).
 */
public class BubbleSort {
    public static int[] bubbleSort(int[] arr){
        if(arr==null || arr.length<2){ // 代码鲁棒性
            return arr;
        }
        //设置一个标志，表明该待排序列是否已经有序。并置标志初始值为true，表示待排序列默认有序
        boolean flag = true;
        //外层循环控制比较的轮数：次数为数组长度
        for (int i = 0; i < arr.length - 1; i++) {
            //内层循环控制每一轮比较的次数，初始比较次数为（数组长度-1），下一轮比上一轮的比较次数-1.
            for(int j=0; j<arr.length - 1 - i; j++){//j表示数组的下标
                //相邻比较，如果前一个比后一个大，则交换，最终最大的元素移动到最终位置
                if(arr[j] > arr[j+1]){ //只在大于的时候交换，等于的时候不交换，这样保证排序稳定性
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    //代码运行到这里，说明发生了元素交换，待排序列并不是有序的
                    flag = false;
                }
            }
            if(flag == true){
                //本趟排序没有发生元素交换，说明序列已经有序，直接返回
                return arr;
            }
        }
        return arr;
    }
    public static void main(String[] args) {
        int arr[]={8,9,1,7,2,3,5,4,6,0};
        int[] newArr = bubbleSort(arr);
        for (int i : newArr){
            System.out.print(i + " ");
        }
    }
}
