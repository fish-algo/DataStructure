package com.github.fish56.set;

import org.junit.Test;

import java.util.Random;

public class SetTest {
    private void insert(Set set){
        Random random = new Random();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10 * 1000; i++) {
            set.add(random.nextInt(1000 * 1000));
        }
        long times = System.currentTimeMillis() - start;
        System.out.println(String.format("耗时: %s 毫秒", times));
    }

    @Test
    public void insert(){
        BSTSet bstSet = new BSTSet();
        insert(bstSet);
        // 耗时: 18 毫秒

        LinkedListSet linkedListSet = new LinkedListSet();
        insert(linkedListSet);
        // 耗时: 162 毫秒
    }
}
