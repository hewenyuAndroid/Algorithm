package com.hwy.kotlin;

/**
 * 两数相加:
 * <p>
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 */
public class CH02 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 记录两个数据相加后是否有进位
        int offset = 0;
        // 记录根节点
        ListNode rootNode = null;
        // 记录当前的节点
        ListNode currentNode = null;
        while (l1 != null || l2 != null) {
            // 记录两个节点相加的和（带上进位）
            int value = offset;
            ListNode l1Next = null;
            ListNode l2Next = null;
            if (l1 != null) {
                value += l1.val;
                l1Next = l1.next;
            }
            if (l2 != null) {
                value += l2.val;
                l2Next = l2.next;
            }
            offset = value / 10;
            ListNode node = new ListNode(value % 10);
            if (rootNode == null) {
                rootNode = node;
            }

            if (currentNode == null) {
                currentNode = node;
            } else {
                currentNode.next = node;
                currentNode = node;
            }
            l1 = l1Next;
            l2 = l2Next;
        }

        if (offset > 0) {
            // 最后一位计算有进位时，需要单独处理
            currentNode.next = new ListNode(offset);
        }
        return rootNode;
    }

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

}
