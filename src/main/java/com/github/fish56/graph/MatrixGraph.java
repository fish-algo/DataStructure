package com.github.fish56.graph;

import com.alibaba.fastjson.JSONObject;
import lombok.ToString;

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
 *
 * 空间复杂度为O(n * n)
 */
@ToString
public class MatrixGraph extends AbstractGraph {
    /**
     * 存储当前图的信息
     * matrix[i][j]为true代表点i和点j相连
     */
    private boolean[][] matrix;

    /**
     * true: 有向图
     * false: 无向图
     */
    private boolean directed;

    public MatrixGraph(int vertexNumber, boolean directed){
        this.vertexNumber = vertexNumber;
        this.directed = directed;
        this.matrix = new boolean[vertexNumber][vertexNumber];
    }

    /**
     * 两个点添加一个线
     * @param a: 第一个点
     * @param b: 第二个点
     */
    @Override
    public void addEdge(int a, int b){
        if (hasEdge(a, b)) {
            return;
        }

        matrix[a][b] = true;

        // 如果它是无向图，这边也要加
        if (!directed) {
            matrix[b][a] = true;
        }
        edgeNumber++;
    }

    /**
     * 这里问题是对于有向图来说：
     *   出度和入度算不算neighbors?
     *   下面的实现是出度算但是入读不算
     *
     * O(n)复杂度
     * @param v
     * @return
     */
    @Override
    public List<Integer> neighbors(int v) {
        List<Integer> pointList = new ArrayList<>();

        // 遍历matrix[v]中的每个元素
        for (int i = 0; i < vertexNumber; i++) {
            if (matrix[v][i]){
                pointList.add(i);
            }
        }
        return pointList;
    }

    /**
     * 判断a -> b是否有线段
     * @param a
     * @param b
     * @return
     */
    @Override
    public boolean hasEdge(int a, int b) {
        // 不管是不是有向图，判断这一个就行了
        return matrix[a][b];
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(matrix);
    }
}
