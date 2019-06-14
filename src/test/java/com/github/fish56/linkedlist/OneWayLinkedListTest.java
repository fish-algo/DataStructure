package com.github.fish56.linkedlist;

import org.junit.Test;

import static org.junit.Assert.*;

public class OneWayLinkedListTest {
    @Test
    public void insertFirst() {
        OneWayLinkedList<Integer> linkedList = new OneWayLinkedList<>();

        for (int i = 0; i < 10; i++) {
            linkedList.insertFirst(i);
            System.out.println(linkedList);
        }
        for (int i = 0; i < 10; i++) {
            linkedList.removeFirst();
            System.out.println(linkedList);
        }
    }

    @Test
    public void insert() {
        OneWayLinkedList<Integer> linkedList = new OneWayLinkedList<>();

        for (int i = 0; i < 10; i++) {
            linkedList.insert(i, i % 2);
            System.out.println(linkedList);
        }
    }
}