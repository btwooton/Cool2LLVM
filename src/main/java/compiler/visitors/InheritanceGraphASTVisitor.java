package compiler.visitors;
import compiler.ast.*;
import compiler.semantics.InheritanceGraph;

public class InheritanceGraphASTVisitor implements ASTVisitor<Void> {
    public InheritanceGraph inheritanceGraph;

    public InheritanceGraphASTVisitor() {
        inheritanceGraph = new InheritanceGraph();
    }

    @Override
    public Void visitProgram(ProgramNode node) {
        // To visit a program, visit all of its child classes
        node.classes.forEach(classNode -> classNode.accept(this));
        return null;
    }

    @Override
    public Void visitClass(ClassNode node) {
        // To visit a class, add it and any parent as nodes/edges to the graph
        if (node.parentName != null) {
            inheritanceGraph.addEdge(node.className, node.parentName);
        } else {
            // if no parent, just add class as a node
            inheritanceGraph.addNode(node.className);
        }
        return null;
    }

    @Override
    public Void visitAttributeFeature(AttributeFeatureNode node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitAttributeFeature'");
    }

    @Override
    public Void visitMethodFeature(MethodFeatureNode node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitMethodFeature'");
    }

    @Override
    public Void visitFormal(FormalNode node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitFormal'");
    }

    @Override
    public Void visitAssignExpr(AssignExprNode node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitAssignExpr'");
    }

    @Override
    public Void visitMethodCallExpr(MethodCallExprNode node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitMethodCallExpr'");
    }

    @Override
    public Void visitConditionalExpr(ConditionalExprNode node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitConditionalExpr'");
    }

    @Override
    public Void visitLoopExpr(LoopExprNode node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitLoopExpr'");
    }

    @Override
    public Void visitBlockExpr(BlockExprNode node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitBlockExpr'");
    }

    @Override
    public Void visitLetExpr(LetExprNode node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitLetExpr'");
    }

    @Override
    public Void visitLetBinding(LetBindingNode node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitLetBinding'");
    }

    @Override
    public Void visitCaseExpr(CaseExprNode node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitCaseExpr'");
    }

    @Override
    public Void visitNewExpr(NewExprNode node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitNewExpr'");
    }

    @Override
    public Void visitIsVoidExpr(IsVoidExprNode node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitIsVoidExpr'");
    }

    @Override
    public Void visitBinaryOpExpr(BinaryOpExprNode node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitBinaryOpExpr'");
    }

    @Override
    public Void visitUnaryOpExpr(UnaryOpExprNode node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitUnaryOpExpr'");
    }

    @Override
    public Void visitIdentifierExpr(IdentifierExprNode node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitIdentifierExpr'");
    }

    @Override
    public Void visitLiteralExpr(LiteralExprNode node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitLiteralExpr'");
    }

    @Override
    public Void visitParenExpr(ParenExprNode node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitParenExpr'");
    }

}
