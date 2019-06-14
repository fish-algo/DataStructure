package com.github.fish56.stack;

public interface Stack<E> {
    /**
     * 向栈中插入数据
     * @param e
     */
    void push(E e);

    /**
     * 弹出栈顶的一个元素
     * @return
     */
    E pop();

    /**
     * 查看栈顶的元素
     * @return
     */
    E peek();

    int getSize();

    boolean isEmpty();
}
