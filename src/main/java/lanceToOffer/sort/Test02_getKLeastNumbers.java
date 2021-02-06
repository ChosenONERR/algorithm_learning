package lanceToOffer.sort;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 题目：输入n个整数，找出其中最小的 k 个数。例如输入 4, 5, 1, 6, 2, 7, 3, 8 这 8 个数字，
 * 则最小的 4 个数字是1, 2, 3, 4。
 */
public class Test02_getKLeastNumbers {

    /**
     * 最普通解法：采用普通的排序算法，排序之后位于最前面的 k 个数就是最小的 k 个数。这种思路的时间复杂度是O(nlogn），
     * 面试官会提示我们还有更快的算法。
     */

    /**
     * 解法一：采用堆排序，时间复杂度为 O(nlogk) 的算法，特别适合处理海量数据。
     * 我们可以先创建一个大小为 k 的数据容器来存储最小的 k 个数字，接下来我们每次从输入的n个整数中读入一个数。
     * 如果容器中已有数字少于k个，则直接把这次读入的整数放入容器中；如果容器中已有k个数字了，也就是容器已满，
     * 此时我们不能再插入新的数字了而只能替换已有的数字。找出这已有的 k 个数中的最大值，
     * 然后拿这次待插入的整数和最大值进行比较。如果待插入的值比当前已有的最小值小，则用这个数替换当前已有的最大值；
     * 如果待插入的值比当前已有的最大值还大，那么这个数不可能是最小的 k 个整数之一，于是我们可以抛弃这个整数。
     *
     * 因此当容器满了之后，我们要做3件事：
     * 一是：在k个整数中找到最大数；
     * 二是：有可能在这个容器中删除最大数；
     * 三是：有可能要插入一个新的数字。
     *
     * 如果用一个二叉树来实现这个容器，那么我们能在O(logk）时间内实现这三步操作。因此对于n个输入的数字而言，
     * 总的时间效率是O(nlogk).
     *
     * 我们可以选择用不同的二叉树来实现这个数据容器。由于每次都需要找到 k 个整数中的最大数字，我们很容易想到用最大堆。
     * 在最大堆中，根节点的值总是大于它的子树中的任意结点的值。于是我们每次可以在O(1）得到已有的 k 个数字中的最大值，
     * 但需要 O(logk) 时间完成删除及插入操作。
     *
     * 虽然这种方法要比第二种慢一点，但是有两个明显的优点：
     * 1、没有修改输入数据的顺序；
     * 2、适合海量数据的输入。假设题目是从海量数据中查找出最小的 k 个数字，由于内存的大小是有限的，
     * 有可能不能把这些海量数据一次性全部载入内存。这个时候，我们可以从辅助空间（比如：硬盘）中每次读入一个数字，
     * 再判断是否要放入到容器中。
     */
    public ArrayList<Integer> getLeastNumbers1(int[] arr, int k){
        // 将结果result封装到ArrayList集合中
        ArrayList<Integer> result = new ArrayList<Integer>();
        int len = arr.length;
        if(k > len || k == 0){
            return result;
        }

        // 用优先级队列来实现最大堆：最大堆在堆顶元素是堆中最大值，k为堆的大小
//        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>(){
//
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        });

        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, (o1, o2) -> o1 - o2);

        for (int i = 0; i < len; i++) {
            if(maxHeap.size() != k){
                // 如果堆中的元素小于k，则直接将当前元素插入
                maxHeap.offer(arr[i]);
            }else if(maxHeap.peek() > arr[i]){
                // 当堆中元素的个数等于k && 堆顶元素大于输入的元素值时
                // 将堆顶元素删除，将新输入的元素插入
                Integer temp = maxHeap.poll();
                temp = null;   // 释放资源
                maxHeap.offer(arr[i]);
            }
        }

        // 将最大堆中的k个元素加入到result结果集合中
        for(Integer integer : maxHeap){
            result.add(integer);
        }
        return result;

    }

    /**
     * 解法二：时间复杂度为O(n)的算法，只有当我们可以修改输入的数组时可用
     *
     * 我们同样可以基于 Partition 函数来解决这个问题。如果基于数组的第 k 个数字来调整，
     * 使得比第 k 个数字小的所有数字都位于数组的左边，比第 k 个数字大的所有数字都位于数组的右边。这样调整之后，
     * 位于数组中左边的 k 个数字就是最小的 k 个数字。
     */

    public ArrayList<Integer> getLeastNumbers2(int[] input, int k){
        ArrayList<Integer> result = new ArrayList<Integer>();

        if(input.length == 0 || k == 0 || k > input.length){
            return result;
        }

        int start = 0;
        int end = input.length - 1;
        int index = partition(input, start, end);
        while(index != k - 1){
            if(index > k -1){
                end = index - 1;
                index = partition(input, start, end);
            }else{
                start = index + 1;
                index = partition(input, start, end);
            }
        }

        for (int i = 0; i < k; i++) {
            result.add(input[i]);
        }
        return result;
    }

    private int partition(int[] arr, int left, int right) {
        int temp = arr[left];
        if(left > right){
            return 0;
        }

        while(left < right){
            while(left < right && arr[right] >= temp){
                right--;
            }
            arr[left] = arr[right];

            while(left < right && arr[left] < temp){
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;
        return temp;
    }
}
