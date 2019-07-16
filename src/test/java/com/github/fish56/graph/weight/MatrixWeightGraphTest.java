package com.github.fish56.graph.weight;

import com.github.fish56.graph.AbstractGraph;
import com.github.fish56.graph.MatrixGraph;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class MatrixWeightGraphTest {
    @Test
    public void addEdge() {
        MatrixWeightGraph graph = new MatrixWeightGraph(3, true);
        graph.addEdge(2, 1, 4.34);
        Assert.assertTrue(graph.getEdgeNumber() == 1);
        System.out.println(graph);
        // MatrixWeightGraph(vertexNumber=3, directed=true, edgeNumber=1,
        // matrix=[[0.0, 0.0, 0.0], [0.0, 0.0, 0.0], [0.0, 4.34, 0.0]])
        System.out.println(graph.neighbors(2));
        // [1]
    }

    @Test
    public void hasEdge() {
        Random random = new Random();
        MatrixWeightGraph graph = new MatrixWeightGraph(100, true);

        for (int i = 0; i < 8000; i++) {
            graph.addEdge(random.nextInt(100), random.nextInt(100), random.nextDouble());
        }

        System.out.println(graph.neighbors(30));
    }

    /**
     * 深度优先遍历
     */
    @Test
    public void deepFirstLoop() {
        Random random = new Random();
        MatrixWeightGraph graph = new MatrixWeightGraph(4, true);
        graph.addEdge(2, 3, random.nextDouble());
        graph.addEdge(3, 1, random.nextDouble());
        graph.addEdge(1, 0, random.nextDouble());
        List<Integer> list = graph.deepFirstLoop(2);
        System.out.println(list);
        assertTrue(list.get(0) == 3);
        assertTrue(list.get(1) == 1);
        assertTrue(list.get(2) == 0);
    }

    @Test
    public void deepFirstLoop2() {
        Random random = new Random();
        MatrixWeightGraph graph = new MatrixWeightGraph(100, true);
        for (int i = 0; i < 800; i++) {
            graph.addEdge(random.nextInt(100), random.nextInt(100), random.nextDouble());
        }
        List<Integer> list = graph.deepFirstLoop(2);
        System.out.println(graph.neighbors(2));
        System.out.println(list);
    }

    @Test
    public void deepFirstRoad() {
        Random random = new Random();
        MatrixWeightGraph graph = new MatrixWeightGraph(4, true);
        graph.addEdge(2, 3, random.nextDouble());
        graph.addEdge(3, 1, random.nextDouble());
        graph.addEdge(1, 0, random.nextDouble());

        List<Integer> roads = graph.deepFirstRoad(2, 0);
        System.out.println(roads);
    }

    @Test
    public void deepFirstRoad2() {
        Random random = new Random();
        MatrixWeightGraph graph = new MatrixWeightGraph(100, true);
        for (int i = 0; i < 8000; i++) {
            graph.addEdge(random.nextInt(100), random.nextInt(100), random.nextDouble());
        }
        List<Integer> roads = graph.deepFirstRoad(2, 98);
        System.out.println(roads);
    }
}