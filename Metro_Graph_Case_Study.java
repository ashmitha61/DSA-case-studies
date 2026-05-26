import java.util.*;

public class Metro_Graph_Case_Study {

    private int V;
    private LinkedList<Integer>[] adj;

    // Metro Station Names
    String[] stations = {
        "Tirupati",
        "Renigunta",
        "Chandragiri",
        "Pakala"
    };

    // Constructor
    Metro_Graph_Case_Study(int v) {

        V = v;

        adj = new LinkedList[v];

        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    // Add Metro Route
    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // BFS Traversal
    void BFS(int s) {

        boolean visited[] = new boolean[V];

        LinkedList<Integer> queue = new LinkedList<>();

        visited[s] = true;

        queue.add(s);

        while (!queue.isEmpty()) {

            s = queue.poll();

            System.out.print(stations[s] + " -> ");

            Iterator<Integer> i = adj[s].listIterator();

            while (i.hasNext()) {

                int n = i.next();

                if (!visited[n]) {

                    visited[n] = true;

                    queue.add(n);
                }
            }
        }
    }

    // DFS Utility
    void DFSUtil(int v, boolean visited[]) {

        visited[v] = true;

        System.out.print(stations[v] + " -> ");

        Iterator<Integer> i = adj[v].listIterator();

        while (i.hasNext()) {

            int n = i.next();

            if (!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }

    // DFS Traversal
    void DFS(int v) {

        boolean visited[] = new boolean[V];

        DFSUtil(v, visited);
    }

    // Main Method
    public static void main(String[] args) {

        Metro_Graph_Case_Study metro =
                new Metro_Graph_Case_Study(4);

        // Metro Routes
        metro.addEdge(0, 1);
        metro.addEdge(0, 2);
        metro.addEdge(1, 2);
        metro.addEdge(2, 0);
        metro.addEdge(2, 3);
        metro.addEdge(3, 3);

        System.out.println("Metro BFS Traversal:");
        metro.BFS(2);

        System.out.println("\n");

        System.out.println("Metro DFS Traversal:");
        metro.DFS(2);
    }
}