package com.github.fish56.stack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LinkedListStackTest {
    private static int loopTimes = 30 * 1000;

    private LinkedListStack<Integer> linkedListStack;
    @Before
    public void init(){
        linkedListStack = new LinkedListStack<>();
    }

    @Test
    public void base() {
        linkedListStack.push(33);
        linkedListStack.push(44);
        assertTrue(linkedListStack.getSize() == 2);
        System.out.println(linkedListStack);
        linkedListStack.pop();
        assertFalse(linkedListStack.isEmpty());
        System.out.println(linkedListStack);
    }
}