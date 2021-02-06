package lanceToOffer.linked_List;

/**
 * 题目： 输入一个链表，输出该链表中倒数第k哥结点。为了符合大多数人的习惯，
 * 本题从1开始计数，即链表的尾结点是倒数第1个结点。
 * 例如一个链表有6个结点，从头结点开始它们的值依次是1，2，3，4，5，6。这个链表的倒数第3个结点是值为4的结点。
 *
 * 思路1： 为了得到第K个结点，很自然的想法是先走到链表的尾端，再从尾端回溯K步。
 * 可是我们从链表结点的定义可以看出本题中的链表是单向链表，
 * 单向链表的结点只有从前往后的指针而没有从后往前的指针，因此这种思路行不通
 *
 * 思路2：既然不能从尾节点开始遍历这个链表，我们还是把思路回到头结点上来。
 * 假设整个链表有N个结点，那么倒数第k个结点就是从头结点开始的第n-k+1个结点。
 * 那么我们只要从头结点开始往后走n-k+1步就可以了。如何得到节点数n？这个不难，只需要从头开始遍历链表，每经过一个结点，计数器加1就行了
 * 也就是说我们需要遍历链表两次，第一次统计出链表中结点的个数，第二次就能找到倒数第k个结点。
 *
 * 思路3：由于面试官大多数时候会要求“之能遍历链表一次”（思路3可以在遍历一次链表的过程中确定链表中总的节点个数n的大小以及倒数第k个节点的元素，）
 * 为了实现只遍历链表一次就能找到倒数第k个结点，我们可以定义两个指针。
 * 第一个指针从链表的头指针开始遍历向前走k-1步。第二个指针保持不动；
 * 从第k步开始，第二个指针也开始从链表的头指针开始遍历。由于两个指针的距离保持在k-1步（不是距离k-1个结点），
 * 当第一个（走在前面的）指针到达链表的尾指结点时，第二个指针正好是倒数第k个结点
 *
 * 思路3就是通过倒数第k个位置和尾节点之间的关系来实现的：倒数第k个结点只要往后再走k-1步，就可以到到达尾节点。
 * 如何表现这样的关系？本思路就是通过两个指针建立联系的。通过这两个结点的关系，只要其中一个结点找到了尾节点，
 * 那么自然可以通过它们之间的位置关系将倒数第k个节点表示出来了。
 *
 * 代码鲁棒性的问题：
 * 1、输入Head指针为null。由于代码会试图访问空指针指向的内存，程序会崩溃。
 * 2、输入以Head为头结点的链表的结点总数少于k。由于在for循环中会在链表向前走k-1步，仍然会由于空指针造成崩溃。
 * 3、输入的参数k为0或负数，同样会造成程序的崩溃。
 */
public class Test05_FindKthToTailNode {

    static class Node{
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }

        public Node() {
        }
    }

    /**
     * 非简洁但易懂型
     */
    public static Node FindKthToTailNode1(Node nodelist, int k){

        //保证鲁棒性体现1：避免输入的链表为null 或者 输入的k小于等于0
        if(nodelist == null || k<=0){
            return null;
        }

        //定义两个指针
        Node left = nodelist;
        Node right = nodelist;

        for(int i=1; i<k; i++){
            if(right.next != null){
                //右指针先向后遍历链表
                right = right.next;
            }else {
                //保证鲁棒性体现2：避免输入的链表长度小于k
                System.out.println("输入的链表长度小于k！");
                return null;
            }
        }

        //for循环之后，右指针已经走了k-1步；此时，左指针也开始从头遍历，这样一来，两个指针的距离相差k-1步
        //也就是说：当右指针遍历到尾结点的时候，做指针刚刚好到达倒数第k个结点
        // （因为存在这样的一个关系：倒数第k个结点只要往后再走k-1步，就可以到到达尾节点）
        while(right.next!=null){
            left = left.next;
            right = right.next;
        }
        return left;
    }

    /**
     * 简洁型（感觉没有前者那么好理解）
     */
    public static Node FindKthToTailNode2(Node nodelist, int k){

        //定义两个指针
        Node left = nodelist;
        Node right = nodelist;

        int i=0;
        for( ; right!=null; i++){
            if(i>=k){
                //从i=k开始，之前已经循环了k-1次，也就是右指针已经走了k-1步；所以当i=k时，左指针也要开始从头遍历
                left=left.next;
            }
            right=right.next;
        }

        return i<k ? null : left;
    }

    //测试
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=node6;

        System.out.println(FindKthToTailNode1(node1, 3).data);
    }
}
