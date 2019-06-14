package com.github.fish56.stack;

import com.github.fish56.array.Array;
import com.github.fish56.linkedlist.OneWayLinkedList;
import lombok.ToString;

@ToString
public class LinkedListStack<E> implements Stack<E> {
    private OneWayLinkedList<E> linkedList;

    public LinkedListStack() {
        linkedList = new OneWayLinkedList<>();
    }

    @Override
    public void push(E e) {
        linkedList.insertFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
