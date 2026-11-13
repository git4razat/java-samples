package sample2026.walmart.graph;

import java.util.*;

public class WorkFlowDAGValidator {

    /**
     * Validates the DAG, detects cycles, and returns a valid topological sort order.
     */

    public static List<String> getExecutionOrder(List<String> tasks, Map<String, List<String>> dependencies) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();

        // 1. Initialize data structures
        for (String task: tasks) {
            graph.put(task, new ArrayList<>());
            inDegree.put(task, 0);
        }

        // 2. Build the graph and calculate in-degrees
        // If Task B depends on Task A (A -> B), then A points to B.
        for (String task: tasks) {
            List<String> preqs = dependencies.getOrDefault(task, new ArrayList<>());
            for (String preq: preqs) {
                if (!tasks.contains(preq))
                    throw new IllegalArgumentException("Invalid preq , not part of input tasks");
                graph.get(preq).add(task);
                inDegree.put(task, inDegree.get(task)+1);
            }
        }

        // 3. Queue up all nodes with 0 in-degree (no prerequisites)
        Queue<String> preqQueue = new LinkedList<>();
        for (String key: inDegree.keySet()) {
            int inDg = inDegree.get(key);
            if (inDg == 0) {
                preqQueue.add(key);
            }
        }

        List<String> topologicalOrder = new ArrayList<>();

        // 4. process the graph
        while(!preqQueue.isEmpty()) {
            String task = preqQueue.poll();
            topologicalOrder.add(task);
            List<String> nextTasks = graph.get(task);
            for (String nextTask: nextTasks) {
                inDegree.put(nextTask, inDegree.get(nextTask) - 1);
                if (inDegree.get(nextTask) == 0) {
                    preqQueue.add(nextTask);
                }
            }
        }
        // 5. Verify if a cycle exists (if topologicalOrder size doesn't match total tasks)
        if (topologicalOrder.size() != tasks.size()) {
            throw new IllegalStateException("Circular dependency detected in workflow DAG!");
        }
        return topologicalOrder;
    }

    public static void main(String[] args) {
        List<String> tasks = Arrays.asList("TaskA", "TaskB", "TaskC", "TaskD");
        Map<String, List<String>> dependencies = new HashMap<>();
        dependencies.put("TaskC", Arrays.asList("TaskA", "TaskB"));
        dependencies.put("TaskD", Collections.singletonList("TaskC"));

        List<String> order = getExecutionOrder(tasks, dependencies);
        System.out.println("Valid Execution Order: " + order);

        // Test cycle detection
        try {
            dependencies.put("TaskA", Collections.singletonList("TaskD")); // Creates A -> C -> D -> A cycle
            getExecutionOrder(tasks, dependencies);
        } catch (IllegalStateException e) {
            System.out.println("Cycle caught successfully: " + e.getMessage());
        }
    }
}
