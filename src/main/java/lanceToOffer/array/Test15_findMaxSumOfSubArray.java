package lanceToOffer.array;

/**
 * 题目：连续子数组的最大和
 *  例如输入的数组为｛1，-2，3，10，-4，7，2，-5 }，和最大的子数组为{3，10，-4，7，2}，因此输出为该子数组的和为18。
 *
 *  看到该题目，很多人都能想到最直观的方法，即枚举出数组的所有子数组并求出他们的和。一个长度为n的数组，总共有n(n+1)/2 个子数组。
 *  计算出所有的子数组的和，最快也需要O(n^{2})的时间。通常最直观的方法不会是最优的方法，面试官将提示我们还有更快的方法。
 *
 * 另解：
 * 我们试着从头尾逐个累加示例数组中的每个数字。初始化和为0.第一步加上第一个数字，此时和为1.接下来第二步加上数字-2，
 * 和就变成了-1.第三步加上数字3.我们注意到由于此前累计的和为-1，小于0，那如果用-1加3，得到的和为2，比3本身还小。
 * 也就是说从第一个数字开始的子数组的和会小于从第三个数字开始的子数组的和。因此我们不用考虑从第一个子数组，之前累计的和也被抛弃。
 *
 * 所以我们抛弃前面的和 -1，我们从第三个数字重新开始累加，此时得到的和为3.接下来第四步加10，得到和为13.第五步加上为9.我们发现-4是一个负数，
 * 因此累加-4之后得到的和比原来的还小。因此我们要把之前得到的和13保存下来，它有可能是最大的子数组的和。第六步加上数字7，
 * 9加7的结果是16，此时和比之前最大的和13还要大，把最大的子数组的和由13更新为16.第七步加上2，累加得到的和为18，
 * 同时我们也要更新最大子数组的和。第八步加上最后一个数字-5，由于得到结果为13，小于此前得到的最大和18，
 * 因此最终最大的子数组的和为18，对应的子数组是｛3，10，-4，7，2｝
 */
public class Test15_findMaxSumOfSubArray {

    /**
     * 动态规划解法，以后补
     */

    public static int findMaxSumOfSubArray(int[] arr){
        //代码鲁棒性
        if(arr==null || arr.length<0){
            return Integer.MIN_VALUE;
        }

        //定义两个变量
        //定义当前加和（currentSum）
        int curSum = 0;
        //定义最大加和
        int biggestSum = 0;

        for (int i = 0; i < arr.length; i++) {
            if(curSum<=0){
                //// 如果curSum为0或负数，则将surSum更新为array[i]
                // （代码体现的就是前述的思想，当加上当前数之后，发现比当前数还小，则抛弃掉前面的加和，重新从当前数进行累加）
                curSum = arr[i];
            }else {
                // 如果curSum为正数，则将surSum更新为array[i]
                curSum += arr[i];
            }

            if(curSum > biggestSum){
                //更新最大值
                biggestSum = curSum;
            }
        }

        return biggestSum;
    }
}
