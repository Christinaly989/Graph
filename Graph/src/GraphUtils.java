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

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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
    	
    	// if any of the inputs are null, return -1
    	if (graph == null || src == null || dest == null) {return -1;}
    	// if the source or destination nodes do not exist in the graph, return -1
    	if (!graph.containsNode(src) || !graph.containsNode(dest)) {return -1;}
    	// if the source and destination nodes are the same, return 0
    	if (src.equals(dest)) {
			return 0;
		}
    	
        // Use a queue to support BFS
        Queue<String> queue = new LinkedList<>();
        // Map to store the distance of each node from src
        Map<String, Integer> distance = new HashMap<>();

        // Initialize BFS
        queue.add(src);
        distance.put(src, 0);

        while (!queue.isEmpty()) {
            String current = queue.remove();

            // Check all neighbors of the current node
            for (String neighbor : graph.getNodeNeighbors(current)) {
                // If this neighbor hasn't been visited yet
                if (!distance.containsKey(neighbor)) {
                    // Update distance for the neighbor
                    distance.put(neighbor, distance.get(current) + 1);
                    queue.add(neighbor);

                    // If the neighbor is the destination, return the distance
                    if (neighbor.equals(dest)) {
                        return distance.get(neighbor);
                    }
                }
            }
        }

        // If the destination is not reachable from the source
        return -1;
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
        // Check for invalid inputs
        if (graph == null || src == null || distance < 1 || !graph.containsNode(src)) {
            return null;
        }

        Set<String> withinDistance = new HashSet<>();
        Queue<String> toExplore = new LinkedList<>();
        Map<String, Integer> nodeDistance = new HashMap<>();
        Set<String> marked = new HashSet<>();

        // Initialize BFS
        toExplore.add(src);
        marked.add(src);
        nodeDistance.put(src, 0);

        while (!toExplore.isEmpty()) {
            String current = toExplore.remove();
            for (String neighbor : graph.getNodeNeighbors(current)) {
                if (!marked.contains(neighbor)) {
                    marked.add(neighbor);
                    int dist = nodeDistance.get(current) + 1;
                    nodeDistance.put(neighbor, dist);

                    // If the neighbor is within the specified distance, add it to the set
                    if (dist <= distance) {
                        withinDistance.add(neighbor);
                    }

                    toExplore.add(neighbor);
                }
            }
        }

        // Ensure src is not included in the result set
        withinDistance.remove(src);

        return withinDistance;
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
        // Check for null inputs
        if (g == null || values == null) {
            return new HamiltonianReport(HamiltonianReport.Status.NULL_INPUT, null);
        }
        
        // Check for empty values list
		if (values.isEmpty()) {
			return new HamiltonianReport(HamiltonianReport.Status.INVALID_CYCLE, null);
		}

        // Check for invalid graph length or values length
        if (g.getNumNodes() < 3 || values.size() < 3) {
            return new HamiltonianReport(HamiltonianReport.Status.INVALID_LENGTH, null);
        }

        // Check if the first and last nodes are the same, and every node appears exactly once
        
        if (!values.get(0).equals(values.get(values.size() - 1))) {
            return new HamiltonianReport(HamiltonianReport.Status.INVALID_CYCLE, null);
        }

        
        Set<String> visitedNodes = new HashSet<>();

        // Check if all nodes exist in the graph and all consecutive nodes have an edge between them
        for (int i = 0; i < values.size() - 1; i++) {
            String currentNode = values.get(i);
            String nextNode = values.get(i + 1);
            
            // Check for duplicate nodes (excluding the valid repetition of the start/end node)
            if (i != values.size() - 1 && !visitedNodes.add(currentNode)) { // Add returns false if the set already contains the element
                return new HamiltonianReport(HamiltonianReport.Status.INVALID_NODE, currentNode);
            }
            // Check if the current node exists in the graph
            if (!g.containsNode(currentNode)) {
                return new HamiltonianReport(HamiltonianReport.Status.INVALID_NODE, currentNode);
            }

            // Check for an edge between the current and next node
            Set<String> neighbors = g.getNodeNeighbors(currentNode);
            if (!neighbors.contains(nextNode)) {
                return new HamiltonianReport(HamiltonianReport.Status.INVALID_NODE, nextNode);
            }
        }
        
     // Make sure the last node (before repeating the start node) is also checked for existence
        if (!g.containsNode(values.get(values.size() - 1))) {
            return new HamiltonianReport(HamiltonianReport.Status.INVALID_NODE, values.get(values.size() - 1));
        }

        // After adding all nodes from values list (except the repeated start/end node), check if the count matches the graph's node count
        if (visitedNodes.size() != g.getNumNodes()) {
            // The cycle does not include all nodes in the graph
            return new HamiltonianReport(HamiltonianReport.Status.INVALID_CYCLE, null);
        }

        // If all checks passed, it's a valid Hamiltonian Cycle
        return new HamiltonianReport(HamiltonianReport.Status.VALID, null);
    }
}
