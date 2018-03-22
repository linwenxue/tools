package com.lin.java.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 树的层次遍历
 */
public class LayerTree {
    Node root;
    static class Node {
        int value;
        Node left, right;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static Node init() {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3, a, b);
        Node d = new Node(4, b, b);
        Node e = new Node(5, c, d);
        return e;
    }


    public static void print(Node node) {
        if(node==null) return ;
        Queue<Node> queue = new ArrayDeque<Node>();
        //队列小知识：使用offer和poll优于add和remove之处在于它们返回值可以判断成功与否，而不抛出异常
        queue.offer(node);              //进入队列
        while(!queue.isEmpty())
        {
            node = queue.poll();           //当前节点出队列
            System.out.print(node.value);
            if(node.left != null)              //当前节点左孩子去排队，在前面哦
                queue.offer(node.left);
            if(node.right != null)            //右孩子排第二
                queue.offer(node.right);
        }
    }


    public static void main(String[] args) {
       print(init());
    }
}




