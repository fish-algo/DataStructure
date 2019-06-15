package com.github.fish56.graph;

import lombok.Getter;

/**
 * 无向图的基本结构
 */
public abstract class AbstractGraph {
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
     * 为点v和点w添加一个边
     * @param v
     * @param w
     */
    public abstract void addEdge(int v, int w);

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
    public abstract Iterable<Integer> neighbors(int v);
}
