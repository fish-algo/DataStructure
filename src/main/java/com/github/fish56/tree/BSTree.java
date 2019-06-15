package com.github.fish56.tree;

import lombok.Getter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二分搜索树
 * @param <E>
 */
@ToString
public class BSTree<E extends Comparable<E>> {
    private Node root;
    @Getter
    private int size;

    public BSTree() {
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * @param e
     * @param node 这个节点不会为空，自己手动确保这一点方便编码
     * 将元素e插入到node节点中，
     * 如果e和已有的值相等，则忽略这次插入
     *
     * O(log(n))的复杂度
     */
    private void addToNode(E e, Node node){
        // 相等的话，不做任何事
        if (node.e.equals(e)){
            return;
        }
        // 这个值应该被插入左子树
        if (node.e.compareTo(e) > 0){
            if (node.left == null){
                node.left = new Node(e);
                size++;
            } else {
                addToNode(e, node.left);
            }
        } else {
            // 这个值应该被插入右子树
            if (node.right == null){
                node.right = new Node(e);
                size++;
            } else {
                addToNode(e, node.right);
            }
        }
    }
    public void add(E e){
        if (root == null) {
            root = new Node(e);
        }  else {
            addToNode(e, root);
        }
    }

    /**
     * 看看这个节点以及它的子节点是不是包含e
     * @param e
     * @param node
     * @return
     * O(log(n))
     */
    private boolean contains(E e, Node node){
        if (node == null) {
            return false;
        } else if (node.e.equals(e)){
            return true;
        } else if (node.e.compareTo(e) > 0) {
            return contains(e, node.left);
        } else {
            return contains(e, node.right);
        }
    }
    public boolean contains(E e){
        return contains(e, root);
    }

    /**
     * 中序遍历
     * 返回值从小到大
     * @param node
     */
    private void inOrder(Node node){
        if (node == null) {
            return;
        }
        inOrder(node.left);
        // 在中间
        System.out.println(node.e);
        inOrder(node.right);
    }
    public void inOrder(){
        inOrder(root);
    }

    /**
     * 前遍历
     * @param node
     */
    private void preOrder(Node node){
        if (node == null) {
            return;
        }
        // 在前面
        System.out.println(node.e);
        inOrder(node.right);
        inOrder(node.left);
    }
    public void preOrder(){
        preOrder(root);
    }

    /**
     * 按照层级广度优先遍历
     * 通过一个队列来存储下一个层级的节点
     * 每次遍历当前的节点后将当前节点的左右子树添加到队列中
     */
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        if (root == null) {
            return;
        }
        queue.add(root);
        while (!queue.isEmpty()){
            Node currentNode = queue.remove();
            System.out.println(currentNode.e);
            if (currentNode.left != null){
                queue.add(currentNode.left);
            }
            if (currentNode.right != null){
                queue.add(currentNode.right);
            }
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
    public E findMinimum(){
        if (root == null) {
            throw new RuntimeException("🌲中没有元素了");
        }
        return findMinimum(root).e;
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
    public E findMaximum(){
        if (root == null) {
            throw new RuntimeException("🌲中没有元素了");
        }
        return findMaximum(root).e;
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
     * 这里我们把寻找最小值和删除最小值给分开
     * 简化的编码的逻辑
     * @return
     */
    public E removeMin(){
        if (root == null) {
            throw new RuntimeException("你的树已经没有元素了");
        }
        E e = findMinimum();
        root = removeMin(root);
        return e;
    }

    /**
     * 删除给定node的最大节点
     *  并返回 <strong>删除节点后新的二分搜索树的的根</strong>
     * 这个定义很重要，可以让我们的编码更加的方便
     * @param node
     * @return
     */
    private Node removeMax(Node node){
        //  当前节点就是最小节点
        if (node.right == null) {
            Node rightNode = node.left;
            node.left = null;
            size--;
            return rightNode;
        } else {
            node.right = removeMax(node.right);
            return node;
        }
    }
    /**
     * 这里我们把寻找最小值和删除最小值给分开
     * 简化的编码的逻辑
     * @return
     */
    public E removeMax(){
        if (root == null) {
            throw new RuntimeException("你的树已经没有元素了");
        }
        E e = findMaximum();
        root = removeMax(root);
        return e;
    }

    private Node remove(E e, Node node){
        if (node == null){
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(e, node.left);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(e, node.right);
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
                node.e = successor.e;
                return node;
            }
        }

    }
    public void remove(E e){
        root = remove(e, root);
    }

    @ToString
    private class Node{
        public E e;
        public Node left, right;
        public Node(E e){
            this.e = e;
        }
    }
}
