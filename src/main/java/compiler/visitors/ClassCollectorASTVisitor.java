package compiler.visitors;

import compiler.ast.*;
import compiler.semantics.ClassTable;

public class ClassCollectorASTVisitor extends BaseASTVisitor<Void> {
    public ClassTable classTable;

    public ClassCollectorASTVisitor(ClassTable classTable) {
        this.classTable = classTable;
    }


    @Override
    public Void visitProgram(ProgramNode node) {
        // to visit a program, visit all of its classes
        for (ClassNode c : node.classes) {
            c.accept(this);
        }
        return null;
    }

    @Override
    public Void visitClass(ClassNode node) {
        // to visit a class, add it to the class table
        classTable.addClass(node);
        return null;
    }

}
