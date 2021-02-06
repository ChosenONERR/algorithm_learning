package lanceToOffer.linked_List;

import java.io.InputStream;

/**
 * 输入两个递增排序的链表，【合并】这两个链表并使新链表中的结点仍然是按照递增排序的。
 *
 * 思路：首先分析合并两个链表的过程。我们的分析从合并两个链表的头结点开始。链表1的头结点的值小于链表2的头结点的值，
 * 因此链表1的头结点将是合并后链表的头结点；我们继续合并两个链表中剩余的结点。在两个链表中剩下的结点依然是排序的，
 * 因此合并这两个链表的步骤和前面的步骤是一样的。我们还是比较两个头结点的值。此时链表2的头结点的值小于链表1的头结点的值，
 * 因此链表2的头结点的值将是合并剩余结点得到的链表的头结点。我们把这个结点和前面合并链表时得到的链表的尾节点链接起来
 * 当我们得到两个链表中值较小的头结点并把它链接到已经合并的链表之后，两个链表剩余的结点依然是排序的，
 * 因此合并的步骤和之前的步骤是一样的。这就是典型的递归的过程，我们可疑定义递归函数完成这一合并过程
 *
 * 接下来我们来解决鲁棒性的问题。每当代码试图访问空指针指向的内存时程序就会崩溃，从而导致鲁棒性问题。
 * 在本题中一旦输入空的链表就会引入空的指针，因此我们要对空链表单独处理。当第一个链表是空链表，
 * 也就是它的头结点是一个空指针时，那么把它和第二个链表合并，显然合并的过程就是第二个链表。
 * 同样，当输入的第二个链表的头结点是空指针的时候，我们把它和第一个链表合并得到的结果就是第一个链表。
 * 如果两个链表都是空链表，合并的结果是得到一个空链表
 */
public class Test04_MergeTwoLinkedList {

    static class Node{
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }

        public Node() {
        }
    }

    /**
     * 代码实现1：采用递归思想进行合并
     */
    public static Node merge1(Node nodeList1, Node nodeList2){
        //增强代码鲁棒性
        if(nodeList1 == null){
            return nodeList2;
        }
        if(nodeList2 == null){
            return nodeList1;
        }

        if(nodeList1.data <= nodeList2.data){
            //链表的指针向下移一位
            nodeList1.next=merge1(nodeList1.next, nodeList2);
            return nodeList1;
        }else {
            nodeList2.next = merge1(nodeList1, nodeList2.next);
            return nodeList2;
        }
    }

    /**
     * 代码实现2：非递归
     */
    public static Node merge2(Node nodeList1, Node nodeList2){
        if(nodeList1 == null){
            return nodeList2;
        }
        if(nodeList2 == null){
            return nodeList1;
        }

        //定义两个指针
        //定义 合并后的链表的头结点
        Node mergeHead = null;
        //定义 合并后的【当前链表的】尾结点
        Node mergeCurrent = null;

        //第一个和第二个链表都没有合并完成
        while(nodeList1!=null && nodeList2!=null){
            if(nodeList1.data <= nodeList2.data){
                if(mergeHead == null){
                    //初始时合并的链表为空，则头尾节点相同
                    mergeHead = mergeCurrent = nodeList1;
                }else {
                    //把此时的nodeList1结点放入当前合并链表的尾结点【2步】
                    mergeCurrent.next = nodeList1;
                    mergeCurrent = mergeCurrent.next;
                }

                //list1向后移动一个结点
                nodeList1 = nodeList1.next;

            }else {
                if(mergeHead == null){
                    mergeHead = mergeCurrent = nodeList2;
                }else{
                    //把此时的nodeList2结点放入当前合并链表的尾结点【2步】
                    mergeCurrent.next = nodeList2;
                    mergeCurrent = mergeCurrent.next;
                }

                //list2向后移动一个结点
                nodeList2 = nodeList2.next;

            }
        }

        //可能一个链表先合并完成，另一个链表还未结束
        if (nodeList1 == null){
            mergeCurrent.next = nodeList2;
        }else {
            mergeCurrent.next = nodeList1;
        }

        return mergeHead;
    }

    //测试
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(5);
        Node node4 = new Node(2);
        Node node5 = new Node(7);
        Node node6 = new Node(9);

        node1.next=node2;
        node2.next=node3;
        node4.next=node5;
        node5.next=node6;

        Node nodeList = merge2(node1, node4);

        System.out.println(nodeList.data);
        System.out.println(nodeList.next.data);
        System.out.println(nodeList.next.next.data);
        System.out.println(nodeList.next.next.next.data);
        System.out.println(nodeList.next.next.next.next.data);
        System.out.println(nodeList.next.next.next.next.next.data);
    }
}
