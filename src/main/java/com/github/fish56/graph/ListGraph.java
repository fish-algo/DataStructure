package com.github.fish56.graph;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用邻接表实现稀疏图
 * a,b,c
 *
 * a: 2, 3
 * b: 1
 * c: 1
 *
 * 这种表示连接关系
 *
 * 首先是一个数组，数组的长度就是顶点数
 * 每个元素是一个list，存储了所有和这一个点相连接的其他点
 */
public class ListGraph extends AbstractGraph{

    /**
     * 存储当前图的信息
     * graph[i][j]为true代表点i和点j相连
     *
     * 用堆存储比较好吧。。
     */
    @Getter
    private List<Integer>[] adjacencyList;

    /**
     * true: 有向图
     * false: 无向图
     */
    private boolean directed;

    public ListGraph(int vertexNumber, boolean directed){
        this.vertexNumber = vertexNumber;
        this.directed = directed;
        this.adjacencyList = new ArrayList[vertexNumber];
        for (int i = 0; i < vertexNumber; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    @Override
    public Iterable<Integer> neighbors(int v) {
        return null;
    }

    /**
     * 两个点添加一个线
     * @param a: 第a个点
     * @param b: 第b个点
     */
    @Override
    public void addEdge(int a, int b){
        // 效率比较低，可以放弃这个判断
        if (hasEdge(a, b)) {
            return;
        }
        if (a == b) {
            return;
        }

//        graph.get(a).add(b);
//
//        // 如果它是无向图，这边也要加
//        if (!directed) {
//            graph.get(b).add(a);
//        }
//        edges++;
    }

    /**
     * 判断a -> b是否有线段
     * 时间复杂度为O(n)
     * @param a
     * @param b
     * @return
     */
    @Override
    public boolean hasEdge(int a, int b) {
//        for (int i = 0; i < graph.get(a).size(); i++) {
//            if (graph.get(a).get(i).equals(b)) {
//                return true;
//            }
//        }
        return false;
    }

    /**
     * 获得点a的相邻边
     * @param a
     * @return 和a有相邻边的点的list
     */
//    public List<Integer> getEdge(int a){
//        return graph.get(a);
//    }
}
