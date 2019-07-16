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
     * @param e 要出入的元素e
     * @param node 目标节点
     * 将元素e插入到node节点中，
     * 如果e和已有的值相等，则忽略这次插入
     * 同时手动的确保这个节点node不会为null，这样会方便写代码
     *
     * O(h)的复杂度 h为🌲的高度，一般为log(n)
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
            size++;
        }  else {
            addToNode(e, root);
        }
    }

    /**
     * 看看这个节点以及它的子节点是不是包含e
     * @param e 要查找的元素
     * @param node 目标节点
     * @return 当前二叉搜索树是否包含目标值
     */
    private boolean contains(E e, Node node){
        // 这里我们还要注意下node为null的情况
        if (node == null) {
            return false;
        }

        // 判断当前节点是否包含目标节点
        if (node.e.equals(e)){

            // 既然已经查询到了，直接返回即可
            return true;

            // 判断目标值e可能会出现在当前节点的左边还是右边
        } else if (node.e.compareTo(e) > 0) {

            // 说明在当前节点的左边，递归查询即可
            return contains(e, node.left);

        } else {
            // 说明在当前节点的右边，递归查询即可
            return contains(e, node.right);
        }
    }
    public boolean contains(E e){
        return contains(e, root);
    }

    /**
     * 中序遍历
     * 返回值是按照从小到大的顺序的
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
     * 前序遍历
     * @param node
     */
    private void preOrder(Node node){
        if (node == null) {
            return;
        }
        // 在前面
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }
    public void preOrder(){
        preOrder(root);
    }

    /**
     * 后续遍历
     * 适用于某些需要先处理子节点的情况
     * @param node
     */
    private void postOrder(Node node){
        if (node == null) {
            return;
        }
        postOrder(node.left);
        // 在中间
        System.out.println(node.e);
        postOrder(node.right);
    }
    public void postOrder(){
        postOrder(root);
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
