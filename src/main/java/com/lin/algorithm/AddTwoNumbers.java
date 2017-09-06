package com.lin.algorithm;

public class AddTwoNumbers {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    /**
     * Just like how you would sum two numbers on a piece of paper, we begin by summing the least-significant digits, which is the head of l1l1 and l2l2. Since each digit is in the range of 0 \ldots 90…9, summing two digits may "overflow". For example 5 + 7 = 125+7=12. In this case, we set the current digit to 22 and bring over the carry = 1carry=1 to the next iteration. carrycarry must be either 00 or 11 because the largest possible sum of two digits (including the carry) is 9 + 9 + 1 = 199+9+1=19.
     *
     * The pseudocode is as following:
     *
     * Initialize current node to dummy head of the returning list.
     * Initialize carry to 00.
     * Initialize pp and qq to head of l1l1 and l2l2 respectively.
     * Loop through lists l1l1 and l2l2 until you reach both ends.
     * Set xx to node pp's value. If pp has reached the end of l1l1, set to 00.
     * Set yy to node qq's value. If qq has reached the end of l2l2, set to 00.
     * Set sum = x + y + carrysum=x+y+carry.
     * Update carry = sum / 10carry=sum/10.
     * Create a new node with the digit value of (sum \bmod 10)(summod10) and set it to current node's next, then advance current node to next.
     * Advance both pp and qq.
     * Check if carry = 1carry=1, if so append a new node with digit 11 to the returning list.
     * Return dummy head's next node.
     * Note that we use a dummy head to simplify the code. Without a dummy head, you would have to write extra conditional statements to initialize the head's value.

     * Complexity Analysis
     *
     * Time complexity : O(\max(m, n))O(max(m,n)). Assume that mm and nn represents the length of l1l1 and l2l2 respectively, the algorithm above iterates at most \max(m, n)max(m,n) times.
     *
     * Space complexity : O(\max(m, n))O(max(m,n)). The length of the new list is at most \max(m,n) + 1max(m,n)+1.
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}
