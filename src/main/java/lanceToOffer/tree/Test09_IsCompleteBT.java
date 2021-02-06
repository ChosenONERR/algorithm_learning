package lanceToOffer.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 【关于判断XXX二叉树的套路】：
 *
 *  1.列出所有可能性
 *  2.整理出返回值的类型ReturnData【整个递归要按照同样的返回值的结构】
 *  3.得到左右子树的信息
 *  4.整合子树的信息
 *  5.返回我的信息
 */

/**
 * 判断一棵树是否是完全二叉树
 * 思路：采用 层序遍历 + 完全二叉树的特点 进行判断
 *
 * 代码思路：在层序遍历的过程中，会有以下两大情况：
 * 情况1：左右双全，则看下一个结点
 * 情况2：如果一个结点不是左右双全：
 *  2.1 如果一个结点无左结点，有右节点，则一定不是完全二叉树
 *  2.2 如果一个结点有左结点，无右节点，则后面遇到的结点必须都是叶节点才能使完全二叉树，否则false
 *  2.3 如果一个结点无左结点，无右结点，则后面遇到的结点必须都是叶节点才能使完全二叉树，否则false
 */
public class Test09_IsCompleteBT {

    public Boolean isCompleteBT(BinaryTreeNode root){
        //代码鲁棒性
        if(root == null){
            return false;
        }

        //当前节点后面的节点必须都是叶子节点 的判断开启标志
        Boolean afterMustLeaf = false;

        //开始层序遍历
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        //根节点先入队
        queue.add(root);
        //先指定两个左右孩子指针，方便后面判断
        BinaryTreeNode leftChild = null;
        BinaryTreeNode rightChild = null;

        while(!queue.isEmpty()){

            root = queue.poll();
            leftChild = root.getLeftChild();
            rightChild = root.getRightChild();

            // 当开启所有子节点都必须为叶节点时，出现非叶节点，或者出现左子节点为空，右子节点不为空的情况直接返回false
            if(afterMustLeaf && (leftChild != null || rightChild != null) || (leftChild == null && rightChild != null)){
                return false;
            }

            if(leftChild != null){
                // 压入左子节点
                queue.add(leftChild);
            }

            if(rightChild != null){
                // 压入右子节点【根据第一个if，此时若右节点能够成功压入，说明一定是左右双全了】
                queue.add(rightChild);
            }else {
                // 前面的节点都是左右双全，但是到这里少了右子节点【左子节点可能有也可能没有】，后序节点都必须为叶节点，开启标志
                afterMustLeaf = true;
            }
        }
        return true;
    }
}
