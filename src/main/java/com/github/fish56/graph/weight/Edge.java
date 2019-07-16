package com.github.fish56.graph.weight;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 在有权图中，表示一个边的类
 *
 * 但是这个边怎么设计呢？
 * 要不要有from？毕竟我们在后续的使用中，是用不道的。。。
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Edge {
    /**
     * 一个从from到to的变
     * 如果directed为true，那么就忽略方向问题
     */
    private Integer from;
    private Integer to;
    private Boolean directed;

    /**
     * 两个点之间的距离
     */
    private Double weight;

    /**
     * 获得另一个顶点
     * @param that
     * @return
     */
    public Integer other(Integer that){
        return from.equals(that) ? to : from;
    }
}
