package com.github.fish56.graph;

import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class ListGraphTest {

    @Test
    public void addEdge() {
        ListGraph graph = new ListGraph(30, true);
        for (int i = 0; i < 20; i++) {
            graph.addEdge(i, i + 1);
        }
        assertTrue(graph.getEdgeNumber() == 20);
    }

    @Test
    public void hasEdge() {
        ListGraph graph = new ListGraph(4, true);
        graph.addEdge(2, 3);
    }

    @Test
    public void getEdge(){
        ListGraph graph = new ListGraph(4, true);
        assertFalse(graph.hasEdge(2, 3));
        graph.addEdge(2, 3);
        assertTrue(graph.hasEdge(2, 3));
    }

    /**
     * 深度优先遍历
     */
    @Test
    public void deepFirstLoop() {
        ListGraph graph = new ListGraph(4, true);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(1, 0);
        List<Integer> list = graph.deepFirstLoop(2);
        System.out.println(list);
        assertTrue(list.get(0) == 3);
        assertTrue(list.get(1) == 1);
        assertTrue(list.get(2) == 0);
    }

    @Test
    public void deepFirstLoop2() {
        Random random = new Random();
        ListGraph graph = new ListGraph(100, true);
        for (int i = 0; i < 800; i++) {
            graph.addEdge(random.nextInt(100), random.nextInt(100));
        }
        List<Integer> list = graph.deepFirstLoop(2);
        System.out.println(graph.neighbors(2));
        System.out.println(list);
    }

    @Test
    public void breadthFirstTravers() {
        ListGraph graph = new ListGraph(4, true);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(1, 0);
        List<Integer> list = graph.breadthFirstRoad(2, 0);
        System.out.println(graph.neighbors(2));
        System.out.println(list);
    }
}