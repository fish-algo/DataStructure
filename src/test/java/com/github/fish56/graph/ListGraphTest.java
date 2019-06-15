package com.github.fish56.graph;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListGraphTest {

    @Test
    public void addEdge() {
//        ListGraph graph = new ListGraph(4, true);
//        graph.addEdge(2, 3);
//        System.out.println(graph);
//        assertTrue(graph.getGraph().get(2).get(0).equals(3));
//        assertTrue(graph.getGraph().get(3).size() == 0);
//        // [[],[],[3],[]]
//
//        ListGraph graph2 = new ListGraph(4, false);
//        graph2.addEdge(2, 3);
//        System.out.println(graph2);
//        assertTrue(graph2.getGraph().get(2).get(0).equals(3));
//        assertTrue(graph2.getGraph().get(3).get(0).equals(2));
//        // [[],[],[3],[2]]
    }

    @Test
    public void hasEdge() {
        ListGraph graph = new ListGraph(4, true);
        graph.addEdge(2, 3);
    }

    @Test
    public void getEdge(){
        ListGraph graph = new ListGraph(4, true);
        graph.addEdge(2, 3);
        //System.out.println(JSONObject.toJSONString(graph.getEdge(2)));
        // [3]
    }
}