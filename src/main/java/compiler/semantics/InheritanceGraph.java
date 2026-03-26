package compiler.semantics;
import java.util.HashSet;


public class InheritanceGraph {

    private record Edge(String e1, String e2) {}
    private HashSet<String> nodes;
    private HashSet<Edge> edges;

    public InheritanceGraph() {
        nodes = new HashSet<>();
        edges = new HashSet<>();
    }

    public void addEdge(String e1, String e2) {
        edges.add(new Edge(e1, e2));
        nodes.add(e1);
        nodes.add(e2);
    }

    public boolean hasEdge(String e1, String e2) {
        return edges.contains(new Edge(e1, e2));
    }

    public void addNode(String n) {
        nodes.add(n);
    }

    public boolean hasNode(String n) {
        return nodes.contains(n);
    }

    public int numEdges() {
        return edges.size();
    }

    public int numNodes() {
        return nodes.size();
    }
    
}
