package lanceToOffer.sort.base.others;

import java.util.Arrays;

/**
 * 基数排序
 * 1、简介：
 * 基数排序（Radix sort）是一种非比较型整数排序算法，其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。
 * 由于整数也可以表达字符串（比如名字或日期）和特定格式的浮点数，所以基数排序也不是只能使用于整数。
 * 2、基本实现思想：
 *      将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。
 *      这样从最低位排序一直到最高位排序完成以后，数列就变成一个有序序列。
 * 基数排序按照优先从高位或低位来排序有两种实现方案：
 *    1、MSD（Most significant digital） 从最左侧高位开始进行排序。先按k1排序分组, 同一组中记录, 关键码k1相等,
 *    再对各组按k2排序分成子组, 之后, 对后面的关键码继续这样的排序分组, 直到按最次位关键码kd对各子组排序后.
 *    再将各组连接起来, 便得到一个有序序列。MSD方式适用于位数多的序列。
 *    2、LSD （Least significant digital）从最右侧低位开始进行排序。先从kd开始排序，再对kd-1进行排序，依次重复，
 *    直到对k1排序后便得到一个有序序列。LSD方式适用于位数少的序列。
 *
 * 3、代码思路：不断重复分配和收集两个过程
 * 1、分配：我们将L[i]中的元素取出，首先确定其个位上的数字，根据该数字分配到与之序号相同的桶中
 * 2、-收集：当序列中所有的元素都分配到对应的桶中，再按照顺序依次将桶中的元素收集形成新的一个待排序列L[]。
 * 然后重复这两个过程，对新形成的序列L[]重复执行分配和收集元素中的十位、百位...直到分配完该序列中的最高位，则排序结束
 */
public class RadixSort {

    public static void radixSort(int[] arr){
        //鲁棒性
        if(arr==null || arr.length<=1){
            return;
        }

        //1.获得待排数组中位数最大的元素
        //1.1获得数组中最大数
        int maxNum = 0;
        for (int i = 0; i <arr.length; i++) {
            if(maxNum < arr[i]){
                maxNum=arr[i];
            }
        }
        //1.2获得数组中最大数的位数
        int maxDigit = 1;//初始化位数：1（只要是个数字，至少都是1位的）
        while (maxNum /10 > 0){
            maxDigit++;
            maxNum /= 10;
        }

        //初始化桶空间：十个桶，表示每一位数0~9；每个桶的长度为待排数组的长度，因为极限情况是每个元素在某一位上都相同
        int[][] buckets = new int[10][arr.length];
        //被整除的基数，用来计算得到元素某一位上的数值
        int base = 10;

        //最外层for循环，是对元素的每一位进行遍历（共maxDigit位）
        for (int i = 0; i < maxDigit; i++) {
            //记录每个桶（共10个）存入元素的个数
            int[] bucketLen = new int[10];

            //【分配过程】：for循环遍历每个元素，把所有的元素分配到对应的桶中
            for (int j = 0; j < arr.length; j++) {
                //找到对应的桶
                int whichBucket = (arr[j] % base) / (base/10);
                //把元素分配到对应的桶
                buckets[whichBucket][bucketLen[whichBucket]] = arr[j];//bucketLen[whichBucket]数组的每个元素初始默认都是0
                //记录对应桶的元素个数：+1
                bucketLen[whichBucket]++;
            }

            //【收集过程】：for循环遍历每个桶（两个for循环遍历二维数组），把桶的元素捞起来，为下一轮排序做准备
            int k = 0;//此处的k表示原数组的下标
            for (int b = 0; b < buckets.length; b++) {//外层for循环，遍历所有桶，buckets.length=10个
                for (int p = 0; p < bucketLen[b]; p++) {//内层for循环，遍历每个桶中的元素
                    arr[k++] = buckets[b][p];
                }
            }
            //基数变化
            base *= 10;

            System.out.println("输出第"+ i +"趟排序得到的序列（倒数第"+ i +"位）： "+ Arrays.toString(arr));
        }
    }

    public static void main(String[] args) {
        int[] arr={845,967,14,7,2,354,5555,41,6,50};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
