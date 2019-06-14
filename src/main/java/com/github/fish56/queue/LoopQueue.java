package com.github.fish56.queue;

import lombok.ToString;

@ToString
public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int size;

    /**
     * 队列头部在底层数组中的索引
     */
    private int front;
    /**
     * 队列尾部在底层数组中的索引 + 1
     * 如果数组为空，单独用tail == front判断
     */
    private int tail;

    public LoopQueue(int capacity) {
        this.data = (E[])new Object[capacity];
        front = 0;
        tail = 0;
        size = 0;
    }
    public LoopQueue(){
        this(20);
    }

    /**
     * @param index: 队列中某个元素的索引
     * @return: 对应底层数组的索引
     */
    private int getIndexOfDataFromQueue(int index){
        // 考虑循环的情况，需要求余
        return (front + index) % data.length;
    }

    /**
     * 修改底层数组的容量
     */
    public void resize(int newSize){
        // 复制数组
        E[] newData = (E[])new Object[newSize];
        for (int i = 0; i < size; i++) {
            newData[i] = data[getIndexOfDataFromQueue(i)];
        }
        this.data = newData;

        // 应为是新复制的数组，所以front就是0，tail就是size
        front = 0;
        tail = size;
    }

    @Override
    public void enqueue(E e) {
        // 说明数组是满的,需要resize
        if (size + 1 == data.length){
            resize(data.length * 2);
        }

        // 赋值
        data[tail] = e;
        // 然后维护下tail的值
        tail =  (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        // 说明底层数组太浪费, 可以resize
        if (size + 1 <= data.length / 3 && data.length >= 20){
            resize(data.length / 2);
        }

        if (size == 0) {
            throw new RuntimeException("已经没得地方dequequ了");
        }

        E e = data[front];
        data[front] = null;
        // 队列前端向后走一步就行了
        front = (front + 1) % data.length;
        size--;
        return e;
    }

    @Override
    public E peekFront() {
        if (size == 0 ){
            throw new RuntimeException("队列里已经没有元素了");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    public int getCapacity(){
        return data.length - 1;
    }
}
