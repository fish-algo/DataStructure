package com.github.fish56.graph.weight;

import lombok.Getter;

import java.util.List;

/**
 * 无向图的基本结构
 * 无向图安装双有向图来处理
 *
 * 默认为简单图，没有平行边和自环边
 */
public abstract class AbstractWeightGraph {
    /**
     * 当前图的顶点数
     */
    @Getter
    protected int vertexNumber;

    /**
     * 当前图的边数
     */
    @Getter
    protected int edgeNumber;

    /**
     * true: 有向图
     * false: 无向图
     * 无向图可以理解为双有向图
     */
    @Getter
    protected boolean directed;

    /**
     * 添加一个边
     */
    public abstract void addEdge(Edge edge);

    /**
     * 判断点v和点w是否相连
     * @param v
     * @param w
     */
    public abstract boolean hasEdge(int v, int w);

    /**
     * 获得和点v相连的所有的的点的集合
     * @param v
     * @return
     */
    public abstract Iterable<Edge> neighbors(int v);

    /**
     * 深度优先遍历点a
     * 边将点安装顺序返回
     * @param a
     */
    public abstract Iterable<Integer> deepFirstLoop(int a);

    /**
     * 使用广度优先遍历获得两点之间的路径
     * @param beginPoint： 起始点
     * @param endPoint： 终点
     * @return： 路径中点的有序集合
     */
    public abstract List<Integer> deepFirstRoad(int beginPoint, int endPoint);

    public abstract List<Integer> breadthFirstRoad(int beginPoint, int endPoint);
}
