package lanceToOffer.tree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树遍历的非递归实现
 * 小结一下：前、中和后序遍历都采用了栈，而层级遍历采用了队列结构
 */
public class Test02_Order {
    /**
     * 前序遍历：中 —> 左 —> 右
     * 采用【栈】的方式实现前序遍历：
     * 先处理当前结点（根节点先进栈），若当前节点有右子树先压右子树进栈，有左孩子再压左子树进栈，
     * 这样栈弹出时候就会是先左，再右。
     */
    public void preOrder(BinaryTreeNode root){

        //代码鲁棒性
        if(root == null){
            return;
        }

        //创建栈结构
        Stack<BinaryTreeNode> stack = new Stack<>();
        //根节点先进栈
        stack.push(root);

        //遍历开始，直至栈空
        while(!stack.isEmpty()){

            //1.当前节点先出栈（其实可以理解为根节点）
            root = stack.pop();
            System.out.println(root.getValue());
            //2.若有右子树，则右子树先进栈
            if(root .getRightChild() != null){
                stack.push(root.getRightChild());
            }
            //3.若有左子树，则左子树后进栈
            if(root.getLeftChild() != null){
                stack.push(root.getLeftChild());
            }
        }
    }

    /**
     * 中序遍历： 左 —> 中  —> 右
     * 采用【栈】的方式实现中序遍历：
     * 当前节点会把自己的左边界一次性都压到栈里，然后依次弹出，直到遇到一个有右孩子的节点，处理它的右孩子.
     * 这样就模拟了“左、中、右”这样的一个过程
     */
    public void inOrder(BinaryTreeNode root){

        //鲁棒性
       if(root == null){
           return;
       }

       //创建栈结构
       Stack<BinaryTreeNode> stack = new Stack<>();

       //以下while循环总思路：把当前节点的左边界一次性压入栈中，然后依次弹出；弹出的每一个节点再作为“当前节点”，重复刚刚的操作。
       while(!stack.isEmpty() || root!=null){//需要判断root是否为null，是因为后序不同于先序和中序，它是在循环中将root才压入栈的
            if (root != null){
                while(root != null){
                    //把当前节点的左边界一次性压入栈中
                    stack.push(root);
                    root = root.getLeftChild();
                }
            }else {//当前节点为空时，说明上面已经压完了当前节点的所有左边界节点

                //依次弹出刚刚压入栈的节点,
                root = stack.pop();
                System.out.println(root.getValue());

                //对弹出节点的右子树进行处理
                root = root.getRightChild();
            }
       }
    }

    /**
     * 后序遍历
     * 采用两个栈实现，由 变异的先序过程 + 一个栈 改进而来，先序中左右 ->(变异) 中右左 ->(栈) 左右中。
     *
     * 代码分析：
     * 【先序中左右】，先处理当前结点，然后改为先压左孩子，再压右孩子，那么弹出的顺序就成为了右左【中右左】，
     * 再利用一个栈即可变为【左右中】
     */
    public void postOrder(BinaryTreeNode root){

        //代码鲁棒性
        if(root == null){
            return;
        }
        //创建两个栈结构
        Stack<BinaryTreeNode> stack1 = new Stack<>();
        Stack<BinaryTreeNode> stack2 = new Stack<>();

        //以下是变异的先序遍历（// stack1中最后弹出的顺序是：中右左，所以压栈顺序为：先压左再压右）
        stack1.push(root);

        while(!stack1.isEmpty()){

            root = stack1.pop();

            // stack2的压入顺序即为stack1的弹出顺序：中右左，因此stack2的弹出顺序为：左右中
            stack2.push(root);

            //先把左子树压入栈
            if(root.getLeftChild() != null){
                stack1.push(root.getLeftChild());
            }
            //后把右子树压入栈
            if(root.getRightChild() != null){
                stack1.push(root.getRightChild());
            }
        }

        // 弹出stack2中的元素
        while(!stack2.isEmpty()){
            BinaryTreeNode treeNode = stack2.pop();
            System.out.println(treeNode.getValue());
        }
    }

    /**
     * 层级遍历
     * 只需要增加一个【队列】，将遍历过程中的节点依次放进去，再去按照队列中的节点去遍历它们的子节点。
     */
    public void levelOrder(BinaryTreeNode root){
        //代码鲁棒性
        if(root == null){
            return;
        }

        //创建队列结构
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        //根节点先入队列
        queue.add(root);

        //
        while (!queue.isEmpty()){
            //当前节点出队
            BinaryTreeNode treeNode = queue.poll();
            System.out.println(treeNode.getValue());
            if(treeNode.getLeftChild() != null){
                //当前节点如果有左孩子，则入队
                queue.add(treeNode.getLeftChild());
            }
            if(treeNode.getRightChild() != null){
                //当前节点如果有右孩子，则入队
                queue.add(treeNode.getRightChild());
            }
        }
    }









    /**
     * 初始化一颗二叉树
     *         A
     *    B        C
     *  D   E   F
     */
    private BinaryTreeNode initBinaryTreeNode() {
        BinaryTreeNode Fnode = new BinaryTreeNode("F", null, null);
        BinaryTreeNode Cnode = new BinaryTreeNode("C", Fnode, null);
        BinaryTreeNode Enode = new BinaryTreeNode("E", null, null);
        BinaryTreeNode Dnode = new BinaryTreeNode("D", null, null);
        BinaryTreeNode Bnode = new BinaryTreeNode("B", Dnode, Enode);
        BinaryTreeNode root = new BinaryTreeNode("A", Bnode, Cnode);
        return root;
    }

    @Test
    public void test() {
        BinaryTreeNode root = initBinaryTreeNode();
        System.out.println("非递归后序遍历（采用两个栈的方式）:");
        postOrder(root);
        System.out.println();

        System.out.println("非递归后序遍历（采用一个栈的方式）：");
        //postOrderWithOneStack(root);
        System.out.println();

        System.out.println("递归后序遍历:");
        //postOrderWithRecursion(root);
        System.out.println();

        System.out.println("非递归先序遍历:");
        preOrder(root);
        System.out.println();

        System.out.println("递归先序遍历：");
        //priOrderWithRecursion(root);
        System.out.println();

        System.out.println("非递归中序遍历:");
        inOrder(root);
        System.out.println();

        System.out.println("递归中序遍历：");
        //inOrderWithRecursion(root);
    }
}
