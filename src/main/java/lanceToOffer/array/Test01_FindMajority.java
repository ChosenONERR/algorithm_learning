package lanceToOffer.array;

import java.util.*;

/**
 * 两道题
 */

public class Test01_FindMajority {
    /**
     * 题目1：找出数组中出现次数大于数组长度一半的数
     * 时间复杂的 O(N)，额外空间复杂度 O(1)
     *
     * 分析：（两个角度：删除 和 最多多少种这样的数）
     * 1.在数组中一次同时删掉两个不同的元素，如果存在某个数出现次数大于数组长度的一半，那么即使每次都删，最后也会至少剩下 1 个
     * 2.一个数组中出现次数大于数组长度一半的数显然最多只有一种。（因为不可能存在两种数都超过一半）
     *
     * 代码思想：阵地攻守的思想
     * 遍历数组：
     * 1.第一个数字作为第一个士兵即候选人 candiate，守阵地；
     * 2.遍历过程中，遇到相同元素，count++；遇到不相同元素，即为敌人，同归于尽，count--；（互删）
     * 3.当遇到 count 为 0 的情况，又以新的 i 值作为守阵地的士兵，继续重复上述步骤
     * 4.到最后还留在阵地上的士兵，有可能是出现次数超过数组长度一半的元素。需要再遍历一次，确定这个士兵的个数看是否大于数组一半即可
     *（第4步实际上是防止出现这样的一个情况：当数组内的元素都各不相同且数组长度为单数的时候，避免误以为数组最后一个元素就是答案）
     */
    public static int findOverHalfNum(int[] arr){

        //代码鲁棒性体现1
        if(arr==null || arr.length<1){
            return Integer.MIN_VALUE;
        }

        //候选人初始值为0（注：并不影响数组元素为0的情况，因为即使当candidate=0，count=0时，表示无候选人）
        int candiate = 0;
        int count = 0;

        for (int i = 0; i < arr.length; i++) {

            if(candiate == 0){
                // 还没有候选人，则当前值直接设置为候选人
                candiate = arr[i];
                count++;
            }else if(arr[i] == candiate){
                // 有候选人，并且当前数和候选人一致：候选人+1
                count++;
            }else {
                // 有候选人，但是当前数和候选人不一致：互删
                count--;

                //假如当前候选人数目为0，则把候选人重置为0
                if(count == 0){
                    candiate = 0;
                }
            }
        }

        // 最后剩下的候选人是可能出现次数超过数组总长度一半的数，但是还需要校验下
        if(count != 0){
            count = 0;
            for (int i = 0; i < arr.length; i++) {
                if(arr[i] == candiate){
                    count++;
                }
            }
        }
        if(count > arr.length/2){
            return candiate;
        }

        return Integer.MIN_VALUE;
    }

    /**
     * 题目2：找出数组中出现次数大于数组长度 N/K 的数
     * （注：N：数组元素个数；K：数组中不同元素的个数；例：数组{1，2，3，4，5，5，5，5}，此时 N=8，K=5）
     *
     * 分析：（两个角度：删除 和 最多多少种这样的数）
     * 1.若数组内有N个数，且有 K 种不同的数，那么要求每次删除K个不同的数时，最多能删除 N/K 次，并且个数大于 N/K 的数一定会剩下来，除非没有个数大于 N/K 的数。
     * 2.若数组内有N个数，且有 K 种不同的数，那么个数大于 N/K 的数至多有 K-1 个（类比上一题：至多只有1种数的个数大于数组长度的一半）
     */
    public static ArrayList<Integer> findOverKTimes(int[] arr, int k){
        if(arr==null || arr.length<k || k<1){
            return null;
        }

        // 候选表：用HashMap记录最多 K-1 个出现次数大于 N/K 的数的情况
        HashMap<Integer, Integer> candidates = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if(candidates.containsKey(arr[i])){
                // 当前数在候选表中,出现次数加1
                candidates.put(arr[i], candidates.get(arr[i]) + 1);
            }else if(candidates.size() < k-1){
                // 当前数不在候选表中，并且候选表还没有满时，直接插入
                candidates.put(arr[i], 1);
            }else {
                // 当前数不在候选表中，并且候选表差1个到k个（也就是当候选人数为k-1的时候），将所有候选者次数都减1，当前数也不要（因为当前数次数也为1）
                //（这一步实际上就是上面分析的：一次删除k个不同的数）
                allCandiatesDeleteOne(candidates);
            }
        }

        //结果表
        ArrayList<Integer> list = new ArrayList<>();

        if(!candidates.isEmpty()){
            /**
             * 经历了上面的for循环之后，map集合的元素都是幸存下来的候选人（没有被删除），是存在符合题目条件可能性的，
             * 需要把这些候选人和原来的数组元素一一对比一次，统计候选人对应个数：getRealCandiates方法
             */
            HashMap<Integer, Integer> realCandidates = getRealCandidates(arr, candidates);

            for(Map.Entry<Integer, Integer> set : realCandidates.entrySet()){
                //判断是否符合题目条件
                if(set.getValue() > arr.length / k){
                    list.add(set.getKey());
                }
            }
        }
        return list;
    }

    private static void allCandiatesDeleteOne(HashMap<Integer, Integer> candiates) {

        List<Integer> removeList = new LinkedList<Integer>();//删除键值形成的链表
        for(Map.Entry<Integer, Integer> candiate : candiates.entrySet()){
            int count = candiate.getValue();
            int key = candiate.getKey();
            if(count == 1){
                //candidates.remove(key);//不使用这种方法，而是先用list记录要被移除的key，等下统一remove。否则会报错：ConcurrentModificationException
                // 当前候选人出现的次数仅为1，则直接从候选表中删除
                removeList.add(key);
            }else {
                // 出现次数减1
                candiates.put(key, count-1);
            }
        }

        //统一删除
        for (Integer removeKey : removeList){
            candiates.remove(removeKey);
        }
    }

    public static HashMap<Integer, Integer> getRealCandidates(int[] arr, HashMap<Integer, Integer> candiates){
        HashMap<Integer, Integer> res = new HashMap<>();
        for(Map.Entry<Integer, Integer> set : candiates.entrySet()){
            int candidate = set.getKey();
            int count = 0;

            for (int i = 0; i < arr.length; i++) {
                if(candidate == arr[i]){
                    count++;
                }
            }
            res.put(candidate, count);
        }
        return res;
    }


    public static void main(String[] args) {
        int[] arr1 = {1,1,1,2};
        System.out.println(findOverHalfNum(arr1));

        int[] arr2 = {1,2,3,4, 1,2,3,4, 1,2,3,4, 1,2,3,4, 1};
        System.out.println(findOverKTimes(arr2, 4));
    }
}
