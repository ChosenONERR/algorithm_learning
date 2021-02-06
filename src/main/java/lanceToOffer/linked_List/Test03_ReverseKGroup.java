package lanceToOffer.linked_List;

/**
 * 题目：K 个一组翻转链表:给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序
 *
 * 示例 :
 * 给定这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * 说明 :
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 思路：若是单纯地翻转链表：一个 while 循环就解决了；但是题目并不是单纯的。
 *
 * 代码具体思路：
 * 1.链表分区为已翻转部分+待翻转部分+未翻转部分
 * 2.每次翻转前，要确定翻转链表的范围，这个必须通过 k 此循环来确定
 * 3.需记录翻转链表前驱和后继，方便翻转完成后把已翻转部分和未翻转部分连接起来
 * 4.初始需要两个变量 pre 和 end，pre 代表待翻转链表的前驱，end 代表待翻转链表的末尾
 * 5.经过k此循环，end 到达末尾，记录待翻转链表的后继 next = end.next
 * 6.翻转链表，然后将三部分链表连接起来，然后重置 pre 和 end 指针，然后进入下一次循环
 * 7.特殊情况，当翻转部分长度不足 k 时，在定位 end 完成后，end==null，已经到达末尾，说明题目已完成，直接返回即可
 * 时间复杂度为 O(n∗K)O(n*K)O(n∗K) 最好的情况为 O(n)O(n)O(n) 最差的情况未 O(n2)O(n^2)O(n2)
 * 空间复杂度为 O(1)O(1)O(1) 除了几个必须的节点指针外，我们并没有占用其他空间
 */
public class Test03_ReverseKGroup {

    static class ListNode {
        public int data;
        public ListNode next;

        public ListNode(int data) {
            this.data = data;
        }

        public ListNode() {
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k){

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        //pre指针指向要翻转链表的前驱
        ListNode pre = dummy;
        //end指针指向要翻转链表的尾节点
        ListNode end = dummy;

        //while循环
        while (end.next != null) {
            //for循环：确定要反转链表的范围，这个必须通过k次循环来确定
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            //start指针指向要反转链表的头节点
            ListNode start = pre.next;
            //next指针指向要翻转链表的后继
            ListNode next = end.next;

            //下面的2句代码是“翻转链表”的过程
            //1.切断要反转链表和待翻转链表的联系
            end.next = null;
            //2.开始翻转（翻转过程是reverse()方法，pre.next的赋值是把已翻转部分和待翻转部分（刚反转完部分）连接起来）
            pre.next = reverse(start);
            //把待翻转部分（刚翻转部分）和未翻转部分连接起来
            start.next = next;

            //重置pre和end指针，以进入下一次循环
            pre = start;
            end = pre;
        }
        return dummy.next;

    }

    //链表的翻转过程（改变指针的指向）
    private static ListNode reverse(ListNode head) {
        //这个pre指针用来指向翻转后的链表头节点
        ListNode pre = null;
        //curr指针指向每次链表反转前的头节点
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    //测试
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

        System.out.println(nodeList.data);
        System.out.println(nodeList.next.data);
        System.out.println(nodeList.next.next.data);
        System.out.println(nodeList.next.next.next.data);
        System.out.println(nodeList.next.next.next.next.data);
        System.out.println(nodeList.next.next.next.next.next.data);
    }
}
