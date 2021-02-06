package lanceToOffer.tree;

import lombok.Data;

import java.util.Stack;

/**
 * 判断一棵树是否是搜索二叉树、判断一棵树是否是完全二叉树
 *
 * 思路：根据 二叉搜索树的特点 进行判断
 * 二叉排序树特点：没有重复结点（有重复的值可以放到同一个节点中，拉个链表），对任何一节点，左子树都比它小，右子树都比它大
 *
 * 代码思路：
 * 对二叉排序树进行中序遍历（左中右），在排序过程中对节点的值进行前后比较，如果是依次升序，就是搜索二叉树
 */
public class Test10_IsBinarySearchTree {
    /**
     * 因为要比较节点值的大小，所以这里重新定义二叉树节点，值为int类型
     */
    @Data
     static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int val){
            this.value = val;
        }
    }

    /**
     * 判断一棵树是否是二叉搜索树
     */
    public Boolean IsBinarySearchTree(Node root){
        //代码鲁棒性
        if(root == null){
            return true;// 空树是二叉搜索树
        }


        // 这里最好将值设置为int类型的最小值，因为树里面第一个节点可能存的也是很小的值
        int pre = Integer.MIN_VALUE;

        Stack<Node> stack = new Stack<>();
        while(!stack.isEmpty() || root!=null){

            if(root != null){
                while(root != null){
                    stack.push(root);
                    root = root.getLeft();
                }
            }else {
                root = stack.pop();

                // 判断前一个节点的值是否小于当前节点的值
                if(pre > root.getValue()){
                    return false;
                }

                //更新pre
                pre = root.getValue();

                root = root.getRight();
            }
        }
        return true;
    }
}
