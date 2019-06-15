package com.github.fish56.graph;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@ToString
public class ListGraph extends AbstractGraph{

    /**
     * 存储当前图的信息
     * adjacencySet[i]是一个集合，表示点i指向的点
     *
     */
    private Set<Integer>[] adjacencySet;

    /**
     * true: 有向图
     * false: 无向图
     */
    private boolean directed;

    public ListGraph(int vertexNumber, boolean directed){
        this.vertexNumber = vertexNumber;
        this.directed = directed;
        this.adjacencySet = new HashSet[vertexNumber];
        for (int i = 0; i < vertexNumber; i++) {
            adjacencySet[i] = new HashSet<>();
        }
    }

    @Override
    public Set<Integer> neighbors(int v) {
        return adjacencySet[v];
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

        adjacencySet[a].add(b);

        // 如果它是无向图，这边也要加
        if (!directed) {
            adjacencySet[a].add(b);
        }
        edgeNumber++;
    }

    /**
     * 判断a -> b是否有线段
     * 时间复杂度为O(1)
     * @param a
     * @param b
     * @return
     */
    @Override
    public boolean hasEdge(int a, int b) {
        return adjacencySet[a].contains(b);
    }
}
