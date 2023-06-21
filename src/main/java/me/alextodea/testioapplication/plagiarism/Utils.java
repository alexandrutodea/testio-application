package me.alextodea.testioapplication.plagiarism;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import me.alextodea.testioapplication.model.Submission;

import java.util.*;

public class Utils {

    public static Map<String, Integer> getSubtreeNodeFrequency(Node subtree) {

        NodeCollector nodeCollector = new NodeCollector();
        Map<String, Integer> subtreeNodeFrequency = new HashMap<>();
        List<String> subtreeNodes = new ArrayList<>();
        subtree.accept(nodeCollector, subtreeNodes);

        for (String node : subtreeNodes) {
            subtreeNodeFrequency.putIfAbsent(node, 0);
            Integer currentFrequency = subtreeNodeFrequency.get(node);
            subtreeNodeFrequency.put(node, currentFrequency + 1);
        }

        return subtreeNodeFrequency;
    }

    public static String getNodesString(Node node) {
        NodeCollector nodeCollector = new NodeCollector();
        List<String> nodesString = new ArrayList<>();
        node.accept(nodeCollector, nodesString);
        return String.join(";", nodesString);
    }

    public static double computeSubtreeSimilarity(Node firstSubtree, Node secondSubtree) {

        int l = 0;
        int s = 0;
        int r = 0;

        Map<String, Integer> firstSubtreeNodeFrequency = Utils.getSubtreeNodeFrequency(firstSubtree);
        Map<String, Integer> secondSubtreeNodeFrequency = Utils.getSubtreeNodeFrequency(secondSubtree);
        Map<String, Map<String, Integer>> combinedFrequencies = new HashMap<>();

        Set<Map.Entry<String, Integer>> firstSubtreeEntries = firstSubtreeNodeFrequency.entrySet();
        Set<Map.Entry<String, Integer>> secondSubtreeEntries = secondSubtreeNodeFrequency.entrySet();

        for (Map.Entry<String, Integer> firstSubtreeEntry : firstSubtreeEntries) {
            String key = firstSubtreeEntry.getKey();
            Integer value = firstSubtreeEntry.getValue();
            combinedFrequencies.putIfAbsent(key, new HashMap<>());
            Map<String, Integer> currentFrequency = combinedFrequencies.get(key);
            currentFrequency.put("s1", value);
        }

        for (Map.Entry<String, Integer> secondSubtreeEntry : secondSubtreeEntries) {
            String key = secondSubtreeEntry.getKey();
            Integer value = secondSubtreeEntry.getValue();
            combinedFrequencies.putIfAbsent(key, new HashMap<>());
            Map<String, Integer> currentFrequency = combinedFrequencies.get(key);
            currentFrequency.put("s2", value);
        }

        for (Map.Entry<String, Map<String, Integer>> combinedFrequenciesEntry : combinedFrequencies.entrySet()) {
            Map<String, Integer> frequencyMap = combinedFrequenciesEntry.getValue();
            int s1 = frequencyMap.getOrDefault("s1", 0);
            int s2 = frequencyMap.getOrDefault("s2", 0);

            if (s1 == s2) {
                s += 1;
            } else if (s1 > s2) {
                l += (s1 - s2);
                s += s2;
            } else {
                r += (s2 - s1);
                s += s1;
            }

        }

        return (double) 2 * s / (2 * s + l + r);
    }

    public static boolean isMember(List<ClonePair> clonePairs, Node subtree) {
        for (ClonePair clonePair : clonePairs) {
            if (subtree.equals(clonePair.getLeftMapEntry().getNode())) {
                return true;
            }
        }
        return false;
    }

    public static void removeClonePair(List<ClonePair> clonePairs, Node subtree) {
        clonePairs.removeIf(clonePair -> subtree.equals(clonePair.getLeftMapEntry().getNode()) &&
                                         subtree.getParentNode().isPresent());
    }


}
