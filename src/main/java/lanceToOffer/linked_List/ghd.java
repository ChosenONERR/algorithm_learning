package lanceToOffer.linked_List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ghd {
    public static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val){
            this.val = val;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k){
        // 判断链表中元素的个数
        int count = 0;
        ListNode current = head;
        while(current != null){
            count++;
            current = current.next;
            // k - 1是因为next到了k个
            if(count == k - 1){
                break;
            }
        }
        // 如果当前temp的下一个节点为空，说明个数不够k，则直接返回
        if(current == null){
            return head;
        }

        int num = k;       // 保存翻转的次数
        ListNode tempHead = head;  // 保存递归后连接的头结点
        // 翻转链表
        ListNode pre = null;
        while(num > 0){
            // 在head的next指针断开前，先保存它的next指针指向的对象
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
            num--;
        }
        // current.next为下一次翻转的头结点
        current = current.next;
        tempHead.next = reverseKGroup(current, k);
        return pre;
    }

    /**
     * 找到数组中出现次数大于 N/K 的数：最多有 K - 1 个
     */
    public static ArrayList<Integer> findOverKTimes(int[] arr, int k){
        if(arr == null || arr.length < k){
            return null;
        }

        // 候选表：用HashMap记录最多K-1个出现次数大于N/K的数的情况
        HashMap<Integer, Integer> candiates = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            // 当前数在候选表中,出现次数加1
            if(candiates.containsKey(arr[i])){
                candiates.put(arr[i], candiates.get(arr[i]) + 1);
            }else if(candiates.size() < k - 1){
                // 当前数不在候选表中，并且候选表还没有满时，直接插入
                candiates.put(arr[i], 1);
            }else{
                // 当前数不在候选表中，并且候选表已经满了，则将所有候选者次数都减1，当前数也不要（因为当前数次数也为1）
                allCandiatesDeleteOne(candiates);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        // 还有候选人
        if(!candiates.isEmpty()){
            // 得到候选人的真实个数
            HashMap<Integer, Integer> reals = getReals(arr, candiates);
            for(Map.Entry<Integer, Integer> set : reals.entrySet()){
                if(set.getValue() > arr.length / k){
                    list.add(set.getKey());
                }
            }
        }
        return list;
    }

    public static void allCandiatesDeleteOne(HashMap<Integer, Integer> candiates){
        for(Map.Entry<Integer, Integer> candiate : candiates.entrySet()){
            Integer count = candiate.getValue();
            if(count == 1){
                // 当前候选人出现的次数仅为1，则直接从候选表中删除
                candiates.remove(candiate.getKey());
            }else{
                // 出现次数减1
                candiates.put(candiate.getKey(), --count);
            }
        }
    }

    public static HashMap<Integer, Integer> getReals(int[] arr, HashMap<Integer, Integer> candiates){
        HashMap<Integer, Integer> res = new HashMap<>();
        for(Map.Entry<Integer, Integer> set : candiates.entrySet()){
            int candiate = set.getKey();
            int count = 0;
            for(int i = 0; i < arr.length; i++){
                if(arr[i] == candiate){
                    count++;
                }
            }
            res.put(candiate, count);
        }
        return res;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);

        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=node6;

        ListNode nodeList =reverseKGroup(node1, 2);

        System.out.println(nodeList.val);
        System.out.println(nodeList.next.val);
        System.out.println(nodeList.next.next.val);
        System.out.println(nodeList.next.next.next.val);
        System.out.println(nodeList.next.next.next.next.val);
        System.out.println(nodeList.next.next.next.next.next.val);


        int[] arr2 = {1,2,3,4, 1,2,3,4, 1,2,3,4, 1,2,3,4};
        System.out.println(findOverKTimes(arr2, 4));
    }
}
