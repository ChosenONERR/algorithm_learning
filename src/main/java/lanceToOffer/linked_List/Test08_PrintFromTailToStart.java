package lanceToOffer.linked_List;

import java.util.Stack;

/**
 * 题目：输入一个链表的头结点，从尾到头反过来打印出每个节点的值。
 * 看到这道题，很多人的第一反应是从头到尾输出将会比较简单，于是我们很自然的想到把链表中的节点的指针反转过来，改变链表的方向，然后就可以从头到尾输出了。
 * 但该方法将会改变原来链表的结构。
 * 是否允许在打印链表的时候修改链表的结构？这个取决于面试官的要求，因此在面试的时候我们要询问清楚面试官的要求。
 *
 * 通常打印是一个只读操作，我们不希望打印时修改内容。假设面试官也要求这个题目不能改变链表的结构。
 *
 * 接下来我们想到解决这个问题肯定是要遍历链表的。遍历的顺序是从头到尾的顺序，可输出的顺序却是从尾到头。
 * 也就是说第一个遍历的节点最后一个输出，而最后一个遍历到的节点第一个输出。这就是典型的“后进先出“，我们可以用【栈】实现这种顺序。
 * 每经过一个节点的时候，把该节点放到一个栈中。当遍历完整的链表后，再从栈顶开始逐个输出节点的值，此时输出的节点的顺序已经反转过来了。
 */
public class Test08_PrintFromTailToStart {
    /**
     * 链表类
     */
    static class Node{
        //数据域
        public int data;
        //指针域
        public Node next;

        public Node(){

        }
        public Node(int data){
            data=this.data;
        }
    }

    /**
     * 代码实现1：只用栈结构逆序打印链表
     */
    public static void printFormTailToStart(Node node){
        //判空
        if(node == null){
            System.out.println("你输入的链表为空");
            return;
        }
        //思路：遍历链表，把遍历得到的元素放到到栈中；再从栈中输出元素
        //1.创建栈
        Stack<Node> stack = new Stack<>();
        //2.遍历链表
        while(node!=null){
            //入栈
            stack.push(node);
            //指针移动到下一个，以遍历下一个
            node=node.next;
        }
        //3.遍历并打印输出栈的元素
        while(!stack.isEmpty()){
            System.out.print(stack.pop().data);
        }
    }

    /**
     * 代码实现2：采用递归思想
     * 既然想到了用栈来实现这个函数，而递归在本身上就是一个栈结构，于是自然就想到了用递归来实现。要实现反过来输出链表，
     * 我们每访问到一个节点的时候，先递归输出它后面的节点，再输出该节点本身，这样链表的输出结果就反过来了。
     * 优点：代码简洁
     * 缺点：虽然代码简洁，但是当链表的长度很长的时候就会导致函数调用的层级很深，从而有可能导致函数调用栈溢出
     */
    public static void printFormTailToStartByRec(Node node){

        if(node!=null){
            //递归的终止条件：node的后继结点为null
            if(node.next!=null){
                printFormTailToStartByRec(node.next);
            }
            System.out.print(node.data);
        }else {
            System.out.println("你输入的链表为空！");
        }
    }


    //测试
    public static void main(String[] args) {
        //创造结点
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        //串成链表
        node1.next=node2;
        node2.next=node3;

        //test1
        printFormTailToStart(node1);

        System.out.println("---------");

        //test2
        printFormTailToStartByRec(node1);
    }
}
