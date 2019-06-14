package com.github.fish56.array;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.junit.Assert.assertTrue;

public class ArrayTest {
    private static int loopTimes = 30 * 1000;

    private Array<Integer> array;
    @Before
    public void init(){
        array = new Array<Integer>(20);
    }

    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    @Test
    public void getCapacity() {
        assertTrue(array.getCapacity() == 20);
        assertTrue(array.isEmpty());
        array.addFirst(111);
        array.addLast(222);

        System.out.println(array);
        // 现在底层数组应该是这样的：[111, 222]
        assertTrue(array.getCapacity() == 20);
        assertTrue(array.getSize() == 2);
    }

    /**
     * 尾部添加是O(1)复杂度
     */
    @BenchmarkOptions(benchmarkRounds = 3)
    @Test
    public void addLast() {
        for (int i = 0; i < loopTimes; i++) {
            array.addLast(i);
        }
    }
    // round: 0.02 [+- 0.02]

    /**
     * 首部添加O(n)复杂度
     */
    @BenchmarkOptions(benchmarkRounds = 10)
    @Test
    public void addFirst() {
        for (int i = 0; i < loopTimes; i++) {
            array.addFirst(i);
        }
    }
    // round: 1.03

    /**
     * 中间插入也是O(n)复杂度
     */
    @BenchmarkOptions(benchmarkRounds = 10)
    @Test
    public void insertMiddle() {
        for (int i = 0; i < loopTimes; i++) {
            array.insert(i, array.getSize() / 2);
        }
    }
    // round: 0.54

    @Test
    public void findIndex() {
        array.addLast(3);
        assertTrue(array.indexOf(3) == 0);
    }

    /**
     * 尾部删除是O(1)复杂度
     */
    @Test
    public void remove() {
        for (int i = 0; i < loopTimes; i++) {
            array.addLast(i);
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < loopTimes; i++) {
            array.remove(array.getSize() - 1);
        }
        System.out.println("尾部删除: " + (System.currentTimeMillis() - start));
    }
    // 尾部删除: 1

    @Test
    public void resize(){
        Array<Integer> array = new Array<>(3);
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
            System.out.println(array);
        }
    }
}