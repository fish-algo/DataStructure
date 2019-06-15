package com.github.fish56.tree;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class BSTreeTest {

    @Test
    public void add() {
        BSTree<Integer> bsTree = new BSTree<>();
        bsTree.add(5);
        for (int i = 0; i < 10; i++) {
            bsTree.add(i);
            System.out.println(bsTree);
        }
    }
    @Test
    public void add2() {
        BSTree<Integer> bsTree = new BSTree<>();
        Random random = new Random();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10 * 1000 * 1000; i++) {
            bsTree.add(random.nextInt(1000 * 1000));
        }
        System.out.println("添加元素: " + (System.currentTimeMillis() - start));
        // 添加元素: 5768
    }

    @Test
    public void contains() {
        BSTree<Integer> bsTree = new BSTree<>();
        assertFalse(bsTree.contains(3));
        bsTree.add(3);
        System.out.println(bsTree);
        assertTrue(bsTree.contains(3));
    }

    @Test
    public void iterator() {
        BSTree<Integer> bsTree = new BSTree<>();
        bsTree.add(10);
        for (int i = 0; i < 20; i++) {
            bsTree.add(i);
        }
        bsTree.inOrder();
        bsTree.preOrder();
    }
    @Test
    public void levelOrder() {
        BSTree<Integer> bsTree = new BSTree<>();
        bsTree.add(10);
        for (int i = 0; i < 20; i++) {
            bsTree.add(i);
        }
        bsTree.levelOrder();
    }
    @Test
    public void all() {
        BSTree<Integer> bsTree = new BSTree<>();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            bsTree.add(random.nextInt(1000));
        }
        System.out.println("中序遍历:");
        bsTree.inOrder();
        System.out.println("层级遍历");
        bsTree.levelOrder();
    }

    public void all2() {
        BSTree<Integer> bsTree = new BSTree<>();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            bsTree.add(random.nextInt(1000));
        }
        System.out.println("中序遍历:");
        bsTree.inOrder();
        System.out.println("层级遍历");
        bsTree.levelOrder();
    }

    @Test
    public void maxAndMin() {
        BSTree<Integer> bsTree = new BSTree<>();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            bsTree.add(random.nextInt(1000));
        }
        System.out.println("中序遍历:");
        bsTree.inOrder();

        System.out.println("当前最小值");
        System.out.println(bsTree.findMinimum());

        System.out.println("最大值");
        System.out.println(bsTree.findMaximum());
    }

    @Test
    public void removeMin() {
        BSTree<Integer> bsTree = new BSTree<>();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            bsTree.add(random.nextInt(1000));
        }

        for (int i = 0; i < 30; i++) {
            System.out.println("当前最小值");
            System.out.println(bsTree.removeMin());
        }
    }
    @Test
    public void removeMax() {
        BSTree<Integer> bsTree = new BSTree<>();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            bsTree.add(random.nextInt(1000));
        }

        for (int i = 0; i < 30; i++) {
            System.out.println("当前最大值");
            System.out.println(bsTree.removeMax());
        }
    }
    @Test
    public void remove() {
        BSTree<Integer> bsTree = new BSTree<>();
        bsTree.add(15);
        for (int i = 0; i < 30; i++) {
            bsTree.add(i);
        }
        bsTree.inOrder();
        bsTree.remove(20);
        bsTree.inOrder();
    }
}