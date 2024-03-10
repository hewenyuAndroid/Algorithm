package com.hwy.kotlin;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表2
 * <p>
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 不允许修改 链表。
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点
 * <p>
 * 输入：head = [1], pos = -1
 * 输出：返回 null
 * 解释：链表中没有环。
 */
public class CH142 {

    static class ListNode {
        int val;
        CH142.ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode detectCycle(ListNode head) {
        // 使用hash方式
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return head;
            }
            head = head.next;
        }
        return null;
    }

    public ListNode detectCycle2(ListNode head) {
        // 使用快慢指针
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        // 需要保证至少有三个节点才能形成环
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                // 此时，表示快指针已经追上了慢指针，但不一定是环开始的位置
                // 由于快指针比慢指针快了 step = 1，因此速度可以理解为快一倍
                // 假设:
                // 1. head 到 环开始的距离为 A
                // 2. 环开始的位置到 fast 和 slow 相遇的距离为 B
                // 3. 环 减去 B 的长度为 C
                // 得到如下公式: 2(A + B) = A + B + C + B  => 2倍的慢指针长度跟快指针一样
                // 简化后得到 A = C
                // 因此 head 和 slow 按照 step = 1 的方式往后查询，相交的节点即为环起始位置
                while (head != slow) {
                    head = head.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }

}
