import java.util.*;

class Edge {
    int src, dest, weight;

    Edge(int s, int d, int w) {
        src = s;
        dest = d;
        weight = w;
    }
}

public class BellmanFordBMTC {

    static void bellmanFord(List<Edge> edges, int V, int source) {

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[source] = 0;

        for (int i = 1; i < V; i++) {
            for (Edge e : edges) {

                if (dist[e.src] != Integer.MAX_VALUE &&
                    dist[e.src] + e.weight < dist[e.dest]) {

                    dist[e.dest] = dist[e.src] + e.weight;
                }
            }
        }

        for (Edge e : edges) {
            if (dist[e.src] != Integer.MAX_VALUE &&
                dist[e.src] + e.weight < dist[e.dest]) {

                System.out.println("Negative Weight Cycle Found");
                return;
            }
        }

        String[] hubs = {
                "MJC", "KEM", "JAY",
                "KOR", "WHF", "HBR", "MRT"
        };

        System.out.println("Shortest Travel Time from MJC:");

        for (int i = 0; i < V; i++) {
            System.out.println(hubs[i] + " = " + dist[i] + " minutes");
        }
    }

    public static void main(String[] args) {

        int V = 7;

        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0,1,8));    // MJC -> KEM
        edges.add(new Edge(0,2,5));    // MJC -> JAY
        edges.add(new Edge(0,3,12));   // MJC -> KOR
        edges.add(new Edge(2,3,4));    // JAY -> KOR
        edges.add(new Edge(1,5,7));    // KEM -> HBR
        edges.add(new Edge(1,4,10));   // KEM -> WHF
        edges.add(new Edge(3,4,6));    // KOR -> WHF
        edges.add(new Edge(3,6,9));    // KOR -> MRT
        edges.add(new Edge(4,5,3));    // WHF -> HBR
        edges.add(new Edge(5,6,11));   // HBR -> MRT
        edges.add(new Edge(4,6,-3));   // WHF -> MRT

        bellmanFord(edges, V, 0);
    }
}