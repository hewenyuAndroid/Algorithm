package com.hwy.kotlin;

/**
 * 合并K个升序列表
 * <p>
 * https://leetcode.cn/problems/merge-k-sorted-lists/description/
 * <p>
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * <p>
 * 输入：lists = []
 * 输出：[]
 * 输入：lists = [[]]
 * 输出：[]
 * <p>
 * 输入：lists = [[]]
 * 输出：[]
 */
public class CH23 {

    public class ListNode {
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

    public ListNode mergeKLists(ListNode[] lists) {

        /*
        思路: 遍历数组 lists
        1. 依次比较 lists数组中每个头结点的数据大小，得到数值最小的链表 lists[minValue]
        2. 记录 lists[minValue], 然后将当前链表删除头结点 再次进行 lists 数组中所有链表的头节点大小比较
         */

        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode head = null;
        ListNode node = null;

        while (true) {
            // 记录lists链表数组中，头节点值最小的链表的下标，
            int index = -1;
            // 记录 index 下标对应的链表的头节点
            ListNode minNode = null;
            for (int i = 0; i < lists.length; i++) {
                ListNode listNode = lists[i];
                if (listNode == null) {
                    // 如果当前链表已经没有节点了，则跳过下面的比较逻辑
                    continue;
                }
                if (minNode == null || listNode.val < minNode.val) {
                    // 判断是否需要更新本次for循环中值最小的节点
                    minNode = listNode;
                    index = i;
                }
            }
            if (index == -1) {
                // index 为-1表示 lists 数组中所有的链表节点都已经判断完毕，此时需要跳出循环
                break;
            }
            // for 循环已经结束，此时得到了链表中值最小的头结点，此时该节点需要从数组链表中删除
            lists[index] = lists[index].next;
            if (head == null) {
                head = minNode;
                node = minNode;
            } else {
                node.next = minNode;
                node = minNode;
            }
        }

        return head;
    }

}
