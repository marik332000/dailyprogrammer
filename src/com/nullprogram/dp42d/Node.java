package com.nullprogram.dp42d;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents a vertex in a directed graph.
 */
@RequiredArgsConstructor
public final class Node implements Comparable<Node> {

    /**
     * This node's ID number.
     */
    @Getter
    private final int id;

    /**
     * Connection from this node to others.
     */
    @Getter
    private Set<Node> edges = new HashSet<Node>();

    /**
     * Connect 2-way this node with another.
     * @param node  the other node
     */
    public void connect(final Node node) {
        this.edges.add(node);
        node.edges.add(this);
    }

    /**
     * Connect this node to another.
     * @param node  the other node
     */
    public void direct(final Node node) {
        edges.add(node);
    }

    @Override
    public String toString() {
        return "" + id;
    }

    @Override
    public int compareTo(final Node that) {
        return this.id - that.id;
    }
}
