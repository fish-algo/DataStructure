package com.github.fish56;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null){
            return head;
        }
        ListNode res = removeElements(head.next, val);

        if (head.val == val){
            return res;
        } else {
            head.next = res;
            return head;
        }
    }
}