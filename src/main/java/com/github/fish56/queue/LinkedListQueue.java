package com.github.fish56.queue;

import com.github.fish56.linkedlist.OneWayLinkedList;
import lombok.Getter;
import lombok.ToString;
import lombok.val;

/**
 * 链表的head就是队列的头部，同来删除元素，只要O(1)复杂度
 * 链表的尾部tail最为队列的尾部，单独用一个变量来保存，用来添加元素，O(1)的复杂度
 * @param <E>
 */
@ToString
public class LinkedListQueue<E> implements Queue<E> {
    private Node head;
    private Node tail;

    @Getter
    private int size;


    public LinkedListQueue(){
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void enqueue(E e) {
        // 说明当前链表为空
        if (tail == null) {
            head = new Node(e);
            tail = head;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("队列中没有元素！！");
        }
        Node removedNode = head;
        head = head.next;

        // 说明队列空了，tail刚才被移除了
        if (head == null){
            tail = null;
        }
        removedNode.next = null;
        size--;
        return removedNode.e;
    }

    @Override
    public E peekFront() {
        return head == null ? null : head.e;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @ToString
    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }
        public Node(E e){
            this(e, null);
        }
        public Node(){
            this(null, null);
        }
    }
}
