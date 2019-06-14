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

    public AbstractGraph(int vertexNumber) {
        this.vertexNumber = vertexNumber;
    }

    /**
     * 为点v和点w添加一个边
     * @param v
     * @param w
     */
    public abstract void addEdge(int v, int w);

    /**
     * 获得和点v相连的所有的的点
     * @param v
     * @return
     */
    public abstract Iterable<Integer> neighbors(int v);
}
