package com.github.fish56.queue;

import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.junit.Assert.*;

public class ArrayQueueTest {
    private ArrayQueue<Integer> arrayQueue;

    @Before
    public void init(){
        arrayQueue = new ArrayQueue<>();
    }

    @Test
    public void base(){
        for (int i = 0; i < 10; i++) {
            if (i < 5){
                arrayQueue.enqueue(i);
            } else {
                arrayQueue.dequeue();
            }
            System.out.println(arrayQueue);
        }
    }

    /**
     * 因为我们的动态数组，所以这里的出队的复杂度为O(n)
     */
    @Test
    public void dequeue(){
        int loopTimes = 80 * 1000;
        for (int i = 0; i < loopTimes; i++) {
            arrayQueue.enqueue(i);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < loopTimes; i++) {
            arrayQueue.dequeue();
        }
        System.out.println("出队: " + (System.currentTimeMillis() - start));
    }
    // 出队: 7845
}