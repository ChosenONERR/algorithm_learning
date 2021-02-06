package lanceToOffer.tree;

import java.util.HashMap;

/**
 * 【关于判断XXX二叉树的套路】：
 *  1.列出所有可能性
 *  2.整理出返回值的类型ReturnData【整个递归要按照同样的返回值的结构】
 *  3.得到左右子树的信息
 *  4.整合子树的信息
 *  5.返回我的信息
 *
 * 题目：平衡二叉树：对任何一棵树，其左右子树高度差（绝对值）不超过1
 *
 * 思路：只要以每个结点作为根节点的树都是平衡的，则整棵树就是平衡的
 *
 * 代码步骤：
 * 1.左子树不平衡？
 * 2.右子树不平衡？
 * 3.都平衡，左树和右树的高度差超过1，则不是平衡树
 * 4.都平衡，左树和右树的高度差不超过1，则说明是平衡树
 */
public class Test11_IsBalanceBT {

    static class ReturnData{
        //二叉树是否平衡的标志
        Boolean isBalance;
        //二叉树的高度（默认值为0）
        int h;

        public ReturnData(Boolean isBalance, int h) {
            this.isBalance = isBalance;
            this.h = h;
        }
    }

    public static Boolean isBalanceBT(BinaryTreeNode root){
        return proess(root).isBalance;
    }

    public static ReturnData proess(BinaryTreeNode root){
        //代码鲁棒性
        if(root == null){
            return new ReturnData(true, 0);//空结点为平衡二叉树，高度为0
        }

        //判断左子树是否平衡
        //1.获得左子树
        BinaryTreeNode leftChild = root.getLeftChild();
        //2。获得左子树递归返回数据类型
        ReturnData leftData = proess(leftChild);
        if(! leftData.isBalance){
            // 当前节点的左子树不平衡，整棵树都不平衡了，高度信息没有用了，直接就-1
            return new ReturnData(false, -1);
        }

        //判断右子树是否平衡
        //1.获得右子树
        BinaryTreeNode rightChild = root.getRightChild();
        //2。获得右子树递归返回数据类型
        ReturnData rightData = proess(rightChild);
        if(! rightData.isBalance){
            // 当前节点的左子树不平衡，整棵树都不平衡了，高度信息没有用了，直接就-1
            return new ReturnData(false, -1);
        }

        //当代码执行到这里，说明当前节点的左右子树已经【分别】平衡
        //此时比较左右子树是否平衡（左右子树的高度差是否大于1）
        if(Math.abs(proess(leftChild).h - proess(rightChild).h) > 1){
            return new ReturnData(false, -1);
        }

        //当代码执行到这里，说明当前节点的左右子树已经平衡
        // 左右子树都平衡，且高度差小于等于1，则此节点作为根节点的子树是平衡的
        // 高度则为左右子树中最高的高度+1
        return new ReturnData(true, Math.max(leftData.h, rightData.h) +1 );
    }
}
