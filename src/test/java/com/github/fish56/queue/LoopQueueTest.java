package com.github.fish56.queue;

import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.junit.Assert.*;

public class LoopQueueTest {
    private LoopQueue<Integer> loopQueue;
    //@Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    @Before
    public void init(){
        loopQueue = new LoopQueue<>(5);
    }

    @Test
    public void base() {
        for (int i = 0; i < 10; i++) {
            loopQueue.enqueue(i);
            System.out.println(loopQueue);
            System.out.println(loopQueue.peekFront());
        }
        for (int i = 0; i < 9; i++) {
            loopQueue.dequeue();
            System.out.println(loopQueue);
            System.out.println(loopQueue.peekFront());
        }
    }

    /**
     * 因为我们的动态数组，所以这里的出队的复杂度为O(1)
     */
    @Test
    public void dequeue(){
        int loopTimes = 80 * 1000;
        for (int i = 0; i < loopTimes; i++) {
            loopQueue.enqueue(i);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < loopTimes; i++) {
            loopQueue.dequeue();
        }
        System.out.println("出队: " + (System.currentTimeMillis() - start));
    }
    // 出队: 4
}