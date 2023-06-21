package me.alextodea.testioapplication.plagiarism;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import me.alextodea.testioapplication.model.Submission;

public class DetectorEntry {
    private Submission submission;
    private Node node;

    public DetectorEntry(Submission submission, Node node) {
        this.submission = submission;
        this.node = node;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return "DetectorEntry{" +
               "submission=" + submission.getId() +
               ", node=" + node +
               '}';
    }
}
