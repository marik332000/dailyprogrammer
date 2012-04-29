package com.nullprogram.dp42d;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents a graph of vertexes and edges and is its own node
 * factory. All nodes between 0 and n exist.
 */
public final class Graph implements Iterable<Node> {

    /**
     * Read in a graph from an adjacency list in a file.
     * @param file  file containing edges
     * @return the graph
     * @throws IOException on file access error
     */
    public static Graph fromFile(final File file) throws IOException {
        return fromStream(new FileInputStream(file));
    }

    /**
     * Read in a graph from an adjacency list in a stream.
     * @param in  stream containing edges
     * @return the graph
     * @throws IOException on IO error
     */
    public static Graph fromStream(final InputStream in) throws IOException {
        Graph graph = new Graph();
        Scanner scanner = new Scanner(in);
        scanner.useDelimiter("[^0-9]+");
        while (scanner.hasNextInt()) {
            Node a = graph.get(scanner.nextInt());
            Node b = graph.get(scanner.nextInt());
            a.connect(b);
        }
        in.close();
        return graph;
    }

    /**
     * Collection containing all known nodes, mapped to their IDs.
     */
    private Map<Integer, Node> map = new HashMap<Integer, Node>();

    /**
     * Get a node by ID number.
     * @param n  the node's id
     * @return the node
     */
    public Node get(final int n) {
        Node node = map.get(n);
        if (node == null) {
            node = new Node(n);
            map.put(n, node);

            /* Fill in missing in-between nodes. */
            if (map.size() <= n) {
                for (int i = 0; i < n; i++) {
                    get(i);
                }
            }
        }
        return node;
    }

    /**
     * Add the given node to this graph.
     * @param n  node to add
     */
    public void add(final Node n) {
        map.put(n.getId(), n);
    }

    /**
     * Get all nodes in this graph.
     * @return the set of nodes in this graph
     */
    public Set<Node> getNodes() {
        Set<Node> nodes = new TreeSet<Node>();
        for (Integer i : map.keySet()) {
            nodes.add(map.get(i));
        }
        return nodes;
    }

    @Override
    public Iterator<Node> iterator() {
        return getNodes().iterator();
    }

    @Override
    public String toString() {
        if (map.isEmpty()) {
            return "G()";
        }
        StringBuilder str = new StringBuilder();
        str.append("G(");
        for (Node n : this) {
            str.append(n);
            str.append(" ");
        }
        str.deleteCharAt(str.length() - 1);
        str.append(")");
        return str.toString();
    }
}
