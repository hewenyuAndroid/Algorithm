package com.hwy.kotlin;

/**
 * 合并两个有序链表
 * <p>
 * https://leetcode.cn/problems/merge-two-sorted-lists/description/
 * <p>
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * <p>
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * <p>
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 */
public class CH21 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode first = list1.val < list2.val ? list1 : list2;
        ListNode pC = null;

        while (list1 != null || list2 != null) {
            ListNode minNode = null;
            if (list1 == null) {
                minNode = list2;
                list2 = list2.next;
            } else if (list2 == null) {
                minNode = list1;
                list1 = list1.next;
            } else if (list1.val < list2.val) {
                minNode = list1;
                list1 = list1.next;
            } else {
                minNode = list2;
                list2 = list2.next;
            }

            if (pC == null) {
                pC = minNode;
            } else {
                pC.next = minNode;
                pC = minNode;
            }
        }
        return first;
    }

}
