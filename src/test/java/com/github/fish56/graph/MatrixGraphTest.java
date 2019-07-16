package com.github.fish56.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class MatrixGraphTest {

    @Test
    public void addEdge() {
        MatrixGraph graph = new MatrixGraph(3, true);
        graph.addEdge(2, 1);
        Assert.assertTrue(graph.getEdgeNumber() == 1);
        System.out.println(graph);
        // [[false,false,false],[false,false,false],[false,true,false]]
        System.out.println(graph.neighbors(2));
        // [1]
    }

    @Test
    public void hasEdge() {
        Random random = new Random();
        AbstractGraph graph = new MatrixGraph(100, true);
        for (int i = 0; i < 80; i++) {
            graph.addEdge(random.nextInt(100), random.nextInt(100));
        }
        System.out.println(graph.edgeNumber);
        System.out.println(graph.neighbors(30));
    }

    /**
     * 深度优先遍历
     */
    @Test
    public void deepFirstLoop() {
        MatrixGraph graph = new MatrixGraph(4, true);
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
        MatrixGraph graph = new MatrixGraph(100, true);
        for (int i = 0; i < 800; i++) {
            graph.addEdge(random.nextInt(100), random.nextInt(100));
        }
        List<Integer> list = graph.deepFirstLoop(2);
        System.out.println(graph.neighbors(2));
        System.out.println(list);
    }

    @Test
    public void deepFirstRoad() {
        MatrixGraph graph = new MatrixGraph(4, true);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(1, 0);

        List<Integer> roads = graph.deepFirstRoad(2, 0);
        System.out.println(roads);
    }

    @Test
    public void deepFirstRoad2() {
        Random random = new Random();
        MatrixGraph graph = new MatrixGraph(100, true);
        for (int i = 0; i < 8000; i++) {
            graph.addEdge(random.nextInt(100), random.nextInt(100));
        }
        List<Integer> roads = graph.deepFirstRoad(2, 98);
        System.out.println(roads);
    }
}