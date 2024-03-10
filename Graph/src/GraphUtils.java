/*
 * I attest that the code in this file is entirely my own except for the starter
 * code provided with the assignment and the following exceptions:
 * <Enter all external resources and collaborations here. Note external code may
 * reduce your score but appropriate citation is required to avoid academic
 * integrity violations. Please see the Course Syllabus as well as the
 * university code of academic integrity:
 *  https://catalog.upenn.edu/pennbook/code-of-academic-integrity/ >
 * Signed,
 * Author: YOUR NAME HERE
 * Penn email: <YOUR-EMAIL-HERE@seas.upenn.edu>
 * Date: YYYY-MM-DD
 */

import java.util.List;
import java.util.Set;

public class GraphUtils {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 78327812893L;

    /**
     * Given a graph, this method returns the smallest number of edges from the src
     * node to the dest node, or 0 when src = dest, or −1 for any invalid input.
     * Invalid inputs are defined as: any of graph, src, or dest is null; no path
     * exists from src to dest; any of src or dest do not exist in graph.
     *
     * @param graph directed or undirected graph
     * @param src   source node
     * @param dest  destination node
     * @return the smallest number of edges from the src to dest, or -1 for any
     *         invalid input
     */
    public static int minDistance(Graph graph, String src, String dest) {
        return 0;
    }

    /**
     * Given a graph, a src node contained in graph, and a distance of at least 1,
     * this method returns the set of all nodes, excluding src, for which the
     * smallest number of edges from src to each node is less than or equal to
     * distance; null is returned if there is any invalid input. Invalid inputs are
     * defined as: any of graph or src is null; src is not in graph; distance is
     * less than 1.
     *
     * @param graph    directed or undirected graph
     * @param src      source node
     * @param distance maximum distance from source to the nodes to include in
     *                 output set
     * @return set of all nodes, excluding src, for which the smallest number of
     *         edges from src to each node is less than or equal to distance, or
     *         null on invalid input
     */
    public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {
        return null;
    }

    /**
     * Given a Graph, this method indicates whether the List of node values
     * represents a Hamiltonian Cycle.
     *
     * A Hamiltonian Cycle is a valid path through the graph in which every node
     * in the graph is visited exactly once except for the start and end nodes.
     * The method returns a HamiltonianReport object describing the validity of the
     * Hamiltonian Cycle represented by the input List. For this exercise, a cycle must
     * contain at least 3 nodes.
     *
     * @param g      	The directed or undirected graph to operate on
     * @param values 	The proposed path to test on the graph
     * @return Non-null HamiltonianReport describing if values represent a valid
     * 				 	Hamiltonian cycle of g
     */
    public static HamiltonianReport isHamiltonianCycle(Graph g, List<String> values) {
        return null;
    }
}
