package com.github.fish56.graph;

import org.junit.Test;

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
}