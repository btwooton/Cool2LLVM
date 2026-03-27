package compiler.semantics;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
import compiler.ast.ClassNode;


public class InheritanceGraph {

    private HashSet<String> nodes;
    // note: stores edges as "parent": ["child1", "child2", ...]
    private HashMap<String, Set<String>> edges;
    private HashMap<String, String> childToParent;
    private int numEdges;

    public InheritanceGraph() {
        nodes = new HashSet<>();
        edges = new HashMap<>();
        childToParent = new HashMap<>();
        numEdges = 0;
    }

    public void addEdge(String e1, String e2) {
        // initialize edge list if needed
        if (!edges.containsKey(e2)) {
            edges.put(e2, new HashSet<>());
        }
        // add e2 to edge list of e1
        edges.get(e2).add(e1);
        childToParent.put(e1, e2);
        numEdges += 1;
        nodes.add(e1);
        nodes.add(e2);
    }

    public boolean hasEdge(String e1, String e2) {
        return edges.get(e1).contains(e2);
    }

    public void addNode(String n) {
        nodes.add(n);
    }

    public boolean hasNode(String n) {
        return nodes.contains(n);
    }

    public int numEdges() {
        return numEdges;
    }

    public int numNodes() {
        return nodes.size();
    }

    private boolean classInheritsFromForbidden(ClassTable ct, SemanticErrorLogger logger) {
        Set<String> forbiddenParents = Set.of("Int", "Bool", "String");
        boolean classInheritsFromForbidden = false;
        Set<String> childNodes = childToParent.keySet();
        for (String child : childNodes) {
            String parent = childToParent.get(child);
            if (forbiddenParents.contains(parent)) {
                ClassNode childNode = ct.lookup(child);
                logger.log(
                    childNode.lineNumber,
                    "Class " + child + " inherits from forbidden basic class " + parent
                );
                classInheritsFromForbidden = true;
            }
        }
        return classInheritsFromForbidden;
    }

    public boolean isWellFormed(ClassTable ct, SemanticErrorLogger logger) {
        
        boolean undefinedParent = false;

        // initialize a set of all nodes that are ancestors
        Set<String> ancestorNodes = edges.keySet();
        // initialize a queue containing non ancestor nodes
        Queue<String> nonAncestorNodes = new LinkedList<>();
        for (String n : nodes) {
            if (!ancestorNodes.contains(n)) {
                nonAncestorNodes.add(n);
            }
        }
        // initialize a copy of the edges of the graph
        HashMap<String, Set<String>> workingEdges = new HashMap<>();
        for (String key : edges.keySet()) {
            workingEdges.put(key, new HashSet<>(edges.get(key)));
        }
        
        // execute the topological sort
        while (!nonAncestorNodes.isEmpty()) {
            // remove the next node from the nonAncestor nodes
            String currentNode = nonAncestorNodes.poll();
            // now find the unique parent node
            String parentNode = childToParent.get(currentNode);
            // skip if we don't have a parent
            if (parentNode == null) {
                if (!currentNode.equals("Object")) {
                    // We should log an undefined parent error
                    ClassNode currentNodeActual = ct.lookup(currentNode);
                    logger.log(
                        currentNodeActual.lineNumber,
                        "Undefined parent class " + currentNodeActual.parentName + " inherited by " + currentNodeActual.className
                    );
                    undefinedParent = true;
                }
                continue;
            }
            // get list of child nodes of the parentNode
            Set<String> childNodes = workingEdges.get(parentNode);
            childNodes.remove(currentNode);
            // if the parentNode no longer has children, try to add it to the nonAncestors
            if (childNodes.isEmpty()) {
                // add to the non ancestors
                nonAncestorNodes.add(parentNode);
            }
            // remove keys from working edges for any non ancestor nodes
            for (String node : nonAncestorNodes) {
                workingEdges.remove(node);
            }
        }

        // if we still have working edges, we should log semantic errors
        boolean stillHasEdges = !workingEdges.keySet().isEmpty();
        for (String key: workingEdges.keySet()) {
            Set<String> childNodes = workingEdges.get(key);
            for (String child : childNodes) {
                // Get the child class node
                ClassNode childNode = ct.lookup(child);
                StringBuilder errorBuilder = new StringBuilder();
                errorBuilder.append(
                    "Cyclic inheritance relationship between child "
                );
                errorBuilder.append(
                    child
                );
                errorBuilder.append(" and parent ");
                errorBuilder.append(key);

                logger.log(
                    childNode.lineNumber,
                    errorBuilder.toString()
                );
            }
        }
        return !stillHasEdges && !undefinedParent && !classInheritsFromForbidden(ct, logger);
    }
    
}
