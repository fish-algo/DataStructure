package com.github.fish56.map;

import org.junit.Test;

import static org.junit.Assert.*;

public class BSTMapTest {
    @Test
    public void base(){
        Map<Integer, Integer> map = new BSTMap<>();

        for (int i = 0; i < 5; i++) {
            map.add(i, i);
            System.out.println(map);
        }
        for (int i = 0; i < 5; i++) {
            map.remove(i);
            System.out.println(map);
        }
    }
}