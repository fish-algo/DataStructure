package com.github.fish56.set;

import com.github.fish56.tree.BSTree;
import lombok.ToString;

@ToString
public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private BSTree<E> bst;

    public BSTSet(){
        bst = new BSTree<>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
