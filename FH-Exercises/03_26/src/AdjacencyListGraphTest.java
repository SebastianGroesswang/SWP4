import static org.junit.jupiter.api.Assertions.*;

class AdjacencyListGraphTest {

    @org.junit.jupiter.api.Test
    void addEdge_validEdge_createsEdge() {
        AdjacencyListGraph g = new AdjacencyListGraph(5);
        assertTrue(g.addEdge(1, 2));
        assertTrue(g.hasEdge(1, 2));
    }

    @org.junit.jupiter.api.Test
    void addEdge_negativeSrc_returnsFalse() {
        AdjacencyListGraph g = new AdjacencyListGraph(5);
        assertFalse(g.addEdge(-1, 2));
        assertFalse(g.hasEdge(-1, 2));
    }

    @org.junit.jupiter.api.Test
    void addEdge_negativeDest_returnsFalse() {
        AdjacencyListGraph g = new AdjacencyListGraph(5);
        assertFalse(g.addEdge(1, -2));
        assertFalse(g.hasEdge(1, -2));
    }

    @org.junit.jupiter.api.Test
    void addEdge_srcOutOfBounds_returnsFalse() {
        AdjacencyListGraph g = new AdjacencyListGraph(3);
        assertFalse(g.addEdge(3, 1)); // src == length
        assertFalse(g.hasEdge(3, 1));
    }

    @org.junit.jupiter.api.Test
    void addEdge_destOutOfBounds_returnsFalse() {
        AdjacencyListGraph g = new AdjacencyListGraph(3);
        assertFalse(g.addEdge(1, 3)); // dest == length
        assertFalse(g.hasEdge(1, 3));
    }

    @org.junit.jupiter.api.Test
    void addEdge_createsListIfNull() {
        AdjacencyListGraph g = new AdjacencyListGraph(4);
        // internal adjacencyList is package-private, we can inspect it here
        assertNull(g.adjacencyList[1]);
        assertTrue(g.addEdge(1, 2));
        assertNotNull(g.adjacencyList[1]);
        assertTrue(g.adjacencyList[1].contains(2));
    }

    @org.junit.jupiter.api.Test
    void addEdge_duplicateEdges_allowedAndContainMultiple() {
        AdjacencyListGraph g = new AdjacencyListGraph(5);
        assertTrue(g.addEdge(1, 2));
        assertTrue(g.addEdge(1, 2)); // duplicate
        // adjacencyList should contain the element at least once
        assertTrue(g.adjacencyList[1].contains(2));
        // size should be 2 because duplicates are added
        assertEquals(2, g.adjacencyList[1].size());
    }

    @org.junit.jupiter.api.Test
    void hasEdge_falseWhenNoEdge() {
        AdjacencyListGraph g = new AdjacencyListGraph(4);
        assertFalse(g.hasEdge(1, 2));
    }

    @org.junit.jupiter.api.Test
    void removeEdge_existingEdge_returnsTrueAndRemoves() {
        AdjacencyListGraph g = new AdjacencyListGraph(5);
        g.addEdge(1, 3);
        assertTrue(g.hasEdge(1, 3));
        assertTrue(g.removeEdge(1, 3));
        assertFalse(g.hasEdge(1, 3));
    }

    @org.junit.jupiter.api.Test
    void removeEdge_nonExistingEdge_returnsFalse() {
        AdjacencyListGraph g = new AdjacencyListGraph(5);
        assertFalse(g.removeEdge(1, 3));
    }

    @org.junit.jupiter.api.Test
    void removeEdge_withDuplicates_removesOnlyOne() {
        AdjacencyListGraph g = new AdjacencyListGraph(5);
        g.addEdge(1, 2);
        g.addEdge(1, 2);
        assertEquals(2, g.adjacencyList[1].size());
        assertTrue(g.removeEdge(1, 2));
        // one duplicate remains
        assertTrue(g.hasEdge(1, 2));
        assertEquals(1, g.adjacencyList[1].size());
    }

    @org.junit.jupiter.api.Test
    void addEdge_selfLoop_allowedForNonZeroIndex() {
        AdjacencyListGraph g = new AdjacencyListGraph(5);
        assertTrue(g.addEdge(2, 2));
        assertTrue(g.hasEdge(2, 2));
    }
}