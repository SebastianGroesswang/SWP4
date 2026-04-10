import static org.junit.jupiter.api.Assertions.*;

class AdjacencyMatrixTest {

    @org.junit.jupiter.api.Test
    void addEdge_validEdge_createsEdge() {
        AdjacencyMatrix m = new AdjacencyMatrix(5);
        assertTrue(m.addEdge(1, 2));
        assertTrue(m.hasEdge(1, 2));
    }

    @org.junit.jupiter.api.Test
    void addEdge_negativeSrc_returnsFalse() {
        AdjacencyMatrix m = new AdjacencyMatrix(5);
        assertFalse(m.addEdge(-1, 2));
        assertFalse(m.hasEdge(-1, 2));
    }

    @org.junit.jupiter.api.Test
    void addEdge_negativeDest_returnsFalse() {
        AdjacencyMatrix m = new AdjacencyMatrix(5);
        assertFalse(m.addEdge(1, -2));
        assertFalse(m.hasEdge(1, -2));
    }

    @org.junit.jupiter.api.Test
    void addEdge_srcOutOfBounds_returnsFalse() {
        AdjacencyMatrix m = new AdjacencyMatrix(3);
        assertFalse(m.addEdge(3, 1)); // src == length
        assertFalse(m.hasEdge(3, 1));
    }

    @org.junit.jupiter.api.Test
    void addEdge_destOutOfBounds_returnsFalse() {
        AdjacencyMatrix m = new AdjacencyMatrix(3);
        assertFalse(m.addEdge(1, 3)); // dest == length
        assertFalse(m.hasEdge(1, 3));
    }

    @org.junit.jupiter.api.Test
    void hasEdge_falseWhenNoEdge() {
        AdjacencyMatrix m = new AdjacencyMatrix(4);
        assertFalse(m.hasEdge(1, 2));
    }

    @org.junit.jupiter.api.Test
    void removeEdge_existingEdge_returnsTrueAndRemoves() {
        AdjacencyMatrix m = new AdjacencyMatrix(5);
        m.addEdge(1, 3);
        assertTrue(m.hasEdge(1, 3));
        assertTrue(m.removeEdge(1, 3));
        assertFalse(m.hasEdge(1, 3));
    }

    @org.junit.jupiter.api.Test
    void removeEdge_nonExistingEdge_returnsFalse() {
        AdjacencyMatrix m = new AdjacencyMatrix(5);
        assertFalse(m.removeEdge(1, 3));
    }

    @org.junit.jupiter.api.Test
    void removeEdge_idempotent_afterRemoval_returnsFalse() {
        AdjacencyMatrix m = new AdjacencyMatrix(5);
        m.addEdge(1, 2);
        assertTrue(m.removeEdge(1, 2));
        assertFalse(m.removeEdge(1, 2));
    }

    @org.junit.jupiter.api.Test
    void addEdge_selfLoop_allowedForNonZeroIndex() {
        AdjacencyMatrix m = new AdjacencyMatrix(5);
        assertTrue(m.addEdge(2, 2));
        assertTrue(m.hasEdge(2, 2));
    }

    @org.junit.jupiter.api.Test
    void indexZero_isNotAllowed() {
        AdjacencyMatrix m = new AdjacencyMatrix(5);
        assertFalse(m.addEdge(0, 1));
        assertFalse(m.hasEdge(0, 1));
        assertFalse(m.removeEdge(0, 1));
    }
}

