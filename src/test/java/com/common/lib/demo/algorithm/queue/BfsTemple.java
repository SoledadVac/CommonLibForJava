package com.common.lib.demo.algorithm.queue;


import com.google.common.collect.Lists;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.Test;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/5/31
 * \* Time: 27:29 PM
 * \* Description:广度优先搜索（BFS）
 * \
 */
public class BfsTemple {
    /**
     * Return the length of the shortest path between root and target node.
     */

    /**
     int BFS(Node root, Node target) {
     Queue<Node> queue;  // store all nodes which are waiting to be processed
     int step = 0;       // number of steps neeeded from root to current node
     // initialize add root to queue;
     // BFS
     while (queue is not empty) {
     step = step + 1;
     // iterate the nodes which are already in the queue
     int size = queue.size();
     for (int i = 0; i < size; ++i) {
     Node cur = the first node in queue;
     return step if cur is target;
     for (Node next : the neighbors of cur) {
     add next to queue;
     }
     remove the first node from queue;
     }
     }
     return -1;          // there is no path from root to target
     }*/


    /**
     * Return the length of the shortest path between root and target node.
     */
    /**
     * int BFS(Node root, Node target) {
     * Queue<Node> queue;  // store all nodes which are waiting to be processed
     * Set<Node> used;     // store all the used nodes
     * int step = 0;       // number of steps neeeded from root to current node
     * // initialize
     * add root to queue;
     * add root to used;
     * // BFS
     * while (queue is not empty) {
     * step = step + 1;
     * // iterate the nodes which are already in the queue
     * int size = queue.size();
     * for (int i = 0; i < size; ++i) {
     * Node cur = the first node in queue;
     * return step if cur is target;
     * for (Node next : the neighbors of cur) {
     * if (next is not in used) {
     * add next to queue;
     * add next to used;
     * }
     * }
     * remove the first node from queue;
     * }
     * }
     * return -1;          // there is no path from root to target
     * }
     */


    int BFS(Node root, Node target) {
        Queue<Node> queue = new LinkedBlockingQueue();  // store all nodes which are waiting to be processed
        int step = 0;       // number of steps neeeded from root to current node
        queue.add(root);
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (cur.equals(target)) {
                    return step;
                }
                if (cur.tails != null) {
                    for (Node node : cur.tails) {
                        queue.add(node);
                    }
                }
            }
        }
        return -1;
    }

    /**
     * 避免一个节点访问两次,单独检查访问过的结点
     *
     * @param root
     * @param target
     * @return
     */
    int BFS1(Node root, Node target) {
        Queue<Node> queue = new LinkedBlockingQueue();  // store all nodes which are waiting to be processed
        Set<Node> userd = new HashSet<>(); // node that be used
        int step = 0;       // number of steps neeeded from root to current node
        queue.add(root);
        userd.add(root);
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (cur.equals(target)) {
                    return step;
                }
                if (cur.tails != null) {
                    for (Node node : cur.tails) {
                        if (!userd.contains(node)) {
                            queue.add(node);
                            userd.add(node);
                        }
                    }
                }
            }
        }
        return -1;
    }


    @Getter
    @Setter
    @EqualsAndHashCode
    @NoArgsConstructor
    class Node implements Serializable {
        private static final long serialVersionUID = 3687337665231315466L;
        String value;
        Collection<Node> previews;
        Collection<Node> tails;

        public Node(String value, Collection<Node> previews, Collection<Node> tails) {
            this.value = value;
        }

        public Node(String value) {
            this.value = value;
        }
    }


    @Test
    public void testBFS() {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B", Lists.newArrayList(nodeA), null);
        Node nodeC = new Node("C", Lists.newArrayList(nodeA), null);
        Node nodeD = new Node("D", Lists.newArrayList(nodeA), null);
        nodeA.setPreviews(null);
        nodeA.setTails(Lists.newArrayList(nodeB, nodeC, nodeD));
        Node nodeE = new Node("E", Lists.newArrayList(nodeB, nodeC), null);
        Node nodeF = new Node("F", Lists.newArrayList(nodeC), null);
        nodeC.setTails(Lists.newArrayList(nodeE, nodeF));
        Node nodeG = new Node("G", Lists.newArrayList(nodeD, nodeF), null);
        nodeD.setTails(Lists.newArrayList(nodeG));
        nodeF.setTails(Lists.newArrayList(nodeG));
        int result = BFS1(nodeA, nodeG);
        System.out.println("result=" + result);
    }
}
