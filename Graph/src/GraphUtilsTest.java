/*
 ***** Important!  Please Read! *****
 *
 *  - Do NOT remove any of the existing import statements
 *  - Do NOT import additional junit packages 
 *  - You MAY add in other non-junit packages as needed
 * 
 *  - Do NOT remove any of the existing test methods or change their name
 *  - You MAY add additional test methods.  If you do, they should all pass
 * 
 *  - ALL of your assert test cases within each test method MUST pass, otherwise the 
 *        autograder will fail that test method
 *  - You MUST write the require number of assert test cases in each test method, 
 *        otherwise the autograder will fail that test method
 *  - You MAY write more than the required number of assert test cases as long as they all pass
 * 
 *  - All of your assert test cases within a method must be related to the method they are meant to test
 *  - All of your assert test cases within a method must be distinct and non-trivial
 *  - Your test cases should reflect the method requirements in the homework instruction specification
 * 
 *  - Your assert test cases will be reviewed by the course instructors and they may take off
 *        points if your assert test cases to do not meet the requirements
 */
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.*;
import org.junit.validator.PublicClassValidator;

class GraphUtilsTest {
	
	
	UndirectedGraph ud_g = GraphBuilder.buildUndirectedGraph("student_graph_test.txt");
	DirectedGraph d_g = GraphBuilder.buildDirectedGraph("student_graph_test.txt");
	
	@Test
	void testMinDistance() {
		/* 
		 * TODO Write at least 5 assert test cases that test your 'minDistance' method
		 * Review the homework instructions and write assert test realated the this methods specification
		 * All 5 assert statements MUST pass.
		 */
		
		// Test 1: graph is null
		DirectedGraph dg = null;
		String src = "1";
		String dest = "0";	
		assertEquals(-1, GraphUtils.minDistance(dg, src, dest));
		
		// Test 2: src or dest is null
		src = null;
		dest = "1";
		assertEquals(-1, GraphUtils.minDistance(ud_g, src, dest));
		src = "1";
		dest = null;
		assertEquals(-1, GraphUtils.minDistance(ud_g, src, dest));
		src = null;
		dest = "1";
		assertEquals(-1, GraphUtils.minDistance(d_g, src, dest));
		src = "1";
		dest = null;
		assertEquals(-1, GraphUtils.minDistance(d_g, src, dest));
		
		// Test 3: src or dest not in graph
		
		src = "1";
		dest = "4";
		assertEquals(-1, GraphUtils.minDistance(ud_g, src, dest));
		src = "5";
		dest = "1";
		assertEquals(-1, GraphUtils.minDistance(d_g, src, dest));
		
		// Test 4: src and dest are the same
		src = "1";
		dest = "1";
		assertEquals(0, GraphUtils.minDistance(ud_g, src, dest));
		assertEquals(0, GraphUtils.minDistance(d_g, src, dest));
		
		
		// Test 5: normal case
		assertEquals(1, GraphUtils.minDistance(ud_g, "0", "3"));
		assertEquals(2, GraphUtils.minDistance(d_g, "0", "3"));
		assertEquals(1, GraphUtils.minDistance(ud_g, "1", "3"));
		assertEquals(2, GraphUtils.minDistance(d_g, "1", "3"));
		assertEquals(1, GraphUtils.minDistance(ud_g, "3", "1"));
		assertEquals(1, GraphUtils.minDistance(d_g, "3", "1"));
		
	}

	@Test
	void testNodesWithinDistance() {
		/* 
		 * TODO Write at least 5 assert test cases that test your 'nodesWithinDistance' method
		 * Review the homework instructions and write assert test realated the this methods specification
		 * All 5 assert statements MUST pass.
		 */

		
		// Test 1: graph is null
		DirectedGraph dg = null;
		String src = "1";
		int distance = 1;
		assertEquals(null, GraphUtils.nodesWithinDistance(dg, src, distance));
		
		// Test 2: src is null
		src = null;
		distance = 1;
		assertEquals(null, GraphUtils.nodesWithinDistance(ud_g, src, distance));
		
		// Test 3: src not in graph
		src = "5";
		distance = 1;
		assertEquals(null, GraphUtils.nodesWithinDistance(ud_g, src, distance));
		
		// Test 4: distance is 0
		src = "1";
		distance = 0;
		assertEquals(null, GraphUtils.nodesWithinDistance(ud_g, src, distance));
		
		// Test 5: normal cases
		src = "0";
		distance = 1;
		String[] expected = {"1", "2", "3"};
		Set<String> expectedSet = new HashSet<String>();
		Collections.addAll(expectedSet, expected);
		assertTrue(compareSet(expectedSet, GraphUtils.nodesWithinDistance(ud_g, src, distance)));
		src = "0";
		distance = 1;
		String[] expected1 = {"1", "2"};
		Set<String> expectedSet1 = new HashSet<String>();
		Collections.addAll(expectedSet1, expected1);
		assertTrue(compareSet(expectedSet1, GraphUtils.nodesWithinDistance(d_g, src, distance)));
		src = "1";
		distance = 1;
		String[] expected2 = {"0", "2"};
		Set<String> expectedSet2 = new HashSet<String>();
		Collections.addAll(expectedSet2, expected2);
		assertTrue(compareSet(expectedSet2, GraphUtils.nodesWithinDistance(d_g, src, distance)));
		src = "3";
		distance = 1;
		String[] expected3 = {"0", "2","1"};
		Set<String> expectedSet3 = new HashSet<String>();
		Collections.addAll(expectedSet3, expected3);
		assertTrue(compareSet(expectedSet3, GraphUtils.nodesWithinDistance(d_g, src, distance)));
	}

