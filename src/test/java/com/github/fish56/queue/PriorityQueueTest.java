package com.github.fish56.queue;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class PriorityQueueTest {
    @Test
    public void base(){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue);
        for (int i = 0; i < 20; i++) {
            queue.dequeue();
            System.out.println(queue);
        }
    }
    @Test
    public void base2(){
        Random random = new Random();
        java.util.PriorityQueue<Integer> queue = new java.util.PriorityQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.add(random.nextInt(50));
        }
        System.out.println(queue);
        for (int i = 0; i < 20; i++) {
            System.out.println(queue.remove());
        }
    }
}