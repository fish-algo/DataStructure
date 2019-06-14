package com.github.fish56.graph;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class DenseGraphTest {

    @Test
    public void addEdge() {
        DenseGraph graph = new DenseGraph(3, true);
        graph.addEdge(2, 1);
        System.out.println(graph);
        // [[false,false,false],[false,false,false],[false,true,false]]
        System.out.println(graph.getEdge(2));
        // [1]
    }

    @Test
    public void hasEdge() {
    }
}