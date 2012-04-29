package com.nullprogram.dp42d;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Finds disjoint subgraphs within a graph.
 */
@RequiredArgsConstructor
public final class GraphSolver implements Runnable {

    /**
     * The graph being inspected.
     */
    private final Graph graph;

    /**
     * List of seen nodes.
     */
    private final Set<Node> seen = new HashSet<Node>();

    /**
     * The list of discovered subgraphs.
     */
    @Getter
    private final List<Graph> islands = new ArrayList<Graph>();

    @Override
    public void run() {
        Deque<Queue<Node>> stack = new ArrayDeque<Queue<Node>>();
        for (Node start : graph.getNodes()) {
            if (!seen.contains(start)) {
                Graph island = new Graph();
                islands.add(island);
                stack.push(new LinkedList<Node>(start.getEdges()));
                island.add(start);
                while (!stack.isEmpty()) {
                    Node n = stack.peek().poll();
                    if (n == null) {
                        stack.pop();
                    } else if (!seen.contains(n)) {
                        seen.add(n);
                        island.add(n);
                        stack.push(new LinkedList<Node>(n.getEdges()));
                    }
                }
            }
        }
    }
}
