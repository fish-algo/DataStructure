package com.github.fish56.set;

import org.junit.Test;

import static org.junit.Assert.*;

public class BSTSetTest {
    @Test
    public void base(){
        BSTSet<Integer> bstSet = new BSTSet<>();
        for (int i = 0; i < 50; i++) {
            bstSet.add(i % 20);
        }
        System.out.println(bstSet.getSize());
        assertTrue(bstSet.contains(3));
        bstSet.remove(3);
        assertFalse(bstSet.contains(3));
        System.out.println(bstSet);
    }
}