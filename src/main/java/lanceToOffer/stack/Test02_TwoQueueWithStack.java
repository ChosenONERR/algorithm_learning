package lanceToOffer.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 如何仅用队列实现栈
 */
public class Test02_TwoQueueWithStack {

    /**
     * 原理：
     * 可以用两个队列（queue1、queue2）来实现栈，加元素时加总是在queue1；删除元素时，
     * 把 queue1 最后一位前的元素全部弹出放入 queue2 队列中，然后再弹出返回 queue1 的最后一位元素（这就达成栈后入先出的要求了），
     * 然后交换 queue1 和 queue2 指针即可
     *
     * 队列本身的方法介绍：
     * poll(移除并返回队列的头部)，add(添加一个元素到队列尾部)，peek（返回队列的头部，不删除）
     */
    class TwoQueueWithStack{

        //定义两个队列
        //我们实质要操作的队列
        private Queue<Integer> queue1;
        //辅助队列
        private Queue<Integer> queue2;

        public TwoQueueWithStack() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        /**
         * 入栈（插入一个元素）
         */
        public void push(Integer x){
            // 插入元素永远都是插入到queue中
            queue1.add(x);
        }

        /**
         * 出栈（删除栈顶元素 并返回这个元素）
         */
        public int pop(){

            //鲁棒性
            if(queue1.isEmpty()){
                throw new RuntimeException("stack is empty!");
            }

            // 将queue1中除最后一个元素外，全部弹出添加到queue2中
            while(queue1.size() > 1){
                queue2.add(queue1.poll());
            }
            int result = queue1.poll();
            swap();

            return result;
        }

        /**
         * 查看一个元素（不删除）
         */
        public int peek(){

            //鲁棒性
            if(queue1.isEmpty()){
                throw new RuntimeException("stack is empty!");
            }

            // 将queue1中除最后一个元素外，全部弹出添加到queue2中
            while(queue1.size() > 1){
                queue2.add(queue1.poll());
            }
            int result = queue1.poll();
            queue2.add(result);
            swap();

            return result;
        }

        /**
         * 互换 queue1 和 queue2 的指针, queue2 只是辅助队列，始终操作的还是queue1
         */
        public void swap(){

            //错误写法,因为：queue2 只是辅助队列，始终操作的还是queue1
            //Queue<Integer> temp = queue1;
            //queue1 = queue2;
            //queue2 = temp;

            Queue<Integer> temp = queue2;
            queue2 = queue1;
            queue1 = temp;
        }
    }
}
