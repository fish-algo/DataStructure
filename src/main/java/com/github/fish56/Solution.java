package com.github.fish56;

import java.util.*;

class Solution {
    private class Freq implements Comparable<Freq>{
        public int ele;
        public int freq;

        public Freq(int ele, int freq) {
            this.ele = ele;
            this.freq = freq;
        }

        /**
         * 频率高的优先级更加的高
         */
        @Override
        public int compareTo(Freq anther) {
            if (this.freq > anther.freq){
                return 1;
            } else if (this.freq < anther.freq){
                return -1;
            } else {
                return 0;
            }
        }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> eleToFrequency = new HashMap<>();

        // 频率统计: O(N)
        for (int num : nums){
            if (!eleToFrequency.containsKey(num)){
                eleToFrequency.put(num, 1);
            } else {
                eleToFrequency.put(num, eleToFrequency.get(num) + 1);
            }
        }

        // 自带的最小堆
        PriorityQueue<Freq>  pq = new PriorityQueue<>();

        for(Integer key : eleToFrequency.keySet()){
            pq.add(new Freq(key, eleToFrequency.get(key)));

            // 如果队列超过了k,就可以删除当前频率最小元素了的了
            if (pq.size() == k + 1){
                pq.remove();
            }
        }
        LinkedList<Integer> list = new LinkedList<>();
        for (Freq ele : pq){
            list.addFirst(ele.ele);
        }
        return list;
    }
}