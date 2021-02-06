package lanceToOffer.tree;

/**
 * 题目：在二叉树中找一个节点的后继节点（ps：前中后序的树，其对应的前驱和后继是不同的）
 * 二叉树节点结构多了一个指向父节点的parent指针。假设有一棵Node类型的节点组成的二叉树，
 * 树中每个节点的parent指针都正确地指向自己的父节点，头节点的parent指向null。现在只给一个在二叉树中的某个节点 node，
 * 请实现返回node的后继节点的函数。
 *
 * 思路：根据当前节点有无右子节点分为两种情况：
 * 1.有右子树：则当前节点的后继节点则是右子树中最左的左子节点
 * 2.无右子树：则当前节点的后继节点则是向上查找，找出包括 他自身 + 它的所有祖先节点，在这些节点中找出一个“作为左子节点”
 *   节点（当有多个时，越靠近当前节点的优先级越高）的父节点，这个父节点就是当前节点的后继节点。
 *
 * 通过画图理解思路：
 *                  1
 *             2        3
 *          4   5     6    7
 * 4的后继节点：由于4没有右子树，则找它第一个作为左子节点的父节点，4本身是左子节点，其父节点为2，因此2是4的后继节点
 * 5的后继节点：由于5没有右子树，则找它第一个作为左子节点的父节点，即找2的父节点为1，因此5的后继节点是1
 * 1的后继节点：由于1有右子树，则找它右子树中的最左节点，即为6，因此6是1的后继节点
 *
 *
 *
 * 补充：找一个节点的前驱节点其实和找该节点的后继节点是对应的，根据该节点是否有左子树进行划分：
 * 1、当前节点有左子树，则找左子树中最右的节点即为当前节点的前驱节点；
 * 2、当前节点没有左子树，则向上找到一个父节点是作为右子节点的，那么这个父节点的父节点即为当前节点的前驱节点。
 */
public class Test08_GetSuccessorNode {

    public BinaryTreeNode getSuccessorNode(BinaryTreeNode root){

        if(root == null){
            return null;
        }


        if(root.getRightChild() != null){
            // 情况1：如果该节点存在右子节点，后继节点是右子树中的最左边节点

            //找到右子树的最左边的节点
            //1.获得右子树
            BinaryTreeNode node = root.getRightChild();
            //2.遍历右子树的左边
            while(node.getLeftChild() != null){
                node = node.getLeftChild();
            }
            return node;

        }else {
            // 情况2：如果没有右子树，向上查找当前节点属于哪个节点的左子树下面，即找到一个父节点是左子节点的

            BinaryTreeNode parent = root.getParent();
            //while循环（向上遍历）的两个条件：
            // 1.父节点不可以为null，这是为最后一个节点（父节点）设置的（根节点的父节点为null）
            // 2.若父节点的左孩子为本节点，也就是本届点自身也是左节点，那么就不用循环了，直接把本节点的父节点作为结果返回就可以了
            while(parent != null && parent.getLeftChild() != root){
                //向上遍历
                root = parent;
                parent = parent.getParent();
            }
            return parent;
        }
    }
}
