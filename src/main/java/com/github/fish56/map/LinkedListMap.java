package com.github.fish56.map;

import lombok.Getter;
import lombok.ToString;

/**
 * 相对于LinkedListSet，就是在节点上多了一个空间来容纳value
 * 不过我们还是要重新实现我们的LinkedList的结构
 * @param <K>
 * @param <V>
 */
@ToString
public class LinkedListMap<K, V> implements Map<K, V> {
    private Node head;
    @Getter
    private int size;

    public LinkedListMap() {
        head = null;
        size = 0;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 获得索引为index处的node节点
     * @param index
     * @return
     */
    private Node getNode(int index){
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("索引应该在[0, size) 之间");
        }

        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    /**
     * 所有方法中就是这里比较耗时O(n)
     * @param key
     * @return 响应节点的引用，可以原地修改
     */
    private Node getNode(K key){
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.key.equals(key)){
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;
    }
    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }
    @Override
    public V get(K key){
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    /**
     * 这里尝试修改时，我们就是update
     * @param key
     * @param value
     */
    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            Node newNode = new Node(key, value);
            newNode.next = head;
            head = newNode;
            size++;
        } else {
            node.value = value;
        }
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node == null) {
            throw new IllegalArgumentException("键不存在");
        } else {
            node.value = newValue;
        }
    }

    @Override
    public V remove(K key) {
        if (head == null) {
            throw new IllegalArgumentException("链表是空的啊");
        }
        if (head.key.equals(key)){
            Node removedNode = head;
            head = head.next;
            size--;
            return removedNode.value;
        }

        Node preNode = head;
        Node currentNode = head.next;

        while (currentNode != null) {
            if (currentNode.key.equals(key)){
                Node removedNode = currentNode;
                preNode.next = currentNode.next;
                size--;
                return removedNode.value;
            }
            preNode = preNode.next;
            currentNode = currentNode.next;
        }

        throw new IllegalArgumentException("key: " + key + " 不存在");

    }

    @ToString
    private class Node{
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node(K key){
            this(key, null);
        }
    }
}
