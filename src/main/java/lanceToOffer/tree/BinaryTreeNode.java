package lanceToOffer.tree;

import lombok.Data;

/**
 * 二叉树节点的数据结构
 */
@Data
public class BinaryTreeNode {
 
    /**
     * 节点数据
     */
    private String value;
    /**
     * 左子节点
     */
    private BinaryTreeNode leftChild;
    /**
     * 右子节点
     */
    private BinaryTreeNode rightChild;
    /**
     * 指向父节点的指针（一般的二叉树可能没有这一项）
     */
    private BinaryTreeNode parent;

    /**
     * 构造函数（该构造函数用于使用队列创造二叉树的时候使用）
     * @param value 节点的值
     */
    public BinaryTreeNode(String value) {
        this.value = value;
    }

    /**
     * 构造函数
     * @param value 节点的值
     * @param leftChild 左孩子
     * @param rightChild 右孩子
     */
    public BinaryTreeNode(String value, BinaryTreeNode leftChild, BinaryTreeNode rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}
