package com.github.fish56.queue;

public interface Queue<E> {
    void enqueue(E e);
    E dequeue();
    E peekFront();
    int getSize();
    boolean isEmpty();
}
