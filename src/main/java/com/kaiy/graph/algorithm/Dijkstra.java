package com.kaiy.graph.algorithm;


import com.kaiy.graph.struct.Edge;
import com.kaiy.graph.struct.Graph;
import com.kaiy.graph.struct.Vertex;

import java.util.*;

/**
 * Shortest Path
 */
public class Dijkstra {

    public void search(Graph graph, Vertex s, Vertex t) {
        Map<Vertex, Integer> dist = new HashMap<>();
        graph.vertices().forEach(v -> dist.put(v, (Integer.MAX_VALUE >> 1) - 1));
        Map<Vertex, Vertex> parent = new HashMap<>();
        PriorityQueue<SearchVertex> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        queue.offer(new SearchVertex(s, 0));
        dist.put(s, 0);
        Set<Vertex> vertices = new HashSet<>();
        vertices.add(s);
        while (!queue.isEmpty()) {
            SearchVertex searchVertex = queue.poll();
            Vertex curVertex = searchVertex.vertex;
            Collection<Edge> edges = graph.outEdges(curVertex);
            if (edges == null || edges.isEmpty()) {
                continue;
            }
            for (Edge edge : edges) {
                int newToW = edge.getW() + dist.get(curVertex);
                Vertex toVertex = graph.vertex(edge.getTo());
                Integer oldToW = dist.get(toVertex);
                if (newToW < oldToW) {
                    dist.put(toVertex, newToW);
                    parent.put(toVertex, curVertex);
                }
                if (!vertices.contains(toVertex)) {
                    queue.offer(new SearchVertex(toVertex, Math.min(newToW, oldToW)));
                }
            }
        }

        Vertex toT = parent.get(t);
        if (toT == null) {
            System.out.println("没有到达s的路径");
            return;
        }

        LinkedList<String> list = new LinkedList<>();
        list.addFirst(t.getId());
        Vertex to = t;
        while (true) {
            Vertex vertex = parent.get(to);
            if (vertex == null) {
                break;
            }
            list.addFirst(vertex.getId());
            to = vertex;
            if (vertex == s) {
                break;
            }
        }
        System.out.println("short path = " + dist.get(t));
        System.out.println("path = " + list);
    }


    public static void main(String[] args) {

        Vertex s = new Vertex("s");
        Vertex vertex2 = new Vertex("2");
        Vertex vertex3 = new Vertex("3");
        Vertex vertex4 = new Vertex("4");
        Vertex vertex5 = new Vertex("5");
        Vertex t = new Vertex("t");

        List<Vertex> vertices = List.of(s, vertex2, vertex3, vertex4, vertex5, t);

        Edge edge1 = new Edge(s.getId(), vertex2.getId(), 1);
        Edge edge2 = new Edge(s.getId(), vertex3.getId(), 2);
        Edge edge3 = new Edge(vertex2.getId(), vertex4.getId(), 3);
        Edge edge4 = new Edge(vertex2.getId(), vertex5.getId(), 4);
        Edge edge5 = new Edge(vertex3.getId(), vertex4.getId(), 5);
        Edge edge6 = new Edge(vertex3.getId(), vertex5.getId(), 6);
        Edge edge7 = new Edge(vertex4.getId(), t.getId(), 17);
        Edge edge8 = new Edge(vertex5.getId(), t.getId(), 8);

        /**
         *      2     4
         * s              t
         *      3     5
         */

        List<Edge> edges = List.of(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8);
        Graph graph = Graph.build(vertices, edges);
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.search(graph, s, t);
    }

    record SearchVertex(Vertex vertex, int dist) {
    }
}
