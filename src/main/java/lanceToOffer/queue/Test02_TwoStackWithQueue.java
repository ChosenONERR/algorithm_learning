package lanceToOffer.queue;

import java.util.Stack;

/**
 * 如何仅用栈结构实现队列
 */
public class Test02_TwoStackWithQueue {
    /**
     * 原理：可以用两个栈（stack1和stack2）来实现队列 ，进入时放入stack1栈，出栈时从stack2栈出，
     * 这样就能把顺序变为先进先出,( 栈：push,pop,peek)
     *
     * 关键的两个点：
     * 1、只有当stack2为空时，stack1才能往stack2中放数据，不然顺序就会乱了；
     * 2、如果stack1要往stack2中放数据，肯定是一次性将stack1中的数据全部放到stack2中。
     */
    class TwoStackWithQueue{
        Stack<Integer> stack1;
        Stack<Integer> stack2;

        public TwoStackWithQueue(){
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        /**
         * 入队（往队列尾部添加元素）
         */
        public void add(int x){
            stack1.add(x);
        }

        /**
         * 出队（移除队列头部元素并返回）
         */
        public int poll() {
            if (stack1.size() == 0 && stack2.size() == 0) {
                throw new RuntimeException("队列为空！");
            } else if (stack2.size() == 0) {
                while (!stack1.isEmpty()) {
                    // stack2如果为空，则stack1中的元素全部倒进stack2中
                    stack2.push(stack1.pop());
                }
            }
            // 如果stack2中有元素，则直接弹出。只有当stack2为空时，才会从stack1中往stack2中放数据，而且肯定是一次性放完
            int result = stack2.pop();
            return result;
        }

        /**
         * 查看队列元素（查看队头元素但是不删除）
         */
        public int peek(){
            if (stack1.size() == 0 && stack2.size() == 0) {
                throw new RuntimeException("队列为空！");
            } else if (stack2.size() == 0) {
                while (!stack1.isEmpty()) {
                    // stack2如果为空，则stack1中的元素全部倒进stack2中
                    stack2.push(stack1.pop());
                }
            }
            // 弹出stack2中最上面的元素，即实现了队列的先进先出
            int result = stack2.peek();// 前面的和poll一样，只不过最后需要返回而不是删除
            return result;
        }
    }
}
