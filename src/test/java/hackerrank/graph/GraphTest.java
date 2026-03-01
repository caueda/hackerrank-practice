package hackerrank.graph;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    Graph graph;

    @BeforeEach
    void setUp() {
        this.graph = new Graph();
        graph.addVertex("Bob");
        graph.addVertex("Alice");
        graph.addVertex("Mark");
        graph.addVertex("Rob");
        graph.addVertex("Maria");
        graph.addEdge("Bob", "Alice");
        graph.addEdge("Bob", "Rob");
        graph.addEdge("Alice", "Mark");
        graph.addEdge("Rob", "Mark");
        graph.addEdge("Alice", "Maria");
        graph.addEdge("Rob", "Maria");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void depthFirstTraversalTest() {
        assertEquals("[Bob, Rob, Maria, Alice, Mark]", graph.depthFirstTraversal(graph, "Bob").toString());
    }

    @Test
    void breadthFirstTraversalTest() {
        assertEquals(
                "[Bob, Alice, Rob, Mark, Maria]", graph.breadthFirstTraversal(graph, "Bob").toString());
    }
}