package lanceToOffer.array;

import java.util.Arrays;

/**
 * 题目：调整数组顺序使奇数位于偶数前面
 */
public class Test03_recorderOddEven {

    /**
     * 解法：在扫描这个数组的时候，如果发现有偶数在奇数的前面，我们可以交换他们的数序，交换之后就符合要求
     *
     * 代码思路：
     * 我们可以维护两个指针，第一个指针初始化时指向数组的第一个数字，它只向后移动；
     * 第二个指针初始化时指向数组的最后一个数字，它只向前移动。在两个指针相遇之前，第一个指针总是位于第二个指针的前面。
     * 如果第一个指针的数字是偶数，并且第二个指针指向的数字是奇数，我们就交换两个数字。
     * 当第二个指针移动到了第一个指针前面，则表明所有的奇数都位于偶数的前面
    */
    public static int[] recorderOddEven1(int[] arr){
        //代码鲁棒性体现
        if(arr==null || arr.length<=0){
            return null;
        }

        //定义两个指针
        int left = 0;
        int right = arr.length - 1;

        while(left < right){ //保证左指针在右边指针左边
            if(left < right && (arr[left]%2) != 0){
                left++;
            }
            if(left < right && (arr[right]%2) == 0){
                right--;
            }

            if(left < right){
                //交换位置
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        return arr;
    }

    /**
     * 题目扩展1：
     * 当我们写完上述答案之后，面试官可能会考察我们对“代码扩展性”的理解。
     * 比如题目条件多样，会有 1.所有的负数在所有的非负数的前面；1.能被3整除的数都在不能被3整除的数的前面
     *
     * 代码扩展性：希望我们能够给出一个模式，在这个模式下能够很方面第把已有的解决方案扩展到同类型的问题上去
     *
     * 实则：利用了java代码封装的特性：把判断逻辑封装成一个方法，以后在方法内修改判断逻辑就可以了
     */

    //封装判断逻辑（判断数据是否为偶数）
    public static boolean isEven(int data){
        return (data%2) == 0;
    }


    /**
     * 题目扩展2：... ,并保证奇数和奇数，偶数和偶数之间的相对位置不变
     * 可以看出来，上面的代码会改变原有的奇数和奇数、偶数和偶数之间的顺序。那么如果现在不能改变这个顺序又要如何实现呢？
     */
    public static int[] recorderOddEven2(int[] arr){

        if(arr==null || arr.length<=0){
            return null;
        }

        //定义两个指针
        int i = 0, j;

        while(i < arr.length){
            //指针i找偶数
            while(i < arr.length && !isEven(arr[i])){
                //非偶数，则向右移动
                i++;
            }

            // i指向偶数时，j从i+1开始向后遍历找奇数
            j = i+1;
            //指针j找奇数
            while(j < arr.length && isEven(arr[j])){
                //非奇数，则向右移动
                j++;
            }

            if(j < arr.length){
                // 将[i,...,j-1]的元素整体后移1位，最后将找到的奇数放入i位置
                int temp = arr[j];
                for (int k = j-1; k>=i; k--){
                    arr[k+1] = arr[k];
                }
                //不可以写成++i（因为下一次是从i+1开始遍历，而本次要从数组的index=i的位置取值）
                arr[i++] = temp;
            }else {
                //当j从i+1的位置开始遍历完整个数组，仍然找不到奇数，可以结束while循环了
                break;
            }
        }
        return arr;
    }

    /**
     * 上面这种解法每次发现一个奇数在偶数后面，就需要搬移他们之间所有的元素，时间复杂度为O(n^2)，
     * 下面我们将使用一种【空间换时间】的思想，将时间复杂度降低为O(n)。
     *
     * 代码实现思路：
     * 首先统计奇数的个数，然后新建一个与原数组等长的数组，设置两个指针，奇数指针从0开始，
     * 偶数指针从奇数个数的末尾开始遍历填数
     *
     * 增加了的空间：新数组
     * 时间减少了，空间复杂度为O(n)
     */
    public static int[] recorderOddEven3(int[] arr){

        //代码鲁棒性
        if(arr==null || arr.length<=1){
            return null;
        }

        //定义两个指针
        int oddCount = 0;
        int oddBegin = 0;
        //创建一个和原数组长度相同的数组
        int[] newArr = new int[arr.length];

        //统计原数组的奇数个数
        for (int i = 0; i < arr.length; i++) {
            if(!isEven(arr[i])){
                oddCount++;
            }
        }

        //按规则填充新数组
        for (int i = 0; i < arr.length; i++) {
            if(!isEven(arr[i])){
                //若是奇数，在新数组中，从头开始放
//                newArr[oddBegin] = arr[i];
//                oddBegin++;
                newArr[oddBegin++] = arr[i];
            }else {
                //若是偶数，在新数组中，从奇数总数个数之后开始放
//                newArr[oddCount] = arr[i];
//                oddCount++;
                newArr[oddCount++] = arr[i];
            }
        }

        //把新数组赋给原数组
        for (int i = 0; i < arr.length; i++) {
            arr[i] = newArr[i];
        }
        return arr;
    }

    // 测试
    public static void main(String[] args) {

        int[] arr1= {2,4,1,3};
        int[] arr2= {2,4,1,3};
        int[] arr3= {2,4,1,3};

        int[] data1 = recorderOddEven1(arr1);
        int[] data2 = recorderOddEven2(arr2);
        int[] data3 = recorderOddEven3(arr3);
        System.out.println(Arrays.toString(data1));
        System.out.println(Arrays.toString(data2));
        System.out.println(Arrays.toString(data3));
    }
}
