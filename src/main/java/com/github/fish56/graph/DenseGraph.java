package com.github.fish56.graph;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用邻接矩阵实现稠密图(有向，不考虑平行边)
 *
 * a, b, c 三个点，有下面的矩阵
 *
 *    a  b c
 * a: 0，1，0
 * b: 1，0，0
 * c: 0，0，0
 *
 * 代表a和b互相连接，其他的点没有连接
 */
public class DenseGraph {
    /**
     * 这个图的点数
     */
    @Getter
    private int points;

    /**
     * 图中边的个数
     */
    @Getter
    private int edges;

    /**
     * 存储当前图的信息
     * graph[i][j]为true代表点i和点j相连
     */
    private boolean[][] graph;

    /**
     * true: 有向图
     * false: 无向图
     */
    private boolean directed;

    public DenseGraph(int points, boolean directed){
        this.points = points;
        this.directed = directed;
        this.graph = new boolean[points][points];
    }

    /**
     * 两个点添加一个线
     * @param a: 第一个点
     * @param b: 第二个点
     */
    public void addEdge(int a, int b){
        if (hasEdge(a, b)) {
            return;
        }

        graph[a][b] = true;

        // 如果它是无向图，这边也要加
        if (!directed) {
            graph[b][a] = true;
        }
        edges++;
    }

    /**
     * 判断a -> b是否有线段
     * @param a
     * @param b
     * @return
     */
    public boolean hasEdge(int a, int b) {
        return graph[a][b];
    }

    /**
     * 获得点a的相邻边
     * @param a
     * @return 和a有相邻边的点的数组
     */
    public List<Integer> getEdge(int a){
        List<Integer> pointList = new ArrayList<>();
        for (int i = 0; i < points; i++) {
            if (graph[a][i]){
                pointList.add(i);
            }
        }
        return pointList;
    }
    @Override
    public String toString() {
        return JSONObject.toJSONString(graph);
    }
}
