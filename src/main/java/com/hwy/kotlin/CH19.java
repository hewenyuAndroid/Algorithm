package com.hwy.kotlin;

/**
 * 删除链表的倒数第N个节点
 * <p>
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * <p>
 * 输入：head = [1], n = 1
 * 输出：[]
 * <p>
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 */
public class CH19 {

    static public class ListNode {
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

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // 使用快慢指针的方式
        // 1. 先让指针 fast 向前移动 n 个位置
        // 2. 此时如果链表长度 < n 直接返回 head
        // 3. fast 从 n 位置开始，slow 和 fast 同时向后遍历，直到 fast.next = null
        // 4. fast.next = null ,表示此时 slow 为 链表的倒数第 n+1个节点
        // 5. 此时， 删除slow 的next 节点即可
        if (head == null) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;
        while (n > 0) {
            if (fast == null) {
                break;
            }
            fast = fast.next;
            n--;
        }

        if (fast == null) {
            // 链表的长度 < n，此时直接删除第一个节点
            return head.next;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;
        return head;
    }


}
