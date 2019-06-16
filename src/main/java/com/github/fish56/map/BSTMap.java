package com.github.fish56.map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V>{
    private Node root;
    @Getter
    private int size;

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * @param node 这个节点不会为空，自己手动确保这一点方便编码
     * 将元素e插入到node节点中，
     * 如果e和已有的值相等，则忽略这次插入
     *
     * O(h)的复杂度 h为🌲的高度，一般为log(n)
     */
    private void addToNode(K key, V value, Node node){
        // 相等的话，不做任何事
        if (node.key.equals(key)){
            node.value = value;
            return;
        }
        // 这个值应该被插入左子树
        if (node.key.compareTo(key) > 0){
            if (node.left == null){
                node.left = new Node(key, value);
                size++;
            } else {
                addToNode(key, value, node.left);
            }
        } else {
            // 这个值应该被插入右子树
            if (node.right == null){
                node.right = new Node(key, value);
                size++;
            } else {
                addToNode(key, value, node.right);
            }
        }
    }
    @Override
    public void add(K key, V value){
        if (root == null) {
            root =  new Node(key, value);
            size++;
        }  else {
            addToNode(key, value, root);
        }
    }

    /**
     * 看看这个节点以及它的子节点是不是包含e
     * @param key
     * @param node
     * @return
     * O(log(n))
     */
    private boolean contains(K key, Node node){
        if (node == null) {
            return false;
        } else if (node.key.equals(key)){
            return true;
        } else if (node.key.compareTo(key) > 0) {
            return contains(key, node.left);
        } else {
            return contains(key, node.right);
        }
    }
    @Override
    public boolean contains(K k){
        return contains(k, root);
    }

    /**
     * 寻找这个树中最大的node
     * 就是类似链表操作，一直向最右边查找
     * @param node
     * @return
     */
    private Node findMaximum(Node node){
        Node currentNode = node;
        while (currentNode.right != null) {
            currentNode = currentNode.right;
        }
        return currentNode;
    }
    public K findMaximum(){
        if (root == null) {
            throw new RuntimeException("🌲中没有元素了");
        }
        return findMaximum(root).key;
    }

    /**
     * 删除给定node的最小节点
     *  并返回 <strong>删除节点后新的二分搜索树的的根</strong>
     * 这个定义很重要，可以让我们的编码更加的方便
     * @param node
     * @return
     */
    private Node removeMin(Node node){
        //  当前节点就是最小节点
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        } else {
            node.left = removeMin(node.left);
            return node;
        }
    }

    /**
     * 寻找这个树中最小的node
     * @param node
     * @return
     */
    private Node findMinimum(Node node){
        Node currentNode = node;
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode;
    }
    public K findMinimum(){
        if (root == null) {
            throw new RuntimeException("🌲中没有元素了");
        }
        return findMinimum(root).key;
    }

    /**
     * 这里我们把寻找最小值和删除最小值给分开
     * 简化的编码的逻辑
     * @return
     */
    public K removeMin(){
        if (root == null) {
            throw new RuntimeException("你的树已经没有元素了");
        }
        K e = findMinimum();
        root = removeMin(root);
        return e;
    }

    private Node remove(K key, Node node){
        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(key, node.left);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(key, node.right);
            return node;
        } else { // e == node.e
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            } else { // 左右子树都不为空
                // 找到比要删除节点大的最小节点（或者小的最大节点）
                // 用这个节点代替他
                Node successor = removeMin(node.right);
                node.key = successor.key;
                return node;
            }
        }

    }

    @Override
    public V remove(K key){
        V value = get(key);
        root = remove(key, root);
        return value;
    }

    private Node get(K key, Node node){
        if (node == null) {
            return null;
        } else if (node.key.equals(key)){
            return node;
        } else if (node.key.compareTo(key) > 0) {
            return get(key, node.left);
        } else {
            return get(key, node.right);
        }
    }

    @Override
    public V get(K key) {
        Node node = get(key, root);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = get(key, root);
        if (node == null){
            throw new IllegalArgumentException("key" + key + "不存在");
        }
        node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @ToString
    private class Node{
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node(K key) {
            this.key = key;
        }
    }
}
