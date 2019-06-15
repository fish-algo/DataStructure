package com.github.fish56.graph;

import org.junit.Test;

public class MatrixGraphTest {

    @Test
    public void addEdge() {
        MatrixGraph graph = new MatrixGraph(3, true);
        graph.addEdge(2, 1);
        System.out.println(graph);
        // [[false,false,false],[false,false,false],[false,true,false]]
        System.out.println(graph.neighbors(2));
        // [1]
    }

    @Test
    public void hasEdge() {
    }
}