package com.github.fish56.map;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListMapTest {

    @Test
    public void add() {
        Map<Integer, String> map = new LinkedListMap<>();

        for (Integer i = 0; i < 5; i++) {
            map.add(i, i.toString());
            System.out.println(map);
        }
        for (Integer i = 0; i < 5; i++) {
            map.remove(i);
            System.out.println(map);
        }
    }
}