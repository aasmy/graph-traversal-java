## Graph Traversal (Java)

This implementation models a graph using an adjacency matrix with support for both directed and undirected edges. It provides DFS and BFS traversal operations while preserving vertex ordering to ensure deterministic traversal behavior. 

The code is structured with clean and modular logic, separating traversal behavior into focused methods while maintaining traversal state internally, including visit order and DFS finish (dead-end) order. The implementation handles disconnected graphs by iterating across all components, and traversal methods can be executed from a specific starting vertex or across the full graph, with consistent state reset between runs to avoid side effects.

This work was developed as part of the COMP 3760 course (2026, BCIT). Full project specifications and requirements are included in the PDF in this repository.
