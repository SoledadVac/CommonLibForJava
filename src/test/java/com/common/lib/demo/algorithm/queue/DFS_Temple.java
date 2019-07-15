package com.common.lib.demo.algorithm.queue;


import com.google.common.collect.Lists;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.Test;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/7/12
 * \* Time: 27:00 PM
 * \* Description: 深度优先搜索 - DFS
 * \
 */
public class DFS_Temple {


    /*
     * 基于递归的模板
     */
    boolean DFS(Node cur, Node target, Set<Node> visited) {
        if (cur == target) {
            return true;
        }
        if (cur.tails == null || cur.tails.size() < 1) {
            return false;
        }
        for (Node n : cur.tails) {
            visited.add(n);
            if (DFS(n, target, visited)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 基于stack的模板
     *
     * @param cur
     * @param target
     * @return
     */
    boolean DFS1(Node cur, Node target) {
        Set<Node> visited = new HashSet<>();
        Stack<Node> stack = new Stack();
        stack.push(cur);
        while (!stack.isEmpty()) {
            Node temp = stack.pop();
            if (temp == target) {
                return true;
            }
            for (Node n : temp.tails) {
                if (!visited.contains(n)) {
                    visited.add(n);
                    stack.push(n);
                }
            }
        }
        return false;
    }

    @Getter
    @Setter
    @EqualsAndHashCode
    @NoArgsConstructor
    class Node implements Serializable {
        private static final long serialVersionUID = -3257653412863893211L;
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
    public void test() {
        Set<Node> visited = new HashSet<>();
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
        boolean result = DFS1(nodeA, nodeG);
        System.out.println("result=" + result);

    }

}
