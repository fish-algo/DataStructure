package com.github.fish56.set;

import org.junit.Test;

import java.util.HashSet;
import java.util.Objects;
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

    private class Monkey{
        String name;

        public Monkey(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            return name.equals(((Monkey) obj).name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    /**
     * 同时重写hashCode和equals方法才能让两个对象相等
     */
    @Test
    public void contain(){
       Monkey monkey1 = new Monkey("Jon");
       Monkey monkey2 = new Monkey("Jon");

        java.util.Set<Monkey> monkeySet = new HashSet<>();
       monkeySet.add(monkey1);

       System.out.println(monkeySet.contains(monkey2));
       // false

       monkeySet.add(monkey2);
       System.out.println(monkeySet.size());
       // 2
    }
}
