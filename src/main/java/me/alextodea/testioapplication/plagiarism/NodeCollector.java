package me.alextodea.testioapplication.plagiarism;

import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.type.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class NodeCollector extends VoidVisitorAdapter<List<String>> {

    @Override
    public void visit(AnnotationDeclaration annotationDeclaration, List<String> collector) {
        super.visit(annotationDeclaration, collector);
        collector.add("AnnotationDeclaration");
    }

    @Override
    public void visit(AnnotationMemberDeclaration annotationDeclaration, List<String> collector) {
        super.visit(annotationDeclaration, collector);
        collector.add("AnnotationMemberDeclaration");
    }

    @Override
    public void visit(ArrayAccessExpr arrayAccessExpr, List<String> collector) {
        super.visit(arrayAccessExpr, collector);
        collector.add("ArrayAccessExpr");
    }

    @Override
    public void visit(ArrayCreationExpr arrayCreationExpr, List<String> collector) {
        super.visit(arrayCreationExpr, collector);
        collector.add("ArrayCreationExpr");
    }

    @Override
    public void visit(ArrayCreationLevel arrayCreationLevel, List<String> collector) {
        super.visit(arrayCreationLevel, collector);
        collector.add("ArrayCreationLevel");
    }

    @Override
    public void visit(ArrayInitializerExpr arrayInitializerExpr, List<String> collector) {
        super.visit(arrayInitializerExpr, collector);
        collector.add("ArrayInitializerExpr");
    }

    @Override
    public void visit(ArrayType arrayType, List<String> collector) {
        super.visit(arrayType, collector);
        collector.add("ArrayType");
    }

    @Override
    public void visit(AssertStmt assertStmt, List<String> collector) {
        super.visit(assertStmt, collector);
        collector.add("AssertStmt");
    }

    @Override
    public void visit(AssignExpr assignExpr, List<String> collector) {
        super.visit(assignExpr, collector);
        collector.add("AssignExpr");
    }

    @Override
    public void visit(BinaryExpr binaryExpr, List<String> collector) {
        super.visit(binaryExpr, collector);
        collector.add("BinaryExpr");
    }

    @Override
    public void visit(BlockComment blockComment, List<String> collector) {
        super.visit(blockComment, collector);
        collector.add("BlockComment");
    }

    @Override
    public void visit(BlockStmt blockStmt, List<String> collector) {
        super.visit(blockStmt, collector);
        collector.add("BlockStmt");
    }

    @Override
    public void visit(BooleanLiteralExpr booleanLiteralExpr, List<String> collector) {
        super.visit(booleanLiteralExpr, collector);
        collector.add("BooleanLiteralExpr");
    }

    @Override
    public void visit(BreakStmt breakStmt, List<String> collector) {
        super.visit(breakStmt, collector);
        collector.add("BreakStmt");
    }

    @Override
    public void visit(CastExpr castExpr, List<String> collector) {
        super.visit(castExpr, collector);
        collector.add("CastExpr");
    }

    @Override
    public void visit(CatchClause catchClause, List<String> collector) {
        super.visit(catchClause, collector);
        collector.add("CatchClause");
    }

    @Override
    public void visit(CharLiteralExpr charLiteralExpr, List<String> collector) {
        super.visit(charLiteralExpr, collector);
        collector.add("CharLiteralExpr");
    }

    @Override
    public void visit(ClassExpr classExpr, List<String> collector) {
        super.visit(classExpr, collector);
        collector.add("ClassExpr");
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, List<String> collector) {
        super.visit(classOrInterfaceDeclaration, collector);
        collector.add("ClassOrInterfaceDeclaration");
    }

    @Override
    public void visit(ClassOrInterfaceType classOrInterfaceType, List<String> collector) {
        super.visit(classOrInterfaceType, collector);
        collector.add("ClassOrInterfaceType");
    }

    @Override
    public void visit(CompilationUnit compilationUnit, List<String> collector) {
        super.visit(compilationUnit, collector);
        collector.add("CompilationUnit");
    }

    @Override
    public void visit(ConditionalExpr conditionalExpr, List<String> collector) {
        super.visit(conditionalExpr, collector);
        collector.add("ConditionalExpr");
    }

    @Override
    public void visit(ConstructorDeclaration constructorDeclaration, List<String> collector) {
        super.visit(constructorDeclaration, collector);
        collector.add("ConstructorDeclaration");
    }

    @Override
    public void visit(ContinueStmt continueStmt, List<String> collector) {
        super.visit(continueStmt, collector);
        collector.add("ContinueStmt");
    }

    @Override
    public void visit(DoStmt doStmt, List<String> collector) {
        super.visit(doStmt, collector);
        collector.add("DoStmt");
    }

    @Override
    public void visit(DoubleLiteralExpr doubleLiteralExpr, List<String> collector) {
        super.visit(doubleLiteralExpr, collector);
        collector.add("DoubleLiteralExpr");
    }

    @Override
    public void visit(EnclosedExpr enclosedExpr, List<String> collector) {
        super.visit(enclosedExpr, collector);
        collector.add("EnclosedExpr");
    }

    @Override
    public void visit(EnumConstantDeclaration enumConstantDeclaration, List<String> collector) {
        super.visit(enumConstantDeclaration, collector);
        collector.add("EnumConstantDeclaration");
    }

    @Override
    public void visit(EnumDeclaration enumDeclaration, List<String> collector) {
        super.visit(enumDeclaration, collector);
        collector.add("EnumDeclaration");
    }

    @Override
    public void visit(ExplicitConstructorInvocationStmt explicitConstructorInvocationStmt, List<String> collector) {
        super.visit(explicitConstructorInvocationStmt, collector);
        collector.add("ExplicitConstructorInvocationStmt");
    }

    @Override
    public void visit(ExpressionStmt expressionStmt, List<String> collector) {
        super.visit(expressionStmt, collector);
        collector.add("ExpressionStmt");
    }

    @Override
    public void visit(FieldAccessExpr fieldAccessExpr, List<String> collector) {
        super.visit(fieldAccessExpr, collector);
        collector.add("FieldAccessExpr");
    }

    @Override
    public void visit(FieldDeclaration fieldDeclaration, List<String> collector) {
        super.visit(fieldDeclaration, collector);
        collector.add("FieldDeclaration");
    }

    @Override
    public void visit(ForEachStmt forEachStmt, List<String> collector) {
        super.visit(forEachStmt, collector);
        collector.add("ForEachStmt");
    }

    @Override
    public void visit(ForStmt forstmt, List<String> collector) {
        super.visit(forstmt, collector);
        collector.add("ForStmt");
    }

    @Override
    public void visit(IfStmt ifStmt, List<String> collector) {
        super.visit(ifStmt, collector);
        collector.add("IfStmt");
    }

    @Override
    public void visit(ImportDeclaration importDeclaration, List<String> collector) {
        super.visit(importDeclaration, collector);
        collector.add("ImportDeclaration");
    }

    @Override
    public void visit(InitializerDeclaration initializerDeclaration, List<String> collector) {
        super.visit(initializerDeclaration, collector);
        collector.add("InitializerDeclaration");
    }

    @Override
    public void visit(InstanceOfExpr instanceOfExpr, List<String> collector) {
        super.visit(instanceOfExpr, collector);
        collector.add("InstanceOfExpr");
    }

    @Override
    public void visit(IntegerLiteralExpr integerLiteralExpr, List<String> collector) {
        super.visit(integerLiteralExpr, collector);
        collector.add("IntegerLiteralExpr");
    }

    @Override
    public void visit(IntersectionType intersectionType, List<String> collector) {
        super.visit(intersectionType, collector);
        collector.add("IntersectionType");
    }

    @Override
    public void visit(JavadocComment javadocComment, List<String> collector) {
        super.visit(javadocComment, collector);
        collector.add("JavadocComment");
    }

    @Override
    public void visit(LabeledStmt labeledStmt, List<String> collector) {
        super.visit(labeledStmt, collector);
        collector.add("LabeledStmt");
    }

    @Override
    public void visit(LambdaExpr lambdaExpr, List<String> collector) {
        super.visit(lambdaExpr, collector);
        collector.add("LambdaExpr");
    }

    @Override
    public void visit(LineComment lineComment, List<String> collector) {
        super.visit(lineComment, collector);
        collector.add("LineComment");
    }

    @Override
    public void visit(LocalClassDeclarationStmt localClassDeclarationStmt, List<String> collector) {
        super.visit(localClassDeclarationStmt, collector);
        collector.add("LocalClassDeclarationStmt");
    }

    @Override
    public void visit(LongLiteralExpr longLiteralExpr, List<String> collector) {
        super.visit(longLiteralExpr, collector);
        collector.add("LongLiteralExpr");
    }

    @Override
    public void visit(MarkerAnnotationExpr markerAnnotationExpr, List<String> collector) {
        super.visit(markerAnnotationExpr, collector);
        collector.add("MarkerAnnotationExpr");
    }

    @Override
    public void visit(MemberValuePair memberValuePair, List<String> collector) {
        super.visit(memberValuePair, collector);
        collector.add("MemberValuePair");
    }

    @Override
    public void visit(MethodCallExpr methodCallExpr, List<String> collector) {
        super.visit(methodCallExpr, collector);
        collector.add("MethodCallExpr");
    }

    @Override
    public void visit(MethodDeclaration methodDeclaration, List<String> collector) {
        super.visit(methodDeclaration, collector);
        collector.add("MethodDeclaration");
    }

    @Override
    public void visit(MethodReferenceExpr methodReferenceExpr, List<String> collector) {
        super.visit(methodReferenceExpr, collector);
        collector.add("MethodReferenceExpr");
    }

    @Override
    public void visit(Name name, List<String> collector) {
        super.visit(name, collector);
        collector.add("Name");
    }

    @Override
    public void visit(NameExpr nameExpr, List<String> collector) {
        super.visit(nameExpr, collector);
        collector.add("NameExpr");
    }

    @Override
    public void visit(NodeList nodeList, List<String> collector) {
        super.visit(nodeList, collector);
        collector.add("NodeList");
    }

    @Override
    public void visit(NormalAnnotationExpr normalAnnotationExpr, List<String> collector) {
        super.visit(normalAnnotationExpr, collector);
        collector.add("NormalAnnotationExpr");
    }

    @Override
    public void visit(NullLiteralExpr nullLiteralExpr, List<String> collector) {
        super.visit(nullLiteralExpr, collector);
        collector.add("NullLiteralExpr");
    }

    @Override
    public void visit(ObjectCreationExpr objectCreationExpr, List<String> collector) {
        super.visit(objectCreationExpr, collector);
        collector.add("ObjectCreationExpr");
    }

    @Override
    public void visit(PackageDeclaration packageDeclaration, List<String> collector) {
        super.visit(packageDeclaration, collector);
        collector.add("PackageDeclaration");
    }

    @Override
    public void visit(Parameter parameter, List<String> collector) {
        super.visit(parameter, collector);
        collector.add("Parameter");
    }

    @Override
    public void visit(PrimitiveType primitiveType, List<String> collector) {
        super.visit(primitiveType, collector);
        collector.add("PrimitiveType");
    }

    @Override
    public void visit(ReturnStmt returnStmt, List<String> collector) {
        super.visit(returnStmt, collector);
        collector.add("ReturnStmt");
    }

    @Override
    public void visit(SimpleName simpleName, List<String> collector) {
        super.visit(simpleName, collector);
        collector.add("SimpleName");
    }

    @Override
    public void visit(SingleMemberAnnotationExpr singleMemberAnnotationExpr, List<String> collector) {
        super.visit(singleMemberAnnotationExpr, collector);
        collector.add("SingleMemberAnnotationExpr");
    }

    @Override
    public void visit(StringLiteralExpr stringLiteralExpr, List<String> collector) {
        super.visit(stringLiteralExpr, collector);
        collector.add("StringLiteralExpr");
    }

    @Override
    public void visit(SuperExpr superExpr, List<String> collector) {
        super.visit(superExpr, collector);
        collector.add("ReturnStmt");
    }

    @Override
    public void visit(SwitchEntry switchEntry, List<String> collector) {
        super.visit(switchEntry, collector);
        collector.add("SwitchEntry");
    }

    @Override
    public void visit(SwitchStmt switchStmt, List<String> collector) {
        super.visit(switchStmt, collector);
        collector.add("SwitchStmt");
    }

    @Override
    public void visit(SynchronizedStmt synchronizedStmt, List<String> collector) {
        super.visit(synchronizedStmt, collector);
        collector.add("SynchronizedStmt");
    }

    @Override
    public void visit(ThisExpr thisExpr, List<String> collector) {
        super.visit(thisExpr, collector);
        collector.add("ThisExpr");
    }

    @Override
    public void visit(ThrowStmt throwStmt, List<String> collector) {
        super.visit(throwStmt, collector);
        collector.add("ThrowStmt");
    }

    @Override
    public void visit(TryStmt tryStmt, List<String> collector) {
        super.visit(tryStmt, collector);
        collector.add("TryStmt");
    }

    @Override
    public void visit(TypeExpr typeExpr, List<String> collector) {
        super.visit(typeExpr, collector);
        collector.add("TypeExpr");
    }

    @Override
    public void visit(TypeParameter typeParameter, List<String> collector) {
        super.visit(typeParameter, collector);
        collector.add("TypeParameter");
    }

    @Override
    public void visit(UnaryExpr unaryExpr, List<String> collector) {
        super.visit(unaryExpr, collector);
        collector.add("UnaryExpr");
    }

    @Override
    public void visit(UnionType unionType, List<String> collector) {
        super.visit(unionType, collector);
        collector.add("UnionType");
    }

    @Override
    public void visit(UnknownType unknownType, List<String> collector) {
        super.visit(unknownType, collector);
        collector.add("UnknownType");
    }

    @Override
    public void visit(VariableDeclarationExpr variableDeclarationExpr, List<String> collector) {
        super.visit(variableDeclarationExpr, collector);
        collector.add("VariableDeclarationExpr");
    }

    @Override
    public void visit(VariableDeclarator variableDeclarator, List<String> collector) {
        super.visit(variableDeclarator, collector);
        collector.add("VariableDeclarator");
    }

    @Override
    public void visit(VoidType voidType, List<String> collector) {
        super.visit(voidType, collector);
        collector.add("VoidType");
    }

    @Override
    public void visit(WhileStmt whileStmt, List<String> collector) {
        super.visit(whileStmt, collector);
        collector.add("WhileStmt");
    }

    @Override
    public void visit(WildcardType wildcardType, List<String> collector) {
        super.visit(wildcardType, collector);
        collector.add("WildcardType");
    }

}