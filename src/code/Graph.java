import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a graph using an adjacency matrix.
 * Supports directed and undirected graphs and provides
 * Depth First Search (DFS) and Breadth First Search (BFS) traversal methods.
 *
 * @author Abdullah Asmy (A01446687)
 * @version 1.0
 */
public class Graph
{
    private final String[] vertexLabels;
    private final int[][] adjacencyMatrix;
    private final boolean directed;
    private final int n;

    private List<String> lastDFSOrder;
    private List<String> lastDFSDeadEndOrder;
    private boolean hasRunDFS;

    private List<String> lastBFSOrder;
    private boolean hasRunBFS;

    /**
     * Constructs a Graph object.
     *
     * @param vertexLabels array of Strings representing vertex names
     * @param isDirected   boolean indicating if graph is directed or undirected
     */
    public Graph(final String[] vertexLabels,
                 final boolean isDirected)
    {
        this.n = vertexLabels.length;
        this.vertexLabels = Arrays.copyOf(vertexLabels, n);
        this.directed = isDirected;
        this.adjacencyMatrix = new int[n][n];

        this.hasRunDFS = false;
        this.hasRunBFS = false;
    }

    /**
     * Checks if the graph is directed.
     *
     * @return true if directed, false otherwise
     */
    public boolean isDirected()
    {
        return directed;
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * @return the number of vertices
     */
    public int size()
    {
        return n;
    }

    /**
     * Returns the label of the vertex at the given internal index.
     *
     * @param v the internal index
     * @return the string name of the vertex
     */
    public String getLabel(final int v)
    {
        return vertexLabels[v];
    }

    /**
     * Helper method to find the internal index of a vertex by its label.
     *
     * @param label the string name of the vertex
     * @return the index, or -1 if not found
     */
    private int getIndex(final String label)
    {
        int i;

        for (i = 0; i < n; i++)
        {
            if (vertexLabels[i].equals(label))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds an edge between two vertices.
     * If undirected, sets both directions.
     *
     * @param a the starting vertex label
     * @param b the ending vertex label
     */
    public void addEdge(final String a,
                        final String b)
    {
        int indexA;
        int indexB;

        indexA = getIndex(a);
        indexB = getIndex(b);

        if (indexA != -1 && indexB != -1)
        {
            adjacencyMatrix[indexA][indexB] = 1;

            if (!directed)
            {
                adjacencyMatrix[indexB][indexA] = 1;
            }
        }
    }

    /**
     * Returns a string representation of the adjacency matrix.
     *
     * @return formatted string of the graph
     */
    @Override
    public String toString()
    {
        StringBuilder sb;
        int i;
        int j;

        sb = new StringBuilder();

        for (i = 0; i < n; i++)
        {
            sb.append(vertexLabels[i]).append(":");

            for (j = 0; j < n; j++)
            {
                sb.append(" ").append(adjacencyMatrix[i][j]);
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Performs Depth First Search on the graph.
     * Visits all vertices even if the graph is disconnected.
     *
     * @param quiet if true, suppresses console output
     */
    public void runDFS(final boolean quiet)
    {
        boolean[] visited;
        int i;

        visited = new boolean[n];
        lastDFSOrder = new ArrayList<>();
        lastDFSDeadEndOrder = new ArrayList<>();
        hasRunDFS = true;

        for (i = 0; i < n; i++)
        {
            if (!visited[i])
            {
                dfsHelper(i, visited, quiet);
            }
        }
    }

    /**
     * Performs Depth First Search starting from a specific vertex.
     * Only visits vertices reachable from the starting vertex.
     *
     * @param v     the starting vertex label
     * @param quiet if true, suppresses console output
     */
    public void runDFS(final String v, final boolean quiet)
    {
        int startIndex;
        startIndex = getIndex(v);

        if (startIndex == -1)
        {
            return;
        }
        boolean[] visited;

        visited = new boolean[n];

        lastDFSOrder = new ArrayList<>();
        lastDFSDeadEndOrder = new ArrayList<>();
        hasRunDFS = true;
        dfsHelper(startIndex, visited, quiet);
    }

    /**
     * Recursive helper method for Depth First Search.
     *
     * @param vertexIndex the internal index of the current vertex
     * @param visited     array tracking visited vertices
     * @param quiet       if true, suppresses console output
     */
    private void dfsHelper(final int vertexIndex,
                           final boolean[] visited,
                           final boolean quiet)
    {
        int i;

        visited[vertexIndex] = true;
        lastDFSOrder.add(vertexLabels[vertexIndex]);

        if (!quiet)
        {
            System.out.println("Visiting vertex " + vertexLabels[vertexIndex]);
        }

        for (i = 0; i < n; i++)
        {
            if (adjacencyMatrix[vertexIndex][i] == 1 && !visited[i])
            {
                dfsHelper(i, visited, quiet);
            }
        }

        lastDFSDeadEndOrder.add(vertexLabels[vertexIndex]);
    }

    /**
     * Performs Breadth First Search on the graph.
     * Visits all vertices even if the graph is disconnected.
     *
     * @param quiet if true, suppresses console output
     */
    public void runBFS(final boolean quiet)
    {
        boolean[] visited;
        int i;

        visited = new boolean[n];
        lastBFSOrder = new ArrayList<>();
        hasRunBFS = true;

        for (i = 0; i < n; i++)
        {
            if (!visited[i])
            {
                bfsHelper(i, visited, quiet);
            }
        }
    }

    /**
     * Performs Breadth First Search starting from a specific vertex.
     * Only visits vertices reachable from the starting vertex.
     *
     * @param v     the starting vertex label
     * @param quiet if true, suppresses console output
     */
    public void runBFS(final String v,
                       final boolean quiet)
    {
        int startIndex;
        startIndex = getIndex(v);

        if (startIndex == -1)
        {
            return;
        }

        boolean[] visited;

        visited = new boolean[n];
        lastBFSOrder = new ArrayList<>();
        hasRunBFS = true;

        bfsHelper(startIndex, visited, quiet);
    }

    /**
     * Helper method for Breadth First Search using a Queue.
     *
     * @param startIndex the internal index of the starting vertex
     * @param visited    array tracking visited vertices
     * @param quiet      if true, suppresses console output
     */
    private void bfsHelper(final int startIndex,
                           final boolean[] visited,
                           final boolean quiet)
    {
        List<Integer> queue;
        int currentIndex;
        int i;

        queue = new ArrayList<>();

        visited[startIndex] = true;
        queue.add(startIndex);

        while (!queue.isEmpty())
        {
            currentIndex = queue.remove(0);
            lastBFSOrder.add(vertexLabels[currentIndex]);

            if (!quiet)
            {
                System.out.println("BFS visiting vertex " + vertexLabels[currentIndex]);
            }

            for (i = 0; i < n; i++)
            {
                if (adjacencyMatrix[currentIndex][i] == 1 && !visited[i])
                {
                    visited[i] = true;
                    queue.add(i); // enqueue
                }
            }
        }
    }

    /**
     * Gets the result of the most recently performed DFS.
     *
     * @return formatted string of DFS order, or an error message
     */
    public String getLastDFSOrder()
    {
        if (!hasRunDFS)
        {
            return "Error: DFS has not been run yet.";
        }
        return String.join(" -> ", lastDFSOrder);
    }

    /**
     * Gets the dead-end order result of the most recently performed DFS.
     *
     * @return formatted string of DFS dead-end order, or an error message
     */
    public String getLastDFSDeadEndOrder()
    {
        if (!hasRunDFS)
        {
            return "Error: DFS has not been run yet.";
        }
        return String.join(" -> ", lastDFSDeadEndOrder);
    }

    /**
     * Gets the result of the most recently performed BFS.
     *
     * @return formatted string of BFS order, or an error message
     */
    public String getLastBFSOrder()
    {
        if (!hasRunBFS)
        {
            return "Error: BFS has not been run yet.";
        }
        return String.join(" -> ", lastBFSOrder);
    }
}