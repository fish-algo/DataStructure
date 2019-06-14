package com.github.fish56.queue;

import org.junit.Before;
import org.junit.Test;

public class LinkedListQueueTest {
    private LinkedListQueue<Integer> linkedListQueue;

    @Before
    public void init(){
        linkedListQueue = new LinkedListQueue<>();
    }

    @Test
    public void base(){
        for (int i = 0; i < 10; i++) {
            if (i < 5){
                linkedListQueue.enqueue(i);
            } else {
                linkedListQueue.dequeue();
            }
            System.out.println(linkedListQueue);
        }
    }

    /**
     * 因为我们的动态数组，所以这里的出队的复杂度为O(1)
     */
    @Test
    public void dequeue(){
        int loopTimes = 8 * 1000 * 1000;
        for (int i = 0; i < loopTimes; i++) {
            linkedListQueue.enqueue(i);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < loopTimes; i++) {
            linkedListQueue.dequeue();
        }
        System.out.println("出队: " + (System.currentTimeMillis() - start));
    }
    // 出队: 94
}