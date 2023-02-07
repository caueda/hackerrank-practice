package hackerrank.graph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Graph {
    private Map<Vertex, List<Vertex>> adjVertices = new HashMap<>();

    public void addVertex(String label) {
        var vertex = new Vertex(label);
        adjVertices.putIfAbsent(vertex, new ArrayList<>());
    }

    public void removeVertex(String label) {
        var vertex = new Vertex(label);
        adjVertices.values().stream().forEach(e -> e.remove(vertex));
        adjVertices.remove(vertex);
    }

    public void addEdge(String label1, String label2) {
        var v1 = new Vertex(label1);
        var v2 = new Vertex(label2);
        adjVertices.get(v1).add(v2);
        adjVertices.get(v2).add(v1);
    }

    public void removeEdge(String label1, String label2) {
        var v1 = new Vertex(label1);
        var v2 = new Vertex(label2);
        List<Vertex> eV1 = adjVertices.get(v1);
        List<Vertex> eV2 = adjVertices.get(v2);
        if(eV1 != null) {
            eV1.remove(v2);
        }
        if(eV2 != null) {
            eV2.remove(v1);
        }
    }

    public List<Vertex> getAdjVertices(String label) {
        return adjVertices.get(new Vertex(label));
    }

    public Set<String> depthFirstTraversal(Graph graph, String root) {
        Set<String> visited = new LinkedHashSet<>();
        Stack<String> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            String vertex = stack.pop();
            if(!visited.contains(vertex)) {
                visited.add(vertex);
                for(Vertex v : graph.getAdjVertices(vertex)) {
                    stack.push(v.getLabel());
                }
            }
        }
        return visited;
    }

    public Set<String> breadthFirstTraversal(Graph graph, String root) {
        Set<String> visited = new LinkedHashSet<>();
        Queue<String> stack = new LinkedList<>();
        stack.add(root);
        while(!stack.isEmpty()) {
            String vertex = stack.poll();
            if(!visited.contains(vertex)) {
                visited.add(vertex);
                for(Vertex v : graph.getAdjVertices(vertex)) {
                    stack.add(v.getLabel());
                }
            }
        }
        return visited;
    }
}
