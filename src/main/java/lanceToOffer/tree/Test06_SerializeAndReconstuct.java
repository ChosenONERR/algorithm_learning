package lanceToOffer.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化
 *
 * 【分析】：序列化：怎么记录下来（包括结构这些），反序列化：怎么还原结构。
 * “_”：用于分开节点中的值；
 * “#”：用于表示null空节点，用这些符号表示null节点把位置给占住，不然无法区分一些节点值都相等的情况。
 *
 * 反序列化：【技巧】：利用递归，怎么序列化就用同样的方式反序列化【先序、中序、后序都是一个套路】
 * ps：反序列化都用到了队列
 */
public class Test06_SerializeAndReconstuct {

    /**
     *  前序遍历来序列化一棵二叉树
     *  【像极了 递归遍历二叉树】
     */
    public String serializeByPre(BinaryTreeNode root){
        //代码鲁棒性
        if(root == null){
            return "#_";    //空节点则返回“#_”
        }

        String serialStr = root.getValue() + "_";   //中；“_”：用于分开节点中的值
        serialStr += serializeByPre(root.getLeftChild());//左
        serialStr += serializeByPre(root.getRightChild());//右

        return serialStr;
    }

    /**
     * 反序列化一棵二叉树（怎么序列化就怎么反序列化，此处采用前序遍历来反序列化）
     * reconstruct
     */
    public BinaryTreeNode reconstruct(String serialStr){
        //转换成数组
        String[] values = serialStr.split("_");
        //构造队列
        Queue<String> queue = new LinkedList<>();
        for (String value : values){
            queue.add(value);
        }
        return reconPreOrder(queue);//这里封装具体反序列化方案，此处reconPreOrder是通过前序遍历反序列化
    }

    /**
     * 利用队列【前序】遍历构造二叉树
     * 省略中序、后序【先序、中序、后序都是一个套路】，层级遍历除外
     * @param queue 字符串值队列
     */
    private BinaryTreeNode reconPreOrder(Queue<String> queue) {

        String value = queue.poll();
        if(value == "#_"){
            return null; // 如果值为#，则返回一个空节点
        }
        // 还是按照中、左、右的方式去递归构建二叉树
        BinaryTreeNode root = new BinaryTreeNode(value);
        root.setLeftChild(reconPreOrder(queue));
        root.setRightChild(reconPreOrder(queue));

        return root;
    }



    /**
     * 层级遍历来序列化一棵二叉树
     * 像极了【层级遍历二叉树】
     */
    public String serializeByLevel(BinaryTreeNode root){
        if(root == null){
            return "#_";
        }
        String serialStr = root.getValue() + "_";
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            //中
            root = queue.poll();

            //左
            if(root.getLeftChild() != null){
                serialStr += root.getLeftChild().getValue() +"_";
                queue.add(root.getLeftChild());
            }else {
                serialStr += "#_";
            }

            //右
            if(root.getRightChild() != null){
                serialStr += root.getRightChild().getValue() +"_";
                queue.add(root.getRightChild());
            }else {
                serialStr += "#_";
            }
        }

        return serialStr;
    }


    /**
     * 按层反序列化
     */
    public BinaryTreeNode recornByLevelString(String serialStr){
        //转换成数组
        String[] values = serialStr.split("_");
        //数组下标
        int index = 0;
        //构造队列
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        //根据第一个value值构建节点
        BinaryTreeNode root = generateNode(values[index++]);

        //中
        if(root != null){//判断根节点是否为null，若是，则队列不添加，仍为空，不走下面的while循环，直接返回null‘
            queue.add(root);
        }

        //这里有个疑问，此处直接不用node，直接用root，不好像也行嘛？
        BinaryTreeNode node = null;

        while(!queue.isEmpty()){
            node = queue.poll();

            //左
            node.setLeftChild(generateNode(values[index++]));//null也可以插入成为左孩子
            if(node.getLeftChild() != null){
                queue.add(node.getLeftChild());
            }

            //右
            node.setRightChild(generateNode(values[index++]));
            if(node.getRightChild() != null){
                queue.add(node.getRightChild());
            }
        }

        return root;
    }

    /**
     * 生成树节点
     */
    private BinaryTreeNode generateNode(String value) {

        if(value == "#_"){
            return null;
        }

        return new BinaryTreeNode(value);
    }
}
