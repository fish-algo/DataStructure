
定义二叉堆：有以下特征

- 完全二叉树
- 某个节点的值总是不大于父节点的值

推论： 
- 根节点是最大值
- 底层节点未必比上层节点小

维持这种数据结构的话，

### 使用数组来实现

根节点为 0
二层节点为 1 ,2
三层节点为 3，4，5，6

对于任意的节点i，有索引的逻辑关系

父节点为 (i -1 ) / 2, 向下取整

左子节点为 i * 2 + 1
右子节点为 2 * i + 2
