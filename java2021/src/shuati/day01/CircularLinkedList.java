package shuati.day01;
 /*
  *环形链表
  */
public class CircularLinkedList {
    class ListNode{
        int val;
        ListNode next;

        ListNode(int val){
            this.val = val;
            next = null;
        }
    }

    public boolean hasCircular(ListNode head){
        if (head == null){
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                return true;
            }
        }
        return false;
    }
}
