## 循环队列
https://www.jianshu.com/p/6b88855017d5

[ 1, 1, null, null]

添加元素就是
[1, 1, 1, null]

删除元素就是
[null, 1, 1, null]

定义:
- front为队列首部在底层数组中的索引
- tail 为队列尾部在底层数组中的索引 + 1 

  之所以tail 是 + 1的，只为了方便表示数组为空的状态

一开始队列为空
那么front == 0, tail == 0
我们通过这种方式表示队列为空

然后添加一个元素
- front 依旧为0，代表队列第一个元素的索引
- tail 为1，代表队列最后一个元素的下一个位置
- tail - front 为队列的size


front == tail 说明数组为空

front < tail 的时候队列处于普通的状态

tail < front 的时候说明发生了一次尾部跳转

tail + 1 == front || tail + 1 = data.length 时，说明数组满了

如果允许底层数组满负载，那么满负载的状态下tail 和 front就重叠了
或之前队列为空比较相识，我们不得不额外的做出判断

所以这里底层数组刻意的浪费一个空间
