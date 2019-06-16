package com.github.fish56.linkedlist;

import lombok.Getter;
import lombok.ToString;

/**
 * 单向链表
 * @param <E>
 */
@ToString
public class OneWayLinkedList<E> {
    private Node head;

    @Getter
    private int size;

    public OneWayLinkedList() {
        head = null;
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 在链表头添加一个节点
     * @param e
     */
    public void insertFirst(E e){
        Node node = new Node(e);
        node.next = head;
        this.head = node;
        size++;
    }

    /**
     * 获得索引为index处的node节点
     * @param index
     * @return
     */
    public Node getNode(int index){
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("索引应该在[0, size) 之间");
        }

        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }
    public E get(int index){
        return getNode(index).e;
    }
    public E getFirst(){
        if (size == 0) {
            throw new IllegalArgumentException("这是空链表");
        }
        return head.e;
    }

    private Node removeFirstNode(){
        if (head == null) {
            throw new IllegalArgumentException("链表是空的啊");
        }
        Node removed = head;
        this.head = head.next;
        size--;
        return removed;
    }
    private Node removeNode(int index){
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("索引应该在[0, size) 之间");
        }
        if (index == 0){
            return removeFirstNode();
        }
        // 获得索引为index-1出的node
        Node preNode = getNode(index - 1);
        Node currentNote = preNode.next;
        preNode.next = currentNote.next;
        size--;
        return currentNote;
    }
    public E removeFirst(){
        return removeFirstNode().e;
    }

    /**
     * 将元素e放到链表索引为index的位置
     * 原来的元素向后移动
     *
     * 原来a, b, c
     * insert(m, 1)
     *   ==> a, m, b, c
     *
     * 为了插入成功，我们需要寻找到第index-1和index这两个节点
     * @param e
     * @param index
     */
    public void insert(E e, int index){
        Node node = new Node(e);

        // 这种情况下没有第index - 1 个
        if (index == 0){
            insertFirst(e);
            return;
        }

        // 第index-1个节点
        Node node1 = getNode(index - 1);
        // 第index个节点
        Node node2 = node1.next;

        // 插入
        node1.next = node;
        node.next = node2;
        size++;
    }
    public void remove(E e){
        if (!contains(e)){
            throw new RuntimeException("链表中没有这个元素，无法删除");
        }
        // 这个判断之后head必然不是null
        // 并且lis中一定有元素e
        size--;

        if (head.e.equals(e)){
            head = head.next;
            return;
        }

        Node currentNode = head;

        while (currentNode.next != null) {
            if (currentNode.next.e.equals(e)){
                currentNode.next = currentNode.next.next;
                return;
            }
            currentNode = currentNode.next;
        }
    }

    /**
     * 看看链表中时候有元素e
     * @param e
     * @return
     */
    public boolean contains(E e){
        if (head == null) {
            return false;
        }

        Node currentNode = head;
        while (currentNode != null){
            if (currentNode.e.equals(e)){
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
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
