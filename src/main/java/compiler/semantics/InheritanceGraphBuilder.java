package compiler.semantics;

import compiler.ast.ClassNode;

public class InheritanceGraphBuilder {
    private InheritanceGraph ig;
    
    public InheritanceGraphBuilder() {
        ig = new InheritanceGraph();
    }

    public void buildFromClassTable(ClassTable ct) {
        for (ClassNode node : ct.getAllClasses()) {
            // Get the effective parent
            String parentName = ct.getEffectiveParent(node);
            // Get the parent node
            ClassNode parentNode = ct.lookup(parentName);
            if (parentNode == null) {
                ig.addNode(node.className);
            } else {
                ig.addEdge(node.className, parentName);
            }
        }
    }

    public InheritanceGraph getInheritanceGraph() {
        return ig;
    }
}
