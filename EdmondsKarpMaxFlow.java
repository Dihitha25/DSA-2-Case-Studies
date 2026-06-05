import java.util.*;

public class EdmondsKarpMaxFlow {

    static final int V = 6;

    boolean bfs(int[][] residualGraph, int source, int sink, int[] parent) {
        boolean[] visited = new boolean[V];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (!visited[v] && residualGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return visited[sink];
    }

    int edmondsKarp(int[][] graph, int source, int sink) {

        int[][] residualGraph = new int[V][V];

        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                residualGraph[i][j] = graph[i][j];

        int[] parent = new int[V];

        int maxFlow = 0;

        while (bfs(residualGraph, source, sink, parent)) {

            int pathFlow = Integer.MAX_VALUE;

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residualGraph[u][v]);
            }

            System.out.print("Augmenting Path: ");

            ArrayList<Integer> path = new ArrayList<>();

            for (int v = sink; v != -1; v = parent[v]) {
                path.add(v);
            }

            Collections.reverse(path);

            for (int node : path) {
                System.out.print(getNode(node) + " ");
            }

            System.out.println("\nBottleneck Flow = " + pathFlow);

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residualGraph[u][v] -= pathFlow;
                residualGraph[v][u] += pathFlow;
            }

            maxFlow += pathFlow;

            System.out.println("Current Total Flow = " + maxFlow);
            System.out.println();
        }

        return maxFlow;
    }

    static String getNode(int n) {
        switch (n) {
            case 0: return "S";
            case 1: return "A";
            case 2: return "B";
            case 3: return "C";
            case 4: return "D";
            case 5: return "T";
        }
        return "";
    }

    public static void main(String[] args) {

        int[][] graph = {
                {0,10,10,0,0,0},   // S
                {0,0,2,4,8,0},     // A
                {0,0,0,9,0,0},     // B
                {0,0,0,0,0,10},    // C
                {0,0,0,6,0,10},    // D
                {0,0,0,0,0,0}      // T
        };

        EdmondsKarpMaxFlow maxFlowObj = new EdmondsKarpMaxFlow();

        int maxFlow = maxFlowObj.edmondsKarp(graph, 0, 5);

        System.out.println("Maximum Oil Flow = " + maxFlow + " Kilolitres/Hour");
    }
}