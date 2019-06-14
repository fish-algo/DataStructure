package com.github.fish56.stack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayStackTest {
    private static int loopTimes = 30 * 1000;

    private ArrayStack<Integer> arrayStack;
    @Before
    public void init(){
        arrayStack = new ArrayStack<Integer>(20);
    }

    @Test
    public void base() {
        arrayStack.push(33);
        arrayStack.push(44);
        assertTrue(arrayStack.getSize() == 2);
        System.out.println(arrayStack);
        arrayStack.pop();
        assertFalse(arrayStack.isEmpty());
        System.out.println(arrayStack);
    }
}