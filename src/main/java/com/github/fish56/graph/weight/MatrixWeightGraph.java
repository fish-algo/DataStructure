package com.github.fish56.graph.weight;

import lombok.Getter;
import lombok.ToString;

import java.util.*;

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
 *
 */
@ToString
@Getter
public class MatrixWeightGraph {
    private int vertexNumber;
    private boolean directed;
    private int edgeNumber;

    /**
     * 存储当前图的信息
     *
     * matrix[i][j]为0代表点i和点j不相连
     * 否则说明两个点之间连通，值代表连接的权重
     */
    private double[][] matrix;

    public MatrixWeightGraph(int vertexNumber, boolean directed){
        this.vertexNumber = vertexNumber;
        this.directed = directed;
        this.matrix = new double[vertexNumber][vertexNumber];
    }

    /**
     * 两个点添加一个线
     * @param a: 第一个点
     * @param b: 第二个点
     * @param weight: 边的权重
     */
    public void addEdge(int a, int b, double weight){
        if (hasEdge(a, b)) {
            return;
        }

        matrix[a][b] = weight;

        // 如果它是无向图，另一个边也要加
        if (!directed) {
            matrix[b][a] = weight;
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
    public List<Integer> neighbors(int v) {
        List<Integer> pointList = new ArrayList<>();

        // 遍历matrix[v]中的每个元素
        for (int i = 0; i < vertexNumber; i++) {
            if (matrix[v][i] != 0){
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

    public boolean hasEdge(int a, int b) {
        if (a >= vertexNumber || b >= vertexNumber) {
            throw new IllegalArgumentException("数组中没有这么多点");
        }

        // 不管是不是有向图，判断这一个就行了
        return matrix[a][b] != 0;
    }


    /**
     * 用来做递归调用的
     * @param a
     * @param loopedPoints
     * @param points
     * @return
     */
    private void deepFirstLoop(int a, Set<Integer> loopedPoints, List<Integer> points) {
        // 遍历当前点的连通点
        for (int currentPoint = 0; currentPoint < vertexNumber; currentPoint++ ){
            // 当前路径为false就跳过
            if (matrix[a][currentPoint] == 0){
                continue;
            }
            // 当前节点以及被遍历，跳过
            if (loopedPoints.contains(currentPoint)){
                continue;
            }

            loopedPoints.add(currentPoint);
            points.add(currentPoint);
            deepFirstLoop(currentPoint, loopedPoints, points);
        }
    }


    public List<Integer> deepFirstLoop(int point) {
        // 保存已经被遍历的点供后续查询
        Set<Integer> loopedPoints = new TreeSet<>();

        // 保存已经被遍历的点
        List<Integer> points = new ArrayList<>();

        deepFirstLoop(point, loopedPoints, points);

        return points;
    }

    /**
     *
     * @param beginPoint: 起始点
     * @param endPoint: 终点
     * @param loopedPoints: 以及被遍历的点
     * @param roads: 当前的路径
     * @return: 返回true就说明找到的目标点，直接返回
     *          没找到就返回false
     */
    private boolean deepFirstRoad(int beginPoint, int endPoint,
                               Set<Integer> loopedPoints, Stack<Integer> roads){
        // 遍历当前点的连通点
        for (int currentPoint = 0; currentPoint < vertexNumber; currentPoint++ ){
            // 当前路径为false就跳过
            if (matrix[beginPoint][currentPoint] == 0){
                continue;
            }

            // 说明找到了目标点，并且处于连通状态，直接返回就行了
            if (currentPoint == endPoint) {
                roads.push(currentPoint);
                return true;
            }

            // 当前节点已经被遍历，跳过
            if (loopedPoints.contains(currentPoint)){
                continue;
            }

            // 现在就以当前节点为起点，来递归的查询后续的连通性
            loopedPoints.add(currentPoint);
            roads.push(currentPoint);

            // 说明在当前节点的子节点遍历中，找到的目标的点
            boolean res = deepFirstRoad(currentPoint, endPoint, loopedPoints, roads);
            if (res) {
                return true;
            } else {
                // 说明这个节点不同，把它弹出去就行了
                roads.pop();
            }
        }
        // 说明遍历一圈后还没有找到
        return false;
    }

    public Stack<Integer> deepFirstRoad(int beginPoint, int endPoint) {
        // 保存已经被遍历的点供后续查询
        Set<Integer> loopedPoints = new TreeSet<>();

        // 存储我们的路线图
        Stack<Integer> roads = new Stack<>();

        roads.push(beginPoint);
        boolean res = deepFirstRoad(beginPoint, endPoint, loopedPoints, roads);

        return res ? roads : null;
    }

    public List<Integer> breadthFirstRoad(int beginPoint, int endPoint) {
        return null;
    }
}
