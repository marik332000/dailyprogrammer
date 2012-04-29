package com.nullprogram.dp42d;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {

    /**
     * Run against the challenge itself.
     */
    @Test
    public void sampleTest() throws IOException {
        InputStream in = getClass().getResourceAsStream("sample.txt");
        Graph graph = Graph.fromStream(in);
        GraphSolver solver = new GraphSolver(graph);
        solver.run();

        /* Encode the corrent answer. */
        int[][] correct = {{0, 2, 20, 22, 38, 39, 42, 44, 47},
                           {1, 5, 8, 19, 24, 40},
                           {3},
                           {4, 9, 14, 23, 25, 29, 33, 36, 41},
                           {6, 17, 18, 35, 37, 43, 45, 46},
                           {7, 10, 11, 15, 16, 21, 28, 30, 34, 48, 49},
                           {12, 13, 26, 27, 31, 32}};
        Set<Set<Integer>> subgraphs = new HashSet<Set<Integer>>();
        for (int i = 0; i < correct.length; i++) {
            Set<Integer> subgraph = new HashSet<Integer>();
            for (int n : correct[i]) {
                subgraph.add(n);
            }
            subgraphs.add(subgraph);
        }

        /* Encode the results. */
        Set<Set<Integer>> results = new HashSet<Set<Integer>>();
        for (Graph island : solver.getIslands()) {
            Set<Integer> subgraph = new HashSet<Integer>();
            for (Node n : island) {
                subgraph.add(n.getId());
            }
            results.add(subgraph);
        }

        /* Compare. */
        assertEquals(subgraphs, results);
    }
}
