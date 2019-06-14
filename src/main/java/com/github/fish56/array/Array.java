package com.github.fish56.array;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Array<E> {
    private static int increaseCapacityTimes = 2;

    /**
     * 实际的容量
     */
    @Getter
    private int size;

    /**
     * 底层数组
     */
    private E[] data;

    public Array(int capacity){
        data = (E[])new Object[capacity];
    }
    public Array(){
        this(10);
    }

    /**
     * 底层数组的最大容量
     */
    public int getCapacity(){
        return data.length;
    }
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 添加底层数组容量
     */
    private void increaseCapacity(){
        E[] newData = (E[])new Object[data.length * increaseCapacityTimes];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * 降低底层数组容量
     */
    private void decreaseCapacity(){
        E[] newData = (E[])new Object[data.length / 2 + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * 修改数组的容量
     */
    private void resize(){
        if (size == data.length){
            increaseCapacity();
        }
        if (size <= data.length / 3 && data.length > 20){
            decreaseCapacity();
        }
    }

    /**
     * 假设数组为 [11, 22, 33, 44]
     * insert(99, 2)后，数组变为
     * [11, 22, 99, 33, 44]
     * 原始数组索引为[2, length)都要向后移动
     * 新插入原始的索引为2
     * @param e
     * @param index: 可以取到size
     */
    public void insert(E e, int index){
        resize();
        if (index > size || index < 0) {
            throw new IllegalArgumentException("索引应该在[0, size]之间");
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i-1];
        }
        data[index] = e;
        size++;
    }
    public void addLast(E e){
        insert(e, size);
    }
    public void addFirst(E e){
        insert(e, 0);
    }
    public boolean contains(E e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    /**
     * 寻找当前元素的位置，如果不存在就返回-1
     * @param e
     * @return
     */
    public int indexOf(E e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除指定索引位置的元素，并将这个元素的值返回
     * @param index
     * @return
     *
     * data[size]这个位置的值还在，但是没有关系的
     */
    public E remove(int index){
        assertIndex(index);
        resize();
        E currentValue = data[index];
        for (int i = index; i < size - 1 ; i++) {
            data[i] = data[i + 1];
        }
        size--;
        // 释放资源
        data[size] = null;
        return currentValue;
    }
    public E removeLast(){
        return remove(size - 1);
    }
    public E removeFirst(){
        return remove(0);
    }

    public boolean removeElement(E e){
        int index = indexOf(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * 获得底层数组索引为index的位置的元素
     * @param index
     * @return
     */
    public E get(int index){
        assertIndex(index);
        return data[index];
    }
    public E getLast(){
        return get(size - 1);
    }
    public E getFirst(){
        return get(0);
    }

    public void set(E e, int index){
        assertIndex(index);
        data[index] = e;
    }

    private void assertIndex(int index){
        if (index >= size || index < 0) {
            throw new IllegalArgumentException("索引应该在[0, size)之间");
        }
    }
}