	@Test
	void testIsHamiltonianCycle() {
		/* 
		 * TODO Write at least 5 assert test cases that test your 'isHamiltonianCycle' method
		 * Review the homework instructions and write assert test realated the this methods specification
		 * All 5 assert statements MUST pass.
		 */
        // Scenario 1: Graph is null
        List<String> cycle = Arrays.asList("A", "B", "A");
        assertEquals(HamiltonianReport.Status.NULL_INPUT, GraphUtils.isHamiltonianCycle(null, cycle).getStatus(), "Graph is null");

        // Scenario 2: Values list is null
        Graph g = new DirectedGraph(); // Assuming DirectedGraph is properly instantiated
        assertEquals(HamiltonianReport.Status.NULL_INPUT, GraphUtils.isHamiltonianCycle(d_g, null).getStatus(), "Values list is null");
        assertEquals(HamiltonianReport.Status.NULL_INPUT, GraphUtils.isHamiltonianCycle(ud_g, null).getStatus(), "Values list is null");

        // Scenario 3: Invalid length
        List<String> shortCycle = Arrays.asList("1", "2"); // Not enough for a cycle (less than 3)
        assertEquals(HamiltonianReport.Status.INVALID_LENGTH, GraphUtils.isHamiltonianCycle(d_g, shortCycle).getStatus(), "Invalid length");
        assertEquals(HamiltonianReport.Status.INVALID_LENGTH, GraphUtils.isHamiltonianCycle(ud_g, shortCycle).getStatus(), "Invalid length");

        // Scenario 4: Invalid cycle (start and end nodes are not the same)
        List<String> nonCycle = Arrays.asList("1", "2", "3"); // Start and end nodes are different
        assertEquals(HamiltonianReport.Status.INVALID_CYCLE, GraphUtils.isHamiltonianCycle(d_g, nonCycle).getStatus(), "Invalid cycle");
        assertEquals(HamiltonianReport.Status.INVALID_CYCLE, GraphUtils.isHamiltonianCycle(ud_g, nonCycle).getStatus(), "Invalid cycle");

        // Scenario 5: Node does not exist
        List<String> missingNode = Arrays.asList("1", "4", "1"); // Node "D" does not exist in the graph
        assertEquals(HamiltonianReport.Status.INVALID_NODE, GraphUtils.isHamiltonianCycle(d_g, missingNode).getStatus(), "Node does not exist");
        assertEquals(HamiltonianReport.Status.INVALID_NODE, GraphUtils.isHamiltonianCycle(ud_g, missingNode).getStatus(), "Node does not exist");

        // Scenario 6: Edge does not exist
        List<String> missingEdge = Arrays.asList("1", "3", "2", "1"); // Assuming there's no direct edge from 1 to 3
        assertEquals(HamiltonianReport.Status.INVALID_NODE, GraphUtils.isHamiltonianCycle(d_g, missingEdge).getStatus(), "Edge does not exist");

        // Scenario 7: Duplicate node in cycle
        List<String> duplicateNode = Arrays.asList("1", "2", "2", "3", "1"); // Node "2" appears twice (excluding the intentional repeat)
        assertEquals(HamiltonianReport.Status.INVALID_NODE, GraphUtils.isHamiltonianCycle(d_g, duplicateNode).getStatus(), "Duplicate node in cycle");

        // Scenario 8: Valid Hamiltonian cycle
        List<String> validCycle = Arrays.asList("0", "1", "2", "3","0"); // A valid Hamiltonian cycle
        assertEquals(HamiltonianReport.Status.VALID, GraphUtils.isHamiltonianCycle(d_g, validCycle).getStatus(), "Valid Hamiltonian cycle");
    }
	
	public boolean compareSet(Set<String> s1, Set<String> s2) {
        if(s1.size() != s2.size()) {
            return false;
        }
        for(String s : s1) {
            if(!s2.contains(s)) {
                return false;
            }
        }
        return true;
    }
}
