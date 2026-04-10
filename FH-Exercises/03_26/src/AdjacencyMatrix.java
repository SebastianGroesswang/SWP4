public class AdjacencyMatrix implements Graph, Printable {

    private final boolean[][] adjacencyMatrix;

    public AdjacencyMatrix(int numVertices) {
        this.adjacencyMatrix = new boolean[numVertices][numVertices];
    }

    private boolean isValidEdge(int src, int dest) {
        return src < adjacencyMatrix.length && src > 0 && dest < adjacencyMatrix.length && dest > 0;
    }

    @Override
    public boolean addEdge(int src, int dest) {
        if (!isValidEdge(src, dest)) {
            return false;
        }
        return adjacencyMatrix[src][dest] = true;
    }

    @Override
    public boolean hasEdge(int src, int dest) {
        return isValidEdge(src, dest) && adjacencyMatrix[src][dest];
    }

    @Override
    public boolean removeEdge(int src, int dest) {
        if (!hasEdge(src, dest)) {
            return false;
        }
         adjacencyMatrix[src][dest] = false;
        return true;
    }

    @Override
    public void print() {

    }
}

