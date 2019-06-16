package com.github.fish56.heap;

import com.github.fish56.array.Array;
import lombok.ToString;

/**
 * 基于数组实现的最大堆
 */
@ToString
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }
    public MaxHeap(){
        data = new Array<>();
    }

    public int size(){
        return data.getSize();
    }
    public boolean isEmpty(){
        return data.isEmpty();
    }

    /**
     * 获得一个元素的父节点的索引
     * @param index
     * @return
     */
    private int parent(int index){
        if (index == 0){
            throw new IllegalArgumentException("index-0 没有父节点");
        }
        return (index - 1) / 2;
    }
    private int leftChild(int index){
        return index * 2 + 1;
    }
    private int rightChild(int index){
        return index * 2 + 2;
    }

    /**
     * 将某个元素上浮，通常用于最后一个元素
     * @param index
     */
    private void siftUp(int index){
        while (index > 0){
            E current = data.get(index);
            E parent = data.get(parent(index));
            // 需要上浮
            if (current.compareTo(parent) > 0) {
                // 父节点的值移动下来，当前的值移动上去
                swap(index, parent(index));
                index = parent(index);
            } else {
                return;
            }
        }
    }
    private void swap(int index1, int index2){
        E tem = data.get(index1);
        data.set(data.get(index2), index1);
        data.set(tem, index2);
    }

    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 将某个节点下浮
     * 对于以后符合要求，将不会做出任何改变
     * 确保以给定index的为根的堆的的根节点放到合适的位置
     * 让对最大的元素上浮起来，代替当前的根节点
     */
    private void siftDown(int index){
        // 如果这节点还有孩子，应该是左子节点的索引没有越界
        while (leftChild(index) < data.getSize()){
            int leftChildIndex = leftChild(index);
            // 右节点未必存在
            int rightChildIndex = rightChild(index);
            int maxChildIndex;

            // 又节点存在并且比左节点大
            if (rightChildIndex < data.getSize()
                    && data.get(rightChildIndex).compareTo(data.get(leftChildIndex)) > 0){
                maxChildIndex = rightChildIndex;
            } else {
                maxChildIndex = leftChildIndex;
            }

            if (data.get(index).compareTo(data.get(maxChildIndex)) > 0) {
                return;
            } else {
                swap(index, maxChildIndex);
                siftDown(maxChildIndex);
            }

        }
    }
    public E findMax(){
        if (data.getSize() == 0) {
            throw new RuntimeException("没有元素了");
        }
        return data.get(0);
    }

    /**
     * 删除最大的节点，即根节点
     * @return
     */
    public E extractMax(){
        E ret = findMax();

        // 将最后一个元素放到首部
        data.set(data.getLast(), 0);
        data.removeLast();

        // 将顶部元素修改下，维护堆的性质
        siftDown(0);
        return ret;
    }
}
