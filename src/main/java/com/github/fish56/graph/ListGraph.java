package com.github.fish56.graph;

import lombok.ToString;

import java.util.*;

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

    /**
     * 用来做递归调用的
     * @param a: 遍历的目标顶点
     * @param loopedPoints: 已经被遍历的点的集合
     * @param points: 这个list用来存储获得的子节点
     * @return
     */
    private void deepFirstLoop(int a, Set<Integer> loopedPoints, List<Integer> points) {
        // 遍历当前点的连通点
        for (Integer point : adjacencySet[a]){
            // 说明这个点已经被遍历过了
            if (loopedPoints.contains(point)){
                continue;
            }
            loopedPoints.add(point);
            points.add(point);
            deepFirstLoop(point, loopedPoints, points);
        }
    }

    @Override
    public List<Integer> deepFirstLoop(int point) {
        // 保存已经被遍历的点供后续查询
        Set<Integer> loopedPoints = new TreeSet<>();

        // 保存已经被遍历的点
        List<Integer> points = new ArrayList<>();

        deepFirstLoop(point, loopedPoints, points);

        return points;
    }

    @Override
    public List<Integer> deepFirstRoad(int beginPoint, int endPoint) {
        return null;
    }

    public List<Integer> breadthFirstTravers(int endPoint, Queue<Integer> waitQueue,
                                             Set<Integer> loopedPoints) {

        return null;
    }

    /**
     * 广度优先遍历，但是如何确认遍历的路径呢？？
     * 这一点我不会写啊
     * todo: 记录最短路径
     * @param beginPoint
     * @param endPoint
     * @return
     */
    @Override
    public List<Integer> breadthFirstRoad(int beginPoint, int endPoint) {
        // 路径、以及遍历过的点的集合 以及 循环等待的队列
        Stack<Integer> roads = new Stack<>();
        Set<Integer> loopedPoints = new TreeSet<>();
        Queue<Integer> waitQueue = new ArrayDeque<>();

        waitQueue.add(beginPoint);
        while (!waitQueue.isEmpty()){
            // 取出队列中的第一个元素
            Integer currentPoint = waitQueue.remove();
            roads.push(currentPoint);

            // 遍历和当前点的连通点
            for (Integer point : adjacencySet[currentPoint]) {
                if (point.equals(endPoint)){
                    return roads;
                } else if (loopedPoints.contains(point)) {
                    continue;
                }else {
                    loopedPoints.add(point);
                    waitQueue.add(point);
                }
            }
        }
        return null;
    }
}
