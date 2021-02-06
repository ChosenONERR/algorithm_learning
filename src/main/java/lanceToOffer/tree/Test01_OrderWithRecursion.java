package lanceToOffer.tree;

import javax.swing.tree.TreeNode;
/**
 * 二叉树遍历的递归实现
 */
public class Test01_OrderWithRecursion {

    //先序
    public void preOrder(BinaryTreeNode root){
        if(root != null){
            System.out.println(root);
        }
        preOrder(root.getLeftChild());
        preOrder(root.getRightChild());
    }

    //中序
    public void inOrder(BinaryTreeNode root){
        if(root != null){
            inOrder(root.getLeftChild());
            System.out.println(root.getValue());
            inOrder(root.getRightChild());
        }
    }

    //后序
    public void postOrder(BinaryTreeNode root){
        if(root != null){
            postOrder(root.getLeftChild());
            postOrder(root.getRightChild());
            System.out.println(root.getValue());
        }
    }

}
