import java.util.ArrayList;
import java.util.List;

public class AdjacencyListGraph implements Graph, Printable {

    //normally implemented as hashmap
    final List<Integer>[] adjacencyList;

    public AdjacencyListGraph(int numVertices) {
        this.adjacencyList = new ArrayList[numVertices];
    }

    private boolean isValidEdge(int src, int dest) {
        return src < adjacencyList.length && src > 0 && dest < adjacencyList.length && dest > 0;
    }

    @Override
    public boolean addEdge(int src, int dest) {
        if (!isValidEdge(src, dest)) {
            return false;
        }

        if(this.adjacencyList[src] == null) {
            this.adjacencyList[src] = new ArrayList<>();
        }

        adjacencyList[src].add(dest);
        return this.adjacencyList[src].contains(dest);
    }

    @Override
    public boolean hasEdge(int src, int dest) {
        return isValidEdge(src, dest) && adjacencyList[src] != null && adjacencyList[src].contains(dest);
    }

    @Override
    public boolean removeEdge(int src, int dest) {
        return hasEdge(src, dest) && adjacencyList[src].remove((Object) dest); //overload is interesting
    }

    @Override
    public void print() {

    }
}
