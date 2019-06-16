package com.github.fish56.heap;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class MaxHeapTest {

    @Test
    public void add() {
        Random random = new Random();
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        for (int i = 0; i < 20; i++) {
            maxHeap.add(random.nextInt(1000));
            System.out.println(maxHeap);
        }
        for (int i = 0; i < 20; i++) {
            System.out.println(maxHeap.extractMax());
        }
    }
}