package shuati.day01;
/**
 *   回型链表的第一个交点
 */
public class FirstNode {
    class ListNode{
        int val;
        ListNode next;

        ListNode(int val){
            this.val = val;
            next = null;
        }
    }

    public ListNode getCircularFirst(ListNode head){
        //1.获取环的长度
        int cirlength = getCircularLength(head);
        ListNode fast = head;
        ListNode slow = head;

        //先让快指针走一个环长
        for (int i = 0; i < cirlength; i++) {
            fast = fast.next;
        }
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    //获取环长
    public int getCircularLength(ListNode head){
        if (head == null) {
            return 0;
        }
        ListNode fast = head;
        ListNode slow = head;
        int length = 0;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                fast = fast.next;
                length++;
                while (fast != slow) {
                    fast = fast.next;
                    length++;
                }
            }
        }
        return length;
    }
}
