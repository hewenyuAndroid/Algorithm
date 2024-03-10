package com.hwy.kotlin;

import java.util.ArrayList;

/**
 * 回文链表
 * <p>
 * https://leetcode.cn/problems/palindrome-linked-list/description/
 * <p>
 * 给你一个单链表的头节点 head ，请你判断该链表是否为
 * 回文链表
 * 。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 输入：head = [1,2,2,1]
 * 输出：true
 * <p>
 * 输入：head = [1,2]
 * 输出：false
 */
public class CH234 {

    public static class ListNode {
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

    public static boolean isPalindrome(ListNode head) {
        /*
        方案1: 将链表的数据转换到集合中，针对集合使用双指针的判断是否是回文链表
         */
        if (head == null) {
            return false;
        }
        ArrayList<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int posFist = 0;
        int posLast = list.size() - 1;
        if (posFist == posLast) {
            return true;
        }

        while (posFist < posLast) {
            if (list.get(posFist) != list.get(posLast)) {
                return false;
            }
            posFist++;
            posLast--;
        }
        return true;
    }

    public static boolean isPalindrome2(ListNode head) {
        /*
        方案2:
        1. 使用快慢指针，分隔链表
        2. 反转后半段链表后判断两段链表
         */
        if (head == null) {
            return false;
        }

        // 1. 使用快慢指针分隔链表
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 2. 反转后面半段的链表
        ListNode temp = null;
        ListNode prev = null;
        ListNode node = slow.next;
        while (node != null) {
            temp = node.next;
            node.next = prev;
            prev = node;
            node = temp;
        }

        // 3. 比较两段链表，这里只需要判断 prev != null 即可
        while (prev != null) {
            if (prev.val != head.val) {
                return false;
            }
            prev = prev.next;
            head = head.next;
        }

        return true;
    }

}
