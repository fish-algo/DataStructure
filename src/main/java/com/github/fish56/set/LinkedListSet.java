package com.github.fish56.set;

import com.github.fish56.linkedlist.OneWayLinkedList;
import lombok.ToString;

/**
 * 其实是一个无序集合
 * @param <E>
 */
@ToString
public class LinkedListSet<E> implements Set<E> {
    private OneWayLinkedList<E> linkedList;

    public LinkedListSet() {
        linkedList = new OneWayLinkedList<>();
    }

    @Override
    public void add(E e) {
        if (linkedList.contains(e)){
            return;
        } else {
            linkedList.insertFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        linkedList.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
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
