package com.github.fish56.set;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListSetTest {
    @Test
    public void base(){
        Set<Integer> linkedListSet = new LinkedListSet<>();
        for (int i = 0; i < 50; i++) {
            linkedListSet.add(i % 20);
        }
        System.out.println(linkedListSet.getSize());
        assertTrue(linkedListSet.contains(3));
        linkedListSet.remove(3);
        assertFalse(linkedListSet.contains(3));
        System.out.println(linkedListSet);
    }
}