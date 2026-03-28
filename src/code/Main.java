/**
 * Driver class used to test and demonstrate the Graph implementation.
 * Includes sample graph setup and traversal execution (DFS and BFS).
 *
 * @author Abdullah Asmy
 * @version 1.0
 */
public class Main
{
    /**
     * Main method - entry point for the application.
     *
     * @param args command-line arguments
     */
    public static void main(final String[] args)
    {
        String[] vnames;
        Graph g;

        vnames = new String[]{"a", "b", "c", "d", "e", "f", "g", "h"};

        // Create an undirected graph as per the lecture example
        g = new Graph(vnames, false);

        // Add edges matching the lab manual test case
        g.addEdge("a", "b");
        g.addEdge("a", "e");
        g.addEdge("a", "f");
        g.addEdge("b", "f");
        g.addEdge("b", "g");
        g.addEdge("c", "d");
        g.addEdge("c", "g");
        g.addEdge("d", "h");
        g.addEdge("e", "f");
        g.addEdge("g", "h");

        // 1. Test DFS
        System.out.println("DFS order traversal of graph:");
        g.runDFS(false);

        // 2. Test BFS
        System.out.println("\nBFS traversal of graph:");
        g.runBFS(false);

        // 3. Test the Adjacency Matrix output
        System.out.println("\nAdjacency Matrix:");
        System.out.println(g.toString());
    }
}